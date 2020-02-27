package pe.com.nextel.service;

import java.rmi.RemoteException;

import java.sql.SQLException;

import java.util.HashMap;

import javax.naming.Context;

import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;

import pe.com.nextel.ejb.SEJBGeneralRemote;
import pe.com.nextel.ejb.SEJBGeneralRemoteHome;
import pe.com.nextel.util.MiUtil;


public class GeneralService extends GenericService
{
  protected static Logger logger = Logger.getLogger(GeneralService.class);
  public GeneralService()
  {
  }
  
  /*
   * EDUARDO
   */
      private static SEJBGeneralRemote getSEJBGeneralRemote() {
        try {
            logger.debug("Iniciar Contexo");
            final Context context = MiUtil.getInitialContext();
            logger.debug("Contexto iniciado");
            logger.debug("Imprimiendo Contexto "+ context);
            
             Object homeObject = context.lookup("SEJBGeneralDispatch");
             logger.debug("EJB Encontrado");
             
             SEJBGeneralRemoteHome sEJBGeneralRemoteHome = 
             (SEJBGeneralRemoteHome)PortableRemoteObject.narrow(homeObject,SEJBGeneralRemoteHome.class);
            
             logger.debug("EJB Instanciado");
            
            SEJBGeneralRemote sEJBGeneralCreate = sEJBGeneralRemoteHome.create();
            
             return sEJBGeneralCreate;
    
        } catch (Exception ex) {
            System.out.println("Exception : [GeneralService][getSEJBGeneralRemote][" + ex.getMessage() + "]");
            return null;
        }
    } 
    
      /**
   * @see pe.com.nextel.dao.GeneralDAO#getDivisionList()
   */
  public HashMap getDivisionList() {
    HashMap mapaDivisionNegocio = null;
      try {
        //hshDataMap = objGeneralDAO.getDivisionList();
        mapaDivisionNegocio = getSEJBGeneralRemote().getDivisionList();
      }catch(Throwable t){
        manageCatch(mapaDivisionNegocio, t);
      }
    return mapaDivisionNegocio;
  }
  
  
   /**
   * Retorna las categorias
   * @return un objeto HashMap con las categorias
   * @param lSolutionId
   */
  public HashMap getCategoryList(long lSolutionId) {
    HashMap mapaCategoria = null;
      try {
        //hshDataMap = objGeneralDAO.getDivisionList();
        mapaCategoria = getSEJBGeneralRemote().getCategoryList(lSolutionId);
      }catch(Throwable t){
        manageCatch(mapaCategoria, t);
      }
    return mapaCategoria;
  }
  
  /**
   * Retorna las sub categorias por el id de la categoria
   * @return un objeto HashMap con las sub categorias
   * @param lSolutionId
   */
  public HashMap getSubCategoryList(String strCategoria, long lSolutionId) {
    HashMap mapaSubCategoria = null;
      try {
        //hshDataMap = objGeneralDAO.getDivisionList();
        mapaSubCategoria = getSEJBGeneralRemote().getSubCategoryList(strCategoria,lSolutionId);
      }catch(Throwable t){
        manageCatch(mapaSubCategoria, t);
      }
    return mapaSubCategoria;
  }
  
  
    
	/**
   * Motivo: Se obtiene el valor de la NpTable
	 * 
	 * @param strTableName Nombre del nptable
	 */	
	public HashMap getValueNpTable(String strTableName) {
		    
        String strMessage =  null;
        try{
            return getSEJBGeneralRemote().getValueNpTable(strTableName);
        }catch(RemoteException ex){
             System.out.println("[GeneralService][getValueNpTable][RemoteException][" + ex.getMessage()+"]");
             HashMap hshData=new HashMap();
             hshData.put("strMessage",ex.getMessage());     
             return hshData;   
         }catch(SQLException ex){
             System.out.println("[GeneralService][getValueNpTable][SQLException][" + ex.getMessage()+"]");            
             HashMap hshData=new HashMap();
             hshData.put("strMessage",ex.getMessage());     
             return hshData;    
         }catch(Exception ex){
             System.out.println("[GeneralService][getValueNpTable][Exception][" + ex.getMessage()+"]");            
             HashMap hshData=new HashMap();
             hshData.put("strMessage",ex.getMessage());     
             return hshData;   
         }
	}


/**
   * Retorna las listas de Acciones 
   * @return un objeto HashMap con las acciones
   * @param strParam,intState
   */
  public HashMap getActionDetail(String strParam,int intState) {
    HashMap mapa= null;
      try {
        //hshDataMap = objGeneralDAO.getDivisionList();
        mapa = getSEJBGeneralRemote().getActionDetail(strParam,intState);
      }catch(Throwable t){
        manageCatch(mapa, t);
      }
    return mapa;
  }
  /**
   * Retorna las listas de Razones 
   * @return un objeto HashMap con las Razones
   * @param strParam,intState
  **/
    public HashMap getReasonDetail(String strParam,int intState) {
    HashMap mapa= null;
      try {
        //hshDataMap = objGeneralDAO.getDivisionList();
        mapa = getSEJBGeneralRemote().getReasonDetail(strParam,intState);
      }catch(Throwable t){
        manageCatch(mapa, t);
      }
    return mapa;
  }



  
  //****************************************
  /**
   * <br>Realizado por: <a href="mailto:mario.mendoza-ludena@hp.com">Antonio Mendoza</a>
   * <br>Fecha: 05/10/2010
   * @see pe.com.nextel.dao.GeneralDAO#get_AttChannel_Struct(int)
   */          
  public HashMap get_AttChannel_Struct(int intSalesstructid){
    HashMap hshDataMap = new HashMap();
      try {
        int i = getSEJBGeneralRemote().get_AttChannel_Struct(intSalesstructid);        
        hshDataMap.put("iRetorno",i+"");
      }catch(Throwable t){
        manageCatch(hshDataMap, t);
      }
    return hshDataMap; 
  }
  
 

  public HashMap getLocationList() {
    HashMap mapaLocation = null;
      try {
        //hshDataMap = objGeneralDAO.getDivisionList();
        mapaLocation = getSEJBGeneralRemote().getLocationList();
      }catch(Throwable t){
        manageCatch(mapaLocation, t);
      }
    return mapaLocation;
  }
  
  public HashMap getUserList(int buildingId) {
    HashMap mapaUser = null;
      try {
        //hshDataMap = objGeneralDAO.getDivisionList();
        mapaUser = getSEJBGeneralRemote().getUserList(buildingId);
      }catch(Throwable t){
        manageCatch(mapaUser, t);
      }
    return mapaUser;
  }
  

  
}