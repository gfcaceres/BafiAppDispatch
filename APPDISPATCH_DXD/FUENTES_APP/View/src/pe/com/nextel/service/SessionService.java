package pe.com.nextel.service;

import java.rmi.RemoteException;

import java.sql.SQLException;

import java.util.HashMap;

import javax.naming.Context;

import javax.rmi.PortableRemoteObject;

import pe.com.nextel.bean.PortalSessionBean;
import pe.com.nextel.ejb.SEJBSessionRemote;
import pe.com.nextel.ejb.SEJBSessionRemoteHome;
import pe.com.nextel.util.MiUtil;


public class SessionService extends GenericService{
  
  public SessionService(){}
  
    
  public static SEJBSessionRemote getSEJBSessionRemote()throws Exception {
        try {
            final Context context = MiUtil.getInitialContext();
            final SEJBSessionRemoteHome remoteHome = (SEJBSessionRemoteHome) PortableRemoteObject.narrow(context.lookup("SEJBSessionDispatch"), SEJBSessionRemoteHome.class);
            return remoteHome.create();            
        } catch (Exception ex) {
            System.out.println("Exception : [SessionService][getSEJBSessionRemote][" + ex.getMessage() + "]");
            return null;
        }
    }
  
  
    public int getSecureRol(int intSecureId){
    try{
       return getSEJBSessionRemote().getSecureRol(intSecureId);
    }catch (Exception e) {
       return -1;
    }
  }
  
  
   public static void setUserSession(PortalSessionBean portalUser) {
    try {
      SEJBSessionRemote ejbSession = getSEJBSessionRemote();
      ejbSession.setUserSession(portalUser);
    }catch (Exception ex) {
      System.out.println("Exception : [SessionService][setUserSession][" + ex.getMessage() + "]");
      ex.printStackTrace();
    }
  }
  
  
  public static PortalSessionBean getUserSession(String portalID){
    PortalSessionBean portalUser = new PortalSessionBean();
    
    try{
      System.out.println("[SessionService][getUserSession][Inicio]");
      System.out.println("[SessionService][getUserSession][portalID]"+portalID);              
      SEJBSessionRemote ejbSession = getSEJBSessionRemote();
      
      if (ejbSession==null){
        System.out.println("[SessionService][getUserSession][Referencia objeto ejbSession es NULO - Fall�]");
        portalUser = null;
        return portalUser;
      }else{
        System.out.println("[SessionService][getUserSession][Referencia objeto ejbSession es NO NULO - Correcto]");
        portalUser = ejbSession.getUserSession(portalID);
        if (portalUser==null){
          System.out.println("[SessionService][getUserSession][Errror al obtener la session del usuario portalUser NULO - SessionId = "+portalID+"]"); 
        }else{
          System.out.println("[SessionService][getUserSession][Exito al obtener la session del usuario portalUser NO NULO - SessionId = " + portalID + " --> " + portalUser.getLogin()+ "]"); 
        }
        System.out.println("[SessionService][getUserSession][Fin]");
        return portalUser;
      }
    }catch (Exception ex) {
       System.out.println("[Exception][SessionService][getUserSession]"+ ex.getMessage() );
       portalUser = null;
       return portalUser;
    }
  }
  
  
    //AGAMARRA 08/04/2009
  public PortalSessionBean getSessionData(String v_phone, int n_rolid, int intSalesstructid) {
    PortalSessionBean objPortalSessionBean = new PortalSessionBean();
    try{
      return objPortalSessionBean = getSEJBSessionRemote().PortalSessionDAOubicar(v_phone,n_rolid, intSalesstructid);
    }catch (Exception e) {
      System.out.println("[Exception][SessionService][getSessionData]"+ e.getMessage());
      objPortalSessionBean.setMessage("Error : " + e.getClass() + " - " + e.getMessage());
      return objPortalSessionBean;
    }
  }
  
    public HashMap addUserSessionIni(PortalSessionBean portalUser) {
    HashMap hshDataMap = new HashMap();
      try {
        System.out.println("[SessionService][addUserSessionIni][Inicio]");
        System.out.println("[SessionService][Registro del usario]"+"["+portalUser.getNom_user() + " - " + portalUser.getDn_Num()+"]");
        hshDataMap = getSEJBSessionRemote().addUserSession(portalUser);
      }catch(Throwable t){
        if( hshDataMap!= null && hshDataMap.get("strMessage")!= null)
        System.out.println("El registro de Session no se realiz� con �xito [Usuario="+portalUser.getLogin()+"] : " + hshDataMap.get("strMessage"));
        manageCatch(hshDataMap, t);
      }
		return hshDataMap;
  }
  
  
   public HashMap getSessionId(){
    HashMap objHashMap = new HashMap();
    String  strMessage = new String();
    
    try{
       objHashMap = getSEJBSessionRemote().getSessionId();
       return objHashMap;
    }catch (SQLException e) {
       System.out.println("[SQLException][SessionService][getSessionId][" + e.getMessage() + "]["+e.getClass()+"]");
       System.out.println(strMessage);
       objHashMap.put("strMessage",strMessage);
       return objHashMap;
    }catch (RemoteException e) {
       System.out.println("[RemoteException][SessionService][getSessionId][" + e.getMessage() + "]["+e.getClass()+"]");
       System.out.println(strMessage);
       objHashMap.put("strMessage",strMessage);
       return objHashMap;
    }catch (Exception e) {
       strMessage  = "[Exception][NewOrderService][SessionService][" + e.getClass() + " " + e.getMessage()+"]";
       objHashMap.put("strMessage",strMessage);
       return objHashMap;
    }
  }
  
  public int getUsersConnected(){
    try{
        return getSEJBSessionRemote().getUsersConnected();
    }catch(SQLException e){
      System.out.println("[Exception][NewOrderService][getUsersConnected][" + e.getClass() + " " + e.getMessage()+"]");
      return -1;
    }catch (RemoteException e) {
      System.out.println("[Exception][NewOrderService][getUsersConnected][" + e.getClass() + " " + e.getMessage()+"]");
      return -1;
    }catch(Exception e){
      System.out.println("[Exception][NewOrderService][getUsersConnected][" + e.getClass() + " " + e.getMessage()+"]");  
      return -1;
    }
  }
  
    //AGAMARRA
  public HashMap getUserApp(String strLogin){
    try{
      return(getSEJBSessionRemote().getUserApp(strLogin));
    }catch(Exception e){
      e.printStackTrace();
      return(null);
    }
  }
  
  //AGAMARRA
  public int getUserId(String strLogin){
    try{
      return(getSEJBSessionRemote().getUserId(strLogin));
    }catch(Exception e){
      e.printStackTrace();
      return(0);
    }
  }
  
  //AGAMARRA
  public int getProviderId(int wn_userid){
    try{
      return(getSEJBSessionRemote().getProviderId(wn_userid));
    }catch(Exception e){
      e.printStackTrace();
      return(0);
    }
  }
  
  
    //AGAMARRA
  public HashMap getPositionList(int wn_swprovidergrpid){
    try{
      return(getSEJBSessionRemote().getPositionList(wn_swprovidergrpid));
    }catch(Exception e){
      e.printStackTrace();
      return(null);
    }
  }
  
  
  
  
  
}