package com.azki.repository;

import com.azki.driver.JDBCHelper;
import com.azki.model.Reminder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReminderDao implements Repository<Reminder, Integer>{
    public static final String INSERT_SQL_QUERY     = "INSERT INTO [Reminders]([id],[type],[time],[remindPeriod],[user.id]) VALUES(?,?,?,?,?)";
    public static final String UPDATE_SQL_QUERY     = "UPDATE [Reminders] SET [id]=?,[type]=?,[time]=?,[remindPeriod]=?,[user.id]=? WHERE [id]=?";
    public static final String SELECT_SQL_QUERY     = "SELECT * FROM [Reminders] WHERE [id]=?";
    public static final String SELECT_ALL_SQL_QUERY = "SELECT * FROM [Reminders]";
    public static final String DELETE_SQL_QUERY     = "DELETE FROM [Reminders] WHERE [id]=?";

    public final Connection con;

    public ReminderDao() throws SQLException {
        con = JDBCHelper.getConnection();
    }

    @Override
    public Reminder findById(Integer id) {
        Reminder reminder = new Reminder();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return reminder;
            }

            ps = con.prepareStatement(SELECT_SQL_QUERY);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.isBeforeFirst()){
                System.out.println( "No reminder by id = " + id + " is found!");
                return null;
            }

            System.out.println( "retrieveReminder => " + ps.toString() );

            while (rs.next()){
                reminder.setId(rs.getInt("id"));
                reminder.setType(rs.getString("type"));
                reminder.setTime(rs.getDate("time"));
                reminder.setRemindPeriod(rs.getInt("remindPeriod"));
                reminder.setUser_id(rs.getInt("[user.id]"));
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

        return reminder;
    }

    @Override
    public List<Reminder> findByIDs(Collection<Integer> ids) {
        List<Reminder> reminders = new ArrayList<>();

        for (Integer id : ids){
            reminders.add(findById(id));
        }

        return reminders;
    }

    @Override
    public List<Reminder> findAll() {
        List<Reminder> reminders = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return reminders;
            }

            ps = con.prepareStatement(SELECT_ALL_SQL_QUERY);
            rs = ps.executeQuery();

            System.out.println("retrieveReminders => " + ps.toString());

            while (rs.next()) {
                Reminder reminder = new Reminder();

                reminder.setId(rs.getInt("id"));
                reminder.setType(rs.getString("type"));
                reminder.setTime(rs.getDate("time"));
                reminder.setRemindPeriod(rs.getInt("remindPeriod"));
                reminder.setUser_id(rs.getInt("[user.id]"));

                reminders.add(reminder);
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

        return reminders;
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

            System.out.println( "deleteReminder => " + ps.toString() );
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
    public Reminder save(Reminder E) {
        Reminder reminder = findById(E.getId());

        PreparedStatement ps = null;

        if (reminder != null) {
            try {
                if (con == null) {
                    System.out.println("Error getting the connection. Please check if the DB server is running");
                    return null;
                }
                ps = con.prepareStatement(UPDATE_SQL_QUERY);

                ps.setString(1, E.getType());
                ps.setDate(2, E.getTime());
                ps.setInt(3, E.getRemindPeriod());
                ps.setInt(4, E.getUser_id());
                ps.setInt(5, E.getId());
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
                ps.setString(2, E.getType());
                ps.setDate(3, E.getTime());
                ps.setInt(4, E.getRemindPeriod());
                ps.setInt(5, E.getUser_id());

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
