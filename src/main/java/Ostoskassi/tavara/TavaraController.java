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
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    //

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

//    @RequestMapping("/tavara")
//    public Tavara greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new Tavara(counter.incrementAndGet(),
//                String.format(template, name));
//    }
    
    @RequestMapping(value="1", method = RequestMethod.GET)
    public String getTavara() throws SQLException, URISyntaxException{
        
         Connection connection = GetPostGreSQLConnection.getConnection();
         Statement statement = connection.createStatement();
         //ResultSet resset = statement.executeQuery("SELECT * FROM Tavara where ID="+id);
         ResultSet resset = statement.executeQuery("SELECT * FROM Tavara where ID=1");
        ResultSetToJSON rs = new ResultSetToJSON();
        JSONArray json = rs.convert(resset);
        
        
        
        return (String) json.get(0);
    }
    

    @RequestMapping(method = RequestMethod.POST)
    public String postTavara(@RequestParam MultiValueMap<String, String> params) {
        String t ="";
        
        //params.get
        //t=t+params.get("name")+params.get("hinta");
        
        return t;
    }
}
