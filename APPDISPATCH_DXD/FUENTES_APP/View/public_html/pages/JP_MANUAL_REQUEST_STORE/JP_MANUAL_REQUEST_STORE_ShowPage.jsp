<%@ page contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="pe.com.nextel.bean.DominioBean" %>
<%@ page import="pe.com.nextel.service.RequestManualService" %>
<%@ page import="pe.com.nextel.util.Constante" %>
<%@ page import="pe.com.nextel.servlet.ManualRequestStoreServlet" %>
<%@page import="pe.com.nextel.bean.PortalSessionBean" %>
<%@page import="pe.com.nextel.exception.SessionException" %>
<%@page import="pe.com.nextel.service.SessionService"%>
<%@ page import="pe.com.nextel.util.MiUtil" %>
<%@ page import="oracle.portal.provider.v2.render.PortletRenderRequest" %>
<%@ page import="oracle.portal.provider.v2.http.HttpCommonConstants" %>
<%@ page import="oracle.portal.provider.v2.ProviderUser" %>
<%@page import="oracle.portal.provider.v2.ProviderSession" %>
<%@page import="oracle.portal.provider.v2.render.PortletRendererUtil" %>
<%@page import="oracle.portal.provider.v2.render.http.HttpPortletRendererUtil" %>
<%@page import="pe.com.nextel.bean.PortalSessionBean" %>
<%@page import="pe.com.nextel.exception.SessionException" %>
<%@page import="pe.com.nextel.service.SessionService"%>


<%
  
try{
          
          
  //String strSessionSearchRequestId = "655718738877632405544903018086691539998102659";
  String strSessionSearchRequestId = "";
          

  try{
    PortletRenderRequest pReq = (PortletRenderRequest) request.getAttribute(HttpCommonConstants.PORTLET_RENDER_REQUEST);
    ProviderUser objetoUsuario1 = pReq.getUser();
    strSessionSearchRequestId=objetoUsuario1.getPortalSessionId();
    System.out.println("Sesión capturada  JP_MANUAK_REQUEST_STORE_ShowPage : " + objetoUsuario1.getName() + " - " + strSessionSearchRequestId );
  }catch(Exception e){
    System.out.println("Portler Not Found : " + e.getClass() + " - " + e.getMessage() );
    out.println("Portlet JP_MANUAK_REQUEST_STORE_ShowPage Not Found");
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
      String usuario = "";
      String tienda = "";
          
      iUserId  = portalSessionBean3.getUserid();
      iAppId   = portalSessionBean3.getAppId();  
      tienda = portalSessionBean3.getTienda();
          
      idbuilding = portalSessionBean3.getBuildingid();
      usuario = portalSessionBean3.getLogin();
      
      request.setAttribute("strUsuario", usuario );    
      
      System.out.println("iUserId:"+iUserId);
      System.out.println("iAppId:"+iAppId);      
      System.out.println("idbuilding:"+idbuilding);
      System.out.println("strUsuario:"+usuario);

      new ManualRequestStoreServlet().iniciarPagina(request, response); 

%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Solicitud Manual a Alamcen</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/websales/Resource/salesweb.css"></link>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/websales/Resource/displaytag.css"/></link>
    <!--<script type="text/javascript" src="<%=request.getContextPath()%>/websales/Resource/jQuery-min.js"></script>-->
    <script type="text/javascript" src="<%=request.getContextPath()%>/websales/Resource/library.js"></script>   
    <link rel="stylesheet" href="<%=request.getContextPath()%>/websales/Resource/jquery-ui.css"/></link>
    <script type="text/javascript" src="<%=request.getContextPath()%>/websales/Resource/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/websales/Resource/jquery-ui.js"></script>

    
    <script type="text/javascript">
    
      function fxSearchDocument(npnumerationid){
            
            url = "../JP_SEARCH_COMPANY/searchcompanyservlet?METHOD=INI_PAGE"
            WinAsist = window.open(url,"WinAsist","toolbar=no,location=0,directories=no,status=yes,menubar=0,scrollbars=yes,resizable=no,screenX=100,top=80,left=100,screenY=80,width=800,height=400,modal=yes");
      }
      
             
       function nuevaSolicitud(){
       
            var formulario = document.frmdatos;
                         
            var txtNroOrden = document.getElementById("txtNroOrden").value;            
            var cboTipoDocumento = document.getElementById("cboTipoDocumento").value;
            var txtNroDocumento = document.getElementById("txtNroDocumento").value;
            var txtCodigo = document.getElementById("txtNumber").value;
            var txtRuc = document.getElementById("txtRuc").value;
            var txtCustomerName = document.getElementById("txtNameCompania").value;            
            var txtCodBscs = document.getElementById("txtBscs").value;
            var cboDireccionEntrega = document.getElementById("cboDireccion").value;
            var txtObservaciones = document.getElementById("txtObservaciones").value;
            
            var cboMotivoDetalle = document.getElementById("cboMotivoDetalle").value;
            var cboModalidad = document.getElementById("cboModalidad").value;
            var cboEstado = document.getElementById("cboEstado").value;
            var cboAccesorio = document.getElementById("cboAccesorio").value;
            var cboSolucionNegocio = document.getElementById("cboSolucionNegocio").value;            
            var cboLineaProducto = document.getElementById("cboLineaProducto").value;
            var cboProducto = document.getElementById("cboProducto").value;            
            var cboRepuestos = document.getElementById("cboRepuestos").value;
            var txtCantidad = document.getElementById("txtCantidad").value;
            
            var txtUsuario = "<%=usuario%>";            
            var idbuilding = "<%=idbuilding%>"
             
            var parametros = "txtNroOrden="+txtNroOrden+"&cboTipoDocumento="+cboTipoDocumento+
                             "&txtNroDocumento="+txtNroDocumento+"&txtCodigo="+txtCodigo+
                             "&txtRuc="+txtRuc+"&txtCustomerName="+txtCustomerName+
                             "&txtCodBscs="+txtCodBscs+"&cboDireccionEntrega="+cboDireccionEntrega+
                             "&txtObservaciones="+txtObservaciones+"&cboMotivoDetalle="+cboMotivoDetalle+
                             "&cboModalidad="+cboModalidad+"&cboEstado="+cboEstado+"&cboAccesorio="+cboAccesorio+
                             "&cboSolucionNegocio="+cboSolucionNegocio+"&cboLineaProducto="+cboLineaProducto+
                             "&cboProducto="+cboProducto+"&cboRepuestos="+cboRepuestos+"&txtCantidad="+txtCantidad+
                             "&txtUsuario="+txtUsuario+"&idbuilding="+idbuilding;
                             
            //Obtenemos los parametros de la tabla
            var tblProductos =  document.getElementById("tblProductos");
            var filasTB = document.getElementById("tblProductos").rows.length;
            var paramLista = "";
            
            //alert("filasTB = "+filasTB);
            
            if(filasTB > 1){
                //Restamos uno por la cabecera de la tabla
                filasTB = filasTB - 1;
                
                if(filasTB > 1){
                    
                    for(var i=0; i<filasTB; i++){
                        paramLista += "&hdnIdProducto_"+i+"="+document.frmdatos.hdnIdProducto[i].value;
                        paramLista += "&hdnDescripcionProducto_"+i+"="+document.frmdatos.hdnDescripcionProducto[i].value;
                        paramLista += "&hdnIdRepuesto_"+i+"="+document.frmdatos.hdnIdRepuesto[i].value;
                        paramLista += "&hdnDescripcionRepuesto_"+i+"="+document.frmdatos.hdnDescripcionRepuesto[i].value;
                        paramLista += "&hdnIdMotivo_"+i+"="+document.frmdatos.hdnIdMotivo[i].value;
                        paramLista += "&hdnDescripcionMotivo_"+i+"="+document.frmdatos.hdnDescripcionMotivo[i].value;
                        paramLista += "&hdnIdModalidad_"+i+"="+document.frmdatos.hdnIdModalidad[i].value;
                        paramLista += "&hdnDescripcionModalidad_"+i+"="+document.frmdatos.hdnDescripcionModalidad[i].value;
                        paramLista += "&hdnIdEstado_"+i+"="+document.frmdatos.hdnIdEstado[i].value;
                        paramLista += "&hdnDescripcionEstado_"+i+"="+document.frmdatos.hdnDescripcionEstado[i].value;
                        paramLista += "&hdnIdAccesorio_"+i+"="+document.frmdatos.hdnIdAccesorio[i].value;
                        paramLista += "&hdnDescripcionAccesorio_"+i+"="+document.frmdatos.hdnDescripcionAccesorio[i].value;
                        paramLista += "&hdnCantidad_"+i+"="+document.frmdatos.hdnCantidad[i].value;
                        paramLista += "&hdnCodigoSolucionNegocio_"+i+"="+document.frmdatos.hdnCodigoSolucionNegocio[i].value;
                        paramLista += "&hdnDescripcionSolucionNegocio_"+i+"="+document.frmdatos.hdnDescripcionSolucionNegocio[i].value;
                        
                        paramLista += "&hdnCodigoLineaProducto_"+i+"="+document.frmdatos.hdnCodigoLineaProducto[i].value;
                        paramLista += "&hhdnDescripcionLineaProducto_"+i+"="+document.frmdatos.hdnDescripcionLineaProducto[i].value;
                                              
                    }
                    
                    
                }else{
                    
                    paramLista += "&hdnIdProducto_0="+document.getElementById("hdnIdProducto").value;
                    paramLista += "&hdnDescripcionProducto_0="+document.getElementById("hdnDescripcionProducto").value;
                    paramLista += "&hdnIdRepuesto_0="+document.getElementById("hdnIdRepuesto").value;
                    paramLista += "&hdnDescripcionRepuesto_0="+document.getElementById("hdnDescripcionRepuesto").value;
                    paramLista += "&hdnIdMotivo_0="+document.getElementById("hdnIdMotivo").value;
                    paramLista += "&hdnDescripcionMotivo_0="+document.getElementById("hdnDescripcionMotivo").value;
                    paramLista += "&hdnIdModalidad_0="+document.getElementById("hdnIdModalidad").value;
                    paramLista += "&hdnDescripcionModalidad_0="+document.getElementById("hdnDescripcionModalidad").value;
                    paramLista += "&hdnIdEstado_0="+document.getElementById("hdnIdEstado").value;
                    paramLista += "&hdnDescripcionEstado_0="+document.getElementById("hdnDescripcionEstado").value;
                    paramLista += "&hdnIdAccesorio_0="+document.getElementById("hdnIdAccesorio").value;
                    paramLista += "&hdnDescripcionAccesorio_0="+document.getElementById("hdnDescripcionAccesorio").value;
                    paramLista += "&hdnCantidad_0="+document.getElementById("hdnCantidad").value;
                    paramLista += "&hdnCodigoSolucionNegocio_0="+document.getElementById("hdnCodigoSolucionNegocio").value;
                    paramLista += "&hdnDescripcionSolucionNegocio_0="+document.getElementById("hdnDescripcionSolucionNegocio").value;
                    
                    paramLista += "&hdnCodigoLineaProducto_0="+document.getElementById("hdnCodigoLineaProducto").value;
                    paramLista += "&hdnDescripcionLineaProducto_0="+document.getElementById("hdnDescripcionLineaProducto").value;
                     
                
                }
                
                
                
                //alert("paramLista = " + paramLista);
                parametros += paramLista;   
                parametros += "&txtNroFilas="+filasTB;   
                //alert("parametros = " + parametros);
                  
                $.ajax({
                    cache:false,
                    type: "POST",  
                    url: '<c:out value="${pageContext.request.contextPath}"/>/manualrequeststore',  
                    data: "METHOD=NEW_REQUEST_MANUAL&"+parametros,                  
                    success: function(cadena){
                                
                        var mensaje="";
                        var idSolicitud="";
                        var posInicio =0;
                      
                        if(cadena.indexOf('|') != -1){
                            posInicio = cadena.indexOf('|');
                            mensaje = cadena.slice(0,posInicio);
                        
                            cadena = cadena.substr(posInicio+1);                   
                            idSolicitud = cadena.slice(0,posInicio);
                        
                            alert(mensaje);
                        
                            $('#panelIdSolicitud').html(idSolicitud);
                            
                            document.getElementById("btnNuevaSolicitud").disabled=true;
                            
                        }else{
                            alert(cadena);
                        }
                    }                
                }); 
                        
            }else{
                alert("Debe ingresar al menos un item");
            }
            
          
       }
       
       
      
      /* Busca datos del Cliente */
   function fxSearchCustomer(){
      if (bCompany){
         url = "/portal/pls/portal/!INVENTORY.NP_INVENTORY_PL_PKG.PL_COMPANY_SEARCH?av_customername="+document.frmdatos.txtNameCompania.value;
         url = "/portal/pls/portal/websales.npsl_general_pl_pkg.window_frame?av_title=" + escape("Búsqueda de compañía") + "&av_url=" + escape(url);
         WinAsist = window.open(url,"WinAsist","toolbar=no,location=0,directories=no,status=yes,menubar=0,scrollbars=no,resizable=no,screenX=100,top=80,left=100,screenY=80,width=700,height=600,modal=yes");
      }
   }
   
   /* Realiza validacion de Cliente */
   function fxValidateCustomer(v_origen){
      var frmdatos          = document.frmdatos;
      var txtNameCompania   = frmdatos.txtNameCompania;
      var txtRuc            = frmdatos.txtRuc;
      var txtNumber         = frmdatos.txtNumber;
      var txtBscs           = frmdatos.txtBscs;
      var v_hacer_busqueda  = false;

      if (v_origen == "CUST_NAME"){
         if(txtNameCompania.value != "" )
         {
            v_hacer_busqueda      = true;
            txtNumber.value       = "";
            txtRuc.value          = "";
         }
         else
            if(txtNameCompania.value == "")
            {
                  txtNumber.value   = "";
                  txtRuc.value      = "";
                  txtBscs.value     = "";
                  LimpiarCombo(document.frmdatos.cboDireccion);
            }
      }

      if(v_origen == "CUST_RUC"){
         if(txtRuc.value != "" ){
            v_hacer_busqueda      = true;
            txtNumber.value       = "";
            txtNameCompania.value = "";
            }
         else
            if(txtRuc.value == ""){
               txtNumber.value         = "";
               txtNameCompania.value   = "";
               txtBscs.value           = "";
               LimpiarCombo(document.frmdatos.cboDireccion);
            }
      }

      if(v_origen == "CUST_NUMBER"){
         if(txtNumber.value != "" ){
            v_hacer_busqueda      = true;
            txtRuc.value          = "";
            txtNameCompania.value = "";
            }
         else
            if(txtNumber.value == ""){
               txtRuc.value            = "";
               txtNameCompania.value   = "";
               txtBscs.value           = "";
               LimpiarCombo(document.frmdatos.cboDireccion);
            }
      }

      if(v_origen == "CUST_BSCS"){
         if(txtBscs.value != "" ){
               if (txtNumber.value==""){
                  v_hacer_busqueda      = true;
                  txtNumber.value       = "";
                  txtRuc.value          = "";
                  txtNameCompania.value = "";
               }
            }
         else
            if(txtBscs.value == ""){
               txtNumber.value         = "";
               txtRuc.value            = "";
               txtNameCompania.value   = "";
               LimpiarCombo(document.frmdatos.cboDireccion);
            }
      }

      if (v_hacer_busqueda == true){
           var url = "/portal/pls/portal/!INVENTORY.NP_INVENTORY_PL_PKG.PL_CUSTOMER_VALIDATE"
                     + "?txtNumber=" + txtNumber.value
                     + "&txtRuc=" + txtRuc.value
                     + "&txtNameCompania=" + txtNameCompania.value
                     + "&txtCustCode=" + txtBscs.value;
           parent.bottomFrame.location.replace(url);
        }
     }
      
      
      function LimpiarCombo(thecombo){
         var selLength =thecombo.length;
         if (selLength>0){
              for (var j=1;j<selLength+1;j++){
                thecombo.remove(selLength - j);
             }
            }
         if (thecombo.name != "cbomotivo"){
            oOption = document.createElement("OPTION");
            oOption.value="";
            oOption.text="";
            thecombo.add(oOption);
         }
       }
  
    
    
       
    function isPar(numero){
      if(numero % 2 == 0){        
        return true;
      }else{        
        return false;
      }
    }
    
    /**************************************************************************************************/
    /*$(function() {
      $( "#dialog-modal" ).dialog({
        height: 140,
        modal: true
      });
    }); */
    
    var indice=0;
    function agregarLista(){
      var strBuilding = "<%=idbuilding%>";
      
      var codigoProducto = $('#cboProducto').val();
      var descripcionProducto = $('#cboProducto option:selected').text();
      
      var codigoLineaProducto = $('#cboLineaProducto').val();
      var descripcionLineaProducto = $('#cboLineaProducto option:selected').text();
              
      var codigoSolucionNegocio = $('#cboSolucionNegocio').val();
      var descripcionSolucionNegocio = $('#cboSolucionNegocio option:selected').text();
            
      var codigoRepuesto = $('#cboRepuestos').val();
      var descripcionRepuesto = $('#cboRepuestos option:selected').text();
      
      var codigoMotivo = $('#cboMotivoDetalle').val();
      var descripcionMotivo = $('#cboMotivoDetalle option:selected').text();
      
      var codigoModalidad =  $('#cboModalidad').val();
      var descripcionModalidad =  $('#cboModalidad option:selected').text();
      
      var codigoEstado = $('#cboEstado').val();
      var descripcionEstado = $('#cboEstado option:selected').text();
      
      var codigoAccesorio = $('#cboAccesorio').val();
      var descripcionAccesorio = $('#cboAccesorio option:selected').text();
      
      var cantidad = $('#txtCantidad').val();
      
      //VARIABLES PARA LA API VALIDA STOCK
      var parametros = "";
      var an_specificationid = "";
      var an_productid = "";
      var an_npdispatchplaceid = "";
      var av_organizationoperation ="";
      var an_salesstructporigenid = "";
      var av_pro_status = "";
      
      //Si el codigo del accesorio/repuesto es diferente de vacio entonces se ha seleccionado un accesorio
      an_specificationid = "1000";      
      if(trim(codigoRepuesto) != ""){
          an_productid = codigoRepuesto;
      }else{
          an_productid = codigoProducto;
      }
      an_npdispatchplaceid = strBuilding;
      av_organizationoperation = codigoModalidad;
      an_salesstructporigenid = "0";
      av_pro_status= codigoEstado; 
           
      
      parametros += "an_specificationid=" + an_specificationid; 
      parametros += "&" + "an_productid=" + an_productid;
      parametros += "&" + "an_npdispatchplaceid=" + an_npdispatchplaceid;
      parametros += "&" + "av_organizationoperation=" + av_organizationoperation;
      parametros += "&" + "an_salesstructporigenid=" + an_salesstructporigenid;   
      parametros += "&" + "av_pro_status=" + av_pro_status;   
             
      
      var tds = "";
      
      if(validarDatosDetalleSolicitud()){
      
          $.ajax({
                cache:false,
                type: "POST",  
                url: '<c:out value="${pageContext.request.contextPath}"/>/manualrequeststore',  
                data: "METHOD=VALIDAR_ALMACEN&" + parametros,                  
                success: function(cadena){
                    var flagStock = "";
                    var mensaje = "";
                    var posInicio = 0;
                    
                    if(cadena.indexOf('|') != -1){
                        posInicio = cadena.indexOf('|');
                        flagStock = cadena.slice(0,posInicio);
                        
                        mensaje = cadena.substr(posInicio+1);   
                        
                        tds += '<tr id="tr_'+indice+'">';
                        tds += '<td>';          
                        tds +=  descripcionProducto +'<input type="hidden" name="hdnIdProducto" id="hdnIdProducto" value="'+codigoProducto+'"/><input type="hidden" name="hdnDescripcionProducto" id="hdnDescripcionProducto" value="'+descripcionProducto+'"/>';
                        tds += '<input type="hidden" name="hdnCodigoSolucionNegocio" id="hdnCodigoSolucionNegocio" value="'+codigoSolucionNegocio+'"/>';
                        tds += '<input type="hidden" name="hdnDescripcionSolucionNegocio" id="hdnDescripcionSolucionNegocio" value="'+descripcionSolucionNegocio+'"/>';          
                
                        tds += '<input type="hidden" name="hdnCodigoLineaProducto" id="hdnCodigoLineaProducto" value="'+codigoLineaProducto+'"/>';         
                        tds += '<input type="hidden" name="hdnDescripcionLineaProducto" id="hdnDescripcionLineaProducto" value="'+descripcionLineaProducto+'"/>'; 
                           
                        tds += '<\/td>';
                        tds += '<td>'+ descripcionRepuesto +'<input type="hidden" name="hdnIdRepuesto" id="hdnIdRepuesto" value="'+codigoRepuesto+'"/><input type="hidden" name="hdnDescripcionRepuesto" id="hdnDescripcionRepuesto" value="'+descripcionRepuesto+'"/><\/td>';
                        tds += '<td>'+ descripcionMotivo +'<input type="hidden" name="hdnIdMotivo" id="hdnIdMotivo" value="'+codigoMotivo+'"/><input type="hidden" name="hdnDescripcionMotivo" id="hdnDescripcionMotivo" value="'+descripcionMotivo+'"/><\/td>';
                        tds += '<td>'+ descripcionModalidad +'<input type="hidden" name="hdnIdModalidad" id="hdnIdModalidad" value="'+codigoModalidad+'"/><input type="hidden" name="hdnDescripcionModalidad" id="hdnDescripcionModalidad" value="'+descripcionModalidad+'"/><\/td>';
                        tds += '<td>'+ descripcionEstado +'<input type="hidden" name="hdnIdEstado" id="hdnIdEstado" value="'+codigoEstado+'"/><input type="hidden" name="hdnDescripcionEstado" id="hdnDescripcionEstado" value="'+descripcionEstado+'"/><\/td>';
                        tds += '<td>'+ descripcionAccesorio +'<input type="hidden" name="hdnIdAccesorio" id="hdnIdAccesorio" value="'+codigoAccesorio+'"/><input type="hidden" name="hdnDescripcionAccesorio" id="hdnDescripcionAccesorio" value="'+descripcionAccesorio+'"/><\/td>';
                        tds += '<td>'+ cantidad +'<input type="hidden" name="hdnCantidad" id="hdnCantidad" value="'+cantidad+'"/><\/td>';
                        tds += '<td><input type="button" value="Eliminar" onclick="eliminarFila('+indice+')"><\/td>';
                        tds += '<\/tr>';
                    
                        if(flagStock == "0"){
                            $("#tblProductos").append(tds);                        
                            indice++;                             
                        }
                        if(flagStock == "1"){
                            if(confirm(mensaje)){
                                $("#tblProductos").append(tds);                        
                                indice++;
                            }                                                     
                        }
                        if(flagStock == "2"){
                            alert(mensaje);
                        }
                        
                    }else{
                        alert(cadena);                        
                    }                       
                        
                }   
                                    
          }); 
          
          
                                   
      }       
    }
      
    
    function eliminarFila(filaEliminar){          
         //var fila = parseInt(filaEliminar) + 1;
         //alert("fila = " + fila);
         //document.getElementById("tblProductos").deleteRow(fila);  
         $('#tr_' + filaEliminar).remove();         
         indice--;
    }
    
    /*************************************************************************/
    
    /*
    function inicializarListaDetailRequestManual(){
      
        
      $.ajax({  
          type: "POST",  
          url: '<c:out value="${pageContext.request.contextPath}"/>/manualrequeststore',   
          data: "METHOD=INI_LIST_DETAIL_REQUEST_MANUAL",
          success: function(tabla){                           
            //$('#listaProducto').html(tabla);            
          }                
        }); 
        
    }
    */
    
    function reFresh() {
      location.reload(true);      
    }
    
    function validarDatosDetalleSolicitud(){
      var idMotivoDetalle = document.getElementById("cboMotivoDetalle");
      var idModalidad = document.getElementById("cboModalidad");
      var idEstado = document.getElementById("cboEstado");
      var idAccesorio = document.getElementById("cboAccesorio");
      var idSolucionNegocio = document.getElementById("cboSolucionNegocio");
      var idLineaProducto = document.getElementById("cboLineaProducto");
      var idProducto = document.getElementById("cboProducto");
      var cantidad = document.getElementById("txtCantidad");
      var cboRepuestos = document.getElementById("cboRepuestos");
      
      var hdnFlagValidaProducto = document.getElementById("hdnFlagValidaProducto");
      var hdnFlagValidaAccesorio = document.getElementById("hdnFlagValidaAccesorio");
            
      if(idMotivoDetalle.value == ""){
        alert("Debe seleccionar un motivo");
        idMotivoDetalle.focus();
        return false;
      }
      if(idModalidad.value == ""){
        alert("Debe seleccionar una modalidad");
        idModalidad.focus();
        return false;
      }
      if(idEstado.value == ""){
        alert("Debe seleccionar un estado");
        idEstado.focus();
        return false;
      }
      if(idAccesorio.value == ""){
        alert("Debe seleccionar un accesorio/repuesto");
        idAccesorio.focus();
        return false;
      }
      if(idSolucionNegocio.value == ""){
        alert("Debe seleccionar una solición de negocio");
        idSolucionNegocio.focus();
        return false;
      }
      if(idLineaProducto.value == ""){
        alert("Debe seleccionar una linea de producto");
        idLineaProducto.focus();
        return false;
      }
      
      //alert("hdnFlagValidaProducto = " + hdnFlagValidaProducto.value);      
      if(hdnFlagValidaProducto.value == "1"){
          if(idProducto.value == ""){
            alert("Debe seleccionar un producto");        
            idProducto.focus();
            return false;
          }
      }
      
      //Validamos el combo accesorio/repuesto
      //alert("hdnFlagValidaAccesorio = " + hdnFlagValidaAccesorio.value);
      if(hdnFlagValidaAccesorio.value == "1"){
          if(cboRepuestos.value == ""){
            alert("Debe seleccionar un Accesorio/Repuesto");
            cboRepuestos.focus();
            return false;
          }          
      }
      
      if(trim(cantidad.value) == ""){
        alert("Debe ingresar una cantidad");
        cantidad.value ="";
        cantidad.focus();
        return false;
      }
      
      return true;
    }
    
    function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
       
         if (charCode > 31 && (charCode < 48 || charCode > 57)){
            return false;
          }
 
         return true;
    }
    
    
    function fxLoadGeneral() {
           v_form = document.frmdatos;
           if (v_form.hdnSearchType.value == "1"){
                fxSearchOrder();
           }              
    }
    
    function fxSearchOrder() {
          var form = document.frmdatos;
          form.method="POST";
          form.action = "<c:out value="${pageContext.request.contextPath}"/>/Search.do";  
          form.submit();
        }
    
    
    function obtenerRepuestos(){
      var idSolucionNegocio =  document.getElementById("cboSolucionNegocio").value;
       
       $.ajax({  
          type: "POST",  
          url: "manualrequeststore",  
          data: "METHOD=GET_PRODUCTO_BY_LINEA&ID_LINEA="+idLineaProducto,                  
          success: function(options){  
          //alert(options);
          $('#cboProducto').html(options)                
          }                
      });
      
    }
      
    function obtenerLineaNegocio(){
      var idSolucionNegocio =  document.getElementById("cboSolucionNegocio").value;
      var idModalidad =  document.getElementById("cboModalidad").value;
      
      //var idLineaProducto =  document.getElementById("cboLineaProducto").value;      
      //var idProducto =  document.getElementById("cboProducto").value;
      
      
       $('#cboLineaProducto').html("<option value=''>Cargando datos...<\/option>");   
       $('#cboProducto').html("<option value=''><\/option>"); 
       $('#cboRepuestos').html("<option value=''><\/option>");
      
       
            $.ajax({  
                type: "POST",  
                url: '<c:out value="${pageContext.request.contextPath}"/>/manualrequeststore',  
                data: 'METHOD=GET_LINEA_PRODUCTO_BY_SOLUCION&ID_SOLUCION='+idSolucionNegocio+'&ID_MODALIDAD='+idModalidad,  //+'&ID_LINEPRODUCTO='+idLineaProducto+'&ID_PRODUCTO='+idProducto,
                contentType: "application/x-www-form-urlencoded; charset=iso-8859-1",
                success: function(cadena){  
                  //alert(options);
                  //  var optionSolucion = "";
                    //var optionProducto = "";
                    //var optionAccesorio = "";
                  
                    //var posInicio = cadena.indexOf('|');
                    //optionSolucion = cadena.slice(0,posInicio);
                    
                    //cadena = cadena.substr(posInicio+1);                   
                    //optionProducto = cadena.slice(0,posInicio);
                    
                    //posInicio = cadena.indexOf('|');
                    //cadena = cadena.substr(posInicio+1); 
                    //optionAccesorio = cadena.slice(0,posInicio);
                                                     
                    $('#cboLineaProducto').html(cadena);               
                    //$('#cboProducto').html(optionProducto);
                    //$('#cboRepuestos').html(optionAccesorio);
                    
            }                
          }); 
    }    
       
    
    function obtenerProductos(){
       
        var idLineaProducto = $('#cboLineaProducto').val();        
        var idModalidad =  document.getElementById("cboModalidad").value;  
        
        //var idProducto = document.getElementById("cboProducto").value; 
       
       $('#cboProducto').html("<option value=''>Cargando datos....<\/option>");
       $('#cboRepuestos').html("<option value=''>Cargando datos....<\/option>");
       
            $.ajax({  
                type: "POST",  
                url: '<c:out value="${pageContext.request.contextPath}"/>/manualrequeststore',  
                data: 'METHOD=GET_PRODUCTO_BY_LINEA&ID_LINEAPRODUCTO='+idLineaProducto+'&ID_MODALIDAD='+idModalidad, //+'&ID_PRODUCTO='+idProducto,                  
                success: function(cadena){  
                    //alert(cadena)                                                        
                    var optionProducto = "";
                    var optionAccesorio = "";
                    
                    var flagValidaProducto = "";
                    var flagValidaAccesorio = "";
                                                           
                    var posInicio = cadena.indexOf('|');
                    optionProducto = cadena.slice(0,posInicio);
                                        
                    cadena = cadena.substr(posInicio+1);   
                    posInicio = cadena.indexOf('|');
                    optionAccesorio = cadena.slice(0,posInicio);
                    
                    //alert("--cadena = " + cadena);
                    posInicio = cadena.indexOf('|');
                    //alert("--posInicio = " + posInicio);
                    cadena = cadena.substr(posInicio+1);
                    //alert("-->cadena = " +cadena);
                    posInicio = cadena.indexOf('|');
                    flagValidaProducto = cadena.slice(0,posInicio);
                    //alert("flagValidaProducto = " + flagValidaProducto);
                                        
                    cadena = cadena.substr(posInicio+1);                    
                    flagValidaAccesorio = cadena.slice(0,posInicio);
                    //alert("flagValidaAccesorio = " + flagValidaAccesorio);
                                        
                    document.getElementById("hdnFlagValidaProducto").value = flagValidaProducto;
                    document.getElementById("hdnFlagValidaAccesorio").value = flagValidaAccesorio;
                                                     
                    $('#cboProducto').html(optionProducto);
                    
                    if(trim(optionAccesorio) != ""){
                        $('#cboRepuestos').html(optionAccesorio);
                        document.getElementById("cboRepuestos").disabled =  false;
                        
                    }else{
                        $('#cboRepuestos').html("<option value=''><\/option>");
                        //document.getElementById("cboRepuestos").disabled =  true;
                    }
                    
            }                
          }); 
      
    } 
     
     function obtnerAccesoriosRepuesto(){
        
        var idModalidad =  document.getElementById("cboModalidad").value; 
        var idLineaProducto = document.getElementById("cboLineaProducto").value; 
        var idProducto = document.getElementById("cboProducto").value; 
        
       $('#cboRepuestos').html("<option value=''>Cargando datos...<\/option>");   
       
       
            $.ajax({  
                type: "POST",  
                url: '<c:out value="${pageContext.request.contextPath}"/>/manualrequeststore',  
                data: 'METHOD=OBTENER_ACCESORIOS&ID_MODALIDAD='+idModalidad+'&ID_LINEAPRODUCTO='+idLineaProducto+"&ID_PRODUCTO="+idProducto,
                contentType: "application/x-www-form-urlencoded; charset=iso-8859-1",
                success: function(option){ 
                    //alert("option = " + option);
                    if(trim(option) != ""){
                        $('#cboRepuestos').html(option);
                        document.getElementById("cboRepuestos").disabled =  false;
                        
                    }else{
                        $('#cboRepuestos').html("<option value=''><\/option>");  
                        //document.getElementById("cboRepuestos").disabled =  true;
                    }
                }                
          }); 
      }
  
      function obtnerAccesoriosAndProductos(){
        
        var idModalidad =  document.getElementById("cboModalidad").value; 
        var idLineaProducto = document.getElementById("cboLineaProducto").value; 
        
        //Inicializamos el combo solucion de negocio con el primer valor
        document.getElementById("cboSolucionNegocio")[0].selected =true;
        //document.getElementById("cboLineaProducto")[0].selected =true;
        
       $('#cboLineaProducto').html("<option value=''><\/option>");   
       $('#cboRepuestos').html("<option value=''><\/option>");  
       $('#cboProducto').html("<option value=''><\/option>");
       
       
           /* $.ajax({  
                type: "POST",  
                url: '<c:out value="${pageContext.request.contextPath}"/>/manualrequeststore',  
                data: 'METHOD=OBTENER_ACCESORIOS_AND_PRODUCTOS&ID_MODALIDAD='+idModalidad+'&ID_LINEAPRODUCTO='+idLineaProducto,   //+"&ID_PRODUCTO="+idProducto,
                contentType: "application/x-www-form-urlencoded; charset=iso-8859-1",
                success: function(cadena){ 
                    
                    var optionAccesorio = "";
                    var optionProducto = "";
                    
                  
                    var posInicio = cadena.indexOf('|');
                    optionAccesorio = cadena.slice(0,posInicio);
                    
                    cadena = cadena.substr(posInicio+1);                   
                    optionProducto = cadena.slice(0,posInicio);
                    
                    $('#cboProducto').html(optionProducto);
                    
                    if(trim(optionAccesorio) != "0"){
                        $('#cboRepuestos').html(optionAccesorio);
                        document.getElementById("cboRepuestos").disabled =  false;
                        
                    }else{
                        $('#cboRepuestos').html("<option value=''><\/option>");  
                        document.getElementById("cboRepuestos").disabled =  true;
                    }
                }                
          }); */
      }
    
   </script>
   
   <style>
    table.orders th {
        font-weight: bold;
        padding: 2px 4px  ;
        text-align: center;
        vertical-align: top;
    }
  </style>
   
  </head>
  <body >
         
    <form  name="frmdatos" method="post">
    
    <input type="hidden" name="hdnFlagValidaProducto" id="hdnFlagValidaProducto"/>
    <input type="hidden" name="hdnFlagValidaAccesorio" id="hdnFlagValidaAccesorio"/>
    
    
            <!--<div id="dialog-modal" title="Basic modal dialog">
              <p>Adding the modal overlay screen makes the dialog look more prominent because it dims out the page content.</p>
            </div> -->
      <!--<input type="hidden" name="hdnRuc" id="hdnRuc"/>
      <input type="hidden" name="hdnCustomerIdbscs" id="hdnCustomerIdbscs"/>
      <input type="hidden" name="hdnSearchContinue" id="hdnSearchContinue"/>
      <input type="hidden" name="hdnSearchType" id="hdnSearchType"/>
      
      <input type="hidden" name="hdnStatusCollection" id="hdnStatusCollection"/>
      <input type="hidden" name="hdnStatusVEP" id="hdnStatusVEP"/>
      <input type="hidden" name="hdnTypecia" id="hdnTypecia"/>
      <input type="hidden" name="hdnNumber" id="hdnNumber"/>
      <input type="hidden" name="hdnCodbscs" id="hdnCodbscs"/>
      <input type="hidden" name="hdnCustomerName" id="hdnCustomerName"/>
      <input type="hidden" name="hdnCustomerId" id="hdnCustomerId"/>-->
      
      <input type="hidden" name="hdn_cboubiori" value="">
     <input type="hidden" name="hdn_documentType" value="">
     <input type="hidden" name="hdn_operationType" value="">
     <input type="hidden" name="hdn_cbocarrier" value="">
     <input type="hidden" name="hdn_npcustomerid" value="">
     <input type="hidden" name="hdn_nptransferreason" value="">
     <input type="hidden" name="hdn_nptransferreasondesc" value="">
     <input type="hidden" name="hdn_Orden" value="">
     <input type="hidden" name="hdn_TypeDocRef" value="">
     <input type="hidden" name="hdn_NumberDocRef" value="">
     <input type="hidden" name="hdn_npaddress" value="">
     <input type="hidden" name="hdn_DocRefpasa" value="true">
     <input type="hidden" name="hdn_IdDocRef" value="">
     <input type="hidden" name="hdn_npmodality_imei" value="">
     <input type="hidden" name="hdn_npmodality" value="">
     <input type="hidden" name="hdn_npcontractdeactivated" value="">
     
     <script>
          var bCompany=true;
          var bImei=true;
          var rownum=0;
          var rownum2=0;
     </script>
    
     <!-- <table class="tablaMarco" >       -->
     <table>
        <tbody>
          <!--<td colspan="3" class="tablaMarcotbody">-->
            <td colspan="3">
          
          
              
              <table border="0"  cellspacing="1" cellpadding="1" width="100%">                            
                      <tr>
                        <td width="130px" class="CellLabel">
                          Nro de Orden
                        </td>
                        <td width="145px" class="CellContent">
                          <input type="text" id="txtNroOrden" name="txtNroOrden" onkeypress="return isNumberKey(event)"/>
                        </td>
                        <td width="130px" class="CellLabel">
                        Tipo de Documento
                        <td width="145px" class="CellContent">
                          <select id="cboTipoDocumento" name="cboTipoDocumento">
                            <c:forEach var="tipoDocumento" items="${requestScope.SOLICITUD_MANUAL_LISTA_TIPO_DOCUMENTO}">
                              <option value="<c:out value="${tipoDocumento.valor}"/> ">
                                <c:out value="${tipoDocumento.descripcion}"/>
                              </option>
                            </c:forEach> 
                          </select>            
                        </td>
                        <td width="110px" class="CellLabel">
                          Nro Documento
                        </td>
                        <td width="290px" class="CellContent">
                          <input type="text" id="txtNroDocumento" name="txtNroDocumento" />
                        </td>
                      </tr>
                      <tr>
                        <td class="CellLabel"><font color="#FF0000">*&nbsp;</font> Compania ID</td>
                        <td class="CellContent">
                        <!--<input type="text" id="txtCodigo" name="txtCodigo"/>-->
                        <input type="text" name="txtNumber" id="txtNumber" size="10" maxlength="10" onblur="javascript:fxValidateCustomer('CUST_NUMBER');">
                        </td>
                        <td class="CellLabel"><font color="#FF0000">*&nbsp;</font> RUC</td>
                        <td class="CellContent">                          
                          <!--<input type="text" name="txtRuc" id="txtRuc" value="" size="15" maxlength="11" onKeyDown="if (window.event.keyCode== 9) {this.value=trim(this.value.toUpperCase());fxValidateCustomer('CUST_RUC','0');}" onKeyPress="if (window.event.keyCode== 13) {this.value=trim(this.value.toUpperCase());fxValidateCustomer('CUST_RUC','1');}"/> -->
                          <input type="text" name="txtRuc" size="11" maxlength="11" onblur="javascript:fxValidateCustomer('CUST_RUC');" onclick="javascript:fxValidateCustomer('CUST_RUC');">
                        </td>
                        <td class="CellLabel">
                          <a href="javascript:fxSearchCustomer();"> Raz&oacute;n Social </a>
                                                   
                        </td>
                        <td class="CellContent">
                                                
                        
                        <!-- <input type="text" name="txtCustomerName" size="35" maxlength="75" onKeyDown="if (window.event.keyCode== 9) {this.value=trim(this.value.toUpperCase());fxValidateCustomer('CUST_NAME','0');}" onKeyPress="if (window.event.keyCode== 13) {this.value=trim(this.value.toUpperCase());fxValidateCustomer('CUST_NAME','1');}"/>-->
                        <input type="text" name="txtNameCompania" id="txtNameCompania" size="55" maxlength="55" onchange="this.value=trim(this.value.toUpperCase())" onblur="javascript:fxValidateCustomer('CUST_NAME');">
                        
                        <!--<input type="hidden" name="hdnCustomerName" id="hdnCustomerName"/>
                        <input type="hidden" name="hdnCustomerId" id="hdnCustomerId"/>-->
                        
                        
                        </td>
                      </tr>
                      <tr>
                        <td class="CellLabel">Cod BSCS</td>
                        <td class="CellContent">                          
                          <!--<input type="text" name="txtCodBscs" id="txtCodBscs" value="" size="15" maxlength="50" onKeyDown="if (window.event.keyCode== 9) {this.value=trim(this.value.toUpperCase());fxValidateCustomer('CUST_CODBSCS','0');}" onKeyPress="if (window.event.keyCode== 13) {this.value=trim(this.value.toUpperCase());fxValidateCustomer('CUST_CODBSCS','1');}"/>-->
                          <input type="text" name="txtBscs" id="txtBscs" size="12" maxlength="12" onblur="javascript:fxValidateCustomer('CUST_BSCS');">
                        </td>
                        <td class="CellLabel"><font color="#FF0000">*&nbsp;</font>Direcci&oacute;n de Entrega</td>
                        <td colspan="3" class="CellContent">
                          <!--<select id="cboDireccionEntrega"  name="cboDireccionEntrega">
                            <option value=""></option>                            
                          </select>   
                          -->
                          <select name="cboDireccion" id= "cboDireccion">
                              <option value=""></option>
                          </select>
                        </td>                        
                      </tr>
                  </table>
                  
                  <br/>
                  
                  <table border="0" cellSpacing="0" cellPadding="0">
                    <tbody>
                      <tr class="PortletHeaderColor">
                      <td width="16" align="left" class="SubSectionTitleLeftCurve" vAlign="top"/>
                      <td align="left" class="SubSectionTitle" vAlign="top">
                      Solicitud   
                      </td>
                      <td width="12" align="right" class="SubSectionTitleRightCurve" vAlign="top"/>
                    </tbody>
                  </table>
                  
                  <table border="0" cellspacing="1" cellpadding="1" class="RegionBorder" width="100%">                  
                    <tbody>
                      <tr>
                        <td width="130px" class="CellLabel">Nro. Doc.</td>
                        <td width="145px" class="CellContent">
                          <span id="panelIdSolicitud" ></span>
                          <input type="hidden" id="txtNroDocumento" name="txtNroDocumento" value='<c:out value="......."/>'/>
                        </td>
                        <td width="130px" class="CellLabel"><font color="#FF0000">*&nbsp;</font> Ubicación</td>
                        <td width="145px" class="CellContent">
                          <span><%=tienda%></span>
                          <input type="hidden" name="txtUbicacion" id="txtUbicacion" value='<%=tienda%>'/>
                                                    
                        </td>
                        <td width="110px" class="CellLabel">Estado</td>
                        <td width="290px" class="CellContent">
                          <c:out value="Pendiente"/>
                          <input type="hidden" name="txtEstado" id="txtEstado" value='<c:out value="Pendiente"/>'/>
                        </td>          
                      </tr>
                      <tr>
                        <td class="CellLabel">Fecha de Emisi&oacute;n</td>
                        <td class="CellContent">
                          <c:out value=""/>
                          <input type="hidden" name="txtFechaEmision" ig="txtFechaEmision" value='<c:out value=""/>'/>
                        </td>
                        <td class="CellLabel"><font color="#FF0000">*&nbsp;</font> Usuario</td>
                        <td class="CellContent" colspan="3">
                          <span><%=usuario%></span>
                          <input type="hidden" name="txtUsuario" id="txtUsuario" value='<%=usuario%>'/>
                        </td>
                        
                        <!--
                        <td class="CellLabel">Motivo</td>
                        <td class="CellContent">
                        
                          <select id="cboMotivo" name="cboMotivo">
                            <option value="motivio1">motivio1 </option>
                            <option value="motivio2">motivio2 </option>
                            <option value="motivio3">motivio3 </option>
                            <option value="motivio4">motivio4 </option>
                          </select>
                          -->
                        </td>          
                      </tr>
                      <tr>
                        <td class="CellLabel">Observaciones</td>
                        <td class="CellContent" colspan="5">
                          <textarea id="txtObservaciones" name="txtObservaciones" rows="4" cols="104"></textarea>
                            
                        </td>          
                      </tr>
                    </tbody>
                  </table>
                  
                  <br/>
                  
                  
                  
                  <table border="0" cellSpacing="0" cellPadding="0">
                    <tbody>
                      <tr class="PortletHeaderColor">
                      <td width="16" align="left" class="SubSectionTitleLeftCurve" vAlign="top"/>
                      <td align="left" class="SubSectionTitle" vAlign="top">
                      Detalle   
                      </td>
                      <td width="12" align="right" class="SubSectionTitleRightCurve" vAlign="top"/>
                    </tbody>
                  </table>
                                    
                  <table border="0"  cellspacing="1" cellpadding="1" class="RegionBorder">                    
                    <tbody>
                      <tr>
                        <td width="130px" class="CellLabel"><font color="#FF0000">*&nbsp;</font> Motivo</td>
                        <td width="145px" class="CellContent">
                          <select id="cboMotivoDetalle" name="cboMotivoDetalle" style="width:200px;">
                            
                            <c:forEach var="motivos" items="${requestScope.SOLICITUD_MANUAL_LISTA_MOTIVOS}">
                              <option value="<c:out value="${motivos.valor}"/> ">
                                <c:out value="${motivos.descripcion}"/>
                              </option>
                            </c:forEach>
                          </select>
                         </td>
                        <td width="130px" class="CellLabel"><font color="#FF0000">*&nbsp;</font> Modalidad</td>
                        <td width="145px" class="CellContent">
                        
                          <select id="cboModalidad" name="cboModalidad" onchange="obtnerAccesoriosAndProductos()">
                            <c:forEach var="lista" items="${requestScope.SOLICITUD_MANUAL_LISTA_MODALIDAD}">
                              
                              <c:if test="${lista.valor == '20'}" >
                                <option value="<c:out value='${lista.valor}'/> " selected >
                                    <c:out value="${lista.descripcion}"/>
                                </option>
                                </c:if>
                                
                                <c:if test="${lista.valor != '20'}" >
                                <option value="<c:out value='${lista.valor}'/> "  >
                                 <c:out value="${lista.descripcion}"/>
                                 </option>
                              </c:if>
                              
                            </c:forEach>                            
                          </select>
                          
                        </td>
                        <td width="110px" class="CellLabel"><font color="#FF0000">*&nbsp;</font> Condici&oacute;n</td>
                        <td width="130px" class="CellContent">
                        
                          <select id="cboEstado" name="cboEstado">                             
                            <c:forEach var="lista" items="${requestScope.SOLICITUD_MANUAL_LISTA_ESTADO}">
                                                                                          
                              <c:if test="${lista.valor == 'Nuevos'}" >
                                <option value="<c:out value='${lista.valor}'/> " selected >
                                    <c:out value="${lista.descripcion}"/>
                                </option>
                              </c:if>
                              <c:if test="${lista.valor != 'Nuevos'}" >
                                <option value="<c:out value='${lista.valor}'/> ">
                                    <c:out value="${lista.descripcion}"/>
                                </option>
                              </c:if>                           
                              
                            </c:forEach>                            
                          </select>
                          
                        </td>
                        <td width="110px" class="CellLabel"><font color="#FF0000">*&nbsp;</font> Con Accesorio?</td>
                        <td width="130px" class="CellContent">
                          <select id="cboAccesorio" name="cboAccesorio">
                              <option value="1">SI</option>
                              <option value="0">NO</option>                              
                          </select>
                        </td>
                      </tr>
                             
                      <tr>
                        <td class="CellLabel">Divisi&oacute;n de Negocio</td>
                        <td class="CellContent">
                          <%=Constante.TELEFONIA_MOVIL%>
                        </td>
                        <td class="CellLabel"><font color="#FF0000">*&nbsp;</font> Soluci&oacute;n de Negocio</td>
                        <td class="CellContent">
                        
                          <select id="cboSolucionNegocio" name="cboSolucionNegocio" onchange="obtenerLineaNegocio()">
                            <option value=""></option>
                             <c:forEach var="solucionNegocio" items="${requestScope.SOLICITUD_MANUAL_LISTA_SOLUCION_NEGOCIO}">
                                <option value="<c:out value="${solucionNegocio.valor}"/> ">
                                  <c:out value="${solucionNegocio.descripcion}"/>
                                </option>
                            </c:forEach> 
                          </select>
                        </td>
                        <td class="CellLabel"><font color="#FF0000">*&nbsp;</font> Linea de Producto</td>
                        <td class="CellContent">
                                      
                        
                          <select id="cboLineaProducto" name="cboLineaProducto" onchange="obtenerProductos()" style="width:200px;">
                            <option value=""></option>                            
                          </select>
                          
                        </td>
                        <td class="CellLabel"><font color="#FF0000">*&nbsp;</font> Producto</td>
                        <td class="CellContent">
                        
                          <select id="cboProducto" name="cboProducto" style="width:221px;" onchange="obtnerAccesoriosRepuesto()"> 
                            <option value=""></option>                            
                          </select>
                        </td>
                      </tr>
                      
                      <tr>
                        <td class="CellLabel">Accesorio/Repuesto</td>
                        <td colspan="3" class="CellContent">
                        
                          <select id="cboRepuestos" name="cboRepuestos" style="width:405px;">
                            <option value=""></option>                                                        
                          </select>
                          
                          
                        </td>
                        <td class="CellLabel"><font color="#FF0000">*&nbsp;</font> Cantidad</td>
                        <td class="CellContent">
                          <input type="TEXT" id="txtCantidad" name="txtCantidad"/>
                          
                        </td>
                        <td colspan='2'  class="CellContent"> <input type="button" value="Agregar" onclick="agregarLista()"/> </td>          
                      </tr>
                    </tbody>
                  </table>
                  
                  <br/>
                  
              
              
                    <div id = "listaProducto" style="border: 1px solid #CC9933;" >
                        <table name="tblProductos" id="tblProductos" class="orders" style="width: 100%">
                          <thead>
                            <tr>
                              <th>Producto</th>
                              <th>Accesorio/Repuesto</th>
                              <th>Motivo</th>
                              <th>Modalidad</th>
                              <th>Estado</th>
                              <th>Con Accesorio?</th>
                              <th>Cantidad</th>
                              <th>
                                <!--<input type="BUTTON" id="btnEliminar" value="Eliminar" onclick="eliminarFilas()"/>-->
                              </th>
                            </tr>
                          </thead>
              
                        </table>
                    </div>
              
          
                  <table border="0" align="center">
                  <tr>
                    <td><input type="button" value="Nueva Solicitud" onclick="reFresh()"/> </td>
                    <td><input type="button" id="btnNuevaSolicitud" value="Enviar Solicitud" onclick="nuevaSolicitud()"/> </td>
                  </tr>
                </table>   
          
          </td>
        </tbody>
      </table>
    
    </form>
      
  </body>
  
</html>


<script type="text/javascript">
  //deshabilitamos cboRepuestos
  //var tamanioAccesorioRepuesto = '<%=request.getAttribute("tamanioAccesorioRepuesto")%>';
  //if(tamanioAccesorioRepuesto == "0"){
      document.getElementById("cboRepuestos").disabled =  true;
  //}
  
  
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