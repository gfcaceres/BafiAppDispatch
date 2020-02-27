package pe.com.nextel.ejb.tde; 

import java.rmi.RemoteException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.EJBObject;

import pe.com.nextel.bean.RequestBean;
import pe.com.nextel.util.RequestHashMap;


public interface SEJBRequestRemote extends EJBObject {

    ArrayList getPendingRequest(long lngbuildingId) throws SQLException,
                                                           RemoteException,
                                                           Exception;

    HashMap getRequestAttention(long lngrequestId) throws SQLException,
                                                          RemoteException,
                                                          Exception;

    HashMap doSaveRequest(RequestHashMap objHashMap) throws SQLException,
                                                            Exception,
                                                            RemoteException;

    HashMap getEstadosList() throws SQLException, Exception, RemoteException;

    HashMap getRequestAllList(RequestBean requestBean) throws SQLException,
                                                              Exception,
                                                              RemoteException;

    HashMap updGenerateDoc(long lngrequestId,
                           String strLogin) throws SQLException, Exception,
                                                   RemoteException;

    HashMap saveRequestOl(long lngRequestolid, long lngReqolstatusid,
                          long lngRequestolitemid, String strAction,
                          String strLogin, ArrayList lstItemDevice,
                          String strScheduledate, String strRealdate,
                          String strNotes, long lngDeliveredto,
                          String strProductStatus,String strReason,
                          Long motivoId, Long subMotivoId, Long detalleId,
                          String comentario, Long ordenId) throws SQLException,
                                                   Exception,
                                                   RemoteException;

    HashMap getSIM(String imei, String avchOrigin) throws SQLException, Exception,
                                       RemoteException;

    HashMap validarSim(String an_nprequestolid) throws SQLException, Exception,
                                                       RemoteException;

    HashMap validateModelCondition(long lngRequestolid, long lngReqolstatusid,
                                   long lngRequestolitemid, String strAction,
                                   String strLogin, ArrayList lstItemDevice,
                                   String strProductStatus) throws SQLException,
                                                                   Exception,
                                                                   RemoteException;

    HashMap validateModelCondition2(long lngRequestolid, String strImei,
                                    String strCondicion, long lrequestStateCod,
                                    String strAccion) throws SQLException,
                                                             Exception,
                                                             RemoteException;

    HashMap updateModelCondition(long lngRequestolid, String strImei,
                                 String strCondicion, long lrequestStateCod,
                                 String strAccion,
                                 String strLogin) throws SQLException,
                                                         Exception,
                                                         RemoteException;

    String validateSubInventoryCode(String strSubInventoryCode,
                                    String numRequest) throws Exception,
                                                              SQLException;

    String validateModelResquested(String strImei,
                                   String strNumRequest) throws Exception,
                                                                SQLException;

    int getFlagRequestOlTde(long requestId)throws SQLException, Exception, RemoteException;

}