/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.IEntity;
import com.syntech.model.IRepository;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author rasmi
 * @param <T>
 */
public abstract class AbstractRepository<T extends IEntity> implements IRepository<T> {

    protected abstract EntityManager getEntityManager();
    private Class<T> entityClass;
    
    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
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
//        getEntityManager().merge(findById(t.getId()));
        getEntityManager().flush();
    }

    @Override
    public void delete(T t) {
//        getEntityManager().remove(t);
        getEntityManager().remove(findById(t.getId()));
        getEntityManager().flush();

    }
}
