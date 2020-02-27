package pe.com.nextel.dao.tde; 

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pe.com.nextel.bean.DetailItemBean;
import pe.com.nextel.bean.DeviceBean;
import pe.com.nextel.bean.DominioBean;
import pe.com.nextel.bean.EquipmentBean;
import pe.com.nextel.bean.HeaderRequestBean;
import pe.com.nextel.bean.OrderBean;
import pe.com.nextel.bean.RequestBean;
import pe.com.nextel.dao.GenericDAO;
import pe.com.nextel.dao.Proveedor;
import pe.com.nextel.util.Constante;
import pe.com.nextel.util.MiUtil;


public class RequestDAO extends GenericDAO {

     static  Logger logger = Logger.getLogger(RequestDAO.class);
     
     
     public HashMap updGenerateDoc(long lngrequestId,String strLogin)throws SQLException, Exception 
     {
         System.out.println("INICIO RequestDAOTDE updGenerateDoc: lngrequestId: " + lngrequestId + ", strLogin: " + strLogin );
       	Connection conn = null;
        OracleCallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList arrList = new ArrayList();
        String strMensaje="";
        String strError="";
        HashMap mapa = new HashMap();
      try
        {           
            String sqlStr = "BEGIN INVENTORY.SPI_INS_GENERATE_DOC_TDE(?,?,?,?);END;";
            conn = Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
            cstmt.setString(1, String.valueOf(lngrequestId));
            cstmt.setString(2,strLogin);
            cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
            //cstmt.executeQuery();
            cstmt.executeUpdate();
            
            strMensaje = cstmt.getString(3);
            strError  = cstmt.getString(4);
            
            System.out.println("DOC PINTAMOS strMensaje ="+strMensaje);
            System.out.println("DOC PINTAMOS strError ="+strError);
     
        } catch (SQLException sqle) {
            System.out.println("EDU: ERROR DOC " + sqle);
            logger.error(formatException(sqle));
        } finally {
				closeObjectsDatabase(conn,cstmt,rs); 
        }
        
        //mapa.put(Constante.MESSAGE_OUTPUT,strMensaje);
        //mapa.put(Constante.ERROR_OUTPUT,strError);
        mapa.put("MENSAJE",strMensaje);
        mapa.put("MENSAJE_ERROR",strError);
        
        return mapa;
       
     }
	/**
     * Motivo: Lista las solicitudes Pendientes 
     * Realizado por : Alberto Quineche Poma
     * @param  lngbuildingId
     * @return    HashMap 
     */    
     public ArrayList getPendingRequest(long lngbuildingId)throws SQLException, Exception 
     {
             
       	Connection conn = null;
        OracleCallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList arrList = new ArrayList();
        String error;
        try
        {           
        
            String sqlStr = "BEGIN INVENTORY.SPI_GET_REQUEST_OL_LIST1_TDE(?,?,?);END;";
            conn = Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
            cstmt.setString(1, String.valueOf(lngbuildingId));
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
            cstmt.executeQuery();
          	rs = (ResultSet)cstmt.getObject(2);
            error = cstmt.getString(3);
            if (StringUtils.isNotEmpty(error)) {
                throw new SQLException(error);
            }
            
             int indice = 1;
             while (rs.next()) {
                RequestBean bean = new  RequestBean();               
                 
                int i = 1;
                
                bean.setRequestId(MiUtil.parseLong(rs.getString("nprequestolid")));
                bean.setOrderPay(StringUtils.defaultString(rs.getString("nppaymentstatus")));
                bean.setRequestNumber(StringUtils.defaultString(rs.getString("nprequestnumber")));
                bean.setOrderNumber(StringUtils.defaultString(rs.getString("nporderid")));
                bean.setRequestDate(StringUtils.defaultString(rs.getString("npcreateddate")));
                bean.setRazonSocial(StringUtils.defaultString(rs.getString("npcustomername")));
                bean.setEnvioCourier(StringUtils.defaultString(rs.getString("npcourier")));
                bean.setProvieneDe(StringUtils.defaultString(rs.getString("npreqolsource")));
                bean.setUser(StringUtils.defaultString(rs.getString("npcreatedby")));
                bean.setRequestStateDescription(StringUtils.defaultString(rs.getString("npparamv1")));
                bean.setIndice(indice);
                
                arrList.add(bean);
                indice++;
                                
            }
               } catch (SQLException sqle) {
            logger.error(formatException(sqle));
        } finally {
				closeObjectsDatabase(conn,cstmt,rs); 
        }
        return arrList;
        
     }
	/**
     * Motivo: Lista las solicitudes Pendientes 
     * Realizado por : Alberto Quineche Poma
     * @param  lngbuildingId
     * @return    HashMap 
     */ 
      public HashMap getRequestHeader(long lngrequestId)throws SQLException, Exception 
     {    
        System.out.println("EDU DAO --getRequestHeader --");
        
        Connection conn = null;
        OracleCallableStatement cstmt = null;
        
        ResultSet rs = null;
        //ResultSet rs3 = null;
        ArrayList arrList = new ArrayList();
        //ArrayList arrList3 = new ArrayList();
        
        String error="";
        String motivo="";
        String codigoEntregadoA="";
        HashMap mapaData = new HashMap();
        System.out.println("EDU: DAO d1");
        System.out.println("EDU: DAO lngrequestId = " + lngrequestId);
        
        try
        {           
            String sqlStr = "BEGIN INVENTORY.SPI_GET_NP_REQUEST_OL(?,?,?,?,?,?);END;";
            
            conn = Proveedor.getConnection();            
            cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);            
            cstmt.setString(1, String.valueOf(lngrequestId));            
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);            
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);            
            cstmt.registerOutParameter(4, OracleTypes.CURSOR);            
            cstmt.registerOutParameter(5, OracleTypes.CURSOR);            
            cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
            
            cstmt.executeQuery();
            
          	rs = (ResultSet)cstmt.getObject(2);
                //rs3 = (ResultSet)cstmt.getObject(4);
            
            error = cstmt.getString(6);          
                        
            System.out.println("EDU: DAO error = "+ error);
            
            if (StringUtils.isNotEmpty(error)) {
                throw new SQLException(error);
            }
             
             HeaderRequestBean headRequestBean;
                       
             while (rs.next()) {
                System.out.println("EDU: entro al while ");
                //HashMap objHashMap = new HashMap();
                headRequestBean = new HeaderRequestBean();
                int i = 1;
               // headRequestBean.setWn_requestolid(rs.getInt(i++)+"");
                //headRequestBean.setWv_requestnumber(StringUtils.defaultString(rs.getString(i++)));
                //headRequestBean.setwv
                headRequestBean.setWn_requestolid(rs.getInt(i++)+"");
                headRequestBean.setWv_requestnumber(StringUtils.defaultString(rs.getString(i++)));
                headRequestBean.setWv_createddate(StringUtils.defaultString(rs.getString(i++)));
                headRequestBean.setWv_createdby(StringUtils.defaultString(rs.getString(i++)));
                headRequestBean.setWv_customername(StringUtils.defaultString(rs.getString(i++)));
                headRequestBean.setWv_customertaxnumber(StringUtils.defaultString(rs.getString(i++)));
                headRequestBean.setWv_customeraddress1(StringUtils.defaultString(rs.getString(i++)));
                headRequestBean.setWv_modifyby(StringUtils.defaultString(rs.getString(i++)));
                headRequestBean.setWv_description(StringUtils.defaultString(rs.getString(i++)));
                headRequestBean.setWn_orderid(rs.getInt(i++)+"");
                headRequestBean.setWv_courier(StringUtils.defaultString(rs.getString(i++)));
                headRequestBean.setWv_name(StringUtils.defaultString(rs.getString(i++)));
                headRequestBean.setWv_type(StringUtils.defaultString(rs.getString(i++)));
                headRequestBean.setWv_specification(StringUtils.defaultString(rs.getString(i++)));
                
                headRequestBean.setWn_reqolstatusid(rs.getInt(i++)+"");
                
                headRequestBean.setWv_reqolstatus(MiUtil.trimNotNull(StringUtils.defaultString(rs.getString(i++))));
                headRequestBean.setWv_paymentstatus(StringUtils.defaultString(rs.getString(i++)));
                headRequestBean.setWv_scheduledate(StringUtils.defaultString(rs.getString(i++)));
                headRequestBean.setWv_realdate(StringUtils.defaultString(rs.getString(i++)));
                
                headRequestBean.setWv_npdeliveredto(StringUtils.defaultString(rs.getString(i++))); //Entregado
                
                headRequestBean.setWv_deliveredto(StringUtils.defaultString(rs.getString(i++)));
                headRequestBean.setWv_notes(StringUtils.defaultString(rs.getString(i++)));
                headRequestBean.setWv_npsaletrxnumber(StringUtils.defaultString(rs.getString(i++)));
                
                headRequestBean.setWv_flagGenerarDocumento(StringUtils.defaultString(rs.getString(i++)));
                
                
                motivo = StringUtils.defaultString(rs.getString(i++));   
                 
                headRequestBean.setWn_specificationId( rs.getInt("npspecificationid") );
                
                headRequestBean.setWv_npshortname(MiUtil.trimNotNull(rs.getString("npshortname")));
                headRequestBean.setCodigoSolicitud(rs.getString("npreqolstatusid"));
                codigoEntregadoA = MiUtil.trimNotNull(rs.getString("npdeliveredto"));
                
        
                arrList.add(headRequestBean);
                logger.debug("getRequestHeader  :Tamaño de Lista(arrList) :"+arrList.size());
                }
            
                /*while (rs3.next()) {
                    headRequestBean = new HeaderRequestBean();
                    
                    headRequestBean.setWn_requestolid(rs.getInt("npreqolitemid")+"");
                    
                    arrList3.add(headRequestBean);
                    logger.debug("getRequestHeader  :Tamaño de Lista(arrList3) :"+arrList3.size());
                    
                }*/
                
               } catch (SQLException sqle) {
                  System.out.println("dao error: " + sqle.getMessage());
                  logger.error(formatException(sqle));
                } 
                finally {
                    closeObjectsDatabase(conn,cstmt,rs); 
                }
        
        mapaData.put(Constante.OBJ_ARRAYHEADER, arrList);
        mapaData.put(Constante.MOTIVO_OUTPUT,motivo);
        mapaData.put(Constante.ENTREGADO_A,codigoEntregadoA);
        mapaData.put(Constante.ERROR_OUTPUT,error);
        System.out.println("EDU: DAO todo bien");
        return mapaData;
     }
	/**
     * Motivo: Lista las solicitudes Pendientes 
     * Realizado por : Alberto Quineche Poma
     * @param  lngbuildingId
     * @return    HashMap 
     */ 
      public ArrayList getAccesoryList(long lngrequestId)throws SQLException, Exception 
     {
        Connection conn = null;
        OracleCallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList arrList = new ArrayList();
        String error;
        try
        {           
        
            String sqlStr = "BEGIN INVENTORY.SPI_GET_NP_REQUEST_OL(?,?,?,?,?,?);END;";
            conn = Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
            cstmt.setString(1, String.valueOf(lngrequestId));
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);
            cstmt.registerOutParameter(4, OracleTypes.CURSOR);
            cstmt.registerOutParameter(5, OracleTypes.CURSOR);
            cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
            cstmt.executeQuery();
          	rs = (ResultSet)cstmt.getObject(3);
            error = cstmt.getString(6);
            if (StringUtils.isNotEmpty(error)) {
                throw new SQLException(error);
            }
               int a = 1;  
             while (rs.next()) {
                HashMap objHashMap = new HashMap();
                int i = 1;
                 logger.debug("PINTAMOS EL CONTADOR ACCESORIO:   : : : : : : "+a);
                objHashMap.put("wn_contador",a+"");
                objHashMap.put("wv_itemtypeid", StringUtils.defaultString(rs.getString(i++),""));
                objHashMap.put("wn_productid", rs.getInt(i++)+"");
                objHashMap.put("wv_segment1", StringUtils.defaultString(rs.getString(i++),""));
                objHashMap.put("wv_name", StringUtils.defaultString(rs.getString(i++),""));
                objHashMap.put("wv_modalitysell", StringUtils.defaultString(rs.getString(i++),""));
                objHashMap.put("wv_productstatus", StringUtils.defaultString(rs.getString(i++),""));
                objHashMap.put("wn_quantity", rs.getInt(i++)+"");
                objHashMap.put("wn_organizationid", rs.getInt(i++)+"");
                objHashMap.put("wn_oname", StringUtils.defaultString(rs.getString(i++),""));
                objHashMap.put("wv_subinventoryid", StringUtils.defaultString(rs.getString(i++),""));
                objHashMap.put("wn_reasonid", rs.getInt(i++)+"");
                objHashMap.put("wv_reason_name", StringUtils.defaultString(rs.getString(i++),""));
                objHashMap.put("wv_transnnumber", StringUtils.defaultString(rs.getString(i++),""));
                
                objHashMap.put("wv_descripcionAlmacen", StringUtils.defaultString(rs.getString("name"),""));
                
                //objHashMap.put("wv_reqOlItemId", StringUtils.defaultString(rs.getString("npreqolitemid"),""));
                 
                 
                objHashMap.put("wv_descripcionDocumento", StringUtils.defaultString(rs.getString("nptransnnumber"),""));
                
                arrList.add(objHashMap);
               
                a++;
               
            }
             logger.debug("getAccesoryList:Tamaño de Lista(arrList) :"+arrList.size());
               } catch (SQLException sqle) {
            logger.error(formatException(sqle));
        } finally {
				closeObjectsDatabase(conn,cstmt,rs); 
        }
        return arrList;
     }
	
	/**
     * Motivo: Lista las solicitudes Pendientes 
     * Realizado por : Alberto Quineche Poma
     * @param  lngbuildingId
     * @return    HashMap 
     */ 
      public ArrayList getEquipmentList(long lngrequestId)throws SQLException, Exception 
     {
     
      System.out.println("[AppDispatch.RequestDAO.getEquipmentList][Ingreso]");
        Connection conn = null;
        OracleCallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList arrList = new ArrayList();
        String error;
        try
        {           
        
            String sqlStr = "BEGIN INVENTORY.SPI_GET_NP_REQUEST_OL(?,?,?,?,?,?);END;";
            conn = Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
            cstmt.setString(1, String.valueOf(lngrequestId));
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);
            cstmt.registerOutParameter(4, OracleTypes.CURSOR);
            cstmt.registerOutParameter(5, OracleTypes.CURSOR);
            cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
            cstmt.executeQuery();
          	rs = (ResultSet)cstmt.getObject(4);
            error = cstmt.getString(6);
            System.out.println("ERROR: " + error);
            if (StringUtils.isNotEmpty(error)) {
                throw new SQLException(error);
            }
            
            //Obtnemos la lista de DEVICE
            List listaDevice = getImeiSim(lngrequestId);
            
            int a =1;
            while (rs.next()) {
                //HashMap objHashMap = new HashMap();
                EquipmentBean equipmentBean =  new EquipmentBean();
                
                int i = 1;
                
                System.out.println("PINTAMOS EL CONTADOR EQUIPO:   : : : : : : "+a);
                
                equipmentBean.setWn_contador(a+"");                
                equipmentBean.setWv_reqolitemid(StringUtils.defaultString(rs.getString("npreqolitemid"),""));                                
                equipmentBean.setWv_itemtypeid(StringUtils.defaultString(rs.getString("npitemtypeid"),""));                
                equipmentBean.setWn_productid(rs.getInt("npproductid")+"");                
                equipmentBean.setWv_segment1(StringUtils.defaultString(rs.getString("npsegment1"),""));                
                equipmentBean.setWv_name(StringUtils.defaultString(rs.getString("npname"),""));
                equipmentBean.setWv_reqolimei(StringUtils.defaultString(rs.getString("npreqolimei"),""));                
                equipmentBean.setWv_reqimeisim(StringUtils.defaultString(rs.getString("npreqimeisim"),""));                
                equipmentBean.setWv_returneqimeisim(StringUtils.defaultString(rs.getString("npreturneqimeisim"),""));                
                equipmentBean.setWv_modalitysell(StringUtils.defaultString(rs.getString("npmodalitysell"),""));                
                equipmentBean.setWv_productstatus(StringUtils.defaultString(rs.getString("npproductstatus"),""));                
                equipmentBean.setWn_quantity(rs.getInt("npquantity")+"");                
                equipmentBean.setWv_accessory(StringUtils.defaultString(rs.getString("npaccessory"),""));
                equipmentBean.setWn_reasonid(rs.getInt("npreasonid")+"");
                equipmentBean.setWv_reason_name(StringUtils.defaultString(rs.getString("reason_name"),""));
                equipmentBean.setWv_transnnumber(StringUtils.defaultString(rs.getString("nptransnnumber"),""));
                equipmentBean.setWv_solution(StringUtils.defaultString(rs.getString("npsolutionname"),""));
                equipmentBean.setWv_npreqolimei(StringUtils.defaultString(rs.getString("npreqolimei"),""));
                equipmentBean.setWv_npreqimeisim(StringUtils.defaultString(rs.getString("npreqimeisim"),""));
                
                System.out.println("[equipmentBean.setWv_reqolitemid ]["+equipmentBean.getWv_reqolitemid()+"]");
                
                //Agregamos los item device 
                equipmentBean.setListaDeviceBean(new ArrayList());                
                if(listaDevice.size()>0){
                    for(int x=0; x<listaDevice.size(); x++){
                        DeviceBean device = (DeviceBean)listaDevice.get(x);
                        if(equipmentBean.getWv_reqolitemid().equals(device.getWv_reqolitemid_device())){
                            equipmentBean.getListaDeviceBean().add(device);                            
                        }
                    }
                }else{
                    DeviceBean device = new DeviceBean();
                    device.setWv_reqalmacen("");
                    device.setWv_reqimeisim("");
                    device.setWv_reqolimei("");
                    device.setWv_reqolitemdeviceid("");
                    device.setWv_reqolitemid_device("");
                    device.setWv_reqorganizationid("");
                    device.setWv_reqsubinventario("");  
                    
                    equipmentBean.getListaDeviceBean().add(device);
                }
  
                arrList.add(equipmentBean);
                
              a++;
                   
            }
               } catch (SQLException sqle) {
               System.out.println("" + sqle.getMessage() );
            System.out.println((formatException(sqle)));
        } finally {
				closeObjectsDatabase(conn,cstmt,rs); 
        }
        return arrList;
     }
     
     
     
       public ArrayList getImeiSim(long lngrequestId)throws SQLException, Exception 
     {
        Connection conn = null;
        OracleCallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList arrList = new ArrayList();
        String error;
        try
        {           
        
            String sqlStr = "BEGIN INVENTORY.SPI_GET_NP_REQUEST_OL(?,?,?,?,?,?);END;";
            conn = Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
            cstmt.setString(1, String.valueOf(lngrequestId));
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);
            cstmt.registerOutParameter(4, OracleTypes.CURSOR);
            cstmt.registerOutParameter(5, OracleTypes.CURSOR);
            cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
            cstmt.executeQuery();
          	rs = (ResultSet)cstmt.getObject(5);
            error = cstmt.getString(6);
            if (StringUtils.isNotEmpty(error)) {
                throw new SQLException(error);
            }

             while (rs.next()) {
                
                DeviceBean deviceBean =  new DeviceBean();
                
                int i = 1;

                deviceBean.setWv_reqolitemid_device(String.valueOf(rs.getInt("npreqolitemid")));
                deviceBean.setWv_reqolitemdeviceid(String.valueOf(rs.getInt("npreqolitemdeviceid")));
                deviceBean.setWv_reqolimei(StringUtils.defaultString(rs.getString("npreqolimei"),""));
                deviceBean.setWv_reqimeisim(StringUtils.defaultString(rs.getString("npreqimeisim"),""));
                deviceBean.setWv_reqalmacen(StringUtils.defaultString(rs.getString("name"),""));
                deviceBean.setWv_reqsubinventario(StringUtils.defaultString(rs.getString("npsubinventoryid"),""));
                deviceBean.setWv_reqorganizationid(String.valueOf(rs.getInt("nporganizationid")));
                                
                arrList.add(deviceBean);
                logger.debug("getImeiSim:Tamaño de Lista(arrList) :"+arrList.size());
 
            }
               } catch (SQLException sqle) {
            logger.error(formatException(sqle));
        } finally {
				closeObjectsDatabase(conn,cstmt,rs); 
        }
        return arrList;
     }

    /**
     * Motivo: Inserta una solicitud al almacén
     * <br>Realizado por: <a href="mailto:miguel.montoya@hp.com">Miguel Ángel Montoya</a>
     * <br>Fecha: 11/07/2007       
     * @return    HashMap 
     */    
    public HashMap insRequest(RequestBean requestBean, Connection conn) throws Exception, SQLException {      
       HashMap hshRetorno=new HashMap();
   
   
      String strMessage=null;
      OracleCallableStatement cstmt = null;     
      
      ResultSet rs = null;
      OrderBean orbResume = new OrderBean();
     
      //conn = Proveedor.getConnection();
      
      try{
        String strSql = "BEGIN NP_ORDERS01_PKG.SP_GET_ORDER_DETAIL( ? , ? , ? ); end;";        
		  
        cstmt = (OracleCallableStatement)conn.prepareCall(strSql);
        cstmt.setLong(1, 1162);
        cstmt.registerOutParameter(2, Types.CHAR);
        cstmt.registerOutParameter(3, OracleTypes.CURSOR);
        cstmt.execute();
        strMessage = cstmt.getString(2);
        if (strMessage == null){
           rs = (ResultSet)cstmt.getObject(3);
           if (rs.next()) {
             orbResume.setNpOrderCode(rs.getString("npOrderCode")); //id solicitud
              orbResume.setNpType(rs.getString("npType")); //categoria
              orbResume.setNpSpecification(rs.getString("npSpecification")); //subcategoria  
              orbResume.setNpCreatedDate(rs.getTimestamp("npCreatedDate")); // Fecha de creacion
              orbResume.setNpOrderId(rs.getInt("npOrderId")); // Orden ID                
              orbResume.setNpStatus(rs.getString("npStatus")); // Estado de la orden                                
              orbResume.setNpSalesmanName(rs.getString("npSalesmanName")); 
              orbResume.setNpDealerName(rs.getString("npDealerName")); 
              orbResume.setNpPaymentStatus(rs.getString("npPaymentStatus"));
              orbResume.setNpPaymentFutureDate(rs.getDate("npPaymentFutureDate")); 
              orbResume.setNpPaymentDate(rs.getDate("npPaymentDate"));  
              orbResume.setNpDescription(rs.getString("npDescription")); 
              orbResume.setNpScheduleDate(rs.getDate("npScheduleDate")); 
              orbResume.setNpScheduleStatus(rs.getInt("npScheduleStatus"));
              //orbResume.setNpSolutionName(rs.getString("npname")); 
              //orbResume.setNpSolutionId(rs.getInt("npsolutionid")); 
              orbResume.setNpSignDate(rs.getTimestamp("npSignDate")); 
              orbResume.setNpWorkFlowId(rs.getInt("npworkflowid")); 
              orbResume.setNpSpecificationId(rs.getInt("npSpecificationId")); 
              orbResume.setNpDeliveryDate(rs.getTimestamp("npDeliveryDate")); 
              orbResume.setNpCreatedBy(rs.getString("npCreatedBy"));
              orbResume.setNpExceptionApprove(rs.getInt("npExceptionApprove")); 
              orbResume.setNpPaymentTerms(rs.getString("npPaymentTerms"));
              orbResume.setNpCreatedDate(rs.getTimestamp("npCreatedDate"));  
              orbResume.setNpPaymentTotal(rs.getDouble("npPaymentTotal")); 
              orbResume.setNpPaymentAmount(rs.getDouble("npPaymentAmount")); 

           }
        }                   
        logger.debug("PINTAMOS LA RESPUESTA : "+orbResume.getNpOrderId());
        
        hshRetorno.put("objResume",orbResume);
        hshRetorno.put("strMessage",strMessage);        
      }catch(Exception e){
         hshRetorno.put("strMessage",e.getMessage()); 
      }finally{
        try{
          closeObjectsDatabase(conn,cstmt,rs); 
          }catch (Exception e) {
            logger.error(formatException(e));
          }
      }   
      return hshRetorno;
    }
      
     
      
      public HashMap getEstadosList() throws SQLException, Exception {
      if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
      
      HashMap hshDataMap = new HashMap();
      List arrEstadoList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      
      String sqlStr = "BEGIN INVENTORY.SPI_GET_CONFIGURATION2_TDE(?,?,?); END;";
      try{
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
         cstmt.setString(1, Constante.PARAM_BD_OL_NPREQOLSTATUS);
         cstmt.registerOutParameter(2,OracleTypes.VARCHAR);
         cstmt.registerOutParameter(3, OracleTypes.CURSOR);
         cstmt.executeQuery();         
         strMessage = cstmt.getString(2);
         
         rs = (ResultSet)cstmt.getObject(3);
         
         if (StringUtils.isBlank(strMessage)) {
            while (rs.next()) {
               DominioBean dominio = new DominioBean();
               int i = 1;               
               try {
                  dominio.setValor(String.valueOf(rs.getInt(1)));
                  dominio.setDescripcion(MiUtil.trimNotNull(rs.getString(2)));
               }
               catch(SQLException sqle) {
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(1)));
               }
               arrEstadoList.add(dominio);
            }
         }         
         
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
      hshDataMap.put(Constante.SOLICITUD_LISTA_ESTADOS, arrEstadoList);
      hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
      return hshDataMap;
    }
    
    /**
   * Motivo: Obtiene las solicitudes segun los filtros de busqueda
   * @throws java.lang.Exception
   * @throws java.sql.SQLException
   * @return 
   */
     public HashMap getRequestAllList(RequestBean requestBean) throws SQLException, Exception {
     
      if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
      
      HashMap hshDataMap = new HashMap();
      List arrRequestAllList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      
      String sqlStr = "BEGIN INVENTORY.SPI_GET_REQUEST_OL_LIST2_TDE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
      
      System.out.println("----Pametros de busqueda-----"); 
            
      System.out.println("requestBean.getLocation() = " + requestBean.getLocation());
      System.out.println("requestBean.getUser() = " + requestBean.getUser());
      System.out.println("requestBean.getRequestNumber() = " + requestBean.getRequestNumber());
      System.out.println("requestBean.getOrderPay() = " + requestBean.getOrderPay());
      System.out.println("requestBean.getEstadoSolicitud() = " + requestBean.getEstadoSolicitud());
      System.out.println("requestBean.getBusinessDivision() = " + requestBean.getBusinessDivision());
      System.out.println("requestBean.getCategory() = " + requestBean.getCategory());
      System.out.println("requestBean.getSubCategory() = " + requestBean.getSubCategory());
      System.out.println("requestBean.getRazonSocial() = " + requestBean.getRazonSocial());
      System.out.println("requestBean.getCodBSCS() = " + requestBean.getCodBSCS());
      System.out.println("requestBean.getFacturaNumber() = " + requestBean.getFacturaNumber());
      System.out.println("requestBean.getRequestDateBegin() = " + requestBean.getRequestDateBegin());
      System.out.println("requestBean.getRequestDateEnd() = " + requestBean.getRequestDateEnd());
      System.out.println(" requestBean.getOrderNumber() = " + requestBean.getOrderNumber());
       
      String estadoSolicitud=null;
      if(requestBean.getEstadoSolicitud() != ""){
          estadoSolicitud = requestBean.getEstadoSolicitud();
      }
        
      try{
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
         cstmt.setInt(1, requestBean.getLocation());
         cstmt.setString(2, requestBean.getUser());
         cstmt.setString(3, requestBean.getRequestNumber());
         cstmt.setString(4, requestBean.getOrderPay());
         cstmt.setString(5, estadoSolicitud);
         cstmt.setInt(6, requestBean.getBusinessDivision());
         cstmt.setString(7, requestBean.getCategory());
         cstmt.setString(8, requestBean.getSubCategory());
         cstmt.setString(9, requestBean.getRazonSocial());
         cstmt.setString(10, requestBean.getCodBSCS());
         cstmt.setString(11, requestBean.getFacturaNumber());         
         cstmt.setString(12, requestBean.getRequestDateBegin());
         cstmt.setString(13, requestBean.getRequestDateEnd());      
         cstmt.setString(14, requestBean.getOrderNumber());             
         cstmt.registerOutParameter(15, OracleTypes.CURSOR);
         cstmt.registerOutParameter(16, OracleTypes.VARCHAR);
         
         cstmt.executeQuery();         
         strMessage = cstmt.getString(16);
         System.out.println("EDU:---- strMessage = " + strMessage);
         
         rs = (ResultSet)cstmt.getObject(15);
                  
         /* EDU: borrar
         System.out.println("EDU: rs.getMetaData().getColumnCount()= " +rs.getMetaData().getColumnCount());
         for (int x=1;x<=rs.getMetaData().getColumnCount();x++){
            System.out.println(rs.getMetaData().getColumnName(x)+ " "+ rs.getMetaData().getColumnTypeName(x));
            //System.out.println(x);
          }
          */
         
         if (StringUtils.isBlank(strMessage)) {
            
            int indice = 1;         
            while (rs.next()) {   
            
               RequestBean bean =  new RequestBean();    
               
               try {  
                  bean.setRequestId(rs.getLong(1));                  
                  bean.setOrderPay(rs.getString(2));                  
                  bean.setRequestNumber(rs.getString(3));                  
                  bean.setOrderNumber(rs.getString(4));                  
                  bean.setRequestDate(rs.getString(5));                  
                  bean.setRazonSocial(rs.getString(6));                  
                  bean.setEnvioCourier(rs.getString(7));                  
                  bean.setProvieneDe(rs.getString(8));                  
                  bean.setUser(rs.getString(9));                  
                  bean.setRequestStateDescription(rs.getString(10));                  
                  bean.setIndice(indice);
                  
                  indice++;
                  
               }
               catch(SQLException sqle) {
                  System.out.println("ERROR: sqle = " + sqle.getMessage());
                  logger.error(sqle);
               }
               arrRequestAllList.add(bean);
            }
          
         }         
         
      }
      catch(Exception ex){
        System.out.println("ERROR: ex = " + ex.getMessage());
        throw new Exception(ex);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
      
      System.out.println("EDU: DAO arrRequestAllList.size() = " + arrRequestAllList.size());
      
      hshDataMap.put(Constante.SOLICITUD_LISTA_BEAN, arrRequestAllList);
      hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
      return hshDataMap;
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
                                String strReason )throws SQLException, Exception {
         System.out.println("----INICIO RequestDAOTDE - saveRequestOl lngRequestolid: " + lngRequestolid + ", lngReqolstatusid: " + lngReqolstatusid
                            + ", lngRequestolitemid: " + lngRequestolitemid +", strAction: " + strAction + ", strLogin: " + strLogin + ", lstItemDevice: " + lstItemDevice
                            + ", strScheduledate: " + strScheduledate + ", strRealdate: " + strRealdate + ", strNotes: " + strNotes + ", lngDeliveredto: " + lngDeliveredto
                            + ", strProductStatus: " + strProductStatus + ", strReason: " + strReason);
                                 
        System.out.println("[AppRepair.REQUESTDAO.saveRequestOl][Ingreso]");
        
        ResultSet rs=null;
        Connection conn = null;
        OracleCallableStatement cstmt = null;
        String strMessage = null;
        String msg=null;
        String strError=null;
        HashMap   objHashMapResultado = new HashMap();   
        ArrayList arrRequest    = new ArrayList();
        ArrayList arrRequest2    = new ArrayList();
        String sqlStr = "BEGIN INVENTORY.SPI_SAVE_REQUEST_OL_TDE(?,?,?,?,?,?,?,?,?,?,?,?,?);END;";
      
      try
        {           
            conn = Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);            
            
            StructDescriptor sdRequest = StructDescriptor.createDescriptor("INVENTORY.TO_NP_REQ_OL_ITEM_DEVICE", conn);
            ArrayDescriptor  adRequest = ArrayDescriptor.createDescriptor("INVENTORY.TT_NP_REQ_OL_ITEM_DEVICE", conn);
            
            StructDescriptor sdRequest2 = StructDescriptor.createDescriptor("INVENTORY.TO_NP_REQUEST_OL_ITEM", conn);
            ArrayDescriptor  adRequest2 = ArrayDescriptor.createDescriptor("INVENTORY.TT_NP_REQUEST_OL_ITEM", conn);
            
            System.out.println("EDU lstItemDevice.size() = " + lstItemDevice.size());
            
            System.out.println("[lngRequestolid][" + lngRequestolid+"][lngReqolstatusid]["+lngReqolstatusid+"][strAction]["+strAction+"]");
             System.out.println("EDU: dao SPI_SAVE_REQUEST_OL-- strLogin = " + strLogin);
             System.out.println("[strLogin][" + strLogin+"][strScheduledate]["+strScheduledate+"][strRealdate]["+strRealdate+"][strNotes]["+strNotes+"][lngDeliveredto]["+lngDeliveredto+"]");
             //System.out.println("EDU 5 aryItemAgreement = " + aryItemAgreement);
             System.out.println("EDU 10 strReason = " + strReason);
             System.out.println("[strReason][" + strReason+"][strProductStatus]["+strProductStatus+"][lngRequestolitemid]["+lngRequestolitemid+"]");
            
            for(int i=0; i<lstItemDevice.size(); i++){ 
                //HashMap objHashMap = (HashMap)lstItemDevice.get(i);
                DetailItemBean item = (DetailItemBean)lstItemDevice.get(i); 
                
                String strIdEquipo = item.getIdEquipo();
                String strImei = item.getImei();
                String strSim = item.getSim();
                String strAlamcen = item.getAlmacen();
                String strSubInventario = item.getSubInventario();
                String strOrganitationId = item.getOrganitationId();
                
                System.out.println("  "+i+" item.getIdEquipo() = " + item.getIdEquipo());
                System.out.println("  "+i+" item.getImei() = " + item.getImei());
                System.out.println("  "+i+" item.getSim() = " + item.getSim());
                System.out.println("  "+i+" item.getAlmacen() = " + item.getAlmacen());
                System.out.println("  "+i+" item.getSubInventario() = " + item.getSubInventario());
                System.out.println("  "+i+" item.getOrganitationId() = " + item.getOrganitationId());
                
                if(strIdEquipo.equals("")){
                   strIdEquipo = null; 
                }
                if(strImei.equals("")){
                   strImei = null; 
                }
                if(strSim.equals("")){
                   strSim = null; 
                }
                if(strAlamcen.equals("")){
                   strAlamcen = null; 
                }
                if(strSubInventario.equals("")){
                   strSubInventario = null; 
                }
                if(strOrganitationId.equals("")){
                   strOrganitationId = null; 
                }               
                
                System.out.println("EDU  "+i+" strIdEquipo = " + strIdEquipo);
                System.out.println("EDU  "+i+" strImei = " + strImei);
                System.out.println("EDU  "+i+" strSim = " + strSim);
                System.out.println("EDU  "+i+" strAlamcen = " + strAlamcen);
                System.out.println("EDU  "+i+" strSubInventario = " + strSubInventario);
                System.out.println("EDU  "+i+" strOrganitationId = " + strOrganitationId);
                
                       
                Object[] objRequest = {	strIdEquipo, strImei, strSim, strOrganitationId, strSubInventario };
                Object[] objRequest2 = { lngRequestolitemid, lngRequestolid,
                                            null, null, null, null, null,
                                            strProductStatus,
                                            null, null, null, null, null, null, null, null, null,
                                            strLogin,
                                            null, null, null, null, null };
                              
                //Object[] objRequest = {	"60", "001700162462910","12345"};
                STRUCT stcRequest = new STRUCT(sdRequest, conn, objRequest);
                STRUCT stcRequest2 = new STRUCT(sdRequest2, conn, objRequest2);
              
              System.out.println("EDU 1");
              arrRequest.add(stcRequest); 
              arrRequest2.add(stcRequest2); 
              System.out.println("EDU 2");
              

            }
             ARRAY aryItemAgreement = new ARRAY(adRequest, conn, arrRequest.toArray());
             ARRAY aryItemAgreement2 = new ARRAY(adRequest2, conn, arrRequest2.toArray());
             
             System.out.println("EDU 3");        
             
             
            cstmt.setLong(1, lngRequestolid);             
            cstmt.setLong(2, lngReqolstatusid);             
            cstmt.setString(3,strAction);
            cstmt.setString(4,strLogin);
          
            cstmt.setARRAY(5, aryItemAgreement);
            cstmt.setARRAY(6, aryItemAgreement2);
          
            cstmt.setString(7,strScheduledate);
            
            cstmt.setString(8,strRealdate);
             
            cstmt.setString(9,strNotes);
             
             cstmt.setLong(10, lngDeliveredto);
             
             cstmt.setString(11,strReason);
             
             cstmt.registerOutParameter(12, OracleTypes.VARCHAR);
             
              cstmt.registerOutParameter(13, OracleTypes.VARCHAR);
            
            cstmt.executeUpdate();
            
             System.out.println("EDU 15");
             msg = cstmt.getString(12);
             strError = cstmt.getString(13);
             
            logger.debug("PITAMOS EL MENSAJE DE SALIDA : "+msg);
            logger.debug("PITAMOS EL ERROR DE SALIDA : "+strError);
            //System.out.println("SALIO REQUESTDAO > saveRequestOl");
             
             objHashMapResultado.put("strMessage",msg);
             objHashMapResultado.put("strError",strError);
            
             
        } catch (SQLException sqle) {
            //logger.error(formatException(sqle));
             System.out.println("error "+sqle.getMessage());
             
            
        } catch (Exception ex) {
            //logger.error(formatException(sqle));
             System.out.println("DAO error: "+ex.getMessage());
             System.out.println("DAO causado: "+ ex.getCause());
             System.out.println("DAO localize: "+ ex.getLocalizedMessage());             
             ex.printStackTrace();
        }
        
        
        finally {
				closeObjectsDatabase(conn,cstmt,rs); 
        }
        return objHashMapResultado;              
     }
     
     
     public HashMap getSIM(String imei, String avchOrigin)throws SQLException, Exception {
         System.out.println("----INICIO RequestDAO getSIM param: " + imei + "----, avchOrigin: "+avchOrigin);
        if(logger.isDebugEnabled()){
            logger.debug("--Inicio--");
        }
      
        HashMap hshDataMap = new HashMap();
        String strSIM = "";
        Connection conn = null;
        OracleCallableStatement cstmt = null;
        ResultSet rs = null;
        //DbmsOutput dbmsOutput = null;
        String strMessage = null;
        System.out.println("DAO: --- getSIM ---");
                
        String sqlStr = "BEGIN websales.spi_get_sim_by_imei_tde(?,?,?,?); END;";
        try{
             conn = Proveedor.getConnection();
             cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
             
             cstmt.setString(1, imei);
             cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
             cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
             cstmt.setString(4, avchOrigin);
             cstmt.executeQuery();  
             
             strSIM = cstmt.getString(2);                  
             strMessage = cstmt.getString(3);
             
             System.out.println("strSIM: " + strSIM);
             System.out.println("strMessage: " + strMessage);
                      
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
      hshDataMap.put(Constante.SIM, strSIM);
      hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
      return hshDataMap;
      
     }
     
     
    
     
     public HashMap validarSim(String an_nprequestolid)throws SQLException, Exception {
                
        if(logger.isDebugEnabled()){
            logger.debug("--Inicio--");
        }
      
        HashMap hshDataMap = new HashMap();
        int flagValidacion = 0;
        Connection conn = null;
        OracleCallableStatement cstmt = null;
        ResultSet rs = null;
        //DbmsOutput dbmsOutput = null;
        String strMessage = null;
        System.out.println("DAO: --- validarSim ---");
        System.out.println("an_nprequestolid = " + an_nprequestolid);
               
        String sqlStr = " { ? = call INVENTORY.NP_INVENTORY_REQUEST01_OL_PKG.FX_GET_VALIDATE_SIM ( ? ) } "; 
        /*         
        Si es 1 : Se realiza validacion
        Si es 0: No se realiza validacion
        */
        try{
             conn = Proveedor.getConnection();             
             cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);                            
             cstmt.registerOutParameter(1,Types.NUMERIC);                          
             cstmt.setInt(2, Integer.parseInt(an_nprequestolid));
             cstmt.execute();                    
             flagValidacion = cstmt.getInt(1);
             
             System.out.println("flagValidacion: " + flagValidacion);
              
                      
      }
      catch(Exception e){
        strMessage = e.getMessage();
        System.out.println("Error - validarSim:" + e.getMessage());
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
      hshDataMap.put(Constante.FLAG_VALIDA_SIM, String.valueOf(flagValidacion));
      hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
      return hshDataMap;
      
     }
    
    public HashMap validateModelCondition(long lngRequestolid,
                                long lngReqolstatusid,
                                long lngRequestolitemid,
                                String strAction,
                                String strLogin,
                                ArrayList lstItemDevice,
                                String strProductStatus )throws SQLException, Exception {
                                
        System.out.println("[AppRepair.REQUESTDAO.validateModelCondition][Ingreso]");
        
        ResultSet rs=null;
        Connection conn = null;
        OracleCallableStatement cstmt = null;
        String strMessage = null;
        String msg=null;
        String strError=null;
        HashMap   objHashMapResultado = new HashMap();   
        ArrayList arrRequest    = new ArrayList();
        ArrayList arrRequest2    = new ArrayList();
        String sqlStr = "BEGIN INVENTORY.SPI_VAL_MODEL_CONDITION_TDE(?,?,?,?,?,?,?);END;";
      
      try
        {           
            conn = Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);            
            
            StructDescriptor sdRequest = StructDescriptor.createDescriptor("INVENTORY.TO_NP_REQ_OL_ITEM_DEVICE", conn);
            ArrayDescriptor  adRequest = ArrayDescriptor.createDescriptor("INVENTORY.TT_NP_REQ_OL_ITEM_DEVICE", conn);
            
            StructDescriptor sdRequest2 = StructDescriptor.createDescriptor("INVENTORY.TO_NP_REQUEST_OL_ITEM", conn);
            ArrayDescriptor  adRequest2 = ArrayDescriptor.createDescriptor("INVENTORY.TT_NP_REQUEST_OL_ITEM", conn);
                        
            System.out.println("[lngRequestolid][" + lngRequestolid+
                              "]\n[lngReqolstatusid]["+lngReqolstatusid+
                              "]\n[lngRequestolitemid]["+lngRequestolitemid+
                              "]\n[strAction]["+strAction+
                              "]\n[strLogin]["+strLogin+
                              "]\n[lstItemDevice.size]["+lstItemDevice.size()+
                              "]\n[strProductStatus]["+strProductStatus+"]");
          
            for(int i=0; i<lstItemDevice.size(); i++){ 
                //HashMap objHashMap = (HashMap)lstItemDevice.get(i);
                DetailItemBean item = (DetailItemBean)lstItemDevice.get(i); 
                
                String strIdEquipo = item.getIdEquipo();
                String strImei = item.getImei();
                String strSim = item.getSim();
                String strAlamcen = item.getAlmacen();
                String strSubInventario = item.getSubInventario();
                String strOrganitationId = item.getOrganitationId();
                
                System.out.println("  "+i+" item.getIdEquipo() = " + item.getIdEquipo());
                System.out.println("  "+i+" item.getImei() = " + item.getImei());
                System.out.println("  "+i+" item.getSim() = " + item.getSim());
                System.out.println("  "+i+" item.getAlmacen() = " + item.getAlmacen());
                System.out.println("  "+i+" item.getSubInventario() = " + item.getSubInventario());
                System.out.println("  "+i+" item.getOrganitationId() = " + item.getOrganitationId());
                
                if(strIdEquipo.equals("")){
                   strIdEquipo = null; 
                }
                if(strImei.equals("")){
                   strImei = null; 
                }
                if(strSim.equals("")){
                   strSim = null; 
                }
                if(strAlamcen.equals("")){
                   strAlamcen = null; 
                }
                if(strSubInventario.equals("")){
                   strSubInventario = null; 
                }
                if(strOrganitationId.equals("")){
                   strOrganitationId = null; 
                }               
                
                System.out.println( "["+i+"][strIdEquipo][" + strIdEquipo+"]\n"+
                                    "["+i+"][strSim][" + strSim+"]\n"+
                                    "["+i+"][strAlamcen][" + strAlamcen+"]\n"+
                                    "["+i+"][strSubInventario][" + strSubInventario+"]\n"+
                                    "["+i+"][strOrganitationId][" + strOrganitationId+"]\n"
                                   );
                
                Object[] objRequest = { strIdEquipo, strImei, strSim, strOrganitationId, strSubInventario };
                Object[] objRequest2 = { lngRequestolitemid, lngRequestolid,
                                            null, null, null, null, null,
                                            strProductStatus,
                                            null, null, null, null, null, null, null, null, null,
                                            strLogin,
                                            null, null, null, null, null };
                              
                //Object[] objRequest = {       "60", "001700162462910","12345"};
                STRUCT stcRequest = new STRUCT(sdRequest, conn, objRequest);
                STRUCT stcRequest2 = new STRUCT(sdRequest2, conn, objRequest2);
              
              arrRequest.add(stcRequest); 
              arrRequest2.add(stcRequest2); 
              
            }
             ARRAY aryItemAgreement = new ARRAY(adRequest, conn, arrRequest.toArray());
             ARRAY aryItemAgreement2 = new ARRAY(adRequest2, conn, arrRequest2.toArray());
             
            cstmt.setLong(1, lngRequestolid);             
            cstmt.setLong(2, lngReqolstatusid);             
            cstmt.setString(3,strAction);
            cstmt.setString(4,strLogin);
          
            cstmt.setARRAY(5, aryItemAgreement);
            cstmt.setARRAY(6, aryItemAgreement2);
            
            cstmt.registerOutParameter(7, OracleTypes.VARCHAR); 
            
            cstmt.executeUpdate();
            
            msg = cstmt.getString(7);
             
            logger.debug("PITAMOS EL MENSAJE DE SALIDA : "+msg);
            //System.out.println("SALIO REQUESTDAO > saveRequestOl");
             
            objHashMapResultado.put("strMessage",msg);
            
        } catch (SQLException sqle) {
            //logger.error(formatException(sqle));
             System.out.println("error "+sqle.getMessage());
             
        } catch (Exception ex) {
            //logger.error(formatException(sqle));
             System.out.println("DAO error: "+ex.getMessage());
             System.out.println("DAO causado: "+ ex.getCause());
             System.out.println("DAO localize: "+ ex.getLocalizedMessage());             
             ex.printStackTrace();
        }
        
        
        finally {
                                closeObjectsDatabase(conn,cstmt,rs); 
        }
        return objHashMapResultado;              
     }    
    
    public HashMap validateModelCondition2(long lngRequestolid,
                                           String strImei,
                                           String strCondicion,
                                           long lrequestStateCod,
                                           String strAccion)throws SQLException, Exception {
         System.out.println("INICIO RequestDAOTDE validateModelCondition2: lngRequestolid: " + lngRequestolid + ", strImei: " + strImei + ", strCondicion: " + strCondicion + ", lrequestStateCod: " + lrequestStateCod + ", strAccion: " + strAccion);                        
        System.out.println("[AppRepair.REQUESTDAO.validateModelCondition2][Ingreso]");
        
        ResultSet rs=null;
        Connection conn = null;
        OracleCallableStatement cstmt = null;
        String msg=null;

        HashMap   objHashMapResultado = new HashMap();   
        ArrayList arrRequest    = new ArrayList();
        ArrayList arrRequest2    = new ArrayList();
        String sqlStr = "BEGIN INVENTORY.SPI_VAL_MODEL_CONDITION_TDE(?,?,?,?,?,?);END;";
      
      try
        {           
            conn = Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);            
            
                        
            System.out.println("[lngRequestolid][" + lngRequestolid+
                              "]\n[lrequestStateCod]["+lrequestStateCod+
                              "]\n[strAccion]["+strAccion+
                              "]\n[strImei]["+strImei+
                              "]\n[strCondicion]["+strCondicion+"]");
             
            cstmt.setLong(1, lngRequestolid);
            cstmt.setLong(2, lrequestStateCod);
            cstmt.setString(3,strAccion);
            cstmt.setString(4, strImei);             
            cstmt.setString(5,strCondicion);
          
            cstmt.registerOutParameter(6, OracleTypes.VARCHAR); 
            
            cstmt.executeUpdate();
            
            msg = cstmt.getString(6);
             
            logger.debug("PITAMOS EL MENSAJE DE SALIDA : "+msg);
            System.out.println("PITAMOS EL MENSAJE DE SALIDA : "+msg);
             
            objHashMapResultado.put("strMessage",msg);
            
        } catch (SQLException sqle) {
            //logger.error(formatException(sqle));
             System.out.println("error "+sqle.getMessage());
             
        } catch (Exception ex) {
            //logger.error(formatException(sqle));
             System.out.println("DAO error: "+ex.getMessage());
             System.out.println("DAO causado: "+ ex.getCause());
             System.out.println("DAO localize: "+ ex.getLocalizedMessage());             
             ex.printStackTrace();
        }
        
        
        finally {
            closeObjectsDatabase(conn,cstmt,rs); 
        }
        return objHashMapResultado;              
     }
    
    public HashMap updateModelCondition(long lngRequestolid,
                                        String strImei,
                                        String strCondicion,
                                        long lrequestStateCod,
                                        String strAccion,
                                        String strLogin)throws SQLException, Exception {
         System.out.println("INICIO RequestDAOTDE updateModelCondition: lngRequestolid: " + lngRequestolid + ", strImei: " + strImei + ", strCondicion: " + strCondicion + ", lrequestStateCod: " + lrequestStateCod + ", strAccion: " + strAccion);                                                
        System.out.println("[AppRepair.REQUESTDAO.updateModelCondition][Ingreso]");
        
        ResultSet rs=null;
        Connection conn = null;
        OracleCallableStatement cstmt = null;
        String msg=null;

        HashMap   objHashMapResultado = new HashMap();   
        ArrayList arrRequest    = new ArrayList();
        ArrayList arrRequest2    = new ArrayList();
        String sqlStr = "BEGIN INVENTORY.SPI_UPDATE_MODEL_CONDITION_TDE(?,?,?,?,?,?,?);END;";
      
      try
        {           
            conn = Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);            
            
                        
            System.out.println("[lngRequestolid][" + lngRequestolid+
                              "]\n[lrequestStateCod]["+lrequestStateCod+
                              "]\n[strAccion]["+strAccion+
                              "]\n[strImei]["+strImei+
                              "]\n[strCondicion]["+strCondicion+                               
                              "]\n[strLogin]["+strLogin+"]");
             
            cstmt.setLong(1, lngRequestolid);
            cstmt.setLong(2, lrequestStateCod);
            cstmt.setString(3,strAccion);
            cstmt.setString(4, strImei);             
            cstmt.setString(5,strCondicion);
            cstmt.setString(6,strLogin);
            
            cstmt.registerOutParameter(7, OracleTypes.VARCHAR); 
            
            cstmt.executeUpdate();
            
            msg = cstmt.getString(7);
             
            logger.debug("PITAMOS EL MENSAJE DE SALIDA : "+msg);
            System.out.println("PITAMOS EL MENSAJE DE SALIDA : "+msg);
             
            objHashMapResultado.put("strMessage",msg);
            
        } catch (SQLException sqle) {
            //logger.error(formatException(sqle));
             System.out.println("error "+sqle.getMessage());
             
        } catch (Exception ex) {
            //logger.error(formatException(sqle));
             System.out.println("DAO error: "+ex.getMessage());
             System.out.println("DAO causado: "+ ex.getCause());
             System.out.println("DAO localize: "+ ex.getLocalizedMessage());             
             ex.printStackTrace();
        }
        
        
        finally {
            closeObjectsDatabase(conn,cstmt,rs); 
        }
        return objHashMapResultado;              
     }
    
  
    /**
     * Motivo: Valida que el Sub Inventario devuelto luego de ingresar el IMEI no está configurado como sub-almacén de préstamo.
     * <br>Realizado por: <a href="mailto:jorge.gabriel@teamsoft.com.pe">Jorge Gabriel</a>
     * <br>Fecha: 20/06/2016       
     * @return    String 
     */    
    public String validateSubInventoryCode(String strSubInventoryCode,String numRequest) throws Exception, SQLException {   
        System.out.println("INICIO SEJBRequestBean validateSubInventoryCode: param: strSubInventoryCode: " + strSubInventoryCode + ", numRequest: " + numRequest);
      String strMessage=null;
      OracleCallableStatement cstmt = null;     
      Connection conn = null;
      ResultSet rs = null;
     
      conn = Proveedor.getConnection();
      
      try{
        System.out.println("[RequestDAO - strSubInventoryCode - "+strSubInventoryCode+" - numRequest - "+numRequest+"]");
          
        String strSql = "BEGIN REPAIR.SPI_VALIDATE_SUBINVENTORY_CODE(?,?,?); end;";        
                  
        cstmt = (OracleCallableStatement)conn.prepareCall(strSql);
        cstmt.setString(1, strSubInventoryCode);
        cstmt.setString(2, numRequest);
        cstmt.registerOutParameter(3, Types.VARCHAR);
        cstmt.execute();
        strMessage = cstmt.getString(3);
       
      }catch(Exception e){
          strMessage =e.getMessage();
      }finally{
        try{
          closeObjectsDatabase(conn,cstmt,rs); 
          }catch (Exception e) {
            logger.error(formatException(e));
          }
      }   
      return strMessage;
    }

    /**
     * Motivo: Valida que el modelo del imei que se esta ingresando se encuentre configurado en la matrix de prestamos.
     * <br>Realizado por: <a href="mailto:jorge.gabriel@teamsoft.com.pe">Jorge Gabriel</a>
     * <br>Fecha: 20/06/2016       
     * @return    String 
     */    
     public String validateModelResquested(String strImei, String strNumRequest) throws Exception, SQLException {   
         System.out.println("INICIO RequestDAOTDE validateModelResquested strImei: " + strImei + ", strNumRequest: " + strNumRequest);
       String strMessage=null;
       OracleCallableStatement cstmt = null;     
       Connection conn = null;
       ResultSet rs = null;
      
       conn = Proveedor.getConnection();
       
       try{
         System.out.println("[RequestDAO - strImei - "+strImei+", strNumRequest - "+strNumRequest+"]");
           
         String strSql = "BEGIN REPAIR.SPI_VAL_MODEL_REQUESTED_TDE(?,?,?); END;";       

         cstmt = (OracleCallableStatement)conn.prepareCall(strSql);
         cstmt.setString(1, strImei);
         cstmt.setString(2, strNumRequest);
         cstmt.registerOutParameter(3, Types.VARCHAR);
         cstmt.execute();
                
         strMessage = cstmt.getString(3);

       }catch(Exception e){
           strMessage =e.getMessage();
           e.printStackTrace();
       }finally{
         try{
           closeObjectsDatabase(conn,cstmt,rs); 
           }catch (Exception e) {
             logger.error(formatException(e));
           }
       }   
       return strMessage;
     }

    public int getFlagRequestOlTde(long requestId) throws Exception, SQLException {
        System.out.println("INICIO SEJBRequestBean getFlagRequestOlTde: param: requestId: " + requestId);
        int sReturnValue = 0;
        Connection conn=null;
        OracleCallableStatement cstmt = null;
        try{

            logger.info("requestId         : "+requestId);

            String sqlStr =  " { ? = call  REPAIR.FNC_GET_FLAG_OL_SSTT( ?) } ";

            conn = Proveedor.getConnection();
            cstmt = (OracleCallableStatement)conn.prepareCall(sqlStr);
            cstmt.registerOutParameter(1, Types.INTEGER);

            cstmt.setString(2, ""+requestId);

            cstmt.executeQuery();
            sReturnValue = cstmt.getInt(1);
        }catch(Exception e){
            logger.error(formatException(e));
        }finally{
            try{
                closeObjectsDatabase(conn,cstmt,null);
            }catch (Exception e) {
                logger.error(formatException(e));
            }
        }
		System.out.println("OrderDAO > getFlagRequestOlTde - sReturnValue: "+sReturnValue);
        logger.info("************************** FIN OrderDAO > getFlagRequestOlTde**************************");
        return sReturnValue;
    }

}
