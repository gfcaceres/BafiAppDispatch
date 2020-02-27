package pe.com.nextel.bean;

import java.io.Serializable;

import pe.com.nextel.util.GenericObject;


public class DetailItemBean extends GenericObject implements Serializable{
  private String idEquipo; 
  private String numero;
  private String imei;
  private String sim;
  private String almacen;
  private String subInventario;
  private String organitationId;
  
  public DetailItemBean(){}


  public void setNumero(String numero)
  {
    this.numero = numero;
  }


  public String getNumero()
  {
    return numero;
  }


  public void setImei(String imei)
  {
    this.imei = imei;
  }


  public String getImei()
  {
    return imei;
  }


  public void setSim(String sim)
  {
    this.sim = sim;
  }


  public String getSim()
  {
    return sim;
  }


  public void setAlmacen(String almacen)
  {
    this.almacen = almacen;
  }


  public String getAlmacen()
  {
    return almacen;
  }


  public void setSubInventario(String subInventario)
  {
    this.subInventario = subInventario;
  }


  public String getSubInventario()
  {
    return subInventario;
  }


  public void setIdEquipo(String idEquipo)
  {
    this.idEquipo = idEquipo;
  }


  public String getIdEquipo()
  {
    return idEquipo;
  }


  public void setOrganitationId(String organitationId)
  {
    this.organitationId = organitationId;
  }


  public String getOrganitationId()
  {
    return organitationId;
  }

/*
  public void setArrayIdEquipo(String[] arrayIdEquipo)
  {
    this.arrayIdEquipo = arrayIdEquipo;
  }


  public String[] getArrayIdEquipo()
  {
    return arrayIdEquipo;
  }


  public void setArrayNumero(String[] arrayNumero)
  {
    this.arrayNumero = arrayNumero;
  }


  public String[] getArrayNumero()
  {
    return arrayNumero;
  }


  public void setArrayImei(String[] arrayImei)
  {
    this.arrayImei = arrayImei;
  }


  public String[] getArrayImei()
  {
    return arrayImei;
  }


  public void setArraySim(String[] arraySim)
  {
    this.arraySim = arraySim;
  }


  public String[] getArraySim()
  {
    return arraySim;
  }


  public void setArrayAlmacen(String[] arrayAlmacen)
  {
    this.arrayAlmacen = arrayAlmacen;
  }


  public String[] getArrayAlmacen()
  {
    return arrayAlmacen;
  }


  public void setArraySubInventario(String[] arraySubInventario)
  {
    this.arraySubInventario = arraySubInventario;
  }


  public String[] getArraySubInventario()
  {
    return arraySubInventario;
  }
*/

 


  
  
  
  
}