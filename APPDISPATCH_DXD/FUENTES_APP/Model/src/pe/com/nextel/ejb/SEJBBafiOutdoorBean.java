package pe.com.nextel.ejb;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.log4j.Logger;

import pe.com.nextel.bean.InstalacionBean;
import pe.com.nextel.bean.estadoinstalacion.MotivoBean;
import pe.com.nextel.dao.BafiOutdoorDAO;
import pe.com.nextel.dao.EstadoInstalacionDAO;
import pe.com.nextel.dao.InstalacionDAO;
import pe.com.nextel.dao.RequestDAO;
import pe.com.nextel.exception.UserException;

public class SEJBBafiOutdoorBean implements SessionBean {
    
    private static Logger logger  = Logger.getLogger(SEJBBafiOutdoorBean.class);
    private SessionContext _context;
    private BafiOutdoorDAO bafiOutdoorDAO = null;
    private InstalacionDAO instalacionDAO = null;
    private EstadoInstalacionDAO estadoInstalacionDAO = null;
    
    public void ejbCreate() {
        bafiOutdoorDAO = new BafiOutdoorDAO();
        instalacionDAO = new InstalacionDAO();
        estadoInstalacionDAO = new EstadoInstalacionDAO();
    }

    public void setSessionContext(SessionContext context) throws EJBException {
        _context = context;
    }

    public void ejbRemove() throws EJBException {
    }

    public void ejbActivate() throws EJBException {
    }

    public void ejbPassivate() throws EJBException {
    }
    
    public List<MotivoBean> obtenerListaMotivos() throws UserException {        
        return estadoInstalacionDAO.obtenerListaMotivos();
    }
    
    public boolean validarSolicitudOlBafi(long solicitudOlId) throws UserException{
        boolean respuesta = false;
        logger.info("[SEJBBafiOutdoorBean][validarSolicitudOlBafi] inicio");
        logger.info("[SEJBBafiOutdoorBean][validarSolicitudOlBafi] entrada solicitudOlId["+solicitudOlId+"]");
        respuesta = bafiOutdoorDAO.validarSolicitudOlBafi(solicitudOlId);
        logger.info("[SEJBBafiOutdoorBean][validarSolicitudOlBafi] salida respuesta["+respuesta+"]");
        logger.info("[SEJBBafiOutdoorBean][validarSolicitudOlBafi] fin");
        return respuesta;
    }
    
    public void insertarInstalacion(InstalacionBean instalacionBean)throws UserException {
        logger.info("[SEJBBafiOutdoorBean][insertarInstalacion] inicio");
        logger.info("[SEJBBafiOutdoorBean][insertarInstalacion] entrada "+instalacionBean.toString());
        instalacionDAO.insertar(instalacionBean);
        logger.info("[SEJBBafiOutdoorBean][insertarInstalacion] fin");
    }  
    
    public void actualizarInstalacion(InstalacionBean instalacionBean)throws UserException {
        logger.info("[SEJBBafiOutdoorBean][actualizarInstalacion] inicio");
        logger.info("[SEJBBafiOutdoorBean][actualizarInstalacion] entrada "+instalacionBean.toString());        
        instalacionDAO.actualizar(instalacionBean);
        logger.info("[SEJBBafiOutdoorBean][actualizarInstalacion] fin");
    }  
    
    public boolean validarInstalacionEdit(Long numOrderId) throws UserException {
        boolean respuesta = false;
        logger.info("[SEJBBafiOutdoorBean][validarInstalacionEdit] inicio");
        logger.info("[SEJBBafiOutdoorBean][validarInstalacionEdit] entrada numOrderId["+numOrderId+"]");        
        int editar = bafiOutdoorDAO.validarInstalacionEdit(numOrderId);
        if (editar>0){
            respuesta = true;
        }
        logger.info("[SEJBBafiOutdoorBean][validarInstalacionEdit] salida respuesta["+respuesta+"]");
        logger.info("[SEJBBafiOutdoorBean][validarInstalacionEdit] fin");
        return respuesta;
    }     
    
    public InstalacionBean obtenerInstalacionPorOrdenId(long ordenId) throws UserException{
        InstalacionBean instalacionBean = null;
        logger.info("[SEJBBafiOutdoorBean][obtenerInstalacionPorOrdenId] inicio");
        logger.info("[SEJBBafiOutdoorBean][obtenerInstalacionPorOrdenId] entrada ordenId["+ordenId+"]");        
        instalacionBean= instalacionDAO.obtenerPorOrdenId(ordenId);
        logger.info("[SEJBBafiOutdoorBean][obtenerInstalacionPorOrdenId] salida " + instalacionBean.toString());
        logger.info("[SEJBBafiOutdoorBean][obtenerInstalacionPorOrdenId] fin");
        return instalacionBean;
    }
    
    public boolean validarProcesoBafiActivo() throws UserException{
        String flag = bafiOutdoorDAO.obtenerConfigUnit("BAFI_OUTDOOR_EMPRESA","FLAG_ENCENDIDO");
        if (flag == null || flag.equals("N")) {
            return false;
        }
        return true;
    }    
}
