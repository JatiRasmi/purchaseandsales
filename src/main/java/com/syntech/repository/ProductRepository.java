/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Product;
import com.syntech.model.Unit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rasmi
 */
public class ProductRepository extends AbstractRepository<Product> {

    @Override
    public void create(Product p) {
        try {
            String insert = "insert into product (name,productdescription,unit_id) values(?,?,?)";
            PreparedStatement stmt = connectDB().prepareStatement(insert);
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getDescription());
            stmt.setLong(3, p.getUnitid().getId());
            int i = stmt.executeUpdate();
            System.out.println(i + " Inserted Successfully!!");

        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("Insertion Failed!!!!");
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try {
            String query = "select * from product";
            PreparedStatement stmt = connectDB().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getLong(1), new Unit(rs.getLong(2)), rs.getString(3), rs.getString(4));
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Record Display failed!!!");
//            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Product findById(Long id) {
        Product product = new Product();
        try {
            String query = "select * from product where id = ?";
            PreparedStatement stmt = connectDB().prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getLong(1), new Unit(rs.getLong(2)), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("Record Display Failed!!!");
        }
        return product;
    }
    
    @Override
    public void delete(Product p){
        try{
            String delete = "delete from product where id = ?";
            PreparedStatement stmt = connectDB().prepareStatement(delete);
            stmt.setLong(1,p.getId());
            int i = stmt.executeUpdate();
            System.out.println( i + " Deleted Successfully!!!");
        }catch(SQLException e){
            System.out.println("Deletion Failed!!!");
        }
    }
    
    @Override
    public void edit(Product u) {
//        super.findAll().stream()
//                .filter(n -> n.getId().equals(u.getId()))
//                .forEach((Product un) -> {
//                    un.setName(u.getName());
//                    un.setDescription(u.getDescription());
//                    un.setUnitid(u.getUnitid());
//                });
    }
}
