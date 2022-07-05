///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.syntech.repository;
//
//import com.syntech.model.Customer;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
///**
// *
// * @author rasmi
// */
//@Stateless
//public class CustomerRepository extends AbstractRepository<Customer> {
//
//    @PersistenceContext(name = "psDS")   // inject entity manager
//    private EntityManager em;
//
//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }
//
////    public Customer findAll(){
////        Query query = em.createQuery("SELECT c FROM Customer c ", Customer.class);    //c or any variable instead of * , Customer is class name
////        Customer customer = (Customer) query.getSingleResult();
////        return customer;
////
////    }
//    
//    public Customer findByEmail(String email) {
//        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.email=: e", Customer.class);    //c or any variable instead of * , Customer is class name
//        query.setParameter("e", email);
//        Customer customer = (Customer) query.getSingleResult();
//        return customer;
//    }
//    
//    
////    public Customer findById(Long id) {
////        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.id=: e", Customer.class);    //c or any variable instead of * , Customer is class name
////        query.setParameter("e", id);
////        Customer customer = (Customer) query.getSingleResult();
////        return customer;
////    }
//
////    @Override
////    public void create(Customer c) {
////        try {
////
////            String insert = "insert into customer(name,address,email,contact) values(?,?,?,?)";
////            PreparedStatement stmt = connectDB().prepareStatement(insert);
////            stmt.setString(1, c.getName());
////            stmt.setString(2, c.getAddress());
////            stmt.setString(3, c.getEmail());
////            stmt.setString(4, c.getContact());
////            int i = stmt.executeUpdate();
////            System.out.println(i + " Inserted successfull!!");
////        } catch (SQLException e) {
////            e.printStackTrace();
////            System.out.println("Insertion Failed!!!");
////        }
////    }
////
////    @Override
////    public List<Customer> findAll() {
////        List<Customer> list = new ArrayList<>();
////        try {
////            String query = "select *from customer";
////            PreparedStatement stmt = connectDB().prepareStatement(query);
////            ResultSet rs = stmt.executeQuery();
////            while (rs.next()) {
////                Customer customer = new Customer(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
////                list.add(customer);
////            }
////        } catch (SQLException e) {
////            System.out.println("Record Display Failed!!!");
////        }
////        return list;
////
////    }
////
////    @Override
////    public Customer findById(Long id) {
////        Customer customer = new Customer();
////        try {
////            String query = "select *from customer where id = ?";
////            PreparedStatement stmt = connectDB().prepareStatement(query);
////            stmt.setLong(1, id);
////            ResultSet rs = stmt.executeQuery();
////            while (rs.next()) {
////                customer = new Customer(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
////            }
////        } catch (SQLException e) {
////            System.out.println("Record Display Failed!!");
////        }
////        return customer;
////    }
////
////    @Override
////    public void delete(Customer c) {
////        try {
////            String delete = "delete from customer where id = ?";
////            PreparedStatement stmt = connectDB().prepareStatement(delete);
////            stmt.setLong(1, c.getId());
////            int i = stmt.executeUpdate();
////            System.out.println(i + " Deleted successfully!!");
////        } catch (SQLException e) {
////            System.out.println("Deletion Failed!!!!");
////        }
////    }
////
////    @Override
////    public void edit(Customer c) {
////        try {
////            String edit = "update customer set name = ?, address = ? , email = ? , contact = ? where id = ?";
////            PreparedStatement stmt = connectDB().prepareStatement(edit);
////            stmt.setString(1, c.getName());
////            stmt.setString(2, c.getAddress());
////            stmt.setString(3, c.getEmail());
////            stmt.setString(4, c.getContact());
////            stmt.setLong(5, c.getId());
////            int i = stmt.executeUpdate();
////            System.out.println(i + " Edited Successfully!!");
////        } catch (SQLException e) {
////            System.out.println("Edition Failed!!");
////        }
////    }
//}
