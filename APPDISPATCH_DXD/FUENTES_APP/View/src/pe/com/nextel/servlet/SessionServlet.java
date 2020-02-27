package pe.com.nextel.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.portal.provider.v2.ProviderSession;
import oracle.portal.provider.v2.ProviderUser;
import oracle.portal.provider.v2.http.HttpCommonConstants;
import oracle.portal.provider.v2.render.PortletRenderRequest;

import pe.com.nextel.bean.PortalSessionBean;
import pe.com.nextel.service.GeneralService;
import pe.com.nextel.service.SessionService;
import pe.com.nextel.util.MiUtil;


public class SessionServlet extends HttpServlet 
{
  private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    processRequest(request,response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    processRequest(request,response);
  }
  
  
  public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    String sessionID  = null;
    ProviderUser user = null;
    
    HttpSession session = request.getSession(true);

    try {
      System.out.println("EDU: paso 1");
      PortletRenderRequest pRequest   = (PortletRenderRequest)request.getAttribute(HttpCommonConstants.PORTLET_RENDER_REQUEST);
      System.out.println("EDU: paso 2");
      ProviderSession pSession = (ProviderSession)pRequest.getSession();
      System.out.println("EDU: paso 3");
      user = pRequest.getUser();
      System.out.println("EDU: paso 4");
      sessionID = user.getPortalSessionId();
      System.out.println("EDU: paso 5");
      //La variable a registrar la sesion
      System.out.println("SessionId enviado desde portal : " + sessionID);
      if( sessionID!= null && !sessionID.equals("") && !sessionID.equals("0")){    
          String strRolId = MiUtil.getString(request.getParameter("an_rolid"));
          int intApplicationId  = -1;
          /**Se adiciona el parametro secureId**/
          String  strSecureId     = MiUtil.getString(request.getParameter("secureId"));
    
          System.out.println("Portlet JP_SESSION_ShowPage - Nextel : " + user.getName()+
                              "| strRolId : " + strRolId + "| strSecureId : " + strSecureId + "|");
        
          PortalSessionBean    objPortalSessBean  = new PortalSessionBean();
          SessionService       objSessionService  = new SessionService();
    
          if( !strSecureId.equals("") ){
            intApplicationId = objSessionService.getSecureRol(MiUtil.parseInt(strSecureId));
            System.out.println("Se ingreso mediante un token : Usuario Nextel : " +user.getName() + " SecureId : " + strSecureId + " Application : " + intApplicationId);
          }else{
            System.out.println("No se ingreso mediante un token : Usuario Nextel : " +user.getName() + " SecureId : " + strSecureId + " RolId : " + strRolId);
            intApplicationId  = MiUtil.parseInt(strRolId);
          }
    
   
          //Obtengo las variables de diversas tablas, se requiere wn_salesstructid   
          //Nuevo par?metro para Jerarqu?a de Ventas
          String strSalesstructid  =  MiUtil.getString(request.getParameter("npsalesstructid"));
          if(strSalesstructid==null){
            strSalesstructid  =  MiUtil.getString(request.getParameter("NPSALESSTRUCTID"));
          }
    
          System.out.println("strSalesstructid jerarquia = "+strSalesstructid);
        
          int intSalesstructid = 0;
          if(strSalesstructid!=null){
            intSalesstructid = strSalesstructid.length()==0?0:Integer.parseInt(strSalesstructid);
          }
    
          System.out.println("intSalesstructid jerarquia = "+intSalesstructid);
        
          /*Obtener el id canal de venta*/
          GeneralService objGeneralService= new GeneralService();    
          HashMap hshData=null;
    
          String strMessage = "";    
          int intTipoCanal = 0; // 0 Canal, 1 SubCanal
        
          hshData = objGeneralService.get_AttChannel_Struct(intSalesstructid);
          strMessage=(String)hshData.get("strMessage");
          if (strMessage!=null)
             throw new Exception(strMessage); 
          int idCanalAtencion=MiUtil.parseInt((String)hshData.get("iRetorno"));    
        
    
          //Se agrega el par?metro "wn_salesstructid"
          //objPortalSessBean = objSessionService.getSessionData(user.getName(), intApplicationId);
          objPortalSessBean = objSessionService.getSessionData(user.getName(), intApplicationId, intSalesstructid);
    
          if( objPortalSessBean.getMessage()!=null){
            throw new Exception((String)objPortalSessBean.getMessage());
          }
	
          //objPortalSessBean.setSessionID(sessionID);
          objPortalSessBean.setSalesStructId(intSalesstructid);
      
          //Se guardan las variables obtenidas en sesi?n:
          session.putValue("swprovidergrpid", objPortalSessBean.getProviderGrpId()+"");
          session.putValue("npsalesstructid", strSalesstructid);
          
          objPortalSessBean.setSessionID(sessionID);
    
          //Inserto las variables obtenidas en sesion mediante SP_INS_PERSISTENCE_BY_PORTALID
    
          System.out.println("Antes de agregar la sesion");
          HashMap objHashSession  = objSessionService.addUserSessionIni(objPortalSessBean);
          System.out.println("Despues de agregar la sesion");
    
          if( objHashSession.get("strMessage")!= null )
            throw new Exception((String)objHashSession.get("strMessage"));
            
          }else{
            System.out.println("Error al obtener el SessionId enviado desde portal : " + sessionID);
            throw new Exception("Error al obtener el SessionId enviado desde portal : " + sessionID + " . El m?dulo de ?rdenes no tiene sesi?n del usuario.");
          }
          
          System.out.println("===============[JP_SESSION][Fin]============= -> Nextel : " + user.getName());
      
      }catch(Exception ex){
        System.out.println("Portlet JP_SESSION_ShowPage Not Found : " + ex.getClass() + " - " + ex.getMessage());
        System.out.println("Variable del sessionId : " + sessionID);
        out.println("Portlet JP_SESSION_ShowPage Not Found");
        System.out.println("[Exception][JP_SESSION][Errores al registrar la sesion en BD de Java - operador logistico]"+ex.getClass() + 
        " - " +ex.getMessage());
        out.println("<html>");
        out.println("<head></head>");
        out.println("<body>");
        out.println("<script>");
        out.println("alert('La sesi?n del usuario para la aplicaci?n de ?rdenes no fue registrada correctamente. Causa del error : "+ex.getMessage()+"');");
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");        
      }    
      finally {            
        out.close();
      }
  }
  
  
}