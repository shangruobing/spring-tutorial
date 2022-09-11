package com.infoweaver.springtutorial.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.infoweaver.springtutorial.entity.Product;
import com.infoweaver.springtutorial.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Ruobing Shang 2022-09-01
 */

@RestController
public class ProductController {
    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public Page<Map<String, Object>> selectAllProduct(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "5", required = false) Integer size
    ) {
        return productService.listProducts(page, size);
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


    @PostMapping("/check-product")
    public int checkProduct(@RequestBody List<Product> products) {
        return productService.saveProductBatch(products);
    }
}


