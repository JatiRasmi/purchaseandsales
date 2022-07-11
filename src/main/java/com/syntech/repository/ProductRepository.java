///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package com.syntech.repository;

import com.syntech.model.Product;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//
///**
// *
// * @author rasmi
// */
@Stateless
public class ProductRepository extends AbstractRepository<Product> {

     @PersistenceContext(name = "psDS")
    private EntityManager em;

    public ProductRepository() {
        super(Product.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

//    @Override
//    protected EntityManager getEntityManager() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void create(Product p) {
//        try {
//            String insert = "insert into product (unit_id,name,product_description) values(?,?,?)";
//            PreparedStatement stmt = connectDB().prepareStatement(insert);
//            stmt.setLong(1, p.getUnit().getId());
//            stmt.setString(2, p.getName());
//            stmt.setString(3, p.getDescription());
//            int i = stmt.executeUpdate();
//            System.out.println(i + " Inserted Successfully!!");
//
//        } catch (SQLException e) {
////            e.printStackTrace();
//            System.out.println("***************Insertion Failed!!!!***************");
//        }
//    }
//
//    @Override
//    public List<Product> findAll() {
//        List<Product> list = new ArrayList<>();
//        try {
//            String query = "select * from product";
//            PreparedStatement stmt = connectDB().prepareStatement(query);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Product product = new Product(rs.getLong(1), new Unit(rs.getLong(2)), rs.getString(3), rs.getString(4));
////                 Product product = new Product(rs.getLong(1), new Unit(rs.getLong(2), rs.getString(2)), rs.getString(3), rs.getString(4));
//                list.add(product);
//            }
//        } catch (SQLException e) {
//            System.out.println("Record Display failed!!!");
////            e.printStackTrace();
//        }
//        return list;
//    }
//
//    @Override
//    public Product findById(Long id) {
//        Product product = new Product();
//        try {
//            String query = "select * from product where id = ?";
//            PreparedStatement stmt = connectDB().prepareStatement(query);
//            stmt.setLong(1, id);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                product = new Product(rs.getLong(1), new Unit(rs.getLong(2)), rs.getString(3), rs.getString(4));
//            }
//        } catch (SQLException e) {
//            System.out.println("Record Display Failed!!!");
//        }
//        return product;
//    }
//
//    @Override
//    public void delete(Product p) {
//        try {
//            String delete = "delete from product where id = ?";
//            PreparedStatement stmt = connectDB().prepareStatement(delete);
//            stmt.setLong(1, p.getId());
//            int i = stmt.executeUpdate();
//            System.out.println(i + " ***************Deleted Successfully!!!***************");
//        } catch (SQLException e) {
//            System.out.println("Deletion Failed!!!");
//        }
//    }
//
//    @Override
//    public void edit(Product p) {
//        try {
//            String edit = "update product set unit_id = ?, name =? , product_description = ? where id =?";
//            PreparedStatement stmt = connectDB().prepareStatement(edit);
//            stmt.setLong(1, p.getUnit().getId());
//            stmt.setString(2, p.getName());
//            stmt.setString(3, p.getDescription());
//            stmt.setLong(4, p.getId());
//            int i = stmt.executeUpdate();
//            System.out.println(i + " ***************Edit Successfull!!***************");
//        } catch (SQLException e) {
//            System.out.println("Update Failed!!");
//            e.printStackTrace();
//        }
//    }
}
