/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.PurchaseOrder;
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
public class PurchaseOrderRepository extends AbstractRepository<PurchaseOrder> {

    @Override
    public void create(PurchaseOrder po) {
        try {
            String insert = "insert into purchaseorder(supplier_id,date,expecteddeliverydate) values(?,?,?)";
            PreparedStatement stmt = connectDB().prepareStatement(insert);
            stmt.setLong(1, po.getSupplierid().getId());
            stmt.setString(2, po.getDate());
            stmt.setString(3, po.getExpecteddeliverydate());
            int i = stmt.executeUpdate();
            System.out.println("Insertion Successfull");
        } catch (SQLException e) {
            System.out.println("Insertion Faailed!!");
//            e.printStackTrace();
        }
    }

    @Override
    public List<PurchaseOrder> findAll() {
        List<PurchaseOrder> list = new ArrayList<>();
        try {
            String query = "select * from purchaseorder";
            PreparedStatement stmt = connectDB().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PurchaseOrder purchaseorder = new PurchaseOrder(rs.getLong(1), new Supplier(rs.getLong(2)), rs.getString(3), rs.getString(4));
                list.add(purchaseorder);
            }
            System.out.println("Record Display Successfully!!");
        } catch (SQLException e) {
            System.out.println("Record Display Failed!!");
//            e.printStackTrace();
        }
        return list;
    }

    @Override
    public PurchaseOrder findById(Long id) {
        PurchaseOrder purchaseorder = new PurchaseOrder();
        try {
            String query = "select * from purchaseorder where id =?";
            PreparedStatement stmt = connectDB().prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                purchaseorder = new PurchaseOrder(rs.getLong(1), new Supplier(rs.getLong(2)), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("Record Display Failed!!!");
        }
        return purchaseorder;
    }

    @Override
    public void delete(PurchaseOrder po) {
        try {
            String delete = "delete from purchaseorder where id = ?";
            PreparedStatement stmt = connectDB().prepareStatement(delete);
            stmt.setLong(1, po.getId());
            int i = stmt.executeUpdate();
            System.out.println(i + " Deleted Successfully!!");
        } catch (SQLException e) {
            System.out.println("Deletion Failed!!");
        }
    }
    @Override
    public void edit(PurchaseOrder po) {
        try{
            String edit = "update purchaseorder set supplier_id = ?, date = ? , expecteddeliverydate = ? where id = ?";
            PreparedStatement stmt = connectDB().prepareStatement(edit);
            stmt.setLong(1, po.getSupplierid().getId());
            stmt.setString(2,po.getDate());
            stmt.setString(3,po.getExpecteddeliverydate());
            stmt.setLong(4, po.getId());
            int i = stmt.executeUpdate();
            System.out.println(i + " Edited Successfully!!");
        }catch(SQLException e){
            System.out.println("Edit Failed!!!");
        }
    }
}
