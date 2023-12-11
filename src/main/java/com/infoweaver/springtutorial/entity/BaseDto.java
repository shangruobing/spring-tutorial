package com.infoweaver.springtutorial.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * @author Ruobing Shang 2023-09-24 20:11
 */
@Data
public class BaseDto {
    @Min(value = 1, message = "页码最小为1")
    @Min(value = 1, message = "页码最多为99999")
    private Integer page = 1;
    @Min(value = 1, message = "页面尺寸最小为1")
    @Max(value = 30, message = "页面尺寸最大为30")
    private Integer pageSize = 10;
    /**
     * 排序字段，驼峰命名区分大小写
     */
    private String orderBy;
    /**
     * 排序顺序，默认从小到大ASC，否则从大到小DESC；
     * 该字段只会在传入orderBy时生效
     */
    private Boolean isAsc = false;
}
