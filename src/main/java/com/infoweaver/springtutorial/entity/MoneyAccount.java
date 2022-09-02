package com.infoweaver.springtutorial.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author Ruobing Shang 2022-09-02 10:23
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_money_account")
public class MoneyAccount extends Model<MoneyAccount> {
    private String id;
    private String receiptId;
    private String employeeId;
    private Date date;
    private BigDecimal total;
}
