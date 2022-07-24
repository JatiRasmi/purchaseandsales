/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.model.Product;
import com.syntech.repository.ProductRepository;
import com.syntech.util.MessageUtill;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rasmi
 */
@Named
@ViewScoped
public class ProductController implements Serializable {

    private Product product;
    private List<Product> productList;

    @Inject
    private ProductRepository productRepository;

    @Inject
    MessageUtill messageUtill;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @PostConstruct
    public void init() {
        this.product = new Product();
        this.productList = productRepository.findAll();
    }

    public void beforeCreate() {
        this.product = new Product();
    }

    public void create() {
        productRepository.create(product);
        this.productList = productRepository.findAll();
        messageUtill.showInfo("Product Added Successfully", "Product Added");
    }

    public void beforeEdit(Product product) {
        this.product = productRepository.findById(product.getId());
    }

    public void edit() {
        productRepository.edit(this.product);
        this.productList = productRepository.findAll();
        messageUtill.showInfo("Product Edited Successfully", "Product Edited");
    }

    public void delete(Product product) {
        productRepository.delete(product);
        productList = productRepository.findAll();
        messageUtill.showInfo("Product Deleted Successfully", "Product Deleted" );
    }
}