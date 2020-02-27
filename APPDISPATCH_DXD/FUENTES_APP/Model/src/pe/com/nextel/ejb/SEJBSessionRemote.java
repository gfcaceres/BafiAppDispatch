package pe.com.nextel.ejb;

import java.rmi.RemoteException;

import java.sql.SQLException;

import java.util.HashMap;

import javax.ejb.EJBObject;

import pe.com.nextel.bean.PortalSessionBean;


public interface SEJBSessionRemote extends EJBObject 
{
  int getSecureRol(int intSecureId) throws  Exception, RemoteException, SQLException;
    
  PortalSessionBean PortalSessionDAOubicar(String v_phone, int n_rolid, int intSalesstructid) throws Exception, SQLException, RemoteException;
  HashMap addUserSession(PortalSessionBean sessionBean) throws Exception, SQLException, RemoteException;
  void setUserSession(PortalSessionBean sessionBean) throws Exception, SQLException, RemoteException;
  PortalSessionBean getUserSession(String portalID) throws Exception, SQLException, RemoteException;
  HashMap getSessionId() throws Exception, RemoteException, SQLException;
  int getUsersConnected() throws Exception, RemoteException, SQLException;
  HashMap getUserApp(String strLogin) throws  Exception, RemoteException, SQLException;
  int getUserId(String strLogin) throws  Exception, RemoteException, SQLException;
  int getProviderId(int wn_userid) throws  Exception, RemoteException, SQLException;
  HashMap getPositionList(int wn_swprovidergrpid) throws  Exception, RemoteException, SQLException;


  
}