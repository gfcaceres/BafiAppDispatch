package pe.com.nextel.ejb;

import java.rmi.RemoteException;

import java.sql.SQLException;

import java.util.HashMap;

import javax.ejb.EJBObject;


public interface SEJBItemRemote extends EJBObject 
{
 HashMap getImeiDet(String strImeiNum) throws  SQLException, Exception, RemoteException ;

}