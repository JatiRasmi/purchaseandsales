///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package com.syntech.repository;

import com.syntech.model.PurchaseOrder;
import com.syntech.model.PurchaseOrderDetail;
import com.syntech.model.PurchaseOrder_;
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
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    public PurchaseOrderRepository filterByIdEagerLoad(Long purchaseorderid) {
        Join<PurchaseOrder, PurchaseOrderDetail> purchaseItemJoin = (Join<PurchaseOrder, PurchaseOrderDetail>) root.<PurchaseOrder, PurchaseOrderDetail>fetch("salesOrderDetailList", JoinType.LEFT);
        Predicate criteriaPredicates = criteriaBuilder.equal(purchaseItemJoin.get("purchaseOrder").get("id"), purchaseorderid);
        this.addCriteria(criteriaPredicates);
        return this;
    }

    public PurchaseOrder eagerload(Long poid) {
        PurchaseOrder po = null;
        try {
            po = ((PurchaseOrderRepository) this.startQuery()).filterByIdEagerLoad(poid).getSingleResult();

        } catch (Exception e) {
            Logger.getLogger(PurchaseOrderRepository.class.getName())
                    .log(Level.SEVERE, " Error while eager loading:", e);
            po = null;
        }
        return po;
    }

    public PurchaseOrderRepository filterByEagerLoadAll() {
        Join<PurchaseOrder, PurchaseOrderDetail> purchaseItemJoin = (Join<PurchaseOrder, PurchaseOrderDetail>) root.<PurchaseOrder, PurchaseOrderDetail>fetch("purchaseOrderDetailList", JoinType.LEFT);
        return this;
    }

    //api
    public List<PurchaseOrder> eagerLoadAll() {
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        try {
//            Query query = em.createQuery("SELECT e FROM PurchaseOrder e "
//                    + "INNER JOIN FETCH e.purchaseOrderDetailList t");
//            purchaseOrderList = query.getResultList();
            purchaseOrderList = ((PurchaseOrderRepository) this.startQuery()).filterByEagerLoadAll().getResultList();

        } catch (Exception e) {
            System.out.println("Failed to find all record of purchase");
            Logger.getLogger(PurchaseOrderRepository.class.getName())
                    .log(Level.SEVERE, " Error while eager loading all purchase data:", e);

        }
        return purchaseOrderList;
    }

    public PurchaseOrderRepository filterByDate(Date date) {
        Predicate criteriaPredicates = criteriaBuilder.equal(root.get(PurchaseOrder_.date), date);
        this.addCriteria(criteriaPredicates);
        return this;
    }

    public List<PurchaseOrder> findByDate(Date date) {
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        try {
            purchaseOrderList = ((PurchaseOrderRepository) this.startQuery())
                    .filterByDate(date).getResultList();

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
            CriteriaQuery<BigDecimal> query = this.criteriaBuilder.createQuery(BigDecimal.class);
            Root<PurchaseOrder> root = query.from(PurchaseOrder.class);
            query.select(criteriaBuilder.sum(root.get(PurchaseOrder_.totalAmount)));
            Predicate datePredicate = criteriaBuilder.lessThan(root.get(PurchaseOrder_.date), date);
            query.where(datePredicate);
            amount = (BigDecimal) em.createQuery(query).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(PurchaseOrderRepository.class.getName())
                    .log(Level.SEVERE, " Error while calculating total amount before date:", e);
            amount = BigDecimal.ZERO;
        }
        return amount == null ? BigDecimal.ZERO : amount;
    }
}
