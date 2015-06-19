/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ostoskassi.Lento;

import Ostoskassi.Ostoskassi.database.GetPostGreSQLConnection;
import Ostoskassi.Ostoskassi.database.ResultSetToJSON;
import Ostoskassi.tavara.Tavara;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jesse
 */
@RestController
@RequestMapping("/lento")
public class LentoController {

    /**
     *
     * Palauttaa kaikki ateriat JSON-muodossa
     *
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
