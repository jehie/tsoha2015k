/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ostoskassi.Ostoskassi.database;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Jesse
 */
public class GetPostGreSQLConnection {
//
//    public Connection getConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("org.postgresql.Driver");
//
//        String url = "jdbc:postgresql://ec2-54-204-35-248.compute-1.amazonaws.com/dc7900e88qvbop";
//        Properties props = new Properties();
//        //props.setProperty("ssl", "true");
//        props.setProperty("user", "rmalctxvdhbfnz");
//        props.setProperty("password", "zD4_RBij1e-9rpoF8wF7kXOXNz");
//        Connection conn = DriverManager.getConnection(url, props);
//        return conn;
//
//    }
    
    public static Connection getConnection() throws URISyntaxException, SQLException {
    URI dbUri = new URI(System.getenv("DATABASE_URL"));

    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

    return DriverManager.getConnection(dbUrl, username, password);
}
    
    
}
