package com.infoweaver.springtutorial.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Ruobing Shang 2023-10-31 08:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ExcelPrecheckResult {
    /**
     * 所需的列名
     */
    private List<String> requiredColumnNames;
    /**
     * 上传的列名
     */
    private List<String> uploadColumnNames;
    /**
     * 列数
     */
    private Integer numberOfColumns;
    /**
     * 预览的行数
     */
    private List<List<String>> previewRows;
    /**
     * 行数
     */
    private Integer numberOfRows;
    /**
     * 不匹配的列
     */
    private List<ExcelUnmatchedColumn> unmatchedColumns;
    /**
     * 错误行列表
     */
    private List<Integer> errorLineList;
    /**
     * 是否通过
     */
    private Boolean state;
    /**
     * 消息
     */
    private String message;
}
