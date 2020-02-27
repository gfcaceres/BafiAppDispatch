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
              <img name="IMG_REFRESH" alt="REFRESH" src="../RESOURCES/images/RefreshOff.gif" border="0"/>
            </a>            
          </td>
        </tr>
      </table>
          
            
    </form>
  
  
  </body>
</html>
