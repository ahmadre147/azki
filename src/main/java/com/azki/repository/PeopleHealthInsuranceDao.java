package com.azki.repository;

import com.azki.driver.JDBCHelper;
import com.azki.model.PeopleHealthInsurance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PeopleHealthInsuranceDao implements Repository<PeopleHealthInsurance, Integer>{
    public static final String INSERT_SQL_QUERY     = "INSERT INTO [PeopleHealthInsurance]([id],[relation],[baseInsurance],[ageRange]) VALUES(?,?,?,?)";
    public static final String UPDATE_SQL_QUERY     = "UPDATE [PeopleHealthInsurance] SET [relation]=?,[baseInsurance]=?,[ageRange]=? WHERE [id]=?";
    public static final String SELECT_SQL_QUERY     = "SELECT * FROM [PeopleHealthInsurance] WHERE [id]=?";
    public static final String SELECT_ALL_SQL_QUERY = "SELECT * FROM [PeopleHealthInsurance]";
    public static final String DELETE_SQL_QUERY     = "DELETE FROM [PeopleHealthInsurance] WHERE [id]=?";

    public final Connection con;

    public PeopleHealthInsuranceDao() throws SQLException {
        con = JDBCHelper.getConnection();
    }

    @Override
    public PeopleHealthInsurance findById(Integer id) {
        PeopleHealthInsurance peopleHealthInsurance = new PeopleHealthInsurance();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return peopleHealthInsurance;
            }

            ps = con.prepareStatement(SELECT_SQL_QUERY);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.isBeforeFirst()){
                System.out.println( "No PeopleHealthInsurance by id = " + id + " is found!");
                return null;
            }

            System.out.println( "retrievePeopleHealthInsurance => " + ps.toString() );

            while (rs.next()){
                peopleHealthInsurance.setId(rs.getInt("id"));
                peopleHealthInsurance.setRelation(rs.getString("relation"));
                peopleHealthInsurance.setBaseInsurance(rs.getString("baseInsurance"));
                peopleHealthInsurance.setAgeRange(rs.getString("ageRange"));
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

        return peopleHealthInsurance;
    }

    @Override
    public List<PeopleHealthInsurance> findByIDs(Collection<Integer> ids) {
        List<PeopleHealthInsurance> peopleHealthInsurances = new ArrayList<>();

        for (Integer id : ids){
            peopleHealthInsurances.add(findById(id));
        }

        return peopleHealthInsurances;
    }

    @Override
    public List<PeopleHealthInsurance> findAll() {
        List<PeopleHealthInsurance> peopleHealthInsurances = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return peopleHealthInsurances;
            }

            ps = con.prepareStatement(SELECT_ALL_SQL_QUERY);
            rs = ps.executeQuery();

            System.out.println("retrievePeopleHealthInsurances => " + ps.toString());

            while (rs.next()) {
                PeopleHealthInsurance peopleHealthInsurance = new PeopleHealthInsurance();
                
                peopleHealthInsurance.setId(rs.getInt("id"));
                peopleHealthInsurance.setRelation(rs.getString("relation"));
                peopleHealthInsurance.setBaseInsurance(rs.getString("baseInsurance"));
                peopleHealthInsurance.setAgeRange(rs.getString("ageRange"));

                peopleHealthInsurances.add(peopleHealthInsurance);
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

        return peopleHealthInsurances;
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

            System.out.println( "deletePeopleHealthInsurance => " + ps.toString() );
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
    public PeopleHealthInsurance save(PeopleHealthInsurance E) {
        PeopleHealthInsurance peopleHealthInsurance = findById(E.getId());

        PreparedStatement ps = null;

        if (peopleHealthInsurance != null) {
            try {
                if (con == null) {
                    System.out.println("Error getting the connection. Please check if the DB server is running");
                    return null;
                }
                ps = con.prepareStatement(UPDATE_SQL_QUERY);

                ps.setString(1, E.getRelation());
                ps.setString(2, E.getBaseInsurance());
                ps.setString(3, E.getAgeRange());
                ps.setInt(4, E.getId());
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
                ps.setString(2, E.getRelation());
                ps.setString(3, E.getBaseInsurance());
                ps.setString(4, E.getAgeRange());
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
