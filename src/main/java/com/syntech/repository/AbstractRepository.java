/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.IEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rasmi
 * @param <T>
 */
public abstract class AbstractRepository<T extends IEntity> {

    private List<T> list;

    public Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/purchaseandsales", "root", "toor");
            return con;
        } catch (Exception e) {
            System.out.println("Connection failed!!");
        }
        return null;
    }

    public AbstractRepository() {

        list = new ArrayList();
    }

    public void create(T list) {
        this.list.add(list);
    }

    public List<T> findAll() {
        return list;
    }

    public T findById(Long id) {
        for (T s : list) {
            if (s.getId().equals(id)) {   // create an interface for getId and
                return s;
            }
        }
        return null;
    }

    public void delete(T list) {
        this.list.remove(list);
    }

    abstract void edit(T e);

}
