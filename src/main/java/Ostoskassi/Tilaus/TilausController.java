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

        return json.get(0).toString();

    }

    @RequestMapping(method = RequestMethod.POST)
    public String postAteria(@RequestParam String email, @RequestParam int hinta,
            @RequestParam int lentoID, @RequestParam int ateriaID) throws URISyntaxException, SQLException {

        String statement = "INSERT INTO tilaus(tilaaja_id, toimitettu, ateria_id, yhteishinta, lentoid, added) VALUES ((SELECT id FROM kayttaja WHERE email='" + email + "'), false, " +ateriaID+" ," + hinta + ", " + lentoID + ", now());";
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement SQLstatement = connection.createStatement();
        boolean done = SQLstatement.execute(statement);
        connection.close();
        return statement;
    }

}
