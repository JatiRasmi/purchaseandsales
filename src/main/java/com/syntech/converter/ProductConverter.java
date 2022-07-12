/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.converter;

import com.syntech.model.Product;
import com.syntech.repository.AbstractRepository;
import com.syntech.repository.ProductRepository;
import javax.enterprise.context.RequestScoped;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author rasmi
 */

@RequestScoped
@FacesConverter(value = "productConverter", forClass = Product.class)

public class ProductConverter extends AbstractConverter<Product> {
    
    @Inject
    private ProductRepository productRepository;
    
    @Override
    protected AbstractRepository getRepository() {
        return productRepository;
    }   
}