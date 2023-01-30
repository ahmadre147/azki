package com.azki.repository;

import com.azki.driver.JDBCHelper;
import com.azki.model.PropertyInsurance;
import com.azki.model.PropertyInsurance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PropertyInsuranceDao implements Repository<PropertyInsurance, Integer> {
    public static final String INSERT_SQL_QUERY     = "INSERT INTO [PropertyInsurance]([id],[branches],[companies.id],[user.id],[ability],[price],[discount],[city],[province],[score],[contractTime],[type]) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE_SQL_QUERY     = "UPDATE [PropertyInsurance] SET [branches]=?,[companies.id]=?,[user.id]=?,[ability]=?,[price]=?,[discount]=?,[city]=?,[province]=?,[score]=?,[contractTime]=?,[type]=? WHERE id=?";
    public static final String SELECT_SQL_QUERY     = "SELECT * FROM [PropertyInsurance] WHERE [id]=?";
    public static final String SELECT_ALL_SQL_QUERY = "SELECT * FROM [PropertyInsurance]";
    public static final String DELETE_SQL_QUERY     = "DELETE FROM [PropertyInsurance] WHERE [id]=?";

    public final Connection con;

    public PropertyInsuranceDao() throws SQLException {
        con = JDBCHelper.getConnection();
    }
    @Override
    public PropertyInsurance findById(Integer id) {
        PropertyInsurance propertyInsurance = new PropertyInsurance();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return propertyInsurance;
            }

            ps = con.prepareStatement(SELECT_SQL_QUERY);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.isBeforeFirst()){
                System.out.println( "No propertyInsurance by id = " + id + " is found!");
                return null;
            }

            System.out.println( "retrievePropertyInsurance => " + ps.toString() );

            while (rs.next()){
                propertyInsurance.setId(rs.getInt("id"));
                propertyInsurance.setBranches(rs.getInt("branches"));
                propertyInsurance.setCompanies_id(rs.getInt("companies.id"));
                propertyInsurance.setUser_id(rs.getInt("user.id"));
                propertyInsurance.setAbility(rs.getInt("ability"));
                propertyInsurance.setPrice(rs.getFloat("price"));
                propertyInsurance.setDiscount(rs.getFloat("discount"));
                propertyInsurance.setCity(rs.getString("city"));
                propertyInsurance.setProvince(rs.getString("province"));
                propertyInsurance.setScore(rs.getFloat("score"));
                propertyInsurance.setContractTime(rs.getFloat("contractTime"));
                propertyInsurance.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCHelper.closePreparedStatement(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return propertyInsurance;
    }

    @Override
    public List<PropertyInsurance> findByIDs(Collection<Integer> ids) {
        List<PropertyInsurance> propertyInsurances = new ArrayList<>();

        for (Integer id : ids){
            propertyInsurances.add(findById(id));
        }

        return propertyInsurances;
    }

    @Override
    public List<PropertyInsurance> findAll() {
        List<PropertyInsurance> propertyInsurances = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return propertyInsurances;
            }

            ps = con.prepareStatement(SELECT_ALL_SQL_QUERY);
            rs = ps.executeQuery();

            System.out.println("retrievePropertyInsurances => " + ps.toString());

            while (rs.next()) {
                PropertyInsurance propertyInsurance = new PropertyInsurance();

                propertyInsurance.setId(rs.getInt("id"));
                propertyInsurance.setBranches(rs.getInt("branches"));
                propertyInsurance.setCompanies_id(rs.getInt("companies.id"));
                propertyInsurance.setUser_id(rs.getInt("user.id"));
                propertyInsurance.setAbility(rs.getInt("ability"));
                propertyInsurance.setPrice(rs.getFloat("price"));
                propertyInsurance.setDiscount(rs.getFloat("discount"));
                propertyInsurance.setCity(rs.getString("city"));
                propertyInsurance.setProvince(rs.getString("province"));
                propertyInsurance.setScore(rs.getFloat("score"));
                propertyInsurance.setContractTime(rs.getFloat("contractTime"));
                propertyInsurance.setType(rs.getString("type"));

                propertyInsurances.add(propertyInsurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCHelper.closePreparedStatement(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return propertyInsurances;
    }

    @Override
    public Boolean deleteByID(Integer id) {
        if (findById(id) == null){
            return false;
        }

        PreparedStatement ps = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return false;
            }

            ps = con.prepareStatement(DELETE_SQL_QUERY);
            ps.setInt(1, id);
            ps.execute();

            System.out.println( "deletePropertyInsurance => " + ps.toString() );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCHelper.closePreparedStatement(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public Boolean deleteByIDs(Collection<Integer> ids) {
        for (Integer id: ids){
            if (!deleteByID(id)){
                return false;
            }
        }

        return true;
    }

    @Override
    public PropertyInsurance save(PropertyInsurance E) {
        PropertyInsurance propertyInsurance = findById(E.getId());

        PreparedStatement ps = null;

        if (propertyInsurance != null) {
            try {
                if (con == null) {
                    System.out.println("Error getting the connection. Please check if the DB server is running");
                    return null;
                }

                ps = con.prepareStatement(UPDATE_SQL_QUERY);
                ps.setInt(1, E.getBranches());
                ps.setInt(2, E.getCompanies_id());
                ps.setInt(3, E.getUser_id());
                ps.setInt(4, E.getAbility());
                ps.setFloat(5, E.getPrice());
                ps.setFloat(6, E.getDiscount());
                ps.setString(7, E.getCity());
                ps.setString(8, E.getProvince());
                ps.setFloat(9, E.getScore());
                ps.setFloat(10, E.getContractTime());
                ps.setString(11, E.getType());
                ps.setInt(12, E.getId());
                ps.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    JDBCHelper.closePreparedStatement(ps);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                if (con == null) {
                    System.out.println("Error getting the connection. Please check if the DB server is running");
                    return null;
                }
                ps = con.prepareStatement(INSERT_SQL_QUERY);
                ps.setInt(1, E.getId());
                ps.setInt(2, E.getBranches());
                ps.setInt(3, E.getCompanies_id());
                ps.setInt(4, E.getUser_id());
                ps.setInt(5, E.getAbility());
                ps.setFloat(6, E.getPrice());
                ps.setFloat(7, E.getDiscount());
                ps.setString(8, E.getCity());
                ps.setString(9, E.getProvince());
                ps.setFloat(10, E.getScore());
                ps.setFloat(11, E.getContractTime());
                ps.setString(12, E.getType());

                ps.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    JDBCHelper.closePreparedStatement(ps);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return findById(E.getId());
    }
}
