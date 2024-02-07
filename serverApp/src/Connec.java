package conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connec {
    Connection con;

    public Connec() {
        System.out.println("trying to connect...");
        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3308/biblio", "root", "");
            System.out.println("connected!!");
        } catch (Exception e) {
            System.out.println("connected not yet!!");
            System.out.println(e);

        }

    }

    public Connection connecion() {
        return con;
    }
}