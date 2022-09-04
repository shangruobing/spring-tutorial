package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.entity.ReceiptDetail;
import com.infoweaver.springtutorial.entity.StockAccount;
import com.infoweaver.springtutorial.mapper.StockAccountMapper;
import com.infoweaver.springtutorial.service.IStockAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Service
public class StockAccountServiceImpl extends ServiceImpl<StockAccountMapper, StockAccount> implements IStockAccountService {
    private final StockAccountMapper stockAccountMapper;

    @Autowired
    public StockAccountServiceImpl(StockAccountMapper stockAccountMapper) {
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

    @Override
    public int outbound(List<ReceiptDetail> receiptDetails) {
        receiptDetails.forEach(e -> {
            LambdaQueryWrapper<StockAccount> wrapper;
            wrapper = Wrappers.lambdaQuery(StockAccount.class).eq(StockAccount::getProductId, e.getProductId());
            StockAccount stockAccount = Optional.ofNullable(stockAccountMapper.selectOne(wrapper))
                    .map(StockAccount::new).orElse(null);
            assert stockAccount != null;
            stockAccount.setQuantity(stockAccount.getQuantity() - e.getQuantity());
            stockAccountMapper.updateById(stockAccount);
        });
        return 1;
    }

    @Override
    public int saveOrUpdateStockAccountBatch(List<StockAccount> stockAccounts) {
        stockAccounts.forEach(e -> {
            LambdaQueryWrapper<StockAccount> wrapper;
            wrapper = Wrappers.lambdaQuery(StockAccount.class).eq(StockAccount::getProductId, e.getProductId());
            StockAccount stockAccount = Optional.ofNullable(stockAccountMapper.selectOne(wrapper))
                    .map(StockAccount::new).orElse(null);
            if (stockAccount != null) {
                stockAccount.setQuantity(stockAccount.getQuantity() + e.getQuantity());
                stockAccountMapper.updateById(stockAccount);
            } else {
                e.setDate(new Date(System.currentTimeMillis()));
                stockAccountMapper.insert(e);
            }
        });
        return 1;
    }
}