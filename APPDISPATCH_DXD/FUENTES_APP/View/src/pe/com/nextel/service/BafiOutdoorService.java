package pe.com.nextel.service;

import java.util.HashMap;
import java.util.List;

import javax.naming.Context;

import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;

import pe.com.nextel.bean.InstalacionBean;
import pe.com.nextel.bean.estadoinstalacion.MotivoBean;
import pe.com.nextel.ejb.SEJBBafiOutdoorRemote;
import pe.com.nextel.ejb.SEJBBafiOutdoorRemoteHome;
import pe.com.nextel.util.MiUtil;

public class BafiOutdoorService extends GenericService{
    
    private static Logger logger = Logger.getLogger(BafiOutdoorService.class);
    
    public BafiOutdoorService() {
        super();
    }
    
    public static SEJBBafiOutdoorRemote getSEJBRemote() {
        try {
        
            Context context = MiUtil.getInitialContext();        
            SEJBBafiOutdoorRemoteHome remoteHome = (SEJBBafiOutdoorRemoteHome) PortableRemoteObject.narrow(context.lookup("SEJBBafiOutdoorDispatch"), SEJBBafiOutdoorRemoteHome.class);
            return remoteHome.create();            
          
        } catch (Exception ex) {
            logger.error("[BafiOutdoorService][getSEJBRemote] Error inesperado",ex);
            return null;
        }
    }
    
    public boolean validarSolicitudOlBafi(long solicitudOlId){
        boolean flagAgendamiento =false;
        try{
            flagAgendamiento = getSEJBRemote().validarSolicitudOlBafi(solicitudOlId);
           
        } catch(Exception e){
            flagAgendamiento = false;
            logger.error("[BafiOutdoorService][validarSolicitudOlBafi] Error inesperado",e);
        }
        finally{
            return flagAgendamiento;
        }
    }    
    public void insertarInstalacion(InstalacionBean instalacionBean){
        try{
           getSEJBRemote().insertarInstalacion(instalacionBean);           
        } catch(Exception e){
            logger.error("[BafiOutdoorService][insertarInstalacion] Error inesperado",e);
        }
    }
    
    public void actualizarInstalacion(InstalacionBean instalacionBean){
        try{
           getSEJBRemote().actualizarInstalacion(instalacionBean);           
        } catch(Exception e){
            logger.error("[BafiOutdoorService][actualizarInstalacion] Error inesperado",e);
        }
    }
    
    public List<MotivoBean> obtenerListaMotivos(){
        List<MotivoBean> motivosList =  null;
        try{
           motivosList = getSEJBRemote().obtenerListaMotivos();           
        } catch(Exception e){
            motivosList = null;
            logger.error("[BafiOutdoorService][obtenerListaMotivos] Error inesperado",e);
        }        
        return motivosList;
    }
    
    public boolean validarInstalacionEdit(Long numOrderId){
        boolean respuesta = false;
        try{
           respuesta = getSEJBRemote().validarInstalacionEdit(numOrderId);           
        } catch(Exception e){
            logger.error("[BafiOutdoorService][validarInstalacionEdit] Error inesperado",e);
        }
        
        return respuesta;
    }
    
    public InstalacionBean obtenerInstalacionPorOrdenId(long ordenId){
        InstalacionBean instalacionBean = null;
        try{
           instalacionBean = getSEJBRemote().obtenerInstalacionPorOrdenId(ordenId);           
        } catch(Exception e){
            logger.error("[BafiOutdoorService][obtenerInstalacionPorOrdenId] Error inesperado",e);
        }
        
        return instalacionBean;
    }
    
    public HashMap validarRegularizarOrdenOutdoor(Long ordenId, String imei){
        HashMap hshDataMap = new HashMap();
        try{
          hshDataMap = getSEJBRemote().validarRegularizarOrdenOutdoor(ordenId, imei);           
        } catch(Exception e){
            logger.error("[BafiOutdoorService][validarRegularizarOrdenOutdoor] Error inesperado",e);
        }
        
        return hshDataMap;
    }
    
    public HashMap regularizarOrdenOutdoor(Long ordenId, String imei, String almacenId, String creadoPor){
        HashMap hshDataMap = new HashMap();
        try{
          hshDataMap = getSEJBRemote().regularizarOrdenOutdoor(ordenId, imei, almacenId, creadoPor);           
        } catch(Exception e){
            logger.error("[BafiOutdoorService][validarRegularizarOrdenOutdoor] Error inesperado",e);
        }
        return hshDataMap;
    }
}
