package conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connec {
    Connection con;

    public Connec() {
        System.out.println("Trying to connect...");
        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3308/biblio", "root", "");
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
