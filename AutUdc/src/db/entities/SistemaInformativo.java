package db.entities;

import java.io.Serializable;

public class SistemaInformativo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codice = "";  
	private String descrizione = "";
	private String isDefault = "";

	public SistemaInformativo(String codice, String descrizione,
			String isDefault) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.isDefault = isDefault;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getIsDefault() {
		return isDefault;
	}
	
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
}
