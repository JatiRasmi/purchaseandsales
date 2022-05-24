/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Customer;
import com.syntech.model.SalesOrder;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rasmi
 */
public class SalesOrderRepository extends AbstractRepository<SalesOrder> {

    @Override
    public void create(SalesOrder so) {
        try {
            String query = "select sum(total_amount) from salesorderdetail group by salesorder_id";
            PreparedStatement smt = connectDB().prepareStatement(query);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                String insert = "insert into salesorder(customer_id, date, total_amount) value(?,?,?)";
                PreparedStatement stmt = connectDB().prepareStatement(insert);
                stmt.setLong(1, so.getCustomer().getId());
                stmt.setString(2, so.getDate());
                stmt.setLong(3, rs.getLong(1));
                int i = stmt.executeUpdate();
                System.out.println(i + " Insertion Successfull");
            }
        } catch (SQLException e) {
            System.out.println("Insertion Failed!!");
            e.printStackTrace();
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
                SalesOrder salesorder = new SalesOrder(rs.getLong(1), new Customer(rs.getLong(2)), rs.getString(3), rs.getBigDecimal(4));
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
                salesorder = new SalesOrder(rs.getLong(1), new Customer(rs.getLong(2)), rs.getString(3), rs.getBigDecimal(4));
            }
        } catch (SQLException e) {
            System.out.println("Record Display Failed!!!");
            e.printStackTrace();
        }
        return salesorder;
    }

    public List<SalesOrder> findByDate(String date) {
        SalesOrder salesorder = new SalesOrder();
        List<SalesOrder> salesOrders = new ArrayList<>();
        try {
            String query = "select customer_id, total_amount from salesorder where date =?";
            PreparedStatement stmt = connectDB().prepareStatement(query);
            stmt.setString(1, date);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                BigDecimal totalamount = rs.getBigDecimal("totalamount");
                salesorder.setCustomer(id);
                salesorder.setTotalAmount(totalamount);
                salesOrders.add(salesorder);
            }
        } catch (SQLException e) {
            System.out.println("Record Display Failed!!!");
        }
        return salesOrders;
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
        try {
            String query = "select sum(total_amount) from salesorderdetail group by salesorder_id";
            PreparedStatement smt = connectDB().prepareStatement(query);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {

                String edit = "update salesorder set customer_id = ?, date = ? , total_amount = ? where id = ?";
                PreparedStatement stmt = connectDB().prepareStatement(edit);
                stmt.setLong(1, so.getCustomer().getId());
                stmt.setString(2, so.getDate());
                stmt.setLong(4, rs.getLong(1));
                stmt.setLong(3, so.getId());
                int i = stmt.executeUpdate();
            }
            System.out.println(" Edited Successfully!!");
        } catch (SQLException e) {
            System.out.println("Edit Failed!!!");
        }
    }

    public void updateSalesOrder(SalesOrder so) {
        try {
            String update = "update salesorder set total_amount = (select sum(total_amount) from salesorderdetail where salesorder_id = ?) where id = ?";
            PreparedStatement stmt = connectDB().prepareStatement(update);
            stmt.setLong(1, so.getId());
            stmt.setLong(2, so.getId());
            int i = stmt.executeUpdate();
            System.out.println(i + " Successfully updated sales order");
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public BigDecimal CalculateTotalAmountBeforeDate(String date) {
        BigDecimal moneyIn = BigDecimal.ZERO;
        try {
            String query = "select sum(total_amount) from salesorder where date <?";
            PreparedStatement stmt = connectDB().prepareStatement(query);
            stmt.setString(1, date);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                moneyIn = rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moneyIn == null ? BigDecimal.ZERO : moneyIn;
    }
}
