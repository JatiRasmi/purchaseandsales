/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.repository;

import com.syntech.model.Unit;
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
            String insert = "insert into unit(name) value(?)";
            PreparedStatement stmt = connectDB().prepareStatement(insert);
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
            String query = "select *from unit";
            PreparedStatement stmt = connectDB().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
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
            String query = "select *from unit where id = ?";
            PreparedStatement stmt = connectDB().prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
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
            String delete = "delete from unit where id = ?";
            PreparedStatement stmt = connectDB().prepareStatement(delete);
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
            String edit = "update unit set name = ? where id = ?";
            PreparedStatement stmt = connectDB().prepareStatement(edit);
            stmt.setString(1, u.getName());
            stmt.setLong(2, u.getId());
            int i = stmt.executeUpdate();
            System.out.println(i + " Updated successfull!!");
        } catch (SQLException e) {
            System.out.println("Edition failed!!!");
        }
    }

}
