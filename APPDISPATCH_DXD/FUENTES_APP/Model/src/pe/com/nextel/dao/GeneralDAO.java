package pe.com.nextel.dao;

import java.lang.Exception;
import java.lang.String;
import java.lang.System;

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
import oracle.sql.NUMBER;
import oracle.sql.STRUCT;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pe.com.nextel.bean.DominioBean;
import pe.com.nextel.bean.TableBean;
import pe.com.nextel.util.Constante;
import pe.com.nextel.util.MiUtil;


public class GeneralDAO extends GenericDAO
{

 protected static Logger logger = Logger.getLogger(GeneralDAO.class);
 
    /**
     * Motivo: Obtiene el npvalue y el npvaluedesc según el np_table
     * Realizado Por : Alberto Quineche
     * @param  strTableName
     * @return hshDataMap
    **/
    public HashMap getValueNpTable(String strTableName) throws SQLException, Exception {
    
      HashMap hshDataMap = new HashMap();
      ArrayList arrNpTable = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;		
      TableBean nptableBean = null;
      ResultSet rs = null;
		//DbmsOutput dbmsOutput = null;
		String strMessage = null;
		String sqlStr = "BEGIN SWBAPPS.SPI_GET_VALUEDESC_X_NPTABLE(?,?,?); END;";
      try{
         conn = Proveedor.getConnection();
         //dbmsOutput = new DbmsOutput(conn);
         //dbmsOutput.enable(1000000);		
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);		
         cstmt.setString(1,strTableName);		
         cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
         cstmt.registerOutParameter(3, OracleTypes.CURSOR);		
         cstmt.executeQuery();
         //dbmsOutput.show();
         //dbmsOutput.close();
         strMessage = cstmt.getString(2);
         System.out.println("strTableName:"+strTableName);
         if (StringUtils.isBlank(strMessage)) {	
            rs = (ResultSet)cstmt.getObject(3);      
            while (rs.next()) {
                DominioBean objDominioBean = new DominioBean();        
                objDominioBean.setValor(MiUtil.trimNotNull(rs.getString("npvalue")));
                objDominioBean.setDescripcion(MiUtil.trimNotNull(rs.getString("npvaluedesc")));
                arrNpTable.add(objDominioBean);		                
            }
         }
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
         closeObjectsDatabase(conn, cstmt, rs);
      }
      hshDataMap.put("objArrayList", arrNpTable);
      hshDataMap.put("strMessage", strMessage);		
         
      return hshDataMap;
    }  
         
        
    /**
     * Motivo: Obtiene la lista de detalles
     * Realizado Por : Alberto Quineche
     * @param  strTableName
     * @param  intState
     * @return hshDataMap
     **/
    
     public HashMap getActionDetail(String strParam , int intState ) throws SQLException, Exception {

    logger.debug("===============General DAO : getActionDetail ==================");
    logger.debug("Parametro : "+strParam);
		HashMap hshDataMap = new HashMap();
    logger.debug("===============1 ==================");
		ArrayList arrNpTable = new ArrayList();
		Connection conn = null;
		OracleCallableStatement cstmt = null;		
    TableBean nptableBean = null;
		ResultSet rs = null;
		//DbmsOutput dbmsOutput = null;
		String strMessage = null;
    logger.debug("Parametro : "+strParam);
    logger.debug("State : "+intState);
     logger.debug("===============2 ==================");
		String sqlStr = "BEGIN INVENTORY.SPI_GET_CONFIGURATION1(?,?,?,?); END;";
      try{
         conn = Proveedor.getConnection();
          logger.debug("===============3 ==================");
         //dbmsOutput = new DbmsOutput(conn);
         //dbmsOutput.enable(1000000);		
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);	
          logger.debug("===============4 ==================");
         cstmt.setString(1,strParam);	
          logger.debug("===============5 ==================");
         cstmt.setInt(2,intState);
          logger.debug("===============6 ==================");
         cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
          logger.debug("===============7 ==================");
         cstmt.registerOutParameter(4, OracleTypes.CURSOR);		
          logger.debug("===============8 ==================");
         cstmt.executeQuery();
          logger.debug("===============9==================");
         //dbmsOutput.show();
         //dbmsOutput.close();
         strMessage = cstmt.getString(3);
          logger.debug("===============10 ==================");
         if (StringUtils.isBlank(strMessage)) {	
            rs = (ResultSet)cstmt.getObject(4);    
             logger.debug("===============11 ==================");
            while (rs.next()) {
             logger.debug("===============12 ==================");
              DominioBean objDominioBean = new DominioBean();
        
               System.out.println("rs.getString('npparamn1')" + rs.getString("npparamn1"));
               System.out.println("rs.getString('npparamv1')" + rs.getString("npparamv1"));
                  
               objDominioBean.setValor(MiUtil.trimNotNull(rs.getString("npparamv1")));
               objDominioBean.setDescripcion(MiUtil.trimNotNull(rs.getString("npparamv1")));
                 logger.debug("VALOR : "+objDominioBean.getValor());
                 logger.debug("DESCRIPCION : "+objDominioBean.getDescripcion());
               arrNpTable.add(objDominioBean);		                
            }
         }
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
         closeObjectsDatabase(conn, cstmt, rs);
      }
      hshDataMap.put("objArrayList", arrNpTable);
      hshDataMap.put("strMessage", strMessage);		
         
      return hshDataMap;
    }  


  /**
     * Motivo: Obtiene la lista de los motivos
     * Realizado Por : Alberto Quineche
     * @param  strParam
     * @param  intState
     * @return hshDataMap
  **/
    
     public HashMap getReasonDetail(String strParam, int intState) throws SQLException, Exception {

    logger.debug("===============General DAO : getActionDetail ==================");
    logger.debug("Parametro : "+strParam);
		HashMap hshDataMap = new HashMap();
		ArrayList arrNpTable = new ArrayList();
		Connection conn = null;
		OracleCallableStatement cstmt = null;		
    TableBean nptableBean = null;
		ResultSet rs = null;
		//DbmsOutput dbmsOutput = null;
		String strMessage = null;
		String sqlStr = "BEGIN INVENTORY.SPI_GET_CONFIGURATION1(?,?,?,?); END;";
      try{
         conn = Proveedor.getConnection();
         //dbmsOutput = new DbmsOutput(conn);
         //dbmsOutput.enable(1000000);		
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);	
         cstmt.setString(1,strParam);	
         cstmt.setInt(2,intState);
         cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
         cstmt.registerOutParameter(4, OracleTypes.CURSOR);		
         cstmt.executeQuery();
         //dbmsOutput.show();
         //dbmsOutput.close();
         strMessage = cstmt.getString(3);
         if (StringUtils.isBlank(strMessage)) {	
            rs = (ResultSet)cstmt.getObject(4);    
            while (rs.next()) {
              DominioBean objDominioBean = new DominioBean();
        
               objDominioBean.setValor((rs.getString("npparamv1")));
               objDominioBean.setDescripcion((rs.getString("npparamv1")));
               arrNpTable.add(objDominioBean);		                
            }
         }
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
         closeObjectsDatabase(conn, cstmt, rs);
      }
      hshDataMap.put("objArrayList", arrNpTable);
      hshDataMap.put("strMessage", strMessage);		
         
      return hshDataMap;
    }  
    
    
    /*
     * Eduardo 
     */
 
 
 
 /**
   * Obtiene una lista de division de negcio
   * @throws java.lang.Exception
   * @throws java.sql.SQLException
   * @return 
   */
   
    //NP.SPI_GET_WAREHOUSE_DET@PORAP11I
    public HashMap getDivisionList() throws SQLException, Exception {
      if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
			
      HashMap hshDataMap = new HashMap();
      ArrayList arrDivisionList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      //ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      String sqlStr = "BEGIN ORDERS.NP_ORDERS08_PKG.SP_GET_DIVISION_LST(?,?); END;";
      try{
        conn = Proveedor.getConnection();
        //dbmsOutput = new DbmsOutput(conn);
        //dbmsOutput.enable(1000000);
        cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
        cstmt.registerOutParameter(1, OracleTypes.ARRAY, "ORDERS.TT_DIVISION_LST");
        cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
        cstmt.execute();
        //dbmsOutput.show();
        //dbmsOutput.close();
        strMessage = cstmt.getString(2);
        ARRAY aryDominioList = (ARRAY)cstmt.getObject(1);
            if (StringUtils.isBlank(strMessage)) {
                  for (int i = 0; i < aryDominioList.getOracleArray().length; i++) {
                      STRUCT stcDominio = (STRUCT) aryDominioList.getOracleArray()[i];
                      DominioBean objDominioBean = new DominioBean();
                      NUMBER nmbDivisionId = ((NUMBER) stcDominio.getOracleAttributes()[0]);
                      
                      String idDivicionNegocio = String.valueOf(nmbDivisionId != null ? nmbDivisionId.longValue() : 0);
                      
                      objDominioBean.setValor(MiUtil.trimNotNull(idDivicionNegocio));
                      objDominioBean.setDescripcion(MiUtil.trimNotNull(stcDominio.getOracleAttributes()[1].toString()));
                      arrDivisionList.add(i, objDominioBean);
                  }
              }
        }
        catch(Exception e){
         throw new Exception(e);
       }
       finally{
          closeObjectsDatabase(conn, cstmt, null);
       }
      hshDataMap.put(Constante.SOLICITUD_LISTA_DIVISION_NEGOCIO, arrDivisionList);
      hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
        return hshDataMap;
    }
    
  /**
   * Obtiene una lista de categorias
   * @throws java.lang.Exception
   * @throws java.sql.SQLException
   * @return 
   * @param lSolutionId
   */
    public HashMap getCategoryList(long lSolutionId) throws SQLException, Exception {
      if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
			
      HashMap hshDataMap = new HashMap();
      ArrayList arrCategoryList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      String sqlStr = "BEGIN ORDERS.SPI_GET_CATEGORY_X_DIVISION(?,?,?); END;";
      try{
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
         cstmt.setLong(1, lSolutionId);
         cstmt.registerOutParameter(2, OracleTypes.CURSOR);
         cstmt.registerOutParameter(3, OracleTypes.VARCHAR );
         cstmt.executeQuery();
         rs = (ResultSet)cstmt.getObject(2);
         strMessage = cstmt.getString(3);
         if (StringUtils.isBlank(strMessage)) {
            while (rs.next()) {
               DominioBean dominio = new DominioBean();
               int i = 1;
               dominio.setValor(StringUtils.defaultString(rs.getString(3)));
               try {
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(3)));
               }
               catch(SQLException sqle) {
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(3)));
               }
               arrCategoryList.add(dominio);
            }
         }
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
		hshDataMap.put(Constante.SOLICITUD_LISTA_CATEGORIA, arrCategoryList);
		hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
        return hshDataMap;
    }
    
  /**
   * Obtiene una lista de subcategorias
   * @throws java.lang.Exception
   * @throws java.sql.SQLException
   * @return 
   * @param lSolutionId
   * @param strCategoria
   */
    public HashMap getSubCategoryList(String strCategoria, long lSolutionId) throws SQLException, Exception {
      if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
			
      HashMap hshDataMap = new HashMap();
      ArrayList arrSubCategoryList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      String sqlStr = "BEGIN ORDERS.SPI_GET_SUBCATEGORY_X_DIVISION(?,?,?,?); END;";
      try{
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
         System.out.println("EDU: dao getSubCategoryList lSolutionId = " + lSolutionId );
         cstmt.setLong(1, lSolutionId);
         System.out.println("EDU: dao getSubCategoryList strCategoria = " + strCategoria );
         cstmt.setString(2, strCategoria);         
         cstmt.registerOutParameter(3, OracleTypes.CURSOR);
         cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
         cstmt.executeQuery();
         strMessage = cstmt.getString(4);
         
         System.out.println("EDU: dao getSubCategoryList strMessage = " + strMessage );
         
         rs = (ResultSet)cstmt.getObject(3);
         
         /*System.out.println("EDU: rs.getMetaData().getColumnCount()= " +rs.getMetaData().getColumnCount());
         for (int x=1;x<=rs.getMetaData().getColumnCount();x++){
            System.out.println(rs.getMetaData().getColumnName(x)+ " "+ rs.getMetaData().getColumnTypeName(x));
            //System.out.println(x);
          }*/
         
         if (StringUtils.isBlank(strMessage)) {
            System.out.println("EDU: dao getSubCategoryList Entro al if ");
               while (rs.next()) {
                   DominioBean dominio = new DominioBean();
                   int i = 1;
                   dominio.setValor(StringUtils.defaultString(rs.getString(5)));
               try {
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(5)));
               }
               catch(SQLException sqle) {
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(4)));
               }
                   arrSubCategoryList.add(dominio);
               }
           }
        }
        catch(Exception e){
          throw new Exception(e);
        }
        finally{
          closeObjectsDatabase(conn, cstmt, rs);
        }
        hshDataMap.put(Constante.SOLICITUD_LISTA_SUB_CATEGORIA, arrSubCategoryList);
        hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
        return hshDataMap;
    }
    
  /**
   * Motivo: Obtiene el id canal de atención
   * <br>Realizado por: <a href="mailto:mario.mendoza-ludena@hp.com">Antonio Mendoza</a>
   * <br>Fecha: 05/10/2010
   * @param     intSalesstructid
   */
  public int get_AttChannel_Struct(int intSalesstructid) 
   throws SQLException,Exception
   {
      int iReturnValue = 0;
      Connection conn=null;
      OracleCallableStatement cstmt = null;
            
      String sqlStr = " { ? = call PRODUCT.FXI_GET_ATTCHANNEL_STRUCT( ? ) } ";
      try{
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement)conn.prepareCall(sqlStr);
         
         cstmt.registerOutParameter(1, Types.NUMERIC);
         cstmt.setInt(2, intSalesstructid);
         
         cstmt.execute();
         
         iReturnValue = cstmt.getInt(1);
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, null);        
      }
      return iReturnValue;
   }
   
   
    /**
   * Motivo: obtiene una lista de ubicacion
   * @throws java.lang.Exception
   * @throws java.sql.SQLException
   * @return 
   * @param strTableName
   */
    public HashMap getLocationList() throws SQLException, Exception {

      HashMap hshDataMap = new HashMap();
      List localList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;		
      DominioBean dominioBean = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      String sqlStr = "BEGIN WEBSALES.SPI_GET_BUILDING_LIST4(?,?,?); END;";      
      String constante = "'Fisica','Fulfillment','Local'";
      System.out.println("EDU: constante = " + constante);
      try{
         conn = Proveedor.getConnection();
         //dbmsOutput = new DbmsOutput(conn);
         //dbmsOutput.enable(1000000);		
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);		
         cstmt.setString(1,constante);		
         cstmt.registerOutParameter(2, OracleTypes.CURSOR);
         cstmt.registerOutParameter(3, OracleTypes.VARCHAR);		
         cstmt.executeQuery();
         //dbmsOutput.show();
         //dbmsOutput.close();
         rs = (ResultSet)cstmt.getObject(2);          
         strMessage = cstmt.getString(3);
         
         if (StringUtils.isBlank(strMessage)) {	                               
            while (rs.next()) {              
               dominioBean = new DominioBean();
               dominioBean.setValor(rs.getString("npbuildingid"));   
               dominioBean.setDescripcion(rs.getString("npbuildingid") + " - " +rs.getString("npshortname"));
               localList.add(dominioBean);		                
            }
         }
        }
        catch(Exception e){          
           throw new Exception(e);
        }
        finally{
           closeObjectsDatabase(conn, cstmt, rs);
        }
        hshDataMap.put(Constante.SOLICITUD_LISTA_UBICACION, localList);
        hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);		
           
        return hshDataMap;
      } 
      
      
  /**
   * Motivo: obtiene una lista de usuarios
   * @throws java.lang.Exception
   * @throws java.sql.SQLException
   * @return 
   * @param buildingId
   */
  public HashMap getUserList(int buildingId) throws SQLException, Exception {

      HashMap hshDataMap = new HashMap();
      List userList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;		
      DominioBean dominioBean = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      String sqlStr = "BEGIN INVENTORY.NP_INVENTORY_DOCUMENT_PKG.SP_GET_USER(?,?,?); END;";      
     
      try{
         conn = Proveedor.getConnection();
         //dbmsOutput = new DbmsOutput(conn);
         //dbmsOutput.enable(1000000);		
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);		
         cstmt.setInt(1, buildingId);		
         cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
         cstmt.registerOutParameter(3, OracleTypes.CURSOR);		
         cstmt.executeQuery();
         //dbmsOutput.show();
         //dbmsOutput.close();
         strMessage = cstmt.getString(2);
         rs = (ResultSet)cstmt.getObject(3);
         
         if (StringUtils.isBlank(strMessage)) {	                               
            while (rs.next()) {              
               dominioBean = new DominioBean();
               dominioBean.setValor(rs.getString("nplogin"));   
               dominioBean.setDescripcion(rs.getString("nplogin"));
               userList.add(dominioBean);		                
            }
         }
        }
        catch(Exception e){          
           throw new Exception(e);
        }
        finally{
           closeObjectsDatabase(conn, cstmt, rs);
        }
        hshDataMap.put(Constante.SOLICITUD_LISTA_USUARIOS, userList);
        hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);		
           
        return hshDataMap;
      }
      
      
      
      
      
  
 
}