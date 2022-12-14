package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.entity.Product;
import com.infoweaver.springtutorial.entity.ReceiptDetail;
import com.infoweaver.springtutorial.entity.StockAccount;
import com.infoweaver.springtutorial.mapper.StockAccountMapper;
import com.infoweaver.springtutorial.service.IStockAccountService;
import com.infoweaver.springtutorial.vo.StockAccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.*;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Service
public class StockAccountServiceImpl extends ServiceImpl<StockAccountMapper, StockAccount> implements IStockAccountService {
    private final StockAccountMapper stockAccountMapper;
    private final ProductServiceImpl productService;

    @Autowired
    public StockAccountServiceImpl(StockAccountMapper stockAccountMapper, ProductServiceImpl productService) {
        this.stockAccountMapper = stockAccountMapper;
        this.productService = productService;
    }

    @Override
    public List<StockAccountVO> listStockAccounts(String brand, String type) {
        List<StockAccount> stockAccounts = stockAccountMapper.selectList(Wrappers.emptyWrapper());
        List<StockAccountVO> stockAccountVOs = stockAccounts.stream().map(StockAccountVO::new).collect(toList());
        stockAccountVOs = addProductName(stockAccountVOs, brand, type);
        return stockAccountVOs;
    }

    private List<StockAccountVO> addProductName(List<StockAccountVO> stockAccountVOs, String brand, String type) {
        Set<String> stockAccountIds = stockAccountVOs.stream().map(StockAccountVO::getProductId).collect(toSet());
        List<Product> products = productService.listProductByFilter(stockAccountIds, brand, type);
        Set<String> productIds = products.stream().map(Product::getId).collect(toSet());
        Map<String, Product> hashMap = products.stream().collect(toMap(Product::getId, Product::new));
        stockAccountVOs = stockAccountVOs.stream()
                .filter(stockAccountVO -> productIds.contains(stockAccountVO.getProductId()))
                .collect(toList());
        stockAccountVOs.forEach(e -> e.setProduct(hashMap.get(e.getProductId())));
        return stockAccountVOs;
    }

    @Override
    public StockAccountVO getStockAccountById(String id) {
        StockAccount stockAccount = stockAccountMapper.selectById(id);
        StockAccountVO stockAccountVo = Optional.ofNullable(stockAccount).map(StockAccountVO::new).orElse(null);
        Optional.ofNullable(stockAccountVo).ifPresent(e ->
                stockAccountVo.setProduct(productService.getProductById(stockAccountVo.getProductId())));
        return stockAccountVo;
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
            LambdaQueryWrapper<StockAccount> wrapper = Wrappers.lambdaQuery(StockAccount.class);
            wrapper.eq(StockAccount::getProductId, e.getProductId());
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
            LambdaQueryWrapper<StockAccount> wrapper = Wrappers.lambdaQuery(StockAccount.class);
            wrapper.eq(StockAccount::getProductId, e.getProductId());
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


    @Override
    public int saveStockTakings(List<StockAccount> stockAccounts) {
        stockAccounts.forEach(e -> {
            LambdaQueryWrapper<StockAccount> wrapper = Wrappers.lambdaQuery(StockAccount.class);
            wrapper.eq(StockAccount::getProductId, e.getProductId());
            StockAccount stockTaking = stockAccountMapper.selectOne(wrapper);
            stockTaking.setNote(e.getNote());
            stockTaking.setDate(new Date(System.currentTimeMillis()));
            stockTaking.setKept(e.getKept());
            stockTaking.setBalance(e.getKept() - stockTaking.getQuantity());
            stockAccountMapper.updateById(stockTaking);
        });
        return 1;
    }
}
