package com.infoweaver.springtutorial.controller;

import com.infoweaver.springtutorial.entity.MoneyAccount;
import com.infoweaver.springtutorial.service.impl.MoneyAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@RestController
public class MoneyAccountController {
    private final MoneyAccountServiceImpl moneyAccountService;

    @Autowired
    public MoneyAccountController(MoneyAccountServiceImpl moneyAccountService) {
        this.moneyAccountService = moneyAccountService;
    }

    @GetMapping("/money-account")
    public List<MoneyAccount> selectAllMoneyAccount() {
        return moneyAccountService.listMoneyAccounts();
    }

    @GetMapping("/money-account/{id}")
    public MoneyAccount getMoneyAccountById(@PathVariable("id") String id) {
        return moneyAccountService.getMoneyAccountById(id);
    }


    @PostMapping("/money-account")
    public int add(@RequestBody MoneyAccount moneyAccount) {
        return moneyAccountService.saveMoneyAccount(moneyAccount);
    }

    @PutMapping("/money-account")
    public int update(@RequestBody MoneyAccount moneyAccount) {
        return moneyAccountService.updateMoneyAccount(moneyAccount);
    }

    @DeleteMapping("/money-account/{id}")
    public int delete(@PathVariable("id") String id) {
        return moneyAccountService.removeMoneyAccount(id);
    }

    @GetMapping("/sum-money-account")
    public BigDecimal sumAllMoneyAccounts() {
        return moneyAccountService.sumAllMoneyAccounts();
    }

    @GetMapping("/sum-money-account/{month}")
    public BigDecimal sumMoneyAccountsByMonth(@PathVariable("month") Integer month) {
        return moneyAccountService.sumMoneyAccountsByMonth(month);
    }
}


