package Ostoskassi.Ateria;

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
 * Ateria luokan Controlleri
 *
 * @author Jesse
 */
@RestController
@RequestMapping("/ateria")
public class AteriaController {

    /**
     *
     * Palauttaa Aterian JSON-muodossa perustuen aterian ID:seen.
     *
     * @param id Tietyn Aterian ID
     * @return Ateria JSON-muodossa
     * @throws java.sql.SQLException
     * @throws java.net.URISyntaxException
     */
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

    /**
     *
     * Palauttaa kaikki tilatut ateriat JSON-muodossa
     *
     * @return Tilatut Ateriat JSON-muodossa
     * @throws java.sql.SQLException
     * @throws java.net.URISyntaxException
     */
    @RequestMapping(value = "/tilatut",method = RequestMethod.GET)
    public String getTilatutAteriat() throws SQLException, URISyntaxException {

        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resset = statement.executeQuery("SELECT id FROM Ateria WHERE id IN (SELECT ateria_id from Tilaus)");
        ResultSetToJSON jsonConverter = new ResultSetToJSON();
        JSONArray jsonArray = jsonConverter.convert(resset);
        connection.close();

        return jsonArray.toString();
    }

    /**
     *
     * Palauttaa kaikki ateriat JSON-muodossa
     *
     * @return Ateriat JSON-muodossa
     * @throws java.sql.SQLException
     * @throws java.net.URISyntaxException
     */
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

    /**
     *
     * Poistaa tietyn aterian
     *
     * @param Id Tietyn Aterian ID
     * @throws java.net.URISyntaxException
     * @throws java.sql.SQLException
     */
    @RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
    public String deleteTAteria(@PathVariable int Id) throws URISyntaxException, SQLException {
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM Ateria WHERE id=" + Id);
        connection.close();
        return "deleted";
    }

    /**
     *
     * Päivitttää tietyn aterian tietoja.
     *
     * @param Id Tietyn Aterian ID
     * @param kuvaus Aterian uusi kuvaus
     * @param hinta Aterian uusi hinta
     * @param saatavilla Onko ateria saatavilla
     * @throws java.net.URISyntaxException
     * @throws java.sql.SQLException
     *
     */
    @RequestMapping(value = "/{Id}", method = RequestMethod.POST)
    public String updateAteria(@PathVariable int Id, @RequestParam String kuvaus, @RequestParam int hinta, @RequestParam boolean saatavilla) throws URISyntaxException, SQLException {
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        String statementS = "UPDATE ateria SET hinta='" + hinta + "', saatavilla='" + saatavilla + "', kuvaus='" + kuvaus + "' WHERE id='" + Id + "'";
        statement.execute(statementS);
        connection.close();
        return statementS;
    }

    /**
     *
     * Lisää uuden Aterian tietokantaan
     *
     * @param nimi Aterian nimi
     * @param hinta Aterian hinta
     * @param saatavilla Onko ateria saatavilla
     * @param kuvaus Aterian kuvaus
     * @throws java.net.URISyntaxException
     * @throws java.sql.SQLException
     *
     */
    @RequestMapping(method = RequestMethod.POST)
    public String postAteria(@RequestParam String nimi, @RequestParam String hinta,
            @RequestParam String saatavilla, @RequestParam String kuvaus) throws URISyntaxException, SQLException {
        String statement = "INSERT INTO ateria(nimi, hinta, saatavilla, kuvaus, lisatty) VALUES ('" + nimi + "', '" + hinta + "', '" + saatavilla + "', '" + kuvaus + "', now());";
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement SQLstatement = connection.createStatement();
        boolean done = SQLstatement.execute(statement);
        connection.close();
        return statement;
    }

}
