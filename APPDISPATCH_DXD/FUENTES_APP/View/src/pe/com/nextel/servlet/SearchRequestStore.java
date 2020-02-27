package pe.com.nextel.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.nextel.bean.RequestBean;
import pe.com.nextel.util.MiUtil;


public class SearchRequestStore extends HttpServlet 
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
        try {
        
        RequestBean requestBean = new RequestBean();
        requestBean.setLocation(MiUtil.parseInt(request.getParameter("cboUbicacion")));        
        requestBean.setUser(request.getParameter("cboUsuario"));
        requestBean.setRequestNumber(request.getParameter("txtNroSolicitud"));
        requestBean.setOrderNumber(request.getParameter("txtNroOrden"));
        requestBean.setOrderPay(request.getParameter("cboOrdenPagada"));
        requestBean.setRequestStateCod(MiUtil.parseInt(request.getParameter("cboEstadoSolicitud")));
        requestBean.setBusinessDivision(MiUtil.parseInt(request.getParameter("cboDivisionNegocio")));
        requestBean.setCategory(request.getParameter("cboCategoria"));
        requestBean.setSubCategory(request.getParameter("cboSubCategoria"));
        requestBean.setFacturaNumber(request.getParameter("txtNroFactura"));
        requestBean.setRazonSocial(request.getParameter("txtNroRazonSocial"));
        requestBean.setRequestDateBegin(request.getParameter("txtCreateDateFrom"));
        requestBean.setRequestDateEnd(request.getParameter("txtCreateDateTill"));
        requestBean.setCodBSCS(request.getParameter("txtCodigoBSCS"));
            
        List listaRequest = new ArrayList();  
         //lleno la lista solo para pruebas   
         //Aqui deberia llamar al DAO
         for(int i=0; i<20; i++){
            listaRequest.add(requestBean);
         }
       
       request.setAttribute("listaRequest", listaRequest);
       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/JP_SEARCH_REQUEST_STORE/JP_OL_REQUEST_LIST_ShowPage.jsp");
       dispatcher.include(request, response);     
            
        } finally {            
            out.close();
        }
    }
  

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    processRequest(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    processRequest(request, response);
  }
  
  
  
}