package pe.com.nextel.service;

import java.util.HashMap;
import java.util.List;

import javax.naming.Context;

import javax.rmi.PortableRemoteObject;

import org.apache.log4j.Logger;

import pe.com.nextel.bean.DominioBean;
import pe.com.nextel.bean.RequestManualBean;
import pe.com.nextel.bean.StockBean;
import pe.com.nextel.ejb.SEJBRequestManualRemote;
import pe.com.nextel.ejb.SEJBRequestManualRemoteHome;
import pe.com.nextel.util.MiUtil;


public class RequestManualService extends GenericService{

  protected static Logger logger = Logger.getLogger(RequestManualService.class);
  public RequestManualService(){}
  
  public static SEJBRequestManualRemote getSEJBRequestManualRemote() {
    try {
        final Context context = MiUtil.getInitialContext();
        final SEJBRequestManualRemoteHome remoteHome = (SEJBRequestManualRemoteHome) PortableRemoteObject.narrow(context.lookup("SEJBRequestManualDispatch"), SEJBRequestManualRemoteHome.class);
        return remoteHome.create();            
    } catch (Exception ex) {
        System.out.println("Exception : [RequestManualService][SEJBRequestManualRemote][" + ex.getMessage() + "]");
        return null;
    }
  } 
  
  public HashMap getModalityList() {
    HashMap mapaModalidad = null;
      try {        
        mapaModalidad = getSEJBRequestManualRemote().getModalityList();
      }catch(Throwable t){
        manageCatch(mapaModalidad, t);
      }
    return mapaModalidad;
  }
  
  public HashMap getEstadoList() {
    HashMap mapaEstado = null;
      try {        
        mapaEstado = getSEJBRequestManualRemote().getEstadoList();
      }catch(Throwable t){
        manageCatch(mapaEstado, t);
      }
    return mapaEstado;
  }
  
  
  public HashMap getAccesoriosList() {
    HashMap mapaAccesorios = null;
      try {        
        mapaAccesorios = getSEJBRequestManualRemote().getAccesoriosList();
      }catch(Throwable t){
        manageCatch(mapaAccesorios, t);
      }
    return mapaAccesorios;
  }
  
  public HashMap getTipoDocumentoList() {
    HashMap mapaTipoDocumento = null;
      try {        
        mapaTipoDocumento = getSEJBRequestManualRemote().getTipoDocumentoList();
      }catch(Throwable t){
        manageCatch(mapaTipoDocumento, t);
      }
    return mapaTipoDocumento;
  }
  
  public HashMap getSolucionNegocioList() {
    HashMap mapaSolucionNegocio = null;
      try {        
        mapaSolucionNegocio = getSEJBRequestManualRemote().getSolucionNegocioList();
      }catch(Throwable t){
        manageCatch(mapaSolucionNegocio, t);
      }
    return mapaSolucionNegocio;
  }
  
  public HashMap getLineaProductoListByIdsolucion(DominioBean solucionNegocio) {
    HashMap mapaLineaProducto = null;
      try {        
        mapaLineaProducto = getSEJBRequestManualRemote().getLineaProductoListByIdsolucion(solucionNegocio);
      }catch(Throwable t){
        manageCatch(mapaLineaProducto, t);
      }
    return mapaLineaProducto;
  }
  
  public HashMap getProductoListByLineaproductoid(DominioBean dominioBean) {
    HashMap mapaProducto = null;
      try {        
        mapaProducto = getSEJBRequestManualRemote().getProductoListByLineaproductoid(dominioBean);
      }catch(Throwable t){
        manageCatch(mapaProducto, t);
      }
    return mapaProducto;
  }
  
  public HashMap getMotivosListByUserLogin(String userLogin){
    HashMap mapaUsuario = null;
      try {        
        mapaUsuario = getSEJBRequestManualRemote().getMotivosListByUserLogin(userLogin);
      }catch(Throwable t){
        manageCatch(mapaUsuario, t);
      }
    return mapaUsuario;
  }
  
  public HashMap saveRequestManual(RequestManualBean requestManualBean, List listaDetailRequestManual ){
    HashMap mapaDatos = null;
      try {        
        mapaDatos = getSEJBRequestManualRemote().saveRequestManual(requestManualBean,listaDetailRequestManual);
      }catch(Throwable t){
        manageCatch(mapaDatos, t);
      }
    return mapaDatos;
  }
  
  
  public HashMap getRepuestosList(DominioBean solucionNegocio) {
    HashMap mapaDatos = null;
      try {        
        mapaDatos = getSEJBRequestManualRemote().getRepuestosList(solucionNegocio);
      }catch(Throwable t){
        manageCatch(mapaDatos, t);
      }
    return mapaDatos;
  }
  
   public HashMap getListaEstadoSolicitud() {
      HashMap mapaDatos = null;
      try {        
        mapaDatos = getSEJBRequestManualRemote().getListaEstadoSolicitud();
      }catch(Throwable t){
        manageCatch(mapaDatos, t);
      }
    return mapaDatos;
  }
  
  public StockBean validarStock(StockBean stockBean) {
    StockBean stock = null;
    HashMap mapaDatos = null;
      try {        
        stock = getSEJBRequestManualRemote().validarStock(stockBean);
      }catch(Throwable t){
        manageCatch(mapaDatos, t);
      }
      
    return stock;
  }
      
  public HashMap obtenerFlagValidarProductosAndAccesorio(int lineaProducto){      
    HashMap mapaDatos = null;
      try {        
        mapaDatos = getSEJBRequestManualRemote().obtenerFlagValidarProductosAndAccesorio(lineaProducto);
      }catch(Throwable t){
        manageCatch(mapaDatos, t);
      }
      
    return mapaDatos;
  }
  
}