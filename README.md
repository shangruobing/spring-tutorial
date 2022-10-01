# Spring Tutorial

- This repository records my process of learning Spring in September 2022.

## 项目待改进之处

- [ ] 用ResponseEntity替代自己实现的Response(无法更改状态码)详见文档243页

## 对于Controller、Service、Mapper的思考

- 保证Mapper层方法的单精度特性
- 业务逻辑封装在Service这一层，不要分散在Controller层。也不要出现在Mapper层
- 当某一个业务功能需要使用其他模块的业务功能时，尽量的调用别人的Service，而不是深入到其他模块的Mapper细节

