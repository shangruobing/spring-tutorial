package com.infoweaver.springtutorial.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.Receipt;
import com.infoweaver.springtutorial.entity.vo.ReceiptVo;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-02 10:36
 */

public interface IReceiptService extends IService<Receipt> {
    /**
     * Retrieve All Receipt.
     *
     * @return Receipt List
     */
    List<Receipt> listReceipts();

    /**
     * Retrieve a Receipt by id.
     *
     * @param id receipt id
     * @return a Receipt instance
     */
    Receipt getReceiptById(String id);

    /**
     * Create a Receipt instance.
     *
     * @param receipt receipt object
     * @return a status
     */
    int saveReceipt(Receipt receipt);

    /**
     * Delete a receipt instance.
     *
     * @param id receipt id
     * @return a status
     */
    int removeReceipt(String id);

    /**
     * Update a receipt instance.
     *
     * @param receipt receipt object
     * @return a status
     */
    int updateReceipt(Receipt receipt);

    /**
     * Get a receipt instance include receipt details.
     *
     * @param id receipt id
     * @return a status
     */
    ReceiptVo getReceiptVoById(String id);

    /**
     * Get all receipt instances include receipt details.
     *
     * @return all ReceiptVo
     */
    List<ReceiptVo> listReceiptVos();

    /**
     * Create a Receipt instance include receipt details.
     *
     * @param receiptVo receipt object include receipt details.
     * @return Receipt Id
     */
    String saveReceiptVo(ReceiptVo receiptVo);

    /**
     * Payment with ReceiptId.
     *
     * @param receiptId Receipt Id
     * @return a status
     */
    int payment(String receiptId);

    /**
     * Product out bound.
     *
     * @param receiptId Receipt Id
     * @return a status
     */
    int outbound(String receiptId);

    /**
     * Customer take delivery.
     *
     * @param receiptId Receipt Id
     * @return a status
     */
    int customerTakeDelivery(String receiptId);
}
