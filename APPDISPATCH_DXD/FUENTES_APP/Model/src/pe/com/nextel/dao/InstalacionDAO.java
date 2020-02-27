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

import pe.com.nextel.bean.InstalacionBean;
import pe.com.nextel.bean.estadoinstalacion.MotivoBean;
import pe.com.nextel.exception.UserException;
import pe.com.nextel.util.MiUtil;

public class InstalacionDAO extends GenericDAO{
    public InstalacionDAO() {
        super();
    }
   
    public void insertar(InstalacionBean instalacionBean) throws UserException {
        logger.debug("[InstalacionDAO][insertar] inicio");
        logger.debug("[InstalacionDAO][insertar] entrada "+ instalacionBean.toString());
        Connection conn = null;
        ResultSet rs = null;
        String strMessage = null;
        OracleCallableStatement cstm = null;
        String sqlStr =
            "BEGIN ORDERS.PKG_SC_BAFIOUTDOOR.SP_SC_INS_INSTALACION(?,?,?,?,?,?,?); END;";

        try {
            conn = Proveedor.getConnection();
            cstm = (OracleCallableStatement)conn.prepareCall(sqlStr);

            cstm.setLong(1,instalacionBean.getOrdenId());
            cstm.setLong(2,instalacionBean.getMotivoId());
            cstm.setLong(3,instalacionBean.getSubMotivoId());
            if(instalacionBean.getDetalleId()==null){
                cstm.setNull(4,OracleTypes.NUMBER);
            }else{
                cstm.setLong(4, instalacionBean.getDetalleId());
            }
            cstm.setString(5,instalacionBean.getComentario()); 
            cstm.setString(6,instalacionBean.getCreadoPor()); 
            cstm.registerOutParameter(7, Types.VARCHAR);
            cstm.executeQuery();

            strMessage = cstm.getString(7);
            if (!StringUtils.isBlank(strMessage)){
                throw new UserException(strMessage);
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
        logger.debug("[InstalacionDAO][insertar] fin");
    }
    
    public InstalacionBean obtenerPorOrdenId(long ordenId) throws UserException {
        logger.debug("[InstalacionDAO][obtenerPorOrdenId] inicio");
        logger.debug("[InstalacionDAO][obtenerPorOrdenId] entrada ordenId["+ordenId+"]");
        Connection conn         = null;
        OracleCallableStatement cstmt = null;
        String strMessage = "";
        ResultSet rs = null;
        InstalacionBean instalacionBean = new InstalacionBean();

        try {
            String strSql = "BEGIN ORDERS.PKG_SC_BAFIOUTDOOR.SP_SC_GET_LISTAINSTALACION( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END; " ;

            conn =  Proveedor.getConnection();
            cstmt = (OracleCallableStatement) conn.prepareCall(strSql);
            cstmt.setLong(1, ordenId);
            cstmt.registerOutParameter(2, Types.NUMERIC);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, Types.NUMERIC);
            cstmt.registerOutParameter(5, Types.VARCHAR);
            cstmt.registerOutParameter(6, Types.NUMERIC);
            cstmt.registerOutParameter(7, Types.VARCHAR);
            cstmt.registerOutParameter(8, Types.NUMERIC);
            cstmt.registerOutParameter(9, Types.VARCHAR);
            cstmt.registerOutParameter(10, Types.NUMERIC);
            cstmt.registerOutParameter(11, Types.VARCHAR);
            cstmt.registerOutParameter(12, Types.NUMERIC);
            cstmt.registerOutParameter(13, Types.VARCHAR);
            cstmt.registerOutParameter(14, Types.VARCHAR);
            cstmt.registerOutParameter(15, Types.NUMERIC);
            cstmt.registerOutParameter(16, Types.VARCHAR);
            cstmt.registerOutParameter(17, Types.VARCHAR);
            cstmt.registerOutParameter(18, Types.VARCHAR);
            
           cstmt.execute();
            strMessage = cstmt.getString(18);

            if (strMessage != null){
                throw new UserException(strMessage);
            }

            instalacionBean.setId(cstmt.getLong(2));
            instalacionBean.setComentario(MiUtil.emptyValConcat(cstmt.getString(3)));
            instalacionBean.setMotivoId(cstmt.getLong(4));
            instalacionBean.setMotivoInstalacion(MiUtil.emptyValConcat(cstmt.getString(5)));
            instalacionBean.setSubMotivoId(cstmt.getLong(6));                              
            instalacionBean.setSubmotivoInstalacion(MiUtil.emptyValConcat(cstmt.getString(7)));
            instalacionBean.setDetalleId(cstmt.getLong(8));
            instalacionBean.setDetalle(MiUtil.emptyValConcat(cstmt.getString(9)));
            instalacionBean.setAgendamientoId(cstmt.getLong(10));
            instalacionBean.setNombreContacto1(MiUtil.emptyValConcat(cstmt.getString(11)));
            instalacionBean.setTelefonoContacto1(cstmt.getLong(12));
            instalacionBean.setHorarioComunicacion1(MiUtil.emptyValConcat(cstmt.getString(13)));
            instalacionBean.setNombreCOntacto2(MiUtil.emptyValConcat(cstmt.getString(14)));
            instalacionBean.setTelefonoContacto2(cstmt.getLong(15));
            instalacionBean.setHorarioComunicacion2(MiUtil.emptyValConcat(cstmt.getString(16)));
            instalacionBean.setCorreoContacto(MiUtil.emptyValConcat(cstmt.getString(17)));
            instalacionBean.setMensaje(MiUtil.emptyValConcat(cstmt.getString(18)));
            logger.debug("[InstalacionDAO][obtenerPorOrdenId] salida instalacionBean["+instalacionBean.toString()+"]");
        } catch (Exception e) {
            logger.error("[InstalacionDAO][obtenerPorOrdenId] Error inesperado",e);
            throw new UserException(strMessage);

        } finally {
            try {
                closeObjectsDatabase(conn,cstmt,rs);
            } catch (Exception e) {
                logger.error(formatException(e));
                throw new UserException(strMessage);
            }
        }

        logger.debug("[InstalacionDAO][obtenerPorId] fin");

        return instalacionBean;
    }
    
    public void actualizar(InstalacionBean instalacionBean) throws UserException {
        logger.debug("[InstalacionDAO][actualizar] inicio");
        logger.debug("[InstalacionDAO][actualizar] entrada " + instalacionBean.toString());
        Connection conn = null;
        ResultSet rs = null;
        String strMessage = null;
        OracleCallableStatement cstm = null;
        String sqlStr =
                "BEGIN ORDERS.PKG_SC_BAFIOUTDOOR.SP_SC_UPD_INSTALACION(?,?,?,?,?,?,?); END;";

        try {
            conn = Proveedor.getConnection();
            cstm = (OracleCallableStatement)conn.prepareCall(sqlStr);

            cstm.setLong(1,instalacionBean.getOrdenId());
            cstm.setLong(2,instalacionBean.getMotivoId());
            cstm.setLong(3,instalacionBean.getSubMotivoId());
            if(instalacionBean.getDetalleId()==null){
                cstm.setNull(4,OracleTypes.NUMBER);
            }else{
                cstm.setLong(4, instalacionBean.getDetalleId());
            }
            cstm.setString(5,instalacionBean.getComentario());
            cstm.setString(6,instalacionBean.getModificadoPor());
            cstm.registerOutParameter(7, Types.VARCHAR);
            cstm.executeQuery();

            strMessage = cstm.getString(7);
            if (!StringUtils.isBlank(strMessage)){
                throw new UserException(strMessage);
            }

        } catch (Exception e) {
            logger.error("[InstalacionDAO][actualizar] Error inesperado",e);
            throw new UserException(e);
        } finally {
            try {
                closeObjectsDatabase(conn, cstm, rs);
            } catch (SQLException e) {
                logger.error("[InstalacionDAO][actualizar] Error al cerrar conexiones",e);
                throw new UserException(e);
            }
        }
        logger.debug("[InstalacionDAO][actualizar] fin");
    }
}
