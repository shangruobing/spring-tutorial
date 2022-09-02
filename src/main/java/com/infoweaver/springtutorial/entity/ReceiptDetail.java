package com.infoweaver.springtutorial.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Ruobing Shang 2022-09-02 10:21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_receipt_detail")
public class ReceiptDetail extends Model<ReceiptDetail> {
    private String id;
    private String productId;
    private String receiptId;
    private Integer quantity;
    private BigDecimal amount;
}
