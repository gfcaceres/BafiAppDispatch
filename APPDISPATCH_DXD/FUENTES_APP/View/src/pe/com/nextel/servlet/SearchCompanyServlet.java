package pe.com.nextel.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.nextel.bean.CompanyBean;
import pe.com.nextel.service.CompanyService;
import pe.com.nextel.util.Constante;
import pe.com.nextel.util.MiUtil;


public class SearchCompanyServlet extends HttpServlet 
{
  private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
  }


  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String metodo = "";   
        
        try {
          //System.out.println();
          metodo = request.getParameter(Constante.SERVLET_PARAM_METHOD);
          System.out.println("metodo = " + metodo); 
          
          if(metodo!= null && metodo.equals(Constante.SERVLET_PARAM_INI_PAGE)){
            System.out.println("EDU: Entro"); 
            iniciarPagina(request,response);
          }
          
          if(metodo!= null && metodo.equals(Constante.SERVLET_PARAM_SEARCH_COMPANY)){
            searchCompany(request,response);
          }            
            
        } finally {            
            out.close();
        }
    }
    
  public void searchCompany(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    response.setContentType("text/plaint;charset=UTF-8");        
    PrintWriter out = response.getWriter();
    
    System.out.println("EDU: metodo ajax");
    
    HashMap mapaData = null;
    CompanyService companyService = new CompanyService();
    CompanyBean companyBean = new CompanyBean();
    //mapaData = companyService.getTipoDocumentoList();
    System.out.println("EDU: 1111");
    //recperamos los datos del formulario
    companyBean.setDocumentType(request.getParameter("cmbDocumentType"));
    System.out.println("EDU: 2");
    companyBean.setDocumentNumbre(request.getParameter("txtDocumentNumber"));
    System.out.println("EDU: 3");
    String customerId = request.getParameter("txtCustomerid");
    System.out.println("EDU: 4");
    companyBean.setCompanyCod(MiUtil.parseInt(request.getParameter("txtCustomerid")));
     
    System.out.println("EDU: 7");
    companyBean.setRazonSocial(request.getParameter("txtRazonSocial"));
    System.out.println("EDU: 8");
    companyBean.setCompanyName(request.getParameter("txtCommercialName"));
    System.out.println("EDU: 9");
    companyBean.setBSCSCod(request.getParameter("txtCustCode"));
    System.out.println("EDU: 10");
    companyBean.setCompamyType(request.getParameter("cmbTypePerson")); 
    System.out.println("EDU: 11");
    
    
    String tabla = "<tr>";
    tabla = tabla + "<td class='CellLabel'>&nbsp;&nbsp;<b>Nro</b></td>";
    tabla = tabla + "<td class='CellLabel'>&nbsp;&nbsp;<b>Codigo</b></td>";
    tabla = tabla + "<td class='CellLabel'>&nbsp;&nbsp;<b>Ruc</b></td>";
    tabla = tabla + "<td class='CellLabel'>&nbsp;&nbsp;<b>Razón Social</b></td>";
    tabla = tabla + "<td class='CellLabel'>&nbsp;&nbsp;<b>Tipo Compañía</b></td>";
    tabla = tabla + "</tr>";
    
    tabla = tabla + "<tr>"; 
    tabla = tabla + "<td class='CellContent' valign='top'>";
    tabla = tabla + "<a href=\"javascript:CompanyElec('1413','20100118760','DINERS CLUB PERU S.A.')\"><img src=\"../../websales/images/viewDetail.gif\" width=\"15\" height=\"15\" border=\"0\"></a>";
    tabla = tabla + "</td>";
    tabla = tabla + "<td class=\"CellContent\" valign=\"top\">1413</td>";
    tabla = tabla + "<td class=\"CellContent\" valign=\"top\">20100118760</td>";
    tabla = tabla + "<td class=\"CellContent\" valign=\"top\">DINERS CLUB PERU S.A.</td>";
    tabla = tabla + "<td class=\"CellContent\" valign=\"top\">Customer</td>";
    tabla = tabla + "</tr>";
   
    out.write(tabla);
    out.close();          
                           
    
    //companyService.getCompaniaList(companyBean);
    
    //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/JP_SEARCH_COMPANY/JP_LIST_COMPANY_ShowPage.jsp");
    //dispatcher.include(request, response);
    
  }  
  
  public void iniciarPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    //response.setContentType("text/html;charset=UTF-8");
    List listaTipoDocumento = null;
    List listaTipoCompania =  null;
    HashMap mapaData = null;
    CompanyService companyService = new CompanyService();
    System.out.println("EDU: 1"); 
    mapaData = companyService.getTipoDocumentoList();
    System.out.println("EDU: 2"); 
    listaTipoDocumento = (ArrayList)mapaData.get(Constante.BUSCAR_COMPANIA_LISTA_TIPO_DOCUMENTO);    
    System.out.println("EDU: 3"); 
    request.setAttribute(Constante.BUSCAR_COMPANIA_LISTA_TIPO_DOCUMENTO,listaTipoDocumento);
    System.out.println("EDU: 4"); 
    
    mapaData = companyService.getTipoCompaniaList();
    System.out.println("EDU: 5"); 
    listaTipoCompania = (ArrayList)mapaData.get(Constante.BUSCAR_COMPANIA_LISTA_TIPO_COMPANIA);    
    System.out.println("EDU: 6"); 
    request.setAttribute(Constante.BUSCAR_COMPANIA_LISTA_TIPO_COMPANIA,listaTipoCompania);
    System.out.println("EDU: 7"); 
    
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/JP_SEARCH_COMPANY/JP_SEARCH_COMPANY_ShowPage.jsp");
    dispatcher.include(request, response);
    
  } 
    
    
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    processRequest(request,response);
    
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    processRequest(request,response);
  }
  
}