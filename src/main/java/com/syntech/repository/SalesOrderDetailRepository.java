/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.SalesOrderDetail;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rasmi
 */
@Stateless
public class SalesOrderDetailRepository extends AbstractRepository<SalesOrderDetail> {

    @PersistenceContext(name = "psDS")   // inject entity manager
    private EntityManager em;

    public SalesOrderDetailRepository() {
        super(SalesOrderDetail.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}