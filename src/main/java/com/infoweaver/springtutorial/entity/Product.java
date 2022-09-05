package com.infoweaver.springtutorial.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.math.BigDecimal;


/**
 * @author Ruobing Shang 2022-09-02 10:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_product")
public class Product extends Model<Product> {
    private String id;
    private String brand;
    private String type;
    private String model;
    private BigDecimal price;

    public String getProductName() {
        return this.brand.trim() + " " + this.type.trim() + " " + this.model.trim();
    }
}
