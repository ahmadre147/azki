package com.azki.repository;

import com.azki.driver.JDBCHelper;
import com.azki.model.Companies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompaniesDao implements Repository<Companies, Integer> {
    public static final String INSERT_SQL_QUERY     = "INSERT INTO [Companies]([id],[plan],[name],[logo]) VALUES(?,?,?,?)";
    public static final String UPDATE_SQL_QUERY     = "UPDATE [Companies] SET [plan]=?,[name]=?,[logo]=? WHERE id=?";
    public static final String SELECT_SQL_QUERY     = "SELECT * FROM [Companies] WHERE [id]=?";
    public static final String SELECT_ALL_SQL_QUERY = "SELECT * FROM [Companies]";
    public static final String DELETE_SQL_QUERY     = "DELETE FROM [Companies] WHERE [id]=?";

    public final Connection con;

    public CompaniesDao() throws SQLException {
        con = JDBCHelper.getConnection();
    }

    @Override
    public Companies findById(Integer id) {
        Companies company = new Companies();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return company;
            }

            ps = con.prepareStatement(SELECT_SQL_QUERY);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.isBeforeFirst()){
                System.out.println( "No company by id = " + id + " is found!");
                return null;
            }

            System.out.println( "retrieveCompany => " + ps.toString() );

            while (rs.next()){
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                company.setPlan(rs.getString("plan"));
                company.setLogo(rs.getString("logo"));
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

        return company;
    }

    @Override
    public List<Companies> findByIDs(Collection<Integer> ids) {
        List<Companies> companies = new ArrayList<>();

        for (Integer id : ids){
            companies.add(findById(id));
        }

        return companies;
    }

    @Override
    public List<Companies> findAll() {
        List<Companies> companies = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return companies;
            }

            ps = con.prepareStatement(SELECT_ALL_SQL_QUERY);
            rs = ps.executeQuery();

            System.out.println("retrieveCompanies => " + ps.toString());

            while (rs.next()) {
                Companies company = new Companies();

                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                company.setPlan(rs.getString("plan"));
                company.setLogo(rs.getString("logo"));

                companies.add(company);
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

        return companies;
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

            System.out.println( "deleteCompany => " + ps.toString() );
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
    public Companies save(Companies E) {
        Companies company = findById(E.getId());

        PreparedStatement ps = null;

        if (company != null) {
            try {
                if (con == null) {
                    System.out.println("Error getting the connection. Please check if the DB server is running");
                    return null;
                }
                ps = con.prepareStatement(UPDATE_SQL_QUERY);
                ps.setString(1, E.getPlan());
                ps.setString(2, E.getName());
                ps.setString(3, E.getLogo());
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
                ps.setString(2, E.getPlan());
                ps.setString(3, E.getName());
                ps.setString(4, E.getLogo());
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
