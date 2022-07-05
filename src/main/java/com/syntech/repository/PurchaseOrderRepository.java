///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.syntech.repository;
//
//import com.syntech.model.PurchaseOrder;
////import com.syntech.model.PurchaseOrderDetail;
////import com.syntech.model.SalesOrder;
//import com.syntech.model.Supplier;
//import java.math.BigDecimal;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author rasmi
// */
//public class PurchaseOrderRepository extends AbstractRepository<PurchaseOrder> {
//
//    @Override
//    public void create(PurchaseOrder po) {
//        try {
//            String query = "select sum(total_amount) from purchaseorderdetail group by purchaseorder_id";
//            PreparedStatement smt = connectDB().prepareStatement(query);
//            ResultSet rs = smt.executeQuery();
//            while (rs.next()) {
//                String insert = "insert into purchaseorder(supplier_id,date,expected_delivery_date,total_amount) values(?,?,?,?)";
//                PreparedStatement stmt = connectDB().prepareStatement(insert);
//                stmt.setLong(1, po.getSupplierid().getId());
//                stmt.setString(2, po.getDate());
//                stmt.setString(3, po.getExpecteddeliverydate());
//                stmt.setLong(4, rs.getLong(1));
//                int i = stmt.executeUpdate();
//                System.out.println(i + " Insertion Successfull");
//            }
//        } catch (SQLException e) {
//            System.out.println("Insertion Failed!!");
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public List<PurchaseOrder> findAll() {
//        List<PurchaseOrder> list = new ArrayList<>();
//        try {
//            String query = "select * from purchaseorder";
//            PreparedStatement stmt = connectDB().prepareStatement(query);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                PurchaseOrder purchaseorder = new PurchaseOrder(rs.getLong(1), new Supplier(rs.getLong(2)), rs.getString(3), rs.getString(4), rs.getBigDecimal(5));
//                list.add(purchaseorder);
//            }
//            System.out.println("Record Display Successfully!!");
//        } catch (SQLException e) {
//            System.out.println("Record Display Failed!!");
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    @Override
//    public PurchaseOrder findById(Long id) {
//        PurchaseOrder purchaseorder = new PurchaseOrder();
//        try {
//            String query = "select * from purchaseorder where id =?";
//            PreparedStatement stmt = connectDB().prepareStatement(query);
//            stmt.setLong(1, id);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                purchaseorder = new PurchaseOrder(rs.getLong(1), new Supplier(rs.getLong(2)), rs.getString(3), rs.getString(4), rs.getBigDecimal(5));
//            }
//        } catch (SQLException e) {
//            System.out.println("Error");
//        }
//        return purchaseorder;
//    }
//
//    public List<PurchaseOrder> findByDate(String date) {
//        PurchaseOrder purchaseorder = new PurchaseOrder();
//        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
//        try {
//            String query = "select supplier_id, total_amount from purchaseorder where date =?";
//            PreparedStatement stmt = connectDB().prepareStatement(query);
//            stmt.setString(1, date);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Long id = rs.getLong("supplierid");
//                BigDecimal totalamount = rs.getBigDecimal("total_amount");
//                purchaseorder.setSupplierid(id);
//                purchaseorder.setTotalAmount(totalamount);
//                purchaseOrders.add(purchaseorder);
//            }
//        } catch (SQLException e) {
//
//        }
//        return purchaseOrders;
//    }
//
//    @Override
//    public void delete(PurchaseOrder po) {
//        try {
//            String delete = "delete from purchaseorder where id = ?";
//            PreparedStatement stmt = connectDB().prepareStatement(delete);
//            stmt.setLong(1, po.getId());
//            int i = stmt.executeUpdate();
//            System.out.println(i + " Deleted Successfully!!");
//        } catch (SQLException e) {
//            System.out.println("Deletion Failed!!");
//        }
//    }
//
//    @Override
//    public void edit(PurchaseOrder po) {
//        try {
//            String query = "select sum(total_amount) from purchaseorderdetail group by purchaseorder_id";
//            PreparedStatement smt = connectDB().prepareStatement(query);
//            ResultSet rs = smt.executeQuery();
//            while (rs.next()) {
//                String edit = "update purchaseorder set supplier_id = ?, date = ? , expected_delivery_date = ? , total_amount = ? where id = ?";
//                PreparedStatement stmt = connectDB().prepareStatement(edit);
//                stmt.setLong(1, po.getSupplierid().getId());
//                stmt.setString(2, po.getDate());
//                stmt.setString(3, po.getExpecteddeliverydate());
//                stmt.setLong(4, rs.getLong(1));
//                stmt.setLong(5, po.getId());
//                int i = stmt.executeUpdate();
//            }
//            System.out.println(" Edited Successfully!!");
//
//        } catch (SQLException e) {
//            System.out.println("Edit Failed!!!");
//        }
//    }
//
//    public void updatePurchaseOrder(PurchaseOrder po) {
//        try {
//            String update = "update purchaseorder set total_amount = (select sum(total_amount) from purchaseorderdetail where purchaseorder_id = ?) where id = ?";
//            PreparedStatement stmt = connectDB().prepareStatement(update);
//            stmt.setLong(1, po.getId());
//            stmt.setLong(2, po.getId());
//            int i = stmt.executeUpdate();
//            System.out.println(i + " Successfully updated purchase order");
//        } catch (Exception e) {
//            System.out.println("Error");
//            e.printStackTrace();
//        }
//    }
//
//    public BigDecimal CalculateTotalAmountBeforeDate(String date) {
//        BigDecimal moneyout = BigDecimal.ZERO;
//        try {
//            String query = "select sum(total_amount) from purchaseorder where date < ?";
//            PreparedStatement stmt = connectDB().prepareStatement(query);
//            stmt.setString(1, date);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                moneyout = rs.getBigDecimal(1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return moneyout == null ? BigDecimal.ZERO : moneyout;
//    }
//}
