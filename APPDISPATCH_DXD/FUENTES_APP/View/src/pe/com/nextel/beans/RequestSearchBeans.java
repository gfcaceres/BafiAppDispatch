package pe.com.nextel.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pe.com.nextel.bean.DominioBean;
import pe.com.nextel.service.GeneralService;
import pe.com.nextel.util.Constante;


public class RequestSearchBeans 
{
private List listaDivisionNegocio;
private List listaPersonal;
private List listaLocation;
private List listaOrdenPagada;
private List listaEstadoSolicitud;
  
  public RequestSearchBeans(){
    //iniciar();
    detail_request();
  }
  
  public void iniciar(){
    //Obtner lista division de negocio
    GeneralService generalService = new GeneralService(); 
    HashMap mapaData  = generalService.getDivisionList();
    listaDivisionNegocio =(ArrayList)mapaData.get(Constante.SOLICITUD_LISTA_DIVISION_NEGOCIO); 
    //Obtener lista ubicacion
    mapaData = generalService.getLocationList();
    listaLocation = (ArrayList)mapaData.get(Constante.SOLICITUD_LISTA_UBICACION); 
    //Obtener lista orden pagada
    listaOrdenPagada = obtenerListaOrdenPagada();
    //Obtener lista estado de la solicitud
    listaEstadoSolicitud = obtenerEstadosSolicitud();
    
  }
  
  public List obtenerListaOrdenPagada(){
    
    List listaBean = new ArrayList();
    DominioBean bean1 = new DominioBean();
    DominioBean bean2 = new DominioBean();
    
    bean1.setValor(Constante.ORDEN_PAGADA_SI);
    bean1.setDescripcion(Constante.ORDEN_PAGADA_SI);
    listaBean.add(bean1);
    bean2.setValor(Constante.ORDEN_PAGADA_NO);
    bean2.setDescripcion(Constante.ORDEN_PAGADA_NO);
    listaBean.add(bean2);
    return listaBean;
  }
  
  
  public List obtenerEstadosSolicitud(){
    List lista = new ArrayList();
    DominioBean bean1 = new DominioBean();    
    DominioBean bean2 = new DominioBean();
    DominioBean bean3 = new DominioBean();
    DominioBean bean4 = new DominioBean();
    DominioBean bean5 = new DominioBean();
    DominioBean bean6 = new DominioBean();
    
    bean1.setValor(Constante.ESTADO_PENDIENTE);
    bean1.setDescripcion(Constante.ESTADO_PENDIENTE);
    lista.add(bean1);
    bean2.setValor(Constante.ESTADO_ATENDIDA);
    bean2.setDescripcion(Constante.ESTADO_ATENDIDA);
    lista.add(bean2);
    bean3.setValor(Constante.ESTADO_DELIVERY);
    bean3.setDescripcion(Constante.ESTADO_DELIVERY);
    lista.add(bean3);
    bean4.setValor(Constante.ESTADO_ENTREGADA);
    bean4.setDescripcion(Constante.ESTADO_ENTREGADA);
    lista.add(bean4);
    bean5.setValor(Constante.ESTADO_ANULADA);
    bean5.setDescripcion(Constante.ESTADO_ANULADA);
    lista.add(bean5);
    bean6.setValor(Constante.ESTADO_RECHAZADO);
    bean6.setDescripcion(Constante.ESTADO_RECHAZADO);
    lista.add(bean6);
    
    return lista;    
    
  }
  
  public void detail_request()
  {
    GeneralService generalService = new GeneralService(); 
    HashMap mapPersonal  = generalService.getValueNpTable(Constante.PERSONAL_DELIVERY);
    
    listaPersonal = (ArrayList)mapPersonal.get(Constante.OBJ_ARRAYLIST);
    System.out.print("EL tamaño de la lista es : "+listaPersonal.size());
  }
 

  public void setListaDivisionNegocio(List listaDivisionNegocio)
  {
    this.listaDivisionNegocio = listaDivisionNegocio;
  }


  public List getListaDivisionNegocio()
  {
    return listaDivisionNegocio;
  }
  
  public void setListaPersonal(List listaPersonal)
  {
    this.listaPersonal = listaPersonal;
  }
  
  public List getListaPersonal()
  {
    return listaPersonal;
  }


  public void setListaLocation(List listaLocation)
  {
    this.listaLocation = listaLocation;
  }


  public List getListaLocation()
  {
    return listaLocation;
  }


  public void setListaOrdenPagada(List listaOrdenPagada)
  {
    this.listaOrdenPagada = listaOrdenPagada;
  }


  public List getListaOrdenPagada()
  {
    return listaOrdenPagada;
  }


  public void set_listaOrdenPagada(List listaOrdenPagada)
  {
    this.listaOrdenPagada = listaOrdenPagada;
  }


  public List get_listaOrdenPagada()
  {
    return listaOrdenPagada;
  }


  public void setListaEstadoSolicitud(List listaEstadoSolicitud)
  {
    this.listaEstadoSolicitud = listaEstadoSolicitud;
  }


  public List getListaEstadoSolicitud()
  {
    return listaEstadoSolicitud;
  }
}