package com.infoweaver.springtutorial.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ruobing Shang 2022-09-07 9:26
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_money_taking")
public class MoneyTaking extends Model<MoneyTaking> {
    private String id;
    private Date date;
    private String employeeId;
    private BigDecimal beginningBalance;
    private BigDecimal endingBalance;
    private BigDecimal totalPrice;
    private BigDecimal balance;
    private String note;

}
