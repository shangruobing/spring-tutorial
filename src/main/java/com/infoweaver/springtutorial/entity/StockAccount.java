package com.infoweaver.springtutorial.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Ruobing Shang 2022-09-02 10:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_stock_account")
public class StockAccount extends Model<StockAccount> {
    private String id;
    private String productId;
    private String employeeId;
    private Date date;
    private Integer quantity;
    private Integer kept;
    private Integer balance;
    private String note;

    public StockAccount(StockAccount stockAccount) {
        this.id = stockAccount.getId();
        this.productId = stockAccount.getProductId();
        this.employeeId = stockAccount.getEmployeeId();
        this.date = stockAccount.getDate();
        this.quantity = stockAccount.getQuantity();
        this.kept = stockAccount.getKept();
        this.balance = stockAccount.getBalance();
        this.note = stockAccount.getNote();
    }
}
