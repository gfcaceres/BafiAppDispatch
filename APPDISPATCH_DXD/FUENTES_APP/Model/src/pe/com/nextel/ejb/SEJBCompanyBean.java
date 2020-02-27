package pe.com.nextel.ejb;

import java.sql.SQLException;

import java.util.HashMap;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import pe.com.nextel.bean.CompanyBean;
import pe.com.nextel.dao.CompanyDAO;


public class SEJBCompanyBean implements SessionBean 
{

  private SessionContext context;
  private CompanyDAO    objCompanyDAO  = null;
  
  public void ejbCreate()
  {
    /** creamos la instancia **/
    objCompanyDAO = new CompanyDAO();
  }

  public void ejbActivate()
  {
    System.out.println("[SEJBCompanyBean][ejbActivate()]");
  }

  public void ejbPassivate()
  {
    System.out.println("[SEJBCompanyBean][ejbPassivate()]");
  }

  public void ejbRemove()
  {
    System.out.println("[SEJBCompanyBean][ejbRemove()]");
  }

  public void setSessionContext(SessionContext ctx)
  {
    context = ctx;
  }
  
  public HashMap getTipoDocumentoList() throws  SQLException, Exception{
      return objCompanyDAO.getTipoDocumentoList();
  }
  
  public HashMap getTipoCompaniaList() throws  SQLException, Exception{
      return objCompanyDAO.getTipoCompaniaList();
  }
  
  public HashMap getCompaniaList(CompanyBean companyBean) throws  SQLException, Exception{
    return objCompanyDAO.getCompaniaList(companyBean);
  }
  
  
}