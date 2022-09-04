package com.infoweaver.springtutorial.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.ReceiptDetail;

import java.util.List;
import java.util.Set;

/**
 * @author Ruobing Shang 2022-09-02 10:36
 */

public interface IReceiptDetailService extends IService<ReceiptDetail> {
    /**
     * Retrieve All ReceiptDetail.
     *
     * @return ReceiptDetail List
     */
    List<ReceiptDetail> listReceiptDetails();

    /**
     * Retrieve a ReceiptDetail by id.
     *
     * @param id receiptDetail id
     * @return a ReceiptDetail instance
     */
    ReceiptDetail getReceiptDetailById(String id);

    /**
     * Create a ReceiptDetail instance.
     *
     * @param receiptDetail receiptDetail object
     * @return a status code
     */
    int saveReceiptDetail(ReceiptDetail receiptDetail);

    /**
     * Update a receiptDetail instance.
     *
     * @param receiptDetail receiptDetail object
     * @return a status code
     */
    int updateReceiptDetail(ReceiptDetail receiptDetail);

    /**
     * Delete a receiptDetail instance.
     *
     * @param id receiptDetail id
     * @return a status code
     */
    int removeReceiptDetail(String id);

    /**
     * Retrieve a ReceiptDetail by id.
     *
     * @param receiptId Receipt Id
     * @return a ReceiptDetail list
     */
    List<ReceiptDetail> listReceiptDetailsByReceiptId(String receiptId);

    /**
     * Retrieve a ReceiptDetail by id set.
     *
     * @param receiptIds Receipt Id Set
     * @return a ReceiptDetail list
     */
    List<ReceiptDetail> listReceiptDetailsByReceiptIdSet(Set<String> receiptIds);
}
