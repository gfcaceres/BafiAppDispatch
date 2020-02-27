package pe.com.nextel.ejb.tde; 

import java.rmi.RemoteException;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJBObject;

import pe.com.nextel.bean.DominioBean;
import pe.com.nextel.bean.RequestManualBean;
import pe.com.nextel.bean.StockBean;


public interface SEJBRequestManualRemote extends EJBObject {

    HashMap getModalityList() throws SQLException, Exception, RemoteException;

    HashMap getEstadoList() throws SQLException, Exception, RemoteException;

    HashMap getAccesoriosList() throws SQLException, Exception,
                                       RemoteException;

    HashMap getTipoDocumentoList() throws SQLException, Exception,
                                          RemoteException;

    HashMap getSolucionNegocioList() throws SQLException, Exception,
                                            RemoteException;

    HashMap getLineaProductoListByIdsolucion(DominioBean solucionNegocio) throws SQLException,
                                                                                 Exception,
                                                                                 RemoteException;

    HashMap getProductoListByLineaproductoid(DominioBean dominioBean) throws SQLException,
                                                                             Exception,
                                                                             RemoteException;

    HashMap getMotivosListByUserLogin(String userLogin) throws SQLException,
                                                               Exception,
                                                               RemoteException;

    HashMap saveRequestManual(RequestManualBean requestManualBean,
                              List listaDetailRequestManual) throws SQLException,
                                                                    Exception,
                                                                    RemoteException;

    HashMap getRepuestosList(DominioBean solucionNegocio) throws SQLException,
                                                                 Exception,
                                                                 RemoteException;

    HashMap getListaEstadoSolicitud() throws SQLException, Exception,
                                             RemoteException;

    StockBean validarStock(StockBean stockBean) throws SQLException, Exception,
                                                       RemoteException;

    HashMap obtenerFlagValidarProductosAndAccesorio(int lineaProducto) throws SQLException,
                                                                              Exception,
                                                                              RemoteException;

}
