package com.infoweaver.springtutorial.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.infoweaver.springtutorial.entity.Product;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-02 10:36
 */

public interface IProductService extends IService<Product> {
    /**
     * Retrieve All Product.
     *
     * @return Product List
     */
    List<Product> getProductList();

    /**
     * Retrieve a Product by id.
     *
     * @param id product id
     * @return a Product instance
     */
    Product getProductById(Long id);

    /**
     * Create a Product instance.
     *
     * @param product product object
     * @return a status code
     */
    int addProduct(Product product);

    /**
     * Update a product instance.
     *
     * @param product product object
     * @return a status code
     */
    int editProduct(Product product);

    /**
     * Delete a product instance.
     *
     * @param id product id
     * @return a status code
     */
    int removeProduct(Long id);

}
