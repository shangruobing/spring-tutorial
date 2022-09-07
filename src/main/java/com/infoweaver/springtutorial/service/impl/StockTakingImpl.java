package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.entity.StockTaking;
import com.infoweaver.springtutorial.mapper.StockTakingMapper;
import com.infoweaver.springtutorial.service.IStockTakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ruobing Shang 2022-09-07 15:44
 */

@Service
public class StockTakingImpl extends ServiceImpl<StockTakingMapper, StockTaking> implements IStockTakingService {
    private final StockTakingMapper stockTakingMapper;

    @Autowired
    public StockTakingImpl(StockTakingMapper stockTakingMapper) {
        this.stockTakingMapper = stockTakingMapper;
    }
}
