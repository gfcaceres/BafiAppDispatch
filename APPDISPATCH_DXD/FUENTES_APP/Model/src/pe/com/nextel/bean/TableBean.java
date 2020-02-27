package pe.com.nextel.bean;

import java.io.Serializable;

import pe.com.nextel.util.GenericObject;


public class TableBean extends GenericObject  implements Serializable {
    private String npTable;
    private String npValue;
    private String npValueDesc;

    public TableBean() {
    }

    public void setNpTable(String npTable) {
        this.npTable = npTable;
    }

    public String getNpTable() {
        return this.npTable;
    }

    public void setNpValue(String npValue) {
        this.npValue = npValue;
    }

    public String getNpValue() {
        return this.npValue;
    }

    public void setNpValueDesc(String npValueDesc) {
        this.npValueDesc = npValueDesc;
    }

    public String getNpValueDesc() {
        return this.npValueDesc;
    }
}
