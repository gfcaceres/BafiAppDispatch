package pe.com.nextel.bean;

import java.util.Date;

public class InstalacionBean {
    
    private Long   id;
    private Long   solicitudOLId;
    private Long   agendamientoId;
    private String nombreContacto1;
    private String nombreCOntacto2;
    private Long   telefonoContacto1;
    private Long   telefonoContacto2;
    private String horarioComunicacion1;
    private String horarioComunicacion2;
    private String correoContacto;
    private String motivoInstalacion;
    private Long   motivoId;
    private String submotivoInstalacion;
    private Long   subMotivoId;
    private String detalle;
    private Long   detalleId;
    private String comentario;
    private String mensaje;
    private Long   ordenId;
    private String creadoPor;
    private String modificadoPor;
    
    public InstalacionBean() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAgendamientoId(Long agendamientoId) {
        this.agendamientoId = agendamientoId;
    }

    public Long getAgendamientoId() {
        return agendamientoId;
    }

    public void setNombreContacto1(String nombreContacto1) {
        this.nombreContacto1 = nombreContacto1;
    }

    public String getNombreContacto1() {
        return nombreContacto1;
    }

    public void setNombreCOntacto2(String nombreCOntacto2) {
        this.nombreCOntacto2 = nombreCOntacto2;
    }

    public String getNombreCOntacto2() {
        return nombreCOntacto2;
    }

    public void setTelefonoContacto1(Long telefonoContacto1) {
        this.telefonoContacto1 = telefonoContacto1;
    }

    public Long getTelefonoContacto1() {
        return telefonoContacto1;
    }

    public void setTelefonoContacto2(Long telefonoContacto2) {
        this.telefonoContacto2 = telefonoContacto2;
    }

    public Long getTelefonoContacto2() {
        return telefonoContacto2;
    }

    public void setHorarioComunicacion1(String horarioComunicacion1) {
        this.horarioComunicacion1 = horarioComunicacion1;
    }

    public String getHorarioComunicacion1() {
        return horarioComunicacion1;
    }

    public void setHorarioComunicacion2(String horarioComunicacion2) {
        this.horarioComunicacion2 = horarioComunicacion2;
    }

    public String getHorarioComunicacion2() {
        return horarioComunicacion2;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setMotivoInstalacion(String motivoInstalacion) {
        this.motivoInstalacion = motivoInstalacion;
    }

    public String getMotivoInstalacion() {
        return motivoInstalacion;
    }

    public void setMotivoId(Long motivoId) {
        this.motivoId = motivoId;
    }

    public Long getMotivoId() {
        return motivoId;
    }

    public void setSubmotivoInstalacion(String submotivoInstalacion) {
        this.submotivoInstalacion = submotivoInstalacion;
    }

    public String getSubmotivoInstalacion() {
        return submotivoInstalacion;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalleId(Long detalleId) {
        this.detalleId = detalleId;
    }

    public Long getDetalleId() {
        return detalleId;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setOrdenId(Long ordenId) {
        this.ordenId = ordenId;
    }

    public Long getOrdenId() {
        return ordenId;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }


    public void setSolicitudOLId(Long solicitudOLId) {
        this.solicitudOLId = solicitudOLId;
    }

    public Long getSolicitudOLId() {
        return solicitudOLId;
    }

    public void setSubMotivoId(Long subMotivoId) {
        this.subMotivoId = subMotivoId;
    }

    public Long getSubMotivoId() {
        return subMotivoId;
    }
    
    @Override
    public String toString() {
        return "InstalacionBean{" +
                "id=" + id +
                ", solicitudOLId=" + solicitudOLId +
                ", agendamientoId=" + agendamientoId +
                ", motivoInstalacion='" + motivoInstalacion + '\'' +
                ", motivoId=" + motivoId +
                ", submotivoInstalacion='" + submotivoInstalacion + '\'' +
                ", subMotivoId=" + subMotivoId +
                ", detalle='" + detalle + '\'' +
                ", detalleId=" + detalleId +
                ", comentario='" + comentario + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", ordenId=" + ordenId + '\'' +
                '}';
    } 
}
