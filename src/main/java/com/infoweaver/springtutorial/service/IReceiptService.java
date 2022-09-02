package com.infoweaver.springtutorial.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.Receipt;
import com.infoweaver.springtutorial.vo.ReceiptVo;

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
    List<Receipt> getReceiptList();

    /**
     * Retrieve a Receipt by id.
     *
     * @param id receipt id
     * @return a Receipt instance
     */
    Receipt getReceiptById(Long id);

    /**
     * Create a Receipt instance.
     *
     * @param receipt receipt object
     * @return a status code
     */
    int addReceipt(Receipt receipt);

    /**
     * Update a receipt instance.
     *
     * @param receipt receipt object
     * @return a status code
     */
    int editReceipt(Receipt receipt);

    /**
     * Delete a receipt instance.
     *
     * @param id receipt id
     * @return a status code
     */
    int removeReceipt(Long id);

    /**
     * Get a receipt instance include receipt details.
     *
     * @param id receipt id
     * @return a status code
     */
    ReceiptVo getReceiptInformationById(Long id);

    /**
     * Get all receipt instances include receipt details.
     *
     * @return a status code
     */
    List<ReceiptVo> getAllReceiptInformation();

    /**
     * Create a Receipt instance include receipt details.
     *
     * @param receiptVo receipt object include receipt details.
     * @return a status code
     */
    int addReceiptVo(ReceiptVo receiptVo);
}
