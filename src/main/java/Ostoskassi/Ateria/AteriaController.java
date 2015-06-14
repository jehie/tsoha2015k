/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ostoskassi.Ateria;

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
@RequestMapping("/ateria")
public class AteriaController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getAteria(@PathVariable int id) throws SQLException, URISyntaxException {

        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resset = statement.executeQuery("SELECT * FROM Ateria where ID=" + id);
        ResultSetToJSON jsonConverter = new ResultSetToJSON();
        JSONArray json = jsonConverter.convert(resset);
        connection.close();

        return json.get(0).toString();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAteriat() throws SQLException, URISyntaxException {

        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resset = statement.executeQuery("SELECT * FROM Ateria");
        ResultSetToJSON jsonConverter = new ResultSetToJSON();
        JSONArray jsonArray = jsonConverter.convert(resset);
        connection.close();

        return jsonArray.toString();
    }

    @RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
    public String deleteTAteria(@PathVariable int Id) throws URISyntaxException, SQLException {
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM Ateria WHERE id=" + Id);
        connection.close();
        return "deleted";
    }

    @RequestMapping(value = "/{Id}", method = RequestMethod.POST)
    public String updateAteria(@PathVariable int Id, @RequestParam String kuvaus, @RequestParam int hinta) throws URISyntaxException, SQLException {
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        //statement.execute("UPDATE Ateria SET kuvaus='" + kuvaus + ", hinta=" + hinta + " WHERE id=" + Id);
        statement.execute("UPDATE Ateria SET kuvaus='"+kuvaus+"', hinta="+hinta+" WHERE id=" + Id);
        connection.close();
        //System.out.println(kuvaus);
        return "updated";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postAteria(@RequestParam String Aterianvalmistaja_id, @RequestParam String nimi, @RequestParam String hinta,
            @RequestParam String saatavilla, @RequestParam String kuvaus) throws URISyntaxException, SQLException {
        String t = "";

        //params.get
        //t=t+params.get("name")+params.get("hinta");
        String statement = "INSERT INTO Ateria (Aterianvalmistaja_id, nimi, hinta, saatavilla, kuvaus, julkaistu,"
                + " added) VALUES (" + Aterianvalmistaja_id + ", '" + nimi + "', " + hinta + ", true, '" + kuvaus + "', now(), now())";
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement SQLstatement = connection.createStatement();
        boolean done = SQLstatement.execute(statement);
        connection.close();
        return statement;
    }

}
