package Ostoskassi.Kayttaja;

import Ostoskassi.Ostoskassi.database.GetPostGreSQLConnection;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Käyttäjien hallintaan liittyvä Controlleri
 * 
 * @author Jesse
 */
@RestController
@RequestMapping("/kayttaja")
public class KayttajaController {

    /**
     *
     * Lisää käyttäjän tietokantaan
     *
     * @param email Käyttäjän sähköposti
     */
    @RequestMapping(method = RequestMethod.POST)
    public String postKayttaja(@RequestParam String email) throws URISyntaxException, SQLException {

        String statement = "INSERT INTO kayttaja(email) VALUES ('" + email + "');";
        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement SQLstatement = connection.createStatement();
        SQLstatement.execute(statement);
        connection.close();
        return statement;
    }

    /**
     *
     * Tarkistaa onko käyttäjä admin ja palauttaa true jos on ja false jos ei.
     *
     * @param email Käyttäjän sähköposti
     */
    @RequestMapping(value = "/{email:.+}", method = RequestMethod.GET)
    public boolean isUserAdmin(@PathVariable String email) throws SQLException, URISyntaxException {

        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resset = statement.executeQuery("SELECT * FROM Kayttaja where email='" + email + "'");
        connection.close();

        while (resset.next()) {
            boolean isAdmin = resset.getBoolean("admin");
            return isAdmin;
        }
        return false;
    }
}
