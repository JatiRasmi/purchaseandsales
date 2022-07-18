///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package com.syntech.repository;

import com.syntech.model.PurchaseOrder;
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
public class PurchaseOrderRepository extends AbstractRepository<PurchaseOrder> {

    @PersistenceContext(name = "psDS")
    private EntityManager em;

    public PurchaseOrderRepository() {
        super(PurchaseOrder.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchaseOrder eagerload(Long poid) {
        PurchaseOrder po = null;
        try {
            Query query = em.createQuery("SELECT e FROM PurchaseOrder e "
                    + "INNER JOIN FETCH e.purchaseOrderDetailList t WHERE e.id=:poId", PurchaseOrder.class);
            query.setParameter("poId", poid);
            po = (PurchaseOrder) query.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(PurchaseOrderRepository.class.getName())
                    .log(Level.SEVERE, " Error while eager loading:", e);
            po = null;
        }
        return po;
    }
}
