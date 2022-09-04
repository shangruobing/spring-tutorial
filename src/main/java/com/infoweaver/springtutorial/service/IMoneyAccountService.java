package com.infoweaver.springtutorial.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.MoneyAccount;
import com.infoweaver.springtutorial.entity.Receipt;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ruobing Shang 2022-09-02 10:36
 */

public interface IMoneyAccountService extends IService<MoneyAccount> {
    /**
     * Retrieve All MoneyAccount.
     *
     * @return MoneyAccount List
     */
    List<MoneyAccount> listMoneyAccounts();

    /**
     * Retrieve a MoneyAccount by id.
     *
     * @param id moneyAccount id
     * @return a MoneyAccount instance
     */
    MoneyAccount getMoneyAccountById(String id);

    /**
     * Create a MoneyAccount instance.
     *
     * @param moneyAccount moneyAccount object
     * @return a status code
     */
    int saveMoneyAccount(MoneyAccount moneyAccount);

    /**
     * Update a moneyAccount instance.
     *
     * @param moneyAccount moneyAccount object
     * @return a status code
     */
    int updateMoneyAccount(MoneyAccount moneyAccount);

    /**
     * Delete a moneyAccount instance.
     *
     * @param id moneyAccount id
     * @return a status code
     */
    int removeMoneyAccount(String id);

    /**
     * Create a moneyAccount instance with Receipt.
     *
     * @param receipt Receipt object
     * @return a status code
     */
    int saveMoneyAccountByReceipt(Receipt receipt);

    /**
     * Sum the all moneyAccounts.
     *
     * @return sum value
     */
    BigDecimal sumAllMoneyAccounts();
}
