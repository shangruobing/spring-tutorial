package com.infoweaver.springtutorial.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.ReceiptDetail;
import com.infoweaver.springtutorial.entity.StockAccount;
import com.infoweaver.springtutorial.entity.vo.StockAccountVo;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-02 10:36
 */

public interface IStockAccountService extends IService<StockAccount> {
    /**
     * Retrieve All StockAccount.
     *
     * @param brand brand
     * @param type  type
     * @return StockAccount List
     */
    List<StockAccountVo> listStockAccounts(String brand, String type);

    /**
     * Retrieve a StockAccount by id.
     *
     * @param id stockAccount id
     * @return a StockAccount instance
     */
    StockAccountVo getStockAccountById(String id);

    /**
     * Create a StockAccount instance.
     *
     * @param stockAccount stockAccount object
     * @return a status code
     */
    int saveStockAccount(StockAccount stockAccount);

    /**
     * Update a stockAccount instance.
     *
     * @param stockAccount stockAccount object
     * @return a status code
     */
    int updateStockAccount(StockAccount stockAccount);

    /**
     * Delete a stockAccount instance.
     *
     * @param id stockAccount id
     * @return a status code
     */
    int removeStockAccount(String id);

    /**
     * Product outbound
     *
     * @param receiptDetails ReceiptDetails
     * @return a status code
     */
    int outbound(List<ReceiptDetail> receiptDetails);

    /**
     * Save or update StockAccount Batch.
     *
     * @param stockAccounts StockAccount
     * @return a status code
     */
    int saveOrUpdateStockAccountBatch(List<StockAccount> stockAccounts);

    /**
     * Save StockTakings.
     *
     * @param stockAccounts StockAccount
     * @return a status code
     */
    int saveStockTakings(List<StockAccount> stockAccounts);
}
