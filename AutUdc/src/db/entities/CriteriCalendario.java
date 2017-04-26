package db.entities;

import java.io.Serializable;

public class CriteriCalendario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String sistemaInformativo = "";
	private String piattaforma = "";
	private String criterio = "";
	private String colonna1 = "";
	private String colonna2 = "";
	
	public String getSistemaInformativo() {
		return sistemaInformativo;
	}
	
	public void setSistemaInformativo(String sistemaInformativo) {
		this.sistemaInformativo = sistemaInformativo;
	}
	
	public String getPiattaforma() {
		return piattaforma;
	}
	
	public void setPiattaforma(String piattaforma) {
		this.piattaforma = piattaforma;
	}
	
	public String getCriterio() {
		return criterio;
	}
	
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}
	
	public String getColonna1() {
		return colonna1;
	}
	
	public void setColonna1(String colonna1) {
		this.colonna1 = colonna1;
	}
	
	public String getColonna2() {
		return colonna2;
	}
	
	public void setColonna2(String colonna2) {
		this.colonna2 = colonna2;
	}
	
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();	
				
		stringBuffer.append("strutture.grigliaDelChange.CriteriCalendario");
		stringBuffer.append("\n sistemaInformativo: "+sistemaInformativo);
		stringBuffer.append("\n piattaforma: "+piattaforma);
		stringBuffer.append("\n criterio: "+criterio);
		stringBuffer.append("\n colonna1: "+colonna1);
		stringBuffer.append("\n colonna2: "+colonna2);

		return stringBuffer.toString();
	}
	
}
