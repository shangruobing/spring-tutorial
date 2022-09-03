package com.infoweaver.springtutorial.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.StockAccount;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-02 10:36
 */

public interface IStockAccountService extends IService<StockAccount> {
    /**
     * Retrieve All StockAccount.
     *
     * @return StockAccount List
     */
    List<StockAccount> listStockAccounts();

    /**
     * Retrieve a StockAccount by id.
     *
     * @param id stockAccount id
     * @return a StockAccount instance
     */
    StockAccount getStockAccountById(String id);

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

}
