package pe.com.nextel.beans;

public class Persona 
{
  private String nombre;
  private String apellido;
  
  public Persona(){}


  public void setNombre(String nombre)
  {
    this.nombre = nombre;
  }


  public String getNombre()
  {
    return nombre;
  }


  public void setApellido(String apellido)
  {
    this.apellido = apellido;
  }


  public String getApellido()
  {
    return apellido;
  }
}