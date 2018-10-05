package com.xinchen.srs.database;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

// connect to Sqlite using JDBC
public class DBConnectionUtil {

    public static Connection getConnection() {
        try {
        	String url = "jdbc:sqlite:/Users/xinchen/registration.db";
            // create a connection to the database
            return DriverManager.getConnection(url);
        	
            // return dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
