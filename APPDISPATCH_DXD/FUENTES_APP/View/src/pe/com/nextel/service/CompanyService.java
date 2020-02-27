package pe.com.nextel.service;

import java.util.HashMap;

import javax.naming.Context;

import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;

import pe.com.nextel.bean.CompanyBean;
import pe.com.nextel.ejb.SEJBCompanyRemote;
import pe.com.nextel.ejb.SEJBCompanyRemoteHome;
import pe.com.nextel.util.MiUtil;


public class CompanyService extends GenericService
{
  protected static Logger logger = Logger.getLogger(CompanyService.class);
  
  public CompanyService(){}
  
  public static SEJBCompanyRemote getSEJBCompanyRemote() {
        try {
            final Context context = MiUtil.getInitialContext();
            final SEJBCompanyRemoteHome remoteHome = (SEJBCompanyRemoteHome) PortableRemoteObject.narrow(context.lookup("SEJBCompanyDispatch"), SEJBCompanyRemoteHome.class);
            return remoteHome.create();            
        } catch (Exception ex) {
            System.out.println("Exception : [CompanyService][getSEJBCompanyRemote][" + ex.getMessage() + "]");
            return null;
        }
    }
  
  public HashMap getTipoDocumentoList() {
    HashMap mapaTipoDocumento = null;
      try {        
        mapaTipoDocumento = getSEJBCompanyRemote().getTipoDocumentoList();
      }catch(Throwable t){
        manageCatch(mapaTipoDocumento, t);
      }
    return mapaTipoDocumento;
  }
  
  public HashMap getTipoCompaniaList() {
    HashMap mapaTipoCompania = null;
      try {        
        mapaTipoCompania = getSEJBCompanyRemote().getTipoCompaniaList();
      }catch(Throwable t){
        manageCatch(mapaTipoCompania, t);
      }
    return mapaTipoCompania;
  }
  
  public HashMap getCompaniaList(CompanyBean companyBean){
    HashMap mapaCompania = null;
      try {        
        mapaCompania = getSEJBCompanyRemote().getCompaniaList(companyBean);
      }catch(Throwable t){
        manageCatch(mapaCompania, t);
      }
    return mapaCompania;
    
  }
  
}