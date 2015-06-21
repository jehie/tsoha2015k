package Ostoskassi.TavaranValmistaja;

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
 * Tavaravalmistaja luokan controlleri
 *
 * @author Jesse
 */
@RestController
@RequestMapping("/tavaranvalmistaja")
public class TavaranValmistajaController {

    /**
     *
     * Palauttaa kaikki tavaranvalmistajat tietokannasta
     *
     * @return Tavaranvalmistajat JSON muodossa
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getTavaranValmistajat() throws SQLException, URISyntaxException {

        Connection connection = GetPostGreSQLConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resset = statement.executeQuery("SELECT * FROM Tavaranvalmistaja");
        ResultSetToJSON jsonConverter = new ResultSetToJSON();
        JSONArray jsonArray = jsonConverter.convert(resset);
        connection.close();

        return jsonArray.toString();
    }

}
