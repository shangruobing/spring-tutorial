package com.infoweaver.springtutorial.controller;

import com.infoweaver.springtutorial.entity.StockAccount;
import com.infoweaver.springtutorial.service.impl.StockAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@RestController
public class StockController {
    private StockAccountServiceImpl StockAccountService;

    @Autowired
    public void setStockAccountService(StockAccountServiceImpl StockAccountService) {
        this.StockAccountService = StockAccountService;
    }

    @GetMapping("/stockAccount")
    public List<StockAccount> selectAllStockAccount() {
        return StockAccountService.getStockAccountList();
    }

    @GetMapping("/stockAccount/{id}")
    public StockAccount getStockAccountById(@PathVariable("id") long id) {
        return StockAccountService.getStockAccountById(id);
    }


    @PostMapping("/stockAccount")
    public int add(@RequestBody StockAccount StockAccount) {
        return StockAccountService.addStockAccount(StockAccount);
    }

    @PutMapping("/stockAccount")
    public int update(@RequestBody StockAccount StockAccount) {
        return StockAccountService.editStockAccount(StockAccount);
    }

    @DeleteMapping("/stockAccount/{id}")
    public int delete(@PathVariable("id") long id) {
        return StockAccountService.removeStockAccount(id);
    }
}


