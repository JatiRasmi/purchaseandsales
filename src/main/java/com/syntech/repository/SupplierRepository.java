/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author rasmi
 */
public class SupplierRepository extends AbstractRepository<Supplier> {

    
    @Override
    public void create(Supplier supplier){
        try {
            Connection con = connectDB();
            String insert = "INSERT INTO supplier(name, address, email, contact) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(insert);
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getAddress());
            stmt.setString(3, supplier.getEmail());
            stmt.setString(4, supplier.getContact());
            int i = stmt.executeUpdate();
            System.out.println(i + " Insertion for supplier successfull!!");
        } catch (Exception e) {
            System.out.println("Insertion for Supplier failed!!!");
        }
    
    }
    

        
    @Override
    public void edit(Supplier supplier) {
//        try {
//            Connection con = connectDB();
//            String edit = "UPDATE supplier SET name = ?, address = ?, email = ? ,contact = ? where id = ?";
//            PreparedStatement stmt = con.prepareStatement(edit);
//            stmt.setString(1, supplier.getName());
//            stmt.setString(2, supplier.getAddress());
//            stmt.setString(3, supplier.getEmail());
//            stmt.setString(4, supplier.getContact());
//            stmt.execute();
//            stmt.close();
//            con.close();
//        } catch (Exception e) {
//            System.out.println("Supplier's Edit failed!!!");
//        }
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
