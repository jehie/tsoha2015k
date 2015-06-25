package Ostoskassi;

import java.net.URISyntaxException;
import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Luokka Spring-sovelluksen käynnistämiseen
 *
 * @author Jesse
 */
@SpringBootApplication
public class OstoskassiApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, URISyntaxException {
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        System.setProperty("server.port", webPort);
        SpringApplication.run(OstoskassiApplication.class, args);
    }
}
