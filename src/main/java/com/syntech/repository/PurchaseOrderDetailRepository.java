///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.syntech.repository;
//
//import com.syntech.model.Product;
//import com.syntech.model.PurchaseOrder;
//import com.syntech.model.PurchaseOrderDetail;
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
//public class PurchaseOrderDetailRepository extends AbstractRepository<PurchaseOrderDetail> {
//
//    private static PurchaseOrderRepository por;
//
//    @Override
//    public void create(PurchaseOrderDetail pod) {
//        por = new PurchaseOrderRepository();
//        try {
//            String insert = "insert into purchaseorderdetail(purchaseorder_id, product_id, quantity, rate, sub_total, discount_percent, discount_amount, sub_total_after_discount, vat_percent, vat_amount, total_amount) values(?,?,?,?,?,?,?,?,?,?,?)";
//            PreparedStatement stmt = connectDB().prepareStatement(insert);
//            stmt.setLong(1, pod.getPurchaseorder().getId());
//            stmt.setLong(2, pod.getProduct().getId());
//            stmt.setLong(3, pod.getQuantity());
//            stmt.setLong(4, pod.getRate());
//            stmt.setLong(5, pod.getSubtotal());
//            stmt.setLong(6, pod.getDiscount());
//            stmt.setLong(7, pod.getDiscountamount());
//            stmt.setLong(8, pod.getSubtotalAfterDiscount());
//            stmt.setLong(9, pod.getVat());
//            stmt.setLong(10, pod.getVatamount());
//            stmt.setLong(11, pod.getTotalamount());
//            int i = stmt.executeUpdate();
//
//            por.updatePurchaseOrder(pod.getPurchaseorder());
//            System.out.println(i + " Inserted Successfull!!!");
//        } catch (Exception e) {
//            System.out.println("Insertion Failed!!!");
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public List<PurchaseOrderDetail> findAll() {
//        List<PurchaseOrderDetail> list = new ArrayList<>();
//        try {
//            String query = "select * from purchaseorderdetail";
//            PreparedStatement stmt = connectDB().prepareStatement(query);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
////                PurchaseOrderDetail purchaseod = new PurchaseOrderDetail(rs.getLong(1), new PurchaseOrder(rs.getLong(2)), new Product(rs.getLong(3), rs.getString(4)), rs.getLong(5), rs.getLong(6), rs.getLong(7), rs.getLong(8), rs.getLong(9), rs.getLong(10), rs.getLong(11), rs.getLong(12), rs.getLong(13));
//                PurchaseOrderDetail purchaseod = new PurchaseOrderDetail(rs.getLong(1), new PurchaseOrder(rs.getLong(2)), new Product(rs.getLong(3)), rs.getLong(4), rs.getLong(5), rs.getLong(6), rs.getLong(7), rs.getLong(8), rs.getLong(9), rs.getLong(10), rs.getLong(11), rs.getLong(12));
//                list.add(purchaseod);
//            }
//        } catch (SQLException e) {
//            System.out.println("Record Display Failed!!!");
//        }
//        return list;
//    }
//
//    @Override
//    public PurchaseOrderDetail findById(Long id) {
//        PurchaseOrderDetail pod = new PurchaseOrderDetail();
//        try {
//            String query = "select * from purchaseorderdetail where id = ? ";
//            PreparedStatement stmt = connectDB().prepareStatement(query);
//            stmt.setLong(1, id);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                pod = new PurchaseOrderDetail(rs.getLong(1), new PurchaseOrder(rs.getLong(2)), new Product(rs.getLong(3)), rs.getLong(4), rs.getLong(5), rs.getLong(6), rs.getLong(7), rs.getLong(8), rs.getLong(9), rs.getLong(10), rs.getLong(11), rs.getLong(12));
//            }
//        } catch (SQLException e) {
//            System.out.println("Record Display Failed!!");
//        }
//        return pod;
//    }
//
//    @Override
//    public void delete(PurchaseOrderDetail pod) {
//        try {
//            String delete = "delete from purchaseorderdetail where id = ?";
//            PreparedStatement stmt = connectDB().prepareStatement(delete);
//            stmt.setLong(1, pod.getId());
//            int i = stmt.executeUpdate();
//            System.out.println("Deletion Successfull!!!");
//        } catch (SQLException e) {
//            System.out.println("Deletion Failed");
//        }
//    }
//
//    @Override
//    public void edit(PurchaseOrderDetail pod) {
//        try {
//            String edit = "update purchaseorderdetail set purchaseorder_id = ? , product_id = ? ,quantity = ? , rate = ? , sub_total = ? , discount_percent = ? , discount_amount = ? ,sub_total_after_discount = ? , vat_percent = ? , vat_amount = ? , total_amount =? where id  =? ";
//            PreparedStatement stmt = connectDB().prepareStatement(edit);
//            stmt.setLong(1, pod.getPurchaseorder().getId());
//            stmt.setLong(2, pod.getProduct().getId());
//            stmt.setLong(3, pod.getQuantity());
//            stmt.setLong(4, pod.getRate());
//            stmt.setLong(5, pod.getSubtotal());
//            stmt.setLong(6, pod.getDiscount());
//            stmt.setLong(7, pod.getDiscountamount());
//            stmt.setLong(8, pod.getSubtotalAfterDiscount());
//            stmt.setLong(9, pod.getVat());
//            stmt.setLong(10, pod.getVatamount());
//            stmt.setLong(11, pod.getTotalamount());
//            stmt.setLong(12, pod.getId());
//            int i = stmt.executeUpdate();
//            por.updatePurchaseOrder(pod.getPurchaseorder());
//            System.out.println(i + " Edited Successfully!!");
//        } catch (SQLException e) {
//            System.out.println("Edit Failed!!");
//            e.printStackTrace();
//        }
//    }
//}
