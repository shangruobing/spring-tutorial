package com.infoweaver.springtutorial.common;

import com.infoweaver.springtutorial.po.ExcelMapping;
import com.infoweaver.springtutorial.po.ExcelPrecheckResult;
import com.infoweaver.springtutorial.po.ExcelUnmatchedColumn;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ruobing Shang 2023-10-31 17:36
 */
@Data
@Slf4j
public class ExcelUploader<T> {
    /**
     * 类实例
     */
    private Class<?> cls;
    /**
     * 文件对象
     */
    private MultipartFile file;
    /**
     * 表对象
     */
    private XSSFSheet sheet;
    /**
     * 所需列名 中文
     */
    private List<String> requiredColumns;
    /**
     * 所需属性 英文
     */
    private List<String> properties;
    /**
     * 上传的列数量
     */
    private int numberOfColumns;

    public ExcelUploader(Class<?> cls, MultipartFile file, List<ExcelMapping> mappings) {
        this.cls = cls;
        this.file = file;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            this.sheet = workbook.getSheetAt(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.requiredColumns = mappings.stream().map(ExcelMapping::getColumnName).collect(Collectors.toList());
        this.properties = mappings.stream().map(ExcelMapping::getProperty).collect(Collectors.toList());
        this.numberOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
    }

    /**
     * 对上传的Excel进行预检
     *
     * @return 预检结果 ExcelPrecheckResult
     */
    public ResponseEntity<ExcelPrecheckResult> precheckUpload() {
        /**
         * 需要预览的行的数量
         * 如果上传文件小于10行，则取所有；否则取前10行
         */
        int numberOfPreviewRow = Math.min(sheet.getPhysicalNumberOfRows(), 11);
        /**
         * 上传文件的列名列表
         */
        List<String> uploadColumnNames = parseColumnNames();
        /**
         * 不匹配的列
         */
        List<ExcelUnmatchedColumn> unmatchedColumns = getUnmatchedColumns(requiredColumns, uploadColumnNames);
        /**
         * 错误的行
         */
        List<Integer> errorLineList = getErrorLineList();
        /**
         * 获取检查结果
         */
        String message = getPrecheckMessage(uploadColumnNames, unmatchedColumns, errorLineList);
        /**
         * 设置预检结果
         */
        ExcelPrecheckResult excelPrecheckResult = new ExcelPrecheckResult();
        excelPrecheckResult.setRequiredColumnNames(requiredColumns);
        excelPrecheckResult.setUploadColumnNames(uploadColumnNames);
        excelPrecheckResult.setNumberOfColumns(uploadColumnNames.size());
        excelPrecheckResult.setPreviewRows(parseRowData(getRows(numberOfPreviewRow)));
        excelPrecheckResult.setNumberOfRows(sheet.getPhysicalNumberOfRows());
        excelPrecheckResult.setUnmatchedColumns(unmatchedColumns);
        excelPrecheckResult.setErrorLineList(errorLineList);
        excelPrecheckResult.setMessage(message);
        excelPrecheckResult.setState("ok".equals(message));
        if (excelPrecheckResult.getState()) {
            return ResponseEntity.ok(excelPrecheckResult);
        } else {
            return ResponseEntity.badRequest().body(excelPrecheckResult);
        }
    }

    /**
     * 获取实体列表
     *
     * @return 实体列表
     */
    public List<T> getEntityList() {
        try {
            return parseRowData(getRows(sheet.getPhysicalNumberOfRows()))
                    .stream()
                    .map(row -> setValuesByReflect(cls, properties, row))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("数据有误，请先预检EXCEL文件");
        }
    }

    /**
     * 获取错误行列表
     *
     * @return 实体列表
     */
    public List<Integer> getErrorLineList() {
        int count = 1;
        List<Integer> errorLineList = new ArrayList<>();
        for (final List<String> parseRowDatum : parseRowData(getRows(sheet.getPhysicalNumberOfRows()))) {
            try {
                setValuesByReflect(cls, properties, parseRowDatum);
            } catch (Exception e) {
                errorLineList.add(count);
            } finally {
                count++;
            }
        }
        return errorLineList;
    }

    /**
     * 抽取列名称
     *
     * @return 列名的列表中文
     */
    private List<String> parseColumnNames() {
        List<String> columnNames = new ArrayList<>();
        Row headerRow = sheet.getRow(0);
        for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
            Cell cell = headerRow.getCell(colIndex);
            columnNames.add(cell.getStringCellValue());
        }
        return columnNames;
    }

    /**
     * 获取行
     *
     * @param numberOfRow 行的数目
     * @return 行列表
     */
    private List<Row> getRows(int numberOfRow) {
        List<Row> rows = new ArrayList<>();
        for (int i = 1; i < numberOfRow; i++) {
            rows.add(sheet.getRow(i));
        }
        return rows.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 解析行数据
     *
     * @param rowList 行数据转为String列表
     * @return String列表
     */
    private List<List<String>> parseRowData(List<Row> rowList) {
        List<List<String>> rows = new ArrayList<>();
        rowList.forEach(row -> {
            List<String> cells = new ArrayList<>();
            for (int colIndex = 0; colIndex < numberOfColumns; colIndex++) {
                Optional.ofNullable(row.getCell(colIndex)).ifPresentOrElse(
                        cell -> {
                            /**
                             * @issue 2023-11-13
                             * 全都转为String丢失了Excel的数据类型
                             * 如年份转换为45223等
                             */
                            switch (cell.getCellType()) {
                                case STRING -> cells.add(cell.getStringCellValue());
                                case NUMERIC -> cells.add(String.valueOf(cell.getNumericCellValue()));
                                case FORMULA -> cells.add(String.valueOf(cell.getCellFormula()));
                                case BOOLEAN -> cells.add(String.valueOf(cell.getBooleanCellValue()));
                                /**
                                 * _NONE, BLANK, ERROR
                                 */
                                default -> cells.add("");
                            }
                        },
                        /**
                         * null
                         */
                        () -> cells.add("")
                );
            }
            rows.add(cells);
        });
        return rows;
    }

    /**
     * 检查列是否匹配
     *
     * @param requiredColumnNames 需要的列名
     * @param uploadColumnNames   上传文件的列名
     * @return 不匹配列的列表
     */
    private List<ExcelUnmatchedColumn> getUnmatchedColumns(List<String> requiredColumnNames, List<String> uploadColumnNames) {
        List<ExcelUnmatchedColumn> unmatchedColumns = new ArrayList<>();
        for (int i = 0; i < requiredColumnNames.size(); i++) {
            if (!requiredColumnNames.get(i).equals(uploadColumnNames.get(i))) {
                unmatchedColumns.add(new ExcelUnmatchedColumn(requiredColumnNames.get(i), uploadColumnNames.get(i)));
            }
        }
        return unmatchedColumns;
    }

    /**
     * @param uploadColumnNames 上传文件列名
     * @param unmatchedColumns  不匹配列的列表
     * @return 修订信息
     */
    private String getPrecheckMessage(List<String> uploadColumnNames, List<ExcelUnmatchedColumn> unmatchedColumns, List<Integer> errorLineList) {
        String message = "ok";
        if (uploadColumnNames.size() != requiredColumns.size() || !unmatchedColumns.isEmpty()) {
            message = "上传的文件与模板不匹配";
        } else if (!errorLineList.isEmpty()) {
            message = "存在有错误的行";
        }
        return message;
    }

    /**
     * 根据反射调用set为实体赋值
     *
     * @param cls         类
     * @param columnNames 列名列表
     * @param values      值的列表
     * @return 实体
     */
    @SuppressWarnings("unchecked")
    private T setValuesByReflect(Class<?> cls, List<String> columnNames, List<String> values) {
        try {
            Constructor<?> constructor = cls.getConstructor();
            Object entity = constructor.newInstance();
            for (int i = 0; i < columnNames.size(); i++) {
                String columnName = columnNames.get(i);
                Object value = values.get(i);
                /**
                 * 拼接出setter方法
                 */
                String setterMethodName = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                for (final Method method : entity.getClass().getMethods()) {
                    /**
                     * 查找set方法
                     */
                    if (method.getName().equals(setterMethodName)) {
                        /**
                         * set方法只有一个参数，将值转换为对应类型
                         */
                        value = convertValueFormat(method.getParameterTypes()[0].getSimpleName(), value);
                        break;
                    }
                }
                Method setterMethod = entity.getClass().getMethod(setterMethodName, value.getClass());
                setterMethod.invoke(entity, value);
            }
            return (T) entity;
        } catch (UnknownFormatConversionException | InstantiationException |
                 NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 将String值的类型转换为标准数据类型
     *
     * @param parameterType 方法定义的值类型
     * @param value         值
     * @return 标准数据类型的值
     */
    private Object convertValueFormat(String parameterType, Object value) {
//        System.out.println(parameterType);
//        System.out.println(value);
//        System.out.println(value.getClass());
//        System.out.println();
        switch (parameterType) {
            case "Integer" -> {
                try {
                    value = (int) Double.parseDouble(value.toString());
                } catch (Exception e) {
                    log.error("Integer: " + e.getMessage());
                    throw new UnknownFormatConversionException(e.getMessage());
                }
            }
            case "Boolean" -> {
                try {
                    value = Boolean.valueOf(value.toString());
                } catch (Exception e) {
                    log.error("Boolean: " + e.getMessage());
                    throw new UnknownFormatConversionException(e.getMessage());
                }
            }
            case "BigDecimal" -> {
                try {
                    value = BigDecimal.valueOf(Double.parseDouble(value.toString()));
                } catch (Exception e) {
                    log.error("BigDecimal: " + e.getMessage());
                    throw new UnknownFormatConversionException(e.getMessage());
                }
            }
            case "LocalDateTime" -> {
                try {
                    value = LocalDateTime.parse(value.toString());
                } catch (Exception e) {
                    log.error("LocalDateTime: " + e.getMessage());
                    throw new UnknownFormatConversionException(e.getMessage());
                }
            }
            default -> value = value.toString();
        }
        return value;
    }
}
