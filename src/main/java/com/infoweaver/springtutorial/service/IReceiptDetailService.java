package com.infoweaver.springtutorial.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.ReceiptDetail;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-02 10:36
 */

public interface IReceiptDetailService extends IService<ReceiptDetail> {
    /**
     * Retrieve All ReceiptDetail.
     *
     * @return ReceiptDetail List
     */
    List<ReceiptDetail> getReceiptDetailList();

    /**
     * Retrieve a ReceiptDetail by id.
     *
     * @param id receiptDetail id
     * @return a ReceiptDetail instance
     */
    ReceiptDetail getReceiptDetailById(Long id);

    /**
     * Create a ReceiptDetail instance.
     *
     * @param receiptDetail receiptDetail object
     * @return a status code
     */
    int addReceiptDetail(ReceiptDetail receiptDetail);

    /**
     * Update a receiptDetail instance.
     *
     * @param receiptDetail receiptDetail object
     * @return a status code
     */
    int editReceiptDetail(ReceiptDetail receiptDetail);

    /**
     * Delete a receiptDetail instance.
     *
     * @param id receiptDetail id
     * @return a status code
     */
    int removeReceiptDetail(Long id);

}
