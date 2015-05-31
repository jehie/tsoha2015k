package Ostoskassi;

import Ostoskassi.Ostoskassi.database.GetPostGreSQLConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OstoskassiApplication {

    public static void main(String[] args) {
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        System.setProperty("server.port", webPort);
        SpringApplication.run(OstoskassiApplication.class, args);
    }
}
