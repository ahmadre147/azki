package com.azki.repository;

import com.azki.driver.JDBCHelper;
import com.azki.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDao implements Repository<User, Integer>{
    public static final String INSERT_SQL_QUERY     = "INSERT INTO [Users]([id],[firstName],[lastName],[phoneNumber],[referralCode],[remainingInvitations],[password]) VALUES(?,?,?,?,?,?,?)";
    public static final String UPDATE_SQL_QUERY     = "UPDATE [Users] SET [firstName]=?,[lastName]=?,[phoneNumber]=?,[referralCode]=?,[remainingInvitations]=?,[password]=? WHERE [id]=?";
    public static final String SELECT_SQL_QUERY     = "SELECT * FROM [Users] WHERE [id]=?";
    public static final String SELECT_ALL_SQL_QUERY = "SELECT * FROM [Users]";
    public static final String DELETE_SQL_QUERY     = "DELETE FROM [Users] WHERE [id]=?";

    public final Connection con;

    public UserDao() throws SQLException {
        con = JDBCHelper.getConnection();
    }

    @Override
    public User findById(Integer id) {
        User user = new User();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return user;
            }

            ps = con.prepareStatement(SELECT_SQL_QUERY);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.isBeforeFirst()){
                System.out.println( "No user by id = " + id + " is found!");
                return null;
            }

            System.out.println( "retrieveUser => " + ps.toString() );

            while (rs.next()){
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setReferralCode(rs.getString("referralCode"));
                user.setPassword(rs.getString("password"));
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

        return user;
    }

    @Override
    public List<User> findByIDs(Collection<Integer> ids) {
        List<User> users = new ArrayList<>();

        for (Integer id : ids){
            users.add(findById(id));
        }

        return users;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return users;
            }

            ps = con.prepareStatement(SELECT_ALL_SQL_QUERY);
            rs = ps.executeQuery();

            System.out.println("retrieveUsers => " + ps.toString());

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setReferralCode(rs.getString("referralCode"));
                user.setPassword(rs.getString("password"));

                users.add(user);
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

        return users;
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

            System.out.println( "deleteUser => " + ps.toString() );
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
    public User save(User E) {
        User user = findById(E.getId());

        PreparedStatement ps = null;

        if (user != null) {
            try {
                if (con == null) {
                    System.out.println("Error getting the connection. Please check if the DB server is running");
                    return null;
                }
                ps = con.prepareStatement(UPDATE_SQL_QUERY);
                ps.setString(1, E.getFirstName());
                ps.setString(2, E.getLastName());
                ps.setString(3, E.getPhoneNumber());
                ps.setString(4, E.getReferralCode());
                ps.setString(5, E.getRemainingInvitations());
                ps.setString(6, E.getPassword());
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
                ps.setString(2, E.getFirstName());
                ps.setString(3, E.getLastName());
                ps.setString(4, E.getPhoneNumber());
                ps.setString(5, E.getReferralCode());
                ps.setString(6, E.getRemainingInvitations());
                ps.setString(7, E.getPassword());

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
