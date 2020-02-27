package pe.com.nextel.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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

import pe.com.nextel.bean.DetailRequestManualBean;
import pe.com.nextel.bean.DominioBean;
import pe.com.nextel.bean.RequestManualBean;
import pe.com.nextel.bean.StockBean;
import pe.com.nextel.util.Constante;
import pe.com.nextel.util.MiUtil;


public class RequestManualDAO extends GenericDAO{
  
  protected static Logger logger = Logger.getLogger(RequestManualDAO.class);
  
  public RequestManualDAO(){}
  
  public HashMap getModalityList() throws SQLException, Exception {
      if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
      
      HashMap hshDataMap = new HashMap();
      List arrModalityList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      String sqlStr = "BEGIN INVENTORY.SPI_GET_CONFIGURATION2(?,?,?); END;";
      try{
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
         cstmt.setString(1, Constante.PARAM_BD_OL_MODALITY);
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
                  dominio.setValor(MiUtil.trimNotNull(rs.getString("npparamn1")));
                  dominio.setDescripcion(MiUtil.trimNotNull(rs.getString("npparamv1")));
               }
               catch(SQLException sqle) {
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(1)));
               }
               arrModalityList.add(dominio);
            }
         }        
         
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
		hshDataMap.put(Constante.SOLICITUD_MANUAL_LISTA_MODALIDAD, arrModalityList);
		hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
        return hshDataMap;
    }
    
    
    public HashMap getEstadoList() throws SQLException, Exception {
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
      String sqlStr = "BEGIN INVENTORY.SPI_GET_CONFIGURATION2(?,?,?); END;";
      
      
      
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
                  dominio.setValor(rs.getString("npparamv1"));
                  dominio.setDescripcion(rs.getString("npparamv1"));
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
		hshDataMap.put(Constante.SOLICITUD_MANUAL_LISTA_ESTADO, arrEstadoList);
		hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
        return hshDataMap;
    }
  
   public HashMap getListaEstadoSolicitud() throws SQLException, Exception {
      if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
      
      System.out.println(" DAO : getListaEstadoSolicitud " );
      
      HashMap hshDataMap = new HashMap();
      List arrEstadoList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      String sqlStr = "BEGIN INVENTORY.SPI_GET_CONFIGURATION2(?,?,?); END;";
      
      
      try{
        System.out.println(" DAO : estadoSolicitud 1  " );
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
         cstmt.setString(1, "OL_STATUS_PRODUCT");
         cstmt.registerOutParameter(2,OracleTypes.VARCHAR);
         cstmt.registerOutParameter(3, OracleTypes.CURSOR);
         cstmt.executeQuery();         
         strMessage = cstmt.getString(2);
         
         System.out.println(" DAO : strMessage = " + strMessage );
         rs = (ResultSet)cstmt.getObject(3);
         
         if (StringUtils.isBlank(strMessage)) {
          
            while (rs.next()) {
              //System.out.println(" DAO : Entro al bucle"  );
               DominioBean dominio = new DominioBean();
               int i = 1;               
               try {
                  dominio.setValor(MiUtil.trimNotNull(rs.getString("npparamv1"))); 
                  dominio.setDescripcion(MiUtil.trimNotNull(rs.getString("npparamv1")));
                  
                  System.out.println("npparamn1 = " + rs.getString("npparamn1"));
                  System.out.println("npparamv1 = " + rs.getString("npparamv1"));
                  System.out.println("npstatus = " + rs.getString("npstatus"));
                  
                  arrEstadoList.add(dominio);
                  
               }
               catch(SQLException sqle) {
                System.out.println("ERROR: sqle = " + sqle.getMessage());
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(1)));
               }
               
            }
         }         
         
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
      System.out.println(" DAO : arrEstadoList.size() = " + arrEstadoList.size() );
		hshDataMap.put(Constante.SOLICITUD_MANUAL_LISTA_ESTADO, arrEstadoList);
		hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
        return hshDataMap;
    }
  
  
  public HashMap getAccesoriosList() throws SQLException, Exception {
      if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
      
      HashMap hshDataMap = new HashMap();
      List arrAccesoriosList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      
      String sqlStr = "BEGIN INVENTORY.SPI_GET_CONFIGURATION2(?,?,?); END;";
      try{
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
         cstmt.setString(1, Constante.PARAM_BD_OL_NPACCESSORY);
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
                  dominio.setValor(rs.getString("npparamv1"));
                  dominio.setDescripcion(rs.getString("npparamv1"));
               }
               catch(SQLException sqle) {
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(1)));
               }
               arrAccesoriosList.add(dominio);
            }
         }         
         
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
      hshDataMap.put(Constante.SOLICITUD_MANUAL_LISTA_ACCESORIOS, arrAccesoriosList);
      hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
      return hshDataMap;
    }
    
    
    
    //Falata publicar el packte -- ERROR
    public HashMap getTipoDocumentoList() throws SQLException, Exception {
      if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
      
      HashMap hshDataMap = new HashMap();
      List arrTipoDocumentoList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      
      String sqlStr = "BEGIN INVOICE.NP_DOC_NUMERATION_PKG.SP_GET_TYPEDOC(?,?,?); END;";
      try{
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
         cstmt.setInt(1, 4);
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
                  dominio.setValor(rs.getString(1));
                  dominio.setDescripcion(rs.getString(2));
               }
               catch(SQLException sqle) {
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(1)));
               }
               arrTipoDocumentoList.add(dominio);
            }
         }         
         
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
      hshDataMap.put(Constante.SOLICITUD_MANUAL_LISTA_TIPO_DOCUMENTO, arrTipoDocumentoList);
      hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
      return hshDataMap;
    }
    
    
    public HashMap getSolucionNegocioList() throws SQLException, Exception {
      if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
      
      HashMap hshDataMap = new HashMap();
      List arrSolucionNegocioList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      
      String sqlStr = "BEGIN PRODUCT.SPI_GET_SOLUTION_LST2(?,?,?); END;";
      try{
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
         cstmt.setInt(1, 1);
         cstmt.registerOutParameter(2,OracleTypes.VARCHAR);
         cstmt.registerOutParameter(3,OracleTypes.CURSOR);
         cstmt.executeQuery();     
          
         strMessage = cstmt.getString(2);
         
         rs = (ResultSet)cstmt.getObject(3);
         
         if (StringUtils.isBlank(strMessage)) {
            while (rs.next()) {
               DominioBean dominio = new DominioBean();
               int i = 1;               
               try {
                  dominio.setValor(rs.getString(1));
                  dominio.setDescripcion(rs.getString(2));
               }
               catch(SQLException sqle) {
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(1)));
               }
               arrSolucionNegocioList.add(dominio);
            }
         }         
         
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
      hshDataMap.put(Constante.SOLICITUD_MANUAL_LISTA_SOLUCION_NEGOCIO, arrSolucionNegocioList);
      hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
      return hshDataMap;
    }
    
    
    public HashMap getLineaProductoListByIdsolucion(DominioBean solucionNegocio) throws SQLException, Exception {
      if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
      System.out.println("EDU: >>> getLineaProductoList <<< ");
      HashMap hshDataMap = new HashMap();
      List arrLineaProductoList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      
      String sqlStr = "BEGIN PRODUCT.SPI_GET_PRODUCTLINE_SOLUTION(?,?,?); END;";
      try{
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);         
         cstmt.setInt(1, MiUtil.parseInt(solucionNegocio.getValor()));
         cstmt.registerOutParameter(2,OracleTypes.CURSOR);
         cstmt.registerOutParameter(3,OracleTypes.VARCHAR);
         cstmt.executeQuery();     
          
         strMessage = cstmt.getString(3);
         System.out.println("EDU: strMessage = "+ strMessage );
         
         rs = (ResultSet)cstmt.getObject(2);
         
         // EDU: borrar
         System.out.println("EDU: ----rs.getMetaData().getColumnCount()= " +rs.getMetaData().getColumnCount());
         for (int x=1;x<=rs.getMetaData().getColumnCount();x++){
            System.out.println(rs.getMetaData().getColumnName(x)+ " "+ rs.getMetaData().getColumnTypeName(x));
            //System.out.println(x);
          }          
         
         if (StringUtils.isBlank(strMessage)) {
            while (rs.next()) {
               DominioBean dominio = new DominioBean();
                      
               try {
                  dominio.setValor(rs.getString(2));
                  dominio.setDescripcion(rs.getString(3));                  
                  
               }
               catch(SQLException sqle) {
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(1)));
               }
               arrLineaProductoList.add(dominio);
            }
         }         
         
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
      hshDataMap.put(Constante.SOLICITUD_MANUAL_LISTA_LINEA_PRODUCTO, arrLineaProductoList);
      hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
      return hshDataMap;
      
      
      
    }
    
    
    
    
    public HashMap getProductoListByLineaproductoid(DominioBean dominioBean ) throws SQLException, Exception {
      if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
      
      System.out.println("DAO:.... getProductoListByLineaproductoid ....");
      
      HashMap hshDataMap = new HashMap();
      List arrProductoList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      
      //String sqlStr = "BEGIN PRODUCT.SPI_GET_PRODUCT_LST1(?,?,?); END;";
      String sqlStr = "BEGIN PRODUCT.SPI_GET_PRODUCTO_FILTER(?,?,?,?); END;";
      
      /*
      an_npproductlineid    IN NUMBER,
      an_npmodality         IN NUMBER,      
      ac_list               OUT NP_TYPES_PKG.TYPCUR,
      av_message            OUT VARCHAR2 */

      String idLineaProducto = dominioBean.getParam1();
      String idModalidad = dominioBean.getParam2();
      try{
        System.out.println("DAO: idLineaProducto = " + idLineaProducto);
        System.out.println("DAO: idModalidad = " + idModalidad);
      
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
         cstmt.setInt(1, MiUtil.parseInt(idLineaProducto));
         cstmt.setInt(2, MiUtil.parseInt(idModalidad));
         cstmt.registerOutParameter(3,OracleTypes.CURSOR);
         cstmt.registerOutParameter(4,OracleTypes.VARCHAR);
         
         cstmt.executeQuery();     
          
         strMessage = cstmt.getString(4);
         System.out.println("EDU: strMessage = "+ strMessage );
         rs = (ResultSet)cstmt.getObject(3);
         
         /*System.out.println("EDU: rs.getMetaData().getColumnCount()= " +rs.getMetaData().getColumnCount());
         for (int x=1;x<=rs.getMetaData().getColumnCount();x++){
            System.out.println(rs.getMetaData().getColumnName(x)+ " "+ rs.getMetaData().getColumnTypeName(x));
            //System.out.println(x);
          }*/
         
         if (StringUtils.isBlank(strMessage)) {
            while (rs.next()) {
              
               //System.out.println("EDU - DAO: entro al bucle");
              
               DominioBean dominio = new DominioBean();
                        
               try {
                  dominio.setValor(String.valueOf(rs.getInt("npproductid")));
                  dominio.setDescripcion(rs.getString("npname"));
                  arrProductoList.add(dominio);
               }
               catch(SQLException sqle) {
                  System.out.println("ERROR: sqle = " + sqle.getMessage());
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(1)));
               }
               
            }
         }         
         
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
      System.out.println("DAO: arrProductoList.size() = " + arrProductoList.size());
      hshDataMap.put(Constante.SOLICITUD_MANUAL_LISTA_PRODUCTO, arrProductoList);
      hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
      return hshDataMap;
    }
  
  
  public HashMap getMotivosListByUserLogin(String userLogin) throws SQLException, Exception {
      if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
      
      System.out.println("EDU: DAO getMotivosListByUserLogin");
      System.out.println("EDU: DAO - userLogin = " + userLogin);
      
      HashMap hshDataMap = new HashMap();
      List arrMotivosList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      
      String sqlStr = "BEGIN INVENTORY.SPI_GET_NP_REASONS_LIST(?,?,?); END;";
      try{
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
         cstmt.setString(1, userLogin);
         cstmt.registerOutParameter(2,OracleTypes.CURSOR);
         cstmt.registerOutParameter(3,OracleTypes.VARCHAR);
         cstmt.executeQuery();     
          
         strMessage = cstmt.getString(3);
         System.out.println("EDU: strMessage = "+ strMessage );
         rs = (ResultSet)cstmt.getObject(2);
                  
         
         if (StringUtils.isBlank(strMessage)) {
            
            while (rs.next()) {
               DominioBean dominio = new DominioBean();
               
               int i = 1;               
               try {
                  dominio.setValor(String.valueOf(rs.getInt(1)));
                  dominio.setDescripcion(rs.getString(2));
               }
               catch(SQLException sqle) {
                  System.out.println("ERROR: getMotivosListByUserLogin - sqle= " + sqle.getMessage());
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(1)));
               }
               arrMotivosList.add(dominio);
            }
         }         
         
      }
      catch(Exception e){
         System.out.println("ERROR: getMotivosListByUserLogin - e= " + e.getMessage());
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
      hshDataMap.put(Constante.SOLICITUD_MANUAL_LISTA_MOTIVOS, arrMotivosList);
      hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
      return hshDataMap;
    }
  
  
    public HashMap saveRequestManual(RequestManualBean requestManualBean, List listaDetailRequestManual )throws SQLException, Exception {
                                
        System.out.println("Entro RequestManualDAO > saveRequestManual");
        
        ResultSet rs=null;
        Connection conn = null;
        OracleCallableStatement cstmt = null;
        String strMessage = null;
        int requestId = 0;
        String requestNumber = "";
        
        RequestManualBean objRequestManualBean = new RequestManualBean();
                 
        HashMap   objHashMapResultado = new HashMap();   
        
        ArrayList arrRequest    = new ArrayList();
        List arrDetailRequest = new ArrayList();
        String sqlStr = "BEGIN INVENTORY.SPI_INS_REQUEST_OL(?,?,?,?,?);END;";
        //conn = Proveedor.getConnection();
        
      try
        {           
            conn = Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
            
            StructDescriptor sdRequest = StructDescriptor.createDescriptor("INVENTORY.TO_NP_REQUEST_OL", conn);
            ArrayDescriptor  adRequest = ArrayDescriptor.createDescriptor("INVENTORY.TT_NP_REQUEST_OL", conn);
            
            StructDescriptor sdDetailRequest = StructDescriptor.createDescriptor("INVENTORY.TO_NP_REQUEST_OL_ITEM", conn);
            ArrayDescriptor  adDetailRequest = ArrayDescriptor.createDescriptor("INVENTORY.TT_NP_REQUEST_OL_ITEM", conn);
                    
             
              System.out.println(" String.valueOf(requestManualBean.getBuildingId()) = " +  String.valueOf(requestManualBean.getBuildingId()));
              System.out.println("requestManual.getOrdenNumber() = " + requestManualBean.getOrdenNumber());
              System.out.println("requestManual.getCod() = " + requestManualBean.getCod());
              System.out.println("requestManual.getUser() = " + requestManualBean.getUser());
              System.out.println("requestManual.getRazonSolcial() = " + requestManualBean.getRazonSolcial());
              
              String strBuilding = String.valueOf(requestManualBean.getBuildingId());
              
              String strOrdenNumber = MiUtil.trimNotNull(requestManualBean.getOrdenNumber());
              String strCodigo = MiUtil.trimNotNull(requestManualBean.getCod());
              String strUser = MiUtil.trimNotNull(requestManualBean.getUser());
              String strRazonSocial = MiUtil.trimNotNull(requestManualBean.getRazonSolcial());
              
              if(strOrdenNumber.equals("")){
                  strOrdenNumber = null;
              }
              if(strCodigo.equals("")){
                  strCodigo = null;
              }
              if(strUser.equals("")){
                  strUser = null;
              }
              if(strRazonSocial.equals("")){
                  strRazonSocial = null;
              }
                          
              
              Object[] objRequest = {	
                                    null,
                                    null,
                                    strBuilding, //"22", 
                                    strOrdenNumber, //"7316364",
                                    strCodigo, //"19043",
                                    null,
                                    null,
                                    null,
                                    null,
                                    "Manual",
                                    null,
                                    null,
                                    strUser, //"MMILLA", 
                                    null,
                                    null,
                                    null,
                                    null,
                                    strRazonSocial, //"CLIENTE DE PRUEBA CSC", 
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null 
                      };
              
              System.out.println("EDU: DAO P1");
                   
              STRUCT stcRequest = new STRUCT(sdRequest, conn, objRequest);
              arrRequest.add(stcRequest); 
              
              System.out.println("EDU: DAO P2");

            
             ARRAY aryRequestAgreement = new ARRAY(adRequest, conn, arrRequest.toArray());
             
             System.out.println("EDU: DAO P3");
                                       
             //--------------------------
                          
             for(int i=0; i<listaDetailRequestManual.size(); i++){ 
              //HashMap objHashMap = (HashMap)lstItemDevice.get(i);
              DetailRequestManualBean detailRequestManual = (DetailRequestManualBean)listaDetailRequestManual.get(i); 
              
              int indice = i + 1;
              System.out.println(" ------------------");
              System.out.println(" indice = " + indice);
              System.out.println(" ACCESORIOS = ACCESORIOS");
              
              String strProductCode = MiUtil.trimNotNull(detailRequestManual.getProductCode());
              String strQuantity = MiUtil.trimNotNull(detailRequestManual.getQuantity());
              String strModeCode = MiUtil.trimNotNull(detailRequestManual.getModeCode());
              String strStatusCode = MiUtil.trimNotNull(detailRequestManual.getStatusCode());
              String strReplacementCode = MiUtil.trimNotNull(detailRequestManual.getReplacementCode());
              String strAccesoryCode = MiUtil.trimNotNull(detailRequestManual.getAccesoryCode());              
              String strReasonCode = MiUtil.trimNotNull(detailRequestManual.getReasonCode());
              String strBusinessSolucionCode = MiUtil.trimNotNull(detailRequestManual.getBusinessSolucionCode());
              String strProducLineCode = MiUtil.trimNotNull(detailRequestManual.getProductLineCode());
                            
              System.out.println(" detailRequestManual.getProductCode() = " + detailRequestManual.getProductCode());
              System.out.println(" detailRequestManual.getQuantity() = " + detailRequestManual.getQuantity());              
              System.out.println(" detailRequestManual.getModeCode() = " + detailRequestManual.getModeCode());
              System.out.println(" detailRequestManual.getStatusCode() = " + detailRequestManual.getStatusCode());
              System.out.println(" detailRequestManual.getAccesoryCode() = " + detailRequestManual.getAccesoryCode());
              System.out.println(" detailRequestManual.getReplacementCode() = " + detailRequestManual.getReplacementCode());              
              System.out.println(" detailRequestManual.getReasonCode() = " + detailRequestManual.getReasonCode()); 
              System.out.println(" detailRequestManual.getBusinessSolucionCode() = " + detailRequestManual.getBusinessSolucionCode()); 
              System.out.println(" detailRequestManual.getProductLineCode() = " + detailRequestManual.getProductLineCode()); 
              
              if(strProductCode.equals("")){
                strProductCode= null; 
              }
              if(strQuantity.equals("")){
                strQuantity= null; 
              }
              if(strModeCode.equals("")){
                strModeCode= null; 
              }
              if(strStatusCode.equals("")){
                strStatusCode= null; 
              }
              if(strAccesoryCode.equals("")){
                strAccesoryCode= null; 
              }
              if(strReasonCode.equals("")){
                strReasonCode= null; 
              }
              if(strBusinessSolucionCode.equals("")){
                strBusinessSolucionCode= null; 
              }
              if(strReplacementCode.equals("")){
                strReplacementCode= null; 
              }       
              /*if(strProducLineCode.equals("")){
                strProducLineCode= null; 
              }*/
              
              
              
              System.out.println(" strProductCode = " + strProductCode);
              System.out.println(" strQuantity = " + strQuantity);
              System.out.println(" strModeCode = " + strModeCode);
              System.out.println(" strAccesoryCode = " + strAccesoryCode);
              System.out.println(" strReasonCode = " + strReasonCode);
              System.out.println(" strBusinessSolucionCode = " + strBusinessSolucionCode);
              System.out.println(" strReplacementCode = " + strReplacementCode);
              System.out.println(" strProducLineCode = " + strProducLineCode);
              
              System.out.println(" Hasta aqui bien");
              
              String[] lineasProducto = {"2","202","228","193"};
              
              for(int k =0; k<lineasProducto.length ; k++){
                                              //2
                  if(lineasProducto[k].equals(strProducLineCode)){
                      strProductCode = detailRequestManual.getProductCode();
                      break;
                  }else{
                    strProductCode = strReplacementCode;
                  }
              }
              
              System.out.println("Nuevo: strProductCode = " + strProductCode);
              
              
              Object[] objDetailRequest = {	
                                    null,
                                    null,
                                    String.valueOf(indice),//"1",//
                                    "ACCESORIOS", //null
                                    strProductCode, //"100453",//
                                    strQuantity,  //"1",// 
                                    strModeCode,  //"VENTA",//
                                    strStatusCode,  //"NUEVOS",//
                                    strAccesoryCode,  //"0",//Accesorio/Repuesto
                                    null, //"82",//null
                                    null, //"ACCE",//
                                    strReasonCode,  //"18",//
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,//,
                                    strBusinessSolucionCode//"3"//
              };
                          
              STRUCT stcDetailRequest = new STRUCT(sdDetailRequest, conn, objDetailRequest);
              arrDetailRequest.add(stcDetailRequest); 

            }
             ARRAY aryDetailRequestAgreement = new ARRAY(adDetailRequest, conn, arrDetailRequest.toArray());
                          
             //-------------------------------
             
            cstmt.setARRAY(1, aryRequestAgreement);
            cstmt.setARRAY(2, aryDetailRequestAgreement);
            
            cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(4, OracleTypes.NUMBER);
            cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
                                   
            cstmt.executeUpdate();
            
            strMessage = cstmt.getString(3);
            requestId = cstmt.getInt(4);
            requestNumber = cstmt.getString(5);
            
            objRequestManualBean.setRequestId(requestId);
            objRequestManualBean.setRequestNumber(requestNumber);  
            
            System.out.println("DAO strMessage = " + strMessage);
            System.out.println("DAO requestId = " + requestId);
            System.out.println("DAO requestNumber = " + requestNumber);
            
             
               } catch (SQLException sqle) { 
               
             System.out.println("error "+sqle.getMessage());
             
            
        } finally {
				closeObjectsDatabase(conn,cstmt,rs); 
        }
        
        objHashMapResultado.put(Constante.REQUEST_MANUAL_BEAN, objRequestManualBean);
        objHashMapResultado.put(Constante.MESSAGE_OUTPUT, strMessage);
        return objHashMapResultado;
       
     }
     
     
     
     public HashMap getRepuestosList(DominioBean dominioBean) throws SQLException, Exception {
        
        if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
      
      System.out.println("... getRepuestosList ...");
      
      HashMap hshDataMap = new HashMap();
      List arrRespuestosList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      
      //String sqlStr = "BEGIN PRODUCT.SPI_GET_PRODUCTLINE_SOLUTION(?,?,?); END;";
      String sqlStr = "BEGIN PRODUCT.SPI_GET_PROD_ACCE_FILTER(?,?,?,?,?); END;";
      
      try{
         
         System.out.println("dominioBean.getParam1() = "+dominioBean.getParam1());
         System.out.println("dominioBean.getParam2() = "+dominioBean.getParam2());
         System.out.println("dominioBean.getParam3() = "+dominioBean.getParam3());
         
         String idProducto = MiUtil.trimNotNull(dominioBean.getParam1());
         String idLineaProducto = MiUtil.trimNotNull(dominioBean.getParam2());
         String idModalida = MiUtil.trimNotNull(dominioBean.getParam3());
         
         if(idProducto.equals("")){
            idProducto = null;
         }
         if(idLineaProducto.equals("")){
            idLineaProducto = null;
         }
         if(idModalida.equals("")){
            idModalida = null;
         }        
         
         System.out.println("idProducto = " + idProducto);
         System.out.println("idLineaProducto = " + idLineaProducto);
         System.out.println("idModalidad = " + idModalida);
         
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
         
         cstmt.setString(1, idProducto);// ID Producto
         cstmt.setInt(2, Integer.parseInt(idLineaProducto) );// ID Solucion de Negocio
         cstmt.setInt(3, Integer.parseInt(idModalida));// ID Modalidad
         
         cstmt.registerOutParameter(4,OracleTypes.CURSOR);
         cstmt.registerOutParameter(5,OracleTypes.VARCHAR);
         
         cstmt.executeQuery();     
          
         strMessage = cstmt.getString(5);
         //System.out.println("getRepuestosList - strMessage: "+ strMessage );
         
                          
         
         System.out.println("DAO: paso");
         
         if (StringUtils.isBlank(strMessage)) {
             
            rs = (ResultSet)cstmt.getObject(4); 
            
            //System.out.println("DAO: entro al if");
            //System.out.println("DAO: rs=  " + rs);
            
            while (rs.next()) {
               //System.out.println("DAO: entro al while"); 
               DominioBean dominio = new DominioBean();
               int i = 1;               
              // try {
                  dominio.setValor(rs.getString("npproductid"));
                  dominio.setDescripcion(rs.getString("npname"));
                  arrRespuestosList.add(dominio);
                  //System.out.println("DAO: cargo registro"); 
               //}
               /*catch(SQLException sqle) {
                  System.out.println("Error: sqle="+ sqle.getMessage());
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(1)));
               }*/
               
            }
         }
         
         
         
      }
      catch(Exception e){
         System.out.println("DAO: ERROR- getRepuestosList: " + e.getMessage());  
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
      
      System.out.println("getRepuestosList - arrRespuestosList.size(): "+ arrRespuestosList.size() );
      
      hshDataMap.put(Constante.SOLICITUD_MANUAL_LISTA_REPUESTOS, arrRespuestosList);
      hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
      return hshDataMap;
           
     }
     
     
     
     
     
     public StockBean validarStock(StockBean stockBean) throws SQLException, Exception {
        if(logger.isDebugEnabled()){
          logger.debug("--Inicio--");
        }
      
        System.out.println("DAO ----validarStock----");        
      
        HashMap hshDataMap = new HashMap();
        List arrMotivosList = new ArrayList();
        Connection conn = null;
        OracleCallableStatement cstmt = null;
        ResultSet rs = null;
        //DbmsOutput dbmsOutput = null;
        String strMessage = null;
      
        String sqlStr = "BEGIN ORDERS.SPI_GET_STOCK_MESSAGE(?,?,?,?,?,?,?,?,?); END;";
        try{
             conn = Proveedor.getConnection();
             cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
             
             if(stockBean.getAn_specificationid() == null || Constante.BLANK_STRING.equals(stockBean.getAn_specificationid())){
                  cstmt.setNull(1, OracleTypes.NUMBER);
             }
             else{        
                  cstmt.setInt(1, Integer.parseInt(stockBean.getAn_specificationid()));
             }
             
             if(stockBean.getAn_productid() == null || Constante.BLANK_STRING.equals(stockBean.getAn_productid())){
                  cstmt.setNull(2, OracleTypes.NUMBER);
             }
             else{        
                  cstmt.setInt(2, Integer.parseInt(stockBean.getAn_productid()));
             }
             
             if(stockBean.getAn_npdispatchplaceid() == null || Constante.BLANK_STRING.equals(stockBean.getAn_npdispatchplaceid())){
                  cstmt.setNull(3, OracleTypes.NUMBER);
             }
             else{        
                  cstmt.setInt(3, Integer.parseInt(stockBean.getAn_npdispatchplaceid()));
             }
             
             if(stockBean.getAv_organizationoperation() == null || Constante.BLANK_STRING.equals(stockBean.getAv_organizationoperation())){
                  cstmt.setNull(4, OracleTypes.VARCHAR);
             }
             else{        
                  cstmt.setString(4, stockBean.getAv_organizationoperation());
             }
             
             if(stockBean.getAn_salesstructporigenid() == null || Constante.BLANK_STRING.equals(stockBean.getAn_salesstructporigenid())){
                  cstmt.setNull(5, OracleTypes.NUMBER);
             }
             else{        
                  cstmt.setInt(5, Integer.parseInt(stockBean.getAn_salesstructporigenid()));
             }
             
             if(stockBean.getAv_pro_status() == null || Constante.BLANK_STRING.equals(stockBean.getAv_pro_status())){
                  cstmt.setNull(6, OracleTypes.VARCHAR);
             }
             else{        
                  cstmt.setString(6, stockBean.getAv_pro_status());
             }
                          
             cstmt.registerOutParameter(7,OracleTypes.VARCHAR);
             cstmt.registerOutParameter(8,OracleTypes.VARCHAR);
             cstmt.registerOutParameter(9,OracleTypes.VARCHAR);
             cstmt.executeQuery();     
              
             if(cstmt.getString(7) != null){
                stockBean.setAv_message_stock(cstmt.getString(7));              
             } else{
                stockBean.setAv_message_stock("");
             }
             
             if(cstmt.getString(8) != null){
                stockBean.setAv_flag_stock(cstmt.getString(8));              
             } else{
                stockBean.setAv_flag_stock("");
             }
             
             if(cstmt.getString(9) != null){
                stockBean.setAv_message(cstmt.getString(9));              
             } else{
                stockBean.setAv_message("");
             }
            
             System.out.println("av_message_stock: " + stockBean.getAv_message_stock());
             System.out.println("av_flag_stock: " + stockBean.getAv_flag_stock());
             System.out.println("av_message: " + stockBean.getAv_message());
             
        }
        catch(Exception e){
           stockBean.setAv_message(e.getMessage());
           System.out.println("ERROR - validarStock: " + e.getMessage());           
           throw new Exception(e);
        }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
      
      return stockBean;
    }
     
     
     /**
     * Obtiene los flag que indica si los combos productos y accesorios de la pagina de creación de la solicitud
     * se van ha validar.
     * @throws java.lang.Exception
     * @throws java.sql.SQLException
     * @return Un objeto DominioBean
     * <ul>
     *    <li>DominioBean.getFlagValidaProducto(): 1 = Si valido / 0 = No valido nada </li>
     *    <li>DominioBean.getFlFlagValidaAccesorio(): 1 = Si valido / 0 = No valido nada </li>
     * </ul>
     * @param lineaProducto Es el codigo de linea de produccto.
     */
     public HashMap obtenerFlagValidarProductosAndAccesorio(int lineaProducto) throws SQLException, Exception {
        if(logger.isDebugEnabled()){
          logger.debug("--Inicio--");
        }
      
        System.out.println("DAO ----obtenerFlagValidarProductosAndAccesorio----");      
        System.out.println("lineaProducto = " + lineaProducto);
        
        DominioBean dominioBean = new DominioBean();
        HashMap mapaData = new HashMap();
        List arrMotivosList = new ArrayList();
        Connection conn = null;
        OracleCallableStatement cstmt = null;
        ResultSet rs = null;
        String strMessage = null;
      
        String sqlStr = "BEGIN INVENTORY.NP_INVENTORY_REQUEST01_OL_PKG.SP_VALIDATE_PROD_ACCE(?,?,?,?); END;";
        try{
             conn = Proveedor.getConnection();
             cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
                                  
             cstmt.setInt(1, lineaProducto);
             cstmt.registerOutParameter(2,OracleTypes.VARCHAR);
             cstmt.registerOutParameter(3,OracleTypes.VARCHAR);
             cstmt.registerOutParameter(4,OracleTypes.VARCHAR);
             
             cstmt.executeQuery();     
             strMessage = cstmt.getString(4); 
             
             if (Constante.CADENA_VACIA.equals(MiUtil.trimNotNull(strMessage))) {
                  dominioBean.setFlagValidaProducto(cstmt.getString(2));
                  dominioBean.setFlagValidaAccesorio(cstmt.getString(3));
             }
             
             System.out.println("FlagValidaProducto = " + dominioBean.getFlagValidaProducto());
             System.out.println("FlagValidaAccesorio = " + dominioBean.getFlagValidaAccesorio());
             
        }
        catch(Exception e){
           strMessage = e.getMessage();
           System.out.println("ERROR - obtenerFlagValidarProductosAndAccesorio: " + e.getMessage());           
           throw new Exception(e);
        }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
      
      mapaData.put(Constante.DOMINIOBEAN,dominioBean);
      mapaData.put("MESSAGE_OUTPUT",strMessage);
      return mapaData;
      
    }
 
  
  
}