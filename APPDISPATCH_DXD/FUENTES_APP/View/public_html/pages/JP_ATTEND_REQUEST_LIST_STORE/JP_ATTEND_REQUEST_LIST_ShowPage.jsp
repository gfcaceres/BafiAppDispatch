<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Atender Solicitudes a Alamcen</title>
    <link rel="stylesheet" href="../../websales/Resource/salesweb.css"></link>
    
    
    <script language="javascript">
        
        function refresh(){
            document.attendRequestStoreForm.submit();
         }
    </script>
    
               
  </head>
  <body>
  
  
    <!--<form method="POST" action="attendRequestStore">-->
    <form name="attendRequestStoreForm" method="POST" action="#">
      <table width="1065px" class="PortletHeaderColor" border="0" cellSpacing="0" cellPadding="0" align="center" >
        <tbody>
          <tr class="PortletHeaderColor">
            <td width="10" height="10" align="left" class="LeftCurve" noWrap="" vAlign="top"></td>
            <td width="100%" align="left" class="PortletHeaderColor" noWrap="" vAlign="top">
              <font class="PortletHeaderText">Solicitudes a Almacen</font>
            </td>
            <td align="right" class="PortletHeaderColor" noWrap="" vAlign="top"></td>
            <td width="10" align="right" class="RightCurve" noWrap=""></td>
          </tr>
        </tbody>
      </table>
      
      <table width="1065px" class="RegionBorder" border="1" cellSpacing="0" cellPadding="2"  align="center" >
        <tr>
          <td >            
            <a class="PortletText4" href="javascript:refresh()">
              <img name="IMG_REFRESH" alt="REFRESH" src="../../websales/images/RefreshOff.gif" border="0"/>
            </a>  
        <BR>
          <FONT class="BannerSecondaryText">
            <B>ORDEN  :</B> Fecha de Solicitud<BR>
          </FONT>
            <table border="1" cellpadding="2" cellspacing="0" class="RegionBorder" width="100%">
       <tr>
        <td class="CellLabel">Nro.</td>
        <td class="CellLabel">Nro Solicitud</td>
        <td class="CellLabel">Nro de Orden</td>
        <td class="CellLabel">Pagado</td>
        <td class="CellLabel">Fecha de Solicitud</td>
        <td class="CellLabel">Razon Social</td>
        <td class="CellLabel">Envio Courier</td>
        <td class="CellLabel">Origen de Sol.</td>
        <td class="CellLabel">Usuario</td>
        <td class="CellLabel">Estado</td>
      </tr>
        </table>
          </td>
        </tr>
      </table>
      <table width="1065px" class="RegionBorder" border="1" cellSpacing="0" cellPadding="2"  align="center" >
      
      </table>
            
    </form>
  
  
  </body>
</html>
