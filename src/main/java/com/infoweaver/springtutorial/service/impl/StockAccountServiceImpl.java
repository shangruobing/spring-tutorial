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

    public List<StockAccount> getStockAccountList() {
        return stockAccountMapper.selectList(null);
    }

    public StockAccount getStockAccountById(Long id) {
        return stockAccountMapper.selectById(id);
    }

    public int addStockAccount(StockAccount stockAccount) {
        return stockAccountMapper.insert(stockAccount);
    }

    public int editStockAccount(StockAccount stockAccount) {
        return stockAccountMapper.updateById(stockAccount);
    }

    public int removeStockAccount(Long id) {
        return stockAccountMapper.deleteById(id);
    }
}
