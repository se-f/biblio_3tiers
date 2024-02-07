import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.List;

public interface ServerInterface extends Remote {

    public String addUser(String id, String nom) throws RemoteException;

    public String deleteUserById(String id) throws RemoteException;

    public String getUserById(String id) throws RemoteException;

    public String updateUser(String id, String nom) throws RemoteException;
    
    public List<List<String>> getAllString() throws RemoteException;
}
