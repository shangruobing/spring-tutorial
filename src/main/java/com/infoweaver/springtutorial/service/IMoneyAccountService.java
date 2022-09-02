package com.infoweaver.springtutorial.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.MoneyAccount;

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
    List<MoneyAccount> getMoneyAccountList();

    /**
     * Retrieve a MoneyAccount by id.
     *
     * @param id moneyAccount id
     * @return a MoneyAccount instance
     */
    MoneyAccount getMoneyAccountById(Long id);

    /**
     * Create a MoneyAccount instance.
     *
     * @param moneyAccount moneyAccount object
     * @return a status code
     */
    int addMoneyAccount(MoneyAccount moneyAccount);

    /**
     * Update a moneyAccount instance.
     *
     * @param moneyAccount moneyAccount object
     * @return a status code
     */
    int editMoneyAccount(MoneyAccount moneyAccount);

    /**
     * Delete a moneyAccount instance.
     *
     * @param id moneyAccount id
     * @return a status code
     */
    int removeMoneyAccount(Long id);

}
