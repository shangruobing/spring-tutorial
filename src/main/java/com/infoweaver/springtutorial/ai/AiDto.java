package com.infoweaver.springtutorial.ai;

import com.infoweaver.springtutorial.entity.BaseDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ruobing Shang 2025-08-05 08:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AiDto extends BaseDto {
    @NotNull(message = "model不能为空")
    private String model;
    @NotNull(message = "prompt不能为空")
    private String prompt;
}
