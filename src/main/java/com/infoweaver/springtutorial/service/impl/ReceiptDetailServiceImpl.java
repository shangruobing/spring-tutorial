package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.entity.ReceiptDetail;
import com.infoweaver.springtutorial.mapper.ReceiptDetailMapper;
import com.infoweaver.springtutorial.service.IReceiptDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Service
public class ReceiptDetailServiceImpl extends ServiceImpl<ReceiptDetailMapper, ReceiptDetail> implements IReceiptDetailService {
    private final ReceiptDetailMapper receiptDetailMapper;

    @Autowired
    public ReceiptDetailServiceImpl(ReceiptDetailMapper receiptDetailMapper) {
        this.receiptDetailMapper = receiptDetailMapper;
    }

    @Override
    public List<ReceiptDetail> listReceiptDetails() {
        return receiptDetailMapper.selectList(null);
    }

    @Override
    public ReceiptDetail getReceiptDetailById(String id) {
        return receiptDetailMapper.selectById(id);
    }

    @Override
    public int saveReceiptDetail(ReceiptDetail receiptDetail) {
        return receiptDetailMapper.insert(receiptDetail);
    }

    @Override
    public int updateReceiptDetail(ReceiptDetail receiptDetail) {
        return receiptDetailMapper.updateById(receiptDetail);
    }

    @Override
    public int removeReceiptDetail(String id) {
        return receiptDetailMapper.deleteById(id);
    }

    @Override
    public List<ReceiptDetail> listReceiptDetailsByReceiptId(String receiptId) {
        LambdaQueryWrapper<ReceiptDetail> wrapper = Wrappers.lambdaQuery(ReceiptDetail.class)
                .eq(ReceiptDetail::getReceiptId, receiptId);
        return receiptDetailMapper.selectList(wrapper);
    }

}
