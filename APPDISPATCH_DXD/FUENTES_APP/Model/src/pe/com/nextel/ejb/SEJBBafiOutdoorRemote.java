package pe.com.nextel.ejb;

import java.rmi.RemoteException;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJBObject;

import pe.com.nextel.bean.InstalacionBean;
import pe.com.nextel.bean.estadoinstalacion.MotivoBean;
import pe.com.nextel.exception.UserException;

public interface SEJBBafiOutdoorRemote extends EJBObject {
    
    List<MotivoBean> obtenerListaMotivos() throws UserException, RemoteException;
    boolean validarSolicitudOlBafi(long solicitudOlId) throws UserException,RemoteException;
    void insertarInstalacion(InstalacionBean instalacionBean) throws UserException,RemoteException; 
    void actualizarInstalacion(InstalacionBean instalacionBean) throws UserException,RemoteException;
    boolean validarInstalacionEdit(Long numOrderId) throws UserException,RemoteException;
    InstalacionBean obtenerInstalacionPorOrdenId(long ordenId)  throws UserException,RemoteException;
    boolean validarProcesoBafiActivo() throws UserException,RemoteException;
    HashMap validarRegularizarOrdenOutdoor(Long ordenId, String imei) throws UserException,RemoteException;
    void regularizarOrdenOutdoor(Long ordenId, String imei, String almacenId, String creadoPor)throws UserException,RemoteException;
}
