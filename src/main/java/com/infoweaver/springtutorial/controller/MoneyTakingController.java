package com.infoweaver.springtutorial.controller;

import com.infoweaver.springtutorial.entity.MoneyTaking;
import com.infoweaver.springtutorial.service.impl.MoneyTakingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ruobing Shang 2022-09-07 10:45
 */

@RestController
public class MoneyTakingController {
    private final MoneyTakingServiceImpl moneyTakingService;

    @Autowired
    public MoneyTakingController(MoneyTakingServiceImpl moneyTakingService) {
        this.moneyTakingService = moneyTakingService;
    }

    @GetMapping("/moneyTaking")
    public List<MoneyTaking> selectAllMoneyTaking() {
        return moneyTakingService.listMoneyTakings();
    }

    @GetMapping("/moneyTaking/{id}")
    public MoneyTaking getMoneyTakingById(@PathVariable("id") String id) {
        return moneyTakingService.getMoneyTakingById(id);
    }

    @PostMapping("/moneyTaking")
    public BigDecimal add(@RequestBody MoneyTaking moneyTaking) {
        return moneyTakingService.saveMoneyTaking(moneyTaking);
    }
}
