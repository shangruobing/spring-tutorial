package com.infoweaver.springtutorial.dto;

import com.infoweaver.springtutorial.entity.BaseDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ruobing Shang 2023-10-12 23:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyDto extends BaseDto {
    /**
     * 记录Id
     */
    @NotNull(message = "ID不能为空", groups = {ValidatedGroup.Update.class, ValidatedGroup.Delete.class})
    private Integer id;
    /**
     * 公司名称
     */
    @NotNull(message = "公司名称不能为空", groups = {ValidatedGroup.Create.class, ValidatedGroup.Update.class})
    private String name;
    /**
     * 公司类型
     */
    @NotNull(message = "公司类型不能为空", groups = {ValidatedGroup.Create.class, ValidatedGroup.Update.class})
    private String type;
}
