package pe.com.nextel.bean;

import java.io.Serializable;

import pe.com.nextel.util.GenericObject;


public class RequestManualBean extends GenericObject implements Serializable
{

  private String ordenNumber;
  private String cod;
  private String codBSCS;
  private String documentType;
  private String RUC;
  private String deliveryAddress;
  private String documentNumber;
  private String razonSolcial;
  
  private String dateIssue;
  private String location;
  private String user;
  private String status;
  
  private String observation;
  
  private int requestId;
  private String requestNumber;
  
  private int buildingId;

  
  
  public RequestManualBean(){}
  

  public void setOrdenNumber(String ordenNumber)
  {
    this.ordenNumber = ordenNumber;
  }


  public String getOrdenNumber()
  {
    return ordenNumber;
  }


  public void setCod(String cod)
  {
    this.cod = cod;
  }


  public String getCod()
  {
    return cod;
  }


  public void setCodBSCS(String codBSCS)
  {
    this.codBSCS = codBSCS;
  }


  public String getCodBSCS()
  {
    return codBSCS;
  }


  public void setDocumentType(String documentType)
  {
    this.documentType = documentType;
  }


  public String getDocumentType()
  {
    return documentType;
  }


  public void setRUC(String RUC)
  {
    this.RUC = RUC;
  }


  public String getRUC()
  {
    return RUC;
  }


  public void setDeliveryAddress(String deliveryAddress)
  {
    this.deliveryAddress = deliveryAddress;
  }


  public String getDeliveryAddress()
  {
    return deliveryAddress;
  }


  public void setDocumentNumber(String documentNumber)
  {
    this.documentNumber = documentNumber;
  }


  public String getDocumentNumber()
  {
    return documentNumber;
  }


  public void setRazonSolcial(String razonSolcial)
  {
    this.razonSolcial = razonSolcial;
  }


  public String getRazonSolcial()
  {
    return razonSolcial;
  }


  public void setObservation(String observation)
  {
    this.observation = observation;
  }


  public String getObservation()
  {
    return observation;
  }
  

  public void setDateIssue(String dateIssue)
  {
    this.dateIssue = dateIssue;
  }


  public String getDateIssue()
  {
    return dateIssue;
  }


  public void setLocation(String location)
  {
    this.location = location;
  }


  public String getLocation()
  {
    return location;
  }


  public void setUser(String user)
  {
    this.user = user;
  }


  public String getUser()
  {
    return user;
  }


  public void setStatus(String status)
  {
    this.status = status;
  }


  public String getStatus()
  {
    return status;
  }


  public void setRequestId(int requestId)
  {
    this.requestId = requestId;
  }


  public int getRequestId()
  {
    return requestId;
  }


  public void setRequestNumber(String requestNumber)
  {
    this.requestNumber = requestNumber;
  }


  public String getRequestNumber()
  {
    return requestNumber;
  }


  public void setBuildingId(int buildingId)
  {
    this.buildingId = buildingId;
  }


  public int getBuildingId()
  {
    return buildingId;
  }

 
  
}