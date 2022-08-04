/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.controller;

import com.syntech.repository.ExcelUpload;
import com.syntech.model.Product;
import com.syntech.repository.ProductRepository;
import com.syntech.util.MessageUtill;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author rasmi
 */
@Named
@ViewScoped
public class ProductController implements Serializable {

    private static final Logger logger = Logger.getLogger(ProductController.class.getName());

    private Product product;

    @Inject
    private ExcelUpload excelUpload;

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
        if (product.getName() == null || product.getName().isEmpty()) {
            messageUtill.showError("Product Name is required", "Name Required");
            logger.log(Level.SEVERE, "Product name is null");
        }
//        if (product.getUnit() == null || product.getUnit().getName().isEmpty()) {
        if (product.getUnit() == null) {
            messageUtill.showError("Unit is required", "Unit Required");
            logger.log(Level.SEVERE, "Product unit is null");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            messageUtill.showError("Product Description is required", "Description Required");
            logger.log(Level.SEVERE, "Product description is null");
        }
        productRepository.create(product);
        this.productList = productRepository.findAll();
        messageUtill.showInfo("Product Added Successfully", "Product Added");
        logger.log(Level.INFO, " Product Created Successfully!!!:");
    }

    public void beforeEdit(Product product) {
        this.product = productRepository.findById(product.getId());
    }

    public void edit() {
        if (product.getId() == null) {
            logger.log(Level.SEVERE, "Product is null");
        }
        productRepository.edit(this.product);
        this.productList = productRepository.findAll();
        messageUtill.showInfo("Product Edited Successfully", "Product Edited");
        logger.log(Level.INFO, " Product Edited Successfully!!!:");
    }

    public void delete(Product product) {
        if (product.getId() == null) {
            logger.log(Level.SEVERE, "Product is null");
        }
        productRepository.delete(product);
        productList = productRepository.findAll();
        messageUtill.showInfo("Product Deleted Successfully", "Product Deleted");
        logger.log(Level.INFO, " Product Deleted Successfully!!!:");
    }

    private List<Product> products;

    public void handleExcelFileUpload(FileUploadEvent event) {
        this.products = new ArrayList<>();
        try {
            InputStream file = event.getFile().getInputStream();
            products = excelUpload.uploadExcelFile(file);
            logger.log(Level.INFO, "Succesfully uploaded product file");
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Failed to upload file");
        }
    }
    
    public void saveExcelFile(){
        this.products.forEach((p) -> {
            productRepository.create(p);
            logger.log(Level.INFO, "Data uploaded to database Successfully");
        });
    }
}
