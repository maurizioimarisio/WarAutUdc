package autudc.chiamaBS.ude.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;

public class PrerequisitiUdc extends CommonDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String piattaforma 		= "";
	private String codiceUdc 		= "";
	private String artemis			= "";
	private String responsabile		= "";
	private String descrizione		= "";
	private String data				= "";
	
	public PrerequisitiUdc() {
		super();
	}

	public PrerequisitiUdc(
			String piattaforma, 
			String codiceUdc,
			String artemis, 
			String responsabile, 
			String descrizione, 
			String data) {
		super();
		this.piattaforma = piattaforma;
		this.codiceUdc = codiceUdc;
		this.artemis = artemis;
		this.responsabile = responsabile;
		this.descrizione = descrizione;
		this.data = data;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getPiattaforma() {
		return piattaforma;
	}

	public String getCodiceUdc() {
		return codiceUdc;
	}

	public String getArtemis() {
		return artemis;
	}

	public String getResponsabile() {
		return responsabile;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getData() {
		return data;
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
