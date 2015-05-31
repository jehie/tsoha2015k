/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ostoskassi.Ostoskassi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Jesse
 */
public class GetPostGreSQLConnection {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://localhost/johietan";
        Properties props = new Properties();
        //props.setProperty("ssl", "true");
        Connection conn = DriverManager.getConnection(url, props);
        return conn;

    }
}
