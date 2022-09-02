package com.infoweaver.springtutorial.controller;

import com.infoweaver.springtutorial.entity.Product;
import com.infoweaver.springtutorial.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@RestController
public class ProductController {
    private ProductServiceImpl ProductService;

    @Autowired
    public void setProductService(ProductServiceImpl ProductService) {
        this.ProductService = ProductService;
    }

    @GetMapping("/product")
    public List<Product> selectAllProduct() {
        return ProductService.getProductList();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") long id) {
        return ProductService.getProductById(id);
    }


    @PostMapping("/product")
    public int add(@RequestBody Product Product) {
        return ProductService.addProduct(Product);
    }

    @PutMapping("/product")
    public int update(@RequestBody Product Product) {
        return ProductService.editProduct(Product);
    }

    @DeleteMapping("/product/{id}")
    public int delete(@PathVariable("id") long id) {
        return ProductService.removeProduct(id);
    }
}


