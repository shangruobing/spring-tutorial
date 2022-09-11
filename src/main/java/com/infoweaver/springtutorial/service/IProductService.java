package com.infoweaver.springtutorial.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * @author Ruobing Shang 2022-09-02 10:36
 */

public interface IProductService extends IService<Product> {
    /**
     * Retrieve All Product.
     *
     * @param currentPage current page
     * @param size        size
     * @return Product List
     */
    Page<Map<String, Object>> listProducts(Integer currentPage, Integer size);

    /**
     * Retrieve a Product by id.
     *
     * @param id product id
     * @return a Product instance
     */
    Product getProductById(String id);

    /**
     * Create a Product instance.
     *
     * @param product product object
     * @return a status code
     */
    int saveProduct(Product product);

    /**
     * Update a product instance.
     *
     * @param product product object
     * @return a status code
     */
    int updateProduct(Product product);

    /**
     * Delete a product instance.
     *
     * @param id product id
     * @return a status code
     */
    int removeProduct(String id);

    /**
     * Save product Batch.
     *
     * @param products product List
     * @return a status code
     */
    int saveProductBatch(List<Product> products);
}
