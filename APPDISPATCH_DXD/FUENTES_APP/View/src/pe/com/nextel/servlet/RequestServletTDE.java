package pe.com.nextel.servlet;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pe.com.nextel.bean.DominioBean;
import pe.com.nextel.bean.ItemBean;
import pe.com.nextel.bean.RequestBean;
import pe.com.nextel.service.BafiOutdoorService;
import pe.com.nextel.service.tde.GeneralService;
import pe.com.nextel.service.ItemService;
import pe.com.nextel.service.tde.RequestService;
import pe.com.nextel.util.Constante;
import pe.com.nextel.util.MiUtil;


//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.usermodel.*;

public class RequestServletTDE extends GenericServlet {
    
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    static  Logger logger = Logger.getLogger(RequestServletTDE.class);
    String categoria = "";
    
        
    
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
         
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        String metodo = request.getParameter(Constante.SERVLET_PARAM_METHOD);
        
        
        System.out.println("EDU: metodo = " + metodo);
        
        try {
          if(metodo!=null && metodo.equals("INI_PAGE")){
            iniciarPagina(request, response);
          }
          
          if(metodo != null && metodo.equals("IMBOX")){
            doLoadInfo(request,response);
          } 
          
          if(metodo!=null && metodo.equals("SEARCH_REQUEST")){             
             buscarSolicitudes(request, response);
          }
          if(metodo != null && metodo.equals("GET_CATEGORIA_BY_DIVISION_NEGOCIO")){
            obtenerCategoriasByIdDivisionNegocio(request, response);
          }
          if(metodo != null && metodo.equals("GET_SUBCATEGORIA_BY_CATEGORIA")){
            obtenerSubCategoriasByIdCategoria(request, response);
          }
          if(metodo != null && metodo.equals("SEND_PARAM_SEARCH")){   
            obtenerDatosFormulario(request,response);
          }

          if(metodo != null && metodo.equals("VALIDAR_IMEI")){
            validarIMEI(request,response);
          }
          if(metodo != null && metodo.equals("OBTENER_SIM")){
            obtenerSIM(request,response);
          }
          
          if(metodo!=null && metodo.equals("REFRESH_PAGE")){             
             refrescarLista(request, response);
          }
          if(metodo!=null && metodo.equals("EXPORTAR_PAGE")){             
             exportarExcel(request, response);
          }
          //INICIO JGABRIEL REQ-0123  
          if(metodo!=null && metodo.equals("VALIDAR_SUBINVENTORYCODE")){             
            doValidateSubInventoryCode(request, response);
          }
          if(metodo!=null && metodo.equals("VALIDAR_MOELO_SOLICITADO")){             
            doValidateModelRequested(request, response);
          }  
          //FIN JGABRIEL REQ-0123  
            
          if(metodo!=null && metodo.equals("VALIDAR_REGULARIZAR_ORDEN_OUTDOOR")){             
            validarRegularizarOrdenOutdoor(request, response);
          }              

          if(metodo!=null && metodo.equals("REGULARIZAR_ORDEN_OUTDOOR")){             
            regularizarOrdenOutdoor(request, response);
          } 
            
        } catch (Exception ex) {
            System.out.println("ERROR: " +ex.getMessage());
        }finally{
          out.close();
        }

         
    }
  

   public void validarIMEI(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
     System.out.println("ServletTDE: ---validarIMEI---");
    response.setContentType("text/plaint");
    PrintWriter out = response.getWriter();
    ItemService itemService = new ItemService();
    RequestService requestService = new RequestService();
    HashMap hsmpResultado = new HashMap();
    ArrayList listaItem = new ArrayList();

   // List listaItem = null;
    String cadena = "";
      
      String codigoIMEI = MiUtil.trimNotNull(request.getParameter("COD_IMEI"));    
      long requestId = Long.parseLong(MiUtil.trimNotNull(request.getParameter("strRequestId")));

    //obtener flag, avchOrigin
    String flagRequestTde=requestService.getFlagRequestOlTde(requestId);
    
    //listaItem = itemService.getImeiDet(codigoIMEI);
    hsmpResultado =  itemService.getImeiDet(codigoIMEI);
    listaItem = (ArrayList) hsmpResultado.get("objArrayList");
    String error = (String)hsmpResultado.get(Constante.ERROR_OUTPUT);
    
    System.out.println("Servlet validarIMEI");
    System.out.println("listaItem.size() = " + listaItem.size());
    System.out.println("error = " + error);
    
    if(error == null){
        if(listaItem.size()>0){
          cadena = ((ItemBean)listaItem.get(0)).getNpname() + "|"+((ItemBean)listaItem.get(0)).getNpsubinventorycode() + "|"+ ((ItemBean)listaItem.get(0)).getNporganizationid();
          System.out.println("cadena = " + cadena);
          System.out.println("Recuperamos el SIM para el IMEI: " + codigoIMEI);          
          HashMap mapaDatos =  requestService.getSIM(codigoIMEI, flagRequestTde);
          String strSim = (String)mapaDatos.get(Constante.SIM);
          String strMensaje = (String)mapaDatos.get(Constante.MESSAGE_OUTPUT);
          if(strMensaje != null){
              strSim = "";              
          }
          else if("".equals(MiUtil.trimNotNull(strSim))){
              strSim = "";                            
          }
          
          cadena +="|"+strSim;
          
          System.out.println("strMensaje = " + strMensaje);
          System.out.println("cadena Final = " + cadena);
        }
    }else{
       cadena = error;
    }
        
    out.write(cadena);
    out.close();
    
    
  }
  
    
 
 public void obtenerCategoriasByIdDivisionNegocio(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
    GeneralService generalServices = new GeneralService();
    List listaCategorias = null;
    HashMap mapaData = null;
    
    PrintWriter out = response.getWriter();
    
    long idDivisionNegocio =MiUtil.parseLong(MiUtil.trimNotNull(request.getParameter("ID_DIVISION_NEGOCIO"))); 
    mapaData = generalServices.getCategoryList(idDivisionNegocio);
    listaCategorias = (ArrayList)mapaData.get(Constante.SOLICITUD_LISTA_CATEGORIA); 
    
    
    
    String optionHTML = "<option value=''></option>";
    if(listaCategorias.size() > 0){
      categoria = ((DominioBean)listaCategorias.get(0)).getValor();
      System.out.println("EDU: categoria = " + categoria);
      
      for(int i=0; i<listaCategorias.size(); i++){
        DominioBean dominioBean = (DominioBean)listaCategorias.get(i);
        
        optionHTML = optionHTML + "<option value='"+dominioBean.getValor()+"'>"+dominioBean.getDescripcion()+"</option>";
      }
    }else{
      optionHTML = "<option value=''></option>";      
    }
    
    out.write(optionHTML);
    out.close();
  
  }
   
   
  public void obtenerSubCategoriasByIdCategoria(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
    GeneralService generalServices = new GeneralService();
    List listaSubCategorias = null;
    HashMap mapaData = null;
    
    PrintWriter out = response.getWriter();
    
    long idDivisionNegocio =MiUtil.parseLong(MiUtil.trimNotNull(request.getParameter("ID_DIVISION_NEGOCIO"))); 
    String idCategoria = MiUtil.trimNotNull(request.getParameter("ID_CATEGORIA"));
    System.out.println("idCategoria.length() = "+idCategoria.length());
    if(idCategoria.length() == 0){       
      idCategoria = categoria;       
    }
    
    mapaData = generalServices.getSubCategoryList(idCategoria,idDivisionNegocio);
    
    listaSubCategorias = (ArrayList)mapaData.get(Constante.SOLICITUD_LISTA_SUB_CATEGORIA);
    
    System.out.println("EDU: listaSubCategorias = " + listaSubCategorias.size());
    
    String optionHTML = "<option value=''></option>";
    if(listaSubCategorias.size() > 0){
      for(int i=0; i<listaSubCategorias.size(); i++){
        DominioBean dominioBean = (DominioBean)listaSubCategorias.get(i);
        optionHTML = optionHTML + "<option value='"+dominioBean.getValor()+"'>"+dominioBean.getDescripcion()+"</option>";
      }
    }else{
      optionHTML = "<option value=''></option>";      
    }
    
    out.write(optionHTML);
    out.close();
  
  }
   
  
  public void buscarSolicitudes(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
      System.out.println("--ResquestServlet_TDE buscarSolicitudes--");
    RequestService requestServices = new RequestService();
    List listaRequestBean = null;
    HashMap mapaData = null;
    
    String fechaInicio="";
    String fechaFin="";
    
    System.out.println("--Servlet buscarSolicitudes--");
    
    try
    {
    //Recuperamos los datos del formulario busqueda
    RequestBean requestBean = new RequestBean(); 
    System.out.println("p1");
    requestBean = obtenerDatosFormulario(request);  //ksal
    System.out.println("p2");    
    fechaInicio =  requestBean.getRequestDateBegin();
    System.out.println("p3");
    fechaFin =  requestBean.getRequestDateEnd();
    System.out.println("p4");
    mapaData = requestServices.getRequestAllList(requestBean);
    System.out.println("p5");
    listaRequestBean = (ArrayList)mapaData.get(Constante.SOLICITUD_LISTA_BEAN);
    System.out.println("EDU: listaRequest.size() = " + listaRequestBean.size());
    
    /*if(listaRequestBean.size()<1){
      RequestBean rbean = new RequestBean();
      listaRequestBean.add(rbean);
    }*/
    
    request.setAttribute(Constante.SOLICITUD_LISTA_BEAN, listaRequestBean);
    request.setAttribute("fechaInicio", fechaInicio);
    request.setAttribute("fechaFin", fechaFin);
    //request.setAttribute("vieneDe", "BUSQUEDA");
       
    
    }catch(Exception e)
    {
     logger.error("RequestSevlet Error ",e);
    }
  }
  
  public RequestBean obtenerDatosFormulario(HttpServletRequest request)  throws ServletException, IOException{
    RequestBean requestBean = new RequestBean();        
    
    requestBean.setLocation(MiUtil.parseInt(MiUtil.trimNotNull(request.getParameter("cboUbicacion"))));    
    requestBean.setUser(MiUtil.trimNotNull(request.getParameter("cboUsuario")));        
    requestBean.setRequestNumber(MiUtil.trimNotNull(request.getParameter("txtNroSolicitud")));
    requestBean.setOrderNumber(MiUtil.trimNotNull(request.getParameter("txtNroOrden")));    
    requestBean.setOrderPay(MiUtil.trimNotNull(request.getParameter("cboOrdenPagada")));   
    requestBean.setEstadoSolicitud(MiUtil.trimNotNull(request.getParameter("cboEstadoSolicitud")));    
    requestBean.setBusinessDivision(MiUtil.parseInt(MiUtil.trimNotNull(request.getParameter("cboDivisionNegocio"))));   
    requestBean.setCategory(MiUtil.trimNotNull(request.getParameter("cboCategoria")));    
    requestBean.setSubCategory(MiUtil.trimNotNull(request.getParameter("cboSubCategoria")));  
    requestBean.setFacturaNumber(MiUtil.trimNotNull(request.getParameter("txtNroFactura")));  
    requestBean.setRazonSocial(MiUtil.trimNotNull(request.getParameter("txtCustomerName")));
    requestBean.setRequestDateBegin(MiUtil.trimNotNull(request.getParameter("txtCreateDateFrom")));    
    requestBean.setRequestDateEnd(MiUtil.trimNotNull(request.getParameter("txtCreateDateTill")));
    requestBean.setCodBSCS(MiUtil.trimNotNull(request.getParameter("txtCodBscs")));
    
    return requestBean;
  }
  
  
  public void obtenerDatosFormulario(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
    
    logger.debug("obtenerDatosFormulario *****************************");
    
    PrintWriter out = response.getWriter();
    String cboUbicacion = MiUtil.trimNotNull(request.getParameter("cboUbicacion"));
    String cboUsuario = MiUtil.trimNotNull(request.getParameter("cboUsuario"));
    String txtNroSolicitud = MiUtil.trimNotNull(request.getParameter("txtNroSolicitud"));
    String txtNroOrden = MiUtil.trimNotNull(request.getParameter("txtNroOrden"));
    String cboOrdenPagada = MiUtil.trimNotNull(request.getParameter("cboOrdenPagada"));
    String cboEstadoSolicitud = MiUtil.trimNotNull(request.getParameter("cboEstadoSolicitud"));
    String cboDivisionNegocio = MiUtil.trimNotNull(request.getParameter("cboDivisionNegocio"));
    String cboCategoria = MiUtil.trimNotNull(request.getParameter("cboCategoria"));
    String cboSubCategoria = MiUtil.trimNotNull(request.getParameter("cboSubCategoria"));
    String txtNroFactura = MiUtil.trimNotNull(request.getParameter("txtNroFactura"));
    String txtCustomerName = MiUtil.trimNotNull(request.getParameter("txtCustomerName"));
    String txtCreateDateFrom = MiUtil.trimNotNull(request.getParameter("txtCreateDateFrom"));
    String txtCreateDateTill = MiUtil.trimNotNull(request.getParameter("txtCreateDateTill"));
    String txtCodBscs = MiUtil.trimNotNull(request.getParameter("txtCodBscs"));
    String vieneDe = MiUtil.trimNotNull(request.getParameter("vieneDe"));
    String parametros = "cboUbicacion="+cboUbicacion+"&cboUsuario="+cboUsuario+"&txtNroSolicitud="+txtNroSolicitud+
                        "&txtNroOrden="+txtNroOrden+"&cboOrdenPagada="+cboOrdenPagada+"&cboEstadoSolicitud="+cboEstadoSolicitud+
                        "&cboDivisionNegocio="+cboDivisionNegocio+"&cboCategoria="+cboCategoria+"&cboSubCategoria="+cboSubCategoria+
                        "&txtNroFactura="+txtNroFactura+"&txtCustomerName="+txtCustomerName+"&txtCreateDateFrom="+txtCreateDateFrom+
                        "&txtCreateDateTill="+txtCreateDateTill+"&txtCodBscs="+txtCodBscs+"&vieneDe="+vieneDe;
                        
    //EDU: Comentado por pruebas locales
    String strUrl="/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_LIST?"+parametros;
    logger.debug("URParametrosSearch: : "+strUrl);    
    out.print("<script>parent.mainFrame.location.replace('"+strUrl+"');</script>"); 
                                                               
  }
  
  public void exportarExcel(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
    
    System.out.println("Servlet exportarExcel ********");
    
    PrintWriter out = response.getWriter();
    RequestService requestServices = new RequestService();
    
    List listaRequestBean = null;
    HashMap mapaData = null;
    
    String fechaInicio="";
    String fechaFin="";
  
    RequestBean requestBean = new RequestBean();  
  
    requestBean.setLocation(MiUtil.parseInt(MiUtil.trimNotNull(request.getParameter("cboUbicacion"))));    
    requestBean.setUser(MiUtil.trimNotNull(request.getParameter("cboUsuario")));        
    requestBean.setRequestNumber(MiUtil.trimNotNull(request.getParameter("txtNroSolicitud")));
    requestBean.setOrderNumber(MiUtil.trimNotNull(request.getParameter("txtNroOrden")));    
    requestBean.setOrderPay(MiUtil.trimNotNull(request.getParameter("cboOrdenPagada")));   
    requestBean.setEstadoSolicitud(MiUtil.trimNotNull(request.getParameter("cboEstadoSolicitud")));    
    requestBean.setBusinessDivision(MiUtil.parseInt(MiUtil.trimNotNull(request.getParameter("cboDivisionNegocio"))));   
    requestBean.setCategory(MiUtil.trimNotNull(request.getParameter("cboCategoria")));    
    requestBean.setSubCategory(MiUtil.trimNotNull(request.getParameter("cboSubCategoria")));  
    requestBean.setFacturaNumber(MiUtil.trimNotNull(request.getParameter("txtNroFactura")));  
    requestBean.setRazonSocial(MiUtil.trimNotNull(request.getParameter("txtCustomerName")));
    requestBean.setRequestDateBegin(MiUtil.trimNotNull(request.getParameter("txtCreateDateFrom")));    
    requestBean.setRequestDateEnd(MiUtil.trimNotNull(request.getParameter("txtCreateDateTill")));
    requestBean.setCodBSCS(MiUtil.trimNotNull(request.getParameter("txtCodBscs")));
    
    System.out.println("requestBean.getLocation() = " + requestBean.getLocation());
    System.out.println("requestBean.getUser() = " + requestBean.getUser());
    System.out.println("requestBean.getRequestNumber() = " + requestBean.getRequestNumber());
    System.out.println("requestBean.getOrderNumber() = " + requestBean.getOrderNumber());
    System.out.println("requestBean.getOrderPay() = " + requestBean.getOrderPay());
    System.out.println("requestBean.getEstadoSolicitud() = " + requestBean.getEstadoSolicitud());
    System.out.println("requestBean.getBusinessDivision() = " + requestBean.getBusinessDivision());
    System.out.println("requestBean.getCategory() = " + requestBean.getCategory());
    System.out.println("requestBean.getSubCategory() = " + requestBean.getSubCategory());
    System.out.println("requestBean.getFacturaNumber() = " + requestBean.getFacturaNumber());
    System.out.println("requestBean.getRazonSocial() = " + requestBean.getRazonSocial());
    System.out.println("requestBean.getRequestDateBegin() = " + requestBean.getRequestDateBegin());
    System.out.println("requestBean.getRequestDateEnd() = " + requestBean.getRequestDateEnd());
    System.out.println("requestBean.getCodBSCS() = " + requestBean.getCodBSCS());
    
    fechaInicio =  requestBean.getRequestDateBegin();
    fechaFin =  requestBean.getRequestDateEnd();
  
    mapaData = requestServices.getRequestAllList(requestBean);
    listaRequestBean = (ArrayList)mapaData.get(Constante.SOLICITUD_LISTA_BEAN);
    
    
    String tabla = "";
    String tabla2 = "";
    
    tabla +="<table  border='0'>";
    tabla +="<tr>";
    tabla +="<td>&nbsp;&nbsp;</td>";
    tabla +="<td   align='center'><h1>Solicitudes a Almacén</h1></td>";
    tabla +="</tr>";
    tabla +="<tr>";
    tabla +="<td>&nbsp;&nbsp;</td>";
    tabla +="<td colspan='2'><b>Criterios de Búsqueda</b></td>";
    tabla +="</tr>";
    tabla +="<tr>";
    tabla +="<td>&nbsp;&nbsp;</td>";   
    tabla +="<td>Fecha desde: &nbsp;&nbsp;"+fechaInicio+"</td>";
    tabla +="</tr>";
    tabla +="<tr>";
    tabla +="<td>&nbsp;&nbsp;</td>";
    tabla +="<td>Fecha hasta: &nbsp;&nbsp;"+fechaFin+"</td>";
    tabla +="</tr>";
    tabla +="</table>";
    tabla +="<br/><br/>";
    
    tabla2 +="<table><tr><td></td></tr></table";
    tabla2 +="<table border='1'>";
    tabla2 +="<TR>";
    tabla2 +="<TH align='center' bgcolor='#FFCC99'>Nro</TH>";
    tabla2 +="<TH align='center' bgcolor='#FFCC99'>Nro. Solicitud</TH>";
    tabla2 +="<TH align='center' bgcolor='#FFCC99'>Nro. de Orden</TH>";
    tabla2 +="<TH align='center' bgcolor='#FFCC99'>Pago</TH>";
    tabla2 +="<TH align='center' bgcolor='#FFCC99'>Fecha de Solicitud</TH>";
    tabla2 +="<TH align='center' bgcolor='#FFCC99'>Razon Social</TH>";
    tabla2 +="<TH align='center' bgcolor='#FFCC99'>Envio Courier</TH>";
    tabla2 +="<TH align='center' bgcolor='#FFCC99'>Proviene de</TH>";
    tabla2 +="<TH align='center' bgcolor='#FFCC99'>Usuario</TH>";
    tabla2 +="<TH align='center' bgcolor='#FFCC99'>Estado</TH>";
    tabla2 +="</TR>";
    
    for(int i=0; i<listaRequestBean.size(); i++){
        RequestBean rq = (RequestBean)listaRequestBean.get(i);
        
        tabla2 +="<tr>";
        tabla2 +="<td>"+rq.getIndice()+"</td>";
        tabla2 +="<td>"+rq.getRequestNumber()+"</td>";
        tabla2 +="<td>"+rq.getOrderNumber()+"</td>";
        tabla2 +="<td>"+rq.getOrderPay()+"</td>";
        tabla2 +="<td>"+rq.getRequestDate()+"</td>";
        tabla2 +="<td>"+rq.getRazonSocial()+"</td>";
        tabla2 +="<td>"+rq.getEnvioCourier()+"</td>";
        tabla2 +="<td>"+rq.getProvieneDe()+"</td>";
        tabla2 +="<td>"+rq.getUser()+"</td>";
        tabla2 +="<td>"+rq.getRequestStateDescription()+"</td>";
        tabla2 +="</tr>";
      
    }
    
    tabla2 +="</TABLE>";
    
    response.setContentType("application/vnd.ms-excel");
    response.setHeader("Content-Disposition", "inline; filename=Solicitudes_Almacen.xls");
    
    out.write(tabla);
    out.write(tabla2);
    out.close();
    
    
  }
  
	public void doLoadInfo (HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
      
       ArrayList listRequest = new ArrayList();
       RequestService requestService = new RequestService();
       listRequest = requestService.getPendingRequest(22);
       request.setAttribute(Constante.SOLICITUD_LISTA_BEAN, listRequest);
       request.setAttribute("vieneDe", "INBOX");
       
       PrintWriter out = response.getWriter();
       String strUrl="/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_LIST";
       out.print("<script>parent.mainFrame.location.replace('"+strUrl+"');</script>"); 
       
       /*RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/JP_SEARCH_REQUEST_STORE/JP_OL_REQUEST_LIST_ShowPage.jsp");
       dispatcher.include(request, response); */   
    }

    public void iniciarPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      List listaEstado = null;
      List listaDivisionNegocio = null;
      List listaLocation = null;
      List listaOrdenPagada = null;
      List listaUsuario = null;
      
      List listaCategoria = null;
      List listaSubCategoria = null;
      
      HashMap mapaData = null;
      RequestService requestService = new RequestService();
      GeneralService generalService = new GeneralService(); 
      
      //Obtener usuarios
      String idbuilding = (String)request.getAttribute("idbuilding");
      mapaData = generalService.getUserList(MiUtil.parseInt(idbuilding));
      listaUsuario = (ArrayList)mapaData.get(Constante.SOLICITUD_LISTA_USUARIOS );
      request.setAttribute(Constante.SOLICITUD_LISTA_USUARIOS,listaUsuario);
      
      //Obtener division de negocio
      mapaData  = generalService.getDivisionList();
      listaDivisionNegocio =(ArrayList)mapaData.get(Constante.SOLICITUD_LISTA_DIVISION_NEGOCIO); 
      request.setAttribute(Constante.SOLICITUD_LISTA_DIVISION_NEGOCIO,listaDivisionNegocio);
      
       //Obtener categorias
      DominioBean divisionNegocio =  null; 
      String idDivisionNegocio = "";
      for(int i=0; i<listaDivisionNegocio.size();i++){
        divisionNegocio = (DominioBean)listaDivisionNegocio.get(i);
        
        if(MiUtil.trimNotNull(divisionNegocio.getValor()).equals("1")){
           idDivisionNegocio = divisionNegocio.getValor();
        }
      
      }
      mapaData  = generalService.getCategoryList(MiUtil.parseLong(idDivisionNegocio)); 
      listaCategoria =(ArrayList)mapaData.get(Constante.SOLICITUD_LISTA_CATEGORIA); 
      request.setAttribute(Constante.SOLICITUD_LISTA_CATEGORIA,listaCategoria);
      
      //Obtener lista ubicacion
      mapaData = generalService.getLocationList();
      listaLocation = (ArrayList)mapaData.get(Constante.SOLICITUD_LISTA_UBICACION); 
      request.setAttribute(Constante.SOLICITUD_LISTA_UBICACION,listaLocation);
      
      //Obtener lista orden pagada
      listaOrdenPagada = obtenerListaOrdenPagada();
      request.setAttribute(Constante.SOLICITUD_LISTA_ORDEN_PAGADA,listaOrdenPagada);
    
      //Obtner estados de la solicitud
      mapaData = requestService.getEstadosList();
      listaEstado = (ArrayList)mapaData.get(Constante.SOLICITUD_LISTA_ESTADOS);
      request.setAttribute(Constante.SOLICITUD_LISTA_ESTADOS,listaEstado);
      
      
      
      //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/JP_SEARCH_REQUEST_STORE/JP_SEARCH_REQUEST_STORE_ShowPage.jsp");
      //dispatcher.include(request, response);
      
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
  
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doGet(request, response);
    }
        
    /**
      * @see GenericServlet#executeDefault(HttpServletRequest, HttpServletResponse)
      */         
    public void executeDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.debug("Antes de hacer el sendRedirect");
            //response.sendRedirect("/portal/page/portal/ORDERS/RETAIL_NEW");
            
            response.sendRedirect("/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_LIST");
        } catch (Exception e) {
            logger.error(formatException(e));
        }
    }
    
   
    public void refrescarLista(HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException, IOException {
       List listaInterna = new ArrayList();
       RequestService requestService = new RequestService();
       String building = (String)request.getParameter("building");
       listaInterna = requestService.getPendingRequest(MiUtil.parseInt(building)); 
       
       int tamanioInicial = MiUtil.parseInt(request.getParameter("tamanioInicial"));
       
       PrintWriter out = response.getWriter();
       
       /*HttpSession session = request.getSession(true);
       List listaSesion = (ArrayList)session.getAttribute("listRequestIni");*/
       
       System.out.println("tamaño Lista Interna:"+listaInterna.size());
       System.out.println("tamaño Lista tamanioInicial:"+tamanioInicial);
      if (listaInterna.size()> tamanioInicial) {      
          //tamanioInicial = listaInterna.size();
          out.write("1");
       }else
       {
          out.write("0");    
       }
       out.close();
    }
    
    public void loadListRequest(HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException, IOException
    {   
         String building = (String)request.getAttribute("building");
         RequestService requestService = new RequestService();
         String tamanioInicial = "";
         
        System.out.println("--ResquestServlet_TDE loadListRequest params: " + building + "--");
        
         List listRequestIni = requestService.getPendingRequest(MiUtil.parseInt(building)); 
         
         tamanioInicial = String.valueOf(listRequestIni.size());
         
         request.setAttribute("SOLICITUD_LISTA_BEAN",listRequestIni);
         request.setAttribute("tamanioInicial",tamanioInicial);
         
         /*HttpSession session = request.getSession(true);
         session.setAttribute("listRequestIni" ,listRequestIni);*/
          
         System.out.println("lista todas las solicitudes : tamanioInicial = " + tamanioInicial);
         System.out.println("paso bien loadListRequest");
         listRequestIni = null;

    }
    
    public void obtenerSIM(HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException, IOException{
      
      String imei = request.getParameter("COD_IMEI");
      String strRequesrId = request.getParameter("strRequesrId");
      String flagValidaSim = ""; 
      String cadena = "";
      
      System.out.println("Servlet: ---obtenerSIM---");
      System.out.println("imei = " + imei);
      System.out.println("strRequesrId = " + strRequesrId);
      long requestId = Long.parseLong(MiUtil.trimNotNull(strRequesrId));
      
      RequestService requestService = new RequestService();   
      //obtener flag, avchOrigin
      String flagRequestTde=requestService.getFlagRequestOlTde(requestId);
      System.out.println("flag obtenido (flagRequestTde)= " + flagRequestTde);
      
      HashMap mapaDatos =  requestService.getSIM(imei, flagRequestTde);      
      String strSim = MiUtil.trimNotNull((String)mapaDatos.get(Constante.SIM));      
      String strMensaje = (String)mapaDatos.get(Constante.MESSAGE_OUTPUT);
      
      //---Validamos SIM---
      HashMap mapaDatos2 =  requestService.validarSim(strRequesrId);    
      flagValidaSim = (String)mapaDatos2.get(Constante.FLAG_VALIDA_SIM);
      
      cadena = strSim + "|" + flagValidaSim;
      
      System.out.println("strSim = " + strSim);
      System.out.println("flagValidaSim = " + flagValidaSim);
      
      PrintWriter out = response.getWriter();
      out.write(cadena);
      out.close();
    
    }
    
    //FIN JGABRIEL REQ-0123
    public void doValidateSubInventoryCode(HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException, IOException{
      
      String strSubInventoryCode = request.getParameter("sub_inventorycode");
      String strNumRequest = request.getParameter("numrequest");
 
      System.out.println("---------doValidateSubInventoryCode---------");
      System.out.println("strSubInventoryCode = " + strSubInventoryCode);
      System.out.println("strNumRequest = " + strNumRequest);
      System.out.println("--------------------------------------------");
      
      RequestService requestService = new RequestService();      
      String strMensaje =  requestService.validateSubInventoryCode(strSubInventoryCode,strNumRequest);
      
      System.out.println("strMensaje = " + strMensaje);
      
      PrintWriter out = response.getWriter();
      out.write(strMensaje);
      out.close();
    } 
    
    public void doValidateModelRequested(HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException, IOException{
        System.out.println("INICIO RequestServletTDE doValidateModelRequested");
      String strImei = request.getParameter("imei");
      String strNumRequest = request.getParameter("numrequest");
    
      System.out.println("----------doValidateModelResquested----------");
      System.out.println("strImei = " + strImei);
      System.out.println("strNumRequest = " + strNumRequest);
      System.out.println("---------------------------------------------");
      
      RequestService requestService = new RequestService();      
      String strMensaje =  requestService.validateModelResquested(strImei,strNumRequest)  ;      
      
      System.out.println("strMensaje = " + strMensaje);
      
      PrintWriter out = response.getWriter();
      out.write(strMensaje);
      out.close();
    } 
    //FIN JGABRIEL REQ-0123
    public void validarRegularizarOrdenOutdoor(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        logger.info("[validarRegularizarOrdenOutdoor] inicio");
        BafiOutdoorService bafiOutdoorService = new BafiOutdoorService();
        HashMap respuesta = null;
        Gson gson = new Gson();
        String respuestaJson = null;
        
        Long ordenId = Long.parseLong(request.getParameter("ordenId"));
        String imei     = request.getParameter("imei");
        
        respuesta = bafiOutdoorService.validarRegularizarOrdenOutdoor(ordenId, imei);

        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        respuestaJson = gson.toJson(respuesta);
        logger.info("[validarRegularizarOrdenOutdoor] respuestaJson: "+respuestaJson);
        response.getWriter().write(respuestaJson);
        response.getWriter().flush();
        logger.info("[validarRegularizarOrdenOutdoor] fin");     
    } 
    
    public void regularizarOrdenOutdoor(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        logger.info("[regularizarOrdenOutdoor] inicio");
        HashMap respuesta = null;
        BafiOutdoorService bafiOutdoorService = new BafiOutdoorService();
        Gson gson = new Gson();
        String respuestaJson = null;
        
        Long ordenId = Long.parseLong(request.getParameter("ordenId"));
        String imei     = request.getParameter("imei");
        String almacenId = request.getParameter("almacenId");
        String creadoPor = request.getParameter("creadoPor");
        
        respuesta = bafiOutdoorService.regularizarOrdenOutdoor(ordenId, imei,almacenId,creadoPor);

        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        respuestaJson = gson.toJson(respuesta);
        logger.info("[regularizarOrdenOutdoor] respuestaJson: "+respuestaJson);
        response.getWriter().write(respuestaJson);
        response.getWriter().flush();
        logger.info("[regularizarOrdenOutdoor] fin");     
    }           
}