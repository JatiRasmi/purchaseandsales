/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.SalesOrder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
     public SalesOrder eagerload(Long soid) {
        SalesOrder so = null;
        try {
            Query query = em.createQuery("SELECT e FROM SalesOrder e "
                    + "INNER JOIN FETCH e.salesOrderDetailList t WHERE e.id=:soId", SalesOrder.class);
            query.setParameter("soId", soid);
            so = (SalesOrder) query.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(SalesOrderRepository.class.getName())
                    .log(Level.SEVERE, " Error while eager loading:", e);
            so = null;
        }
        return so;
    }
}
