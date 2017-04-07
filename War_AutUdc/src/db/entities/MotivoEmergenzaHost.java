package db.entities;

import java.io.Serializable;


public class MotivoEmergenzaHost implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	String sistemaInformativo = "";
	String codMotivoEmergenza = "";
	String descrMotivoEmergenza = "";
	
	public String getSistemaInformativo() {
		return sistemaInformativo;
	}
	
	public void setSistemaInformativo(String sistemaInformativo) {
		this.sistemaInformativo = sistemaInformativo;
	}
	
	public String getCodMotivoEmergenza() {
		return codMotivoEmergenza;
	}
	
	public void setCodMotivoEmergenza(String codMotivoEmergenza) {
		this.codMotivoEmergenza = codMotivoEmergenza;
	}
	
	public String getDescrMotivoEmergenza() {
		return descrMotivoEmergenza;
	}
	
	public void setDescrMotivoEmergenza(String descrMotivoEmergenza) {
		this.descrMotivoEmergenza = descrMotivoEmergenza;
	}

}
