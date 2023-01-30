package com.azki.repository;

import com.azki.driver.JDBCHelper;
import com.azki.model.LifeInsurance;
import com.azki.model.LifeInsurance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LifeInsuranceDao implements Repository<LifeInsurance, Integer> {
    public static final String INSERT_SQL_QUERY     = "INSERT INTO [LifeInsurance]([id],[medicalExpenses],[companies.id],[deathCapital],[buybackValue],[user.id],[contractTime]) VALUES(?,?,?,?,?,?,?)";
    public static final String UPDATE_SQL_QUERY     = "UPDATE [LifeInsurance] SET [medicalExpenses]=?,[companies.id]=?,[deathCapital]=?,[buybackValue]=?,[user.id]=?,[contractTime]=? WHERE [id]=?";
    public static final String SELECT_SQL_QUERY     = "SELECT * FROM [LifeInsurance] WHERE [id]=?";
    public static final String SELECT_ALL_SQL_QUERY = "SELECT * FROM [LifeInsurance]";
    public static final String DELETE_SQL_QUERY     = "DELETE FROM [LifeInsurance] WHERE [id]=?";


    public final Connection con;

    public LifeInsuranceDao() throws SQLException {
        con = JDBCHelper.getConnection();
    }

    @Override
    public LifeInsurance findById(Integer id) {
        LifeInsurance lifeInsurance = new LifeInsurance();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return lifeInsurance;
            }

            ps = con.prepareStatement(SELECT_SQL_QUERY);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.isBeforeFirst()){
                System.out.println( "No lifeInsurance by id = " + id + " is found!");
                return null;
            }

            System.out.println( "retrieveLifeInsurance => " + ps.toString() );

            while (rs.next()){
                lifeInsurance.setId(rs.getInt("id"));
                lifeInsurance.setMedicalExpenses(rs.getFloat("medicalExpenses"));
                lifeInsurance.setCompanies_id(rs.getInt("[companies.id]"));
                lifeInsurance.setDeathCapital(rs.getFloat("deathCapital"));
                lifeInsurance.setBuybackValue(rs.getFloat("buybackValue"));
                lifeInsurance.setUser_id(rs.getInt("[user.id]"));
                lifeInsurance.setContractTime(rs.getInt("contractTime"));
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

        return lifeInsurance;
    }

    @Override
    public List<LifeInsurance> findByIDs(Collection<Integer> ids) {
        List<LifeInsurance> lifeInsurances = new ArrayList<>();

        for (Integer id : ids){
            lifeInsurances.add(findById(id));
        }

        return lifeInsurances;
    }

    @Override
    public List<LifeInsurance> findAll() {
        List<LifeInsurance> lifeInsurances = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return lifeInsurances;
            }

            ps = con.prepareStatement(SELECT_ALL_SQL_QUERY);
            rs = ps.executeQuery();

            System.out.println("retrieveLifeInsurances => " + ps.toString());

            while (rs.next()) {
                LifeInsurance lifeInsurance = new LifeInsurance();

                lifeInsurance.setId(rs.getInt("id"));
                lifeInsurance.setMedicalExpenses(rs.getFloat("medicalExpenses"));
                lifeInsurance.setCompanies_id(rs.getInt("[companies.id]"));
                lifeInsurance.setDeathCapital(rs.getFloat("deathCapital"));
                lifeInsurance.setBuybackValue(rs.getFloat("buybackValue"));
                lifeInsurance.setUser_id(rs.getInt("[user.id]"));
                lifeInsurance.setContractTime(rs.getInt("contractTime"));

                lifeInsurances.add(lifeInsurance);
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

        return lifeInsurances;
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

            System.out.println( "deleteLifeInsurance => " + ps.toString() );
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
    public LifeInsurance save(LifeInsurance E) {
        LifeInsurance lifeInsurance = findById(E.getId());

        PreparedStatement ps = null;

        if (lifeInsurance != null) {
            try {
                if (con == null) {
                    System.out.println("Error getting the connection. Please check if the DB server is running");
                    return null;
                }

                ps = con.prepareStatement(UPDATE_SQL_QUERY);
                ps.setFloat(1, E.getMedicalExpenses());
                ps.setInt(2, E.getCompanies_id());
                ps.setFloat(3, E.getDeathCapital());
                ps.setFloat(4, E.getBuybackValue());
                ps.setInt(5, E.getUser_id());
                ps.setInt(6, E.getContractTime());
                ps.setInt(7, E.getId());
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
                ps.setFloat(2, E.getMedicalExpenses());
                ps.setInt(3, E.getCompanies_id());
                ps.setFloat(4, E.getDeathCapital());
                ps.setFloat(5, E.getBuybackValue());
                ps.setInt(6, E.getUser_id());
                ps.setInt(7, E.getContractTime());
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
