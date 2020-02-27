package pe.com.nextel.bean;

import java.io.Serializable;

import java.util.List;

import pe.com.nextel.util.GenericObject;


public class EquipmentBean extends GenericObject implements Serializable {
    
    private String wn_contador;
    private String wv_reqolitemid; 
    private String wv_itemtypeid;
    private String wn_productid;
    private String wv_segment1;
    private String wv_name;
    private String wv_reqolimei;
    private String wv_reqimeisim;
    private String wv_returneqimeisim;
    private String wv_modalitysell;
    private String wv_productstatus;
    private String wn_quantity;
    private String wv_accessory;
    
    private String wn_reasonid;
    private String wv_reason_name;
    private String wv_transnnumber;
    private String wv_solution;
    private String wv_npreqolimei;
    private String wv_npreqimeisim;
    
    //private DeviceBean deviceBean;
    private List listaDeviceBean;
    
    public EquipmentBean(){
      //listaDeviceBean =  new ArrayList();
    }


 


  public void setWv_reqolitemid(String wv_reqolitemid)
  {
    this.wv_reqolitemid = wv_reqolitemid;
  }


  public String getWv_reqolitemid()
  {
    return wv_reqolitemid;
  }


  public void setWv_itemtypeid(String wv_itemtypeid)
  {
    this.wv_itemtypeid = wv_itemtypeid;
  }


  public String getWv_itemtypeid()
  {
    return wv_itemtypeid;
  }

  public void setWv_segment1(String wv_segment1)
  {
    this.wv_segment1 = wv_segment1;
  }


  public String getWv_segment1()
  {
    return wv_segment1;
  }


  public void setWv_name(String wv_name)
  {
    this.wv_name = wv_name;
  }


  public String getWv_name()
  {
    return wv_name;
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


  public void setWv_returneqimeisim(String wv_returneqimeisim)
  {
    this.wv_returneqimeisim = wv_returneqimeisim;
  }


  public String getWv_returneqimeisim()
  {
    return wv_returneqimeisim;
  }


  public void setWv_modalitysell(String wv_modalitysell)
  {
    this.wv_modalitysell = wv_modalitysell;
  }


  public String getWv_modalitysell()
  {
    return wv_modalitysell;
  }


  public void setWv_productstatus(String wv_productstatus)
  {
    this.wv_productstatus = wv_productstatus;
  }


  public String getWv_productstatus()
  {
    return wv_productstatus;
  }


  public void setWn_quantity(String wn_quantity)
  {
    this.wn_quantity = wn_quantity;
  }


  public String getWn_quantity()
  {
    return wn_quantity;
  }


  public void setWv_accessory(String wv_accessory)
  {
    this.wv_accessory = wv_accessory;
  }


  public String getWv_accessory()
  {
    return wv_accessory;
  }


  public void setWn_reasonid(String wn_reasonid)
  {
    this.wn_reasonid = wn_reasonid;
  }


  public String getWn_reasonid()
  {
    return wn_reasonid;
  }


  public void setWv_reason_name(String wv_reason_name)
  {
    this.wv_reason_name = wv_reason_name;
  }


  public String getWv_reason_name()
  {
    return wv_reason_name;
  }


  public void setWv_transnnumber(String wv_transnnumber)
  {
    this.wv_transnnumber = wv_transnnumber;
  }


  public String getWv_transnnumber()
  {
    return wv_transnnumber;
  }


  public void setWv_solution(String wv_solution)
  {
    this.wv_solution = wv_solution;
  }


  public String getWv_solution()
  {
    return wv_solution;
  }


  public void setWv_npreqolimei(String wv_npreqolimei)
  {
    this.wv_npreqolimei = wv_npreqolimei;
  }


  public String getWv_npreqolimei()
  {
    return wv_npreqolimei;
  }


  public void setWv_npreqimeisim(String wv_npreqimeisim)
  {
    this.wv_npreqimeisim = wv_npreqimeisim;
  }


  public String getWv_npreqimeisim()
  {
    return wv_npreqimeisim;
  }

  public void setWn_contador(String wn_contador)
  {
    this.wn_contador = wn_contador;
  }


  public String getWn_contador()
  {
    return wn_contador;
  }


  public void setWn_productid(String wn_productid)
  {
    this.wn_productid = wn_productid;
  }


  public String getWn_productid()
  {
    return wn_productid;
  }


  public void setListaDeviceBean(List listaDeviceBean)
  {
    this.listaDeviceBean = listaDeviceBean;
  }


  public List getListaDeviceBean()
  {
    return listaDeviceBean;
  }


  


  
  
  
}