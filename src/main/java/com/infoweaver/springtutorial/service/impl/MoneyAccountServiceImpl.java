package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.entity.MoneyAccount;
import com.infoweaver.springtutorial.entity.Receipt;
import com.infoweaver.springtutorial.mapper.MoneyAccountMapper;
import com.infoweaver.springtutorial.service.IMoneyAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Service
public class MoneyAccountServiceImpl extends ServiceImpl<MoneyAccountMapper, MoneyAccount> implements IMoneyAccountService {
    private final MoneyAccountMapper moneyAccountMapper;

    @Autowired
    public MoneyAccountServiceImpl(MoneyAccountMapper moneyAccountMapper) {
        this.moneyAccountMapper = moneyAccountMapper;
    }

    @Override
    public List<MoneyAccount> listMoneyAccounts() {
        return moneyAccountMapper.selectList(null);
    }

    @Override
    public MoneyAccount getMoneyAccountById(String id) {
        return moneyAccountMapper.selectById(id);
    }

    @Override
    public int saveMoneyAccount(MoneyAccount moneyAccount) {
        return moneyAccountMapper.insert(moneyAccount);
    }

    @Override
    public int updateMoneyAccount(MoneyAccount moneyAccount) {
        return moneyAccountMapper.updateById(moneyAccount);
    }

    @Override
    public int removeMoneyAccount(String id) {
        return moneyAccountMapper.deleteById(id);
    }

    @Override
    public int saveMoneyAccountByReceipt(Receipt receipt) {
        MoneyAccount moneyAccount = new MoneyAccount();
        moneyAccount.setReceiptId(receipt.getId());
        moneyAccount.setEmployeeId(receipt.getCashier());
        moneyAccount.setDate(new Date(System.currentTimeMillis()));
        moneyAccount.setTotal(receipt.getTotalPrice());
        return moneyAccountMapper.insert(moneyAccount);
    }

    @Override
    public BigDecimal sumAllMoneyAccounts() {
        return moneyAccountMapper.selectList(null)
                .stream()
                .map(MoneyAccount::getTotal)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal sumMoneyAccountsByMonth(int month) {
        LambdaQueryWrapper<MoneyAccount> wrapper = Wrappers.lambdaQuery(MoneyAccount.class);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year, month, 0, 23, 59, 59);
        Date endDate = calendar.getTime();
        calendar.set(year, calendar.get(Calendar.MONTH), 1, 0, 0, 0);
        Date startDate = calendar.getTime();
        wrapper.between(MoneyAccount::getDate, startDate, endDate);
        return moneyAccountMapper.selectList(wrapper)
                .stream()
                .map(MoneyAccount::getTotal)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}