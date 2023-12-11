package com.infoweaver.springtutorial.po;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Ruobing Shang 2023-11-01 22:47
 */
@Data
@AllArgsConstructor
public class ExcelMapping {
    /**
     * 中文列名
     */
    private String columnName;
    /**
     * 英文属性名称
     */
    private String property;
}
