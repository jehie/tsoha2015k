package Ostoskassi.Lento;

import Ostoskassi.Ostoskassi.database.GetPostGreSQLConnection;
import Ostoskassi.Ostoskassi.database.ResultSetToJSON;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Luokka lentojen hallintaan
 *
 * @author Jesse
 */
@RestController
@RequestMapping("/lento")
public class LentoController {

    /**
     *
     * Palauttaa kaikki tietokannasta löytyvät lennot JSON-muodossa. Lennot
     * tietokantaan "tulevat" muualta, joten niitä ei tässä muokata.
     *
     * @return Lennot JSON
     * @throws java.sql.SQLException
     * @throws java.net.URISyntaxException
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getLennot() throws SQLException, URISyntaxException {

        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resset = statement.executeQuery("SELECT * FROM Lento");
        ResultSetToJSON jsonConverter = new ResultSetToJSON();
        JSONArray jsonArray = jsonConverter.convert(resset);
        connection.close();

        return jsonArray.toString();
    }

}
