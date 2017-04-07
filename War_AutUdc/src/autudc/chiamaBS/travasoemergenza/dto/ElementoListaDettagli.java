package autudc.chiamaBS.travasoemergenza.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;


public class ElementoListaDettagli extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String numeroDettaglio = "";
	private String tipoDettaglio = "";
	private String statoDettaglio = "";
	private String statoDettaglioBreve = "";
	private String statoDettaglioEsteso = "";
	private String noteAttuatore = "";
	
	public ElementoListaDettagli() {}
	
	public ElementoListaDettagli(
			String numeroDettaglio, 
			String tipoDettaglio,
			String statoDettaglio, 
			String statoDettaglioBreve,
			String statoDettaglioEsteso, 
			String noteAttuatore) {
		this.numeroDettaglio = numeroDettaglio;
		this.tipoDettaglio = tipoDettaglio;
		this.statoDettaglio = statoDettaglio;
		this.statoDettaglioBreve = statoDettaglioBreve;
		this.statoDettaglioEsteso = statoDettaglioEsteso;
		this.noteAttuatore = noteAttuatore;
	}
	
	public String getNumeroDettaglio() {
		return numeroDettaglio;
	}
	
	public String getTipoDettaglio() {
		return tipoDettaglio;
	}
	
	public String getStatoDettaglio() {
		return statoDettaglio;
	}
	
	public String getStatoDettaglioBreve() {
		return statoDettaglioBreve;
	}
	
	public String getStatoDettaglioEsteso() {
		return statoDettaglioEsteso;
	}
	
	public String getNoteAttuatore() {
		return noteAttuatore;
	}
	
	public boolean equals(Object object) {
		return super.equals(object);
	}

	public int hashCode() {
		return super.hashCode();
	}

	public String toString() {
		return super.toString();
	}
}
