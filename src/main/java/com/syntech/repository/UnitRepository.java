/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Unit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rasmi
 */
public class UnitRepository extends AbstractRepository<Unit> {

    @Override
    public void create(Unit u) {

        try {
            Connection con = connectDB();
            String insert = "insert into unit(name) value(?)";
            PreparedStatement stmt = con.prepareStatement(insert);
            stmt.setString(1, u.getName());
            int i = stmt.executeUpdate();
            System.out.println(i + " Insertion for unit successfull!!");

        } catch (SQLException e) {
            System.out.println("Insertion for unit failed!!!");
        }
    }

    /**
     *
     * @return
     */
    @Override
    public List<Unit> findAll() {
        List<Unit> list = new ArrayList<>();
        try {
            Connection con = connectDB();
            String query = "select *from unit";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Unit unit = new Unit(rs.getLong(1), rs.getString(2));
                list.add(unit);
            }
        } catch (SQLException e) {
            System.out.println("Unit display failed!!!");
        }
        return list;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Unit findById(Long id) {
        Unit unit = new Unit();
        try {
            Connection con = connectDB();
            String query = "select *from unit where id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                unit = new Unit(rs.getLong(1), rs.getString(2));

            }
        } catch (SQLException e) {
            System.out.println("Unit display failed!!!");
        }
        return unit;
    }

    @Override

    public void delete(Unit u) {
        try {
            Connection con = connectDB();
            String delete = "delete from unit where id = ?";
            PreparedStatement stmt = con.prepareStatement(delete);
            stmt.setLong(1, u.getId());
            int i = stmt.executeUpdate();
            System.out.println(i + " Deletion for unit successfull!!");
        } catch (SQLException e) {
            System.out.println("Deletion for unit failed!!!");
        }
    }

    /**
     *
     * @param u
     */
    @Override
    public void edit(Unit u){
//        super.findAll().stream()
//                    .filter(n -> n.getId().equals(u.getId()))
//                    .forEach((Unit un) -> {
//                        un.setName(u.getName());
//                    });
    }

}
