package com.infoweaver.springtutorial.controller;

import com.infoweaver.springtutorial.entity.MoneyAccount;
import com.infoweaver.springtutorial.service.impl.MoneyAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@RestController
public class MoneyAccountController {
    private MoneyAccountServiceImpl moneyAccountService;

    @Autowired
    public void setMoneyAccountService(MoneyAccountServiceImpl moneyAccountService) {
        this.moneyAccountService = moneyAccountService;
    }

    @GetMapping("/moneyAccount")
    public List<MoneyAccount> selectAllMoneyAccount() {
        return moneyAccountService.listMoneyAccounts();
    }

    @GetMapping("/moneyAccount/{id}")
    public MoneyAccount getMoneyAccountById(@PathVariable("id") String id) {
        return moneyAccountService.getMoneyAccountById(id);
    }


    @PostMapping("/moneyAccount")
    public int add(@RequestBody MoneyAccount moneyAccount) {
        return moneyAccountService.saveMoneyAccount(moneyAccount);
    }

    @PutMapping("/moneyAccount")
    public int update(@RequestBody MoneyAccount moneyAccount) {
        return moneyAccountService.updateMoneyAccount(moneyAccount);
    }

    @DeleteMapping("/moneyAccount/{id}")
    public int delete(@PathVariable("id") String id) {
        return moneyAccountService.removeMoneyAccount(id);
    }
}


