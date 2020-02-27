package pe.com.nextel.ejb;

import java.sql.SQLException;

import java.util.HashMap;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import pe.com.nextel.bean.PortalSessionBean;
import pe.com.nextel.dao.PortalSessionDAO;
import pe.com.nextel.dao.UsuarioDAO;


public class SEJBSessionBean implements SessionBean 
{
    private SessionContext _context;
    public HashMap objHashMap;
    UsuarioDAO objUsuarioDAO = null;
    
  public void ejbCreate()
  {
      objHashMap  = new HashMap();
      System.out.println("El EJB de Sesión dio inicio");
  }

  public void ejbActivate()
  {
    System.out.println("[SEJBSessionBean][ejbActivate()]");
  }

  public void ejbPassivate()
  {
    System.out.println("[SEJBSessionBean][ejbPassivate()]");
  }

  public void ejbRemove()
  {
    System.out.println("[SEJBSessionBean][ejbRemove()]");
  }

  public void setSessionContext(SessionContext ctx)
  {
    _context = ctx;
  }
  
  public int getSecureRol(int intSecureId) throws  Exception, SQLException{
    PortalSessionDAO objPortalSessionDAO = new PortalSessionDAO();
    return objPortalSessionDAO.getSecureRol(intSecureId);
  }
  
      //AGAMARRA
  public PortalSessionBean PortalSessionDAOubicar(String v_phone, int n_rolid, int intSalesstructid){
    PortalSessionBean s = new PortalSessionBean();
    //System.out.println("--------------SEJBSessionBean.PortalSessionDAOubicar--------------");
    try {
      PortalSessionDAO.ubicar(v_phone, n_rolid, intSalesstructid, s);
      //System.out.println(BeanUtils.describe(s));
    }catch (Exception e) {
      e.printStackTrace();
    }
    return s;
  }
  
  
    public HashMap addUserSession(PortalSessionBean sessionBean) throws Exception, SQLException{
      System.out.println("Registramos al usuario : " + sessionBean.getLogin() + " " + sessionBean.getNom_user());
      //System.out.println("Cantidad de usuarios registrados : " + objHashMap.size() );
      PortalSessionDAO objPortalSessionDAO = new PortalSessionDAO();
      return objPortalSessionDAO.doSaveSessionUser(sessionBean);
  }
  
    /*public synchronized void setUserSession(PortalSessionBean sessionBean) {
    objHashMap.put(sessionBean.getSessionID(),sessionBean);
  
   }*/
    public void setUserSession(PortalSessionBean sessionBean) {
    objHashMap.put(sessionBean.getSessionID(),sessionBean);
    
    }
   
     public HashMap getSessionId() throws Exception, SQLException  {
      objUsuarioDAO = new UsuarioDAO();
      return objUsuarioDAO.getSessionId();
  }
  
  public PortalSessionBean getUserSession(String portalID) throws Exception, SQLException{
      PortalSessionDAO objPortalSessionDAO = new PortalSessionDAO();
      PortalSessionBean objSessionBean = null;
      HashMap objHashMap = objPortalSessionDAO.getUserSession(portalID);
      if( objHashMap!= null && objHashMap.get("strMessage")!= null){
        System.out.println("La obtención de la sesión no se realizó con éxito : " + objHashMap.get("strMessage"));
        return null;
      }else{
        objSessionBean  = (PortalSessionBean)objHashMap.get("objPortalSessionBean");
        return objSessionBean;  
      }
      
  }
  
    
    public int getUsersConnected() throws Exception, SQLException{
    return objHashMap.size();
  }
  
    //AGAMARRA
  public HashMap getUserApp(String strLogin) throws Exception, SQLException{
    PortalSessionDAO objPortalSessionDAO = new PortalSessionDAO();
    return objPortalSessionDAO.getUserApp(strLogin);
  }


  public int getUserId(String strLogin) throws Exception, SQLException{
    PortalSessionDAO objPortalSessionDAO = new PortalSessionDAO();
    return objPortalSessionDAO.getUserId(strLogin);
  }
  
  //AGAMARRA
  public int getProviderId(int wn_userid) throws Exception, SQLException{
    PortalSessionDAO objPortalSessionDAO = new PortalSessionDAO();
    return objPortalSessionDAO.getProviderId(wn_userid);
  }
  
  
    //AGAMARRA
  public HashMap getPositionList(int wn_swprovidergrpid) throws Exception, SQLException{
    PortalSessionDAO objPortalSessionDAO = new PortalSessionDAO();
    return objPortalSessionDAO.getPositionList(wn_swprovidergrpid);
  }

  
  
}