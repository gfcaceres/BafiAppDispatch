package pe.com.nextel.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pe.com.nextel.bean.DetailItemBean;
import pe.com.nextel.bean.EquipmentBean;
import pe.com.nextel.bean.HeaderRequestBean;
import pe.com.nextel.bean.InstalacionBean;
import pe.com.nextel.bean.RequestBean;
import pe.com.nextel.service.BafiOutdoorService;
import pe.com.nextel.service.GeneralService;
import pe.com.nextel.service.RequestService;
import pe.com.nextel.util.Constante;
import pe.com.nextel.util.MiUtil;


public class GeneralServlet extends GenericServlet 
{
   private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
   protected static  Logger logger =  Logger.getLogger(GeneralServlet.class);
 

   public void init(ServletConfig config) throws ServletException {
      super.init(config);
   }
   


    protected  void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String metodo = "";      
        try {
        
        metodo = request.getParameter(Constante.SERVLET_PARAM_METHOD);
        System.out.println("----metodo = " + metodo); 
        logger.debug("EDU: ---metodo = " + metodo);
        
        if(metodo.equals(Constante.SERVLET_LOAD_INFO)){
          doLoadInfo(request,response);
        }
        
        if(metodo.equals(Constante.SERVLET_LOAD_REQUEST)){
          loadRequestDetail(request,response);
        }  
        
        if(metodo.equals(Constante.SERVLET_SEND_REQUEST)){          
          loadSendRequest(request,response);
        }   

        if(metodo != null && metodo.equals("GENERATE_DOC")){
            generate_doc(request,response);
        }
        if(metodo != null && metodo.equals("SAVE_REQUEST")){
            doSaveRequest(request,response);
        }
        if(metodo != null && metodo.equals("VALID_MODEL_CONDITION")){
            validateModelCondition(request,response);
        }
        if(metodo != null && metodo.equals("VALID_MODEL_CONDITION2")){
            validateModelCondition2(request,response);
        }
        if(metodo != null && metodo.equals("UPDATE_MODEL_CONDITION")){
            updateModelCondition(request,response);
        }

        }catch(Exception e)
        {
          System.out.print("ERROR: "+e.getMessage());
        }
		finally {            
            out.close();
        }
    }

	public void doLoadInfo (HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
   
    List objArrayActions = new ArrayList();
    List objArrayReasons = new ArrayList();
    GeneralService generalService = new GeneralService();
    int num =1;
    HashMap mapAction = generalService.getActionDetail(Constante.OL_ACTIONS,num);
    objArrayActions = (ArrayList)mapAction.get(Constante.OBJ_ARRAYLIST);
    System.out.print ("objArrayActions : "+objArrayActions);
    request.setAttribute(Constante.LISTA_ACCIONES,objArrayActions);
     int n =1;
    HashMap mapReason =generalService.getReasonDetail(Constante.OL_REASONS,n);
    objArrayReasons = (ArrayList)mapReason.get(Constante.OBJ_ARRAYLIST);
    System.out.print ("objArrayReasons : "+objArrayReasons);
    request.setAttribute(Constante.LISTA_MOTIVO,objArrayReasons);
    
    PrintWriter out = response.getWriter();
    String strUrl="/portal/page/portal/inventory/DISPATCH_DETAIL_REQUEST";
    logger.debug("URParametrosSendReequest: : "+strUrl);    
    out.print("<script>parent.mainFrame.location.replace('"+strUrl+"');</script>"); 
    out.close();
    
    /*RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/pages/JP_DETAIL_REQUEST_STORE/JP_DETAIL_REQUEST_ShowPage.jsp");
    dispatcher.include(request,response);*/

  }
  
  public void loadSendRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, Exception{
       
    PrintWriter out = response.getWriter();
    String strRequesrId =  StringUtils.defaultString(request.getParameter("strRequesrId"), "0");
    String strBuilding =  StringUtils.defaultString(request.getParameter("strBuilding"), "0");
    System.out.println("AQ :strRequesrId :"+strRequesrId );
       
    
    String strUrl="/portal/page/portal/inventory/DISPATCH_DETAIL_REQUEST?strRequesrId="+strRequesrId+"&strBuilding="+strBuilding;
    logger.debug("URParametrosSendReequest: : "+strUrl);
    
    out.print("<script>parent.mainFrame.location.replace('"+strUrl+"');</script>"); 
  }
  
  

	public void loadRequestDetail(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, Exception{
    
    System.out.println("SERVLET --- loadRequestDetail---");
    String strRequesrId =  StringUtils.defaultString(request.getParameter("strRequesrId"), "0");
    String strBuilding = StringUtils.defaultString(request.getParameter("strBuilding"), "0");
    
    String motivo = "";
    String codigoEntregadoA = "";
    RequestService reqService = new RequestService();
    long lngRequest =Long.valueOf(strRequesrId).longValue();
    EquipmentBean equip = new EquipmentBean();
    
    HashMap  hashRequestAttention=reqService.getRequestAttention(lngRequest);
    
    ArrayList lista1 = (ArrayList)hashRequestAttention.get(Constante.OBJ_ARRAYHEADER);
    ArrayList lista2 = (ArrayList)hashRequestAttention.get(Constante.OBJ_ARRAYACCESORY);
    ArrayList lista3 = (ArrayList)hashRequestAttention.get(Constante.OBJ_ARRAYEQUIPMENT);
    ArrayList lista4 = (ArrayList)hashRequestAttention.get(Constante.OBJ_ARRAYDEVICE);
    
      for(int i=0;i<lista3.size();i++){
           equip =(EquipmentBean)lista3.get(i);
        }
    System.out.println("[AppDispatch.GeneralServlet.loadRequestDetail.equip]["+equip.getWv_reqolitemid()+"]");
    
    motivo = (String)hashRequestAttention.get(Constante.MOTIVO_OUTPUT);
    codigoEntregadoA = (String)hashRequestAttention.get(Constante.ENTREGADO_A);
    System.out.println("Servlet motivo = " + motivo);
    System.out.println("Servlet codigoEntregadoA = " + codigoEntregadoA);
    
    System.out.println("lista1.size() = "+lista1.size());
    System.out.println("lista2.size() = "+lista2.size());
    System.out.println("lista3.size() = "+lista3.size());
    System.out.println("lista4.size() = "+lista4.size());
    
    List actions= new ArrayList();
    List reason = new ArrayList();
    if(lista1.size() > 0){
        HeaderRequestBean reqBean = (HeaderRequestBean)lista1.get(0);
        System.out.println("paso 1");    
        actions = doLoadActions(Integer.parseInt(reqBean.getWn_reqolstatusid().trim()));
        
        System.out.println("paso 2");        
        reason = doLoadReason(Integer.parseInt(reqBean.getWn_reqolstatusid().trim()));
    }else{
      actions.add(new HeaderRequestBean());
      reason.add(new HeaderRequestBean());
    }
    
    
    int state=0;
    
    
    System.out.println("paso 3");    
    GeneralService generalService = new GeneralService(); 
    System.out.println("paso 4");
    HashMap mapPersonal  = generalService.getValueNpTable(Constante.PERSONAL_DELIVERY);
    System.out.println("paso 5");
    List listaPersonal;
    listaPersonal = (ArrayList)mapPersonal.get(Constante.OBJ_ARRAYLIST);
    System.out.println("paso 6");
    request.setAttribute(Constante.PERSONAL_DELIVERY,listaPersonal);
    request.setAttribute(Constante.OBJ_ARRAYHEADER,lista1);
    request.setAttribute(Constante.OBJ_ARRAYACCESORY,lista2);
    request.setAttribute(Constante.OBJ_ARRAYEQUIPMENT,lista3);
    request.setAttribute(Constante.OBJ_ARRAYDEVICE,lista4);
    request.setAttribute(Constante.OL_ACTIONS,actions);
    request.setAttribute(Constante.OL_REASONS,reason);
    request.setAttribute(Constante.OL_MOTIVO,motivo);
    request.setAttribute(Constante.ENTREGADO_A,codigoEntregadoA);
    request.setAttribute("strReqolitemid",equip.getWv_reqolitemid());
    
    System.out.println("paso 7");
  
  }

  
   
   public List doLoadActions (int param)  throws ServletException, IOException, Exception{ 
    List objArrayActions = new ArrayList();

    GeneralService generalService = new GeneralService();
    HashMap mapAction = generalService.getActionDetail(Constante.OL_ACTIONS,param);
    
    objArrayActions = (ArrayList)mapAction.get(Constante.OBJ_ARRAYLIST);

    return objArrayActions;
  }
   public List doLoadReason (int param)  throws ServletException, IOException, Exception{ 
    List objArrayReason = new ArrayList();
    GeneralService generalService = new GeneralService();
    HashMap mapAction = generalService.getReasonDetail(Constante.OL_REASONS,param);
    objArrayReason = (ArrayList)mapAction.get(Constante.OBJ_ARRAYLIST);

    return objArrayReason;
  }

  
  public void generate_doc(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, Exception{
        
        response.setContentType("text/plaint");
        PrintWriter out = response.getWriter();
               
        RequestService reqService = new RequestService();
        
        String login = MiUtil.trimNotNull(request.getParameter("strLogin"));   
        
        long   requestId =  Long.parseLong(MiUtil.trimNotNull(request.getParameter("strRequestId")));
        HashMap mapaData = reqService.updGenerateDoc(requestId,login);
        
        String strMensaje = (String)mapaData.get("MENSAJE");
        String strError = (String)mapaData.get("MENSAJE_ERROR");
        
        System.out.println("Servlet DOC strMensaje =  " + strMensaje);
        System.out.println("Servlet DOC strError =  " + strError);
        
        if(strError == null){
            out.write(strMensaje);
        }else{
            out.write(strError);
        }
               
        out.close();
                
        //request.setAttribute("strMensajeGenerarDocumento",msg);
        //request.setAttribute("flagDocumento",Constante.SI_GENERO_DOCUMENTO);

        
        
        /*RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/pages/JP_DETAIL_REQUEST_STORE/JP_DETAIL_REQUEST_ShowPage.jsp?METHOD=LOAD_REQUEST&strRequesrId="+requestId);       
        dispatcher.include(request,response);
          */ 
    /*
        
        String strUrl="/portal/page/portal/inventory/DISPATCH_DETAIL_REQUEST?METHOD=LOAD_REQUEST&strRequesrId="+requestId;
        logger.debug("URParametrosSendReequest: : "+strUrl);    
        out.print("<script>parent.mainFrame.location.replace('"+strUrl+"');</script>");*/
        
  }
  
  
  /**
    * <br>Realizado por: <a href="mailto:miguel.montoya@hp.com">Miguel Ángel Montoya</a>
    * <br>Fecha: 04/02/2013
    * <br>Miguel Montoya     04/02/2013  Creación     
    * @see pe.com.nextel.dao.RequestDAO#doSaveRequest()
    */
    public void doSaveRequest(HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");                
        PrintWriter out = response.getWriter();
                
        RequestBean requestBean = new RequestBean();
        requestBean.setNpRequestId( MiUtil.parseLong(MiUtil.trimNotNull(request.getParameter("txtRequestId") )));
        requestBean.setRequestStateCod(MiUtil.parseInt(MiUtil.trimNotNull(request.getParameter("txtCodigoEstado"))));
        requestBean.setAccion(MiUtil.trimNotNull(request.getParameter("cmbAcciones") ));
        requestBean.setUser(MiUtil.trimNotNull(request.getParameter("strLogin")));
        //Estructura
        requestBean.setDateProgram(MiUtil.trimNotNull(request.getParameter("txtCreateDateFrom") ));
        requestBean.setDateDeliveryActual(MiUtil.trimNotNull(request.getParameter("txtCreateDateTill") ));        
        requestBean.setObservacion(MiUtil.trimNotNull(request.getParameter("txtDescription2") ));
        requestBean.setDeliveyTo(MiUtil.trimNotNull(request.getParameter("cmbPersonalDelivery") ));   
        requestBean.setCodigoEntregado(MiUtil.parseLong(MiUtil.trimNotNull(request.getParameter("cmbPersonalDelivery") )));   
        String strProductStatus = MiUtil.trimNotNull(request.getParameter("hdnProductStatus"));
        
        System.out.println("[AppDispatch.GeneralServlet.doSaveRequest.strProductStatus]["+strProductStatus+"]");
        
        requestBean.setRequestReasonDescription(MiUtil.trimNotNull(request.getParameter("cmbMovito") ));
        
        long lngReqOlItemId = MiUtil.parseLong(MiUtil.trimNotNull(request.getParameter("hdnRequestOlItemId") )) ;
        
        int numeroFilas = MiUtil.parseInt(request.getParameter("txtNroFilas"));
        
        Long motivoId = null;
        Long subMotivoId = null;
        Long detalleId = null;
        String comentario = null;
        Long numOrden = null;
        String hdnFlagAgendamiento = request.getParameter("hdnFlagAgendamiento");
        //Solo procesa si tiene agendamiento
        if(hdnFlagAgendamiento.equals("true")){
            String selMotivo= request.getParameter("selMotivo");
            String selSubMotivo= request.getParameter("selSubMotivo");
            String selDetalle= request.getParameter("selDetalle");
            motivoId = (selMotivo!=null && selMotivo!="")?Long.parseLong(selMotivo):null;
            subMotivoId = (selSubMotivo!=null && selSubMotivo!="")?Long.parseLong(selSubMotivo):null;
            detalleId = (selDetalle!=null && selDetalle.length()>0)?Long.parseLong(selDetalle):null;
            comentario = request.getParameter("txtComentario");
            numOrden = Long.parseLong(request.getParameter("txtNumOrden"));
            System.out.println("[GeneralServlet][doSaveRequest] motivoId = "+motivoId); 
            System.out.println("[GeneralServlet][doSaveRequest] subMotivoId = "+subMotivoId); 
            System.out.println("[GeneralServlet][doSaveRequest] detalleId = "+detalleId); 
            System.out.println("[GeneralServlet][doSaveRequest] comentario = "+comentario); 
            System.out.println("[GeneralServlet][doSaveRequest] numOrden = "+numOrden); 
        }
        

        

        
        System.out.println("Numero de Filas = " + numeroFilas + "");
        System.out.println("requestBean.getNpRequestId() = "+requestBean.getNpRequestId());
        System.out.println("requestBean.getRequestStateCod() = "+requestBean.getRequestStateCod());
        System.out.println("requestBean.getRequestStateDescription() = "+requestBean.getRequestStateDescription());
        System.out.println("requestBean.getUser() = "+requestBean.getUser());
        System.out.println("requestBean.getDateProgram() = "+requestBean.getDateProgram());
        System.out.println("requestBean.getDateDeliveryActual() = "+requestBean.getDateDeliveryActual());
        System.out.println("requestBean.getObservacion() = "+requestBean.getObservacion());
        System.out.println("requestBean.getDeliveyTo() = "+requestBean.getDeliveyTo());
        System.out.println("requestBean.getRequestReasonDescription() = "+requestBean.getRequestReasonDescription()); 
        

        
        RequestService requestService = new RequestService();
        ArrayList listaImeiSin = new ArrayList();
        HashMap hshResultado = new HashMap();
        
        int contarParametros = 0;
        String error = null;
        String mensaje = null;
                
        if(numeroFilas > 0){
            for(int i=0; i<numeroFilas; i++){
        
                DetailItemBean bean = new DetailItemBean();
                bean.setImei(MiUtil.trimNotNull(request.getParameter("imei_num_"+i)));
                bean.setSim(MiUtil.trimNotNull(request.getParameter("sim_num_"+i)));
                bean.setIdEquipo(MiUtil.trimNotNull(request.getParameter("txtIdEquipment"+i)));
                bean.setAlmacen(MiUtil.trimNotNull(request.getParameter("almacen_desc_"+i)));
                bean.setSubInventario(MiUtil.trimNotNull(request.getParameter("subintentario_desc_"+i)));               
                bean.setOrganitationId(MiUtil.trimNotNull(request.getParameter("txtIdOrganitation_"+i)));
                
                listaImeiSin.add(bean);
                
                System.out.println("txtIdEquipment"+i+" = " + request.getParameter("txtIdEquipment"+i));                  
                System.out.println("imei_num_"+i+" = " + request.getParameter("imei_num_"+i));
                System.out.println("sim_num_"+i+" = "  + request.getParameter("sim_num_"+i));
                System.out.println("almacen_desc_"+i+" = "  + request.getParameter("almacen_desc_"+i));
                System.out.println("subintentario_desc_"+i+" = "  + request.getParameter("subintentario_desc_"+i));    
                System.out.println("txtIdOrganitation_"+i+" = "  + request.getParameter("txtIdOrganitation_"+i));    
          
            }
            
        }else{
            DetailItemBean bean = new DetailItemBean();
            bean.setImei("");
            bean.setSim("");
            bean.setIdEquipo("");
            bean.setAlmacen("");
            bean.setSubInventario("");
            bean.setOrganitationId("");
            listaImeiSin.add(bean);
        }
        
        
       System.out.println("listaImeiSin.size() = " + listaImeiSin.size());
       
       System.out.println("EDU: Servlet save requestBean.getUser() = " + requestBean.getUser());
       
       
       hshResultado =   requestService.saveRequestOl(requestBean.getNpRequestId(),
                                                      requestBean.getRequestStateCod(),
                                                      lngReqOlItemId,
                                                      requestBean.getAccion(),
                                                      requestBean.getUser(),
                                                      listaImeiSin,
                                                      requestBean.getDateProgram() ,
                                                      requestBean.getDateDeliveryActual(),
                                                      requestBean.getObservacion(),
                                                      MiUtil.parseLong(MiUtil.trimNotNull(requestBean.getDeliveyTo())),
                                                      strProductStatus,
                                                      requestBean.getRequestReasonDescription(),
                                                      motivoId,
                                                      subMotivoId,
                                                      detalleId,
                                                      comentario,
                                                      numOrden); 
        
        mensaje =  (String) hshResultado.get("strMessage");
        error =  (String) hshResultado.get("strError");
        
        System.out.println("Repuesta del RequestServlet"); 
        System.out.println("mensaje = " + mensaje); 
        System.out.println("error = " + error);
        
        request.setAttribute("flagDocumento",Constante.SI_GRABO_DOCUMENTO);
        request.setAttribute("estadoDocumento",Constante.SI_GRABO_DOCUMENTO);
        
        if(error == null){
            if(mensaje != null){
                out.print(MiUtil.repleaceCharacterSpecialByUNICODE(mensaje));
            }else{
                out.print("");
            }
          
        }else{
          out.print(MiUtil.repleaceCharacterSpecialByUNICODE(error));        
        }
        
        out.close();
    }
    
    /**
      * <br>Realizado por: <a href="mailto:carlos.delossantos@teamsoft.com.pe">Carlos De los santos</a>
      * <br>Fecha: 31/05/2014
      * @see pe.com.nextel.dao.RequestDAO#validateModelCondition()
      */
      public void validateModelCondition(HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException, IOException {
                          
          PrintWriter out = response.getWriter();
                  
          RequestBean requestBean = new RequestBean();
          requestBean.setNpRequestId( MiUtil.parseLong(MiUtil.trimNotNull(request.getParameter("txtRequestId") )));
          requestBean.setRequestStateCod(MiUtil.parseInt(MiUtil.trimNotNull(request.getParameter("txtCodigoEstado"))));
          requestBean.setAccion(MiUtil.trimNotNull(request.getParameter("cmbAcciones") ));
          requestBean.setUser(MiUtil.trimNotNull(request.getParameter("strLogin")));
          //Estructura
          requestBean.setDateProgram(MiUtil.trimNotNull(request.getParameter("txtCreateDateFrom") ));
          requestBean.setDateDeliveryActual(MiUtil.trimNotNull(request.getParameter("txtCreateDateTill") ));        
          requestBean.setObservacion(MiUtil.trimNotNull(request.getParameter("txtDescription2") ));
          requestBean.setDeliveyTo(MiUtil.trimNotNull(request.getParameter("cmbPersonalDelivery") ));   
          requestBean.setCodigoEntregado(MiUtil.parseLong(MiUtil.trimNotNull(request.getParameter("cmbPersonalDelivery") )));   
          String strProductStatus = MiUtil.trimNotNull(request.getParameter("hdnProductStatus"));
          
          System.out.println("[AppDispatch.GeneralServlet.doSaveRequest.strProductStatus]["+strProductStatus+"]");
          
          requestBean.setRequestReasonDescription(MiUtil.trimNotNull(request.getParameter("cmbMovito") ));
          
          long lngReqOlItemId = MiUtil.parseLong(MiUtil.trimNotNull(request.getParameter("hdnRequestOlItemId") )) ;
          
          int numeroFilas = MiUtil.parseInt(request.getParameter("txtNroFilas"));
                  
          
          System.out.println("Numero de Filas = " + numeroFilas + "");
          System.out.println("requestBean.getNpRequestId() = "+requestBean.getNpRequestId());
          System.out.println("requestBean.getRequestStateCod() = "+requestBean.getRequestStateCod());
          System.out.println("requestBean.getRequestStateDescription() = "+requestBean.getRequestStateDescription());
          System.out.println("requestBean.getUser() = "+requestBean.getUser());
          System.out.println("requestBean.getDateProgram() = "+requestBean.getDateProgram());
          System.out.println("requestBean.getDateDeliveryActual() = "+requestBean.getDateDeliveryActual());
          System.out.println("requestBean.getObservacion() = "+requestBean.getObservacion());
          System.out.println("requestBean.getDeliveyTo() = "+requestBean.getDeliveyTo());
          System.out.println("requestBean.getRequestReasonDescription() = "+requestBean.getRequestReasonDescription()); 
          
          RequestService requestService = new RequestService();
          ArrayList listaImeiSin = new ArrayList();
          HashMap hshResultado = new HashMap();
          
          int contarParametros = 0;
          String error = null;
          String mensaje = null;
                  
          if(numeroFilas > 0){
              for(int i=0; i<numeroFilas; i++){
          
                  DetailItemBean bean = new DetailItemBean();
                  bean.setImei(MiUtil.trimNotNull(request.getParameter("imei_num_"+i)));
                  bean.setSim(MiUtil.trimNotNull(request.getParameter("sim_num_"+i)));
                  bean.setIdEquipo(MiUtil.trimNotNull(request.getParameter("txtIdEquipment"+i)));
                  bean.setAlmacen(MiUtil.trimNotNull(request.getParameter("almacen_desc_"+i)));
                  bean.setSubInventario(MiUtil.trimNotNull(request.getParameter("subintentario_desc_"+i)));               
                  bean.setOrganitationId(MiUtil.trimNotNull(request.getParameter("txtIdOrganitation_"+i)));
                  
                  listaImeiSin.add(bean);
                  
                  System.out.println("txtIdEquipment"+i+" = " + request.getParameter("txtIdEquipment"+i));                  
                  System.out.println("imei_num_"+i+" = " + request.getParameter("imei_num_"+i));
                  System.out.println("sim_num_"+i+" = "  + request.getParameter("sim_num_"+i));
                  System.out.println("almacen_desc_"+i+" = "  + request.getParameter("almacen_desc_"+i));
                  System.out.println("subintentario_desc_"+i+" = "  + request.getParameter("subintentario_desc_"+i));    
                  System.out.println("txtIdOrganitation_"+i+" = "  + request.getParameter("txtIdOrganitation_"+i));    
            
              }
              
          }else{
              DetailItemBean bean = new DetailItemBean();
              bean.setImei("");
              bean.setSim("");
              bean.setIdEquipo("");
              bean.setAlmacen("");
              bean.setSubInventario("");
              bean.setOrganitationId("");
              listaImeiSin.add(bean);
          }
          
          
         System.out.println("listaImeiSin.size() = " + listaImeiSin.size());
         
         System.out.println("EDU: Servlet save requestBean.getUser() = " + requestBean.getUser());
         
         
         hshResultado =   requestService.validateModelCondition(requestBean.getNpRequestId(),
                                                        requestBean.getRequestStateCod(),
                                                        lngReqOlItemId,
                                                        requestBean.getAccion(),
                                                        requestBean.getUser(),
                                                        listaImeiSin,
                                                        strProductStatus ); 
          
          mensaje =  (String) hshResultado.get("strMessage");
          error =  (String) hshResultado.get("strError");
          
          System.out.println("Repuesta del RequestServlet"); 
          System.out.println("mensaje = " + mensaje); 
          System.out.println("error = " + error);
          
          request.setAttribute("flagDocumento",Constante.SI_GRABO_DOCUMENTO);
          request.setAttribute("estadoDocumento",Constante.SI_GRABO_DOCUMENTO);
          
          if(error == null){
              if(mensaje != null){
                  out.print(MiUtil.repleaceCharacterSpecialByUNICODE(mensaje));
              }else{
                  out.print("");
              }
            
          }else{
            out.print(MiUtil.repleaceCharacterSpecialByUNICODE(error));        
          }
          
          out.close();
      }
    
    /**
      * <br>Realizado por: <a href="mailto:rensso.martinez@hp.com">Rensso Martínez</a>
      * <br>Fecha: 12/07/2014
      * @see pe.com.nextel.dao.RequestDAO#validateModelCondition2()
      */
      public void validateModelCondition2(HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException, IOException {
                          
          PrintWriter out = response.getWriter();

          RequestBean requestBean = new RequestBean();
          int numeroFilas = MiUtil.parseInt(request.getParameter("txtNroFilas"));
          requestBean.setNpRequestId( MiUtil.parseLong(MiUtil.trimNotNull(request.getParameter("txtRequestId") )));
          String strProductStatus = MiUtil.trimNotNull(request.getParameter("hdnProductStatus"));
          requestBean.setRequestStateCod(MiUtil.parseInt(MiUtil.trimNotNull(request.getParameter("txtCodigoEstado"))));
          requestBean.setAccion(MiUtil.trimNotNull(request.getParameter("cmbAcciones") ));
          requestBean.setUser(MiUtil.trimNotNull(request.getParameter("strLogin")));
          
          System.out.println("GeneralServlet --> validateModelCondition2");
          System.out.println("Numero de Filas = " + numeroFilas + "");
          System.out.println("requestBean.getNpRequestId() = "+requestBean.getNpRequestId());
          System.out.println("requestBean.getRequestStateCod() = "+requestBean.getRequestStateCod());
          System.out.println("requestBean.getAccion() = "+requestBean.getAccion());
          System.out.println("requestBean.getUser() = "+requestBean.getUser());
          
          RequestService requestService = new RequestService();
          ArrayList listaImeiSin = new ArrayList();
          HashMap hshResultado = new HashMap();
          
          String error = null;
          String mensaje = null;
          DetailItemBean imeiBean = new DetailItemBean();
          if(numeroFilas > 0){
              for(int i=0; i<numeroFilas; i++){
                  imeiBean.setImei(MiUtil.trimNotNull(request.getParameter("imei_num_"+i)));
                  System.out.println("imei_num_"+i+" = " + request.getParameter("imei_num_"+i));
              }
          }else{
              imeiBean.setImei("");
          }
          
         
         hshResultado = requestService.validateModelCondition2(requestBean.getNpRequestId(), 
                                                               imeiBean.getImei(),
                                                               strProductStatus, 
                                                               requestBean.getRequestStateCod(), 
                                                               requestBean.getAccion());

          mensaje =  (String) hshResultado.get("strMessage");
          //error =  (String) hshResultado.get("strError");
          
          System.out.println("Repuesta del RequestServlet"); 
          System.out.println("mensaje = " + mensaje); 
          //System.out.println("error = " + error);
          
          //request.setAttribute("flagDocumento",Constante.SI_GRABO_DOCUMENTO);
          //request.setAttribute("estadoDocumento",Constante.SI_GRABO_DOCUMENTO);
          
          if(error == null){
              if(mensaje != null){
                  out.print(MiUtil.repleaceCharacterSpecialByUNICODE(mensaje));
              }else{
                  out.print("");
              }
            
          }else{
            out.print(MiUtil.repleaceCharacterSpecialByUNICODE(error));        
          }
          
          out.close();
      }
    
    /**
      * <br>Realizado por: <a href="mailto:rensso.martinez@hp.com">Rensso Martínez</a>
      * <br>Fecha: 12/07/2014
      * @see pe.com.nextel.dao.RequestDAO#updateModelCondition()
      */
      public void updateModelCondition(HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException, IOException {
                          
          PrintWriter out = response.getWriter();

          RequestBean requestBean = new RequestBean();
          int numeroFilas = MiUtil.parseInt(request.getParameter("txtNroFilas"));
          requestBean.setNpRequestId( MiUtil.parseLong(MiUtil.trimNotNull(request.getParameter("txtRequestId") )));
          String strProductStatus = MiUtil.trimNotNull(request.getParameter("hdnProductStatus"));
          requestBean.setRequestStateCod(MiUtil.parseInt(MiUtil.trimNotNull(request.getParameter("txtCodigoEstado"))));
              requestBean.setAccion(MiUtil.trimNotNull(request.getParameter("cmbAcciones") ));
          requestBean.setUser(MiUtil.trimNotNull(request.getParameter("strLogin")));

          System.out.println("GeneralServlet --> validateModelCondition2");
          System.out.println("Numero de Filas = " + numeroFilas + "");
          System.out.println("requestBean.getNpRequestId() = "+requestBean.getNpRequestId());
          System.out.println("requestBean.getRequestStateCod() = "+requestBean.getRequestStateCod());
          System.out.println("requestBean.getAccion() = "+requestBean.getAccion());
          System.out.println("requestBean.getUser() = "+requestBean.getUser());
          
          RequestService requestService = new RequestService();
          ArrayList listaImeiSin = new ArrayList();
          HashMap hshResultado = new HashMap();
          
          String error = null;
          String mensaje = null;
          DetailItemBean imeiBean = new DetailItemBean();
          if(numeroFilas > 0){
              for(int i=0; i<numeroFilas; i++){
                  imeiBean.setImei(MiUtil.trimNotNull(request.getParameter("imei_num_"+i)));
                  System.out.println("imei_num_"+i+" = " + request.getParameter("imei_num_"+i));
              }
          }else{
              imeiBean.setImei("");
          }
          
         hshResultado = requestService.updateModelCondition(requestBean.getNpRequestId(), 
                                                            imeiBean.getImei(),
                                                            strProductStatus, 
                                                            requestBean.getRequestStateCod(), 
                                                            requestBean.getAccion(),
                                                            requestBean.getUser());

          mensaje =  (String) hshResultado.get("strMessage");
          //error =  (String) hshResultado.get("strError");
          
          System.out.println("Repuesta del RequestServlet"); 
          System.out.println("mensaje = " + mensaje); 
          //System.out.println("error = " + error);
          
          //request.setAttribute("flagDocumento",Constante.SI_GRABO_DOCUMENTO);
          //request.setAttribute("estadoDocumento",Constante.SI_GRABO_DOCUMENTO);
          
          if(error == null){
              if(mensaje != null){
                  out.print(MiUtil.repleaceCharacterSpecialByUNICODE(mensaje));
              }else{
                  out.print("");
              }
            
          }else{
            out.print(MiUtil.repleaceCharacterSpecialByUNICODE(error));        
          }
          
          out.close();
      }    

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    processRequest(request,response);
    
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    processRequest(request,response);
  }
  
  /**
      * @see GenericServlet#executeDefault(HttpServletRequest, HttpServletResponse)
      */         
    public void executeDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.debug("Antes de hacer el sendRedirect");
            //response.sendRedirect("/portal/page/portal/DISPATCH_SEARCH_REQUEST_STORE_LIST");
            
            response.sendRedirect("/portal/page/portal/inventory/DISPATCH_SEARCH_REQUEST_STORE_LIST");
        } catch (Exception e) {
            logger.error(formatException(e));
        }
    }


}