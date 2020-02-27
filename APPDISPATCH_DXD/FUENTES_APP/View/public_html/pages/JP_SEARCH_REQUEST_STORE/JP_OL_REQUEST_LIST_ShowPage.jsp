<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="oracle.portal.provider.v2.render.PortletRenderRequest" %>
<%@page import="oracle.portal.provider.v2.http.HttpCommonConstants" %>
<%@page import="oracle.portal.provider.v2.ProviderSession" %>
<%@page import="oracle.portal.provider.v2.ProviderUser" %>
<%@page import="oracle.portal.provider.v2.PortletDefinition" %>
<%@page import="oracle.portal.provider.v2.PortletInstance" %>
<%@page import="oracle.portal.provider.v2.render.PortletRendererUtil" %>
<%@page import="oracle.portal.provider.v2.render.http.HttpPortletRendererUtil" %>
<%@page import="pe.com.nextel.bean.PortalSessionBean" %>
<%@page import="pe.com.nextel.exception.SessionException" %>
<%@page import="pe.com.nextel.service.SessionService"%>
<%@ page import="pe.com.nextel.servlet.RequestServlet" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@page import="pe.com.nextel.util.MiUtil"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="org.displaytag.tags.TableTagParameters"%>
<%@ page import="org.displaytag.util.ParamEncoder"%>
<%@ page import="pe.com.nextel.bean.RequestBean"%>

<%@ page import="pe.com.nextel.service.RequestService"%>
<%@ taglib uri="http://displaytag.sf.net/" prefix="display"%>

<%
try{
 
  String strSessionSearchRequestListId = "";
  String strPortletPagePathContext="";
    
  try{
    PortletRenderRequest pReq = (PortletRenderRequest) request.getAttribute(HttpCommonConstants.PORTLET_RENDER_REQUEST);
    strPortletPagePathContext = "/" + StringUtils.substringAfter(StringUtils.substringAfter(pReq.getParameter("_page_url"),pReq.getServerName()),"/");
    ProviderUser objetoUsuario1 = pReq.getUser();
    PortletDefinition objetoPortlet = pReq.getPortletDefinition();
    String namePortlet = objetoPortlet.getNameKey();
    PortletInstance objetoPortletInstanced = pReq.getPortletInstance();
    String urlPortlet = pReq.getContentType(); //pReq.getRequestURL();
   
    String instancia = objetoPortletInstanced.getActionParameterName(); // objetoPortletInstanced.getInstanceName();
    strSessionSearchRequestListId=objetoUsuario1.getPortalSessionId();
    System.out.println("Sesión capturada  JP_OL_REQUEST_LIST_ShowPage : " + objetoUsuario1.getName() + " - " + strSessionSearchRequestListId + " - " +instancia+" * / "+urlPortlet);
  
  }catch(Exception e){
    System.out.println("Portler Not Found : " + e.getClass() + " - " + e.getMessage() );
    out.println("Portlet JP_OL_REQUEST_LIST_ShowPage Not Found");
    return;
  }
  
  System.out.println("Sesión capturada después del request : " + strSessionSearchRequestListId );
	PortalSessionBean portalSessionBean3 = (PortalSessionBean)SessionService.getUserSession(strSessionSearchRequestListId);
  
  
    if(portalSessionBean3==null) {
            System.out.println("No se encontró la sesión de Java ->" + strSessionSearchRequestListId);
            throw new SessionException("La sesión finalizó");
    }
      
      //---INICIO: PAGINACION---
      String strParentOrderId = StringUtils.defaultString(request.getParameter("an_nporderid"),"0");
      System.out.println("EDU Servlet::: " + strParentOrderId);
      long lParentOrderId = Long.parseLong(strParentOrderId);
            
      String strHdnNumPagina = StringUtils.defaultString(request.getParameter("hdnNumPagina"),"1");
      long lPageSelected = Long.parseLong(strHdnNumPagina);
      String strHdnNumRegistros = StringUtils.defaultString(request.getParameter("hdnNumRegistros"),"10");
      int iRowsByPage = Integer.parseInt(strHdnNumRegistros);      
      //---FIN: PAGINACION---
      
      
      int  building =   portalSessionBean3.getBuildingid();
      String param = MiUtil.trimNotNull(request.getParameter("vieneDe"));
      
      
      String cboUbicacion = "";
      String cboUsuario = "";
      String txtNroSolicitud = "";
      String txtNroOrden = "";
      String cboOrdenPagada = "";
      String cboEstadoSolicitud = "";
      String cboDivisionNegocio = "";
      String cboCategoria = "";
      String cboSubCategoria = "";
      String txtNroFactura = "";
      String txtCustomerName = "";
      String txtCreateDateFrom = "";
      String txtCreateDateTill = "";
      String txtCodBscs = "";
      
      if(param.equals("BUSQUEDA")){
          
          cboUbicacion = MiUtil.trimNotNull(request.getParameter("cboUbicacion"));    
          cboUsuario = MiUtil.trimNotNull(request.getParameter("cboUsuario"));        
          txtNroSolicitud = MiUtil.trimNotNull(request.getParameter("txtNroSolicitud"));
          txtNroOrden = MiUtil.trimNotNull(request.getParameter("txtNroOrden"));    
          cboOrdenPagada = MiUtil.trimNotNull(request.getParameter("cboOrdenPagada"));   
          cboEstadoSolicitud = MiUtil.trimNotNull(request.getParameter("cboEstadoSolicitud"));    
          cboDivisionNegocio = MiUtil.trimNotNull(request.getParameter("cboDivisionNegocio"));   
          cboCategoria = MiUtil.trimNotNull(request.getParameter("cboCategoria"));    
          cboSubCategoria = MiUtil.trimNotNull(request.getParameter("cboSubCategoria"));  
          txtNroFactura = MiUtil.trimNotNull(request.getParameter("txtNroFactura"));  
          txtCustomerName = MiUtil.trimNotNull(request.getParameter("txtCustomerName"));
          txtCreateDateFrom = MiUtil.trimNotNull(request.getParameter("txtCreateDateFrom"));    
          txtCreateDateTill = MiUtil.trimNotNull(request.getParameter("txtCreateDateTill"));
          txtCodBscs = MiUtil.trimNotNull(request.getParameter("txtCodBscs"));
          
          new RequestServlet().buscarSolicitudes(request,response);
          param = "BUSQUEDA";        
          request.setAttribute("vieneDe","BUSQUEDA");
  
      }else{
          System.out.println("EDU: JSP inbox");
          request.setAttribute("building",String.valueOf(building));
          new RequestServlet().loadListRequest(request,response);        
          request.setAttribute("vieneDe","INBOX");          
          param = "INBOX";
        
      }
        
        
        
        int tamanioInicial = ((ArrayList)request.getAttribute("SOLICITUD_LISTA_BEAN")).size();
        
        System.out.println("JSP tamanioInicial = " + tamanioInicial);
  
%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/websales/Resource/salesweb.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/websales/Resource/displaytag.css"/>    
    <script type="text/javascript" src="<%=request.getContextPath()%>/websales/Resource/BasicOperations.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/websales/Resource/date-picker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/websales/Resource/jQuery-min.js"></script>
    <script type="text/javascript">
          
          
        function fxRequestDetail(strRequesrId){
            var formulario = document.frmdatos;
            var strBuilding = "<%=building%>";
            formulario.action = "<c:out value="${pageContext.request.contextPath}"/>/generalservlet?METHOD=SEND_REQUEST&strRequesrId="+strRequesrId+"&"+"strBuilding="+strBuilding;                                                                                                              
            formulario.submit();          
        }  
          
        function regresarBusqueda(){
           location.replace("/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE");
        }
          
          
          
          function verificarSolicitudes(){
              var building = "<%=building%>";
              var tamanioInicial = '<%=tamanioInicial%>'; 
              $.ajax({  
                    type: "POST",   
                    url: '<c:out value="${pageContext.request.contextPath}"/>/requestservlet',                                     
                    data: "METHOD=REFRESH_PAGE&building="+building+"&tamanioInicial="+tamanioInicial,
                    success: function(cadena){
                      
                        //alert("cadena = " + cadena);     
                        if(cadena=="1"){
                            document.getElementById("panelRefreshOn").style.display="inline";
                            document.getElementById("panelRefreshOff").style.display="none";                        
                        }
                    }                
          }); 
          
          }
    </script>
    
    <style>
      span.pagebanner{
        display:none;
      }
    </style>
  </head>
  <body>
    <form method="post" name="frmdatos">  
          
      <input type="hidden" name="hdnNumPagina" id="hdnNumPagina" value=""/>
      
      <input type="hidden" name="cboUbicacion" id="cboUbicacion" value="<%=cboUbicacion%>"/>
      <input type="hidden" name="cboUsuario" id="cboUsuario" value="<%=cboUsuario%>"/>      
      <input type="hidden" name="txtNroSolicitud" id="txtNroSolicitud" value="<%=txtNroSolicitud%>"/>
      <input type="hidden" name="txtNroOrden" id="txtNroOrden" value="<%=txtNroOrden%>"/>
      <input type="hidden" name="cboOrdenPagada" id="cboOrdenPagada" value="<%=cboOrdenPagada%>"/>
      <input type="hidden" name="cboEstadoSolicitud" id="cboEstadoSolicitud" value="<%=cboEstadoSolicitud%>"/>
      <input type="hidden" name="cboDivisionNegocio" id="cboDivisionNegocio" value="<%=cboDivisionNegocio%>"/>
      <input type="hidden" name="cboCategoria" id="cboCategoria" value="<%=cboCategoria%>"/>
      <input type="hidden" name="cboSubCategoria" id="cboSubCategoria" value="<%=cboSubCategoria%>"/>
      <input type="hidden" name="txtNroFactura" id="txtNroFactura" value="<%=txtNroFactura%>"/>
      <input type="hidden" name="txtCustomerName" id="txtCustomerName" value="<%=txtCustomerName%>"/>
      <input type="hidden" name="txtCreateDateFrom" id="txtCreateDateFrom" value="<%=txtCreateDateFrom%>"/>
      <input type="hidden" name="txtCreateDateTill" id="txtCreateDateTill" value="<%=txtCreateDateTill%>"/>
      <input type="hidden" name="txtCodBscs" id="txtCodBscs" value="<%=txtCodBscs%>"/>
     
      
      <br/>
      <table border="0" cellspacing="7" cellpadding="0" width="100%" align="center">
        <tr>
          <td>
                
                
				<input type="hidden" name="hdnMethod" value=""/>
				<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
					<tr>
					  <td class="SectionTitleLeftCurve" width="10">&nbsp;&nbsp;</td>
					   <td class="SectionTitle">Criterios de Busqueda</td>
					   <td class="SectionTitleRightCurve" width="8">&nbsp;&nbsp;</td>
					 </tr>
					 
					 <tr>
						<td colspan="3" class="tablaMarcotbody">
				  
							<!-- Variables java para manejar las condiciones con jstl -->
							<c:set var="busqueda" value="BUSQUEDA"/>
							<c:set var="inbox" value="INBOX"/>
						  
                           
							<c:if test="${requestScope.vieneDe == inbox}">
							 <div   id="panelRefreshOn" style="display:none;">
               
                <a href="javascript:refresh()">
										<img src="<%=request.getContextPath()%>/websales/images/RefreshOffOn.gif" width="30" height="30" border="0" alt="Refrescar"/>
									  </a>
              </div>
              
               <div   id="panelRefreshOff">
										<img src="<%=request.getContextPath()%>/websales/images/RefreshOff.gif" width="30" height="30" border="0" alt="Refrescar"/>						  
              </div>
							</c:if>
				  
							<c:if test="${requestScope.vieneDe == busqueda}">    
								<table border="0" cellspacing="0" cellpadding="0" width="100%" align="left" class="CellContent">
									<tr>
									  <td align="left" > <!--valign="middle"-->
                        <span class="CriteriaLabel">Fecha desde:</span> 
                        <span class="CellContent"><c:out value="${requestScope.fechaInicio}"/> </span> 
                        <span class="CriteriaLabel">Fecha Hasta:</span> 
                        <span class="CellContent"><c:out value="${requestScope.fechaFin}"/></span> 
									  </td>
                    <td align="right">
                        <a href="javascript:regresarBusqueda()">
                          <img src="<%=request.getContextPath()%>/websales/images/binocular.gif" border="0"/>
                        </a>
									  </td>
									</tr>									
								 </table>
								 <br/><br/>				
													  
							</c:if>  
							
							

							<display:table id="requestList" name="SOLICITUD_LISTA_BEAN" pagesize="25" class="orders" style="width: 100%">
              
								<display:column title="Nro" style="white-space: nowrap;" media="html"> 
									<c:if test="${requestScope.vieneDe == busqueda}" >
									  <%=((RequestBean)requestList).getIndice()%>                        
									</c:if>
									<c:if test="${requestScope.vieneDe == inbox}" >
									  <a href='javascript:fxRequestDetail("<%=((RequestBean) requestList).getRequestId()%>")'>
									  
										<img src="<%=request.getContextPath()%>/websales/images/viewDetail.gif" width="15" height="15" border="0" alt="Detalle de la Orden"/>
									  </a>
                            <%=((RequestBean)requestList).getIndice()%>   
                
									</c:if>                        
								</display:column>                     
								<display:column  title="Nro. Solicitud" >
									<c:if test="${requestScope.vieneDe == busqueda}" >
									  <a href="javascript:fxRequestDetail('<%=((RequestBean)requestList).getRequestId()%>')"> <%=((RequestBean)requestList).getRequestNumber()%></a>
									</c:if>
									<c:if test="${requestScope.vieneDe == inbox}" >
									  <%=((RequestBean)requestList).getRequestNumber()%>  
									</c:if>                      
								</display:column>
								<display:column property="orderNumber" title="Nro. de Orden"/>
								<display:column property="orderPay" title="Pago"/>
								<display:column property="requestDate" title="Fecha de Solicitud"/>
								<display:column property="razonSocial" title="Razon Social"/>
								<display:column property="envioCourier" title="Envio Courier"/>
								<display:column property="provieneDe" title="Proviene de"/>
								<display:column property="user" title="Usuario"/>
								<display:column property="requestStateDescription" title="Estado"/>
							</display:table>
											
							
						
						</td>
					</tr>
				  </table> 
		    
        
				
                
            </td>
        </tr>
      </table>     
				
      
    </form>
    <script>
      $(document).ready(function() {
        
        <%             
        
          if(param != "BUSQUEDA"){     
            out.println("refresarBusqueda();");        
          }        
        %>
        
        
      });
                    
      function refresarBusqueda(){
          
           window.setInterval(verificarSolicitudes,120000);
        
        }
        
       function refresh(){        
          location.reload(true);
          
       }
       
        
        function fxReturnConcatenado() {

            /*strReturnConcatenado = "strResourceType="+document.frmdatos.hdnResourceType.value+"&"+
            "strResource="+document.frmdatos.hdnResource.value+"&"+
            "strCustCode="+document.frmdatos.hdnCustCode.value+"&"+
            "strOrderid="+document.frmdatos.hdnOrderid.value+"&"+
            "strCustomerId="+document.frmdatos.hdnCustomerid.value;*/
            strReturnConcatenado="";            
            strReturnConcatenado += "cboUbicacion="+document.getElementById('cboUbicacion').value + "&";
            strReturnConcatenado += "cboUsuario="+document.getElementById('cboUsuario').value + "&";
            strReturnConcatenado += "txtNroSolicitud="+document.getElementById('txtNroSolicitud').value + "&";
            strReturnConcatenado += "txtNroOrden="+document.getElementById('txtNroOrden').value + "&";
            strReturnConcatenado += "cboOrdenPagada="+document.getElementById('cboOrdenPagada').value + "&";
            strReturnConcatenado += "cboEstadoSolicitud="+document.getElementById('cboEstadoSolicitud').value + "&";
            strReturnConcatenado += "cboDivisionNegocio="+document.getElementById('cboDivisionNegocio').value + "&";
            strReturnConcatenado += "cboCategoria="+document.getElementById('cboCategoria').value + "&";
            strReturnConcatenado += "cboSubCategoria="+document.getElementById('cboSubCategoria').value + "&";
            strReturnConcatenado += "txtNroFactura="+document.getElementById('txtNroFactura').value + "&";
            strReturnConcatenado += "txtCustomerName="+document.getElementById('txtCustomerName').value + "&";            
            strReturnConcatenado += "txtCreateDateFrom="+document.getElementById('txtCreateDateFrom').value + "&";
            strReturnConcatenado += "txtCreateDateTill="+document.getElementById('txtCreateDateTill').value + "&";
            strReturnConcatenado += "txtCodBscs="+document.getElementById('txtCodBscs').value + "&";
            strReturnConcatenado += "vieneDe=<%=param%>";            
              
            return strReturnConcatenado;
        }
    
        function paginar(param) {
            try {
              cadena = "/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_LIST?"+fxReturnConcatenado()+"&d-8015999-p="+param;
            } catch(exception) {
              document.frmdatos.hdnNumPagina.value = param;
              cadena = "/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_LIST?"+fxReturnConcatenado()+"&d-8015999-p="+param;
            }
            document.location.href = cadena;
        }
    
   function fxPrimeraPagina(param) {
    try {
      cadena = "/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_LIST?"+fxReturnConcatenado()+"&d-8015999-p="+param;
    } catch(exception) {
	    document.frmdatos.hdnNumPagina.value = param;
      cadena = "/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_LIST?"+fxReturnConcatenado()+"&d-8015999-p="+param;
	  }
		document.location.href = cadena;
  }
  
  function fxAnteriorPagina(param) {
    try {
     // parent.bottomFrame.document.frmsearch.hdnNumPagina.value = param - 1;
      cadena = "/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_LIST?"+fxReturnConcatenado()+"&d-8015999-p="+(param - 1);
    } catch(exception) {
	    document.frmdatos.hdnNumPagina.value = param - 1;
      cadena = "/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_LIST?"+fxReturnConcatenado()+"&d-8015999-p="+(param - 1);
	  }
		document.location.href = cadena;
  }
  
  function fxSiguientePagina(param) {
    try {
      //parent.bottomFrame.document.frmsearch.hdnNumPagina.value = param + 1;
      cadena = "/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_LIST?"+fxReturnConcatenado()+"&d-8015999-p="+(param + 1);
    } catch(exception) {
	    document.frmdatos.hdnNumPagina.value = param + 1;
      cadena = "/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_LIST?"+fxReturnConcatenado()+"&d-8015999-p="+(param + 1);
	  }
		document.location.href = cadena;
  }
  
  function fxUltimaPagina(param) {
    
    try {
      //document.frmdatos.hdnNumPagina.value = param;
      cadena = "/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_LIST?"+fxReturnConcatenado()+"&d-8015999-p="+param;
    } catch(exception) {
	    //document.frmdatos.hdnNumPagina.value = param;
      cadena = "/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_LIST?"+fxReturnConcatenado()+"&d-8015999-p="+param;
	  }    
		document.location.href = cadena;
  }

	function fxObtenerParametrosDisplayTag(url) {
		var indice = url.indexOf('d-');
		if (indice != -1) {
			url = url.substring(indice);
			indice2 = url.indexOf('&');
			if(indice2 > 0) {
				paramDisplayTag = url.substr(0,indice2);
			}
			else {
				paramDisplayTag = url;
			}
		}
		return paramDisplayTag;
	}

	function fxGetPageNumber(url) {
		var indice = url.indexOf("d-");
		if (indice != -1) {
			url = url.substring(indice);
			indice2 = url.indexOf("&");
			if(indice2 > 0) {
				paramDisplayTag = url.substr(0,indice2);
			} else {
				paramDisplayTag = url;
			}
			indice3 = url.indexOf("=");
			if(indice3 > 0) {
				pageNumber = paramDisplayTag.substr(indice3+1);
			} else {
				pageNumber = 0;
			}
		}
		return "&hdnNumPagina="+pageNumber;
	}
  
  
   function doAjaxCall(url)
		 {
			  var get_request = false
			     if (window.XMLHttpRequest) // if Mozilla, Safari etc
			      get_request = new XMLHttpRequest();
			     else if (window.ActiveXObject) { // if IE
			      try {
			       get_request = new ActiveXObject("Msxml2.XMLHTTP")
			      } catch (e) {
			       try {
			        get_request = new ActiveXObject("Microsoft.XMLHTTP")
			       } catch (e) {}
			      }
			     } else
			      return false;
				
				get_request.onreadystatechange=function() {
			    if(get_request.readyState == 4){
			     	if(get_request.responseText!=null &&  get_request.responseText!=''){
					 document.getElementById("resultAjax").innerHTML=get_request.responseText
			     	}
			     }
				}
				 
				 get_request.open('GET', url, false)
	}
   
  </script>
      
  </body>
</html>
<%}catch(SessionException se) {
    System.out.println("[JP_OL_REQUEST_LIST_ShowPage][SessionException] : " + se.getClass() + " - " + se.getMessage());
    se.printStackTrace();
  }catch(Exception e) {
    String strMessageExceptionGeneralStart = "";
    strMessageExceptionGeneralStart = MiUtil.getMessageClean(e.getMessage());
    System.out.println("[JP_OL_REQUEST_LIST_ShowPage][Exception] : " + e.getClass() + " - " + e.getMessage());
    out.println("<script>alert('"+strMessageExceptionGeneralStart+"');</script>");   
  }finally{
    System.out.println("[Eliminando Lista JP_OL_REQUEST_LIST_ShowPage] : ");
    List listRequestIni = (List)request.getAttribute("SOLICITUD_LISTA_BEAN");
    request.setAttribute("SOLICITUD_LISTA_BEAN",null);
    listRequestIni = null;
    out.flush();
  }
%>