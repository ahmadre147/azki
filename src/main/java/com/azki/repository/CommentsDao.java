package com.azki.repository;

import com.azki.driver.JDBCHelper;
import com.azki.model.Comments;
import com.azki.model.Comments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommentsDao implements Repository<Comments, Integer> {
    public static final String INSERT_SQL_QUERY     = "INSERT INTO [Comments]([id],[premium_rate],[damage_rate],[coverage_rate],[support_rate],[text],[typeOfInsurance],[companies_id]) VALUES(?,?,?,?,?,?,?,?)";
    public static final String UPDATE_SQL_QUERY     = "UPDATE [Comments] SET [premium_rate]=?,[damage_rate]=?,[coverage_rate]=?,[support_rate]=?,[text]=?,[typeOfInsurance]=?,[companies_id]=? WHERE id=?";
    public static final String SELECT_SQL_QUERY     = "SELECT * FROM [Comments] WHERE [id]=?";
    public static final String SELECT_ALL_SQL_QUERY = "SELECT * FROM [Comments]";
    public static final String DELETE_SQL_QUERY     = "DELETE FROM [Comments] WHERE [id]=?";

    public final Connection con;

    public CommentsDao() throws SQLException {
        con = JDBCHelper.getConnection();
    }

    @Override
    public Comments findById(Integer id) {
        Comments comments = new Comments();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return comments;
            }

            ps = con.prepareStatement(SELECT_SQL_QUERY);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.isBeforeFirst()){
                System.out.println( "No comments by id = " + id + " is found!");
                return null;
            }

            System.out.println( "retrieveComments => " + ps.toString() );

            while (rs.next()){
                comments.setId(rs.getInt("id"));
                comments.setPremium_rate(rs.getInt("premium_rate"));
                comments.setDamage_rate(rs.getInt("damage_rate"));
                comments.setCoverage_rate(rs.getInt("coverage_rate"));
                comments.setSupport_rate(rs.getInt("support_rate"));
                comments.setText(rs.getString("text"));
                comments.setTypeOfInsurance(rs.getInt("typeOfInsurance"));
                comments.setCompanies_id(rs.getInt("[companies.id]"));
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

        return comments;
    }

    @Override
    public List<Comments> findByIDs(Collection<Integer> ids) {
        List<Comments> commentss = new ArrayList<>();

        for (Integer id : ids){
            commentss.add(findById(id));
        }

        return commentss;
    }

    @Override
    public List<Comments> findAll() {
        List<Comments> commentss = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return commentss;
            }

            ps = con.prepareStatement(SELECT_ALL_SQL_QUERY);
            rs = ps.executeQuery();

            System.out.println("retrieveCommentss => " + ps.toString());

            while (rs.next()) {
                Comments comments = new Comments();

                comments.setId(rs.getInt("id"));
                comments.setPremium_rate(rs.getInt("premium_rate"));
                comments.setDamage_rate(rs.getInt("damage_rate"));
                comments.setCoverage_rate(rs.getInt("coverage_rate"));
                comments.setSupport_rate(rs.getInt("support_rate"));
                comments.setText(rs.getString("text"));
                comments.setTypeOfInsurance(rs.getInt("typeOfInsurance"));
                comments.setCompanies_id(rs.getInt("[companies.id]"));

                commentss.add(comments);
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

        return commentss;
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

            System.out.println( "deleteComments => " + ps.toString() );
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
    public Comments save(Comments E) {
        Comments comments = findById(E.getId());

        PreparedStatement ps = null;

        if (comments != null) {
            try {
                if (con == null) {
                    System.out.println("Error getting the connection. Please check if the DB server is running");
                    return null;
                }
                ps = con.prepareStatement(UPDATE_SQL_QUERY);

                ps.setInt(1, E.getPremium_rate());
                ps.setInt(2, E.getDamage_rate());
                ps.setInt(3, E.getCoverage_rate());
                ps.setInt(4, E.getSupport_rate());
                ps.setString(5, E.getText());
                ps.setInt(6, E.getTypeOfInsurance());
                ps.setInt(7, E.getCompanies_id());
                ps.setInt(8, E.getId());
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
                ps.setInt(2, E.getPremium_rate());
                ps.setInt(3, E.getDamage_rate());
                ps.setInt(4, E.getCoverage_rate());
                ps.setInt(5, E.getSupport_rate());
                ps.setString(6, E.getText());
                ps.setInt(7, E.getTypeOfInsurance());
                ps.setInt(8, E.getCompanies_id());
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
