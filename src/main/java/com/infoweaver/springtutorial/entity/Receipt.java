package com.infoweaver.springtutorial.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ruobing Shang 2022-09-02 10:11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_receipt")
public class Receipt extends Model<Receipt> {
    private String id;
    private Date date;
    private BigDecimal totalPrice;
    private String seller;
    private String cashier;
    private Integer status;

    public Receipt(Receipt receipt) {
        this.id = receipt.getId();
        this.date = receipt.getDate();
        this.totalPrice = receipt.getTotalPrice();
        this.seller = receipt.getSeller();
        this.cashier = receipt.getCashier();
        this.status = receipt.getStatus();

    }
}
