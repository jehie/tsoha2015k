/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ostoskassi.tavara;

import Ostoskassi.Ostoskassi.database.GetPostGreSQLConnection;
import Ostoskassi.Ostoskassi.database.ResultSetToJSON;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jesse
 */
@RestController
@RequestMapping("/tavara")
public class TavaraController {

  
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String getTavara(@PathVariable int id) throws SQLException, URISyntaxException{
        
         Connection connection = GetPostGreSQLConnection.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resset = statement.executeQuery("SELECT * FROM Tavara where ID="+id);
        ResultSetToJSON jsonConverter = new ResultSetToJSON();
        JSONArray json = jsonConverter.convert(resset);
        connection.close();
        
        
        return json.get(0).toString();
    }
    
        @RequestMapping(method = RequestMethod.GET)
    public String getTavarat() throws SQLException, URISyntaxException{
        
         Connection connection = GetPostGreSQLConnection.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resset = statement.executeQuery("SELECT * FROM Tavara");
        ResultSetToJSON jsonConverter = new ResultSetToJSON();
        JSONArray jsonArray = jsonConverter.convert(resset);
        connection.close();
        
        return jsonArray.toString();
    }
    

    @RequestMapping(method = RequestMethod.POST)
    public String postTavara(@RequestParam String valmistaja_id, @RequestParam String nimi, @RequestParam String hinta, 
            @RequestParam String saatavilla, @RequestParam String varastossa, @RequestParam String kuvaus) throws URISyntaxException, SQLException {
        String t ="";
        
        //params.get
        //t=t+params.get("name")+params.get("hinta");
        
        String statement = "INSERT INTO Tavara (valmistaja_id, nimi, hinta, saatavilla, varastossa, kuvaus, julkaistu,"
                + " added) VALUES ("+valmistaja_id+", '" +nimi+"', "+hinta+", true, 45, '"+kuvaus+"', now(), now())";
        Connection connection = GetPostGreSQLConnection.getConnection();
         Statement SQLstatement = connection.createStatement();
         boolean done = SQLstatement.execute(statement);
        connection.close();
        return statement;
    }
}
