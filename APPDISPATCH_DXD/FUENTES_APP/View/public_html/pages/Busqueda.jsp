<%@ page contentType="text/html;charset=windows-1252"%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Página de prueba</title>
     <link type="text/css" rel="stylesheet"
          href="../RESOURCES/css/salesweb.css"/>
    <script type="text/javascript" src="../RESOURCES/js/date-picker.js"></script>
  </head>  
  <body>
    <script>
        function fxSaveRequest() {
            vForm = document.frmdatos;            
            vForm.action = "<%=request.getContextPath()%>/requestservlet?myaction=doSaveRequest";
            vForm.submit();
        }
    </script>

    <form action="requestservlet" method="post" name="frmdatos">
        <!--
        <input type="button" name="btnGrabar" value="Me siento con suerte" onclick="fxSaveRequest()">
        -->
        <table border="0" cellspacing="1" cellpadding="1" align="center" class="RegionBorder" width="950px">
        <tbody>
          <tr>
            <td class="CellLabel" width="15%">Ubicación</td>
            <td class="CellContent" width="10%">
               <select name="cmbDivisionNegocio" onchange="fxFillSoluciones()">
                  <option value=""></option>
                  <option value="1">Telefonía Móvil</option>
                  <option value="2">Banda Ancha</option>
                  <option value="6">Telefonía Fija</option>
                </select>
            </td>
            <td class="CellLabel" width="15%">Usuario</td>
            <td class="CellContent" width="20%">
               <select name="cmbDivisionNegocio" onchange="fxFillSoluciones()">
                  <option value=""></option>
                  <option value="1">Telefonía Móvil</option>
                  <option value="2">Banda Ancha</option>
                  <option value="6">Telefonía Fija</option>
                </select>
            </td>
            <td class="CellLabel" width="15%">N° Solicitud</td>
            <td class="CellContent" width="15%">
                <input type="text" id="txtNroSolicitud" name="txtNroSolicitud" size="7" maxlength="15" onblur="this.value=fxTrim(this.value);">                
            </td>
         </tr>
         <tr>
            <td class="CellLabel">N° Orden</td>
            <td class="CellContent">
                <input type="text" id="txtNroSolicitud" name="txtNroSolicitud" size="7" maxlength="15" onblur="this.value=fxTrim(this.value);">                
            </td>
            <td class="CellLabel" >Orden Pagada</td>
            <td class="CellContent">
               <select name="cmbDivisionNegocio" onchange="fxFillSoluciones()">
                  <option value=""></option>
                  <option value="1">Telefonía Móvil</option>
                  <option value="2">Banda Ancha</option>
                  <option value="6">Telefonía Fija</option>
                </select>
            </td>
            <td class="CellLabel">Estado de Solicitud</td>
            <td class="CellContent">
                <select name="cmbDivisionNegocio" onchange="fxFillSoluciones()">
                  <option value=""></option>
                  <option value="1">Telefonía Móvil</option>
                  <option value="2">Banda Ancha</option>
                  <option value="6">Telefonía Fija</option>
                </select>
            </td>
         </tr>
          <tr>
            <td class="CellLabel">División de Negocio</td>
            <td class="CellContent">
                 <select name="cmbDivisionNegocio" onchange="fxFillSoluciones()">
                  <option value=""></option>
                  <option value="1">Telefonía Móvil</option>
                  <option value="2">Banda Ancha</option>
                  <option value="6">Telefonía Fija</option>
                </select>
            </td>
            <td class="CellLabel">Categoría</td>
            <td class="CellContent">
               <select name="cmbDivisionNegocio" onchange="fxFillSoluciones()">
                  <option value=""></option>
                  <option value="1">Telefonía Móvil</option>
                  <option value="2">Banda Ancha</option>
                  <option value="6">Telefonía Fija</option>
                </select>
            </td>
            <td class="CellLabel">Sub Categoría</td>
            <td class="CellContent">
                <select name="cmbDivisionNegocio" onchange="fxFillSoluciones()">
                  <option value=""></option>
                  <option value="1">Telefonía Móvil</option>
                  <option value="2">Banda Ancha</option>
                  <option value="6">Telefonía Fija</option>
                </select>
            </td>
         </tr>
         
		           <tr>
            <td class="CellLabel">N° Factura</td>
            <td class="CellContent">
                  <input type="text" id="txtNroSolicitud" name="txtNroSolicitud" size="7" maxlength="15" onblur="this.value=fxTrim(this.value);">                
           
            </td>
            <td class="CellLabel"></td>
            <td class="CellContent">
              
            </td>
            <td class="CellLabel">Fecha Solicitud</td>
            <td class="CellContent">
                <select name="cmbDivisionNegocio" onchange="fxFillSoluciones()">
                  <option value=""></option>
                  <option value="1">Telefonía Móvil</option>
                  <option value="2">Banda Ancha</option>
                  <option value="6">Telefonía Fija</option>
                </select>
            </td>
         </tr>
        
         <tr>
            <td class="CellLabel">Cod BSCS</td>
            <td class="CellContent">
                <input type="text" id="txtNroSolicitud" name="txtNroSolicitud" size="7" maxlength="15" onblur="this.value=fxTrim(this.value);">                
            </td>
            <td class="CellLabel">
              &nbsp;<font color="#FF0000">*&nbsp;</font><a href="javascript:fxSearchCustomer()">Raz&oacute;n&nbsp;Social</a>
            </td>
            <td class="CellContent">
                <input type="text" id="txtNroSolicitud" name="txtNroSolicitud" size="7" maxlength="15" onblur="this.value=fxTrim(this.value);">                
            </td>
            <td class="CellLabel">Fecha Solicitud</td>
            <td class="CellContent">
               &nbsp;desde&nbsp;<input type="text" name="txtCreateDateFrom" size="10" maxlength="10" value="" onblur="this.value=fxTrim(this.value);">
                <a href="javascript:show_calendar('frmdatos.txtCreateDateFrom',null,null,'DD/MM/YYYY');" onmouseover="window.status='Fecha desde';return true;" onmouseout="window.status='';return true;">
                <img src="../websales/images/show-calendar.gif" width="24" height="22" border="0"></a>&nbsp;<i>DD/MM/YYYY</i>
                <br/>
                &nbsp;hasta&nbsp;&nbsp;<input type="text" name="txtCreateDateTill" size="10" maxlength="10" value="" onblur="this.value=fxTrim(this.value);">
                <a href="javascript:show_calendar('frmdatos.txtCreateDateTill',null,null,'DD/MM/YYYY');" onmouseover="window.status='Fecha hasta';return true;" onmouseout="window.status='';return true;">
                <img src="../websales/images/show-calendar.gif" width="24" height="22" border="0"></a>&nbsp;<i>DD/MM/YYYY</i>
            </td>
         </tr>
         <tr>
            <td colspan="6" align="center">
                <input type="button" name="btnGrabar" value="Buscar" onclick="fxSaveRequest()">
                <input type="button" name="btnGrabar" value="Exportar" onclick="fxSaveRequest()">
            </td>
         </tr>
      </tbody>
    </table>
    
</form>  

  </body>
</html>
