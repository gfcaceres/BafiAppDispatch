<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ page import="pe.com.nextel.util.Constante" %>
<%@ page import="oracle.portal.provider.v2.render.PortletRenderRequest" %>
<%@ page import="oracle.portal.provider.v2.http.HttpCommonConstants" %>
<%@ page import="oracle.portal.provider.v2.ProviderUser" %>
<%@page import="oracle.portal.provider.v2.ProviderSession" %>
<%@page import="oracle.portal.provider.v2.render.PortletRendererUtil" %>
<%@page import="oracle.portal.provider.v2.render.http.HttpPortletRendererUtil" %>
<%@page import="pe.com.nextel.bean.PortalSessionBean" %>
<%@page import="pe.com.nextel.exception.SessionException" %>
<%@page import="pe.com.nextel.service.SessionService"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator"%>
<%@page import="pe.com.nextel.bean.HeaderRequestBean"%>
<%@ page import="pe.com.nextel.util.MiUtil" %>
<%@ page import="pe.com.nextel.service.GeneralService"%>
<%@ page import="pe.com.nextel.servlet.GeneralServlet"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ taglib uri="http://displaytag.sf.net/" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
  
  try{

 
  //String strSessionDetailRequestId = "655718738877632405544903018086691539998102659";
  String strSessionDetailRequestId = "";
  try{
    PortletRenderRequest pReq = (PortletRenderRequest) request.getAttribute(HttpCommonConstants.PORTLET_RENDER_REQUEST);
    ProviderUser objetoUsuario1 = pReq.getUser();
    strSessionDetailRequestId=objetoUsuario1.getPortalSessionId();
    System.out.println("Sesión capturada  JP_DETAIL_REQUEST_ShowPage : " + objetoUsuario1.getName() + " - " + strSessionDetailRequestId );
    
    
    
  }catch(Exception e){
    System.out.println("Portler Not Found : " + e.getClass() + " - " + e.getMessage() );
    out.println("Portlet JP_DETAIL_REQUEST_ShowPage Not Found");
    return;
  }
  
  
  System.out.println("Sesión capturada después del request : " + strSessionDetailRequestId );
	PortalSessionBean portalSessionBean3 = (PortalSessionBean)SessionService.getUserSession(strSessionDetailRequestId);
	if(portalSessionBean3==null) {
    System.out.println("No se encontró la sesión de Java ->" + strSessionDetailRequestId);
		throw new SessionException("La sesión finalizó");
	} 
  
  new GeneralServlet().loadRequestDetail(request,response);
   
  GeneralService objGeneralService = new GeneralService();
	HashMap hshDataMap = new HashMap();
	ArrayList valoresCombo = new ArrayList();
	String strMessage = null;
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.MONTH, -1);

  ArrayList objArrayActions   = (ArrayList)request.getAttribute("objArrayActions");
  //if(objArrayActions != null)
  //System.out.println("Tamaño de lista :"+objArrayActions.size());
  ArrayList objArrayHeader  = (ArrayList)request.getAttribute(Constante.OBJ_ARRAYHEADER);
  HeaderRequestBean headReq = new HeaderRequestBean();
  if(objArrayHeader != null){
  //System.out.println("Tamaño de lista De Header:"+objArrayHeader.size());
  //System.out.println("Tamaño de lista get:"+objArrayHeader.get(0));
      for(int i=0;i<objArrayHeader.size();i++){
         headReq =(HeaderRequestBean)objArrayHeader.get(i);
      }
  }
  List listaEquipos = (ArrayList)request.getAttribute("arrayEquipment");

%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="websales/Resource/salesweb.css"/>
    <link rel="stylesheet" href="websales/Resource/displaytag.css"/>
    <script type="text/javascript" src="websales/Resource/date-picker.js"></script>
    <script type="text/javascript" src="websales/Resource/jQuery-min.js"></script>
    <style>
      span.pagebanner{
        display:none;
      }
    </style>
    
    <script type="text/javascript">
    
     function ValidNum(f)  {
          if (isNaN(f.txtSims.value)) {
            alert("Error:\nEste campo debe tener sólo números.");
            f.txtSims.focus();
             return (false);
           }
      }

       function generateDoc(){
    
        var formulario = document.frmdatos;
        var  strLogin='MMILLA';
        var  strRequestId ='<%=headReq.getWn_requestolid()%>';
        alert('Usuario  Login : '+strLogin);
        alert('Request ID : '+strRequestId);
          $.ajax({  
                type: "POST",  
                url: "requestservlet",  
                data: "METHOD=GENERATE_DOC&strLogin="+strLogin+"&strRequestId="+strRequestId,                  
                success: function(cadena){  
                alert(cadena);         
            }                
          }); 
          
          loadAccesory();
          loadEquipment();

    }

   function loadAccesory(){
   
   }
   
   function loadEquipment(){
   
   }


    

    function fxRequestXML(servlet,funcion, params){
        var url = "<%=request.getContextPath()%>/"+servlet+"?metodo=" + funcion + "&"+params;
        var msxml = new ActiveXObject("msxml2.XMLHTTP");
        msxml.Open("GET", url, false);
        msxml.Send("");
        var ret = msxml.responseText;		
        if(ret.indexOf("OK")!=0){      
          return null;
        }      
      return ret.substring(2,ret.length);
    } 
    
  
   function validarSIM(){
    
      var numeroSim = document.getElementById('txtSims').value; 
      var radio = document.frmdatos.item_imei_radio;
      var indice = 0;
      var i=0;
      
      //Recorremos todos los redios
      for(i=0; i<radio.length; i++){          
					if(radio[i].checked) {            
            indice = radio[i].value;
						break;						
					} 
			}
      
      document.getElementById("sim_num_"+indice).value = numeroSim;  
      
       
   }
    
   function validarIMEI(){
        var imei =  document.getElementById("txtImeis").value;         
        var radio = document.frmdatos.item_imei_radio;
        var indice = 0;
        var i=0;
        
        var posicion = 0;
        var almacen = "";
        var subinventario = "";
        
        for(i=0; i<radio.length; i++){          
					if(radio[i].checked) {            
            indice = radio[i].value;
						break;						
					} 
				}
        document.getElementById("imei_num_"+indice).value=imei;
                        
         $.ajax({  
                type: "POST",   
                url: "<c:out value="${pageContext.request.contextPath}"/>/requestservlet",
                data: "METHOD=VALIDAR_IMEI&COD_IMEI="+imei,                  
                success: function(cadena){  
                  if(cadena.length > 1){     
                    posicion = cadena.indexOf('|');
                    almacen = cadena.slice(0,posicion);
                    subinventario = cadena.substr(posicion+1);
                  }
                  
                  alert("almacen:"+almacen);
                
                document.getElementById("almacen_desc_"+indice).value = almacen;
                document.getElementById("subintentario_desc_"+indice).value = subinventario;                 
                                       
                }                
          }); 
          
      }
      
      
      function grabarDatos(){
          var formulario = document.frmdatos;
          formulario.action = "<c:out value="${pageContext.request.contextPath}"/>/requestservlet?METHOD=SAVE_REQUEST";              
          formulario.submit();            
          
      }
      
 </script>
</head>

<body>
  <form action="generalServlet" method="post" name="frmdatos">
     <table border="0"  cellspacing="0" cellpadding="0" width="100%" align="center">
        <tr>
            <td class="SectionTitleLeftCurve" width="10">&nbsp;&nbsp;</td>
            <td class="SectionTitle">&nbsp;&nbsp;Intentario - Atenci&oacute;n de Solicitudes a Almac&eacute;n</td>
       </tr>
    </table>

    <table border="0" cellspacing="1" cellpadding="1" align="center" class="RegionBorder" width="100%">
    <tr>
      <td class="CellLabel" width="122" >Nro Solicitud</td>
      <td class="CellContent" width="338">
      <input type="text" id="txtNumRequest" name="txtNumRequest" size="30"  readonly="true" maxlength="30"/>      </td>
      
      <input type="hidden" id="txtRequestId" name="txtRequestId" value='<%=headReq.getWn_requestolid()%>' />
      
      <td class="CellLabel" >Fecha Solicitud</td>
      <td class="CellContent" >
          <input type="text" id="txtReqDate" readonly="true" name="txtReqDate" size="30"
                  maxlength="15"/>      </td>
      <td class="CellLabel" >Creado por</td>
      <td width="299" class="CellContent">
      <input type="text" id="txtCreated"  readonly="true" name="txtCreated" size="40"
                  maxlength="15"/>
      &nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    <tr >
      <td class="CellLabel" width="122">Razón Social</td>
      <td class="CellContent" width="338"><input type="text" name="txtRazonSocial" id ="txtRazonSocial" size="55" readonly="true" maxlength="30"/></td>
      <td class="CellLabel" width="115">Nro de Factura</td>
      <td class="CellContent" width="281">
      <input type="text" id="txtFactura" readonly="true" name="txtFactura" size="30"
                  maxlength="15"/>      </td>
      <td class="CellLabel" width="127">R.U.C.</td>
      <td class="CellContent">
      <input type="text" id="txtRUC" readonly="true" name="txtRUC" size="40"
                  maxlength="15"/>
      &nbsp;&nbsp;&nbsp;&nbsp;</td>
    </tr>
     <tr >
      <td class="CellLabel" width="122">Dirección</td>
      <td colspan="3" class="CellContent">
       <input type="text" name="txtDir" id ="txtDir" readonly="true" size="100"  maxlength="30"/>      </td>
       <td class="CellLabel" width="127">Modificado Por</td>
      <td class="CellContent" colspan="3">
        <input type="text"  readonly="true" id="txtMod" name="txtMod" size="40" maxlength="15"/>       </td>
    </tr>
    <tr>
    	<td class="CellLabel">
				Detalle de la Orden	  </td>
			<td class="CellContent" colspan="5">
				<textarea name="txtDetalleOrden" id="txtDetalleOrden" rows="5"  readonly="true" cols="80"></textarea>			</td>
    </tr>
    <tr >
      <td class="CellLabel" width="122">Nro de Orden</td>
      <td class="CellContent" width="338">
      <input type="text" name="txtNumOrden" id="txtNumOrden" readonly="true" size="40"  maxlength="30"/></td>
      <td class="CellLabel" width="115">Envio Courier</td>
      <td class="CellContent" colspan="3">
          <input type="text" id="txtCourier" readonly="true" name="txtCourier" size="40"
                  maxlength="15"/>      </td>
      </tr>
      <tr >
      <td class="CellLabel" width="122">División de Neg.</td>
      <td class="CellContent" width="338">
      <input type="text" name="txtDivNego" id="txtDivNego" readonly="true" size="40"  maxlength="30"/>      </td>
      <td class="CellLabel" width="115">Categoria</td>
      <td class="CellContent"  width="281">
      <input type="text" readonly="true" name="txtCategoria" id="txtCategoria" size="40"
                  maxlength="15"/>      </td>
      <td class="CellLabel" width="127">Sub-Categoria</td>
      <td class="CellContent">
      <input type="text"  readonly="true" name="txtSubCategoria" id="txtSubCategoria" size="40"
                  maxlength="15"/>
      &nbsp;&nbsp;&nbsp;&nbsp;</td>
    </tr>
    </table>
    <table border="0" cellspacing="1" cellpadding="1" align="center" class="RegionBorder" width="100%">
    <tr>
    
      <td width="190" class="CellLabel">Estado de la Solicitud</td>
      <td width="132" class="CellContent"> 
      <input type="text" id="txtEstadoSol" readonly="true" name="txtEstadoSol" size="15"
                  maxlength="15"/>      
                  
      <input type="hidden" id="txtCodigoEstado" value="<%=headReq.getWn_reqolstatusid()%>"/>
      
      </td>
      <td class="CellLabel" width="135">Fecha Programada de Entrega</td>
      <td class="CellContent"  width="191">
      <input type="text" name="txtCreateDateFrom" id="txtCreateDateFrom" size="15" maxlength="10" value="" onBlur="this.value=fxTrim(this.value);">
      <a href="javascript:show_calendar('frmdatos.txtCreateDateFrom',null,null,'DD/MM/YYYY');" onMouseOver="window.status='Fecha desde';return true;" onMouseOut="window.status='';return true;">
                <img src="../../websales/images/show-calendar.gif" width="24" height="22" border="0">
      <td class="CellLabel" width="171">Fecha Real de Entrega</td>
      <td class="CellContent"  width="196">
      <input type="text" name="txtCreateDateTill" id="txtCreateDateTill" size="15" maxlength="10" value="" onBlur="this.value=fxTrim(this.value);">
      <a href="javascript:show_calendar('frmdatos.txtCreateDateTill',null,null,'DD/MM/YYYY');" onMouseOver="window.status='Fecha desde';return true;" onMouseOut="window.status='';return true;"><img src="../../websales/images/show-calendar.gif" width="24" height="22" border="0"></td></a>
      <td width="127" class="CellLabel" >Entregado a : </td>
      <td width="133" class="CellContent">
      <select name="cmbPersonalDelivery" id="cmbPersonalDelivery" style="width: 70%">
        <c:forEach var="listaPersonal" items="${requestSearch.listaPersonal}">                           
        <option value="<c:out value="${listaPersonal.valor}"/> "><c:out value="${listaPersonal.descripcion}"/></option>                    
        </c:forEach>  
      </select>
    </td>
    </tr>
  </table>  

  <table border="0" cellspacing="1" cellpadding="1" align="center" class="RegionBorder" width="100%">
      <tr>
    		<td width="94" class="CellLabel">
				Observaciones	  </td>
			<td width="499" colspan="7" class="CellContent"><textarea name="txtDescription2" rows="5" cols="100"></textarea></td>
     </tr>
   </table>  
      </br>
  <table >
   <tr >
  </table>
       </br>
      <table border="0" cellpadding="0" cellspacing="0" class="RegionBorder">
         <tr class="PortletHeaderColor">
            <td class="SubSectionTitleLeftCurve" valign="top" align="left" width="16"></td>
            <td class="SubSectionTitle" align="LEFT" valign="top">Accesorios/Repuestos</td>
            <td class="SubSectionTitleRightCurve" valign="top" align="right" width="12"></td>
         </tr>
      </table>
      <display:table name="arrayAccesory" id="arrayAccesory"  class="orders" style="width: 100%">
                    <display:column title="Nro" style="white-space: nowrap;" media="html">                   
                      <label name ="num"><b><%=((HashMap)arrayAccesory).get("wn_contador")%></b></label>
                      </a>
                    </display:column>
                    <display:column property="wv_itemtypeid" title="Tip.Articulo"/>
                    <display:column property="wv_name" title="Producto"/>
                    <display:column property="wv_modalitysell" title="Modalidad"/>
                    <display:column property="wv_productstatus" title="Condición"/>
                    <display:column property="wn_quantity" title="Cantidad"/>
                    <display:column property="wn_organizationid" title="Almacén"/>
                    <display:column property="wv_subinventoryid" title="SubInventario"/>
                    <display:column property="wv_reason_name" title="Motivo"/>
                    <display:column property="wv_transnnumber" title="Doc.Generado"/>
        </display:table>
        </br>
          <table border="0" cellpadding="0" cellspacing="0" class="RegionBorder">
         <tr class="PortletHeaderColor">
            <td class="SubSectionTitleLeftCurve" valign="top" align="left" width="16"></td>
            <td class="SubSectionTitle" align="LEFT" valign="top">Equipos</td>
            <td class="SubSectionTitleRightCurve" valign="top" align="right" width="12"></td>
         </tr>
      </table>
      
            <display:table name="arrayEquipment" id="arrayEquipment" class="orders" style="width: 100%">
                    <display:column title="Nro" style="white-space: nowrap;" media="html">         
                       <!-- <input type="radio" name="idRadioEquipment" value="<%=((HashMap)arrayEquipment).get("wn_contador")%>" onclick="getResults('<%=((HashMap)arrayEquipment).get("wn_quantity")%>','<%=((HashMap)arrayEquipment).get("wn_contador")%>')"> -->             
                        <label name ="num"><b><%=((HashMap)arrayEquipment).get("wn_contador")%></b></label>
                      </a>
                    </display:column>
                    <display:column property="wn_productid" title="Solución de Neg."/>
                    <display:column property="wv_itemtypeid" title="Tip.Articulo"/>
                    <display:column property="wv_segment1" title="Producto"/>
                    <display:column property="wv_returneqimeisim" title="SIM"/>
                    <display:column property="wv_modalitysell" title="Modalidad"/>
                    <display:column property="wv_productstatus" title="Condición"/>
                    <display:column property="wn_quantity" title="Cantidad"/>
                    <display:column property="wv_accessory" title="Con Accesorios?"/>
                    <display:column property="wv_reason_name" title="Motivo"/>
                    <display:column property="wv_transnnumber" title="Doc.Generado"/>
        </display:table>
    </table>
  </table>
  <table width="75%">
  <tr>      
    <td> 
      <div id="divServicios" >      </div>                   
      <div id="divImeisItems">
        <table class="RegionBorder" width="100%" align="right">
          <tr>
            <td width="18%" class="CellLabel">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IMEI</td>
          <td width="17%" class="CellContent">
            <input type="text" name="txtImeis" id="txtImeis" maxlength="15"   onblur="validarIMEI()" tabindex="1">                  </td>
            <td width="18%" class="CellLabel">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SIM</td>
          <td width="54%" class="CellContent">
            <input type="text" name="txtSims" value="" maxlength="20"  onblur="validarSIM()" tabindex="2">
            <br>                  </td>
          </tr>
          <tr>
            <td colspan="4" class="CellContent">
              <div id="grid_region" style="overflow: auto; width:100%; height:200px;">
                <table name="table_imeis" width="100%" id="table_imeis" border="1" cellpadding="2" cellspacing="0">
                  <tr class="CellLabel" >
                    <td align="center" width="6%" >N°</td>
                     <td align="center" width="18%" >IMEI</td>
                     <td align="center" width="18%" >SIM</td>
                     <td align="center" width="18%" >Almacén</td>
                     <td align="center" width="26%" >SubInventario</td>  
                   </tr>
                 </table>
                 
                 <table border ='1'>
                 
                 <%
                    String tabla = "";
                    tabla=tabla+"<tbody>";
                    
                    for(int i= 0; i< listaEquipos.size(); i++){
                      String cantidad =  ((HashMap)listaEquipos.get(i)).get("wn_quantity").toString();
                      String contador =  ((HashMap)listaEquipos.get(i)).get("wn_contador").toString();
                      String cadena = "";
                      String idEquipo =  ((HashMap)listaEquipos.get(i)).get("wv_reqolitemid").toString();
               
                       for(int j = 0; j < Integer.parseInt(cantidad); j++){
                       cadena = contador +" - "+ (j+1);
                        tabla = tabla + "<tr>";  
                        tabla=tabla+"<td>"; 
                        
                        
                        tabla =tabla+" <input type='hidden' id='txtIdEquipment"+j+"' name='txtIdEquipment"+j+"' value='"+idEquipo+"' />";
                        if(j==0){
                          tabla =tabla+"<input type='radio' value='"+j+"' name='item_imei_radio' checked>";      
                        }else{
                          tabla =tabla+"<input type='radio' value='"+j+"' name='item_imei_radio' >";
                        }                       
                        
                        tabla =tabla+"<input type='text'  name='item_imei_num_"+j+"' value='"+cadena+"' size='2' readonly>";
                        tabla=tabla+"</td >"; 
                        tabla=tabla+"<td >"; 
                        tabla =tabla+"<input type='text'  name='imei_num_"+j+"' size='30' readonly>";
                        tabla=tabla+"</td >";
                        tabla=tabla+"<td >"; 
                        tabla =tabla+"<input type='text'  name='sim_num_"+j+"' size='30'  readonly>";
                        tabla=tabla+"</td >"; 
                        tabla=tabla+"<td >"; 
                        tabla =tabla+"<input type='text'  name='almacen_desc_"+j+"' size='40'  readonly>";
                        tabla=tabla+"</td >"; 
                        tabla=tabla+"<td >";  
                        tabla =tabla+"<input type='text'  name='subintentario_desc_"+j+"' size='50' readonly>";
                        tabla=tabla+"</td >"; 
                        tabla = tabla + "</tr>";      
                       }                  
                   }                        
                 tabla=tabla+"</tbody>";              
                 out.print(tabla);                
                 %>
                 </table>  
               </div>          
               </td>
            </tr>
          </table>
      </div></td>      
    </tr>
    </table>

    <table width="712" height="51" align="left" >

    <tr>
    <td class="CellLabel" width="60">Acciones </td>
    <td class="CellContent" width="102">
       <select name="cmbAcciones" style="width:150">      
             <c:forEach var="lista" items="${requestScope.OL_ACTIONS}">
                       <option value="<c:out value="${lista.valor}"/> ">
                       <c:out value="${lista.descripcion}"/>
                       </option>
             </c:forEach>  
      </select></td>
      <td class="CellLabel" width="72">Motivo </td>
      <td class="CellContent" width="134">
            <select name="cmbMovito" style="width:150">
             <c:forEach var="lista" items="${requestScope.OL_REASONS}">
                          <option value="<c:out value="${lista.valor}"/> ">
                              <c:out value="${lista.descripcion}"/>
                            </option>
              </c:forEach>  
      </select></td>   
        
    <td width="86">
    <input name="btnSaveRequest" type="button" value="Grabar" onClick="grabarDatos()">
    </td>
    <td width="99">
<input name="btnGenDoc" type="button" value="Generar Doc." onClick="generateDoc()">
    </td>

    </tr>

    </table>
  </form>
  </body>
</html>

<script>
 onLoadForm();
  function onLoadForm(){
    var requestId = '<%=headReq.getWn_requestolid()%>'; 
    var txtRUC = '<%=headReq.getWv_customertaxnumber()%>';
    var txtModifiby = '<%=headReq.getWv_modifyby()%>';
  var txtNumRequest = '<%=headReq.getWv_requestnumber()%>';
  var txtReqDate = '<%=headReq.getWv_createddate()%>'; 
  var txtCreated = '<%=headReq.getWv_createdby()%>';
  var txtRazonSocial = '<%=headReq.getWv_customername()%>';
      var txtFactura = 'Falta DOc!';
  var txtDir = '<%=headReq.getWv_customeraddress1()%>';
  var txtDetalleOrden = '<%=headReq.getWv_description()%>';
  var txtNumOrden = '<%=headReq.getWn_orderid()%>';
  var txtCourier = '<%=headReq.getWv_courier()%>';
  var txtDivNego = '<%=headReq.getWv_name()%>';
  var txtCategoria = '<%=headReq.getWv_type()%>';
  var txtSubCategoria = '<%=headReq.getWv_specification()%>';
  var txtEstadoSol = '<%=headReq.getWv_reqolstatus()%>';
  var codEstado = '<%=headReq.getWn_reqolstatusid()%>';

  document.getElementById('txtNumRequest').value=txtNumRequest;
  document.getElementById('txtReqDate').value=txtReqDate;
  document.getElementById('txtCreated').value=txtCreated;
  document.getElementById('txtRazonSocial').value=txtRazonSocial;
  document.getElementById('txtFactura').value=txtFactura;
  document.getElementById('txtRUC').value=txtRUC;
  document.getElementById('txtDir').value=txtDir;
      document.getElementById('txtMod').value=txtModifiby;
  document.getElementById('txtDetalleOrden').value=txtDetalleOrden;
  document.getElementById('txtNumOrden').value=txtNumOrden;
  document.getElementById('txtCourier').value=txtCourier;
  document.getElementById('txtDivNego').value=txtDivNego;
  document.getElementById('txtCategoria').value=txtCategoria;
  document.getElementById('txtSubCategoria').value=txtSubCategoria;
  document.getElementById('txtEstadoSol').value=txtEstadoSol;
  //document.getElementById('txtCodigoEstado').value=codEstado;
  
 
}
</script>
<%}catch(SessionException se) {
    System.out.println("[JP_DETAIL_REQUEST_ShowPage][SessionException] : " + se.getClass() + " - " + se.getMessage());
    se.printStackTrace();
  }catch(Exception e) {
    String strMessageExceptionGeneralStart = "";
    strMessageExceptionGeneralStart = MiUtil.getMessageClean(e.getMessage());
    System.out.println("[JP_DETAIL_REQUEST_ShowPage][Exception] : " + e.getClass() + " - " + e.getMessage());
    out.println("<script>alert('"+strMessageExceptionGeneralStart+"');</script>");   
  }
%>  