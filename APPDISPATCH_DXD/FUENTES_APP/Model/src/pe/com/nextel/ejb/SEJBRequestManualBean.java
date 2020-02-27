package pe.com.nextel.ejb;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import pe.com.nextel.bean.DominioBean;
import pe.com.nextel.bean.RequestManualBean;
import pe.com.nextel.bean.StockBean;
import pe.com.nextel.dao.RequestManualDAO;


public class SEJBRequestManualBean implements SessionBean 
{
  private SessionContext context;
  private RequestManualDAO    objRequestManualDAO  = null;
  
  public void ejbCreate(){
    objRequestManualDAO = new RequestManualDAO();
  }

  public void ejbActivate()
  {
    System.out.println("[SEJBRequestManualBean][ejbActivate()]");
  }

  public void ejbPassivate()
  {
    System.out.println("[SEJBRequestManualBean][ejbPassivate()]");
  }

  public void ejbRemove()
  {
    System.out.println("[SEJBRequestManualBean][ejbRemove()]");
  }

  public void setSessionContext(SessionContext ctx)
  {
    context = ctx;
  }
  
  public HashMap getModalityList() throws  SQLException, Exception{
      return objRequestManualDAO.getModalityList();
  }
  
  public HashMap getEstadoList() throws  SQLException, Exception{
      return objRequestManualDAO.getEstadoList();
  }
  
  public HashMap getAccesoriosList()throws  SQLException, Exception{
      return objRequestManualDAO.getAccesoriosList();
  }
  
  public HashMap getTipoDocumentoList()throws  SQLException, Exception{
      return objRequestManualDAO.getTipoDocumentoList();
  }
  
  public HashMap getSolucionNegocioList()throws  SQLException, Exception{
    return objRequestManualDAO.getSolucionNegocioList();
  }
  
  public HashMap getLineaProductoListByIdsolucion(DominioBean solucionNegocio) throws  SQLException, Exception{
    return objRequestManualDAO.getLineaProductoListByIdsolucion(solucionNegocio);
  }
  
  public HashMap getProductoListByLineaproductoid(DominioBean dominioBean) throws  SQLException, Exception{
    return objRequestManualDAO.getProductoListByLineaproductoid(dominioBean);
  }
  
  public HashMap getMotivosListByUserLogin(String userLogin) throws  SQLException, Exception{
    return objRequestManualDAO.getMotivosListByUserLogin(userLogin);
  }
  
  public HashMap saveRequestManual(RequestManualBean requestManualBean, List listaDetailRequestManual ) throws  SQLException, Exception{
    return objRequestManualDAO.saveRequestManual(requestManualBean,listaDetailRequestManual);
  
  } 
  
  public HashMap getRepuestosList(DominioBean solucionNegocio)throws  SQLException, Exception{
    return objRequestManualDAO.getRepuestosList(solucionNegocio);
  }
  
  public HashMap getListaEstadoSolicitud()throws  SQLException, Exception{
    return objRequestManualDAO.getListaEstadoSolicitud();
  }
  
  public StockBean validarStock(StockBean stockBean) throws  SQLException, Exception{
    return objRequestManualDAO.validarStock(stockBean);
  }
  
 public HashMap obtenerFlagValidarProductosAndAccesorio(int lineaProducto) throws  SQLException, Exception{
    return objRequestManualDAO.obtenerFlagValidarProductosAndAccesorio(lineaProducto);
  }
  
}