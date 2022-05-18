/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Customer;
import com.syntech.model.SalesOrder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rasmi
 */
public class SalesOrderRepository extends AbstractRepository<SalesOrder>{
    @Override
    public void create(SalesOrder so) {
        try {
            String insert = "insert into salesorder(customer_id,date) values(?,?)";
            PreparedStatement stmt = connectDB().prepareStatement(insert);
            stmt.setLong(1, so.getCustomer().getId());
            stmt.setString(2, so.getDate());
            int i = stmt.executeUpdate();
            System.out.println("Insertion Successfull");
        } catch (SQLException e) {
            System.out.println("Insertion Faailed!!");
//            e.printStackTrace();
        }
    }

    @Override
    public List<SalesOrder> findAll() {
        List<SalesOrder> list = new ArrayList<>();
        try {
            String query = "select * from salesorder";
            PreparedStatement stmt = connectDB().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SalesOrder salesorder = new SalesOrder(rs.getLong(1), new Customer(rs.getLong(2)), rs.getString(3));
                list.add(salesorder);
            }
            System.out.println("Record Display Successfully!!");
        } catch (SQLException e) {
            System.out.println("Record Display Failed!!");
//            e.printStackTrace();
        }
        return list;
    }

    @Override
    public SalesOrder findById(Long id) {
        SalesOrder salesorder = new SalesOrder();
        try {
            String query = "select * from salesorder where id =?";
            PreparedStatement stmt = connectDB().prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                salesorder = new SalesOrder(rs.getLong(1), new Customer(rs.getLong(2)), rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("Record Display Failed!!!");
            e.printStackTrace();
        }
        return salesorder;
    }

    @Override
    public void delete(SalesOrder so) {
        try {
            String delete = "delete from salesorder where id = ?";
            PreparedStatement stmt = connectDB().prepareStatement(delete);
            stmt.setLong(1, so.getId());
            int i = stmt.executeUpdate();
            System.out.println(i + " Deleted Successfully!!");
        } catch (SQLException e) {
            System.out.println("Deletion Failed!!");
        }
    }
    @Override
    public void edit(SalesOrder so) {
        try{
            String edit = "update salesorder set customer_id = ?, date = ? where id = ?";
            PreparedStatement stmt = connectDB().prepareStatement(edit);
            stmt.setLong(1, so.getCustomer().getId());
            stmt.setString(2,so.getDate());
            stmt.setLong(3, so.getId());
            int i = stmt.executeUpdate();
            System.out.println(i + " Edited Successfully!!");
        }catch(SQLException e){
            System.out.println("Edit Failed!!!");
        }
    }    
}
