/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Supplier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rasmi
 */
public class SupplierRepository extends AbstractRepository<Supplier> {

    @Override
    public void create(Supplier s) {
        try {

            String insert = "insert into supplier(name,address,email,contact,description) values(?,?,?,?,?)";
            PreparedStatement stmt = connectDB().prepareStatement(insert);
            stmt.setString(1, s.getName());
            stmt.setString(2, s.getAddress());
            stmt.setString(3, s.getEmail());
            stmt.setString(4, s.getContact());
            stmt.setString(5, s.getDescription());
            int i = stmt.executeUpdate();
            System.out.println(i + " Inserted successfull!!");
        } catch (SQLException e) {
            System.out.println("Insertion Failed!!!");
        }
    }

    @Override
    public List<Supplier> findAll() {
        List<Supplier> list = new ArrayList<>();
        try {
            String query = "select *from supplier";
            PreparedStatement stmt = connectDB().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Supplier supplier = new Supplier(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                list.add(supplier);
            }
        } catch (SQLException e) {
            System.out.println("Record Display Failed!!!");
        }
        return list;

    }

    @Override
    public Supplier findById(Long id) {
        Supplier supplier = new Supplier();
        try {
            String query = "select *from supplier where id = ?";
            PreparedStatement stmt = connectDB().prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                supplier = new Supplier(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (SQLException e) {
            System.out.println("Record Display Failed!!");
        }
        return supplier;
    }

    @Override
    public void delete(Supplier s) {
        try {
            String delete = "delete from supplier where id = ?";
            PreparedStatement stmt = connectDB().prepareStatement(delete);
            stmt.setLong(1, s.getId());
            int i = stmt.executeUpdate();
            System.out.println(i + " Deleted successfully!!");
        } catch (SQLException e) {
            System.out.println("Deletion Failed!!!!");
        }
    }

    @Override
    public void edit(Supplier s) {
        try {
            String edit = "update supplier set name = ?, address = ? , email = ? , contact = ? , description = ? where id = ?";
            PreparedStatement stmt = connectDB().prepareStatement(edit);
            stmt.setString(1, s.getName());
            stmt.setString(2, s.getAddress());
            stmt.setString(3, s.getEmail());
            stmt.setString(4, s.getContact());
            stmt.setString(5, s.getDescription());
            stmt.setLong(6, s.getId());
            int i = stmt.executeUpdate();
            System.out.println(i + " Edited Successfully!!");
        }catch(SQLException e){
            System.out.println("Edition Failed!!");
        }
//        super.findAll()
//                .stream()
//                .filter(n -> n.getId().equals(supplier.getId()))
//                .forEach(s -> {
//                    s.setName(supplier.getName());
//                    s.setAddress(supplier.getAddress());
//                    s.setEmail(supplier.getEmail());
//                    s.setContact(supplier.getContact());
//                    s.setDescription(supplier.getDescription());
//                });
    }
}
