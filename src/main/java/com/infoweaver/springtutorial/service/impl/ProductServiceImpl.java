package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.entity.Product;
import com.infoweaver.springtutorial.mapper.ProductMapper;
import com.infoweaver.springtutorial.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    private ProductMapper productMapper;

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<Product> getProductList() {
        return productMapper.selectList(null);
    }

    public Product getProductById(Long id) {
        return productMapper.selectById(id);
    }

    public int addProduct(Product product) {
        return productMapper.insert(product);
    }

    public int editProduct(Product product) {
        return productMapper.updateById(product);
    }

    public int removeProduct(Long id) {
        return productMapper.deleteById(id);
    }
}
