/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ostoskassi.Tilaus;

import Ostoskassi.Ostoskassi.database.GetPostGreSQLConnection;
import Ostoskassi.Ostoskassi.database.ResultSetToJSON;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONArray;
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
@RequestMapping("/tilaus")
public class TilausController {

    /**
     *
     * Palauttaa Aterian JSON-muodossa perustuen aterian ID:seen.
     *
     * @param id Tietyn Aterian ID
     */
    @RequestMapping(value = "/{email:.+}", method = RequestMethod.GET)
    public String getTilauksetByEmail(@PathVariable String email) throws SQLException, URISyntaxException {

        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resset = statement.executeQuery("SELECT * FROM tilaus where tilaaja_id=(SELECT id FROM kayttaja WHERE email='" + email + "')");
        connection.close();

        ResultSetToJSON jsonConverter = new ResultSetToJSON();
        JSONArray json = jsonConverter.convert(resset);

        return json.toString();

    }

    /**
     *
     * Palauttaa Aterian JSON-muodossa perustuen aterian ID:seen.
     *
     * @param id Tietyn Aterian ID
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getTilaukset() throws SQLException, URISyntaxException {

        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resset = statement.executeQuery("SELECT * FROM tilaus");
        connection.close();

        ResultSetToJSON jsonConverter = new ResultSetToJSON();
        JSONArray json = jsonConverter.convert(resset);

        return json.toString();

    }

    @RequestMapping(method = RequestMethod.POST)
    public String postTilaus(@RequestParam String email, @RequestParam int hinta,
            @RequestParam int lentoID, @RequestParam int ateriaID) throws URISyntaxException, SQLException {

        String statement = "INSERT INTO tilaus(tilaaja_id, toimitettu, ateria_id, yhteishinta, lentoid, added) VALUES ((SELECT id FROM kayttaja WHERE email='" + email + "'), false, " + ateriaID + " ," + hinta + ", " + lentoID + ", now());";
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement SQLstatement = connection.createStatement();
        boolean done = SQLstatement.execute(statement);
        connection.close();
        return statement;
    }

    @RequestMapping(value = "/{email:.+}/last", method = RequestMethod.GET)
    public String getLastTilausByEmail(@PathVariable String email) throws SQLException, URISyntaxException {

        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resset = statement.executeQuery("(SELECT * FROM tilaus where tilaaja_id=(SELECT id FROM kayttaja WHERE email='" + email + "' ORDER BY id DESC LIMIT 1))");
        connection.close();

        ResultSetToJSON jsonConverter = new ResultSetToJSON();
        JSONArray json = jsonConverter.convert(resset);

        return json.get(0).toString();

    }

    /**
     *
     * Poistaa tietyn aterian
     *
     * @param id Tietyn Aterian ID
     */
    @RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
    public String deleteTilaus(@PathVariable int Id) throws URISyntaxException, SQLException {
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM tilaustiedot WHERE tilausid IN (SELECT id FROM Tilaus WHERE id=" + Id + ");");
        statement.execute("DELETE FROM Tilaus where id = " + Id);
        connection.close();
        return "deleted";
    }

    /**
     *
     * Poistaa tietyn aterian
     *
     * @param id Tietyn Aterian ID
     */
    @RequestMapping(value = "/{Id}/toimita", method = RequestMethod.POST)
    public String toimitaTilaus(@PathVariable int Id) throws URISyntaxException, SQLException {
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("UPDATE Tilaus SET toimitettu=true WHERE id=" + Id);
        connection.close();
        return "Tilaus toimitettu";
    }

}
