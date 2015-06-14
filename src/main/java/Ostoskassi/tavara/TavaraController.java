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
 * Tavara luokan controlleri
 *
 * @author Jesse
 */
@RestController
@RequestMapping("/tavara")
public class TavaraController {

    
    /**
     *
     * Palauttaa tietyn Tavaran JSON-muodossa
     *
     * @param id Tavaran id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getTavara(@PathVariable int id) throws SQLException, URISyntaxException {

        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resset = statement.executeQuery("SELECT * FROM Tavara where ID=" + id);
        ResultSetToJSON jsonConverter = new ResultSetToJSON();
        JSONArray json = jsonConverter.convert(resset);
        connection.close();

        return json.get(0).toString();
    }

    /**
     *
     * Palauttaa kaikki Tavarat JSON-muodossa
     *
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getTavarat() throws SQLException, URISyntaxException {

        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resset = statement.executeQuery("SELECT * FROM Tavara");
        ResultSetToJSON jsonConverter = new ResultSetToJSON();
        JSONArray jsonArray = jsonConverter.convert(resset);
        connection.close();

        return jsonArray.toString();
    }

    /**
     *
     * Poistaa tietyn Tavaran
     *
     * @param id Tietyn Tavaran ID
     */
    @RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
    public String deleteTavara(@PathVariable int Id) throws URISyntaxException, SQLException {
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM Tavara WHERE id=" + Id);
        connection.close();
        return "deleted";
    }

    /**
     *
     * Päivitttää tietyn tuotteen tietoja.
     *
     * @param id Tietyn Tuotteen ID
     * @param kuvaus Tuotteen uusi kuvaus
     * @param hinta Tuotteen uusi hinta
     * @param varastossa Tuotteen uusi varastosaldo
     *
     */
    @RequestMapping(value = "/{Id}", method = RequestMethod.POST)
    public String updateTavara(@PathVariable int Id, @RequestParam String kuvaus, @RequestParam int hinta, @RequestParam int varastossa) throws URISyntaxException, SQLException {
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("UPDATE Tavara  SET kuvaus='" + kuvaus + "', varastossa=" + varastossa + ", hinta=" + hinta + " WHERE id=" + Id);
        connection.close();
        //System.out.println(kuvaus);
        return "updated";
    }

    /**
     *
     * Lisää uuden Tavaran tietokantaan
     *
     * @param valmistaja_id Tuotteen valmistajan id
     * @param nimi Tuotteen nimi
     * @param hinta Tuotteen hinta
     * @param saatavilla Onko tuote saatavilla
     * @param varastossa Monta kappaletta tuotetta on varastossa
     * @param kuvaus Tuotteen kuvaus
     *
     */
    @RequestMapping(method = RequestMethod.POST)
    public String postTavara(@RequestParam String valmistaja_id, @RequestParam String nimi, @RequestParam String hinta,
            @RequestParam String saatavilla, @RequestParam String varastossa, @RequestParam String kuvaus) throws URISyntaxException, SQLException {
        String t = "";

        //params.get
        //t=t+params.get("name")+params.get("hinta");
        String statement = "INSERT INTO Tavara (valmistaja_id, nimi, hinta, saatavilla, varastossa, kuvaus, julkaistu,"
                + " added) VALUES (" + valmistaja_id + ", '" + nimi + "', " + hinta + ", true, 45, '" + kuvaus + "', now(), now())";
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement SQLstatement = connection.createStatement();
        boolean done = SQLstatement.execute(statement);
        connection.close();
        return statement;
    }
}
