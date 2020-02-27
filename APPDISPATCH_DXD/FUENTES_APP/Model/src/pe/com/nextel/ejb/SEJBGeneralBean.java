package pe.com.nextel.ejb;

import java.sql.SQLException;

import java.util.HashMap;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.log4j.Logger;

import pe.com.nextel.dao.GeneralDAO;
import pe.com.nextel.dao.RequestDAO;


public class SEJBGeneralBean implements SessionBean {
  
  private SessionContext context;
  private GeneralDAO    objGeneralDAO   = null;
  private RequestDAO objRequestDAO = null;
  protected static Logger logger = Logger.getLogger(SEJBGeneralBean.class);
  
 public SEJBGeneralBean()
 {
  
 }
  
  public void ejbCreate() {  
    /* Creamos las intancias a los DAO's */    
    objGeneralDAO = new GeneralDAO();
    objRequestDAO = new RequestDAO();
    
    logger.debug("Se inició componente SEJBGeneralBean");
  }
  
  public void ejbActivate() throws EJBException {
        System.out.println("[SEJBRequestBean][ejbActivate()]");
    }

    public void ejbPassivate() throws EJBException {
        System.out.println("[SEJBRequestBean][ejbPassivate()]");
    }

    public void ejbRemove() throws EJBException {
        System.out.println("[SEJBRequestBean][ejbRemove()]");
    }

    public void setSessionContext(SessionContext ctx) throws EJBException {
        context = ctx;
    }        
    
    public HashMap getDivisionList() throws  SQLException, Exception{
      return objGeneralDAO.getDivisionList();
    }
    
    public HashMap getCategoryList(long lSolutionId) throws  SQLException, Exception{
      return objGeneralDAO.getCategoryList(lSolutionId);
    }
    
    public HashMap getSubCategoryList(String strCategoria, long lSolutionId)throws  SQLException, Exception{
      return objGeneralDAO.getSubCategoryList(strCategoria,lSolutionId);
    }
    
    
  public HashMap getValueNpTable(String strTableName) throws SQLException, Exception {
		return objGeneralDAO.getValueNpTable(strTableName);
	}
  
  public HashMap getActionDetail(String strParam, int intState)throws SQLException, Exception
  {
    return objGeneralDAO.getActionDetail(strParam,intState);
  }

  public HashMap getReasonDetail(String strParam, int intState)throws SQLException, Exception
  {
    return objGeneralDAO.getReasonDetail(strParam,intState);
  }
	
  
  public int get_AttChannel_Struct(int intSalesstructid) throws Exception
  {
   return objGeneralDAO.get_AttChannel_Struct(intSalesstructid);
  }
  
  public HashMap getLocationList() throws SQLException, Exception{
     return objGeneralDAO.getLocationList();
  }
  
  public HashMap getUserList(int buildingId) throws SQLException, Exception{
    return objGeneralDAO.getUserList(buildingId);
  }
  
}