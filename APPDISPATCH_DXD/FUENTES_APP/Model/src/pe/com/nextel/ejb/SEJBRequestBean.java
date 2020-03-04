package pe.com.nextel.ejb;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import pe.com.nextel.bean.InstalacionBean;
import pe.com.nextel.bean.RequestBean;
import pe.com.nextel.dao.BafiOutdoorDAO;
import pe.com.nextel.dao.InstalacionDAO;
import pe.com.nextel.dao.Proveedor;
import pe.com.nextel.dao.RequestDAO;
import pe.com.nextel.util.Constante;
import pe.com.nextel.util.MiUtil;
import pe.com.nextel.util.RequestHashMap;


public class SEJBRequestBean implements SessionBean {
    private SessionContext context;
    private RequestDAO objRequestDAO = null;
    private InstalacionDAO instalacionDAO = null;
    private BafiOutdoorDAO bafiOutdoorDAO = null;
    //private OrderDAO objOrderDAO = null;
    public void ejbCreate() {
        /* Creamos las intancias a los DAO's */        
        objRequestDAO = new RequestDAO();
        instalacionDAO = new InstalacionDAO();
        bafiOutdoorDAO = new BafiOutdoorDAO();
        //objOrderDAO = new OrderDAO();
    }

    public void ejbActivate() throws EJBException {
        System.out.println("[SEJBRequestBean][ejbActivate()]");
    }


    public void ejbPassivate() throws EJBException {
        System.out.println("[SEJBRequestBean][ejbPassivate()]");
    }

    public void ejbRemove() throws EJBException {
        System.out.println("[SEJBRequestBean][ejbRemove()]");
    }

    public void setSessionContext(SessionContext ctx) throws EJBException {
        context = ctx;
    }        
    
	public ArrayList  getPendingRequest(long lngbuildingId) throws  SQLException, Exception{
       return objRequestDAO.getPendingRequest(lngbuildingId);
    }

	public HashMap getRequestAttention(long lngrequestId) throws  SQLException, Exception{
    
       HashMap mapRequest = new HashMap();
       HashMap mapRequestHeader = null;
       String motivo = null;
       String codigoEntregadoA = null; 
       ArrayList lista1 = null;
       ArrayList lista2 = new ArrayList();
       ArrayList lista3 = new ArrayList();
       ArrayList lista4 = new ArrayList();
    
       mapRequestHeader = objRequestDAO.getRequestHeader(lngrequestId);
       lista1 = (ArrayList)mapRequestHeader.get(Constante.OBJ_ARRAYHEADER);
       motivo = (String)mapRequestHeader.get(Constante.MOTIVO_OUTPUT);
       codigoEntregadoA = (String)mapRequestHeader.get(Constante.ENTREGADO_A);
       
       lista2=objRequestDAO.getAccesoryList(lngrequestId);
       lista3=objRequestDAO.getEquipmentList(lngrequestId);
       lista4=objRequestDAO.getImeiSim(lngrequestId);
       
   
       mapRequest.put(Constante.OBJ_ARRAYHEADER,lista1);
       mapRequest.put(Constante.OBJ_ARRAYACCESORY,lista2);
       mapRequest.put(Constante.OBJ_ARRAYEQUIPMENT,lista3);
       mapRequest.put(Constante.OBJ_ARRAYDEVICE,lista4);
       
       mapRequest.put(Constante.MOTIVO_OUTPUT,motivo);
       mapRequest.put(Constante.ENTREGADO_A,codigoEntregadoA);
       
      return mapRequest;
    }


    /**
     * Motivo: Inserta una solicitud al almacén
     * @return HashMap
     * @param objHashMap
     */
    public HashMap doSaveRequest(RequestHashMap objHashMap) {
    System.out.print("Entro EJB Bean Class");
        String strMessage = null;
        HashMap hshResult = new HashMap();        

        try {
            //Obtener la conexion del pool de conexiones (DataSource)
            Connection conn = Proveedor.getConnection();
            //Desactivar el commit automatico de la conexion obtenida
            conn.setAutoCommit(false);            

            try {     
                RequestBean requestBean = getRequestData(objHashMap);            
                hshResult = objRequestDAO.insRequest(requestBean, conn);
                System.out.println("SEJBRequestBean/doSaveRequest/insRequest/->" + (String) hshResult.get("strMessage"));

                if ((String) hshResult.get("strMessage") != null) {
                    if (conn != null) {
                        conn.rollback();
                    }

                    return hshResult;
                }

                return hshResult;
            } finally {
                //Finalmente, pase lo que pase, cerrar la conexion
                if (conn != null) {
                    conn.close();                   
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            strMessage = MiUtil.getMessageClean("[Exception][doSaveRequest][" + ex.getClass() + " " + ex.getMessage() + "]");
            hshResult.put("strMessage", strMessage);
            return hshResult;
        }
    }
    
    /*
     * NOTA: Es coincidencia que el parámetro y el bean de retorno se llamen 
     * request el bean request se llama así porque es la solicitud al almacén 
     * mientras que el request del parametro se llama así porque se trata del 
     * request de la aplicación.
     */
    public RequestBean getRequestData(RequestHashMap request) throws Exception {
        String strRequestId = "";
        String strOrderId = "";
        
        /*
         * 1. Sección de captura de parámetros.        
         */
        
        strRequestId = (String) request.getParameter("hdnNumeroRequest");
        strOrderId = (String) request.getParameter("hdnNumeroOrder");
        
        /*
         * 2. Sección de creación del bean.
         */
        
        RequestBean requestBean = new RequestBean();

        requestBean.setNpRequestId(MiUtil.parseInt(strRequestId));
        requestBean.setNpOrderId(MiUtil.parseInt(strOrderId));
        
        return requestBean;
    }
    
    
    
        
   
    public HashMap getEstadosList() throws  SQLException, Exception{
      return objRequestDAO.getEstadosList();
  }
  
  public HashMap getRequestAllList(RequestBean requestBean)throws  SQLException, Exception {
    return objRequestDAO.getRequestAllList(requestBean);
  }
  
  public HashMap updGenerateDoc(long lngrequestId,String strLogin)throws  SQLException, Exception {
    return objRequestDAO.updGenerateDoc(lngrequestId,strLogin);
  }
  
  public HashMap saveRequestOl(long lngRequestolid,long lngReqolstatusid, long lngRequestolitemid,
                                String strAction,String strLogin,ArrayList lstItemDevice,
                                String strScheduledate ,String strRealdate,String strNotes,
                                long lngDeliveredto, String strProductStatus, String strReason,
                                Long motivoId,
                                Long subMotivoId,
                                Long detalleId,
                                String comentario,
                                Long ordenId) throws  SQLException, Exception {
      System.out.println("Estoy en el bean");   
      
      String strMessage = null;
      HashMap hshResultado = null;
      
      // try {
       
       
        //Obtener la conexion del pool de conexiones (DataSource)
        // Connection conn = Proveedor.getConnection();        
         //Desactivar el commit automatico de la conexion obtenida
         //conn.setAutoCommit(false);     
         
        //try{
       
         //Obtener la conexion del pool de conexiones (DataSource)
         
         //Desactivar el commit automatico de la conexion obtenida
         
         System.out.println("SE conecta a grabar");
      
         hshResultado =  objRequestDAO.saveRequestOl(lngRequestolid,lngReqolstatusid, lngRequestolitemid, 
                                                     strAction,strLogin,lstItemDevice,strScheduledate,strRealdate,strNotes,lngDeliveredto,strProductStatus,strReason);
         System.out.println("SE conecta a grabar -- Antes del commit");
         //conn.commit();
         //
         
         if(bafiOutdoorDAO.validarSolicitudOlBafi(lngRequestolid)){
             System.out.println("[SEJBRequestBean][saveRequestOl] Se graba la instalacion");             
             InstalacionBean instalacionBean = new InstalacionBean();
             instalacionBean.setMotivoId(motivoId);
             instalacionBean.setSubMotivoId(subMotivoId);
             instalacionBean.setDetalleId(detalleId);
             instalacionBean.setComentario(comentario);
             instalacionBean.setOrdenId(ordenId);
             instalacionBean.setSolicitudOLId(lngRequestolid);
             instalacionBean.setCreadoPor(strLogin);
             instalacionBean.setModificadoPor(strLogin);
             int editar = bafiOutdoorDAO.validarInstalacionEdit(ordenId);
             if (editar>0){
                 instalacionDAO.actualizar(instalacionBean);
             }else{
                 instalacionDAO.insertar(instalacionBean);
             }
             
             
         }

         System.out.println("SE conecta a grabar -- Despues del commit");
         
         
        /* } catch (Exception e) {
            if (conn != null) conn.rollback();
               strMessage= e.getMessage();
               //e.printStackTrace();               
               hshResultado.put("strMessage",strMessage);
         } finally {   

            try{
              if (conn != null){
                conn.close();
                conn = null;
              }
            }catch (Exception exConn) {
              exConn.printStackTrace();
              try{
                if (conn != null){
                  conn.close();
                  conn = null;
                }
              }catch (Exception exConnAux) {
                exConnAux.printStackTrace();
                try{
                  if (conn != null){
                    conn.close();
                    conn = null;
                  }
                }catch(Exception exConnAuxiliar){
                  exConnAuxiliar.printStackTrace();
                }
              }
            
            }

         }
         } catch (Exception e) { //Error al obtener la conexion
         //e.printStackTrace();
         strMessage= "[Exception][Fallo la conexion]["+e.getClass() + " " + e.getMessage()+"][updOrder]";
         hshResultado.put("strMessage",strMessage);
         hshResultado.put("strError",strMessage);
      }*/
      
      return hshResultado;
  
  }
  
  
  public HashMap getSIM(String imei,Long ordenId) throws  SQLException, Exception {
      HashMap hashData = null;
      boolean validar= bafiOutdoorDAO.validarContratoActivoImei(ordenId, imei);
      if(validar){
          hashData = bafiOutdoorDAO.obtenerSimPorImei(imei); 
      }else{
          hashData = objRequestDAO.getSIM(imei);     
      }
      return hashData;
  }
  
  public HashMap validarSim(String an_nprequestolid)throws  SQLException, Exception {
      return objRequestDAO.validarSim(an_nprequestolid);
  }
  
    public HashMap validateModelCondition(long lngRequestolid,
                                long lngReqolstatusid,
                                long lngRequestolitemid,
                                String strAction,
                                String strLogin,
                                ArrayList lstItemDevice,
                                String strProductStatus)throws SQLException, Exception{
        return objRequestDAO.validateModelCondition(lngRequestolid, lngReqolstatusid, lngRequestolitemid, strAction, strLogin, lstItemDevice, strProductStatus);
    }
  
    public HashMap validateModelCondition2(long lngRequestolid,
                                           String strImei,
                                           String strCondicion,
                                           long lrequestStateCod,
                                           String strAccion)throws SQLException, Exception{
        return objRequestDAO.validateModelCondition2(lngRequestolid, strImei, strCondicion, lrequestStateCod, strAccion);
    }
    
    public HashMap updateModelCondition(long lngRequestolid,
                                           String strImei,
                                           String strCondicion,
                                           long lrequestStateCod,
                                           String strAccion,
                                           String strLogin)throws SQLException, Exception{
        return objRequestDAO.updateModelCondition(lngRequestolid, strImei, strCondicion, lrequestStateCod, strAccion, strLogin);
    }
  
   //INICICO JGABRIEL REQ-0123
    public String validateSubInventoryCode(String strSubInventoryCode,String numRequest) throws Exception, SQLException {
      return objRequestDAO.validateSubInventoryCode(strSubInventoryCode,numRequest);
    }    
    public String validateModelResquested(String strImei, String strNumRequest) throws Exception, SQLException {
      return objRequestDAO.validateModelResquested(strImei,strNumRequest);
    }        
    //FIN JGABRIEL REQ-0123
}