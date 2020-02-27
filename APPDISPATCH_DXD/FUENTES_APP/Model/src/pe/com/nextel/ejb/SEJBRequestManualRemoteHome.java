package pe.com.nextel.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;


public interface SEJBRequestManualRemoteHome extends EJBHome 
{
  SEJBRequestManualRemote create() throws RemoteException, CreateException;
}