package pe.com.nextel.bean;

import java.io.Serializable;

import pe.com.nextel.util.GenericObject;


public class DeviceBean extends GenericObject implements Serializable {
    
    private String wv_reqolitemid_device;
    private String wv_reqolitemdeviceid;
    private String wv_reqolimei;
    private String wv_reqimeisim;
    private String wv_reqalmacen;
    private String wv_reqsubinventario;
    private String wv_reqorganizationid;
     

    public DeviceBean(){
      
    }


  public void setWv_reqolitemid_device(String wv_reqolitemid_device)
  {
    this.wv_reqolitemid_device = wv_reqolitemid_device;
  }


  public String getWv_reqolitemid_device()
  {
    return wv_reqolitemid_device;
  }


  public void setWv_reqolitemdeviceid(String wv_reqolitemdeviceid)
  {
    this.wv_reqolitemdeviceid = wv_reqolitemdeviceid;
  }


  public String getWv_reqolitemdeviceid()
  {
    return wv_reqolitemdeviceid;
  }


  public void setWv_reqolimei(String wv_reqolimei)
  {
    this.wv_reqolimei = wv_reqolimei;
  }


  public String getWv_reqolimei()
  {
    return wv_reqolimei;
  }


  public void setWv_reqimeisim(String wv_reqimeisim)
  {
    this.wv_reqimeisim = wv_reqimeisim;
  }


  public String getWv_reqimeisim()
  {
    return wv_reqimeisim;
  }


  public void setWv_reqalmacen(String wv_reqalmacen)
  {
    this.wv_reqalmacen = wv_reqalmacen;
  }


  public String getWv_reqalmacen()
  {
    return wv_reqalmacen;
  }


  public void setWv_reqsubinventario(String wv_reqsubinventario)
  {
    this.wv_reqsubinventario = wv_reqsubinventario;
  }


  public String getWv_reqsubinventario()
  {
    return wv_reqsubinventario;
  }


  public void setWv_reqorganizationid(String wv_reqorganizationid)
  {
    this.wv_reqorganizationid = wv_reqorganizationid;
  }


  public String getWv_reqorganizationid()
  {
    return wv_reqorganizationid;
  }
}