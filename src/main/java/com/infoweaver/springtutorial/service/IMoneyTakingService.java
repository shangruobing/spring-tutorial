package com.infoweaver.springtutorial.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.MoneyTaking;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ruobing Shang 2022-09-07 9:53
 */
public interface IMoneyTakingService extends IService<MoneyTaking> {
    /**
     * Retrieve All MoneyTaking.
     *
     * @return MoneyTaking List
     */
    List<MoneyTaking> listMoneyTakings();

    /**
     * Retrieve a MoneyTaking by id.
     *
     * @param id moneyTaking id
     * @return a MoneyTaking instance
     */
    MoneyTaking getMoneyTakingById(String id);

    /**
     * Create a MoneyTaking instance.
     *
     * @param moneyTaking moneyTaking
     * @return a status code
     */

    BigDecimal saveMoneyTaking(MoneyTaking moneyTaking);
}
