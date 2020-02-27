package pe.com.nextel.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;


public interface SEJBItemRemoteHome extends EJBHome 
{
  SEJBItemRemote create() throws RemoteException, CreateException;
}