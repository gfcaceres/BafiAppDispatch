package pe.com.nextel.ejb.tde; 

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;


public interface SEJBRequestRemoteHome extends EJBHome {
    public SEJBRequestRemote create() throws RemoteException, CreateException;
}