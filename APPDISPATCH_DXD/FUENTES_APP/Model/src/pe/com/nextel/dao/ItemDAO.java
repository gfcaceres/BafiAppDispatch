package pe.com.nextel.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import oracle.sql.ARRAY;
import oracle.sql.STRUCT;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pe.com.nextel.bean.ItemBean;
import pe.com.nextel.util.Constante;
import pe.com.nextel.util.MiUtil;


public class ItemDAO extends GenericDAO {
  static  Logger logger = Logger.getLogger(RequestDAO.class);
  
  
  
  
  /**
     * Motivo: Lista el Detalle del IMEI Solicitado 
     * Realizado por : Alberto Quineche Poma
     * @param  strImeiNum
     * @return    HashMap 
     **/
     
     
     
     public HashMap getImeiDet(String strImeiNum)throws SQLException, Exception 
     {
       logger.debug("===============Item DAO : getImeiDet ==================");
       logger.debug("strImeiNum(Numero de IMEI) : "+strImeiNum);
       
       	Connection conn = null;
        OracleCallableStatement cstmt = null;
        ResultSet rs = null;
        ArrayList arrList = new ArrayList();
        HashMap objHashMapResultado = new HashMap();
        String error=null;
        try
        {           
            String sqlStr = "BEGIN  INVENTORY.SPI_GET_IMEI_DET2(?,?,?);END;";
            conn = Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
            cstmt.setString(1, strImeiNum);
            cstmt.registerOutParameter(2, OracleTypes.ARRAY,"INVENTORY.TT_RECORD_IMEI_DETFF");
            cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
            cstmt.executeQuery();    
            error = cstmt.getString(3);             
            
            ARRAY aryDominioList = (ARRAY)cstmt.getObject(2);
           
           System.out.println("PINTAMOS EL ERROR :"+error);
           
            if (StringUtils.isBlank(error)) {
                  for (int i = 0; i < aryDominioList.getOracleArray().length; i++) {
                      STRUCT stcDominio = (STRUCT) aryDominioList.getOracleArray()[i];
                     // DominioBean objDominioBean = new DominioBean();
                      ItemBean bean = new ItemBean();
                      bean.setNpsegment1(MiUtil.defaultString(stcDominio.getAttributes()[0], ""));
                      bean.setNpdescription(MiUtil.defaultString(stcDominio.getAttributes()[1], ""));
                      bean.setNporganizationid(MiUtil.defaultString(stcDominio.getAttributes()[2], ""));
                      bean.setNpname(MiUtil.defaultString(stcDominio.getAttributes()[3], ""));
                      bean.setNpsubinventorycode(MiUtil.defaultString(stcDominio.getAttributes()[4], ""));
                      bean.setNptransactiontypename(MiUtil.defaultString(stcDominio.getAttributes()[5], ""));
                      bean.setNpreasonid(MiUtil.parseLong(MiUtil.defaultString(stcDominio.getAttributes()[6], "")));
                      bean.setNpreasonname(MiUtil.defaultString(stcDominio.getAttributes()[6], ""));
                      bean.setNpcreationdate((MiUtil.defaultString(stcDominio.getAttributes()[7], "")));
                      bean.setNpattribute7(MiUtil.defaultString(stcDominio.getAttributes()[8], ""));
                      bean.setNpattribute6(MiUtil.defaultString(stcDominio.getAttributes()[9], ""));
                      bean.setNptransactionreference(MiUtil.defaultString(stcDominio.getAttributes()[10], ""));
                      bean.setNpcurrentstatus(MiUtil.defaultString(stcDominio.getAttributes()[11], ""));
                      bean.setNpbuildingid(MiUtil.defaultString(stcDominio.getAttributes()[12], ""));
                      bean.setNpmodel(MiUtil.defaultString(stcDominio.getAttributes()[13], ""));
                      bean.setNporgdateto((MiUtil.defaultString(stcDominio.getAttributes()[14], "")));
        
                      arrList.add(bean);
                      
                      logger.debug("getImeiDet:Tamaño de Lista(arrList) :"+arrList.size());

                  }
              }
              
              objHashMapResultado.put(Constante.ERROR_OUTPUT,error);
              objHashMapResultado.put("objArrayList",arrList);

            

            } catch (SQLException sqle) {
            logger.error(formatException(sqle));
        } finally {
				closeObjectsDatabase(conn,cstmt,rs); 
        }
        return objHashMapResultado;
     }   
}