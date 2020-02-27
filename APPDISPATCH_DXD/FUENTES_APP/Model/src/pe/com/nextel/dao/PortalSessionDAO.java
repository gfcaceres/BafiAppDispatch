package pe.com.nextel.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

import pe.com.nextel.bean.PortalSessionBean;
import pe.com.nextel.util.Constante;
import pe.com.nextel.util.MiUtil;


public class PortalSessionDAO extends GenericDAO{

  protected static Logger logger = Logger.getLogger(PortalSessionDAO.class);
  
  public PortalSessionDAO(){}
  
  
  	/**
	 * Motivo: 	Carga las variables de Sesión de un determinado Usuario.
	 * <br>Realizado por: <a href="mailto:david.coca@nextel.com.pe">David Coca</a>
   * <br>Fecha: 06/07/2007.
	 * <br>Modificado por: <a href="mailto:lee.rosales@nextel.com.pe">Lee Rosales</a>
   * <br>Fecha: 26/09/2007.
   * <br>Realizado por: <a href="mailto:richard.delosreyes@nextel.com.pe">Richard De los Reyes</a>
   * <br>Fecha: 13/10/2007.
	 * @param psb - PortalSessionBean (Variable de Salida)
	 * @param iAppId - Id de la Aplicación (an_rolid). Ej: 27
	 * @param v_phone - Teléfono del Usuario (logueo). Ej: 998102396
	 */
  public int getSecureRol(int intSecureId) throws SQLException, Exception {
		Connection conn = null;
    OracleCallableStatement cstmt = null;
    int intSecureIdAux = -1;
		String sql = "";
		
		
    try {
	           
	   conn = Proveedor.getConnection();
	   
				sql = " { ? = call WEBSALES.FXI_GET_SECURE_ROL(?) }";
				
				cstmt = (OracleCallableStatement)conn.prepareCall(sql);
        cstmt.registerOutParameter(1,OracleTypes.NUMBER);
				cstmt.setInt(2,intSecureId);
				cstmt.executeQuery();
        
        intSecureIdAux = cstmt.getInt(1);
        
        return intSecureIdAux;

    }catch (Exception sqle) {
	    logger.error(formatException(sqle));
        return -1;
	  }finally {
	    closeObjectsDatabase(conn, cstmt, null);
	  }
  }
  
  
  	/**
	 * Motivo: 	Carga las variables de Sesión de un determinado Usuario-Jerarquia de Ventas
	 * <br>Realizado por: <a href="mailto:alexis.gamarra@nextel.com.pe">Alexis Gamarra</a>
   * <br>Fecha: 09/07/2009.
	 * @param psb - PortalSessionBean (Variable de Salida)
	 * @param iAppId - Id de la Aplicación (an_rolid). Ej: 27
	 * @param v_phone - Teléfono del Usuario (logueo). Ej: 998102396
	 */
   public static void ubicar(String v_phone, int iAppId, int intSalesstructid, PortalSessionBean psb) throws SQLException, Exception {
    
    Connection conn = null;
    OracleCallableStatement cstmt = null;
    ResultSet rs = null;
		String sql = "";
		String strChooseLogin = getApplicationDetail(iAppId);
    
		
    try {
	 
	  conn = Proveedor.getConnection();
	
      int n_personid, n_buildingid, n_level, n_userid, n_regionId, n_chnlPartnerId, n_customerId, n_areaId, n_vicepresidenciaId, n_cashDeskId, n_idApp, 
      n_busunitId, n_providerGrpId, n_territoryId, n_rolId;
      
      String v_code, v_login, v_nom_user, v_partnerCodBscs, v_dn_Num, v_areaName, v_defaultInBox, v_appName, v_home, msgError, v_type;
      
      v_code = v_login = v_nom_user = v_partnerCodBscs = v_dn_Num = v_areaName = v_defaultInBox = v_appName = v_home = v_type = msgError = null;
			
      
      sql = "BEGIN WEBSALES.NPSL_LOGIN_PKG.SP_GET_USER_SESSION_VALUES(?,?,?,?,?); END;";
      cstmt = (OracleCallableStatement)conn.prepareCall(sql);
      cstmt.setString(1, v_phone);
      cstmt.setInt(2, intSalesstructid);
      cstmt.setInt(3, iAppId);
      cstmt.registerOutParameter(4, OracleTypes.CURSOR);
      cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
      cstmt.executeUpdate();
      
      msgError = cstmt.getString(5);
      
      if( msgError== null || msgError.startsWith(Constante.SUCCESS_ORA_RESULT) ){
        rs = cstmt.getCursor(4);
         
        if (rs.next()) {
          v_login             = rs.getString("wv_swlogin");
          v_type              = rs.getString("wv_swtype");
          n_personid          = n_userid = rs.getInt("wn_swpersonid");          
          n_level             = rs.getInt("wn_swlevel");
          v_code              = rs.getString("wv_swcode");
          v_nom_user          = rs.getString("wv_nom_user");
          n_busunitId         = rs.getInt("wn_swbusunitid");
          n_regionId          = rs.getInt("wn_npregionid");
          n_chnlPartnerId     = rs.getInt("wn_swchnlpartnerid");
          v_partnerCodBscs    = rs.getString("wv_nppartnercodbscs");
          n_providerGrpId     = rs.getInt("wn_swprovidergrpid");
          n_territoryId       = rs.getInt("wn_swterritoryid");
          //v_dn_Num            = rs.getString("wv_dn_num");
          n_rolId             = rs.getInt("wn_rolid");
          n_buildingid 		    = rs.getInt("wn_npbuildingid");
          n_areaId            = rs.getInt("wn_AreaId");
          v_areaName          = rs.getString("wv_AreaName");
          n_vicepresidenciaId = rs.getInt("wn_VicepresidenciaId");
          v_defaultInBox      = rs.getString("wv_default_inbox");
          n_customerId        = rs.getInt(21); //rs.getInt("wn_swcustomerid");
          //==> Esta variable no se está leyendo: wv_npprocessgroup
          
          psb.setAppId(iAppId);
          //Recuperacion de valores, los comentados son los que ya no se deben tomar en cuenta 
          psb.setPersonid(n_personid);
          psb.setBuildingid(n_buildingid);
          //psb.setRolId(n_rolId);             
          psb.setLevel(n_level);
          psb.setCode(v_code);
          psb.setUserid(n_userid);
          psb.setLogin(v_login);
          //psb.setType(v_type);
          psb.setNom_user(v_nom_user);
          psb.setPartnerCodBscs(v_partnerCodBscs);
          
          //psb.setDn_Num(v_dn_Num); <==
          
          //psb.setBusunitId(n_busunitId);
          psb.setRegionId(n_regionId);
          psb.setChnlPartnerId(n_chnlPartnerId);
          psb.setProviderGrpId(n_providerGrpId);
          //psb.setTerritoryId(n_territoryId);
          psb.setCustomerId(n_customerId);
          psb.setAreaId(n_areaId);
          psb.setAreaName(v_areaName);
          psb.setVicepresidenciaId(n_vicepresidenciaId);
          psb.setDefaultInBox(v_defaultInBox);
          psb.setAppName(v_appName);
          psb.setHome(v_home);
          
          HashMap hshOrder = (new OrderDAO()).getBuildingName(MiUtil.parseLong(""+psb.getBuildingid()),psb.getLogin());               
          msgError = (String)hshOrder.get("strMessage");
          
          if( msgError == null){
            HashMap hshData=(HashMap)hshOrder.get("hshData");
            if (hshData!=null && hshData.size() > 0  ) {                                  
              psb.setTienda(MiUtil.getString((String)hshData.get("wv_name")));
              psb.setRegionTramite(MiUtil.getString((String)hshData.get("wv_regionname")));       
            }
          }
          psb.setMessage(msgError);
        }
      }
		}catch (SQLException sqle) {
			logger.error(formatException(sqle));
      sqle.printStackTrace();
      psb.setMessage(formatException(sqle));
		}catch (Exception sqle) {
      logger.error(formatException(sqle));
      sqle.printStackTrace();
      psb.setMessage(formatException(sqle));
    }finally {
			closeObjectsDatabase(conn, cstmt, rs);
		}
  }
  
  
  /**
	 * Motivo: 	Obtiene el Detalle de una Aplicación, en este caso el dato que nos intersa es el NPSESSIONPAGE.
	 * Ej: /pls/portal30/url/page/nextel/CC_LOGIN
   * <br>Realizado por: <a href="mailto:richard.delosreyes@nextel.com.pe">Richard De los Reyes</a>
   * <br>Fecha: 13/10/2007.
	 * @param nApplicationId - Id de una Aplicación. Ej: 27 (an_rolid)
	 * @return String conteniendo el valor del NPSESSIONPAGE.
	 */
	private static String getApplicationDetail(long nApplicationId) throws SQLException, Exception {
    Connection conn = null;    
    OracleCallableStatement cstmt = null;
    ResultSet rs = null;
    String sql = "BEGIN WEBSALES.SPI_GET_APPLICATION_DET(?,?,?); END;";
    String strMessage  = null;
    String strSessionPage = "";
    try {      
      conn = Proveedor.getConnection();
      cstmt = (OracleCallableStatement)conn.prepareCall(sql);
      cstmt.setLong(1, nApplicationId);
      cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
      cstmt.registerOutParameter(3, OracleTypes.CURSOR);
      cstmt.executeUpdate();      
      strMessage = cstmt.getString(2);
      if(strMessage==null){
         rs = cstmt.getCursor(3);
         if(rs.next()) {
            strSessionPage = rs.getString("npsessionpage");
         }
      }
    }catch (SQLException sqle) {
		 logger.error(formatException(sqle));
	 }catch (Exception sqle) {
	    logger.error(formatException(sqle));  
	 }finally {
	    closeObjectsDatabase(conn, cstmt, rs);
	 }
	 return strSessionPage;
	}
  
  
    //AGAMARRA
  public HashMap doSaveSessionUser(PortalSessionBean objPortalSessionBean) throws SQLException, Exception {
    Connection conn = null;    
    OracleCallableStatement cstmt = null;
    //DbmsOutput dbmsOutput = null;
    
    
    String sql = "BEGIN ORDERS.NP_PERSISTENCE_UTIL_PKG.SP_INS_PERSISTENCE_BY_PORTALID(?,?,?,?,?,"+ //5
                                                                                     "?,?,?,?,?,"+ //10
                                                                                     "?,?,?,?,?,"+ //15
                                                                                     "?,?,?,?,?,"+ //20
                                                                                     "?,?,?,?,?,"+ //25
                                                                                     "?,?,?,?,?,"+ //30
                                                                                     "?,?,?,?,?"+ //35
                                                                                     "); END;";
    HashMap objHashMap = new HashMap();
    String strMessage  = null;
    
    try {
      conn = Proveedor.getConnection();
      
      cstmt = (OracleCallableStatement)conn.prepareCall(sql);
      
      cstmt.setString(1, objPortalSessionBean.getSessionID());
      cstmt.setInt(2, objPortalSessionBean.getUserid());
      cstmt.setString(3, objPortalSessionBean.getNom_user());
      cstmt.setString(4, objPortalSessionBean.getNom_user());
      cstmt.setString(5, objPortalSessionBean.getDn_Num());
      cstmt.setInt(6, objPortalSessionBean.getCustomerId());
      cstmt.setInt(7, objPortalSessionBean.getChnlPartnerId());
      cstmt.setString(8, objPortalSessionBean.getLogin());
      cstmt.setString(9, objPortalSessionBean.getDepartamento());//OracleTypes.NULL);
      cstmt.setInt(10, objPortalSessionBean.getDeafultRolId());//OracleTypes.NULL);
      cstmt.setString(11, objPortalSessionBean.getEmail());
      cstmt.setString(12, objPortalSessionBean.getDefaultInBox());
      cstmt.setInt(13, objPortalSessionBean.getBuildingid());
      cstmt.setInt(14, objPortalSessionBean.getAgentId());
      cstmt.setInt(15, objPortalSessionBean.getExtension());
      cstmt.setInt(16, objPortalSessionBean.getUserParentId());
      cstmt.setInt(17, objPortalSessionBean.getRegionId());
      cstmt.setInt(18, objPortalSessionBean.getIncidentGroupId());
      cstmt.setInt(19, objPortalSessionBean.getAppId());
      cstmt.setInt(20, objPortalSessionBean.getPersonid());
      cstmt.setString(21, objPortalSessionBean.getCreatedby());
      cstmt.setInt(22, objPortalSessionBean.getLevel());
      cstmt.setString(23, objPortalSessionBean.getCode());
      cstmt.setString(24, objPortalSessionBean.getPartnerCodBscs());
      cstmt.setInt(25, objPortalSessionBean.getProviderGrpId());//Valor necesario para Jerarquía de Ventas
      cstmt.setInt(26, objPortalSessionBean.getAreaId());
      cstmt.setString(27, objPortalSessionBean.getAreaName());
      cstmt.setInt(28, objPortalSessionBean.getVicepresidenciaId());
      cstmt.setInt(29, objPortalSessionBean.getCashDeskId());
      cstmt.setString(30, objPortalSessionBean.getAppName());
      cstmt.setString(31, objPortalSessionBean.getHome());
      cstmt.setString(32, objPortalSessionBean.getTienda());
      cstmt.setString(33, objPortalSessionBean.getRegionTramite());
      cstmt.setInt(34, objPortalSessionBean.getSalesStructId());//Valor necesario para Jerarquía de Ventas
      cstmt.registerOutParameter(35, OracleTypes.VARCHAR);
      
      cstmt.executeUpdate();
      
      strMessage = cstmt.getString(35);
      objHashMap.put("strMessage",strMessage);
   }catch (Exception sqle) {
	    logger.error(formatException(sqle));
      objHashMap.put("strMessage",formatException(sqle));
	 }finally {
	    closeObjectsDatabase(conn, cstmt, null);
	 }
	 return objHashMap;
  }
  
   //AGAMARRA
  public HashMap getUserSession(String strSessionId) throws SQLException, Exception {
    Connection conn = null;    
    OracleCallableStatement cstmt = null;
    //DbmsOutput dbmsOutput = null;
    PortalSessionBean objPortalSessionBean = new PortalSessionBean();
    
    String sql = "BEGIN ORDERS.NP_PERSISTENCE_UTIL_PKG.SP_GET_PERSISTENCE_BY_PORTALID(?,?,?,?,?,"+ //5
                                                                                     "?,?,?,?,?,"+ //10
                                                                                     "?,?,?,?,?,"+ //15
                                                                                     "?,?,?,?,?,"+ //20
                                                                                     "?,?,?,?,?,"+ //25
                                                                                     "?,?,?,?,?,"+ //30
                                                                                     "?,?,?,?,?);"+ //35
                                                                                     "END;";
    HashMap objHashMap = new HashMap();
    String strMessage  = null;
    
    try {
      conn = Proveedor.getConnection();
      //conn.setAutoCommit(false);
      cstmt = (OracleCallableStatement)conn.prepareCall(sql);
      
      //dbmsOutput = new DbmsOutput(conn);
      //dbmsOutput.enable(1000000);
      
      System.out.println("Búsqueda de la sesión : " + strSessionId);
      
      cstmt.setString(1, strSessionId);
      
      cstmt.registerOutParameter(2,OracleTypes.NUMBER);
      cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
      cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
      cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
      cstmt.registerOutParameter(6, OracleTypes.NUMBER);
      cstmt.registerOutParameter(7, OracleTypes.NUMBER);
      cstmt.registerOutParameter(8, OracleTypes.VARCHAR);
      cstmt.registerOutParameter(9, OracleTypes.VARCHAR);
      cstmt.registerOutParameter(10, OracleTypes.NUMBER);
      cstmt.registerOutParameter(11, OracleTypes.VARCHAR);
      cstmt.registerOutParameter(12, OracleTypes.VARCHAR);
      cstmt.registerOutParameter(13, OracleTypes.NUMBER);
      cstmt.registerOutParameter(14, OracleTypes.NUMBER);
      cstmt.registerOutParameter(15, OracleTypes.NUMBER);
      cstmt.registerOutParameter(16, OracleTypes.NUMBER);
      cstmt.registerOutParameter(17, OracleTypes.NUMBER);
      cstmt.registerOutParameter(18, OracleTypes.NUMBER);
      cstmt.registerOutParameter(19, OracleTypes.NUMBER);
      cstmt.registerOutParameter(20, OracleTypes.NUMBER);
      cstmt.registerOutParameter(21, OracleTypes.VARCHAR);
      cstmt.registerOutParameter(22, OracleTypes.NUMBER);
      cstmt.registerOutParameter(23, OracleTypes.VARCHAR);
      cstmt.registerOutParameter(24, OracleTypes.VARCHAR);
      cstmt.registerOutParameter(25, OracleTypes.NUMBER);
      cstmt.registerOutParameter(26, OracleTypes.NUMBER);
      cstmt.registerOutParameter(27, OracleTypes.VARCHAR);
      cstmt.registerOutParameter(28, OracleTypes.NUMBER);
      cstmt.registerOutParameter(29, OracleTypes.NUMBER);
      cstmt.registerOutParameter(30, OracleTypes.VARCHAR);
      cstmt.registerOutParameter(31, OracleTypes.VARCHAR);
      cstmt.registerOutParameter(32, OracleTypes.VARCHAR);
      cstmt.registerOutParameter(33, OracleTypes.VARCHAR);
      
      //Se debe agregar para soportar el nuevo campo deNP_PERSISTENCE_OBJECT: NPSALESSTRUCTID:
      cstmt.registerOutParameter(34, OracleTypes.NUMBER);
      
      cstmt.registerOutParameter(35, OracleTypes.VARCHAR);
      
      cstmt.execute();
      
      strMessage = cstmt.getString(35);
      objHashMap.put("strMessage",strMessage);
      
      if( strMessage == null ){
        /**Recuperar los datos del Usuarios**/
        objPortalSessionBean.setUserid(cstmt.getInt(2));
        objPortalSessionBean.setNom_user(cstmt.getString(3));
        objPortalSessionBean.setNom_user(cstmt.getString(4));
        objPortalSessionBean.setDn_Num(cstmt.getString(5));
        objPortalSessionBean.setCustomerId(cstmt.getInt(6));
        objPortalSessionBean.setChnlPartnerId( cstmt.getInt(7));
        objPortalSessionBean.setLogin( cstmt.getString(8));
        objPortalSessionBean.setDepartamento(cstmt.getString(9));
        objPortalSessionBean.setDeafultRolId(cstmt.getInt(10));
        objPortalSessionBean.setEmail(cstmt.getString(11));
        objPortalSessionBean.setDefaultInBox( cstmt.getString(12));
        objPortalSessionBean.setBuildingid( cstmt.getInt(13));
        objPortalSessionBean.setAgentId(cstmt.getInt(14));
        objPortalSessionBean.setExtension(cstmt.getInt(15));
        objPortalSessionBean.setUserParentId(cstmt.getInt(16));
        objPortalSessionBean.setRegionId( cstmt.getInt(17));
        objPortalSessionBean.setIncidentGroupId(cstmt.getInt(18));
        objPortalSessionBean.setAppId(cstmt.getInt(19));
        objPortalSessionBean.setPersonid(cstmt.getInt(20));
        objPortalSessionBean.setCreatedby(cstmt.getString(21));
        objPortalSessionBean.setLevel(cstmt.getInt(22));
        objPortalSessionBean.setCode(cstmt.getString(23));
        objPortalSessionBean.setPartnerCodBscs(cstmt.getString(24));
        objPortalSessionBean.setProviderGrpId(cstmt.getInt(25));
        objPortalSessionBean.setAreaId(cstmt.getInt(26));
        objPortalSessionBean.setAreaName(cstmt.getString(27));
        objPortalSessionBean.setVicepresidenciaId(cstmt.getInt(28));
        objPortalSessionBean.setCashDeskId(cstmt.getInt(29));
        objPortalSessionBean.setAppName(cstmt.getString(30));
        objPortalSessionBean.setHome(cstmt.getString(31));
        objPortalSessionBean.setTienda(cstmt.getString(32));
        objPortalSessionBean.setRegionTramite(cstmt.getString(33));
        objPortalSessionBean.setSalesStructId(cstmt.getInt(34));
        
        objHashMap.put("objPortalSessionBean",objPortalSessionBean);
      }
   }catch (Exception sqle) {
	    logger.error(formatException(sqle));
      objHashMap.put("strMessage",formatException(sqle));
	 }finally {
	    closeObjectsDatabase(conn, cstmt, null);
	 }
	 return objHashMap;
  }
  
  
  //AGAMARRA 
 public int getUserId(String strLogin) throws SQLException, Exception{
  Connection conn = null;
  
  String sqlStr =  " { ? = call WEBSALES.NPSL_LOGIN_PKG.FX_GET_PERSONID( ? ) } ";
  
  OracleCallableStatement  cstmt = null;
  int intUserId =0;
  try{
    conn = Proveedor.getConnection();
    cstmt = (OracleCallableStatement)conn.prepareCall(sqlStr);
    
    cstmt.registerOutParameter(1, Types.NUMERIC);
    cstmt.setString(2, strLogin);
    
    cstmt.execute();
    intUserId = cstmt.getInt(1);
  }catch (Exception e) {
   logger.error(formatException(e));
   e.printStackTrace();
  }
  finally{
    closeObjectsDatabase(conn, cstmt, null);  
  }                                  
  return(intUserId);
 }
 
 
  //AGAMARRA
  public HashMap getUserApp(String strLogin)  throws SQLException, Exception{
    Connection conn = null;
    String sql = "BEGIN WEBSALES.NPSL_LOGIN_PKG.SP_GET_USER_APP(?, ?, ?, ?); END;";
    OracleCallableStatement cstmt = null;
    ResultSet rs = null;
    HashMap contAux = null;
    try{
      conn = Proveedor.getConnection();
      cstmt = (OracleCallableStatement)conn.prepareCall(sql);
      
      cstmt.setString(1, strLogin);
      cstmt.registerOutParameter(2, OracleTypes.CURSOR);
      cstmt.registerOutParameter(3, OracleTypes.INTEGER);
      cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
      cstmt.execute();
      
      String msgError = cstmt.getString(4);
      int wn_num_reg = 0;
      Integer wc_datos = null;
      if(msgError== null || msgError.equals("") || msgError.startsWith(Constante.SUCCESS_ORA_RESULT)){
        wn_num_reg = cstmt.getInt(3);//an_num_reg
        
        rs = cstmt.getCursor(2);
        if(rs.next()){
          wc_datos = new Integer(rs.getInt(3)); //npappid
          
          contAux = new HashMap();
          contAux.put("wn_num_reg", new Integer(wn_num_reg));
          contAux.put("wc_datos", wc_datos);
        }
      }
      contAux.put("wn_num_reg", new Integer(wn_num_reg));
      contAux.put("wc_datos", wc_datos);
    }catch(Exception e){
      logger.error(formatException(e));
      e.printStackTrace();
      return(null);
    }finally {
	    closeObjectsDatabase(conn, cstmt, rs);
	  }
    return(contAux);
  } 
  
  
  //AGAMARRA
 public int getProviderId(int wn_userid)  throws SQLException, Exception{
    Connection conn =null;
    
    String strSql = "BEGIN WEBSALES.SPI_GET_PROVIDERID_BY_USERID(?, ?, ?); END;";
    //String strSql = "BEGIN WEBSALES.NP_SALES_STRUCTURE01_PKG.SP_GET_PROVIDERID_BY_USERID(?, ?, ?); END;";
    
    OracleCallableStatement cstmt =null;
    ResultSet rs = null;
    int int_providergrpid = 0;
	
    try{
	
	  conn = Proveedor.getConnection();
	  cstmt = (OracleCallableStatement)conn.prepareCall(strSql);
	   
      cstmt.setInt(1, wn_userid);
      cstmt.registerOutParameter(2, OracleTypes.NUMERIC);
      cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
      cstmt.execute();
      
      String msgError = cstmt.getString(3);
      System.out.println("msgError="+msgError);
      
      if(msgError== null || msgError.equals("") || msgError.startsWith(Constante.SUCCESS_ORA_RESULT)){
        int_providergrpid = cstmt.getInt(2);//an_swprovidergrpid
      }
    }catch(Exception e){
      logger.error(formatException(e));
      e.printStackTrace();
      return(-1);
    }finally {
	    closeObjectsDatabase(conn, cstmt, rs);
	  }
    return(int_providergrpid);
  }
  
  
  //AGAMARRA
  public HashMap getPositionList(int wn_swprovidergrpid)  throws SQLException, Exception{
    Connection conn =null;
    String sql = "BEGIN WEBSALES.SPI_GET_POSITION_LIST(?, ?, ?, ?); END;";
    //String sql = "BEGIN WEBSALES.NP_SALES_STRUCTURE01_PKG.SP_GET_POSITION_LIST(?, ?, ?, ?); END;";
    OracleCallableStatement cstmt = null;
    ResultSet rs = null;
    HashMap contAux = null;
    
    try{    
	
	  conn = Proveedor.getConnection();
	  cstmt = (OracleCallableStatement)conn.prepareCall(sql);
	  
      cstmt.setInt(1, wn_swprovidergrpid);;
      cstmt.registerOutParameter(2, OracleTypes.CURSOR);
      cstmt.registerOutParameter(3, OracleTypes.INTEGER);
      cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
      cstmt.execute();
      
      String msgError = cstmt.getString(4);
      int wn_num_reg = 0;
      if(msgError== null || msgError.equals("") || msgError.startsWith(Constante.SUCCESS_ORA_RESULT)){
        wn_num_reg = cstmt.getInt(3);//an_num_reg
        
        rs = cstmt.getCursor(2);
        
        ArrayList wc_sales_struct = new ArrayList(); 
        ArrayList arrName = new ArrayList();
        
        while(rs.next()){
          Integer int_structId = new Integer(rs.getInt(1)); //npappid
          wc_sales_struct.add(int_structId);
          
          String strName = rs.getString(2);
          arrName.add(strName);
        }
        
        contAux = new HashMap();
        contAux.put("wn_num_pos", new Integer(wn_num_reg));
        contAux.put("wc_sales_struct", wc_sales_struct);
        contAux.put("arrName", arrName);
      }
    }catch(Exception e){
      logger.error(formatException(e));
      e.printStackTrace();
      return(null);
    }finally {
	    closeObjectsDatabase(conn, cstmt, rs);
	  }
    return(contAux);
  }
  
}