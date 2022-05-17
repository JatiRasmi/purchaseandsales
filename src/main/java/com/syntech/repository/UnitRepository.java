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

    Connection con = connectDB();
    PreparedStatement stmt;
    ResultSet rs;

    @Override
    public void create(Unit u) {

        try {
            String insert = "insert into unit(name) value(?)";
            stmt = con.prepareStatement(insert);
            stmt.setString(1, u.getName());
            int i = stmt.executeUpdate();
            System.out.println(i + " Inserted successfull!!");

        } catch (SQLException e) {
            System.out.println("Insertion failed!!!");
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
//            Connection con = connectDB();
            String query = "select *from unit";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Unit unit = new Unit(rs.getLong(1), rs.getString(2));
                list.add(unit);
            }
        } catch (SQLException e) {
            System.out.println("Record Display failed!!!");
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
//            Connection con = connectDB();
            String query = "select *from unit where id = ?";
            stmt = con.prepareStatement(query);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                unit = new Unit(rs.getLong(1), rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Record Display failed!!!");
        }
        return unit;
    }

    @Override

    public void delete(Unit u) {
        try {
//            Connection con = connectDB();
            String delete = "delete from unit where id = ?";
            stmt = con.prepareStatement(delete);
            stmt.setLong(1, u.getId());
            int i = stmt.executeUpdate();
            System.out.println(i + " Deleted successfull!!");
        } catch (SQLException e) {
            System.out.println("Deletion failed!!!");
        }
    }

    /**
     *
     * @param u
     */
    @Override
    public void edit(Unit u) {
        try {
//            Connection con = connectDB();
            String edit = "update unit set name = ? where id = ?";
            stmt = con.prepareStatement(edit);
            stmt.setString(1, u.getName());
            stmt.setLong(2, u.getId());
            int i = stmt.executeUpdate();
            System.out.println(i + " Updated successfull!!");
        } catch (SQLException e) {
            System.out.println("Edition failed!!!");
        }
    }

}
