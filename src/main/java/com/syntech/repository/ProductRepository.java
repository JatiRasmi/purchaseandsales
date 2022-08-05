///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package com.syntech.repository;

import com.syntech.model.Product;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//
///**
// *
// * @author rasmi
// */
@Stateless
public class ProductRepository extends AbstractRepository<Product> {

     @PersistenceContext(name = "psDS")
    private EntityManager em;

    public ProductRepository() {
        super(Product.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
     public Product findByName(String name) {
        Product product = null;
        try {
            Query query = em.createQuery("select e from Product e where e.name =:name");
            query.setParameter("name", name);
            product = (Product) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Record of the Given Date Display Failed!!!");
            Logger.getLogger(Product.class.getName())
                    .log(Level.SEVERE, " Error fetching product data based on product name:", e);

        }
        return product;
    }
}
