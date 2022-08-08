/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.IEntity;
import com.syntech.model.IRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author rasmi
 * @param <T>
 */
public abstract class AbstractRepository<T extends IEntity> implements IRepository<T> {

    protected abstract EntityManager getEntityManager();
    protected CriteriaBuilder criteriaBuilder;
    protected Root<T> root;
    protected List<Predicate> predicates;
    private Class<T> entityClass;
    protected CriteriaQuery<T> criteriaQuery;

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public CriteriaQuery<T> getCriteriaQuery() {
        return criteriaQuery;
    }

    public void setCriteriaQuery(CriteriaQuery<T> criteriaQuery) {
        this.criteriaQuery = criteriaQuery;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public void setCriteriaBuilder(CriteriaBuilder criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
    }

    public Root<T> getRoot() {
        return root;
    }

    public void setRoot(Root<T> root) {
        this.root = root;
    }

    @PostConstruct
    protected void queryStart() {
        this.criteriaBuilder = getEntityManager().getCriteriaBuilder();
        this.criteriaQuery = this.criteriaBuilder.createQuery(getEntityClass());
        root = this.criteriaQuery.from(getEntityClass());
        predicates = new ArrayList<>();

    }

    public AbstractRepository<T> startQuery() {
        this.queryStart();
        return this;
    }

    @Override
    public void create(T t) {
        getEntityManager().persist(t);
        getEntityManager().flush();
    }

    @Override
    public List<T> findAll() {
        return getEntityManager().createQuery("Select t from " + entityClass.getName() + " t").getResultList();
    }

    @Override
    public T findById(Long id) {
        return getEntityManager().find(entityClass, id);

    }

    @Override
    public void edit(T t) {
        getEntityManager().merge(t);
        getEntityManager().flush();
    }

    @Override
    public void delete(T t) {
        getEntityManager().remove(findById(t.getId()));
        getEntityManager().flush();

    }

    public AbstractRepository<T> addCriteria(Predicate p) {
        this.predicates.add(p);
        return this;
    }

    public T getSingleResult() {
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        return getEntityManager().createQuery(criteriaQuery).getSingleResult();
    }

    public List<T> getResultList() {
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    /**
     *
     * @param t
     * @param uniqueColumn
     * @param newValue
     * @param id
     * @return
     */
    @Override
    public Boolean isUnique(T t, String uniqueColumn, Object newValue, Long id) {
        Long count = 0L;
        try {
            String sql = "SELECT COUNT(e)"
                    + " FROM " + t.getClass().getName() + " e"
                    + " WHERE e. " + uniqueColumn + " =:value";
            if (id != null) {
                sql = sql + " and e.id != :id";
            }
            Query query = getEntityManager().createQuery(sql, Long.class);
            query.setParameter("value", newValue);
            if (id != null) {
                query.setParameter("id", id);
            }
            count = (Long) query.getSingleResult();
        } catch (Exception e) {
            count = 1L;
        }
        return count != null && count == 0L ? Boolean.TRUE : Boolean.FALSE;

    }
}
