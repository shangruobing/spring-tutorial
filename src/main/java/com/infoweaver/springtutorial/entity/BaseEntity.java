package com.infoweaver.springtutorial.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Ruobing Shang 2023-10-31 16:21
 */
@Data
@Accessors(chain = true)
public class BaseEntity {
    /**
     * 记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    protected Integer id;
    /**
     * 创建时间 自动录入 但这样会导致在插入和更新返回结果中没有值，必须读取数据库才行
     * create_time DATETIME DEFAULT NOW()
     */
    protected LocalDateTime createTime;
    /**
     * 更新时间 自动录入
     * update_time  DATETIME DEFAULT NOW() ON UPDATE NOW()
     */
    protected LocalDateTime updateTime;
    /**
     * 创建用户id 手动录入
     */
    protected Integer createUserId;
    /**
     * 更新用户id 手动录入
     */
    protected Integer updateUserId;
    /**
     * 逻辑删除
     */
    @JsonIgnore
    @TableLogic
    private Integer deleted;
}
