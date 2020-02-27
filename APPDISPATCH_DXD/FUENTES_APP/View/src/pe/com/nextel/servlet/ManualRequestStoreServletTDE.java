package pe.com.nextel.servlet; 

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.nextel.bean.DetailRequestManualBean;
import pe.com.nextel.bean.DominioBean;
import pe.com.nextel.bean.RequestManualBean;
import pe.com.nextel.bean.StockBean;
import pe.com.nextel.service.tde.RequestManualService;
import pe.com.nextel.util.Constante;
import pe.com.nextel.util.MiUtil;


public class ManualRequestStoreServletTDE extends HttpServlet 
{
  private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
  

  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
   
    
  }

  
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();        
        String metodo=null;
        try {
          metodo = request.getParameter(Constante.SERVLET_PARAM_METHOD);
          
          if(metodo != null && metodo.equals(Constante.SERVLET_PARAM_GET_PRODUCTO_BY_LINEA)){
            obtnerProductoByLineaProducto(request,response );
          }
          if(metodo != null && metodo.equals("GET_LINEA_PRODUCTO_BY_SOLUCION")){
              obtenerLineaProductoBySolucion(request,response);
          }          
         
          if(metodo != null && metodo.equals("NEW_REQUEST_MANUAL")){
            nuevaSolicitud(request, response);
          }
          
          if(metodo != null && metodo.equals("OBTENER_ACCESORIOS")){
            obtenerListaAccesoriosRepuesto(request, response);
          }  
          if(metodo != null && metodo.equals("OBTENER_ACCESORIOS_AND_PRODUCTOS")){
            obtenerAccesoriosProductos(request, response);
          } 
          
          if(metodo != null && metodo.equals("VALIDAR_ALMACEN")){
            validarAlmacen(request, response);
          } 
            
        }catch(Exception ex){
          //throw new Exception(ex);
          System.out.println("ERROR: " +ex.getMessage());
        }
        
        finally {            
            out.close();
        }
    }
  
  
  public void obtnerProductoByLineaProducto(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
  //public void obtnerProductoByLineaProducto(int idLineaProducto)throws ServletException, IOException{
    
    response.setContentType("text/plaint;charset=UTF-8");    
    System.out.println("--obtnerProductoByLineaProducto--");    
    PrintWriter out = response.getWriter();
    
    String optionHtml="";
    
    List listaProducto = null;
    List listaAccesorioRepuesto = null;
       
    HashMap mapaData = null;
    String mensaje = null;
    
      RequestManualService requestManualService =  new RequestManualService(); 
        
      String strIdLineaProducto = MiUtil.trimNotNull(request.getParameter("ID_LINEAPRODUCTO"));     
      String strIdModalidad = MiUtil.trimNotNull(request.getParameter("ID_MODALIDAD"));
      
      System.out.println(">>strIdLineaProducto = " + strIdLineaProducto);
      System.out.println(">>strIdModalidad = " + strIdModalidad);
      //String strIdProducto = null;//MiUtil.trimNotNull(request.getParameter("ID_PRODUCTO"));
     
      //int idLineaProducto = Integer.parseInt(strIdLineaProducto.trim());
      
      DominioBean producto =  new DominioBean();
      producto.setParam1(strIdLineaProducto);
      producto.setParam2(strIdModalidad);
      mapaData = requestManualService.getProductoListByLineaproductoid(producto);
      listaProducto = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_PRODUCTO);
    
      optionHtml = optionHtml +  "<option value=''></option>";  
      System.out.println("...Lista productos...");
      if(listaProducto.size()>0){
       
        for(int i = 0; i<listaProducto.size(); i++){    
          DominioBean dominoBean = (DominioBean)listaProducto.get(i);      
          optionHtml = optionHtml + "<option value='"+MiUtil.trimNotNull(dominoBean.getValor())+"'>"+MiUtil.trimNotNull(dominoBean.getDescripcion())+"</option>"; 
          
        }      
      }
      else{
        optionHtml = "<option value=''>No existe productos</option>";                    
      } 
        
      optionHtml = optionHtml + "|";        
      optionHtml = optionHtml +  "<option value=''></option>"; 
      
      DominioBean dominioBean = new DominioBean();    
      dominioBean.setParam1("");
      dominioBean.setParam2(MiUtil.trimNotNull(strIdLineaProducto));
      dominioBean.setParam3(MiUtil.trimNotNull(strIdModalidad));
    
      mapaData = requestManualService.getRepuestosList(dominioBean);
      listaAccesorioRepuesto = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_REPUESTOS);
         
      System.out.println("...Lista Accesorios Repuestos...");
      if(listaAccesorioRepuesto.size() > 0){
          for(int i = 0; i<listaAccesorioRepuesto.size(); i++){    
              DominioBean dominoBean = (DominioBean)listaAccesorioRepuesto.get(i);      
              optionHtml = optionHtml + "<option value='"+ MiUtil.trimNotNull(dominoBean.getValor())+"'>"+MiUtil.trimNotNull(dominoBean.getDescripcion())+"</option>";
              
              //String borrar = "<option value='"+ MiUtil.trimNotNull(dominoBean.getValor())+"'>"+MiUtil.trimNotNull(dominoBean.getDescripcion())+"</option>"; 
              //System.out.println(borrar);
          } 
      }else{
          optionHtml = optionHtml + "";
      }
    
      //OBTENEMOS LOS FLAG DE VALIDACION
      DominioBean dominioBean2 = null;
      mapaData = requestManualService.obtenerFlagValidarProductosAndAccesorio(MiUtil.parseInt(strIdLineaProducto));
      dominioBean2 =  (DominioBean)mapaData.get(Constante.DOMINIOBEAN);
      mensaje = (String)mapaData.get(Constante.MESSAGE_OUTPUT);
      
      optionHtml += "|" + dominioBean2.getFlagValidaProducto();
      optionHtml += "|" + dominioBean2.getFlagValidaAccesorio();
      
      System.out.println(">>listaProducto.size() = "+listaProducto.size());
      System.out.println(">>listaAccesorioRepuesto.size() = "+listaAccesorioRepuesto.size());
      System.out.println(">>FlagValidaProducto = "+dominioBean2.getFlagValidaProducto());
      System.out.println(">>FlagValidaAccesorio = "+dominioBean2.getFlagValidaAccesorio());      
      /*System.out.println(">>optionHtml = " + optionHtml );*/
        
      out.write(optionHtml);
      out.close();
    
    
       
        
  } 
  
  
 
  
 
  
  
 
  
  public void iniciarPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    
          List listaModalidad = null;
          List listaEstado = null; 
          List listaAccesorios = null; 
          List listaTipoDocumento = null;
          List listaSolucionNegocio = null;
          List listaLineaProducto = null;
          List listaMotivos = null;
          List listaRepuestos = null; 
          List listaProducto = null;
          
          HashMap mapaData = null;
          String strUsuario = null;
          System.out.println("--Solicitud Manual Servlet--");
          System.out.println("--iniciarPagina--");
          strUsuario = MiUtil.trimNotNull((String)request.getAttribute("strUsuario"));
          System.out.println("strUsuario = " + strUsuario);
          
          RequestManualService requestManualService =  new RequestManualService();    
          
          mapaData = requestManualService.getModalityList();
          listaModalidad = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_MODALIDAD);
          request.setAttribute(Constante.SOLICITUD_MANUAL_LISTA_MODALIDAD,listaModalidad);  
          System.out.println("Servlet aqui bien");
          //mapaData = requestManualService.getEstadoList();
          mapaData = requestManualService.getListaEstadoSolicitud();
          listaEstado = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_ESTADO);
          System.out.println("Servlet listaEstado.size() = " + listaEstado.size());
          request.setAttribute(Constante.SOLICITUD_MANUAL_LISTA_ESTADO,listaEstado);
          
          mapaData = requestManualService.getAccesoriosList();
          listaAccesorios = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_ACCESORIOS);
          request.setAttribute(Constante.SOLICITUD_MANUAL_LISTA_ACCESORIOS,listaAccesorios);
          
          mapaData = requestManualService.getTipoDocumentoList();
          listaTipoDocumento = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_TIPO_DOCUMENTO);
          request.setAttribute(Constante.SOLICITUD_MANUAL_LISTA_TIPO_DOCUMENTO,listaTipoDocumento);
          
          mapaData = requestManualService.getSolucionNegocioList();
          listaSolucionNegocio = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_SOLUCION_NEGOCIO);
          request.setAttribute(Constante.SOLICITUD_MANUAL_LISTA_SOLUCION_NEGOCIO,listaSolucionNegocio);
          
          
          //---CARGAMOS LA LISTA DE LINEA DE PRODUCTOS---
          /*DominioBean solucionNegocio = (DominioBean)listaSolucionNegocio.get(0);
          mapaData = requestManualService.getLineaProductoListByIdsolucion(solucionNegocio);
          listaLineaProducto = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_LINEA_PRODUCTO);
          request.setAttribute(Constante.SOLICITUD_MANUAL_LISTA_LINEA_PRODUCTO,listaLineaProducto);*/
          
          //--CARGAMOS LOS COMBO DE PRODUCCTO---
          /*DominioBean producto =  new DominioBean();
          int idLineaProducto = MiUtil.parseInt(((DominioBean)listaLineaProducto.get(0)).getValor());     
          
          producto.setParam1(MiUtil.trimNotNull(((DominioBean)listaLineaProducto.get(0)).getValor()));
          producto.setParam2(MiUtil.trimNotNull(((DominioBean)listaModalidad.get(0)).getValor()));          
          mapaData = requestManualService.getProductoListByLineaproductoid(producto);
          listaProducto = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_PRODUCTO);
          request.setAttribute(Constante.SOLICITUD_MANUAL_LISTA_PRODUCTO,listaProducto); */
                    
          
          mapaData = requestManualService.getMotivosListByUserLogin(strUsuario);
          listaMotivos = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_MOTIVOS);
          request.setAttribute(Constante.SOLICITUD_MANUAL_LISTA_MOTIVOS,listaMotivos);
          
          //---CARGANDO LOS DATOS ACCESORIOS/REPUESTOS---
          /*DominioBean solucionNegocioBean = new DominioBean();
          solucionNegocioBean.setParam1(((DominioBean)listaProducto.get(0)).getValor());// ID PRODUCTO
          solucionNegocioBean.setParam2(((DominioBean)listaLineaProducto.get(0)).getValor());// ID Linea Producto
          solucionNegocioBean.setParam3(((DominioBean)listaModalidad.get(0)).getValor());// ID MODALIDAD                             
          mapaData = requestManualService.getRepuestosList(solucionNegocioBean);
          listaRepuestos = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_REPUESTOS);
          int tamanioAccesorioRepuesto = listaRepuestos.size();
          request.setAttribute(Constante.SOLICITUD_MANUAL_LISTA_REPUESTOS,listaRepuestos);
          request.setAttribute("tamanioAccesorioRepuesto",String.valueOf(tamanioAccesorioRepuesto));
          */
          
          System.out.println("--Cargo bien los datos--");
    
  }
  
  public void nuevaSolicitud(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
      
      System.out.println("Servlet - nuevaSolicitud");
      
      List listaRequestManual =  new ArrayList();
      HashMap mapaDatos = null;
      RequestManualService requestManualService = new RequestManualService();     
      RequestManualBean requestManualBean =  new RequestManualBean();     
      
      requestManualBean.setOrdenNumber(MiUtil.trimNotNull(request.getParameter("txtNroOrden")));      
      requestManualBean.setCod(MiUtil.trimNotNull(request.getParameter("txtCodigo")));
      requestManualBean.setCodBSCS(MiUtil.trimNotNull(request.getParameter("txtCodBscs")));
      requestManualBean.setDocumentType(MiUtil.trimNotNull(request.getParameter("cboTipoDocumento")));
      requestManualBean.setRUC(MiUtil.trimNotNull(request.getParameter("txtRuc")));
      requestManualBean.setDeliveryAddress(MiUtil.trimNotNull(request.getParameter("cboDireccionEntrega")));
      requestManualBean.setDocumentNumber(MiUtil.trimNotNull(request.getParameter("txtNroDocumento")));
      requestManualBean.setRazonSolcial(MiUtil.trimNotNull(request.getParameter("txtCustomerName")));      
      //-------      
      requestManualBean.setDateIssue(MiUtil.trimNotNull(request.getParameter("txtFechaEmision")));
      requestManualBean.setLocation(MiUtil.trimNotNull(request.getParameter("txtUbicacion")));
      requestManualBean.setUser(MiUtil.trimNotNull(request.getParameter("txtUsuario")));
      
      requestManualBean.setBuildingId( MiUtil.parseInt(MiUtil.trimNotNull(request.getParameter("idbuilding"))));
      
      requestManualBean.setStatus(MiUtil.trimNotNull(request.getParameter("txtEstado")));
      requestManualBean.setObservation(MiUtil.trimNotNull(request.getParameter("txtObservaciones")));    
            
      System.out.println("-----Servlet----: Grabar solicitud manual");      
      System.out.println("Datos:");      
      System.out.println("requestManualBean.getOrdenNumber(): " + requestManualBean.getOrdenNumber());      
      System.out.println("requestManualBean.getCod(): " + requestManualBean.getCod());
      System.out.println("requestManualBean.getCodBSCS(): " + requestManualBean.getCodBSCS());
      System.out.println("requestManualBean.getDocumentType(): " +requestManualBean.getDocumentType()) ;
      System.out.println("requestManualBean.getRUC(): " + requestManualBean.getDocumentType());
      System.out.println("requestManualBean.getDeliveryAddress(): " + requestManualBean.getDeliveryAddress());
      System.out.println("requestManualBean.getDocumentNumber(): " + requestManualBean.getDocumentNumber());
      System.out.println("requestManualBean.getRazonSolcial(): " + requestManualBean.getRazonSolcial());      
      System.out.println("requestManualBean.getDateIssue(): " + requestManualBean.getDateIssue());
      System.out.println("requestManualBean.getLocation(): " + requestManualBean.getLocation());
      System.out.println("requestManualBean.getUser(): " + requestManualBean.getUser());      
      System.out.println("requestManualBean.getBuildingId(): " + requestManualBean.getBuildingId()) ;      
      System.out.println("requestManualBean.getStatus(): " + requestManualBean.getStatus());
      System.out.println("requestManualBean.getObservation(): " + requestManualBean.getObservation());    
      
      System.out.println("......................................"); 
      
      //Recuperamos el numero de Filas
      int filas = MiUtil.parseInt(MiUtil.trimNotNull(request.getParameter("txtNroFilas")));
      List listaDetalleSolicitudManual = new ArrayList();
      for(int i=0; i<filas; i++){
          
          DetailRequestManualBean detailRequestManualBean = new DetailRequestManualBean();
    
          detailRequestManualBean.setProductCode(MiUtil.trimNotNull(request.getParameter("hdnIdProducto_"+i)));
          detailRequestManualBean.setProductDescription(MiUtil.trimNotNull(request.getParameter("hdnDescripcionProducto_"+i)));
          detailRequestManualBean.setReplacementCode(MiUtil.trimNotNull(request.getParameter("hdnIdRepuesto_"+i)));
          detailRequestManualBean.setReplacementDescription(MiUtil.trimNotNull(request.getParameter("hdnDescripcionRepuesto_"+i)));
          detailRequestManualBean.setReasonCode(MiUtil.trimNotNull(request.getParameter("hdnIdMotivo_"+i)));
          detailRequestManualBean.setReasonDescription(MiUtil.trimNotNull(request.getParameter("hdnDescripcionMotivo_"+i)));
          detailRequestManualBean.setModeCode(MiUtil.trimNotNull(request.getParameter("hdnIdModalidad_"+i)));
          detailRequestManualBean.setModeDescription(MiUtil.trimNotNull(request.getParameter("hdnDescripcionModalidad_"+i)));
          detailRequestManualBean.setStatusCode(MiUtil.trimNotNull(request.getParameter("hdnIdEstado_"+i)));
          detailRequestManualBean.setStatusDescription(MiUtil.trimNotNull(request.getParameter("hdnDescripcionEstado_"+i)));
          detailRequestManualBean.setAccesoryCode(MiUtil.trimNotNull(request.getParameter("hdnIdAccesorio_"+i)));
          detailRequestManualBean.setAccesoryDescription(MiUtil.trimNotNull(request.getParameter("hdnDescripcionAccesorio_"+i)));
          detailRequestManualBean.setQuantity(MiUtil.trimNotNull(request.getParameter("hdnCantidad_"+i)));
    
          detailRequestManualBean.setBusinessSolucionCode(MiUtil.trimNotNull(request.getParameter("hdnCodigoSolucionNegocio_"+i)));
          detailRequestManualBean.setBusinessSolucionDescription(MiUtil.trimNotNull(request.getParameter("hdnDescripcionSolucionNegocio_"+i)));    
          
          detailRequestManualBean.setProductLineCode(MiUtil.trimNotNull(request.getParameter("hdnCodigoLineaProducto_"+i)));
          detailRequestManualBean.setProductLineDescription(MiUtil.trimNotNull(request.getParameter("hdnDescripcionLineaProducto_"+i))); 
          
          listaDetalleSolicitudManual.add(detailRequestManualBean);
          System.out.println("==========================================="); 
      }
      
      //Datos de la lista borrar
      for(int i=0; i<listaDetalleSolicitudManual.size(); i++){
          DetailRequestManualBean detailRequestManualBean = (DetailRequestManualBean)listaDetalleSolicitudManual.get(i);
         
          System.out.println("hdnIdProducto_"+ i +"= "+ detailRequestManualBean.getProductCode());
          System.out.println("hdnDescripcionProducto_"+ i +"= "+ detailRequestManualBean.getProductDescription());
          System.out.println("hdnIdRepuesto_"+ i +"= "+ detailRequestManualBean.getReplacementCode());
          System.out.println("hdnDescripcionRepuesto_"+ i +"= "+ detailRequestManualBean.getReplacementDescription());
          System.out.println("hdnIdMotivo_"+ i +"= "+ detailRequestManualBean.getReasonCode());
          System.out.println("hdnDescripcionMotivo_"+ i +"= "+ detailRequestManualBean.getReasonDescription());
          System.out.println("hdnIdModalidad_"+ i +"= "+ detailRequestManualBean.getModeCode());
          System.out.println("hdnDescripcionModalidad_"+ i +"= "+ detailRequestManualBean.getModeDescription());
          System.out.println("hdnIdEstado_"+ i +"= "+ detailRequestManualBean.getStatusCode());
          System.out.println("hdnDescripcionEstado_"+ i +"= "+ detailRequestManualBean.getStatusDescription());
          System.out.println("hdnIdAccesorio_"+ i +"= "+ detailRequestManualBean.getAccesoryCode());
          System.out.println("hdnDescripcionAccesorio_"+ i +"= "+ detailRequestManualBean.getAccesoryDescription());
          System.out.println("hdnCantidad_"+ i +"= "+ detailRequestManualBean.getQuantity());          
          System.out.println("hdnCodigoSolucionNegocio_"+ i +"= "+ detailRequestManualBean.getBusinessSolucionCode());
          System.out.println("hdnDescripcionSolucionNegocio_"+ i +"= "+ detailRequestManualBean.getBusinessSolucionDescription());
          
          System.out.println("hdnCodigoLineaProducto_"+ i +"= "+ detailRequestManualBean.getProductLineCode());
          System.out.println("hdnDescripcionLineaProducto_"+ i +"= "+ detailRequestManualBean.getProductLineDescription());
          
      }
      
      mapaDatos = requestManualService.saveRequestManual(requestManualBean,listaDetalleSolicitudManual);
      
      String strMensaje = (String)mapaDatos.get(Constante.MESSAGE_OUTPUT);
      RequestManualBean bean =  (RequestManualBean)mapaDatos.get(Constante.REQUEST_MANUAL_BEAN);
      
      System.out.println("SERVLET RESPUESTA");
      System.out.println("bean.getRequestId() = " + bean.getRequestId());
      System.out.println("bean.getRequestNumber() = " + bean.getRequestNumber());
      System.out.println("strMensaje = " + strMensaje);
      
      response.setContentType("text/plaint;charset=ISO-8859-1");        
      PrintWriter out = response.getWriter();
      
      if(strMensaje == null){
        out.print("Se genero la solicitud con nro. " + bean.getRequestNumber()+"|"+bean.getRequestNumber());
      }else{
        out.print("Error: "+strMensaje);
      }
      
      out.close();
      
  }
  
  
  public void obtenerLineaProductoBySolucion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
     
    response.setContentType("text/plaint;charset=ISO-8859-1");        
    PrintWriter out = response.getWriter();
    RequestManualService requestManualService =  new RequestManualService(); 
    
    System.out.println(">> ----obtenerLineaProductoBySolucion ----");
    
    String optionHtml="";    
    List listaLineaProducto = null;    
    //List listaProducto = null;
    //List listaRepuestos = null;
    HashMap mapaData = null;    
    
    String idSolucion = MiUtil.trimNotNull(request.getParameter("ID_SOLUCION"));
    String idModalidad = MiUtil.trimNotNull(request.getParameter("ID_MODALIDAD"));
    //String idLineaProducto = MiUtil.trimNotNull(request.getParameter("ID_LINEPRODUCTO"));
    //String idProducto = MiUtil.trimNotNull(request.getParameter("ID_PRODUCTO"));
    
    System.out.println(">> idSolucion = " + idSolucion);
    System.out.println(">> idModalidad = " + idModalidad);
    
    DominioBean solucionNegocio = new DominioBean();
    solucionNegocio.setValor(idSolucion);
    mapaData = requestManualService.getLineaProductoListByIdsolucion(solucionNegocio);
    listaLineaProducto = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_LINEA_PRODUCTO);
    
    /*DominioBean producto = new DominioBean();
    producto.setParam1(idSolucion);
    producto.setParam2(idModalidad);    
    mapaData = requestManualService.getProductoListByLineaproductoid(producto);
    listaProducto = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_PRODUCTO);*/
    
    /*String idLineaProducto =  ((DominioBean)listaLineaProducto.get(0)).getValor();
    System.out.println(">> idLineaProducto = " + idLineaProducto);
    String idProducto = ((DominioBean)listaProducto.get(0)).getValor();
    System.out.println(">> idProducto = " + idProducto);
    DominioBean accesorioRepuesto = new DominioBean();
    accesorioRepuesto.setParam1(idProducto);// ID PRODUCTO
    accesorioRepuesto.setParam2(idLineaProducto);// ID Linea Producto
    accesorioRepuesto.setParam3(idModalidad);// ID MODALIDAD                             
    mapaData = requestManualService.getRepuestosList(accesorioRepuesto);
    listaRepuestos = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_REPUESTOS);*/
               
    
    System.out.println(">> listaLineaProducto.size() = "+listaLineaProducto.size());
    //System.out.println("EDU: listaProducto.size() = "+listaProducto.size());
    //System.out.println("EDU: listaRepuestos.size() = "+listaRepuestos.size());
    
    
    if(listaLineaProducto.size()>0){
      optionHtml += "<option value=''></option>";  
      for(int i = 0; i<listaLineaProducto.size(); i++){    
        DominioBean dominoBean = (DominioBean)listaLineaProducto.get(i);
        optionHtml += "<option value='"+MiUtil.trimNotNull(dominoBean.getValor())+"'>"+MiUtil.repleaceCharacterSpecialByHTML(dominoBean.getDescripcion())+"</option>";
        
      }      
    }else{
      optionHtml = "<option value=''>No existe linea de negocio</option>";                    
    }
    
    /*optionHtml = optionHtml + "|";
    
    //Cargamos los datos de los productos
    if(listaProducto.size()>0){      
      for(int i = 0; i<listaProducto.size(); i++){    
        DominioBean dominoBean = (DominioBean)listaProducto.get(i);      
        optionHtml = optionHtml + "<option value='"+MiUtil.trimNotNull(dominoBean.getValor())+"'>"+MiUtil.repleaceCharacterSpecialByHTML(dominoBean.getDescripcion())+"</option>";              
      }      
    }else{
      optionHtml = optionHtml + "<option value=''>No existe producto</option>";                    
    }
    
    optionHtml +="|";
    
    //Cargamos los datos de los accesorios
       
    if(listaRepuestos.size()>0){      
      for(int i = 0; i<listaRepuestos.size(); i++){    
        DominioBean dominoBean = (DominioBean)listaRepuestos.get(i);      
        optionHtml = optionHtml + "<option value='"+MiUtil.trimNotNull(dominoBean.getValor())+"'>"+MiUtil.repleaceCharacterSpecialByHTML(dominoBean.getDescripcion())+"</option>";              
      }      
    }else{
      optionHtml = optionHtml + "0";                    
    }
    */
       
    out.write(optionHtml);    
    out.close();   
        
  } 
  
  //
  public void obtenerListaAccesoriosRepuesto(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
     
        response.setContentType("text/plaint;charset=ISO-8859-1");        
        PrintWriter out = response.getWriter();
        RequestManualService requestManualService =  new RequestManualService(); 
        
        String optionHtml="";    
        List listaRepuestos = null;    
        HashMap mapaData = null;    
            
        DominioBean dominioBean = new DominioBean();
        dominioBean.setParam1(MiUtil.trimNotNull(request.getParameter("ID_PRODUCTO")));// ID PRODUCTO
        dominioBean.setParam2(MiUtil.trimNotNull(request.getParameter("ID_LINEAPRODUCTO")));// ID Linea Producto
        dominioBean.setParam3(MiUtil.trimNotNull(request.getParameter("ID_MODALIDAD")));// ID MODALIDAD
                             
        mapaData = requestManualService.getRepuestosList(dominioBean);
        listaRepuestos = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_REPUESTOS);
        
        System.out.println("Servlet - obtenerListaAccesoriosRepuesto: listaRepuestos.size() =" + listaRepuestos.size());
               
        if(listaRepuestos.size()>0){
          optionHtml += "<option value=''></option>"; 
          for(int i = 0; i<listaRepuestos.size(); i++){    
            DominioBean dominoBean = (DominioBean)listaRepuestos.get(i);    
            optionHtml +=  "<option value='"+MiUtil.trimNotNull(dominoBean.getValor())+"'>"+MiUtil.repleaceCharacterSpecialByHTML(dominoBean.getDescripcion())+"</option>";                        
          }      
        }else{
          optionHtml = "";                    
        }
          
        out.write(optionHtml);    
        out.close();   
        
  } 
  
  
  
  public void obtenerAccesoriosProductos(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
     
        response.setContentType("text/plaint;charset=ISO-8859-1");        
        PrintWriter out = response.getWriter();
        RequestManualService requestManualService =  new RequestManualService(); 
        
        String optionHtml="";    
        List listaRepuestos = null;    
        List listaProducto = null;
        List listaSolucionNegocio = null;
        HashMap mapaData = null;    
        
        //OBTNEMOS LA LISTA ACCESORIOS/REPUESTOS
        /*DominioBean dominioBean = new DominioBean();
        dominioBean.setParam1(MiUtil.trimNotNull(request.getParameter("ID_PRODUCTO")));// ID PRODUCTO
        dominioBean.setParam2(MiUtil.trimNotNull(request.getParameter("ID_LINEAPRODUCTO")));// ID Linea Producto
        dominioBean.setParam3(MiUtil.trimNotNull(request.getParameter("ID_MODALIDAD")));// ID MODALIDAD
                             
        mapaData = requestManualService.getRepuestosList(dominioBean);
        listaRepuestos = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_REPUESTOS);*/
                
        //OBTENEMOS LA LISTA DE PRODUCTOS
        DominioBean producto =  new DominioBean();
        producto.setParam1(MiUtil.trimNotNull(request.getParameter("ID_LINEAPRODUCTO")));
        producto.setParam2(MiUtil.trimNotNull(request.getParameter("ID_MODALIDAD")));
        mapaData = requestManualService.getProductoListByLineaproductoid(producto);
        listaProducto = (ArrayList)mapaData.get(Constante.SOLICITUD_MANUAL_LISTA_PRODUCTO);
        
               
        //System.out.println("Servlet - obtenerAccesoriosProductos: listaRepuestos.size() =" + listaRepuestos.size());
        System.out.println("Servlet - obtenerAccesoriosProductos: listaProducto.size() =" + listaProducto.size());
               
        /*if(listaRepuestos.size()>0){
          
          for(int i = 0; i<listaRepuestos.size(); i++){    
            DominioBean dominoBean = (DominioBean)listaRepuestos.get(i);    
            optionHtml +=  "<option value='"+MiUtil.trimNotNull(dominoBean.getValor())+"'>"+MiUtil.repleaceCharacterSpecialByHTML(dominoBean.getDescripcion())+"</option>";                        
          }      
        }else{
          optionHtml = "0";                    
        }*/
        
        //optionHtml += "|" ;
        
        
        
        if(listaProducto.size()>0){
          optionHtml += "<option value=''></option>";
          for(int i = 0; i<listaProducto.size(); i++){    
            DominioBean dominoBean = (DominioBean)listaProducto.get(i);    
            optionHtml +=  "<option value='"+MiUtil.trimNotNull(dominoBean.getValor())+"'>"+MiUtil.repleaceCharacterSpecialByHTML(dominoBean.getDescripcion())+"</option>";                        
          }      
        }else{
          optionHtml = "<option value=''>No existen productos</option>";
        }
          
        out.write(optionHtml);    
        out.close();   
        
  } 
  
  
  
  public void validarAlmacen(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        
        System.out.println("Servlet: ---validarAlmacen---");
        
        //response.setContentType("text/plaint;charset=ISO-8859-1");        
        PrintWriter out = response.getWriter();
        RequestManualService requestManualService =  new RequestManualService(); 
        
        StockBean stockBean = new StockBean();
        String cadenaHtml="";
        
        stockBean.setAn_specificationid(MiUtil.trimNotNull(request.getParameter("an_specificationid")));
        stockBean.setAn_productid(MiUtil.trimNotNull(request.getParameter("an_productid")));
        stockBean.setAn_npdispatchplaceid(MiUtil.trimNotNull(request.getParameter("an_npdispatchplaceid")));
        stockBean.setAv_organizationoperation(MiUtil.trimNotNull(request.getParameter("av_organizationoperation")));
        stockBean.setAn_salesstructporigenid(MiUtil.trimNotNull(request.getParameter("an_salesstructporigenid")));
        stockBean.setAv_pro_status(MiUtil.trimNotNull(request.getParameter("av_pro_status")));
                
        System.out.println("an_specificationid: " + stockBean.getAn_specificationid());
        System.out.println("an_productid: " + stockBean.getAn_productid());
        System.out.println("an_npdispatchplaceid: " + stockBean.getAn_npdispatchplaceid());
        System.out.println("av_organizationoperation: " + stockBean.getAv_organizationoperation());
        System.out.println("an_salesstructporigenid: " + stockBean.getAn_salesstructporigenid());
        System.out.println("av_pro_status: " + stockBean.getAv_pro_status());
        
        stockBean = requestManualService.validarStock(stockBean);
        
        System.out.println("-->"+stockBean.getAv_flag_stock() + "|" + stockBean.getAv_message_stock()); 
        System.out.println("Av_message = " + stockBean.getAv_message());
        System.out.println("stockBean.getAv_message().length() = " + stockBean.getAv_message().length());
        
        if("".equals(stockBean.getAv_message())){
           cadenaHtml = stockBean.getAv_flag_stock() + "|" + stockBean.getAv_message_stock(); 
        }else{
           cadenaHtml = stockBean.getAv_message(); 
        }
        
        System.out.println("cadenaHtml = " + cadenaHtml);
        
        out.println(cadenaHtml);    
        out.close();   
        
  } 
  
  
  /**
   * Process the HTTP doGet request.
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    processRequest(request,response);
   
  }

  /**
   * Process the HTTP doPost request.
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    processRequest(request,response);
  }
  
  
  
  
}