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

    public List<MoneyAccount> getMoneyAccountList() {
        return moneyAccountMapper.selectList(null);
    }

    public MoneyAccount getMoneyAccountById(Long id) {
        return moneyAccountMapper.selectById(id);
    }

    public int addMoneyAccount(MoneyAccount moneyAccount) {
        return moneyAccountMapper.insert(moneyAccount);
    }

    public int editMoneyAccount(MoneyAccount moneyAccount) {
        return moneyAccountMapper.updateById(moneyAccount);
    }

    public int removeMoneyAccount(Long id) {
        return moneyAccountMapper.deleteById(id);
    }
}
