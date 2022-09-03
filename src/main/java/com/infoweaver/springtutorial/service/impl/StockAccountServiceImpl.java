package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.entity.StockAccount;
import com.infoweaver.springtutorial.mapper.StockAccountMapper;
import com.infoweaver.springtutorial.service.IStockAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Service
public class StockAccountServiceImpl extends ServiceImpl<StockAccountMapper, StockAccount> implements IStockAccountService {
    private StockAccountMapper stockAccountMapper;

    @Autowired
    public void setStockAccountMapper(StockAccountMapper stockAccountMapper) {
        this.stockAccountMapper = stockAccountMapper;
    }

    @Override
    public List<StockAccount> listStockAccounts() {
        return stockAccountMapper.selectList(null);
    }

    @Override
    public StockAccount getStockAccountById(String id) {
        return stockAccountMapper.selectById(id);
    }

    @Override
    public int saveStockAccount(StockAccount stockAccount) {
        return stockAccountMapper.insert(stockAccount);
    }

    @Override
    public int updateStockAccount(StockAccount stockAccount) {
        return stockAccountMapper.updateById(stockAccount);
    }

    @Override
    public int removeStockAccount(String id) {
        return stockAccountMapper.deleteById(id);
    }
}
