/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.SalesOrder;
import com.syntech.model.SalesOrderDetail;
import com.syntech.model.SalesOrder_;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author rasmi
 */
@Stateless
public class SalesOrderRepository extends AbstractRepository<SalesOrder> {

    @PersistenceContext(name = "psDS")
    private EntityManager em;

    public SalesOrderRepository() {
        super(SalesOrder.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SalesOrderRepository filterByIdEagerLoad(Long saleOrderId) {
        Join<SalesOrder, SalesOrderDetail> salesItemJoin = (Join<SalesOrder, SalesOrderDetail>) root.<SalesOrder, SalesOrderDetail>fetch("salesOrderDetailList", JoinType.LEFT);
        Predicate criteriaPredicates = criteriaBuilder.equal(salesItemJoin.get("salesOrder").get("id"), saleOrderId);
        this.addCriteria(criteriaPredicates);
        return this;
    }

    public SalesOrder eagerload(Long soid) {
        SalesOrder so = null;
        try {
            so = ((SalesOrderRepository) this.startQuery()).filterByIdEagerLoad(soid).getSingleResult();

        } catch (Exception e) {
            Logger.getLogger(SalesOrderRepository.class.getName())
                    .log(Level.SEVERE, " Error while eager loading:", e);
            so = null;
        }
        return so;
    }

    public SalesOrderRepository filterByEagerLoadAll() {
        Join<SalesOrder, SalesOrderDetail> salesItemJoin = (Join<SalesOrder, SalesOrderDetail>) root.<SalesOrder, SalesOrderDetail>fetch("salesOrderDetailList", JoinType.LEFT);
        return this;
    }

    public List<SalesOrder> eagerLoadAll() {
        List<SalesOrder> salesOrderList = new ArrayList<>();
        try {
//            Query query = em.createQuery("SELECT e FROM SalesOrder e "
//                    + "INNER JOIN FETCH e.salesOrderDetailList t");
//            salesOrderList = query.getResultList();

            salesOrderList = ((SalesOrderRepository) this.startQuery()).filterByEagerLoadAll().getResultList();
        } catch (Exception e) {
            System.out.println("Sales data doesn't exists!!!");
        }
        return salesOrderList;
    }

    public SalesOrderRepository filterByDate(Date date) {
        Predicate criteriaPredicates = criteriaBuilder.equal(root.get(SalesOrder_.date), date);
        this.addCriteria(criteriaPredicates);
        return this;
    }

    public List<SalesOrder> findByDate(Date date) {
        List<SalesOrder> salesOrderList = new ArrayList<>();
        try {
            salesOrderList = ((SalesOrderRepository) this.startQuery()).filterByDate(date).getResultList();

        } catch (Exception e) {
            System.out.println("Record Display Failed!!!");
        }
        return salesOrderList;
    }

    public BigDecimal calculateTotalAmountBeforeDate(Date date) {
        BigDecimal amount = BigDecimal.ZERO;
        try {
            CriteriaQuery<BigDecimal> query = this.criteriaBuilder.createQuery(BigDecimal.class);
            Root<SalesOrder> root = query.from(SalesOrder.class);
            Predicate datePredicate = criteriaBuilder.lessThan(root.get(SalesOrder_.date), date);
            query.select(criteriaBuilder.sum(root.get(SalesOrder_.totalAmount)));
            query.where(datePredicate);
            amount = (BigDecimal) em.createQuery(query).getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            amount = BigDecimal.ZERO;
        }
        return amount == null ? BigDecimal.ZERO : amount;
    }
}
