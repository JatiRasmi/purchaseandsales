///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package com.syntech.repository;

import com.syntech.model.Product;
import com.syntech.model.Product_;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;
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

    public ProductRepository filterByProductName(String name) {
        Predicate criteriaPredicates = criteriaBuilder.equal(root.get(Product_.name), name);
        this.addCriteria(criteriaPredicates);
        return this;
    }

    //excel file upload
    public Product findByName(String uname) {
        Product product = null;
        try {
//            Query query = em.createQuery("select e from Product e where e.name =:name");
//            query.setParameter("name", name);
//            product = (Product) query.getSingleResult();
            product = ((ProductRepository) this.startQuery()).filterByProductName(uname).getSingleResult();
        } catch (Exception e) {
            System.out.println("Record of the Given Date Display Failed!!!");
            Logger.getLogger(Product.class.getName())
                    .log(Level.SEVERE, " Error fetching product data based on product name:", e);

        }
        return product;
    }
}
