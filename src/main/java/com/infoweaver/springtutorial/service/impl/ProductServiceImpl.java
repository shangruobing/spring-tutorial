package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.entity.Product;
import com.infoweaver.springtutorial.mapper.ProductMapper;
import com.infoweaver.springtutorial.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public Page<Map<String, Object>> listProducts(Integer currentPage, Integer size) {
        Page<Map<String, Object>> page = new Page<>(currentPage, size);
        return productMapper.selectMapsPage(page, null);
    }

    @Override
    public Product getProductById(String id) {
        return productMapper.selectById(id);
    }

    @Override
    public int saveProduct(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public int updateProduct(Product product) {
        return productMapper.updateById(product);
    }

    @Override
    public int removeProduct(String id) {
        return productMapper.deleteById(id);
    }

    public List<Product> listProductByFilter(Set<String> productIds, String brand, String model) {
        LambdaQueryWrapper<Product> wrapper = Wrappers.lambdaQuery(Product.class).in(Product::getId, productIds);
        if (brand.isEmpty() && !model.isEmpty()) {
            wrapper.eq(Product::getModel, model);
        }
        if (!brand.isEmpty() && model.isEmpty()) {
            wrapper.eq(Product::getBrand, brand);
        }
        if (!brand.isEmpty() && !model.isEmpty()) {
            wrapper.eq(Product::getBrand, brand).eq(Product::getModel, model);
        }
        return productMapper.selectList(wrapper);
    }

    @Override
    public int saveProductBatch(List<Product> products) {
        products.forEach(e -> {
            if (Optional.ofNullable(productMapper.selectById(e.getId())).isEmpty()) {
                productMapper.insert(e);
            }
        });
        return 1;
    }

}
