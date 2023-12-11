package com.infoweaver.springtutorial.common;

import com.infoweaver.springtutorial.po.ExcelMapping;
import com.infoweaver.springtutorial.util.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Ruobing Shang 2023-11-06 21:32
 */
@Slf4j
public class ExcelExporter<T> {
    /**
     * Excel文件对象
     */
    private final Workbook workbook;
    /**
     * 表对象
     */
    private final Sheet sheet;
    /**
     * 所需属性 英文
     */
    private final List<String> properties;
    /**
     * 文件名称
     */
    private final String name;
    /**
     * 上传的列数量
     */
    private final int numberOfColumns;

    public ExcelExporter(List<ExcelMapping> mappings, String name) {
        this.name = name;
        this.properties = mappings.stream().map(ExcelMapping::getProperty).collect(Collectors.toList());
        this.numberOfColumns = properties.size();
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet(name);
        List<String> requiredColumns = mappings.stream().map(ExcelMapping::getColumnName).toList();
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < properties.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(requiredColumns.get(i));
        }
    }

    private void convertEntityToRow(List<T> entityList) {
        try {
            for (int i = 0; i < entityList.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                T entity = entityList.get(i);
                for (int j = 0; j < numberOfColumns; j++) {
                    String columnName = properties.get(j);
                    String getterMethodName = "get" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    Method getterMethod = entity.getClass().getMethod(getterMethodName);
                    Object value = getterMethod.invoke(entity);
                    Cell cell = dataRow.createCell(j);
                    addValueForCell(cell, value);
                }
            }
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void addValueForCell(Cell cell, Object value) {
        if (Optional.ofNullable(value).isPresent()) {
            if (value instanceof Integer) {
                cell.setCellValue(Integer.parseInt(value.toString()));
            } else if (value instanceof LocalDateTime) {
                cell.setCellValue(DateTimeUtils.formatLocalDateTime((LocalDateTime) value));
            } else if (value instanceof BigDecimal) {
                cell.setCellValue(value.toString());
            } else {
                cell.setCellValue(value.toString());
            }
        } else {
            cell.setCellValue("");
        }
    }

    private void addExtraColumns(List<String> extraColumnNames, List<List<?>> extraColumnsValueList) {
        /**
         * 为标题行添加列
         */
        Row firstRow = sheet.getRow(0);
        for (int i = numberOfColumns; i < numberOfColumns + extraColumnNames.size(); i++) {
            Cell cell = firstRow.createCell(i);
            cell.setCellValue(extraColumnNames.get(i - numberOfColumns));
        }

        for (int i = 0; i < extraColumnsValueList.size(); i++) {
            Row dataRow = sheet.getRow(i + 1);
            for (int j = 0; j < extraColumnNames.size(); j++) {
                Cell cell = dataRow.createCell(j + numberOfColumns);
                Object value = extraColumnsValueList.get(i).get(j);
                addValueForCell(cell, value);
            }
        }
    }

    private ResponseEntity<byte[]> convertToResponse() {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            String filename = name + "导出_" + DateTimeUtils.getNowDateTimeStampStr() + ".xlsx";
            return ResponseEntity
                    .ok()
                    .header("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8))
                    .header("Access-Control-Expose-Headers", "Content-Disposition")
                    .body(out.toByteArray());
        } catch (IOException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<byte[]> exportToExcel(List<T> entityList) {
        convertEntityToRow(entityList);
        return convertToResponse();
    }

    public ResponseEntity<byte[]> exportToExcel(List<T> entityList, List<String> extraColumnNames, List<List<?>> extraColumnsValueList) {
        convertEntityToRow(entityList);
        addExtraColumns(extraColumnNames, extraColumnsValueList);
        return convertToResponse();
    }
}
