/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author rasmi
 */
public class ProductRepository extends AbstractRepository<Product> {

    Connection con = connectDB();
    PreparedStatement stmt;
    ResultSet rs;
    
//    @Override
//    public void create(Product p){
//        try{
//            String insert = "insert into product((name,product_description,unitid) values(?,?,?)";
//            stmt = con.prepareStatement(insert);
//            stmt.setString(1,p.getName());
//            stmt.setString(2,p.getDescription());
//        }catch(Exception e){
//            System.out.println("Inserted Successfully!!!!");
//        }
//    }
    
    
    @Override
    public void edit(Product u) {
        super.findAll().stream()
                .filter(n -> n.getId().equals(u.getId()))
                .forEach((Product un) -> {
                    un.setName(u.getName());
                    un.setDescription(u.getDescription());
                    un.setUnitid(u.getUnitid());
                });
    }

}
