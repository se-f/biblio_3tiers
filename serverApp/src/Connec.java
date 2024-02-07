package conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connec {
    Connection con;

    public Connec() {
        System.out.println("Trying to connect...");
        try {
            // Make sure to change the URL to the DB as well as credentials
            con = DriverManager.getConnection("jdbc:mysql://localhost:****/******", "****", "*********");
            System.out.println("Connected!!");
        } catch (Exception e) {
            System.out.println("Failed to connect");
            System.out.println(e);

        }

    }

    public Connection connecion() {
        return con;
    }
}
