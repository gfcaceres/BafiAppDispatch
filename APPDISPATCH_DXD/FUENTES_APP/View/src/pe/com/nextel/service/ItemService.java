package pe.com.nextel.service;

import java.io.Serializable;

import java.util.HashMap;

import javax.naming.Context;

import javax.rmi.PortableRemoteObject;

import pe.com.nextel.ejb.SEJBItemRemote;
import pe.com.nextel.ejb.SEJBItemRemoteHome;
import pe.com.nextel.util.MiUtil;


public class ItemService extends GenericService implements Serializable {
	
  
  public static SEJBItemRemote getSEJBGeneralRemote() {
        try {
            final Context context = MiUtil.getInitialContext();
            final SEJBItemRemoteHome remoteHome = (SEJBItemRemoteHome) PortableRemoteObject.narrow(context.lookup("SEJBItemDispatch"), SEJBItemRemoteHome.class);
            return remoteHome.create();            
        } catch (Exception ex) {
            System.out.println("Exception : [ItemService][getSEJBGeneralRemote][" + ex.getMessage() + "]");
            return null;
        }
    }	
    
    	public HashMap getImeiDet(String strImeiNum){
      HashMap objHashMapResultado = new HashMap();
      //ArrayList listaRequest = new ArrayList();
     try {
        //hshDataMap = objGeneralDAO.getDivisionList();
        //listaRequest = getSEJBGeneralRemote().getImeiDet(strImeiNum);
        objHashMapResultado = getSEJBGeneralRemote().getImeiDet(strImeiNum);
      }catch(Throwable t){
        logger.error(t);
      }
        return objHashMapResultado;
    }
    
    
}