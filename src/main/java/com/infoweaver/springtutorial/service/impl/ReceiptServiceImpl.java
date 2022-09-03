package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.constant.ReceiptStatus;
import com.infoweaver.springtutorial.entity.ReceiptDetail;
import com.infoweaver.springtutorial.entity.Receipt;
import com.infoweaver.springtutorial.mapper.ReceiptDetailMapper;
import com.infoweaver.springtutorial.mapper.ReceiptMapper;
import com.infoweaver.springtutorial.service.IReceiptService;
import com.infoweaver.springtutorial.utils.RandomGenerator;
import com.infoweaver.springtutorial.vo.ReceiptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
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
    private ReceiptMapper receiptMapper;
    private ReceiptDetailMapper receiptDetailMapper;
    private ProductServiceImpl productService;
    private ReceiptDetailServiceImpl receiptDetailService;
    private MoneyAccountServiceImpl moneyAccountService;
    private StockAccountServiceImpl stockAccountService;

    @Autowired
    public void setReceiptMapper(ReceiptMapper receiptMapper) {
        this.receiptMapper = receiptMapper;
    }

    @Autowired
    public void setReceiptDetailMapper(ReceiptDetailMapper receiptDetailMapper) {
        this.receiptDetailMapper = receiptDetailMapper;
    }

    @Autowired
    public void setProductService(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @Autowired
    public void setMoneyAccountService(MoneyAccountServiceImpl moneyAccountService) {
        this.moneyAccountService = moneyAccountService;
    }

    @Autowired
    public void setStockAccountService(StockAccountServiceImpl stockAccountService) {
        this.stockAccountService = stockAccountService;
    }

    @Autowired
    public void setReceiptDetailService(ReceiptDetailServiceImpl receiptDetailService) {
        this.receiptDetailService = receiptDetailService;
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
        ReceiptVo receiptVo = Optional.ofNullable(receiptMapper.selectOne(wrapper)).map(ReceiptVo::new).orElse(null);
        Optional.ofNullable(receiptVo).ifPresent(this::addReceiptDetails);
        return receiptVo;
    }

    private void addReceiptDetails(ReceiptVo receiptVo) {
        LambdaQueryWrapper<ReceiptDetail> wrapper = Wrappers.lambdaQuery(ReceiptDetail.class).eq(ReceiptDetail::getReceiptId, receiptVo.getId());
        List<ReceiptDetail> receiptDetails = receiptDetailMapper.selectList(wrapper);
        receiptVo.setReceiptDetails(receiptDetails);
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
        List<ReceiptDetail> receiptDetails = receiptDetailMapper.selectList(Wrappers.lambdaQuery(ReceiptDetail.class).in(ReceiptDetail::getReceiptId, receiptIds));
        Map<String, List<ReceiptDetail>> hashMap = receiptDetails.stream().collect(groupingBy(ReceiptDetail::getReceiptId));
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
                }
        );
        receiptVo.setTotalPrice(receiptVo.getReceiptDetails().stream().map(ReceiptDetail::getAmount)
                .reduce(BigDecimal::add).orElse(null));
        receiptMapper.insert(receiptVo);
        receiptVo.getReceiptDetails().forEach(e -> receiptDetailMapper.insert(e));

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
        stockAccountService.outbound(receiptDetailService.listReceiptDetailsByReceiptId(receipt.getId()));
        return 1;
    }
}