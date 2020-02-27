package pe.com.nextel.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.HashMap;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

import pe.com.nextel.bean.OrderBean;


public class OrderDAO extends GenericDAO
{


  static Logger logger = Logger.getLogger(OrderDAO.class);
  
  public static HashMap getOrderList(int orderId)throws Exception,SQLException
  {
   HashMap hshRetorno=new HashMap();
   
   
      String strMessage=null;
      OracleCallableStatement cstmt = null;     
      Connection conn=null;
      ResultSet rs = null;
      OrderBean orbResume = new OrderBean();
     
      conn = Proveedor.getConnection();
      
      try{
        String strSql = "BEGIN NP_ORDERS01_PKG.SP_GET_ORDER_DETAIL( ? , ? , ? ); end;";        
		  
        cstmt = (OracleCallableStatement)conn.prepareCall(strSql);
        cstmt.setLong(1, orderId);
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
  
  
     /**
   * Motivo: Obteniene los valores de la tienda y Region de tramite
   * <br>Realizado por: <a href="mailto:carmen.gremios@nextel.com.pe">Carmen Gremios</a>
   * <br>Fecha: 20/07/2007
   * @param     intBuildingid     
   * @param     strLogin                 
   * @return    HashMap 
   */        
   public  HashMap getBuildingName(long intBuildingid, String strLogin) throws Exception, SQLException {
   
      HashMap h = new HashMap();
      HashMap hshRetorno = new HashMap();
      String strMessage=null;
      OracleCallableStatement cstmt = null;
      String strName = null;
      int intRegionId = 0;
      String strRegionName = null;
      Connection conn=null;
      System.out.println("[OrderDAO][getBuildingName][Inicio]:"+intBuildingid+"-"+strLogin);
      String sqlStr = "BEGIN ORDERS.NP_ORDERS01_PKG.SP_GET_BUILDING_NAME(?, ?, ?, ?, ?, ?); END;";    
      try{
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement)conn.prepareCall(sqlStr);      
         cstmt.setLong(1, intBuildingid);
         cstmt.setString(2, strLogin);
         cstmt.registerOutParameter(3, Types.CHAR);
         cstmt.registerOutParameter(4, Types.CHAR);
         cstmt.registerOutParameter(5, Types.NUMERIC);
         cstmt.registerOutParameter(6, Types.CHAR);      
         cstmt.executeUpdate();      
         strMessage = cstmt.getString(3);
         if (strMessage == null){
            strName = cstmt.getString(4);
            intRegionId = cstmt.getInt(5);
            strRegionName = cstmt.getString(6);      
            /*Ingresando valores en un HasTable*/              
            h.put("wv_name", strName);
            h.put("wn_regionid", intRegionId + "");
            h.put("wv_regionname", strRegionName);
         }
      }
      catch(Exception e){
         hshRetorno.put("strMessage",e.getMessage());  
      }finally{
			closeObjectsDatabase(conn,cstmt,null); 
         /*if (cstmt != null)
            cstmt.close();
         if (conn != null)
            conn.close();*/
      }
         
      hshRetorno.put("hshData",h);
      hshRetorno.put("strMessage",strMessage);  
      System.out.println("[OrderDAO][getBuildingName][Fin]:"+intBuildingid+"-"+strLogin);
      return hshRetorno;
   }


 
}