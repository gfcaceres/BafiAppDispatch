package pe.com.nextel.bean;

import java.io.Serializable;

import pe.com.nextel.util.GenericObject;


public class CompanyBean extends GenericObject implements Serializable{

  public CompanyBean(){}
  
  private String documentType;
  private String documentNumbre;
  private int companyCod;
  private String razonSocial;
  private String companyName;
  private String BSCSCod;
  private String compamyType;


  public void setDocumentType(String documentType)
  {
    this.documentType = documentType;
  }


  public String getDocumentType()
  {
    return documentType;
  }


  public void setDocumentNumbre(String documentNumbre)
  {
    this.documentNumbre = documentNumbre;
  }


  public String getDocumentNumbre()
  {
    return documentNumbre;
  }


 


  public void setRazonSocial(String razonSocial)
  {
    this.razonSocial = razonSocial;
  }


  public String getRazonSocial()
  {
    return razonSocial;
  }

  public void setBSCSCod(String BSCSCod)
  {
    this.BSCSCod = BSCSCod;
  }


  public String getBSCSCod()
  {
    return BSCSCod;
  }


  public void setCompamyType(String compamyType)
  {
    this.compamyType = compamyType;
  }


  public String getCompamyType()
  {
    return compamyType;
  }


  public void setCompanyCod(int companyCod)
  {
    this.companyCod = companyCod;
  }


  public int getCompanyCod()
  {
    return companyCod;
  }


  public void setCompanyName(String companyName)
  {
    this.companyName = companyName;
  }


  public String getCompanyName()
  {
    return companyName;
  }



  
  
}