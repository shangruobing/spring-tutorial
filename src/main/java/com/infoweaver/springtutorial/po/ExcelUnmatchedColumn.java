package com.infoweaver.springtutorial.po;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Ruobing Shang 2023-11-01 22:51
 */

@Data
@AllArgsConstructor
public class ExcelUnmatchedColumn {
    /**
     * 所需列名
     */
    private String required;
    /**
     * 上传的列名
     */
    private String upload;
}
