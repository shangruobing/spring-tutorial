package com.infoweaver.springtutorial.vo;

/**
 * @author Ruobing Shang 2022-09-01 20:53
 */

import com.infoweaver.springtutorial.entity.Receipt;
import com.infoweaver.springtutorial.entity.ReceiptDetail;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptVO extends Receipt {
    private List<ReceiptDetail> receiptDetails;

    public ReceiptVO(Receipt receipt) {
        super(receipt);
    }
}