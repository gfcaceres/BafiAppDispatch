<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>      
      
  </head>
  <body>
      <%
      String param = request.getParameter("excel");
      if(param != null){
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "inline; filename=excel.xls");
      }
        
 
      %>
      <table align="center" border="2">
        <thead>
            <tr bgcolor="lightgreen">
                <th>Sr. No.</th>
                <th>Text Data</th>
                <th>Number Data</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (int i = 0; i < 10; i++) {
            %>
            <tr bgcolor="lightblue">
                <td align="center"><%=i%></td>
                <td align="center">This is text data <%=i%></td>
                <td align="center"><%=i * i%></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>


      <br/>
      <a href="/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_EXC?excel=yes">Export to Excel</a>
   
      
  </body>
</html>
