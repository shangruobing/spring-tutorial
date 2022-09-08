package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.constant.ReceiptStatus;
import com.infoweaver.springtutorial.entity.ReceiptDetail;
import com.infoweaver.springtutorial.entity.Receipt;
import com.infoweaver.springtutorial.mapper.ReceiptMapper;
import com.infoweaver.springtutorial.service.IReceiptService;
import com.infoweaver.springtutorial.util.RandomGenerator;
import com.infoweaver.springtutorial.entity.vo.ReceiptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
public class ReceiptServiceImpl extends ServiceImpl<ReceiptMapper, Receipt> implements IReceiptService {
    private final ReceiptMapper receiptMapper;
    private final ReceiptDetailServiceImpl receiptDetailService;
    private final ProductServiceImpl productService;
    private final MoneyAccountServiceImpl moneyAccountService;
    private final StockAccountServiceImpl stockAccountService;

    @Autowired
    public ReceiptServiceImpl(ReceiptMapper receiptMapper,
                              ReceiptDetailServiceImpl receiptDetailService,
                              ProductServiceImpl productService,
                              MoneyAccountServiceImpl moneyAccountService,
                              StockAccountServiceImpl stockAccountService) {
        this.receiptMapper = receiptMapper;
        this.receiptDetailService = receiptDetailService;
        this.productService = productService;
        this.moneyAccountService = moneyAccountService;
        this.stockAccountService = stockAccountService;
    }

    @Override
    public List<Receipt> listReceipts() {
        return receiptMapper.selectList(Wrappers.emptyWrapper());
    }

    @Override
    public Receipt getReceiptById(String id) {
        return receiptMapper.selectById(id);
    }

    @Override
    public int saveReceipt(Receipt receipt) {
        receipt.setStatus(ReceiptStatus.NEW_ORDER.getCode());
        return receiptMapper.insert(receipt);
    }

    @Override
    public int updateReceipt(Receipt receipt) {
        return receiptMapper.updateById(receipt);
    }

    @Override
    public int removeReceipt(String id) {
        return receiptMapper.deleteById(id);
    }

    @Override
    public ReceiptVo getReceiptVoById(String id) {
        LambdaQueryWrapper<Receipt> wrapper = Wrappers.lambdaQuery(Receipt.class).eq(Receipt::getId, id);
        ReceiptVo receiptVo = Optional.ofNullable(receiptMapper.selectOne(wrapper))
                .map(ReceiptVo::new).orElse(null);
        Optional.ofNullable(receiptVo).ifPresent(this::addReceiptDetails);
        return receiptVo;
    }

    private void addReceiptDetails(ReceiptVo receiptVo) {
        List<ReceiptDetail> receiptDetails = receiptDetailService.listReceiptDetailsByReceiptId(receiptVo.getId());
        receiptVo.setReceiptDetails(receiptDetails);
    }

    public List<ReceiptVo> getReceiptVoByStatus(Integer status) {
        LambdaQueryWrapper<Receipt> wrapper = Wrappers.lambdaQuery(Receipt.class).eq(Receipt::getStatus, status);
        List<Receipt> receiptList = receiptMapper.selectList(wrapper);
        List<ReceiptVo> receiptVos = receiptList.stream().map(ReceiptVo::new).collect(toList());
        if (receiptVos.size() > 0) {
            addReceiptInfoList(receiptVos);
        }
        return receiptVos;
    }

    @Override
    public List<ReceiptVo> listReceiptVos() {
        List<Receipt> receiptList = receiptMapper.selectList(Wrappers.emptyWrapper());
        List<ReceiptVo> receiptVos = receiptList.stream().map(ReceiptVo::new).collect(toList());
        if (receiptVos.size() > 0) {
            addReceiptInfoList(receiptVos);
        }
        return receiptVos;
    }

    private void addReceiptInfoList(List<ReceiptVo> receiptVos) {
        Set<String> receiptIds = receiptVos.stream().map(Receipt::getId).collect(toSet());
        List<ReceiptDetail> receiptDetails = receiptDetailService.listReceiptDetailsByReceiptIdSet(receiptIds);
        Map<String, List<ReceiptDetail>> hashMap = receiptDetails.stream()
                .collect(groupingBy(ReceiptDetail::getReceiptId));
        receiptVos.forEach(e -> e.setReceiptDetails(hashMap.get(e.getId())));
    }

    @Override
    public String saveReceiptVo(ReceiptVo receiptVo) {
        receiptVo.setId(RandomGenerator.getNumberString(20));
        receiptVo.setStatus(ReceiptStatus.NEW_ORDER.getCode());
        receiptVo.setDate(new Date(System.currentTimeMillis()));
        receiptVo.getReceiptDetails().forEach(e -> {
            e.setReceiptId(receiptVo.getId());
            e.setAmount(calculateAmount(e));
        });
        receiptVo.setTotalPrice(receiptVo.getReceiptDetails().stream()
                .map(ReceiptDetail::getAmount)
                .reduce(BigDecimal::add)
                .orElse(null));
        receiptMapper.insert(receiptVo);
        receiptVo.getReceiptDetails().forEach(receiptDetailService::save);
        return receiptVo.getId();
    }

    private BigDecimal calculateAmount(ReceiptDetail receiptDetail) {
        BigDecimal price = productService.getProductById(receiptDetail.getProductId()).getPrice();
        return price.multiply(new BigDecimal(receiptDetail.getQuantity()));
    }

    @Override
    public int payment(String receiptId) {
        Receipt receipt = receiptMapper.selectById(receiptId);
        receipt.setStatus(ReceiptStatus.PAID.getCode());
        receiptMapper.updateById(receipt);
        return moneyAccountService.saveMoneyAccountByReceipt(receipt);
    }

    @Override
    public int outbound(String receiptId) {
        Receipt receipt = receiptMapper.selectById(receiptId);
        receipt.setStatus(ReceiptStatus.DELIVERED.getCode());
        receiptMapper.updateById(receipt);
        return stockAccountService.outbound(receiptDetailService.listReceiptDetailsByReceiptId(receipt.getId()));
    }

    @Override
    public int customerTakeDelivery(String receiptId) {
        Receipt receipt = receiptMapper.selectById(receiptId);
        receipt.setStatus(ReceiptStatus.FINISHED.getCode());
        return receiptMapper.updateById(receipt);
    }

}