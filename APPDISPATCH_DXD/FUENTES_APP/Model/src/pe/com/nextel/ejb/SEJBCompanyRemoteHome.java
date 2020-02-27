package pe.com.nextel.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;


public interface SEJBCompanyRemoteHome extends EJBHome 
{
  SEJBCompanyRemote create() throws RemoteException, CreateException;
}