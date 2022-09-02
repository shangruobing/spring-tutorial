package com.infoweaver.springtutorial.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;


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
    private String name;
    private String role;
}
