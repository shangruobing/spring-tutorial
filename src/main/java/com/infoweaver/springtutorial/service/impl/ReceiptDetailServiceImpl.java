package com.infoweaver.springtutorial.service.impl;

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
    private ReceiptDetailMapper receiptDetailMapper;

    @Autowired
    public void setReceiptDetailMapper(ReceiptDetailMapper receiptDetailMapper) {
        this.receiptDetailMapper = receiptDetailMapper;
    }

    public List<ReceiptDetail> getReceiptDetailList() {
        return receiptDetailMapper.selectList(null);
    }

    public ReceiptDetail getReceiptDetailById(Long id) {
        return receiptDetailMapper.selectById(id);
    }

    public int addReceiptDetail(ReceiptDetail receiptDetail) {
        return receiptDetailMapper.insert(receiptDetail);
    }

    public int editReceiptDetail(ReceiptDetail receiptDetail) {
        return receiptDetailMapper.updateById(receiptDetail);
    }

    public int removeReceiptDetail(Long id) {
        return receiptDetailMapper.deleteById(id);
    }

}
