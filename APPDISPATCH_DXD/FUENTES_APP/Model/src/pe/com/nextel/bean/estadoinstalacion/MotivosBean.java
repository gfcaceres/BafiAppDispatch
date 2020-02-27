package pe.com.nextel.bean.estadoinstalacion;

import java.util.List;

import pe.com.nextel.bean.estadoinstalacion.MotivoBean;


public class MotivosBean{
    public MotivosBean() {
        super();
    }

    private List<MotivoBean> motivos;

    public void setMotivos(List<MotivoBean> motivos) {
        this.motivos = motivos;
    }

    public List<MotivoBean> getMotivos() {
        return motivos;
    }
}
