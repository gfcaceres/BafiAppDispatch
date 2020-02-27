<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="oracle.portal.provider.v2.render.PortletRenderRequest" %>
<%@page import="oracle.portal.provider.v2.http.HttpCommonConstants" %>
<%@page import="oracle.portal.provider.v2.ProviderSession" %>
<%@page import="oracle.portal.provider.v2.ProviderUser" %>
<%@page import="oracle.portal.provider.v2.render.PortletRendererUtil" %>
<%@page import="oracle.portal.provider.v2.render.http.HttpPortletRendererUtil" %>
<%@page import="pe.com.nextel.bean.PortalSessionBean" %>
<%@page import="pe.com.nextel.exception.SessionException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@page import="pe.com.nextel.util.MiUtil"%>
<%@ page import="pe.com.nextel.service.RequestService" %>
<%@ page import="pe.com.nextel.service.GeneralService" %>
<%@page import="pe.com.nextel.service.SessionService"%>
<%@ page import="pe.com.nextel.util.Constante" %>
<%@ page import="pe.com.nextel.bean.DominioBean" %>
<%@ page import="pe.com.nextel.servlet.RequestServlet" %>
<%@ page import="org.apache.commons.lang.StringUtils"%>

<%


try{

 
  String strSessionSearchRequestId = "";
  String strPortletPagePathContext="";

  try{
    PortletRenderRequest pReq = (PortletRenderRequest) request.getAttribute(HttpCommonConstants.PORTLET_RENDER_REQUEST);
    strPortletPagePathContext = "/" + StringUtils.substringAfter(StringUtils.substringAfter(pReq.getParameter("_page_url"),pReq.getServerName()),"/");
    ProviderUser objetoUsuario1 = pReq.getUser();
    strSessionSearchRequestId=objetoUsuario1.getPortalSessionId();
    System.out.println("Sesión capturada  JP_SEARCH_REQUEST_STORE_ShowPage : " + objetoUsuario1.getName() + " - " + strSessionSearchRequestId );
  
  }catch(Exception e){
    System.out.println("Portler Not Found : " + e.getClass() + " - " + e.getMessage() );
    out.println("Portlet JP_SEARCH_REQUEST_STORE_ShowPage Not Found");
    return;
  }
  
  System.out.println("Sesión capturada después del request : " + strSessionSearchRequestId );
	PortalSessionBean portalSessionBean3 = (PortalSessionBean)SessionService.getUserSession(strSessionSearchRequestId);
	if(portalSessionBean3==null) {
    System.out.println("No se encontró la sesión de Java ->" + strSessionSearchRequestId);
		throw new SessionException("La sesión finalizó");
	}
  
   int iUserId=0;
   int iAppId=0;   
   int idbuilding = 0; 
   String tienda = "";
    
   iUserId  = portalSessionBean3.getUserid();
   iAppId   = portalSessionBean3.getAppId();  
  
   idbuilding = portalSessionBean3.getBuildingid();
   tienda = String.valueOf(idbuilding);
   
   request.setAttribute("tienda", tienda);
  
  System.out.println("JSP iUserId:"+iUserId);
  System.out.println("JSP iAppId:"+iAppId);
  System.out.println("JSP idbuilding:"+idbuilding);
 
   request.setAttribute("idbuilding", String.valueOf(idbuilding));
   new RequestServlet().iniciarPagina(request,response);

    Calendar cal =Calendar.getInstance();
    Calendar cal2 =Calendar.getInstance();
    cal.setTime(new Date());
    cal2.setTime(new Date());
    cal2.add(Calendar.MONTH,-3);
       
%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>Busqueda de Solicitudes</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/websales/Resource/salesweb.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/websales/Resource/date-picker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/websales/Resource/library.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/websales/Resource/jQuery-min.js"></script>
    <script>
        function fxSaveRequest() {
            vForm = document.frmdatos;            
            vForm.action = "<%=request.getContextPath()%>/requestservlet?myaction=doSaveRequest";
            vForm.submit();
        }
        
        function buscar(){
        
          var formulario = document.frmdatos;
          formulario.action = '<c:out value="${pageContext.request.contextPath}"/>/requestservlet?METHOD=SEND_PARAM_SEARCH&vieneDe=BUSQUEDA';    
          if(validar()){
            formulario.submit();            
          }         
        }
        
        
        
        function fxSearchCustomer() {
          var form        = document.frmdatos;
          var txtCustomerName = frmdatos.txtCustomerName;
          if (txtCustomerName.value == "") {
              //frmdatos.txtNumber.value = "";
              //fxCleanObjects();
          }
          frmdatos.hdnCustomerName.value = txtCustomerName.value;
          url = "/portal/pls/portal/!WEBSALES.NPSL_CUSTOMER01_PL_PKG.PL_CUSTOMER_SEARCH"
              + "?av_customername=" + escape(txtCustomerName.value);
              
          url = "/portal/pls/portal/websales.npsl_general_pl_pkg.window_frame?av_title="
              + escape("Búsqueda de compañía") + "&av_url=" + escape(url);
              
          WinAsist = window.open(url,"WinAsist","toolbar=no,location=0,directories=no,status=yes,menubar=0,scrollbars=no,resizable=no,screenX=100,top=80,left=100,screenY=80,width=700,height=500,modal=yes");
        }
        
        
        
    function fxValidateCustomer(v_origen, v_type) {
        var v_form            = document.frmdatos;
        var v_txtCustomerName = v_form.txtCustomerName.value;
        var v_hacer_busqueda  = false;
        var v_hdnCustomerName = v_form.hdnCustomerName.value;
        var v_hdnCustomerId   = v_form.hdnCustomerId.value;
       // var v_txtRuc          = v_form.txtRuc.value;
        var v_txtCodBscs      = v_form.txtCodBscs.value;
        //var v_hdnStatusCollection = v_form.hdnStatusCollection.value;
        //var v_hdnStatusVEP    = v_form.hdnStatusVEP.value;
            
        //v_form.txtNumber.value = "";
        
        if (v_origen == "CUST_NAME") {
            if (v_txtCustomerName != v_hdnCustomerName && v_txtCustomerName != "") {
                //v_form.txtRuc.value ="";
                //v_form.hdnRuc.value="";
                v_form.txtCodBscs.value ="";
                v_form.hdnCustomerId.value="";
                v_form.hdnCustomerId.value="";
                //v_form.hdnCustomerIdbscs.value="";
                //fxCleanGeneralObjects();
                /*if (v_txtCustomerName != "" || v_hdnCustomerId != ""  || v_txtRuc != "" || v_txtCodBscs != "") {
                    v_hacer_busqueda = true;
                    if (v_type == "1")
                       v_form.hdnSearchType.value = "1";
                }*/
                
                if (v_txtCustomerName != "" || v_hdnCustomerId != ""  || v_txtCodBscs != "") {
                    v_hacer_busqueda = true;
                    if (v_type == "1")
                       v_form.hdnSearchType.value = "1";
                }
                
            }
            else if (v_txtCustomerName == "") {
                fxCleanObjects();
            }
        }
        if (v_origen == "CUST_RUC") {
            if ((v_form.txtRuc.value != v_form.hdnRuc.value || v_form.hdnRuc.value == "") &&  v_txtRuc != "") {
                v_form.hdnCustomerName.value ="";
                v_form.txtCustomerName.value ="";
                v_form.txtCodBscs.value ="";
                v_form.hdnCustomerId.value="";
                //v_form.hdnCustomerIdbscs.value="";
                //fxCleanGeneralObjects();
                v_hacer_busqueda = true;
                if (v_type == "1") 
                   v_form.hdnSearchType.value = "1";
            }
            else if (v_txtRuc == "") {
                fxCleanObjects();     
            }
        }
        if (v_origen == "CUST_CODBSCS") {
            if ( (v_form.txtCodBscs.value != v_form.hdnCodbscs.value || v_form.txtCodBscs.value == "") && v_txtCodBscs != "") {
                v_form.hdnCustomerName.value ="";
                v_form.txtCustomerName.value ="";
                //v_form.txtRuc.value ="";
                //v_form.hdnRuc.value="";
                v_form.hdnCustomerId.value="";
                //v_form.hdnCustomerIdbscs.value="";
                
                //fxCleanGeneralObjects();
                v_hacer_busqueda = true;
                if (v_type == "1")
                   v_form.hdnSearchType.value = "1";
            }  
            else if (v_txtCodBscs == "") {
                fxCleanObjects();
            }
        }
        if (v_origen == "CUST_DOCUMENTS") {
            v_hacer_busqueda = true;
        }
        if (v_origen == "CUST_SELECTED") {
            v_hacer_busqueda = true;
        }
        if (v_origen == "CUSTID_SELECTED") {
            if (v_form.hdnCustomerId.value !="") {
                v_hacer_busqueda = true;
            }
        }
        if (v_hacer_busqueda == true){
            v_form.action="/portal/pls/portal/!WEBSALES.NPSL_CUSTOMER02_PL_PKG.PL_VALIDATE_CUSTOMER";
            v_form.target = "bottomFrame";
            v_form.submit();
        }
        return;
    }
    
        function fxCleanObjects() {
            var v_form = document.frmdatos;
            v_form.hdnCustomerName.value ="";
            v_form.hdnCustomerId.value="";
            //v_form.hdnRuc.value="";
            //v_form.hdnCustomerIdbscs.value="";
            //v_form.txtRuc.value ="";
            v_form.txtCodBscs.value ="";
            v_form.txtCustomerName.value ="";
            //v_form.hdnStatusCollection.value="";
            //v_form.hdnStatusVEP.value="";
            //v_form.hdnTypecia.value="";  
            //v_form.hdnNumber.value="";
            //v_form.hdnCodbscs.value="";
            fxCleanGeneralObjects();
        }
        
        function fxCleanGeneralObjects() {
      
        }
        
        function compararFechasRAE(fechaInicio, fechaFin){
          var intFechaIni = 0;
          var intFechaFin = 0;
          var strFechaIni = fechaInicio.value;
          var strFechaFin = fechaFin.value;
          
          var posInicio = strFechaIni.indexOf('/');
                var dia = strFechaIni.slice(0,posInicio);
          strFechaIni = strFechaIni.substr(posInicio+1);
          var posInicio = strFechaIni.indexOf('/');
          var mes = strFechaIni.slice(0,posInicio);
          strFechaIni = strFechaIni.substr(posInicio+1);
          var anho = strFechaIni;
                strFechaIni = anho + mes + dia;  
          intFechaIni = parseInt(strFechaIni);
          
          posInicio = strFechaFin.indexOf('/');  
                dia = strFechaFin.slice(0,posInicio);
          strFechaFin = strFechaFin.substr(posInicio+1);
          posInicio = strFechaFin.indexOf('/');
          mes = strFechaFin.slice(0,posInicio);
          strFechaFin = strFechaFin.substr(posInicio+1);
          anho = strFechaFin;
                strFechaFin = anho + mes + dia;  
          intFechaFin = parseInt(strFechaFin);
          if(intFechaIni > intFechaFin){
            alert("La fecha de inicio no puede ser mayor a la fecha de fin.");
            fechaInicio.focus();
            return false;
          }
          
          return true;
             
        }
        
        function validar(){
          var ubicacion = document.getElementById("cboUbicacion");
          var estadoSolicitud = document.getElementById("cboEstadoSolicitud");
          var nroOrden  = document.getElementById("txtNroOrden");
          var fachaIni =  document.getElementById("txtCreateDateFrom");
          var fachaFin =  document.getElementById("txtCreateDateTill");
          
          if(trim(nroOrden.value)==""){
              if(trim(ubicacion.value)==""){
                alert("Debe seleccionar una ubicacion");
                return false; 
              }
              if(trim(estadoSolicitud.value)==""){              
                alert("Debe seleccionar un Estado de Solicitud");
                return false; 
              }
              if (isValidDate(fachaIni.value)== false)
              {
                  fachaIni.focus();
                  return false;
              }
              
              if (isValidDate(fachaFin.value)== false)
              {
                  fachaFin.focus();
                  return false;
              }
              if(!compararFechasRAE(fachaIni, fachaFin)){
                  return false;
              }
              
          }
          
          return true;
          
        }        
         
        
        function exportarExcel(){ 
          /*var formulario = document.frmdatos;
          
          formulario.action ="portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_EXC";
          
          if(validar()){
            formulario.submit();            
          }   */
          
          var formulario = document.frmdatos;
          formulario.action = '<c:out value="${pageContext.request.contextPath}"/>/requestservlet?METHOD=EXPORTAR_PAGE';    
          if(validar()){
            formulario.submit();            
          }
        }
        
        /****function exportarExcel() {
            //var vForm = document.frmdatos;   
                        
            //vForm = document.frmdatos;
            //vForm.action = "/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_EXC";
            //vForm.action = "/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_LIST";
            //vForm.action = "/portal/page/portal/inventory/DISPATCH_MANUAL_REQUEST_STORE";
            //vForm.target = "_self";
            //vForm.method = "POST";
            ///vForm.submit();
            
            var cboUbicacion = document.getElementById("cboUbicacion").value;            
            var cboUsuario = document.getElementById("cboUsuario").value;
            var txtNroSolicitud = document.getElementById("txtNroSolicitud").value;
            var txtNroOrden = document.getElementById("txtNroOrden").value;
            var cboOrdenPagada = document.getElementById("cboOrdenPagada").value;
            var cboEstadoSolicitud = document.getElementById("cboEstadoSolicitud").value;
            var cboDivisionNegocio = document.getElementById("cboDivisionNegocio").value;
            var cboSubCategoria = document.getElementById("cboSubCategoria").value;
            var txtNroFactura = document.getElementById("txtNroFactura").value;
            var txtCustomerName = document.getElementById("txtCustomerName").value;
            var txtCreateDateFrom = document.getElementById("txtCreateDateFrom").value;
            var txtCreateDateTill = document.getElementById("txtCreateDateTill").value;
            var txtCodBscs = document.getElementById("txtCodBscs").value;
            
            var parametros = "";
            var url = "";
            
            parametros += "cboUbicacion="+cboUbicacion+"&";            
            parametros += "cboUsuario="+cboUsuario+"&";
            parametros += "txtNroSolicitud="+txtNroSolicitud+"&";
            parametros += "txtNroOrden="+txtNroOrden+"&";
            parametros += "cboOrdenPagada="+cboOrdenPagada+"&";
            parametros += "cboEstadoSolicitud="+cboEstadoSolicitud+"&";
            parametros += "cboDivisionNegocio="+cboDivisionNegocio+"&";
            parametros += "cboSubCategoria="+cboSubCategoria+"&";
            parametros += "txtNroFactura="+txtNroFactura+"&";
            parametros += "txtCustomerName="+txtCustomerName+"&";
            parametros += "txtCreateDateFrom="+txtCreateDateFrom+"&";
            parametros += "txtCreateDateTill="+txtCreateDateTill+"&";
            parametros += "txtCodBscs="+txtCodBscs;
            
            url= "/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_EXC?"+parametros;
            //location.replace(url);            
            window.open(url);
        }*/
        
        function fxLoadGeneral() {
             v_form = document.frmdatos;
             if (v_form.hdnSearchType.value == "1")
                fxSearchOrder();
          }
          
        function fxSearchOrder() {
          var form = document.frmdatos;
          form.method="POST";
          form.action = "<c:out value="${pageContext.request.contextPath}"/>/Search.do";  
          form.submit();
        }
        
    </script>
  </head>
  <body>
  
    <form name="frmdatos" method="post">
    
    <input type="hidden" name="hdnSearchContinue" id="hdnSearchContinue"/>
    <input type="hidden" name="hdnSearchType" id="hdnSearchType"/>
        
    <br/>
    
     <table border="0" cellspacing="7" cellpadding="0" width="100%" align="center">
        <tr>
        <td>
        <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
            <tr>
              <td class="SectionTitleLeftCurve" width="10">&nbsp;&nbsp;</td>
               <td class="SectionTitle">&nbsp;&nbsp;B&uacute;squeda&nbsp;Solicitud&nbsp;de&nbsp;Almacen</td>
               <td class="SectionTitleRightCurve" width="8">&nbsp;&nbsp;</td>
             </tr>
         </table>
         <table border="0" cellspacing="1" cellpadding="1" align="center" class="RegionBorder" width="100%">
              <tr>
                <td class="CellLabel" width="120px"><font color="#FF0000">*&nbsp;</font> Ubicaci&oacute;n</td>
                <td class="CellContent">
                  <select name="cboUbicacion" id="cboUbicacion">
                    <option value=""></option>
                    <c:forEach var="ubicacion" items="${requestScope.SOLICITUD_LISTA_UBICACION}">
                    
                      <c:if test="${ubicacion.valor == requestScope.tienda}" > 
                        <option value="<c:out value="${ubicacion.valor}"/>" selected>
                          <c:out value="${ubicacion.descripcion}"/>
                        </option>                        
                      </c:if>
                      
                      <c:if test="${ubicacion.valor != requestScope.tienda}" >
                        <option value="<c:out value="${ubicacion.valor}"/> ">
                          <c:out value="${ubicacion.descripcion}"/>
                        </option>                        
                      </c:if>                  
                      
                    </c:forEach>
                  </select>
                </td>
                <td class="CellLabel" width="120px">Usuario</td>
                <td class="CellContent">           
                
                  <select name="cboUsuario" id="cboUsuario">
                    <option value=""></option> 
                    <c:forEach var="usuario" items="${requestScope.SOLICITUD_LISTA_USUARIOS}">
                      <option value="<c:out value="${usuario.valor}"/> ">
                        <c:out value="${usuario.descripcion}"/>
                      </option>
                    </c:forEach>
                  </select>
                </td>
                <td class="CellLabel" width="120px">N&deg; Solicitud</td>
                <td class="CellContent">
                  <input type="text" id="txtNroSolicitud" name="txtNroSolicitud" size="7" maxlength="15" onblur="this.value=fxTrim(this.value);"/>
                </td>
              </tr>
              <tr>
                <td class="CellLabel">N&deg; Orden</td>
                <td class="CellContent">
                  <input type="text" id="txtNroOrden" name="txtNroOrden" size="7" maxlength="15" onblur="this.value=fxTrim(this.value);"/>
                </td>
                <td class="CellLabel">Orden Pagada</td>
                <td class="CellContent">
                  <select name="cboOrdenPagada" id="cboOrdenPagada">
                    <option value=""></option>
                    <c:forEach var="ordenPagada" items="${requestScope.SOLICITUD_LISTA_ORDEN_PAGADA}">
                      <option value="<c:out value="${ordenPagada.valor}"/> ">
                        <c:out value="${ordenPagada.descripcion}"/>
                      </option>
                    </c:forEach>
                  </select>
                </td>
                <td class="CellLabel">Estado de Solicitud</td>
                <td class="CellContent">
                  <select name="cboEstadoSolicitud" id="cboEstadoSolicitud">
                    <option value=""></option>
                    <c:forEach var="estadoSolicitud" items="${requestScope.SOLICITUD_LISTA_ESTADOS}">
                      <c:if test="${estadoSolicitud.valor == '0'}" >
                        <option value="<c:out value="${estadoSolicitud.valor}"/>" selected>
                          <c:out value="${estadoSolicitud.descripcion}"/>
                        </option>
                      </c:if> 
                      <c:if test="${estadoSolicitud.valor != '0'}" >
                        <option value="<c:out value="${estadoSolicitud.valor}"/> ">
                          <c:out value="${estadoSolicitud.descripcion}"/>
                        </option>
                      </c:if>
                      
                    </c:forEach>
                  </select>
                </td>
              </tr>
              <tr>
                <td class="CellLabel">Divisi&oacute;n de Negocio</td>
                <td class="CellContent">
                  <select name="cboDivisionNegocio" id="cboDivisionNegocio" onchange="obtenerCategoriasByDivisionNegocio(this)">
                  <option value=""></option>
                    <c:forEach var="divisionNegocio" items="${requestScope.SOLICITUD_LISTA_DIVISION_NEGOCIO}">
                      
                       <c:if test="${divisionNegocio.valor == '1'}" >
                          <option value="<c:out value="${divisionNegocio.valor}"/> " selected >
                            <c:out value="${divisionNegocio.descripcion}"/>
                          </option>
                       </c:if>
                       
                       <c:if test="${divisionNegocio.valor != '1'}" >
                          <option value="<c:out value="${divisionNegocio.valor}"/> " >
                            <c:out value="${divisionNegocio.descripcion}"/>
                          </option>
                       </c:if>
                       
                    
                      
                    </c:forEach>
                  </select>
                </td>
                <td class="CellLabel">Categor&iacute;a</td>
                <td class="CellContent">
                  <select name="cboCategoria" id="cboCategoria" onchange="obtenerSubCategoriasByCategoria()">
                    <option value=""></option>                      
                    
                    <c:forEach var="categoria" items="${requestScope.SOLICITUD_LISTA_CATEGORIA}">
                      <option value="<c:out value="${categoria.valor}"/> " >
                            <c:out value="${categoria.descripcion}"/>
                      </option>
                    </c:forEach>                    
                    
                  </select>
                </td>
                <td class="CellLabel">Sub Categor&iacute;a</td>
                <td class="CellContent">
                  <select name="cboSubCategoria" id="cboSubCategoria" >
                    <option value=""></option>                     
                  </select>
                </td>
              </tr>
              <tr>
                <td class="CellLabel">N&deg; Factura</td>
                <td class="CellContent">
                  <input type="text" id="txtNroFactura" name="txtNroFactura" size="7" maxlength="15" onblur="this.value=fxTrim(this.value);"/>
                </td>
                <td class="CellLabel" rowspan="2">&nbsp; 
                  <font color="#FF0000">*&nbsp;</font>
                  <a href="javascript:fxSearchCustomer()">Raz&oacute;n&nbsp;Social</a> 
                </td>
                <td class="CellContent" rowspan="2">
                  <!--<input type="text" id="txtCustomerName" name="txtCustomerName" size="30" maxlength="15" onblur="this.value=fxTrim(this.value);"/>-->
                  <input type="text" name="txtCustomerName" id="txtCustomerName" size="35" maxlength="75" onKeyDown="if (window.event.keyCode== 9) {this.value=trim(this.value.toUpperCase());fxValidateCustomer('CUST_NAME','0');}" onKeyPress="if (window.event.keyCode== 13) {this.value=trim(this.value.toUpperCase());fxValidateCustomer('CUST_NAME','1');}"/>
                  <input type="hidden" name="hdnCustomerName" id="hdnCustomerName"/>
                  <input type="hidden" name="hdnCustomerId" id="hdnCustomerId"/>
                  
                </td>
                <td class="CellLabel" rowspan="2"><font color="#FF0000">*&nbsp;</font> Fecha Solicitud</td>
                <td class="CellContent" rowspan="2">&nbsp;Desde&nbsp;
                  <input type="text" name="txtCreateDateFrom" id="txtCreateDateFrom" size="10" maxlength="10" value="<%=MiUtil.getDate(cal2.getTime(), "dd/MM/yyyy")%>" onblur="this.value=trim(this.value);"/>
                  <a href="javascript:show_calendar('frmdatos.txtCreateDateFrom',null,null,'DD/MM/YYYY');" onmouseover="window.status='Fecha desde';return true;" onmouseout="window.status='';return true;">
                    <img src="<%=request.getContextPath()%>/websales/images/show-calendar.gif" width="24" height="22" border="0"/>
                  </a>&nbsp; 
                  <i>DD/MM/YYYY</i>
                  <br/>&nbsp;Hasta&nbsp;&nbsp;
                  <input type="text" name="txtCreateDateTill" id="txtCreateDateTill" size="10" maxlength="10" value="<%=MiUtil.getDate(cal.getTime(), "dd/MM/yyyy")%>" onblur="this.value=trim(this.value);"/>
                  <a href="javascript:show_calendar('frmdatos.txtCreateDateTill',null,null,'DD/MM/YYYY');" onmouseover="window.status='Fecha hasta';return true;" onmouseout="window.status='';return true;">
                    <img src="<%=request.getContextPath()%>/websales/images/show-calendar.gif" width="24" height="22" border="0"/>
                  </a>&nbsp; 
                  <i>DD/MM/YYYY</i> 
                </td>
              </tr>
              <tr>
                <td class="CellLabel">Cod BSCS</td>
                <td class="CellContent">
                  <!--<input type="text" id="txtCodigoBSCS" name="txtCodigoBSCS" size="7" maxlength="15" onblur="this.value=fxTrim(this.value);"/> -->
                  <input type="text" name="txtCodBscs" id="txtCodBscs" value="" size="15" maxlength="50" onKeyDown="if (window.event.keyCode== 9) {this.value=trim(this.value.toUpperCase());fxValidateCustomer('CUST_CODBSCS','0');}" onKeyPress="if (window.event.keyCode== 13) {this.value=trim(this.value.toUpperCase());fxValidateCustomer('CUST_CODBSCS','1');}"/>
                </td>
              </tr>
             </table>
              <br/>
              <table align="center">
              <tr>
                <td colspan="6" align="center">
                  <input type="button" name="btnGrabar" value="Buscar" onclick="buscar()"/>
                  <input type="button" name="btnExportar" value="Exportar" onclick="exportarExcel()"/>
                  <!--<a href="javascript:exportarExcel()">EXportar Excel</a>-->
                </td>
              </tr>
            </table>
            </tr>
           </td>
      </table>
    </form>
    
    <script>
    
      
      function obtenerCategoriasByDivisionNegocio(idDivisionNegocio){        
        $.ajax({  
            type: "POST",  
            url: "<c:out value="${pageContext.request.contextPath}"/>/requestservlet",
            data: "METHOD=GET_CATEGORIA_BY_DIVISION_NEGOCIO&ID_DIVISION_NEGOCIO="+idDivisionNegocio.value,                  
            success: function(options){               
              $('#cboCategoria').html(options)                
            }                
          }); 
             
          obtenerSubCategoriasByCategoria();
       }
       
       function obtenerSubCategoriasByCategoria(){ 
                  
         var idDivisionNegocio = document.getElementById("cboDivisionNegocio").value;     
         var idCategoria = document.getElementById("cboCategoria").value; 
                   
          $.ajax({  
              type: "POST",  
              url: "<c:out value="${pageContext.request.contextPath}"/>/requestservlet",
              data: "METHOD=GET_SUBCATEGORIA_BY_CATEGORIA&ID_DIVISION_NEGOCIO="+idDivisionNegocio+"&ID_CATEGORIA="+idCategoria,
              success: function(options){               
                $('#cboSubCategoria').html(options)                
              }                
            });       
       }
      
    
    </script>
    
  </body>
</html>

<%

}catch(SessionException se) {
    System.out.println("[JP_SEARCH_REQUEST_STORE_ShowPage][SessionException] : " + se.getClass() + " - " + se.getMessage());
    se.printStackTrace();
  }catch(Exception e) {
    String strMessageExceptionGeneralStart = "";
    strMessageExceptionGeneralStart = MiUtil.getMessageClean(e.getMessage());
    System.out.println("[JP_SEARCH_REQUEST_STORE_ShowPage][Exception] : " + e.getClass() + " - " + e.getMessage());
    out.println("<script>alert('"+strMessageExceptionGeneralStart+"');</script>");   
  }
%>