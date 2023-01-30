package com.azki.driver;

import java.sql.*;

public class JDBCHelper {
    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=kntu;encrypt=true;trustServerCertificate=true;integratedSecurity=true;";

        return DriverManager.getConnection(url);
    }

    public static void closeConnection(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    public static void closePreparedStatement(PreparedStatement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    public static void closeResultSet(ResultSet rs) throws SQLException
    {
        if (rs != null) {
            rs.close();
        }
    }

}
