# Spring Tutorial

- This repository records my process of learning Spring in September 2022.
- **MIS短学期Ⅲ 手机销售管理系统**

## 项目待改进之处

- [ ] 用ResponseEntity替代自己实现的Response(无法更改状态码)详见文档243页

## 对于Controller、Service、Mapper的思考

- 保证Mapper层方法的单精度特性
- 业务逻辑封装在Service这一层，不要分散在Controller层。也不要出现在Mapper层
- 当某一个业务功能需要使用其他模块的业务功能时，尽量的调用别人的Service，而不是深入到其他模块的Mapper细节

## 小票级联增加的实现

启动SpringBootApplication后，向对应端口发送POST请求，请求体包含以下内容。

```json
{
  "seller": "3",
  "cashier": "2",
  "receiptDetails": [
    {
      "productId": "1",
      "quantity": 10
    },
    {
      "productId": "2",
      "quantity": 20
    },
    {
      "productId": "3",
      "quantity": 30
    }
  ]
}
```

### 执行流程

- 在新增小票时，首先为小票添加id
- 设置小票状态为“新订单”(枚举)
- 调用forEach，对每一个小票项设置小票id(外键)和计算小票项金额(调用计算金额方法)
- 使用map和lambda计算出总金额并赋值给小票
- receiptMapper将小票持久化
- 调用forEach，将每一个小票项利用receiptDetailMapper持久化

```java

@Service
public class ReceiptServiceImpl extends ServiceImpl<ReceiptMapper, Receipt> implements IReceiptService {
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
        BigDecimal price = productMapper.selectById(receiptDetail.getProductId()).getPrice();
        return price.multiply(new BigDecimal(receiptDetail.getQuantity()));
    }
}

```