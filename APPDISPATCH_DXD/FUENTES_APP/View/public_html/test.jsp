<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Página de prueba</title>
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
        <input type="button" name="btnGrabar" value="Me siento con suerte" onclick="fxSaveRequest()">
    </form>    
  </body>
</html>
