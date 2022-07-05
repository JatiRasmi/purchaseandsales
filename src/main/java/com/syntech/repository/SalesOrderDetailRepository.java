///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.syntech.repository;
//
//import com.syntech.model.Product;
//import com.syntech.model.SalesOrder;
//import com.syntech.model.SalesOrderDetail;
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
//public class SalesOrderDetailRepository extends AbstractRepository<SalesOrderDetail> {
//
//    private static SalesOrderRepository sor;
//
//    @Override
//    public void create(SalesOrderDetail sod) {
//        sor = new SalesOrderRepository();
//        try {
//            String insert = "insert into salesorderdetail(salesorder_id, product_id, quantity, rate, sub_total, discount_percent, discount_amount, sub_total_after_discount, vat_percent, vat_amount, total_amount) values(?,?,?,?,?,?,?,?,?,?,?)";
//            PreparedStatement stmt = connectDB().prepareStatement(insert);
//            stmt.setLong(1, sod.getSalesorder().getId());
//            stmt.setLong(2, sod.getProduct().getId());
//            stmt.setLong(3, sod.getQuantity());
//            stmt.setLong(4, sod.getRate());
//            stmt.setLong(5, sod.getSubtotal());
//            stmt.setLong(6, sod.getDiscount());
//            stmt.setLong(7, sod.getDiscountamount());
//            stmt.setLong(8, sod.getSubtotalAfterDiscount());
//            stmt.setLong(9, sod.getVat());
//            stmt.setLong(10, sod.getVatamount());
//            stmt.setLong(11, sod.getTotalamount());
//            int i = stmt.executeUpdate();
//            sor.updateSalesOrder(sod.getSalesorder());
//
//            System.out.println(i + " Inserted Successfull!!!");
//        } catch (SQLException e) {
//            System.out.println("Insertion Failed!!!");
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public List<SalesOrderDetail> findAll() {
//        List<SalesOrderDetail> list = new ArrayList<>();
//        try {
//            String query = "select * from salesorderdetail";
//            PreparedStatement stmt = connectDB().prepareStatement(query);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                SalesOrderDetail salesod = new SalesOrderDetail(rs.getLong(1), new SalesOrder(rs.getLong(2)), new Product(rs.getLong(3)), rs.getLong(4), rs.getLong(5), rs.getLong(6), rs.getLong(7), rs.getLong(8), rs.getLong(9), rs.getLong(10), rs.getLong(11), rs.getLong(12));
//                list.add(salesod);
//            }
//        } catch (SQLException e) {
//            System.out.println("Record Display Failed!!!");
//        }
//        return list;
//    }
//
//    @Override
//    public SalesOrderDetail findById(Long id) {
//        SalesOrderDetail sod = new SalesOrderDetail();
//        try {
//            String query = "select * from salesorderdetail where id = ? ";
//            PreparedStatement stmt = connectDB().prepareStatement(query);
//            stmt.setLong(1, id);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                sod = new SalesOrderDetail(rs.getLong(1), new SalesOrder(rs.getLong(2)), new Product(rs.getLong(3)), rs.getLong(4), rs.getLong(5), rs.getLong(6), rs.getLong(7), rs.getLong(8), rs.getLong(9), rs.getLong(10), rs.getLong(11), rs.getLong(12));
//            }
//        } catch (SQLException e) {
//            System.out.println("Record Display Failed!!");
//        }
//        return sod;
//    }
//
//    @Override
//    public void delete(SalesOrderDetail sod) {
//        try {
//            String delete = "delete from salesorderdetail where id = ?";
//            PreparedStatement stmt = connectDB().prepareStatement(delete);
//            stmt.setLong(1, sod.getId());
//            int i = stmt.executeUpdate();
//            System.out.println("Deletion Successfull!!!");
//        } catch (SQLException e) {
//            System.out.println("Deletion Failed");
//        }
//    }
//
//    @Override
//    public void edit(SalesOrderDetail sod) {
//        try {
//            String edit = "update salesorderdetail set salesorder_id = ? , product_id = ? ,quantity = ? , rate = ? , sub_total = ? , discount_percent = ? , discount_amount = ? ,sub_total_after_discount = ? , vat_percent = ? , vat_amount = ? , total_amount =? where id  =? ";
//            PreparedStatement stmt = connectDB().prepareStatement(edit);
//            stmt.setLong(1, sod.getSalesorder().getId());
//            stmt.setLong(2, sod.getProduct().getId());
//            stmt.setLong(3, sod.getQuantity());
//            stmt.setLong(4, sod.getRate());
//            stmt.setLong(5, sod.getSubtotal());
//            stmt.setLong(6, sod.getDiscount());
//            stmt.setLong(7, sod.getDiscountamount());
//            stmt.setLong(8, sod.getSubtotalAfterDiscount());
//            stmt.setLong(9, sod.getVat());
//            stmt.setLong(10, sod.getVatamount());
//            stmt.setLong(11, sod.getTotalamount());
//            stmt.setLong(12, sod.getId());
//            int i = stmt.executeUpdate();
//            sor.updateSalesOrder(sod.getSalesorder());
//            System.out.println(i + " Edited Successfully!!");
//        } catch (SQLException e) {
//            System.out.println("Edit Failed!!");
//            e.printStackTrace();
//        }
//    }
//}
