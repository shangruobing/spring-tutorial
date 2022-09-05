package com.infoweaver.springtutorial.controller;

import com.infoweaver.springtutorial.entity.StockAccount;
import com.infoweaver.springtutorial.service.impl.StockAccountServiceImpl;
import com.infoweaver.springtutorial.vo.StockAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@RestController
public class StockAccountController {
    private final StockAccountServiceImpl stockAccountService;

    @Autowired
    public StockAccountController(StockAccountServiceImpl stockAccountService) {
        this.stockAccountService = stockAccountService;
    }

    @GetMapping("/stockAccount")
    public List<StockAccountVo> selectAllStockAccount(
            @RequestParam(value = "brand", defaultValue = "", required = false) String brand,
            @RequestParam(value = "model", defaultValue = "", required = false) String model
    ) {
        return stockAccountService.listStockAccounts(brand, model);
    }

    @GetMapping("/stockAccount/{id}")
    public StockAccountVo getStockAccountById(@PathVariable("id") String id) {
        return stockAccountService.getStockAccountById(id);
    }

    @PostMapping("/stockAccount")
    public int add(@RequestBody StockAccount stockAccount) {
        return stockAccountService.saveStockAccount(stockAccount);
    }

    @PutMapping("/stockAccount")
    public int update(@RequestBody StockAccount stockAccount) {
        return stockAccountService.updateStockAccount(stockAccount);
    }

    @DeleteMapping("/stockAccount/{id}")
    public int delete(@PathVariable("id") String id) {
        return stockAccountService.removeStockAccount(id);
    }


    @PostMapping("/enter_stock")
    public int productsEnterStock(@RequestBody List<StockAccount> stockAccounts) {
        return stockAccountService.saveOrUpdateStockAccountBatch(stockAccounts);
    }

}


