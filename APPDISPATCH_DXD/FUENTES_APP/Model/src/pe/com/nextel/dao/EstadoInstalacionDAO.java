package pe.com.nextel.dao;

import java.math.BigDecimal;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;

import oracle.jdbc.OracleTypes;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import oracle.sql.STRUCT;

import oracle.sql.StructDescriptor;

import org.apache.commons.lang.StringUtils;

import pe.com.nextel.bean.TableBean;
import pe.com.nextel.bean.estadoinstalacion.DetalleBean;
import pe.com.nextel.bean.estadoinstalacion.MotivoBean;
import pe.com.nextel.bean.estadoinstalacion.SubMotivoBean;
import pe.com.nextel.exception.UserException;

public class EstadoInstalacionDAO extends GenericDAO {
    
    public EstadoInstalacionDAO() {
    }
    
    public List<MotivoBean> obtenerListaMotivos() throws UserException {
        Connection conn = null;
        ResultSet rs = null;
        String strMessage = null;
        OracleCallableStatement cstm = null;
        List<MotivoBean> motivos = null;
        String sqlStr =
            "BEGIN ORDERS.PKG_SC_BAFIOUTDOOR.SP_SC_AC_OBT_EST_INST_MOT(?,?); END;";
        ARRAY arrMotivo = null;
        ARRAY arrSubMotivo = null;
        ARRAY arrDetalle = null;
        try {
            conn = Proveedor.getConnection();
            cstm = (OracleCallableStatement)conn.prepareCall(sqlStr);

            cstm.registerOutParameter(1, Types.ARRAY,
                                      "ORDERS.TT_ESTINST_MOTIVO_BO");
            cstm.registerOutParameter(2, Types.VARCHAR);
            cstm.executeQuery();

            strMessage = (String)cstm.getObject(2);
            if (!StringUtils.isBlank(strMessage))
                throw new RuntimeException(strMessage);

            motivos = new ArrayList<MotivoBean>();
            arrMotivo = (ARRAY)cstm.getObject(1);

            if (arrMotivo != null) {
                for (int i = 0; i < arrMotivo.getOracleArray().length; i++) {
                    STRUCT sMotivo = (STRUCT)arrMotivo.getOracleArray()[i];
                    MotivoBean motivoBean = new MotivoBean();
                    motivoBean.setId(getLongValue(sMotivo.getAttributes()[0]));
                    motivoBean.setNombre(getStringValue(sMotivo.getAttributes()[1]));
                    motivoBean.setEstado(getStringValue(sMotivo.getAttributes()[2]));
                    motivoBean.setCreadoPor(getStringValue(sMotivo.getAttributes()[3]));
                    motivoBean.setFechaCreacion(getStringValue(sMotivo.getAttributes()[4]));
                    motivoBean.setModificadoPor(getStringValue(sMotivo.getAttributes()[5]));
                    motivoBean.setFechaModificacion(getStringValue(sMotivo.getAttributes()[6]));
                    arrSubMotivo = (ARRAY)sMotivo.getAttributes()[7];
                    if (arrSubMotivo != null) {
                        for (int j = 0;
                             j < arrSubMotivo.getOracleArray().length; j++) {
                            STRUCT sSubMotivo =
                                (STRUCT)arrSubMotivo.getOracleArray()[j];
                            SubMotivoBean subMotivoBean = new SubMotivoBean();
                            subMotivoBean.setId(getLongValue(sSubMotivo.getAttributes()[0]));
                            subMotivoBean.setNombre(getStringValue(sSubMotivo.getAttributes()[1]));
                            subMotivoBean.setEstado(getStringValue(sSubMotivo.getAttributes()[2]));
                            subMotivoBean.setCreadoPor(getStringValue(sSubMotivo.getAttributes()[3]));
                            subMotivoBean.setFechaCreacion(getStringValue(sSubMotivo.getAttributes()[4]));
                            subMotivoBean.setModificadoPor(getStringValue(sSubMotivo.getAttributes()[5]));
                            subMotivoBean.setFechaModificacion(getStringValue(sSubMotivo.getAttributes()[6]));
                            arrDetalle = (ARRAY)sSubMotivo.getAttributes()[7];

                            if (arrDetalle != null) {
                                for (int k = 0;
                                     k < arrDetalle.getOracleArray().length;
                                     k++) {
                                    STRUCT sDetalle =
                                        (STRUCT)arrDetalle.getOracleArray()[k];
                                    DetalleBean detalleBean =
                                        new DetalleBean();
                                    detalleBean.setId(getLongValue(sDetalle.getAttributes()[0]));
                                    detalleBean.setNombre(getStringValue(sDetalle.getAttributes()[1]));
                                    detalleBean.setEstado(getStringValue(sDetalle.getAttributes()[2]));
                                    detalleBean.setCreadoPor(getStringValue(sDetalle.getAttributes()[3]));
                                    detalleBean.setFechaCreacion(getStringValue(sDetalle.getAttributes()[4]));
                                    detalleBean.setModificadoPor(getStringValue(sDetalle.getAttributes()[5]));
                                    detalleBean.setFechaModificacion(getStringValue(sDetalle.getAttributes()[6]));
                                    subMotivoBean.getDetalles().add(detalleBean);
                                }
                            }
                            motivoBean.getSubmotivos().add(subMotivoBean);

                        }
                    }
                    motivos.add(motivoBean);

                }

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

        return motivos;
    }
    
    public static Double getDoubleValue(Object value) {
        if (value == null)
            return null;
        else
            return ((BigDecimal)value).doubleValue();
    }

    public static Integer getIntegerValue(Object value) {
        if (value == null)
            return null;
        else
            return ((BigDecimal)value).intValue();
    }

    public static Long getLongValue(Object value) {
        if (value == null)
            return null;
        else
            return ((BigDecimal)value).longValue();
    }

    public static String getStringValue(Object value) {
        if (value == null)
            return null;
        else
            return (String)value;
    }

    public static Date getDateValue(Object value) {
        if (value == null)
            return null;
        else
            return new Date(((Timestamp)value).getTime());
    }

    public static String getFormattedDate(Object value,
                                          SimpleDateFormat sdfFormat) {
        if (value == null) {
            return null;
        } else {
            Date date = new Date(((Timestamp)value).getTime());
            return sdfFormat.format(date);
        }
    }    
}
