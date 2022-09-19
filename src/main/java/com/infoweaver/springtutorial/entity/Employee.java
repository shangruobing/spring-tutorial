package com.infoweaver.springtutorial.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


/**
 * @author Ruobing Shang 2022-09-02 9:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_employee")
public class Employee extends Model<Employee> {
    private String id;
    @NotNull(message = "The name field can't be null.")
    private String name;
    private String role;
    /**
     * Use Annotation JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     */
    private LocalDateTime lastLogin;
}
