package database;

import java.sql.*;

public class DBExample {


    public Connection getConn() {
        return conn;
    }

    Connection conn = null;

    public DBExample() {
        connect();
    }

    private Connection connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:sqlite.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ResultSet selectAll() throws SQLException {
        String sql = "SELECT * FROM demo";
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);

    }

}
