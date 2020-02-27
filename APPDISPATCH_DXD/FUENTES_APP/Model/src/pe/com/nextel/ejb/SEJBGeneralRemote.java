package pe.com.nextel.ejb;

import java.rmi.RemoteException;

import java.sql.SQLException;

import java.util.HashMap;

import javax.ejb.EJBObject;


public interface SEJBGeneralRemote extends EJBObject 
{

   public  HashMap getDivisionList() throws  SQLException, Exception, RemoteException;
   public HashMap getCategoryList(long lSolutionId)throws  SQLException, Exception, RemoteException;
   public HashMap getSubCategoryList(String strCategoria, long lSolutionId)throws SQLException, Exception, RemoteException;
   public HashMap getValueNpTable(String strTableName) throws RemoteException,SQLException, Exception;
   public HashMap getActionDetail(String strParam, int intState) throws RemoteException,SQLException, Exception;
   public HashMap getReasonDetail(String strParam, int intState) throws RemoteException,SQLException, Exception;
   public int get_AttChannel_Struct(int intSalesstructid) throws Exception, RemoteException;
   public HashMap getLocationList() throws SQLException, Exception, RemoteException;
   public HashMap getUserList(int buildingId)throws SQLException, Exception, RemoteException;
}