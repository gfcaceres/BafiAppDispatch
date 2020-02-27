package pe.com.nextel.bean;

import java.io.Serializable;

import pe.com.nextel.util.GenericObject;


public class RequestBean extends GenericObject implements Serializable {    
    private long npRequestId;
    private long npOrderId;
    
    private long requestId;
    private int location;
    private String user;
    private String requestNumber;
    private String orderNumber;
    private String orderPay;
    private String requestStateDescription;
    private int requestStateCod;
    private int businessDivision;
    private String category;
    private String subCategory;
    private String facturaNumber;
    private String razonSocial;
    private String requestDateBegin;
    private String requestDateEnd;
    private String codBSCS;
    private String requestReasonCode;
    private String requestReasonDescription;
    private String observacion;
    
    private String requestDate;
    private String envioCourier;
    private String provieneDe;
    private int indice;

    private String dateProgram;
    private String dateDeliveryActual;
    private String deliveyTo;
    private long codigoEntregado;
    
    private String accion;
    
    private String estadoSolicitud;
    


  public void setNpRequestId(long npRequestId)
  {
    this.npRequestId = npRequestId;
  }


  public long getNpRequestId()
  {
    return npRequestId;
  }


  public void setNpOrderId(long npOrderId)
  {
    this.npOrderId = npOrderId;
  }


  public long getNpOrderId()
  {
    return npOrderId;
  }


  public void setLocation(int location)
  {
    this.location = location;
  }


  public int getLocation()
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


  


  public void setOrderNumber(String orderNumber)
  {
    this.orderNumber = orderNumber;
  }


  public String getOrderNumber()
  {
    return orderNumber;
  }


  public void setOrderPay(String orderPay)
  {
    this.orderPay = orderPay;
  }


  public String getOrderPay()
  {
    return orderPay;
  }

  public void setBusinessDivision(int businessDivision)
  {
    this.businessDivision = businessDivision;
  }


  public int getBusinessDivision()
  {
    return businessDivision;
  }


  public void setCategory(String category)
  {
    this.category = category;
  }


  public String getCategory()
  {
    return category;
  }


  public void setSubCategory(String subCategory)
  {
    this.subCategory = subCategory;
  }


  public String getSubCategory()
  {
    return subCategory;
  }


  public void setFacturaNumber(String facturaNumber)
  {
    this.facturaNumber = facturaNumber;
  }


  public String getFacturaNumber()
  {
    return facturaNumber;
  }


  public void setRazonSocial(String razonSocial)
  {
    this.razonSocial = razonSocial;
  }


  public String getRazonSocial()
  {
    return razonSocial;
  }


  public void setRequestDateBegin(String requestDateBegin)
  {
    this.requestDateBegin = requestDateBegin;
  }


  public String getRequestDateBegin()
  {
    return requestDateBegin;
  }


  public void setRequestDateEnd(String requestDateEnd)
  {
    this.requestDateEnd = requestDateEnd;
  }


  public String getRequestDateEnd()
  {
    return requestDateEnd;
  }


  public void setCodBSCS(String codBSCS)
  {
    this.codBSCS = codBSCS;
  }


  public String getCodBSCS()
  {
    return codBSCS;
  }


  public void setRequestNumber(String requestNumber)
  {
    this.requestNumber = requestNumber;
  }


  public String getRequestNumber()
  {
    return requestNumber;
  }


  public void setRequestDate(String requestDate)
  {
    this.requestDate = requestDate;
  }


  public String getRequestDate()
  {
    return requestDate;
  }


  public void setEnvioCourier(String envioCourier)
  {
    this.envioCourier = envioCourier;
  }


  public String getEnvioCourier()
  {
    return envioCourier;
  }


  public void setProvieneDe(String provieneDe)
  {
    this.provieneDe = provieneDe;
  }


  public String getProvieneDe()
  {
    return provieneDe;
  }


  public void setRequestId(long requestId)
  {
    this.requestId = requestId;
  }


  public long getRequestId()
  {
    return requestId;
  }


  public void setIndice(int indice)
  {
    this.indice = indice;
  }


  public int getIndice()
  {
    return indice;
  }


  public void setRequestStateDescription(String requestStateDescription)
  {
    this.requestStateDescription = requestStateDescription;
  }


  public String getRequestStateDescription()
  {
    return requestStateDescription;
  }


  public void setRequestStateCod(int requestStateCod)
  {
    this.requestStateCod = requestStateCod;
  }


  public int getRequestStateCod()
  {
    return requestStateCod;
  }
  

  public void setRequestReasonCode(String requestReasonCode)
  {
    this.requestReasonCode = requestReasonCode;
  }


  public String getRequestReasonCode()
  {
    return requestReasonCode;
  }


  public void setRequestReasonDescription(String requestReasonDescription)
  {
    this.requestReasonDescription = requestReasonDescription;
  }


  public String getRequestReasonDescription()
  {
    return requestReasonDescription;
  }


  public void setDateProgram(String dateProgram)
  {
    this.dateProgram = dateProgram;
  }


  public String getDateProgram()
  {
    return dateProgram;
  }


  public void setDateDeliveryActual(String dateDeliveryActual)
  {
    this.dateDeliveryActual = dateDeliveryActual;
  }


  public String getDateDeliveryActual()
  {
    return dateDeliveryActual;
  }


  public void setObservacion(String observacion)
  {
    this.observacion = observacion;
  }


  public String getObservacion()
  {
    return observacion;
  }


  public void setDeliveyTo(String deliveyTo)
  {
    this.deliveyTo = deliveyTo;
  }


  public String getDeliveyTo()
  {
    return deliveyTo;
  }


  public void setAccion(String accion)
  {
    this.accion = accion;
  }


  public String getAccion()
  {
    return accion;
  }


  public void setCodigoEntregado(long codigoEntregado)
  {
    this.codigoEntregado = codigoEntregado;
  }


  public long getCodigoEntregado()
  {
    return codigoEntregado;
  }


  public void setEstadoSolicitud(String estadoSolicitud)
  {
    this.estadoSolicitud = estadoSolicitud;
  }


  public String getEstadoSolicitud()
  {
    return estadoSolicitud;
  }
  

   
}