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
    private MoneyAccountServiceImpl MoneyAccountService;

    @Autowired
    public void setMoneyAccountService(MoneyAccountServiceImpl MoneyAccountService) {
        this.MoneyAccountService = MoneyAccountService;
    }

    @GetMapping("/moneyAccount")
    public List<MoneyAccount> selectAllMoneyAccount() {
        return MoneyAccountService.getMoneyAccountList();
    }

    @GetMapping("/moneyAccount/{id}")
    public MoneyAccount getMoneyAccountById(@PathVariable("id") long id) {
        return MoneyAccountService.getMoneyAccountById(id);
    }


    @PostMapping("/moneyAccount")
    public int add(@RequestBody MoneyAccount MoneyAccount) {
        return MoneyAccountService.addMoneyAccount(MoneyAccount);
    }

    @PutMapping("/moneyAccount")
    public int update(@RequestBody MoneyAccount MoneyAccount) {
        return MoneyAccountService.editMoneyAccount(MoneyAccount);
    }

    @DeleteMapping("/moneyAccount/{id}")
    public int delete(@PathVariable("id") long id) {
        return MoneyAccountService.removeMoneyAccount(id);
    }
}


