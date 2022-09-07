package com.infoweaver.springtutorial.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Ruobing Shang 2022-09-07 9:26
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_stock_taking")
public class StockTaking extends Model<StockTaking> {
    private String id;
    private String productId;
    private String employeeId;
    private Date date;
    private Integer quantity;
    private Integer kept;
    private Integer balance;
    private String note;

}
