<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="pe.com.nextel.util.Constante" %>
<%@ page import="oracle.portal.provider.v2.render.PortletRenderRequest" %>
<%@ page import="oracle.portal.provider.v2.http.HttpCommonConstants" %>
<%@ page import="oracle.portal.provider.v2.ProviderUser" %>
<%@ page import="oracle.portal.provider.v2.ProviderSession" %>
<%@ page import="oracle.portal.provider.v2.render.PortletRendererUtil" %>
<%@ page import="oracle.portal.provider.v2.render.http.HttpPortletRendererUtil" %>
<%@ page import="pe.com.nextel.bean.PortalSessionBean" %>
<%@ page import="pe.com.nextel.exception.SessionException" %>
<%@ page import="pe.com.nextel.service.SessionService"%>
<%@ page import="pe.com.nextel.service.BafiOutdoorService"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator"%>
<%@ page import="pe.com.nextel.bean.HeaderRequestBean"%>
<%@ page import="pe.com.nextel.bean.EquipmentBean"%>
<%@ page import="pe.com.nextel.bean.DeviceBean"%>
<%@ page import="pe.com.nextel.bean.estadoinstalacion.MotivoBean"%>
<%@ page import="pe.com.nextel.bean.estadoinstalacion.MotivosBean"%>
<%@ page import="pe.com.nextel.bean.InstalacionBean"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="pe.com.nextel.util.MiUtil"%>
<%@ page import="pe.com.nextel.service.GeneralService"%>
<%@ page import="pe.com.nextel.service.RequestService"%>
<%@ page import="pe.com.nextel.servlet.GeneralServlet"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ taglib uri="http://displaytag.sf.net/" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%
  
  RequestService requestService = new RequestService();
  BafiOutdoorService bafiOutdoorService = new BafiOutdoorService();

  try{

 
  String strSessionDetailRequestId = "";
  String strRequesrId = ""; 
  
   boolean bFlag = false;
  //CARGANDO OBJ_ARRAYHEADER, OBJ_ARRAYACCESORY, OBJ_ARRAYEQUIPMENT, OBJ_ARRAYDEVICE
       
  try{
    PortletRenderRequest pReq = (PortletRenderRequest) request.getAttribute(HttpCommonConstants.PORTLET_RENDER_REQUEST);
    ProviderUser objetoUsuario1 = pReq.getUser();
    strSessionDetailRequestId=objetoUsuario1.getPortalSessionId();
    System.out.println("Sesión capturada  JP_DETAIL_REQUEST_ShowPage : " + objetoUsuario1.getName() + " - " + strSessionDetailRequestId );
    
    strRequesrId = MiUtil.trimNotNull(request.getParameter("strRequesrId"));
    
    new GeneralServlet().loadRequestDetail(request,response);
    
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
  
  String strLogin = portalSessionBean3.getLogin(); 
  String strLoginAux = MiUtil.repleaceCharacterSpecialByHTML(portalSessionBean3.getLogin()).toString().replaceAll("''", "").replaceAll("&", "#");
  strLogin = strLoginAux;
  System.out.println("la sesión de Java strLoginAux; ->" + strLoginAux);
  %>

  <%
  GeneralService objGeneralService = new GeneralService();
	HashMap hshDataMap = new HashMap();
	ArrayList valoresCombo = new ArrayList();
	String strMessage = null;
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.MONTH, -1);

  ArrayList objArrayActions   = (ArrayList)request.getAttribute("objArrayActions");
  if(objArrayActions != null){
  System.out.println("Tamaño de lista :"+objArrayActions.size());
  }
  ArrayList objArrayHeader  = (ArrayList)request.getAttribute(Constante.OBJ_ARRAYHEADER);
  
  HeaderRequestBean headReq = new HeaderRequestBean();
  if(objArrayHeader != null){
  //int intSubCategoriaId = 0;
  System.out.println("Tamaño de lista De Header:"+objArrayHeader.size());
  System.out.println("Tamaño de lista De get0:"+objArrayHeader.size());
  //System.out.println("Tamaño de lista get0:"+objArrayHeader.get(0));
      for(int i=0;i<objArrayHeader.size();i++){
         headReq =(HeaderRequestBean)objArrayHeader.get(i);
      }
      //intSubCategoriaId = headReq.getWn_specificationId();
  }
  List listaEquipos = (ArrayList)request.getAttribute(Constante.OBJ_ARRAYEQUIPMENT);
  List listaDevice = (ArrayList)request.getAttribute(Constante.OBJ_ARRAYDEVICE);
  String strReqolitemid = (String) request.getAttribute("strReqolitemid");
  
  System.out.println("Tamaño de lista listaEquipos.size(): "+listaEquipos.size());
  System.out.println("Tamaño de lista listaDevice.size(): "+listaDevice.size());
  
  //---BORRAR--
  for(int i=0; i<listaDevice.size(); i++){
      System.out.println("Wv_reqalmacen = "+((DeviceBean)listaDevice.get(i)).getWv_reqalmacen());
      System.out.println("Wv_reqimeisim = "+((DeviceBean)listaDevice.get(i)).getWv_reqimeisim());
      System.out.println("Wv_reqolimei = "+((DeviceBean)listaDevice.get(i)).getWv_reqolimei());
      System.out.println("Wv_reqolitemdeviceid = "+((DeviceBean)listaDevice.get(i)).getWv_reqolitemdeviceid());
      System.out.println("Wv_reqolitemid_device = "+((DeviceBean)listaDevice.get(i)).getWv_reqolitemid_device());
      System.out.println("Wv_reqorganizationid = "+((DeviceBean)listaDevice.get(i)).getWv_reqorganizationid());
      System.out.println("Wv_reqsubinventario = "+((DeviceBean)listaDevice.get(i)).getWv_reqsubinventario());
      System.out.println("----------");
      
  }
  
  
  boolean flagAgendamiento = bafiOutdoorService.validarSolicitudOlBafi(Long.parseLong(strRequesrId));
  System.out.println("[JP_DETAIL_REQUEST_ShowPage] Flag de agendamiento : "+String.valueOf(flagAgendamiento));
  InstalacionBean instalacionBean = new InstalacionBean();
  boolean flagInstalacionEdit = false;
  
  if(flagAgendamiento){
      List<MotivoBean> listaMotivo = bafiOutdoorService.obtenerListaMotivos();
      MotivosBean motivosBean = new MotivosBean();
      motivosBean.setMotivos(listaMotivo);
      Gson gson = new Gson();
      String listaMotivoJson = gson.toJson(motivosBean);
      System.out.println("[JP_DETAIL_REQUEST_ShowPage] Cantidad motivos de instalacion: "+listaMotivo.size());      
      request.setAttribute("baseActual", listaMotivoJson);      
      Long ordenId = Long.parseLong(headReq.getWn_orderid());
      
      //Validar si se edita Instalacion
      flagInstalacionEdit = bafiOutdoorService.validarInstalacionEdit(ordenId);
      instalacionBean     = bafiOutdoorService.obtenerInstalacionPorOrdenId(ordenId);
  }
  request.setAttribute("flagInstalacionEdit",String.valueOf(flagInstalacionEdit));
  request.setAttribute("flagAgendamiento",String.valueOf(flagAgendamiento));
%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset="utf-8"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/websales/Resource/salesweb.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/websales/Resource/displaytag.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/websales/Resource/date-picker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/websales/Resource/jQuery-min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/websales/Resource/library.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/websales/Resource/json2.js"></script>
    <style>
      span.pagebanner{
        display:none;
      }
      
      .ocultarTextbox{
        display:none;
      }
      
      .etiquetaTextBox{
				text-transform: uppercase; 
			}
			
			#lblDetalleOrden{
				height:40px;
				overflow: auto;
				border:0px;
				/*border-style: solid;*/
        text-transform: uppercase; 
				width:610px;
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

    
   function obtenerSIM(){
         
         var numeroSim = document.getElementById('txtSims').value; 
         var strRequesrId = '<%=strRequesrId%>';
         var imei = "";
         var radio = document.frmdatos.item_imei_radio;      
         var indice = 0;
         var i=0;
         
         if(trim(numeroSim) != ""){
            
            //Recorremos todos los redios y recuperamos el indice del radio checkeado
            for(i=0; i<radio.length; i++){          
                if(radio[i].checked) {            
                  indice = radio[i].value;
                  break;						
                } 
            }
            
            /*document.getElementById("sim_num_"+indice).value = trim(numeroSim);                     
            document.getElementById('txtSims').value = "";
            */
                        
                       
            imei = document.getElementById("imei_num_" + indice).value;  
            
            if(trim(imei) != ""){
                document.getElementById('imgProcesando').style.display = 'inline'; 
                
                $.ajax({
                    cache:false,
                    type: "POST",   
                    url: '<c:out value="${pageContext.request.contextPath}"/>/requestservlet',
                    data: "METHOD=OBTENER_SIM&COD_IMEI="+imei+"&strRequesrId="+strRequesrId,                  
                    success: function(cadena){                         
                    document.getElementById('imgProcesando').style.display = 'none';
                    //alert("EDU: cadena = " + cadena);
                    var sim = "";
                    var flagValida = "";
                    
                    var posInicio = cadena.indexOf('|');
                    sim = cadena.slice(0,posInicio);
                    //alert("EDU: sim = " + sim);
                    
                    cadena = cadena.substr(posInicio+1);                   
                    flagValida = cadena;//cadena.slice(0,posInicio);
                    //alert("EDU: flagValida = " + flagValida);
                    
                    if(trim(flagValida) == "1"){
                    //Validamos que el sim corresponda
                        
                        //alert("EDU: validamos que el sin corresponda");
                        
                        if(trim(numeroSim) == trim(sim)){
                            document.getElementById("sim_num_"+indice).value = trim(sim); 
                            document.getElementById('txtSims').value = "";
                                                        
                            //---Seleccionamos el siguiente radio button si todos lo campos tienen datos--
                              /*if(trim(document.getElementById("almacen_desc_"+indice).value) != ""          &&
                                  trim(document.getElementById("subintentario_desc_"+indice).value) != ""   &&
                                  trim(document.getElementById("sim_num_"+indice).value) != ""    &&
                                  trim(document.getElementById("imei_num_"+indice).value) != "" ){*/
                                                                    
                                      try{
                                          //Seleccionamos el siguiente radio boton                                          
                                          //Recorremos todos los redios y recuperamos el indice del radio checkeado
                                          var radioAux = document.frmdatos.item_imei_radio;
                                          var x =0;
                                          for(x=0; i<radioAux.length; x++){
                                              if(radioAux[x].checked) { 
                                                radioAux[x+1].checked = true;
                                                break;						
                                              } 
                                          }
                                          
                                      }catch(ex){
                                          //Si el indice esta fuera del rango no hace nada
                                      }
                                      
                                      document.getElementById('cmbProductStatus').disabled = true;
                                      
                              //}
                            
                        }else{
                            alert("IMEI ["+ imei +"] no corresponde al SIM ["+ trim(numeroSim) +"]");
                            document.getElementById('txtSims').value = "";
                            document.getElementById('txtSims').focus();                            
                        }
                    }else{ //No validamos nada y el pasa defrente
                        //alert("EDU: no validamos nada");
                        document.getElementById("sim_num_"+indice).value = trim(numeroSim); 
                        document.getElementById('txtSims').value = "";
                    }                                
                                    
                    }                
                });                
                
            }else{
                alert("No se ha ingresado ningun IMEI en la lista.");
                document.getElementById('txtSims').value = "";
                document.getElementById("txtImeis").focus();
            }            
        }
        
   }
    
   function validarIMEI(){
        var imei =  document.getElementById("txtImeis").value;
        var radio = document.frmdatos.item_imei_radio;
        var indice = 0;
        var i = 0;
        
        var posicion = 0;
        var almacen = "";
        var subinventario = "";
        var organization = "";
        var sim = "";
        
        var numrequest = document.getElementById("txtNumRequest").value;
        if(imei != ""){
           if (validar_Exits_IMEI(imei)){
               alert("El IMEI "+imei+", ya esta cargado en la solicitud, por favor verifique..."); 
               document.getElementById("txtImeis").value = "";
               document.getElementById("txtImeis").focus() ;               
               return; 
           }
        for(i=0; i<radio.length; i++){
					if(radio[i].checked) {
            indice = radio[i].value;
						break;						
					} 
				}
        
        document.getElementById('imgProcesando').style.display = 'inline';
        
         $.ajax({
                cache:false,
                type: "POST",   
                url: '<c:out value="${pageContext.request.contextPath}"/>/requestservlet',
                data: "METHOD=VALIDAR_IMEI&COD_IMEI="+imei,                  
                success: function(cadena){  
                
                  document.getElementById('imgProcesando').style.display = 'none';
                
                  var bFlag = '<%=bFlag%>'; 
                
                  if(cadena.indexOf('|')!=-1){     
                                                   
                    var posInicio = cadena.indexOf('|');
                    almacen = cadena.slice(0,posInicio);
                    
                    cadena = cadena.substr(posInicio+1);
                    posInicio = cadena.indexOf('|');
                    subinventario = cadena.slice(0,posInicio);
                    
                    //INICIO JGABRIEL REQ-0123
                    if (document.getElementById('hdnSubCategoriaId').value== "2029"){
                        //alert("valide imei: "+subinventario);
                        if(!validarSubInventoryCorde(subinventario,numrequest)) {
                            document.getElementById("almacen_desc_"+indice).value = "";
                            document.getElementById("subintentario_desc_"+indice).value = "";  
                            document.getElementById("txtIdOrganitation_"+indice).value = "";   
                            document.getElementById("imei_num_"+indice).value="";                    
                            
                            document.getElementById("txtImeis").value = "";
                            //document.getElementById("txtImeis"+indice).value = "";
                            document.getElementById("txtImeis").focus() ;
                            return;
                        }
                    }
                    //INICIO JGABRIEL REQ-0123
                    var respuesta = validarRegularizarOrdenOutdoor();
                    
                    if (respuesta){
                        cadena = cadena.substr(posInicio+1);
                        posInicio = cadena.indexOf('|');
                        organization = cadena.slice(0,posInicio);     
                        
                        cadena = cadena.substr(posInicio+1);
                        sim = cadena;
                        
                        document.getElementById("almacen_desc_"+indice).value = almacen;
                        document.getElementById("subintentario_desc_"+indice).value = subinventario;  
                        document.getElementById("txtIdOrganitation_"+indice).value = organization;
                        document.getElementById("imei_num_"+indice).value=imei;                    
                        document.getElementById("txtImeis").value = "";                        
                        document.getElementById("txtSims").value = sim;                        
                                                
                        if( bFlag == true ){                        
                            document.getElementById("hdnProductStatus").value = document.getElementById('cmbProductStatus').value;
                            document.getElementById('cmbProductStatus').disabled = true;                        
                        }                 
                    }else{
                          document.getElementById("txtImeis").value = "";
                          document.getElementById("txtImeis").focus() ;
                    } 

                    
                   
                    
                  }
                  else{
                    alert(cadena);
                    document.getElementById("txtImeis").value = "";
                    //document.getElementById("txtImeis"+indice).value = "";
                    document.getElementById("txtImeis").focus() ;
                  }
                                
                }                
          }); 
                        
        }                
    }
   
    //INICIO JGABRIEL REQ-0123
    function myAlert(data) {
    alert(data);
    }
    function validarSubInventoryCorde(sub_inventorycode,numrequest){
    var retorno = false;
         $.ajax({
                cache:false,
                type: "POST",   
                url: '<c:out value="${pageContext.request.contextPath}"/>/requestservlet',
                data: "METHOD=VALIDAR_SUBINVENTORYCODE&sub_inventorycode="+sub_inventorycode+"&numrequest="+numrequest,                  
                async: false,
                success: function(data){  
                    if(data ==""){
                        retorno= true;
                    }else{
                        myAlert(data);
                        retorno= false;
                 }
                },   
                error: function(){
                    alert("Error al validar subinventario");
                }                
          });  
          return retorno;
    } 
    
    function validarModeloSolicitado(imei, numrequest){
        var retorno = false;
         $.ajax({
                cache:false,
                type: "POST",   
                url: '<c:out value="${pageContext.request.contextPath}"/>/requestservlet',
                data: "METHOD=VALIDAR_MOELO_SOLICITADO&imei="+imei+"&numrequest="+numrequest,                  
                async: false,
                success: function(data){  
                    if(data ==""){
                        retorno= true;
                    }else{
                        myAlert(data);
                        retorno= false;
                 }
                },   
                error: function(){
                    alert("Error al validar modelo solicitado");
                }                
          });  
          return retorno;
    }       
    //FIN JGABRIEL REQ-0123  
    
      function verPanelProcesando(){		
        var divPanelFlotante = document.getElementById('divPanelFlotante');			
        divPanelFlotante.style.display = 'block';
      
      }
      
      function cerrarPanelProcesando(){
        var divPanelFlotante = document.getElementById('divPanelFlotante');	
        divPanelFlotante.style.display = 'none';
      }
      
      function limpiarLista(){
          
          var txtNroFilas = document.getElementById("txtNroFilas").value; 
          var radio = document.frmdatos.item_imei_radio;
          var indice = 0;
          var i = 0;
          
          if(txtNroFilas != "0"){
              
              for(i=0; i<radio.length; i++){
                  if(radio[i].checked) {
                      indice = radio[i].value;                      
                      break;						
                  } 
              }
              
              document.getElementById("imei_num_"+indice).value="";
              document.getElementById("sim_num_"+indice).value="";                  
              document.getElementById("almacen_desc_"+indice).value="";
              document.getElementById("subintentario_desc_"+indice).value="";
              //document.getElementById('cmbProductStatus').disabled = false;
          } 
          
      }      
   
 </script>
</head>

<body >
  <form  method="post" name="frmdatos">
      
      
    <input type="hidden" id="hdnBaseActual" name="hdnBaseActual" value="<c:out value='${baseActual}'/>">
    <input type="hidden" id="hdnFlagAgendamiento" name="hdnFlagAgendamiento" value="<c:out value='${flagAgendamiento}'/>">  
    <input type="hidden" id="hdnFlagInstalacionEdit" name="hdnFlagInstalacionEdit" value="<c:out value='${flagInstalacionEdit}'/>">  
     
     <table border="0"  cellspacing="0" cellpadding="0" width="100%" align="center">
        <tr>
            <td class="SectionTitleLeftCurve" width="10">&nbsp;&nbsp;</td>
            <td class="SectionTitle">&nbsp;&nbsp;Intentario - Atenci&oacute;n de Solicitudes a Almac&eacute;n</td>
       </tr>
    </table>
    
    <input type="hidden" id="hdnProductStatus" name="hdnProductStatus" value="">
    <input type="hidden" id="hdnRequestOlItemId" name="hdnRequestOlItemId" value="<%=strReqolitemid%>">
    
    <table border="0" cellspacing="1" cellpadding="1" align="center" class="RegionBorder" width="100%">
		<tr>
		  <td class="CellLabel" width="122" >Nro Solicitud</td>
		  <td class="CellContent" width="338">
        <span id="lblNumRequest" class="etiquetaTextBox"></span>
        <input type="text" id="txtNumRequest" name="txtNumRequest" size="30" class="ocultarTextbox"  readonly="true" maxlength="30"/> 
      </td>
		  
		  <input type="hidden" id="txtRequestId" name="txtRequestId" value='<%=headReq.getWn_requestolid()%>' />
		  
		  <td class="CellLabel" >Fecha Solicitud</td>
		  <td class="CellContent" >
        <span id="lblReqDate" class="etiquetaTextBox"></span>
			  <input type="text" id="txtReqDate" readonly="true" name="txtReqDate" size="30" class="ocultarTextbox" maxlength="15"/>      
      </td>
		  <td class="CellLabel" >Creado por</td>
		  <td width="299" class="CellContent">
          <span id="lblCreated" class="etiquetaTextBox"></span>
          <input type="text" id="txtCreated"  readonly="true" name="txtCreated" size="40" class="ocultarTextbox" maxlength="15"/>
          &nbsp;&nbsp;&nbsp;&nbsp;
      </td>
      </tr>
	  <tr >
		  <td class="CellLabel" width="122">Razón Social</td>
		  <td class="CellContent" width="338">
          <span id="lblRazonSocial" class="etiquetaTextBox"></span>
          <input type="text" name="txtRazonSocial" id ="txtRazonSocial" size="55" class="ocultarTextbox" readonly="true" maxlength="30"/>
          
      </td>
		  <td class="CellLabel" width="115">Nro de Factura</td>
		  <td class="CellContent" width="281">
          <span id="lblFactura" class="etiquetaTextBox"></span>
          <input type="text" id="txtFactura" readonly="true" name="txtFactura" size="30" class="ocultarTextbox" maxlength="15"/>      
      </td>
		  <td class="CellLabel" width="127">R.U.C.</td>
		  <td class="CellContent">
          <span id="lblRUC" class="etiquetaTextBox"></span>
          <input type="text" id="txtRUC" readonly="true" name="txtRUC" size="40" class="ocultarTextbox" maxlength="15"/>
          &nbsp;&nbsp;&nbsp;&nbsp;
      </td>
	   </tr>
		 <tr >
        <td class="CellLabel" width="122">Dirección</td>
        <td colspan="3" class="CellContent">
            <span id="lblDir" class="etiquetaTextBox"></span>
            <input type="text" name="txtDir" id ="txtDir" readonly="true" size="100"  class="ocultarTextbox" maxlength="30"/>      </td>
        <td class="CellLabel" width="127">Modificado Por</td>
        <td class="CellContent" colspan="3">
            <span id="lblMod" class="etiquetaTextBox"></span>
            <input type="text"  readonly="true" id="txtMod" name="txtMod" size="40" class="ocultarTextbox" maxlength="15"/>       
        </td>
		</tr>
		<tr>
			<td class="CellLabel">
					Detalle de la Orden	  </td>
				<td class="CellContent" colspan="5">
          <div id="lblDetalleOrden"></div>
					<textarea name="txtDetalleOrden" id="txtDetalleOrden" rows="5"  readonly="true" class="ocultarTextbox" cols="80"></textarea>			
				</td>
		</tr>
		<tr >
        <td class="CellLabel" width="122">Nro de Orden</td>
        <td class="CellContent" width="338">
            <span id="lblNumOrden" class="etiquetaTextBox"></span>
            <input type="text" name="txtNumOrden" id="txtNumOrden" readonly="true" size="40" class="ocultarTextbox"  maxlength="30"/></td>
            <input type="hidden" id="hdnReqOlItemId" name="hdnReqOlItemId" value="<%=strReqolitemid%>">
            
        <td class="CellLabel" width="115">Envio Courier</td>
        <td class="CellContent" >
            <span id="lblCourier" class="etiquetaTextBox"></span>
            <input type="text" id="txtCourier" readonly="true" name="txtCourier" size="40" class="ocultarTextbox" maxlength="15"/>      
        </td>
        <td class="CellLabel">Lugar de Despacho</td>
        <td class="CellContent">
            <span id="lblLugarDespacho" class="etiquetaTextBox"></span>
            <input type="text"  readonly="true" name="txtLugarDespacho" id="txtLugarDespacho" class="ocultarTextbox" size="40"/>
        </td>
		</tr>
		<tr >
			  <td class="CellLabel" width="122">División de Neg.</td>
			  <td class="CellContent" width="338">
            <span id="lblDivNego" class="etiquetaTextBox"></span>
            <input type="text" name="txtDivNego" id="txtDivNego" readonly="true" size="40" class="ocultarTextbox" maxlength="30"/>      
        </td>
			  <td class="CellLabel" width="115">Categoria</td>
			  <td class="CellContent"  width="281">
            <span id="lblCategoria" class="etiquetaTextBox"></span>
            <input type="text" readonly="true" name="txtCategoria" id="txtCategoria" size="40" class="ocultarTextbox" maxlength="15"/>      </td>
			  <td class="CellLabel" width="127">Sub-Categoria</td>
			  <td class="CellContent">
            <span id="lblSubCategoria" class="etiquetaTextBox"></span>
            <input type="text"  readonly="true" name="txtSubCategoria" id="txtSubCategoria" size="40" class="ocultarTextbox" maxlength="15"/>
            <input type="hidden" name="hdnSubCategoriaId" id="hdnSubCategoriaId">
            &nbsp;&nbsp;&nbsp;&nbsp;
			  </td>
		</tr>
    </table>
	
    <table border="0" cellspacing="1" cellpadding="1" align="center" class="RegionBorder" width="100%">
		<tr>
    
			<td width="190" class="CellLabel">Estado de la Solicitud</td>
			<td width="132" class="CellContent"> 
        <span id="lblEstadoSol" class="etiquetaTextBox"></span>
        
            
				<input type="text" id="txtEstadoSol" readonly="true" name="txtEstadoSol" class="ocultarTextbox" size="15" maxlength="15"/>
				<input type="hidden" id="txtCodigoEstado" name="txtCodigoEstado"  value="<%=headReq.getCodigoSolicitud()%>"/>
			</td>
			<td class="CellLabel" width="135">Fecha Programada de Entrega</td>
			<td class="CellContent"  width="191">
				<input type="text" name="txtCreateDateFrom" id="txtCreateDateFrom" size="15" maxlength="10" value="" onBlur="this.value=fxTrim(this.value);">
				<a href="javascript:show_calendar('frmdatos.txtCreateDateFrom',null,null,'DD/MM/YYYY');" onMouseOver="window.status='Fecha desde';return true;" onMouseOut="window.status='';return true;">
					<img id="imgCalendar1" src="<%=request.getContextPath()%>/websales/images/show-calendar.gif" width="24" height="22" border="0">
			<td class="CellLabel" width="171">Fecha Real de Entrega</td>
		    <td class="CellContent"  width="196">
					<input type="text" name="txtCreateDateTill" id="txtCreateDateTill" size="15" maxlength="10" value="" onBlur="this.value=fxTrim(this.value);">
				<a href="javascript:show_calendar('frmdatos.txtCreateDateTill',null,null,'DD/MM/YYYY');" onMouseOver="window.status='Fecha desde';return true;" onMouseOut="window.status='';return true;"><img id="imgCalendar2" src="<%=request.getContextPath()%>/websales/images/show-calendar.gif" width="24" height="22" border="0"></td></a>
			<td width="127" class="CellLabel" >Entregado a : </td>
		  
			<td width="133" class="CellContent">
				<select name="cmbPersonalDelivery" id="cmbPersonalDelivery" style="width:100px">      
					<c:forEach var="listaPersonal" items="${requestScope.INV_PERSONAL_DELIVERY}">                           
										
						<c:if test="${listaPersonal.valor == requestScope.ENTREGADO_A}" >
							<option value="<c:out value='${listaPersonal.valor}'/> " selected >
								<c:out value="${listaPersonal.descripcion}"/>
							</option>
						</c:if>
          
						<c:if test="${listaPersonal.valor != requestScope.ENTREGADO_A}" >
							<option value="<c:out value='${listaPersonal.valor}'/> "  >
								<c:out value="${listaPersonal.descripcion}"/>
							</option>
						</c:if>
            
					</c:forEach>  
				</select>
			</td>
		</tr>
	</table>  

	<table border="0" cellspacing="1" cellpadding="1" align="center" class="RegionBorder" width="100%">
		  <tr>
				<td width="94" class="CellLabel"> Observaciones	  </td>
				<td width="499" colspan="7" class="CellContent">
					<textarea name="txtDescription2" id="txtDescription2" rows="5" cols="100"></textarea>
				</td>
		 </tr>
	</table>  
		  </br></br><br/>
		  
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
                    <display:column property="wv_descripcionAlmacen" title="Almacén"/>
                    <display:column property="wv_subinventoryid" title="SubInventario"/>
                    <display:column property="wv_reason_name" title="Motivo"/>
                    <display:column property="wv_descripcionDocumento" title="Doc.Generado"/>
        </display:table>
        </br>
    <table border="0" cellpadding="0" cellspacing="0" class="RegionBorder">
         <tr class="PortletHeaderColor">
            <td class="SubSectionTitleLeftCurve" valign="top" align="left" width="16"></td>
            <td class="SubSectionTitle" align="LEFT" valign="top">Equipos</td>
            <td class="SubSectionTitleRightCurve" valign="top" align="right" width="12"></td>
         </tr>
    </table>
      
      
      
    <display:table name="OBJ_ARRAYEQUIPMENT" id="arrayEquipment" class="orders" style="width: 100%">
        <display:column title="Nro" style="white-space: nowrap;" media="html">
            <label name ="num"><b><%=((EquipmentBean)arrayEquipment).getWn_contador()%></b></label>
            </a>
        </display:column>
        
        <display:column property="wv_solution" title="Solución de Neg."/>
        <display:column property="wv_itemtypeid" title="Tip.Articulo"/>
        <display:column property="wv_name" title="Producto"/>
        <display:column property="wv_npreqolimei" title="IMEI"/>
        <display:column property="wv_npreqimeisim" title="SIM"/>                                
        <display:column property="wv_returneqimeisim" title="Dev. Eq. Reparado?"/>
        <display:column property="wv_modalitysell" title="Modalidad"/>
        <display:column title="Condición">
            
            <%
            String wv_productstatus = ((EquipmentBean)arrayEquipment).getWv_productstatus();
            int intSubCategoriaId = headReq.getWn_specificationId();
            System.out.println("intSubCategoriaId >>> "+intSubCategoriaId);
            String estadoSol = headReq.getWv_reqolstatus();
            bFlag = false;
            String[] arrayModifyCondition = Constante.ST_MODIFY_CONDITION.split(",");

            for(int i=0; i<arrayModifyCondition.length; i++){
                if( intSubCategoriaId == Integer.parseInt( arrayModifyCondition[i] )  ){
                   bFlag = true;
                }
            }
            
            //System.out.println("bFlag >>> "+bFlag);
            
            if( bFlag == true ){
                %>
                <select id="cmbProductStatus" disabled="true" name="cmbProductStatus" onchange="javascript:productStatusChange();">
                    <option value="Nuevos" <%=wv_productstatus.equals("Nuevos")?" selected ":""%> >Nuevos</option>
                    <option value="Usados" <%=wv_productstatus.equals("Usados")?" selected ":""%> >Usados</option>
                </select>
                <script type="text/javascript">
                    function productStatusChange(){
                        var cmbProductStatus = $( "#cmbProductStatus option:selected" ).val();
                        $("#hdnProductStatus").val(cmbProductStatus);
                    }
                </script>
                <%
            }else{
                %><%=wv_productstatus%><%
            }
            
            %>
        </display:column>
        <display:column property="wn_quantity" title="Cantidad"/>
        
        <display:column title="Con Accesorios?" >         
        
            <%  
            String wv_accessory = ((EquipmentBean)arrayEquipment).getWv_accessory();
            if("SI".equals(MiUtil.trimNotNull(wv_accessory).toUpperCase()) || "SÍ".equals(MiUtil.trimNotNull(wv_accessory).toUpperCase()) ){
            out.println("<span><font color='#FF0000'>"+ wv_accessory +"</font></span>");
            }else{
            out.println("<span>"+ wv_accessory +"</span>");
            }
            %>
        
        </display:column>
        
        <display:column property="wv_reason_name" title="Motivo"/>
        <display:column property="wv_transnnumber" title="Doc.Generado"/>
    </display:table>
      
	  <br/><br/>
	  <table width="75%">
		  <tr>      
			<td> 
			  <div id="divServicios" >      </div>                   
			  <div id="divImeisItems">
					<table class="RegionBorder" width="100%" align="right">
						  <tr>
								<td width="18%" class="CellLabel">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IMEI</td>
								<td width="17%" class="CellContent">
									<input type="text" name="txtImeis" id="txtImeis" maxlength="15"   onblur="validarIMEI()" tabindex="1">                 
								</td>
								<td width="18%" class="CellLabel">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SIM</td>
								<td width="40%" class="CellContent">
									<input type="text" name="txtSims" id="txtSims" value="" maxlength="20" onblur="obtenerSIM()" tabindex="2">
									<br>
								</td>
                
                
						  </tr>
						  <tr>
								<td colspan="4" class="CellContent" align="center">
									<div id="grid_region" style="overflow: auto; width:100%; height:200px;">
										  
										 <%
											String tabla = "";
											
											tabla = tabla + "<table name='table_imeis' width='100%' id='table_imeis' border='1' cellpadding='2' cellspacing='0'>";
											tabla = tabla + "<tr class='CellLabel'>";
											tabla = tabla + "<td align='center' width='6%' >N°</td>";
											tabla = tabla + "<td align='center' width='18%' >IMEI</td>";
											tabla = tabla + "<td align='center' width='18%' >SIM</td>";
											tabla = tabla + "<td align='center' width='18%' >Almacén</td>";
											tabla = tabla + "<td align='center' width='26%' >SubInventario</td>";
											tabla = tabla + "</tr>";
											
											int filas =0;
											//System.out.println("EDU jsp 0");
											for(int i= 0; i< listaEquipos.size(); i++){
                                                                                            //System.out.println("EDU jsp 1");
                                                                                            EquipmentBean equipmentBean = (EquipmentBean)listaEquipos.get(i);
                                                                                                //System.out.println("EDU jsp 2");
												String cantidad =  equipmentBean.getWn_quantity();
                                                                                                //System.out.println("EDU jsp 3");
												String contador =  equipmentBean.getWn_contador();
                                                                                                //System.out.println("EDU jsp 4");
												String cadena = "";
												String idEquipo =  equipmentBean.getWv_reqolitemid();
                                                                                            //System.out.println("EDU jsp 5");
                        
                                                                                                String idEquipoDevice =  "";
                                                                                                String idDevice = "";
                                                                                                String strImei = "";
                                                                                                String strSim = "";
                                                                                                String strAlmacen = "";
                                                                                                String strSubInventario = "";
                                                                                                String idOrganization =  "";
                       
                        
				for(int j = 0; j < Integer.parseInt(cantidad); j++){
                              //System.out.println("EDU jsp 6");      
                              
                              DeviceBean divice = (DeviceBean)equipmentBean.getListaDeviceBean().get(j);
                            
                              idEquipoDevice =  divice.getWv_reqolitemid_device();
                              idDevice = divice.getWv_reqolitemdeviceid();
                              strImei = divice.getWv_reqolimei();
                              strSim = divice.getWv_reqimeisim();
                              strAlmacen = divice.getWv_reqalmacen();
                              strSubInventario = divice.getWv_reqsubinventario();
                              idOrganization =  divice.getWv_reqorganizationid();
                              
                              
                              System.out.println("EDU jsp idEquipoDevice = " + idEquipoDevice);
                              /*System.out.println("EDU jsp idDevice = " + idDevice);
                              System.out.println("EDU jsp strImei = " + strImei);
                              System.out.println("EDU jsp strSim = " + strSim);
                              System.out.println("EDU jsp strAlmacen = " + strAlmacen);
                              System.out.println("EDU jsp strSubInventario = " + strSubInventario);
                              System.out.println("EDU jsp idOrganization = " + idOrganization);*/
                               
                              cadena = contador +" - "+ (j+1);
                              tabla = tabla + "<tr>";  
                              tabla=tabla+"<td>"; 
                              
                            tabla =tabla+" <input type='hidden' id='txtReqOlItemId"+filas+"' name='txtReqOlItemId"+filas+"' value='"+idEquipoDevice+"' />";
                            tabla =tabla+" <input type='hidden' id='txtIdEquipment"+filas+"' name='txtIdEquipment"+filas+"' value='"+idDevice+"' />";
                            tabla =tabla+" <input type='hidden' id='txtIdOrganitation_"+filas+"' name='txtIdOrganitation_"+filas+"' value='"+idOrganization+"' />";
                            if(filas==0){
                                  tabla =tabla+"<input type='radio' value='"+filas+"' name='item_imei_radio' id='item_imei_radio' checked/>";                                  
                            }else{
                                  tabla =tabla+"<input type='radio' value='"+filas+"' name='item_imei_radio' id='item_imei_radio' />";
                            }                       
												
												tabla =tabla+"<input type='text' id='item_imei_num_"+filas+"'  name='item_imei_num_"+filas+"' value='"+cadena+"' size='2' readonly>";
												tabla=tabla+"</td >"; 
												tabla=tabla+"<td >"; 
												tabla =tabla+"<input type='text' id='imei_num_"+filas+"'  name='imei_num_"+filas+"' size='30'  value='"+strImei+"'  readonly  >";
												tabla=tabla+"</td >";
												tabla=tabla+"<td >"; 
												tabla =tabla+"<input type='text' id='sim_num_"+filas+"'  name='sim_num_"+filas+"' size='30'  value='"+strSim+"' readonly>";
												tabla=tabla+"</td >"; 
												tabla=tabla+"<td >"; 
												tabla =tabla+"<input type='text' id='almacen_desc_"+filas+"' name='almacen_desc_"+filas+"' size='40' value='"+strAlmacen+"' readonly>";
												tabla=tabla+"</td >"; 
												tabla=tabla+"<td >"; 
												tabla =tabla+"<input type='text' id='subintentario_desc_"+filas+"' name='subintentario_desc_"+filas+"' size='50'value='"+strSubInventario+"' readonly>";
												tabla=tabla+"</td >"; 
												tabla = tabla + "</tr>";      
												
												filas++;
												
												   
											   }                  
										   }    
										  
										   
										 tabla =tabla+"</table>";
										 
										 out.print(tabla);            
										 
										 out.print("<input type='hidden' id='txtNroFilas' value='"+filas+"'/>");
										 
										 %>
						   
									</div>   
                        
                      <input type="button" name="btnLimpiarLista" id="btnLimpiarLista" value="Borrar" onclick="limpiarLista()"/>
                      
								</td>
						  </tr>
					</table>
			  </div>
			</td>      
		</tr>
    </table>
    
    <div id="divInstalacion">
    <br>
    <table align="center" BORDER="0" class="PortletHeaderColor" cellspacing="0" cellpadding="0" width="99%">
        <tr class="PortletHeaderColor">
            <td class="SubSectionTitleLeftCurve" width="10">&nbsp;</td>
            <td class="SubSectionTitle">Estado de Trabajo de Instalación</td>
            <td class="SubSectionTitleRightCurve" width="8">&nbsp;</td>
        </tr>
    </table>
    <table align="center" class="RegionBorder" border="0" cellspacing="1" cellpadding="1" width="99%">
        <tr>
            <td class="CellLabel" width="72">&nbsp;<font color="#FF0000" >*</font>Motivo</td>
            <td class="CellContent" width="20%">
                <select id="selMotivo" name="selMotivo">
                </select>
            </td>
            <td class="CellLabel" width="72">&nbsp;<font color="#FF0000" >*</font>Sub Motivo</td>
            <td class="CellContent" width="30%">
                <select id="selSubMotivo" name="selSubMotivo">
                </select>
            </td>
            <td class="CellLabel" width="72">&nbsp;<font color="#FF0000"  >*</font>Detalle</td>
            <td class="CellContent" width="30%">
                <select id="selDetalle" name="selDetalle">
                </select>
            </td>
        </tr>
        <tr>
            <td class="CellLabel" width="72" >Comentarios</td>
            <td class="CellContent" width="99%" colspan="5">
                <textarea name="txtComentario" id="txtComentario" rows="5" class="" cols="120"></textarea>
            </td>
        </tr>
    </table>
    <br>
    </div>
    <table  height="51" align="left" border="0" >
		<tr>
			<td class="CellLabel" width="60">Acciones </td>
			<td class="CellContent" width="102">
			   <select name="cmbAcciones" id="cmbAcciones" style="width:150">      
					 <c:forEach var="lista" items="${requestScope.OL_ACTIONS}">
							  <c:if test="${lista.valor == ''}" >
								<option value="<c:out value='${lista.valor}'/> " selected >
									  <c:out value="${lista.descripcion}"/>
								</option>
							  </c:if>
							  
							  <c:if test="${lista.valor != ''}" >
								<option value="<c:out value='${lista.valor}'/> "  >
							   <c:out value="${lista.descripcion}"/>
							   </option>
							  </c:if>
									
					 </c:forEach>  
			  </select>
			  </td>
			  <td class="CellLabel" width="72">Motivo </td>
			  <td class="CellContent" width="134">
					<select name="cmbMovito" id="cmbMovito" style="width:150">
						 <option value="" ></option>
						   <c:forEach var="lista" items="${requestScope.OL_REASONS}">
													   
								<c:if test="${lista.valor == requestScope.OL_MOTIVO}" >
									<option value="<c:out value='${lista.valor}'/> " selected >
										<c:out value="${lista.descripcion}"/>
									</option>
								</c:if>
							  
								<c:if test="${lista.valor != requestScope.OL_MOTIVO}" >
									<option value="<c:out value='${lista.valor}'/> "  >
										<c:out value="${lista.descripcion}"/>
									</option>
								</c:if>
														
							</c:forEach>  
					</select>
			  </td>   
			
			  <td width="86">
                          
				  <input name="btnSaveRequest" id="btnSaveRequest"  type="button" value="Grabar" onClick="validarGrabarDatos()">
			  </td>
			  <td width="99">
				  <input name="btnGenDoc" id="btnGenDoc"  type="button" value="Generar Doc." onClick="generateDoc()">
			  </td>
			  <td align="right" width="150">
					<img id="imgProcesando" style="display:none;" src="<%=request.getContextPath()%>/websales/images/procesando1.gif" border="0"/>
			  </td>

		</tr>

    </table>
  </form>
  
  
  </body>

<script>
  
  function generateDoc(){
                
        var  strLogin='<%=strLogin%>';
        var  strRequestId ='<%=headReq.getWn_requestolid()%>';
        
        if(validarListaIMEI()){
        
            document.getElementById("btnGenDoc").disabled = true;
            document.getElementById('imgProcesando').style.display =  "inline";
                        
            //alert("strLogin = " + strLogin + " strRequestId = " +strRequestId);
            
            $.ajax({
                cache:false,
                type: "POST",   
                url: '<c:out value="${pageContext.request.contextPath}"/>/generalservlet',
                data: "METHOD=GENERATE_DOC&strLogin="+strLogin+"&strRequestId="+strRequestId,
                success: function(cadena){ 
                    document.getElementById('imgProcesando').style.display =  "none";
                    alert(cadena);     
                    location.reload(true);            
                }                
            });    
        
        }else{
            alert("Por favor ingresar todos los equipos");
            document.getElementById("txtImeis").value = "";
            document.getElementById("txtImeis").focus();            
        }                  
    }
  
    
  function onLoadForm(){
  
  var requestId = '<%=headReq.getWn_requestolid()%>'; 
  var txtRUC = '<%=headReq.getWv_customertaxnumber()%>';
  var txtModifiby = '<%=headReq.getWv_modifyby()%>';
  var txtNumRequest = '<%=headReq.getWv_requestnumber()%>';
  var txtReqDate = '<%=headReq.getWv_createddate()%>'; 
  var txtCreated = '<%=headReq.getWv_createdby()%>';
  var txtRazonSocial = '<%=headReq.getWv_customername()%>';
  var txtFactura = '<%=headReq.getWv_npsaletrxnumber()%>';
  var txtDir = '<%=headReq.getWv_customeraddress1()%>';
  var txtDetalleOrden = '<%=headReq.getWv_description()%>';
  var txtNumOrden = '<%=headReq.getWn_orderid()%>';
  var txtCourier = '<%=headReq.getWv_courier()%>';
  var txtDivNego = '<%=headReq.getWv_name()%>';
  var txtCategoria = '<%=headReq.getWv_type()%>';
  var txtSubCategoria = '<%=headReq.getWv_specification()%>';
  
  var txtLugarDespacho = '<%=headReq.getWv_npshortname()%>';
  
  var txtEstadoSol = '<%=headReq.getWv_reqolstatus()%>';
  var codEstado = '<%=headReq.getCodigoSolicitud()%>';
  
  var fechaEntrega = '<%=headReq.getWv_scheduledate()%>';
  var fechaReal = '<%=headReq.getWv_realdate()%>';
  var observacion = '<%=headReq.getWv_notes()%>';    
  
  
  var txtNroFilas = document.getElementById("txtNroFilas").value;
  var flagGeneroDocumento = '<%=headReq.getWv_flagGenerarDocumento()%>';
  
  var hdnSubCategoriaId = '<%=headReq.getWn_specificationId()%>';
    
  $('#lblNumRequest').html(txtNumRequest);  
  $('#lblReqDate').html(txtReqDate); 
  $('#lblCreated').html(txtCreated); 
  $('#lblRazonSocial').html(txtRazonSocial);
  $('#lblFactura').html(txtFactura);
  $('#lblRUC').html(txtRUC);
  $('#lblDir').html(txtDir);
  $('#lblMod').html(txtModifiby);
  $('#lblDetalleOrden').html(txtDetalleOrden);
  $('#lblNumOrden').html(txtNumOrden);
  $('#lblCourier').html(txtCourier);
  $('#lblLugarDespacho').html(txtLugarDespacho);
  $('#lblDivNego').html(txtDivNego);
  $('#lblCategoria').html(txtCategoria);
  $('#lblSubCategoria').html(txtSubCategoria);
  $('#lblEstadoSol').html(txtEstadoSol);

    
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
  document.getElementById('txtLugarDespacho').value=txtLugarDespacho;
  
  document.getElementById('txtEstadoSol').value=txtEstadoSol;
  document.getElementById('txtCodigoEstado').value=codEstado;
  
  document.getElementById('txtCreateDateFrom').value=fechaEntrega;
  document.getElementById('txtCreateDateTill').value=fechaReal;
  document.getElementById('txtDescription2').value=observacion;
  document.getElementById('hdnSubCategoriaId').value=hdnSubCategoriaId;
  
    
  if(txtNroFilas == "0"){
      document.getElementById('txtImeis').readOnly = true;
      document.getElementById('txtSims').readOnly = true;      
  }  
  
  //  1 = attendida
  //  3 entregada  
  
  
  //Si el estado de la solicitud es distinto de pendiente no se puede seleccionar
  // el estado del producto
  
    //var element = document.getElementById('cmbProductStatus');
    
   // if( bFlag == true ){
   if( document.getElementById('cmbProductStatus') ){
  if(txtEstadoSol != "<%=Constante.ESTADO_PENDIENTE%>"){
    document.getElementById('cmbProductStatus').disabled =  true; 
  }
    }

    
    /*if(txtEstadoSol != "<%=Constante.ESTADO_PENDIENTE%>"){
        document.getElementById('cmbProductStatus').disabled =  true; 
    }*/
      
 
  
  //Si el estado de la solicitud es atendida    
  if(txtEstadoSol == "<%=Constante.ESTADO_ATENDIDA%>"){
    document.getElementById('txtImeis').readOnly = true;
    document.getElementById('txtSims').readOnly = true;    
  }
  
  //Si el estado de la solicitud es entregada    
  if(txtEstadoSol == "<%=Constante.ESTADO_ENTREGADA%>"){
    desactivarTodoControloes();
  }
  
  if(flagGeneroDocumento =="S"){
    document.getElementById('txtImeis').disabled =  true;
    document.getElementById('txtSims').disabled =  true;
  }
  
   
 
}
  function desactivarTodoControloes(){
     document.getElementById('txtNumRequest').readOnly =  true; 
     
     document.getElementById('txtReqDate').readOnly =  true;
     document.getElementById('txtCreated').readOnly =  true;
     document.getElementById('txtRazonSocial').readOnly =  true;
     document.getElementById('txtFactura').readOnly =  true;
     document.getElementById('txtRUC').readOnly =  true;
     document.getElementById('txtDir').readOnly =  true;
     document.getElementById('txtMod').readOnly =  true;
     document.getElementById('txtDetalleOrden').readOnly =  true;
     document.getElementById('txtNumOrden').readOnly =  true;
     document.getElementById('txtCourier').readOnly =  true;
     document.getElementById('txtDivNego').readOnly =  true;
     document.getElementById('txtCategoria').readOnly =  true;
     document.getElementById('txtSubCategoria').readOnly =  true;
     document.getElementById('txtEstadoSol').readOnly =  true;
     document.getElementById('txtCreateDateFrom').readOnly =  true;     
     document.getElementById('imgCalendar1').style.display =  "none";     
     document.getElementById('txtCreateDateTill').readOnly =  true;
     document.getElementById('imgCalendar2').style.display =  "none";
     document.getElementById('cmbPersonalDelivery').disabled =  true;
     document.getElementById('txtDescription2').readOnly =  true;
     document.getElementById('txtImeis').readOnly =  true;
     document.getElementById('txtSims').readOnly =  true;
     document.getElementById('cmbAcciones').disabled =  true;
     document.getElementById('cmbMovito').disabled =  true;
     document.getElementById('btnSaveRequest').disabled =  true;
     document.getElementById('btnGenDoc').disabled =  true;
     document.getElementById('selMotivo').disabled =  true;
     document.getElementById('selSubMotivo').disabled =  true;
     document.getElementById('selDetalle').disabled =  true;
     document.getElementById('txtComentario').disabled =  true;    
  }
  
     function validarGrabarDatos(){
         var hdnSubCategoriaId = '<%=headReq.getWn_specificationId()%>';
         var accion = document.getElementById('cmbAcciones').value;
         var grabar = true;
         if (hdnSubCategoriaId == "2028" || hdnSubCategoriaId == "2029" || hdnSubCategoriaId == "2030"){
            //INICIO JGABRIEL REQ-0123
            if (hdnSubCategoriaId == "2029" && accion == " "){
                var txtNumOrden = '<%=headReq.getWn_orderid()%>';
                var txtNroFilas = document.getElementById("txtNroFilas").value;
                var txtNumRequest = document.getElementById("txtNumRequest").value
                var txtImei = "";
                //obtnemos los valores IMEI's
                for(i=0; i < parseInt(txtNroFilas); i++){               
                    txtImei = document.getElementById("imei_num_"+i).value;
                    //alert("txtNumOrden  "+txtNumOrden+"  txtImei"+txtImei);
                    if(!validarModeloSolicitado(txtImei,txtNumRequest)){
                       return;
                    }
                }
            }
            //INICIO JGABRIEL REQ-0123
            
            validateModelCondition2();
         }else{
            if(!fxValidarGrabarInstalacion()){
                grabar = false;
            }            
            if(grabar){
                grabarDatos();
            }
         }
     }
    
     function grabarDatos(){        
                
        //Recuperamos los datos del formulario
        var strLogin='<%=strLogin%>'; 
        var txtRequestId = document.getElementById("txtRequestId").value;        
        var txtCodigoEstado = document.getElementById("txtCodigoEstado").value;
        var cmbAcciones = document.getElementById("cmbAcciones").value;
        var txtCreateDateFrom = document.getElementById("txtCreateDateFrom").value;
        var txtCreateDateTill = document.getElementById("txtCreateDateTill").value;
        var txtDescription2 = document.getElementById("txtDescription2").value;
        var cmbMovito = document.getElementById("cmbMovito").value;
        var cmbPersonalDelivery = document.getElementById("cmbPersonalDelivery").value;
        
        var hdnProductStatus = document.getElementById("hdnProductStatus").value;
        var hdnRequestOlItemId = document.getElementById("hdnRequestOlItemId").value;
        
        var selMotivo = $("#selMotivo").val();
        var selSubMotivo = $("#selSubMotivo").val();
        var selDetalle = $("#selDetalle").val();
        var txtComentario = $("#txtComentario").val();
        var txtNumOrden = '<%=headReq.getWn_orderid()%>';
        var hdnFlagAgendamiento = $("#hdnFlagAgendamiento").val();
        
        var txtNroFilas = document.getElementById("txtNroFilas").value;
        var i=0;
        var parametrosLista = "";
        var parametros= "";
        
       // var grillaVacia = validarListaIMEI();
          
         /*if(validarListaIMEI()){*/
         
            //desactivamos el boton grabar
            document.getElementById('btnSaveRequest').disabled =  true;
            document.getElementById('imgProcesando').style.display =  "inline";
        
            //obtnemos los valores IMEI's
            for(i=0; i < parseInt(txtNroFilas); i++){              
                parametrosLista = parametrosLista + "&" + "imei_num_"+i+"="+document.getElementById("imei_num_"+i).value;              
                parametrosLista = parametrosLista + "&" + "sim_num_"+i+"="+document.getElementById("sim_num_"+i).value;
                parametrosLista = parametrosLista + "&" + "txtIdEquipment"+i+"="+document.getElementById("txtIdEquipment"+i).value;
                parametrosLista = parametrosLista + "&" + "almacen_desc_"+i+"="+document.getElementById("almacen_desc_"+i).value;
                parametrosLista = parametrosLista + "&" + "subintentario_desc_"+i+"="+document.getElementById("subintentario_desc_"+i).value;
                parametrosLista = parametrosLista + "&" + "txtIdOrganitation_"+i+"="+document.getElementById("txtIdOrganitation_"+i).value;                            
            }
            
            //Recuperamos los parametros necesarios para grabar la solicitud
            parametros="strLogin="+strLogin+"&"+ "txtRequestId="+txtRequestId+"&"+
                        "txtCodigoEstado="+txtCodigoEstado+"&"+ "cmbAcciones="+cmbAcciones+"&"+
                        "txtCreateDateFrom="+txtCreateDateFrom+"&"+ "txtCreateDateTill="+txtCreateDateTill+"&"+
                        "txtDescription2="+txtDescription2+"&"+ "cmbMovito="+cmbMovito+"&"+
                        "cmbPersonalDelivery="+cmbPersonalDelivery + parametrosLista + "&" + 
                        "txtNroFilas="+txtNroFilas+ "&" + 
                        "hdnProductStatus="+hdnProductStatus+ "&" + 
                        "hdnRequestOlItemId="+hdnRequestOlItemId + "&" +
                        "selMotivo="+selMotivo+ "&" +
                        "selSubMotivo="+selSubMotivo+ "&" +
                        "selDetalle="+selDetalle+ "&" +
                        "txtComentario="+txtComentario+ "&" +
                        "txtNumOrden="+txtNumOrden+ "&" +
                        "hdnFlagAgendamiento="+hdnFlagAgendamiento;
                            
                //alert(parametros);
                
                $.ajax({
                        cache:false,
                        type: "POST",   
                        url: '<c:out value="${pageContext.request.contextPath}"/>/generalservlet',
                        data: "METHOD=SAVE_REQUEST&"+parametros,                  
                        success: function(cadena){
                          document.getElementById('imgProcesando').style.display =  "none";
                          
                          if(cadena != ""){
                             alert(cadena);
                          }
                          //Habilitamos el boton grabar
                          //document.getElementById('btnSaveRequest').disabled = false;
                          location.reload(true);
                        } 
                  });             
        
              /*}else{
                  alert("Debe ingresar al menos un IMEI");
                  document.getElementById("txtImeis").value = "";
                  document.getElementById("txtImeis").focus();            
              }*/
          
                
        
             
      }  
      
      function validateModelCondition(){        
                
        //Recuperamos los datos del formulario
        var strLogin='<%=strLogin%>'; 
        var txtRequestId = document.getElementById("txtRequestId").value;        
        var txtCodigoEstado = document.getElementById("txtCodigoEstado").value;
        var cmbAcciones = document.getElementById("cmbAcciones").value;
        var txtCreateDateFrom = document.getElementById("txtCreateDateFrom").value;
        var txtCreateDateTill = document.getElementById("txtCreateDateTill").value;
        var txtDescription2 = document.getElementById("txtDescription2").value;
        var cmbMovito = document.getElementById("cmbMovito").value;
        var cmbPersonalDelivery = document.getElementById("cmbPersonalDelivery").value;
        
        var hdnProductStatus = document.getElementById("hdnProductStatus").value;
        var hdnRequestOlItemId = document.getElementById("hdnRequestOlItemId").value;
        
        var txtNroFilas = document.getElementById("txtNroFilas").value;
        var i=0;
        var parametrosLista = "";
        var parametros= "";
        
       // var grillaVacia = validarListaIMEI();
          
         /*if(validarListaIMEI()){*/
         
            //desactivamos el boton grabar
            //document.getElementById('btnSaveRequest').disabled =  true;
            document.getElementById('imgProcesando').style.display =  "inline";
        
            //obtnemos los valores IMEI's
            for(i=0; i < parseInt(txtNroFilas); i++){              
                parametrosLista = parametrosLista + "&" + "imei_num_"+i+"="+document.getElementById("imei_num_"+i).value;              
                parametrosLista = parametrosLista + "&" + "sim_num_"+i+"="+document.getElementById("sim_num_"+i).value;
                parametrosLista = parametrosLista + "&" + "txtIdEquipment"+i+"="+document.getElementById("txtIdEquipment"+i).value;
                parametrosLista = parametrosLista + "&" + "almacen_desc_"+i+"="+document.getElementById("almacen_desc_"+i).value;
                parametrosLista = parametrosLista + "&" + "subintentario_desc_"+i+"="+document.getElementById("subintentario_desc_"+i).value;
                parametrosLista = parametrosLista + "&" + "txtIdOrganitation_"+i+"="+document.getElementById("txtIdOrganitation_"+i).value;                            
            }
            
                //Recuperamos los parametros necesarios para grabar la solicitud
                parametros="strLogin="+strLogin+"&"+ "txtRequestId="+txtRequestId+"&"+
                            "txtCodigoEstado="+txtCodigoEstado+"&"+ "cmbAcciones="+cmbAcciones+"&"+
                            "txtCreateDateFrom="+txtCreateDateFrom+"&"+ "txtCreateDateTill="+txtCreateDateTill+"&"+
                            "txtDescription2="+txtDescription2+"&"+ "cmbMovito="+cmbMovito+"&"+
                            "cmbPersonalDelivery="+cmbPersonalDelivery + parametrosLista + "&" + 
                            "txtNroFilas="+txtNroFilas+ "&" + 
                            "hdnProductStatus="+hdnProductStatus+ "&" + 
                            "hdnRequestOlItemId="+hdnRequestOlItemId;
                            
                //alert(parametros);
                $.ajax({
                        cache:false,
                        type: "POST",   
                        url: '<c:out value="${pageContext.request.contextPath}"/>/generalservlet',
                        data: "METHOD=VALID_MODEL_CONDITION&"+parametros,                  
                        success: function(cadena){
                          document.getElementById('imgProcesando').style.display =  "none";
                          var r;
                          
                          if(r=="")
                            grabarDatos();
                          else{
                           r = confirm(cadena);
                          
                          if( r == true)
                            grabarDatos();
                          else
                            return false;
                            
                          }
                          //Habilitamos el boton grabar
                          //document.getElementById('btnSaveRequest').disabled = false;
                          //location.reload(true);
                        }               
                  });             
        
              /*}else{
                  alert("Debe ingresar al menos un IMEI");
                  document.getElementById("txtImeis").value = "";
                  document.getElementById("txtImeis").focus();            
              }*/
          
                
        
             
      }
      
      function validateModelCondition2(){        
         
        //Recuperamos los datos del formulario
        var strLogin='<%=strLogin%>'; 
        var txtRequestId = document.getElementById("txtRequestId").value;        
        var txtCodigoEstado = document.getElementById("txtCodigoEstado").value;
        var cmbAcciones = document.getElementById("cmbAcciones").value;
        var txtNroFilas = document.getElementById("txtNroFilas").value;
        var i=0;
        var parametrosLista = "";
        var parametros= "";
		var hdnProductStatus = "";
		
		if (document.getElementById('cmbProductStatus').value!= "undefined" && (document.getElementById('cmbProductStatus').value).length>1){
		   document.getElementById("hdnProductStatus").value = document.getElementById('cmbProductStatus').value;
		   hdnProductStatus = document.getElementById("hdnProductStatus").value;
        }else{
		   hdnProductStatus = document.getElementById("hdnProductStatus").value;
		}
        //obtnemos los valores IMEI's
        for(i=0; i < parseInt(txtNroFilas); i++){              
            parametrosLista = parametrosLista + "&" + "imei_num_"+i+"="+document.getElementById("imei_num_"+i).value;
        }
        
            //Recuperamos los parametros necesarios para grabar la solicitud
            parametros="strLogin="+strLogin+"&"+ "txtRequestId="+txtRequestId+"&"+
                        "txtCodigoEstado="+txtCodigoEstado+"&"+ "cmbAcciones="+cmbAcciones+ parametrosLista+"&"+
                        "txtNroFilas="+txtNroFilas+ "&" + 
                        "hdnProductStatus="+hdnProductStatus;
                        
            //alert(parametros);
            $.ajax({
                    cache:false,
                    type: "POST",   
                    url: '<c:out value="${pageContext.request.contextPath}"/>/generalservlet',
                    data: "METHOD=VALID_MODEL_CONDITION2&"+parametros,                  
                    success: function(cadena){
                                                //document.getElementById('imgProcesando').style.display =  "none";
                                                var r;
                                                
                                                if (cadena.length>1){
                                                    r = confirm(cadena);
                                                
                                                        if( r == true)
                                                                actualizarModeloCondicion();
                                                        else
                                                                return false;
                                                        
                                                }else{
                                                        grabarDatos();
                                                }
                                             }
                });
			
      }
      
      
    function actualizarModeloCondicion(){        
                
        //Recuperamos los datos del formulario
        //alert("actualizarModeloCondicion");
        var txtRequestId = document.getElementById("txtRequestId").value;        
        var txtCodigoEstado = document.getElementById("txtCodigoEstado").value;
        var cmbAcciones = document.getElementById("cmbAcciones").value;
        var hdnProductStatus = document.getElementById("hdnProductStatus").value;
        var strLogin='<%=strLogin%>'; 
        
        var txtNroFilas = document.getElementById("txtNroFilas").value;
        var i=0;
        var parametrosLista = "";
        var parametros= "";
        
        //obtnemos los valores IMEI's
        for(i=0; i < parseInt(txtNroFilas); i++){              
            parametrosLista = parametrosLista + "&" + "imei_num_"+i+"="+document.getElementById("imei_num_"+i).value;                             
        }
        
            //Recuperamos los parametros necesarios para grabar la solicitud
            parametros="strLogin="+strLogin+"&"+ "txtRequestId="+txtRequestId+"&"+
                        "txtCodigoEstado="+txtCodigoEstado+"&"+ "cmbAcciones="+cmbAcciones+ parametrosLista + "&" + 
                        "txtNroFilas="+txtNroFilas+ "&" +
                        "hdnProductStatus="+hdnProductStatus;
                        
            //alert(parametros);
            
            $.ajax({
                    cache:false,
                    type: "POST",   
                    url: '<c:out value="${pageContext.request.contextPath}"/>/generalservlet',
                    data: "METHOD=UPDATE_MODEL_CONDITION&"+parametros,                  
                    success: function(cadena){
                                document.getElementById('imgProcesando').style.display =  "none";
                                if(cadena != ""){
                                   alert(cadena);
								   return false;
                                }else{
								   grabarDatos()
								}
                             }
                   });
			return true;
      }
      
      function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;
 
         return true;
      }
      
      function validarListaIMEI(){
        var txtNroFilas = document.getElementById("txtNroFilas").value;              
        var i=0;
        if(txtNroFilas != "0"){
            for(i=0; i < parseInt(txtNroFilas); i++){            
                if(trim(document.getElementById("imei_num_"+i).value) == ""){
                    return false;
                }               
            }
            
        }else{
            return true;
        }
                
        return true;
      }
         
      function validar_Exits_IMEI(searchIMEI){
        var txtNroFilas = document.getElementById("txtNroFilas").value;              
        var i=0;
        if(txtNroFilas != "0"){
            for(i=0; i < parseInt(txtNroFilas); i++){            
                if(trim(document.getElementById("imei_num_"+i).value) == searchIMEI){
                    return true;
                }               
            }
        }
        return false;
      }      

    onLoadForm();
   
    $(document).ready(function () {  
        fxCargarInstalacion();
        
    $("#selMotivo").change(function () {
        if(fxValidarAgendamiento()){
            var motivoId = $(this).val();
            fxCargarSubMotivo(motivoId);
        }
    });

    $("#selSubMotivo").change(function () {
        if(fxValidarAgendamiento()){
            var subMotivoId = $(this).val();
            fxCargarDetalle(subMotivoId);
        }
    }); 
    });
      
    

    function fxValidarGrabarInstalacion(){
        var motivoId = $("#selMotivo").val();
        var subMotivoId = $("#selSubMotivo").val();
        var detalleId = $("#selDetalle").val();
        var detallesCant = $("#selDetalle option").length;
        if(fxValidarAgendamiento()){
            if(motivoId==""){
                alert("Debe seleccionar un motivo.");
                return false;
            }else if(subMotivoId==""){
                alert("Debe seleccionar un Submotivo.");
                return false;
            }else if(detallesCant>1){
                if(detalleId==""){
                    alert("Debe seleccionar un Detalle.");
                    return false;
                }
            }
        }
        return true;
    }
    
    function fxValidarAgendamiento(){
        var flagAgendamiento=$("#hdnFlagAgendamiento").val();
        if (flagAgendamiento=="true"){
           return true;
        }        
        return false;
    }
    
    function fxCargarInstalacion(){
        if(fxValidarAgendamiento()){
            fxCargarMotivos();            
            if(fxValidarInstalacionEdit()){
                $("#selMotivo").val("<%=instalacionBean.getMotivoId()%>");
                fxCargarSubMotivo($("#selMotivo").val());
                $("#selSubMotivo").val("<%=instalacionBean.getSubMotivoId()%>");
                fxCargarDetalle($("#selSubMotivo").val());
                $("#selDetalle").val("<%=instalacionBean.getDetalleId()%>");
                $("textarea#txtComentario").val("<%=instalacionBean.getComentario()%>");                 
            }
            $("#divInstalacion").show();
        }else{
            $("#divInstalacion").hide();
        }
    }
    
    
    function fxCargarMotivos() {
        var opcionesMotivo = $("#selMotivo option");
        var opcionesSubMotivo = $("#selSubMotivo option");
        var opcionesDetalle = $("#selDetalle option");
        opcionesMotivo.remove();
        opcionesSubMotivo.remove();
        opcionesDetalle.remove();
        baseActual = JSON.parse($("#hdnBaseActual").val());
        $("#selMotivo").append("<option value=''><\/option>");
        for (var i = 0; i < baseActual.motivos.length; i++) {
            var motivo = baseActual.motivos[i];
              if (motivo.estado == "a") {
                $("#selMotivo").append("<option value='" + motivo.id + "'>" + motivo.nombre + "<\/option>");
            }
        }  
    }
    
    function fxCargarSubMotivo(motivoId) {
        var opcionesSubMotivo = $("#selSubMotivo option");
        var opcionesDetalle = $("#selDetalle option");
        opcionesSubMotivo.remove();
        opcionesDetalle.remove();
        var motivo = fxBuscarMotivo(motivoId);
        $("#selSubMotivo").append("<option value=''><\/option>");
        
        if(motivoId!=""){
            if(motivo.submotivos!=null){
                for (var i = 0; i < motivo.submotivos.length; i++) {
                    var subMotivo = motivo.submotivos[i];
                    if (subMotivo.estado == "a") {
                        $("#selSubMotivo").append("<option value='" + subMotivo.id + "'>" + subMotivo.nombre + "<\/option>");
                    }
                }        
            }        
        }


    }

    function fxBuscarMotivo(motivoId) {
        baseActual = JSON.parse($("#hdnBaseActual").val());
        for (var i = 0; i < baseActual.motivos.length; i++) {
            var motivo = baseActual.motivos[i]
            if (motivoId == motivo.id) {
                return motivo;
            }
        }
        return null;
    }
    
    function fxCargarDetalle(subMotivoId) {
        var opcionesDetalle = $("#selDetalle option");
        opcionesDetalle.remove();
        $("#selDetalle").append("<option value=''><\/option>");
        var subMotivo = fxBuscarSubMotivo(subMotivoId);
        
        
        if(subMotivoId!=null && subMotivoId!=""){
            if(subMotivo.detalles!=null){
                for (var i = 0; i < subMotivo.detalles.length; i++) {
                    var detalle = subMotivo.detalles[i];
                    if (detalle.estado == "a") {
                        $("#selDetalle").append("<option value='" + detalle.id + "'>" + detalle.nombre + "<\/option>");
                    }
                }        
            }        
        }

    }    
    
    function fxBuscarSubMotivo(subMotivoId) {
        baseActual = JSON.parse($("#hdnBaseActual").val());
        for (var i = 0; i < baseActual.motivos.length; i++) {
            var motivo = baseActual.motivos[i]
            for (var j = 0; j < motivo.submotivos.length; j++) {
                var submotivo = motivo.submotivos[j];
                if (subMotivoId == submotivo.id) {
                    return submotivo;
                }
            }

        }
        return null;
    }
    
    function fxValidarInstalacionEdit(){
      var hdnFlagInstalacionEdit = $("#hdnFlagInstalacionEdit").val();   
      if(hdnFlagInstalacionEdit=="true"){
        return true;
      }else{
        return false;
      }
    }

    function validarRegularizarOrdenOutdoor(){
        var respuestaVal = false;
        var indiceImei = $("#item_imei_radio").val();
        var idReqItem = "#txtReqOlItemId"+indiceImei;
        var reqOlItemId = $(idReqItem).val();
        var imei = $("#txtImeis").val();
        var numOrden = $("#lblNumOrden").html();

        var url_server = "${pageContext.request.contextPath}/requestservlet";
        var parametros = "ordenId="+numOrden+
                         "&imei="+imei+
                         "&reqOlItemId"+reqOlItemId+
                         "&METHOD=VALIDAR_REGULARIZAR_ORDEN_OUTDOOR";
        jQuery.ajax({
            type: "POST",
            url: url_server,
            data: parametros,
            dataType: "json",
            async: false,
            cache: false,
            success: function (response) {
                
                if (response.errorCodigo == "0") {
                    respuestaVal = true;
                }
                if(response.errorCodigo=="-1" || response.errorCodigo=="2" ){
                   var mensaje = response.errorMensaje;
                   respuestaVal = false;
                                
                }
                if(response.errorCodigo=="1"){
                    var mensaje = response.errorMensaje;
                    mensaje = mensaje.replace(/\|/g, "\n");
                    var respuesta = confirm(mensaje);
                    if(respuesta){
                       regularizarOrdenOutdoor(response.almacenId);
                       respuestaVal = true;
                    }else{
                       respuestaVal = false;
                    }
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                fxValidarErroresAjax(jqXHR, textStatus);
                respuestaVal = false;
      
            }
        }); 
        return respuestaVal;
    }

    function regularizarOrdenOutdoor(imei,almacenId){
        var creadoPor ='<%=strLogin%>'; 
        var imei = $("#txtImeis").val();
        var numOrden = $("#lblNumOrden").html();
        var url_server = "${pageContext.request.contextPath}/requestservlet";
        var parametros = "ordenId="+numOrden+
                         "&imei="+imei+
                         "&almacenId="+almacenId+
                         "&creadoPor="+creadoPor+
                         "&reqOlItemId"+reqOlItemId+
                         "&METHOD=REGULARIZAR_ORDEN_OUTDOOR";
        jQuery.ajax({
            type: "POST",
            url: url_server,
            data: parametros,
            dataType: "json",
            async: false,
            cache: false,
            success: function (response) {
                if (response.errorCodigo == "0") {
                    alert("Se actualizó orden correctamente.");                   
                }else{
                    alert(response.errorMensaje);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                fxValidarErroresAjax(jqXHR, textStatus);
      
            }
        });    
    }
    
    function fxValidarErroresAjax(jqXHR, textStatus) {
        if (jqXHR.status === 0) {
            alert("No hay conexión, verificar.");
        } else if (jqXHR.status == 404) {
            alert("Página no encontrada [404].");
        } else if (jqXHR.status == 500) {
            alert("Error Interno [500].");
        } else if (textStatus === 'parsererror') {
            alert("Error de conversion JSON");
        } else if (textStatus === 'timeout') {
            alert("Tiempo de esperado excedido.");
        } else if (textStatus === 'abort') {
            alert("Solicitud Ajax abortada");
        } else {
            alert("Error inesperado: " + jqXHR.responseText);

        }
    }   
   
</script>
  
</html>


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