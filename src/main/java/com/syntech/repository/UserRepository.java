/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.User;
import com.syntech.model.User_;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author rasmi
 */
@Stateless
public class UserRepository extends AbstractRepository<User> {

    @PersistenceContext(name = "psDS")
    private EntityManager em;

    public UserRepository() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRepository filterByUserName(String uname) {
        Predicate criteriaPredicates = criteriaBuilder.equal(root.get(User_.name), uname);
        this.addCriteria(criteriaPredicates);
        return this;
    }

    public User findByUsername(String uname) {
        User user = null;
        try {
//            Query query = em.createQuery("select e from User e "
//                    + "where e.name=:name", User.class);
//            query.setParameter("name", uname);
//            user = (User) query.getSingleResult();

            user = ((UserRepository) this.startQuery()).filterByUserName(uname).getSingleResult();

            System.out.println("user" + user);
        } catch (Exception e) {
            user = null;
            Logger.getLogger(UserRepository.class.getName())
                    .log(Level.SEVERE, " Error fetching username from user:", e);

        }
        return user;
    }
}
