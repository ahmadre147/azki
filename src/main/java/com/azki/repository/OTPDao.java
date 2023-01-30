package com.azki.repository;

import com.azki.driver.JDBCHelper;
import com.azki.model.OTP;
import com.azki.model.OTP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OTPDao implements Repository<OTP, Integer> {
    public static final String INSERT_SQL_QUERY     = "INSERT INTO [OTP]([id],[code],[user.id]) VALUES(?,?,?)";
    public static final String UPDATE_SQL_QUERY     = "UPDATE [OTP] SET [code]=?,[user.id]=? WHERE [id]=?";
    public static final String SELECT_SQL_QUERY     = "SELECT * FROM [OTP] WHERE [id]=?";
    public static final String SELECT_ALL_SQL_QUERY = "SELECT * FROM [OTP]";
    public static final String DELETE_SQL_QUERY     = "DELETE FROM [OTP] WHERE [id]=?";

    public final Connection con;

    public OTPDao() throws SQLException {
        con = JDBCHelper.getConnection();
    }

    @Override
    public OTP findById(Integer id) {

        OTP otp = new OTP();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return otp;
            }

            ps = con.prepareStatement(SELECT_SQL_QUERY);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.isBeforeFirst()){
                System.out.println( "No otp by id = " + id + " is found!");
                return null;
            }

            System.out.println( "retrieveOTP => " + ps.toString() );

            while (rs.next()){
                otp.setId(rs.getInt("id"));
                otp.setCode(rs.getInt("code"));
                otp.setUser_id(rs.getInt("[user.id]"));
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

        return otp;
    }

    @Override
    public List<OTP> findByIDs(Collection<Integer> ids) {

        List<OTP> otps = new ArrayList<>();

        for (Integer id : ids){
            otps.add(findById(id));
        }

        return otps;
    }

    @Override
    public List<OTP> findAll() {

        List<OTP> otps = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return otps;
            }

            ps = con.prepareStatement(SELECT_ALL_SQL_QUERY);
            rs = ps.executeQuery();

            System.out.println("retrieveOTPs => " + ps.toString());

            while (rs.next()) {
                OTP otp = new OTP();

                otp.setId(rs.getInt("id"));
                otp.setCode(rs.getInt("code"));
                otp.setUser_id(rs.getInt("[user.id]"));

                otps.add(otp);
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

        return otps;
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

            System.out.println( "deleteOTP => " + ps.toString() );
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
    public OTP save(OTP E) {

        OTP otp = findById(E.getId());

        PreparedStatement ps = null;

        if (otp != null) {
            try {
                if (con == null) {
                    System.out.println("Error getting the connection. Please check if the DB server is running");
                    return null;
                }
                ps = con.prepareStatement(UPDATE_SQL_QUERY);
                ps.setInt(1, E.getCode());
                ps.setInt(2, E.getUser_id());
                ps.setInt(3, E.getId());
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
                ps.setInt(2, E.getCode());
                ps.setInt(3, E.getUser_id());
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
