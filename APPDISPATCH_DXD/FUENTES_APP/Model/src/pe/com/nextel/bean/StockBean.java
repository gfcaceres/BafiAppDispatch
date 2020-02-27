package pe.com.nextel.bean;

import java.io.Serializable;

import pe.com.nextel.util.GenericObject;


public class StockBean extends GenericObject implements Serializable {

    private String an_specificationid;
    private String an_productid;          
    private String an_npdispatchplaceid;
    private String av_organizationoperation;
    private String an_salesstructporigenid;
    private String av_pro_status;    
    private String av_message_stock;
    private String av_flag_stock;
    private String av_message;
      
    
    
    public StockBean(){}


  public void setAn_specificationid(String an_specificationid)
  {
    this.an_specificationid = an_specificationid;
  }


  public String getAn_specificationid()
  {
    return an_specificationid;
  }


  public void setAn_productid(String an_productid)
  {
    this.an_productid = an_productid;
  }


  public String getAn_productid()
  {
    return an_productid;
  }


  public void setAn_npdispatchplaceid(String an_npdispatchplaceid)
  {
    this.an_npdispatchplaceid = an_npdispatchplaceid;
  }


  public String getAn_npdispatchplaceid()
  {
    return an_npdispatchplaceid;
  }


  public void setAv_organizationoperation(String av_organizationoperation)
  {
    this.av_organizationoperation = av_organizationoperation;
  }


  public String getAv_organizationoperation()
  {
    return av_organizationoperation;
  }


  public void setAn_salesstructporigenid(String an_salesstructporigenid)
  {
    this.an_salesstructporigenid = an_salesstructporigenid;
  }


  public String getAn_salesstructporigenid()
  {
    return an_salesstructporigenid;
  }


  public void setAv_pro_status(String av_pro_status)
  {
    this.av_pro_status = av_pro_status;
  }


  public String getAv_pro_status()
  {
    return av_pro_status;
  }


  public void setAv_message_stock(String av_message_stock)
  {
    this.av_message_stock = av_message_stock;
  }


  public String getAv_message_stock()
  {
    return av_message_stock;
  }


  public void setAv_flag_stock(String av_flag_stock)
  {
    this.av_flag_stock = av_flag_stock;
  }


  public String getAv_flag_stock()
  {
    return av_flag_stock;
  }


  public void setAv_message(String av_message)
  {
    this.av_message = av_message;
  }


  public String getAv_message()
  {
    return av_message;
  }

    
    

  
}