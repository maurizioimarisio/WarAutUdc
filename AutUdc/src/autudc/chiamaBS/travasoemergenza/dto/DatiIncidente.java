package autudc.chiamaBS.travasoemergenza.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;


public class DatiIncidente extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codiceProgetto = "";
	private String codiceIncidente = "";
	private String descrIncidente = "";
	private String riferimentoIncidente = "";

	
	public DatiIncidente() {}

	public DatiIncidente(
			String codiceProgetto, 
			String codiceIncidente,
			String descrIncidente, 
			String riferimentoIncidente) {
		this.codiceProgetto = codiceProgetto;
		this.codiceIncidente = codiceIncidente;
		this.descrIncidente = descrIncidente;
		this.riferimentoIncidente = riferimentoIncidente;
	}

	public String getCodiceProgetto() {
		return codiceProgetto;
	}

	public String getCodiceIncidente() {
		return codiceIncidente;
	}

	public String getDescrIncidente() {
		return descrIncidente;
	}

	public String getRiferimentoIncidente() {
		return riferimentoIncidente;
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
