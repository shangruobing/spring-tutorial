package com.infoweaver.springtutorial.vo;

import com.infoweaver.springtutorial.entity.Product;
import com.infoweaver.springtutorial.entity.StockAccount;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author Ruobing Shang 2022-09-05 8:57
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class StockAccountVO extends StockAccount {
    private Product product;

    public StockAccountVO(StockAccount stockAccount) {
        super(stockAccount);
    }
}
