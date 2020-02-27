package pe.com.nextel.ejb;

import java.sql.SQLException;

import java.util.HashMap;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import pe.com.nextel.dao.ItemDAO;


public class SEJBItemBean implements SessionBean 
{

  private ItemDAO itemDao = null;
  public void ejbCreate()
  {
  itemDao = new ItemDAO();
  }

  public void ejbActivate()
  {
  }

  public void ejbPassivate()
  {
  }

  public void ejbRemove()
  {
  }

  public void setSessionContext(SessionContext ctx)
  {
  }
  
  

    public HashMap getImeiDet(String strImeiNum)throws  SQLException, Exception {
          return itemDao.getImeiDet(strImeiNum);
   }
}