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
    private ProductServiceImpl productService;

    @Autowired
    public void setProductService(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public List<Product> selectAllProduct() {
        return productService.listProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") String id) {
        return productService.getProductById(id);
    }


    @PostMapping("/product")
    public int add(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/product")
    public int update(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/product/{id}")
    public int delete(@PathVariable("id") String id) {
        return productService.removeProduct(id);
    }
}


