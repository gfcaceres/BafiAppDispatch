package pe.com.nextel.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pe.com.nextel.bean.CompanyBean;
import pe.com.nextel.bean.DominioBean;
import pe.com.nextel.util.Constante;


public class CompanyDAO extends GenericDAO
{

  protected static Logger logger = Logger.getLogger(CompanyDAO.class);
  
  public CompanyDAO(){}
  
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
      String sqlStr = "BEGIN SWBAPPS.SPI_GET_VALUEDESC_X_NPTABLE(?,?,?); END;";
      try{
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
         cstmt.setString(1, Constante.PARAM_BD_DOCUMENT_TYPE);
         cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
         cstmt.registerOutParameter(3, OracleTypes.CURSOR);
         cstmt.executeQuery();
         
         strMessage = cstmt.getString(2);
         rs = (ResultSet)cstmt.getObject(3);
         
         if (StringUtils.isBlank(strMessage)) {
            while (rs.next()) {
               DominioBean dominio = new DominioBean();
               int i = 1;
               dominio.setValor(StringUtils.defaultString(rs.getString(1)));
               try {
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(2)));
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
      hshDataMap.put(Constante.BUSCAR_COMPANIA_LISTA_TIPO_DOCUMENTO, arrTipoDocumentoList);
      hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
        return hshDataMap;
    }
    
    
    
    public HashMap getTipoCompaniaList() throws SQLException, Exception {
      if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
			
      HashMap hshDataMap = new HashMap();
      List arrCompaniaList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      String sqlStr = "BEGIN SWBAPPS.SPI_GET_VALUEDESC_X_NPTABLE(?,?,?); END;";
      try{
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
         cstmt.setString(1, Constante.PARAM_BD_COMPANY_TYPE);
         cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
         cstmt.registerOutParameter(3, OracleTypes.CURSOR);
         cstmt.executeQuery();
         
         strMessage = cstmt.getString(2);
         rs = (ResultSet)cstmt.getObject(3);
         
         if (StringUtils.isBlank(strMessage)) {
            while (rs.next()) {
               DominioBean dominio = new DominioBean();
               int i = 1;
               dominio.setValor(StringUtils.defaultString(rs.getString(1)));
               try {
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(2)));
               }
               catch(SQLException sqle) {
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(1)));
               }
               arrCompaniaList.add(dominio);
            }
         }
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
        hshDataMap.put(Constante.BUSCAR_COMPANIA_LISTA_TIPO_COMPANIA, arrCompaniaList);
        hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
        return hshDataMap;
    }
    
    
    
    public HashMap getCompaniaList(CompanyBean companyBean) throws SQLException, Exception {
      if(logger.isDebugEnabled()){
        logger.debug("--Inicio--");
      }
			
      HashMap hshDataMap = new HashMap();
      List arrCompaniaList = new ArrayList();
      Connection conn = null;
      OracleCallableStatement cstmt = null;
      ResultSet rs = null;
      //DbmsOutput dbmsOutput = null;
      String strMessage = null;
      String strTotalRegistros = null;
      String sqlStr = "BEGIN WEBSALES.NPSL_CUSTOMER_EXT_PKG.SP_GET_CUSTOMER_LIST(" +
          "?, ?, ?, ?, " +
          "?, ?, ?, ?, " +
          "?, ?, ?, ?, " +
          "?, ?, ?, " +
          "?, ?, ?, ?," +
          "?, ?, ?, " +
          "?, ?, ?, ?, " +
          "?, ?, ? ); END;";
      
      
      try{
         conn = Proveedor.getConnection();
         cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
         
         cstmt.setString(1, companyBean.getDocumentType() );
         System.out.println("EDU: companyBean.getDocumentType() = " + companyBean.getDocumentType());         
         cstmt.setString(2, companyBean.getDocumentNumbre());
         System.out.println("EDU: companyBean.getDocumentNumbre() = " + companyBean.getDocumentNumbre());
         cstmt.setString(3, companyBean.getRazonSocial());
         System.out.println("EDU: companyBean.getRazonSocial() = " + companyBean.getRazonSocial());
         cstmt.setString(4, companyBean.getCompanyName());
         System.out.println("EDU: companyBean.getCompanyName() = " + companyBean.getCompanyName());
         cstmt.setString(5, companyBean.getCompamyType());
         System.out.println("EDU: companyBean.getCompamyType() = " + companyBean.getCompamyType());
         cstmt.setString(6, null);
         cstmt.setInt(7, companyBean.getCompanyCod());
         System.out.println("EDU: companyBean.getCompanyCod() = " + companyBean.getCompanyCod());
         
         cstmt.setInt(8, 0);
         cstmt.setString(9, null);
         cstmt.setString(10, null);
         cstmt.setString(11, null);
         cstmt.setString(12, null);
         cstmt.setInt(13, 0);
         cstmt.setInt(14, 0);
         cstmt.setInt(15, 0);
         cstmt.setString(16, null);
         cstmt.setString(17, null);
         cstmt.setString(18, null);
         cstmt.setString(19, null);
         cstmt.setString(20, null);
         cstmt.setInt(21, 0);
         cstmt.setInt(22, 0);
         cstmt.setInt(23, 0);
         cstmt.setInt(24, 0);
         cstmt.setInt(25, 0);
         cstmt.setString(26, companyBean.getBSCSCod());
         System.out.println("EDU: companyBean.getBSCSCod() = " + companyBean.getBSCSCod());
         
         cstmt.registerOutParameter(27, OracleTypes.VARCHAR);
         cstmt.registerOutParameter(28, OracleTypes.CURSOR);
         cstmt.registerOutParameter(29, OracleTypes.VARCHAR);
         
         cstmt.executeQuery();
         
         strMessage = cstmt.getString(27);
         System.out.println("EDU: strMessage = " + strMessage);
         strTotalRegistros = cstmt.getString(29);
         System.out.println("EDU: strTotalRegistros = " + strTotalRegistros);
         rs = (ResultSet)cstmt.getObject(28);
         
         if (StringUtils.isBlank(strMessage)) {
         
          for (int x=1;x<=rs.getMetaData().getColumnCount();x++){
           // System.out.print(rs.getString(x)+ "\t");
          }
          
            while (rs.next()) {
               DominioBean dominio = new DominioBean();
               int i = 1;
               dominio.setValor(StringUtils.defaultString(rs.getString(1)));
               try {
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(2)));
               }
               catch(SQLException sqle) {
                  dominio.setDescripcion(StringUtils.defaultString(rs.getString(1)));
               }
               arrCompaniaList.add(dominio);
            }
         }
      }
      catch(Exception e){
         throw new Exception(e);
      }
      finally{
        closeObjectsDatabase(conn, cstmt, rs);
      }
        hshDataMap.put(Constante.BUSCAR_COMPANIA_LISTA_TIPO_COMPANIA, arrCompaniaList);
        hshDataMap.put(Constante.MESSAGE_OUTPUT, strMessage);
        return hshDataMap;
    }
  
}