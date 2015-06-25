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
@RequestMapping("/tilaustiedot")
public class TilauksenTiedotController {

    /**
     *
     * Postaa uuden Tilaustieto rivin Tilaustieto tietokantaan.
     *
     * @param email käyttäjän email jonka perusteella haetaan oikea tilaus
     * @param kpl monta kappaletta tuotetta on
     * @param tavaraID Tilatun tavaran ID
     */
    @RequestMapping(method = RequestMethod.POST)
    public String postTilauksenTiedot(@RequestParam String email, @RequestParam int kpl,
            @RequestParam int tavaraID) throws URISyntaxException, SQLException {

        String statement = "INSERT INTO tilaustiedot( tilausid, tavaraid, kpl) VALUES ((SELECT id FROM tilaus where tilaaja_id=(SELECT id FROM kayttaja WHERE email='" + email + "') ORDER BY id DESC LIMIT 1), " + tavaraID + ", " + kpl + ");";
        String paivitaKPLMaara = "UPDATE Tavara SET varastossa = varastossa - " + kpl + " where id=" + tavaraID;
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement SQLstatement = connection.createStatement();
        SQLstatement.execute(statement);
        SQLstatement.execute(paivitaKPLMaara);
        connection.close();
        return statement;
    }

    /**
     *
     * Palauttaa tiettyyn tilaukseen liittyvät välitaulut (tilaustiet).
     *
     * @param id Tietyn Tilauksen ID
     * @return Palauttaa JSON-muodossa tilauksen tiedot
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getTilaustiedotByID(@PathVariable int id) throws SQLException, URISyntaxException {

        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resset = statement.executeQuery("SELECT * FROM Tilaustiedot where tilausid=" + id);
        connection.close();

        ResultSetToJSON jsonConverter = new ResultSetToJSON();
        JSONArray json = jsonConverter.convert(resset);

        return json.toString();

    }

    /**
     *
     * Poistaa tietyn tilaustiedon
     *
     * @param id Tietyn Tilaustiedon ID
     */
    @RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
    public String deleteTilaus(@PathVariable int Id) throws URISyntaxException, SQLException {
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM tilaustiedot WHERE id=" + Id);
        connection.close();
        return "deleted";
    }

    /**
     *
     * Poistaa tietyn tilaustiedon
     *
     * @param id Tietyn Tilaustiedon ID
     */
    @RequestMapping(value = "/{Id}/vahenna", method = RequestMethod.POST)
    public String updateTilaustietoVahenna(@PathVariable int Id) throws URISyntaxException, SQLException {
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("UPDATE tilaustiedot SET kpl = kpl - 1 WHERE id=" + Id);
        statement.execute("UPDATE Tavara SET varastossa = varastossa - 1 where id= (SELECT tavaraID from tilaustiedot WHERE id=" + Id + ")");
        connection.close();
        return "deleted";
    }

    /**
     *
     * Poistaa tietyn tilaustiedon
     *
     * @param id Tietyn Tilaustiedon ID
     */
    @RequestMapping(value = "/{Id}/lisaa", method = RequestMethod.POST)
    public String updateTilaustietoLisaa(@PathVariable int Id) throws URISyntaxException, SQLException {
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("UPDATE tilaustiedot SET kpl = kpl + 1 WHERE id=" + Id);
        statement.execute("UPDATE Tavara SET varastossa = varastossa + 1 where id= (SELECT tavaraID from tilaustiedot WHERE id=" + Id + ")");
        connection.close();
        return "deleted";
    }

}
