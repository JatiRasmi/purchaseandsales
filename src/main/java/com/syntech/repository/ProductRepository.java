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
            String insert = "insert into product (unit_id,name,productdescription) values(?,?,?)";
            PreparedStatement stmt = connectDB().prepareStatement(insert);
            stmt.setLong(1, p.getUnitid().getId());
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getDescription());
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
//                 Product product = new Product(rs.getLong(1), new Unit(rs.getLong(2), rs.getString(2)), rs.getString(3), rs.getString(4));
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
    public void delete(Product p) {
        try {
            String delete = "delete from product where id = ?";
            PreparedStatement stmt = connectDB().prepareStatement(delete);
            stmt.setLong(1, p.getId());
            int i = stmt.executeUpdate();
            System.out.println(i + " Deleted Successfully!!!");
        } catch (SQLException e) {
            System.out.println("Deletion Failed!!!");
        }
    }

    @Override
    public void edit(Product p) {
        try {
            String edit = "update product set unit_id = ?, name =? , productdescription = ? where id =?";
            PreparedStatement stmt = connectDB().prepareStatement(edit);
            stmt.setLong(1, p.getUnitid().getId());
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getDescription());
            stmt.setLong(4, p.getId());
            int i = stmt.executeUpdate();
            System.out.println("Edit Successfull!!");
        } catch (SQLException e) {
            System.out.println("Update Failed!!");
            e.printStackTrace();
        }
    }
}
