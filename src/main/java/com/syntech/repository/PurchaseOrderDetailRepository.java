/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.PurchaseOrderDetail;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rasmi
 */
@Stateless
public class PurchaseOrderDetailRepository extends AbstractRepository<PurchaseOrderDetail> {

    @PersistenceContext(name = "psDS")   // inject entity manager
    private EntityManager em;

    public PurchaseOrderDetailRepository() {
        super(PurchaseOrderDetail.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
