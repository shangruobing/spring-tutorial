package com.infoweaver.springtutorial.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Ruobing Shang 2023-10-12 18:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("company")
public class Company extends BaseEntity {
    /**
     * 公司名称
     */
    protected String name;
    /**
     * 公司类型
     */
    protected String type;
}
