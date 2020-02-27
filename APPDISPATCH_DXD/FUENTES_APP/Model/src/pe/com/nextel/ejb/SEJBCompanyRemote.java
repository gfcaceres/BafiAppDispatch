package pe.com.nextel.ejb;

import java.rmi.RemoteException;

import java.sql.SQLException;

import java.util.HashMap;

import javax.ejb.EJBObject;

import pe.com.nextel.bean.CompanyBean;


public interface SEJBCompanyRemote extends EJBObject 
{
  HashMap getTipoDocumentoList() throws  SQLException, Exception, RemoteException;
  HashMap getTipoCompaniaList() throws  SQLException, Exception, RemoteException;
  HashMap getCompaniaList(CompanyBean companyBean) throws  SQLException, Exception, RemoteException;
  
}