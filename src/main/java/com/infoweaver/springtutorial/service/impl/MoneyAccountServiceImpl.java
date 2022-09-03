package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.entity.MoneyAccount;
import com.infoweaver.springtutorial.mapper.MoneyAccountMapper;
import com.infoweaver.springtutorial.service.IMoneyAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Service
public class MoneyAccountServiceImpl extends ServiceImpl<MoneyAccountMapper, MoneyAccount> implements IMoneyAccountService {
    private MoneyAccountMapper moneyAccountMapper;

    @Autowired
    public void setMoneyAccountMapper(MoneyAccountMapper moneyAccountMapper) {
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
}
