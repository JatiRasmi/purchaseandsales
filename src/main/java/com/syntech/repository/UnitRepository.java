/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Unit;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author rasmi
 */
@Stateless
public class UnitRepository extends AbstractRepository<Unit> {

    @PersistenceContext(name = "psDS")   // inject entity manager
    private EntityManager em;

    public UnitRepository() {
        super(Unit.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Boolean isUnique(Unit unit, String uniqueColumn, Object newValue) {
        Long count = 0L;
        try {
            Query query = em.createQuery("SELECT COUNT(e)"
                    + " FROM " + unit.getClass().getName() + " e"
                    + " WHERE e. " + uniqueColumn + " =:value", Long.class);
            query.setParameter("value", newValue);
            count = (Long) query.getSingleResult();
        } catch (Exception e) {
            count = 1L;
        }
        return count != null && count == 0L ? Boolean.TRUE : Boolean.FALSE;

    }
}
