package pe.com.nextel.bean.estadoinstalacion;

import java.util.ArrayList;
import java.util.List;

public class SubMotivoBean {
    
    private Long id;
    private String nombre;
    private String estado;
    private String creadoPor;
    private String fechaCreacion;
    private String modificadoPor;
    private String fechaModificacion;
    private List<DetalleBean> detalles;
        
    public SubMotivoBean() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public List<DetalleBean> getDetalles() {
        if (detalles == null){
            detalles =  new ArrayList<DetalleBean>();
        }
        return detalles;
    }
}
