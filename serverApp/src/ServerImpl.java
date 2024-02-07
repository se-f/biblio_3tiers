import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    ResultSet result;
    Statement st;
    conn.Connec cn = new conn.Connec();

    protected ServerImpl() throws RemoteException {
        super();
        try {
            st = cn.connecion().createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }


    public String addUser(String id, String nom) throws RemoteException {

        String query = "insert into abonne values('" + id + "','" + nom + "')";
        try {
            st.executeUpdate(query);
            return "USER ADDED SUCCESSFULLY!";

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }


    }

    public String deleteUserById(String id) throws RemoteException {
        String query = "delete from abonne where idab='" + id + "'";
        try {
            st.executeUpdate(query);
            return "SUPPRESSION REUSSITE";
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public String getUserById(String id) throws RemoteException {
        String query = "select * from abonne where idab='" + id + "'";
        try {
            result = st.executeQuery(query);
            if (result.next()) {
                return result.getString("nomab");
            } else
                return null;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public String updateUser(String id, String nom) throws RemoteException {
        String query = "update abonne set nomab='" + nom + "' where idab='" + id + "'";
        try {
            st.executeUpdate(query);
            return "UPDATE SUCCESS";

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public List<List<String>> getAllString() throws RemoteException {
        try {
            result = st.executeQuery("select * from abonne");
            return resultSetToTable(result);
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

    }


    private static List<List<String>> resultSetToTable(ResultSet resultSet) {
        List<List<String>> tableOfStrings = new ArrayList<>();

        try {
            while (resultSet.next()) {
                List<String> row = new ArrayList<>();
                row.add(resultSet.getString("idab"));
                row.add(resultSet.getString("nomab"));
                tableOfStrings.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tableOfStrings;
    }

    public static void main(String[] args) {
        try {
            ServerImpl server = new ServerImpl();

            // Create and export the RMI registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("RMIServer", server);

            System.out.println("Server is running...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
