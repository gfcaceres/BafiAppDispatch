package pe.com.nextel.service.tde; 

import java.io.Serializable;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;

import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;

import pe.com.nextel.bean.RequestBean;
import pe.com.nextel.ejb.tde.SEJBGeneralRemote;
import pe.com.nextel.ejb.tde.SEJBGeneralRemoteHome;
import pe.com.nextel.ejb.tde.SEJBRequestRemote;
import pe.com.nextel.ejb.tde.SEJBRequestRemoteHome;
import pe.com.nextel.service.GenericService;
import pe.com.nextel.util.MiUtil;
import pe.com.nextel.util.RequestHashMap;


/**
 * Developer : Lee Rosales
 * Objetivo  : Interface que provee los servicios del EJB
 *             para ser consumidos por la capa Controller
 */
public class RequestService extends GenericService implements Serializable {

  static Logger logger = Logger.getLogger(RequestService.class);
  
    public static SEJBRequestRemote getSEJBRequestRemote() {
        try {
        
            Context context = MiUtil.getInitialContext();
        
            SEJBRequestRemoteHome remoteHome = (SEJBRequestRemoteHome) PortableRemoteObject.narrow(context.lookup("SEJBRequestTDEDispatch"), SEJBRequestRemoteHome.class);
        
            return remoteHome.create();            
          
        } catch (Exception ex) {
            System.out.println("Exception : [RequestService][getSEJBRequestRemote][" + ex.getMessage() + "]");
            return null;
        }
    }
    

	public static SEJBGeneralRemote getSEJBGeneralRemote() {
        try {
            final Context context = MiUtil.getInitialContext();
            final SEJBGeneralRemoteHome remoteHome = (SEJBGeneralRemoteHome) PortableRemoteObject.narrow(context.lookup("SEJBGeneralTDEDispatch"), SEJBGeneralRemoteHome.class);
            return remoteHome.create();            
        } catch (Exception ex) {
            System.out.println("Exception : [GeneralService][getSEJBGeneralRemote][" + ex.getMessage() + "]");
            return null;
        }
    }	

    public HashMap doSaveRequest(RequestHashMap objHashMap) throws Exception {    
        System.out.println("--ResquestService_TDE loadListRequest params: " + objHashMap + "--");
        HashMap mapaSaveRequest = null;
        try {        
            mapaSaveRequest = getSEJBRequestRemote().doSaveRequest(objHashMap);
          }catch(Throwable t){
            manageCatch(mapaSaveRequest, t);
          }
        return mapaSaveRequest;
        
    }

    public ArrayList getPendingRequest(long lngbuildingId){
        System.out.println("--ResquestService_TDE loadListRequest params: " + lngbuildingId + "--");
      ArrayList listaRequest = new ArrayList();
     try {
        //hshDataMap = objGeneralDAO.getDivisionList();
        listaRequest = getSEJBRequestRemote().getPendingRequest(lngbuildingId);
      }catch(Throwable t){
        logger.error(t);
      }
        return listaRequest;
    }
	
	public HashMap getRequestAttention(long lngrequestId) {
      HashMap mapaRequestAttention = null;
      try {
        //hshDataMap = objGeneralDAO.getDivisionList();
        mapaRequestAttention = getSEJBRequestRemote().getRequestAttention(lngrequestId);
      }catch(Throwable t){
        manageCatch(mapaRequestAttention, t);
      }
    return mapaRequestAttention;
  }
    
            
    public HashMap getEstadosList() {
      HashMap mapaEstado = null;
      try {        
        mapaEstado = getSEJBRequestRemote().getEstadosList();
      }catch(Throwable t){
        manageCatch(mapaEstado, t);
      }
    return mapaEstado;
  }
  
  public HashMap getRequestAllList(RequestBean requestBean){
      HashMap mapaRequest = null;
      try {        
        mapaRequest = getSEJBRequestRemote().getRequestAllList(requestBean);
      }catch(Throwable t){
        manageCatch(mapaRequest, t);
      }
    return mapaRequest;
  } 
  
  public HashMap updGenerateDoc(long lngrequestId,String strLogin)
  {
      System.out.println("INICIO RequestServiceTDE updGenerateDoc: lngrequestId: " + lngrequestId + ", strLogin: " + strLogin );
    HashMap mapaData=null;
    
     try {
        //hshDataMap = objGeneralDAO.getDivisionList();
        mapaData = getSEJBRequestRemote().updGenerateDoc(lngrequestId,strLogin);
      }catch(Throwable t){
        logger.error(t);
      }
        return mapaData; 
  }
  
  
  public HashMap saveRequestOl(long lngRequestolid,
                            long lngReqolstatusid,
                            long lngRequestolitemid,
                                String strAction,
                                String strLogin,
                                ArrayList lstItemDevice,
                                String strScheduledate ,
                                String strRealdate,
                                String strNotes,
                                long lngDeliveredto,
                                String strProductStatus, 
                                String strReason,
                               Long motivoId,
                               Long subMotivoId,
                               Long detalleId,
                               String comentario,
                               Long ordenId){
      System.out.println("----INICIO RequestServiceTDE - saveRequestOl lngRequestolid: " + lngRequestolid + ", lngReqolstatusid: " + lngReqolstatusid
                         + ", lngRequestolitemid: " + lngRequestolitemid +", strAction: " + strAction + ", strLogin: " + strLogin + ", lstItemDevice: " + lstItemDevice
                         + ", strScheduledate: " + strScheduledate + ", strRealdate: " + strRealdate + ", strNotes: " + strNotes + ", lngDeliveredto: " + lngDeliveredto
                         + ", strProductStatus: " + strProductStatus + ", strReason: " + strReason);
      String msg=null;
    System.out.println("----ESTOY SERVICES----");
    HashMap hshResultado = null;
     try {
        System.out.println("----ESTOY SERVICES 2----");
        System.out.println("lngRequestolid = " + lngRequestolid);
        System.out.println("lngReqolstatusid = " + lngReqolstatusid);
        System.out.println("strAction = " + strAction);
        System.out.println("strLogin = " + strLogin);
        System.out.println("lstItemDevice = " + lstItemDevice);
        System.out.println("strScheduledate = " + strScheduledate);
        System.out.println("strRealdate = " + strRealdate);
        System.out.println("strNotes = " + strNotes);
        System.out.println("lngDeliveredto = " + lngDeliveredto);
        System.out.println("strReason = " + strReason);
        
        hshResultado = getSEJBRequestRemote().saveRequestOl( lngRequestolid, 
                                                lngReqolstatusid,
                                                lngRequestolitemid,
                                                 strAction, 
                                                 strLogin, 
                                                 lstItemDevice,
                                                strScheduledate ,
                                                strRealdate, 
                                                strNotes,
                                                lngDeliveredto, 
                                                strProductStatus,
                                                            strReason,
                                                            motivoId,
                                                            subMotivoId,
                                                            detalleId,
                                                            comentario,
                                                            ordenId);
          System.out.println("----ESTOY SERVICES 3----");
      }catch(Exception e){
        System.out.println("---ERROR -ESTOY SERVICES----" + e.getMessage());
        logger.error("Error SaveRequest01",e);
      }
        return hshResultado; 
  }
  
  public HashMap getSIM(String imei, String avchOrigin){
      System.out.println("----INICIO RequestServiceTDE - getSIM param: " + imei + "----, avchOrigin: "+avchOrigin);
    HashMap hshResultado = null;
    try {
        hshResultado = getSEJBRequestRemote().getSIM(imei, avchOrigin);
    }catch(Throwable t){        
        logger.error(t);
    }
    return hshResultado; 
  }
  
  public HashMap validarSim(String an_nprequestolid){
    HashMap hshResultado = null;
    try {
        hshResultado = getSEJBRequestRemote().validarSim(an_nprequestolid);        
    }catch(Throwable t){        
        logger.error(t);
    }
    return hshResultado; 
  }
  
    public HashMap validateModelCondition(long lngRequestolid,
                                long lngReqolstatusid,
                                long lngRequestolitemid,
                                String strAction,
                                String strLogin,
                                ArrayList lstItemDevice,
                                String strProductStatus ){
        HashMap hshResultado = null;
        try {
            hshResultado = getSEJBRequestRemote().validateModelCondition(lngRequestolid, lngReqolstatusid, lngRequestolitemid, strAction, strLogin, lstItemDevice, strProductStatus);
            
        }catch(Throwable t){        
            logger.error(t);
        }
        return hshResultado; 
    }
  
    public HashMap validateModelCondition2(long lngRequestolid,
                                           String strImei,
                                           String strCondicion,
                                           long lrequestStateCod,
                                           String strAccion){
        System.out.println("INICIO RequestServiceTDE validateModelCondition2: lngRequestolid: " + lngRequestolid + ", strImei: " + strImei + ", strCondicion: " + strCondicion + ", lrequestStateCod: " + lrequestStateCod + ", strAccion: " + strAccion);
        HashMap hshResultado = null;
        try {
            hshResultado = getSEJBRequestRemote().validateModelCondition2(lngRequestolid, strImei, strCondicion, lrequestStateCod, strAccion);
            
        }catch(Throwable t){        
            logger.error(t);
        }
        return hshResultado; 
    }
    
    public HashMap updateModelCondition(long lngRequestolid,
                                        String strImei,
                                        String strCondicion,
                                        long lrequestStateCod,
                                        String strAccion,
                                        String strLogin){
        System.out.println("INICIO RequestServiceTDE updateModelCondition   : lngRequestolid: " + lngRequestolid + ", strImei: " + strImei + ", strCondicion: " + strCondicion + ", lrequestStateCod: " + lrequestStateCod + ", strAccion: " + strAccion);
        HashMap hshResultado = null;
        try {
            hshResultado = getSEJBRequestRemote().updateModelCondition(lngRequestolid, strImei, strCondicion, lrequestStateCod, strAccion, strLogin);
            
        }catch(Throwable t){        
            logger.error(t);
        }
        return hshResultado; 
    }
  
    //INICICO JGABRIEL REQ-0123
    public String validateSubInventoryCode(String strSubInventoryCode, String numRequest){
        System.out.println("INICIO RequestServiceYDE validateSubInventoryCode: strSubInventoryCode: "+strSubInventoryCode+", numRequest: " + numRequest);
        try {
           return getSEJBRequestRemote().validateSubInventoryCode(strSubInventoryCode,numRequest);
        }catch(Throwable t){        
            logger.error(t);
        }
        return null;
    } 
    
    public String validateModelResquested(String strImei, String strNumRequest){
        System.out.println("INICIO RequestServiceYDE validateModelResquested: strImei: "+strImei+", strNumRequest: " + strNumRequest);
        try {
           return getSEJBRequestRemote().validateModelResquested(strImei, strNumRequest);
        }catch(Throwable t){        
            logger.error(t);
        }
        return null;
    } 
    //FIN JGABRIEL REQ-0123

    //portega est-1343
    public String getFlagRequestOlTde(long requestId) {
        int mapaUser=0;
        try {
            //hshDataMap = objGeneralDAO.getDivisionList();
            mapaUser = getSEJBRequestRemote().getFlagRequestOlTde(requestId);
            return mapaUser==1?"DISPATCH_SEARCH_REQUEST_STORE_LIST_ATENT_TDE":null;
        }catch(Throwable t){        
            logger.error(t);
        }
        return null;
    }

}