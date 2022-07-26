///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package com.syntech.repository;

import com.syntech.model.PurchaseOrder;
import java.math.BigDecimal;
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

    public List<PurchaseOrder> eagerLoadAll() {
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT e FROM PurchaseOrder e "
                    + "INNER JOIN FETCH e.purchaseOrderDetailList t");
            purchaseOrderList = query.getResultList();
        } catch (Exception e) {
            System.out.println("Failed to find all record of purchase");
            Logger.getLogger(PurchaseOrderRepository.class.getName())
                    .log(Level.SEVERE, " Error while eager loading all purchase data:", e);

        }
        return purchaseOrderList;
    }

    public List<PurchaseOrder> findByDate(Date date) {
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        try {
            Query query = em.createQuery("select e from PurchaseOrder e where e.date =:date");
            query.setParameter("date", date);
            purchaseOrderList = query.getResultList();
        } catch (Exception e) {
            System.out.println("Record of the Given Date Display Failed!!!");
            Logger.getLogger(PurchaseOrderRepository.class.getName())
                    .log(Level.SEVERE, " Error fetching purchase data based on date:", e);

        }
        return purchaseOrderList;
    }

    public BigDecimal calculateTotalAmountBeforeDate(Date date) {
        BigDecimal amount = BigDecimal.ZERO;
        try {
            Query query = em.createQuery("SELECT sum(e.totalAmount) from PurchaseOrder e where e.date<:date", BigDecimal.class);
            query.setParameter("date", date);
            amount = (BigDecimal) query.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(PurchaseOrderRepository.class.getName())
                    .log(Level.SEVERE, " Error while calculating total amount before date:", e);
            amount = BigDecimal.ZERO;
        }
        return amount == null ? BigDecimal.ZERO : amount;
    }
}
