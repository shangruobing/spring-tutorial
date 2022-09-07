package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.entity.MoneyTaking;
import com.infoweaver.springtutorial.mapper.MoneyTakingMapper;
import com.infoweaver.springtutorial.service.IMoneyTakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Service
public class MoneyTakingServiceImpl extends ServiceImpl<MoneyTakingMapper, MoneyTaking> implements IMoneyTakingService {
    private final MoneyTakingMapper moneyTakingMapper;
    private final MoneyAccountServiceImpl moneyAccountService;

    @Autowired
    public MoneyTakingServiceImpl(MoneyTakingMapper moneyTakingMapper, MoneyAccountServiceImpl moneyAccountService) {
        this.moneyTakingMapper = moneyTakingMapper;
        this.moneyAccountService = moneyAccountService;
    }

    @Override
    public List<MoneyTaking> listMoneyTakings() {
        return moneyTakingMapper.selectList(null);
    }

    @Override
    public MoneyTaking getMoneyTakingById(String id) {
        return moneyTakingMapper.selectById(id);
    }

    @Override
    public BigDecimal saveMoneyTaking(MoneyTaking moneyTaking) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        moneyTaking.setBeginningBalance(getBeginningBalance(year, month));
        BigDecimal endingBalance = moneyAccountService.sumMoneyAccountsByMonth(month + 1)
                .add(moneyTaking.getBeginningBalance());
        moneyTaking.setEndingBalance(endingBalance);
        moneyTaking.setDate(new Date(System.currentTimeMillis()));
        moneyTaking.setBalance(moneyTaking.getTotalPrice().subtract(moneyTaking.getEndingBalance()));
        moneyTakingMapper.insert(moneyTaking);
        return moneyTaking.getBalance();
    }

    private BigDecimal getBeginningBalance(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 0, 23, 59, 59);
        Date endDate = calendar.getTime();
        calendar.set(year, calendar.get(Calendar.MONTH), 1, 0, 0, 0);
        Date startDate = calendar.getTime();
        LambdaQueryWrapper<MoneyTaking> wrapper = Wrappers.lambdaQuery(MoneyTaking.class);
        wrapper.between(MoneyTaking::getDate, startDate, endDate);
        return Optional.ofNullable(moneyTakingMapper.selectOne(wrapper).getEndingBalance()).orElse(BigDecimal.ZERO);
    }

}