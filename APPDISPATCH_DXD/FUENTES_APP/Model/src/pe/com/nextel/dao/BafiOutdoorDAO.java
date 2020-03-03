package pe.com.nextel.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.HashMap;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;

import oracle.jdbc.OracleTypes;

import org.apache.commons.lang.StringUtils;

import org.apache.log4j.Logger;

import pe.com.nextel.bean.estadoinstalacion.MotivoBean;
import pe.com.nextel.exception.UserException;
import pe.com.nextel.util.Constante;

public class BafiOutdoorDAO extends GenericDAO {
    
    private static Logger logger = Logger.getLogger(BafiOutdoorDAO.class);
    
    public BafiOutdoorDAO() {
        super();
    }
    
    public boolean validarSolicitudOlBafi(long solicitudOlId) throws UserException {
        logger.debug("[BafiOutdoorDAO][validarSolicitudOlBafi] inicio");
        Connection conn = null;
        ResultSet rs = null;
        String strMessage = null;
        OracleCallableStatement cstm = null;
        List<MotivoBean> motivos = null;
        boolean flagAgendamiento = false;
        String sqlStr =
            "BEGIN ORDERS.PKG_SC_BAFIOUTDOOR.SP_SC_VALID_REQUEST_OL(?,?,?); END;";

        try {
            conn = Proveedor.getConnection();
            cstm = (OracleCallableStatement)conn.prepareCall(sqlStr);

            cstm.setLong(1,solicitudOlId);
            cstm.registerOutParameter(2, Types.NUMERIC);
            cstm.registerOutParameter(3, Types.VARCHAR);
            logger.debug("[BafiOutdoorDAO][validarSolicitudOlBafi] entrada solicitudOlId["+solicitudOlId+"]");
            cstm.executeQuery();

            strMessage = cstm.getString(3);
            if (!StringUtils.isBlank(strMessage)){
                throw new UserException(strMessage);
            }
            int respuesta = cstm.getInt(2);
            logger.debug("[BafiOutdoorDAO][validarSolicitudOlBafi] respuesta["+respuesta+"]");
            if (respuesta>0){
                flagAgendamiento= true;
            }else{
                flagAgendamiento= false;
            }
            

        } catch (Exception e) {
            e.printStackTrace();
            throw new UserException(e);
        } finally {
            try {
                closeObjectsDatabase(conn, cstm, rs);
            } catch (SQLException e) {
                throw new UserException(e);
            }
        }        
        logger.debug("[BafiOutdoorDAO][validarSolicitudOlBafi] salida flagAgendamiento["+flagAgendamiento+"]");
        logger.debug("[BafiOutdoorDAO][validarSolicitudOlBafi] inicio");
        return flagAgendamiento;
    }  
    
    public int validarInstalacionEdit(Long numOrderId) throws UserException {
        logger.debug("[BafiOutdoorDAO][validarInstalacionEdit] inicio");
        logger.debug("[BafiOutdoorDAO][validarInstalacionEdit] entrada numOrderId["+numOrderId+"]");
        Connection conn         = null;
        OracleCallableStatement cstmt = null;
        String strMessage = null;
        int validInstalacionEdit = 0;
        ResultSet rs = null;
       
        try {
            String strSql = "BEGIN ORDERS.PKG_SC_BAFIOUTDOOR.SP_SC_VALID_INSTALACIONEDIT( ?, ?, ?); END; " ;

            conn =  Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(strSql);
            cstmt.setLong(1, numOrderId);
            cstmt.registerOutParameter(2, Types.NUMERIC);
            cstmt.registerOutParameter(3, Types.CHAR);
            
            cstmt.execute();
            strMessage = cstmt.getString(3);
            if (!StringUtils.isBlank(strMessage)){
                throw new UserException(strMessage);
            }
            
            validInstalacionEdit = cstmt.getInt(2);

        } catch (Exception e) {
            throw new UserException(e);
        } finally {
            try {
                closeObjectsDatabase(conn,cstmt,rs);
            } catch (Exception e) {
                throw new UserException(e);
            }
        }
        logger.debug("[BafiOutdoorDAO][validarInstalacionEdit] salida validInstalacionEdit["+validInstalacionEdit+"]");
        logger.debug("[BafiOutdoorDAO][validarInstalacionEdit] fin");
        return validInstalacionEdit;
    }

    public String obtenerConfigUnit(String table,String value) throws UserException {
        String strDescription = null;
        Connection conn = null;
        OracleCallableStatement cstmt = null;
        String strMessage = null;
        String sqlStr = "BEGIN SWBAPPS.NP_TABLE01_PKG.SP_GET_VALUEDESC(?,?,?,?); END;";
        try{
            conn = Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(sqlStr);
            cstmt.setString(1, value);
            cstmt.setString(2, table);
            cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
            cstmt.executeQuery();
            strMessage = cstmt.getString(3);
            if(strMessage!=null){
                throw new UserException(strMessage);
            }
            strDescription = cstmt.getString(4);
        }catch(Exception e){
            throw new UserException(e);
        }
        finally{
            try{
                closeObjectsDatabase(conn, cstmt, null);
            } catch(Exception e){
                throw new UserException(e);
            }

        }
        return strDescription;

    }  
    
    public HashMap validarRegularizarOrdenOutdoor(Long ordenId, String imei) throws UserException {
        logger.debug("[BafiOutdoorDAO][validarRegularizarOrdenOutdoor] Inicio");
        logger.debug("[BafiOutdoorDAO][validarRegularizarOrdenOutdoor] Entrada numOrderId["+ordenId+"]");
        logger.debug("[BafiOutdoorDAO][validarRegularizarOrdenOutdoor] Entrada imei["+imei+"]");
        
        Connection conn         = null;
        OracleCallableStatement cstmt = null;
        HashMap hshDataMap = new HashMap();
       
        try {
            String strSql = "BEGIN ORDERS.PKG_SC_ORDERS44.SP_SC_CA_GET_BAFI_OUTDOOR( ?, ?, ?, ?, ?); END; " ;

            conn =  Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(strSql);
            cstmt.setLong(1, ordenId);
            cstmt.setString(2, imei);
            cstmt.registerOutParameter(3, Types.NUMERIC);
            cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
            
            cstmt.executeQuery();
           
            hshDataMap.put("errorCodigo", cstmt.getInt(3));
            hshDataMap.put("errorMensaje", cstmt.getString(4));
            hshDataMap.put("almacenId", cstmt.getString(5));
            
        } catch (Exception e) {
            throw new UserException(e);
        } finally {
            try {
                closeObjectsDatabase(conn,cstmt, null);
            } catch (Exception e) {
                throw new UserException(e);
            }
        }
        logger.debug("[BafiOutdoorDAO][validarRegularizarOrdenOutdoor] Salida validInstalacionEdit["+ hshDataMap.toString() +"]");
        logger.debug("[BafiOutdoorDAO][validarRegularizarOrdenOutdoor] Fin");
        
        return hshDataMap;
    }

    public HashMap regularizarOrdenOutdoor(Long ordenId, String imei, String almacenId, String creadoPor) throws UserException{
        logger.debug("[BafiOutdoorDAO][regularizarOrdenOutdoor] Inicio");
        logger.debug("[BafiOutdoorDAO][regularizarOrdenOutdoor] Entrada numOrderId["+ordenId+"]");
        logger.debug("[BafiOutdoorDAO][regularizarOrdenOutdoor] Entrada imei["+imei+"]");
        logger.debug("[BafiOutdoorDAO][regularizarOrdenOutdoor] Entrada almacenId["+almacenId+"]");
        logger.debug("[BafiOutdoorDAO][regularizarOrdenOutdoor] Entrada creadoPor["+creadoPor+"]");
        Connection conn         = null;
        OracleCallableStatement cstmt = null;
        Integer errorCodigo = null;
        HashMap hshDataMap = new HashMap();
        
        try {
            String strSql = "BEGIN ORDERS.PKG_SC_ORDERS44.SP_SC_CA_UPD_BAFI_OUTDOOR( ?, ?, ?, ?, ?, ?); END; " ;

            conn =  Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(strSql);
            cstmt.setLong(1, ordenId);
            cstmt.setString(2, imei);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(5, Types.NUMERIC);
            cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
            
            cstmt.executeQuery();
            errorCodigo = cstmt.getInt(5);
            
            if(errorCodigo!=0){
                throw new UserException(cstmt.getString(6));
            }else{
                hshDataMap.put("errorCodigo", cstmt.getInt(5));
                hshDataMap.put("errorMensaje", cstmt.getString(6));
            }
            
        } catch (Exception e) {
            throw new UserException(e);
        } finally {
            try {
                closeObjectsDatabase(conn,cstmt, null);
            } catch (Exception e) {
                throw new UserException(e);
            }
        }
        logger.debug("[BafiOutdoorDAO][regularizarOrdenOutdoor] Salida validInstalacionEdit["+ hshDataMap.toString() +"]");
        logger.debug("[BafiOutdoorDAO][regularizarOrdenOutdoor] Fin");
        return hshDataMap;        
    }
}
