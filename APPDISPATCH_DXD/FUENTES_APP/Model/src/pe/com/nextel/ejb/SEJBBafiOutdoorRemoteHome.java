package pe.com.nextel.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface SEJBBafiOutdoorRemoteHome extends EJBHome {
    SEJBBafiOutdoorRemote create() throws RemoteException, CreateException;
}
