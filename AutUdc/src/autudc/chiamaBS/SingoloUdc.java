package autudc.chiamaBS;

import java.io.Serializable;


public class SingoloUdc extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codiceUdc = "";
	private String userIdResponsabile = "";
        private String nominativoIdResponsabile = "";
	private String descrizione = "";
	private String motivoDeroga = "";
	private String zona = "";

	
	public SingoloUdc() {
		super();
	}

	public SingoloUdc(String codiceUdc, String userIdResponsabile,
			  String nomeIdResponsabile, String descrizione) {
		super();
		this.codiceUdc = codiceUdc;
		this.userIdResponsabile = userIdResponsabile;
                this.nominativoIdResponsabile = nomeIdResponsabile;
		this.descrizione = descrizione;

	}
	
	public String getCodiceUdc() {
		return codiceUdc;
	}
	
	public void setCodiceUdc(String codiceUdc) {
		this.codiceUdc = codiceUdc;
	}
	
	public String getUserIdResponsabile() {
		return userIdResponsabile;
	}
	
	public void setUserIdResponsabile(String userIdResponsabile) {
		this.userIdResponsabile = userIdResponsabile;
	}
	public String getNomeIdResponsabile() {
		return nominativoIdResponsabile;
	}

	public void setNomeIdResponsabile(String nomeIdResponsabile) {
		this.nominativoIdResponsabile = nomeIdResponsabile;
	}
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getMotivoDeroga() {
		return motivoDeroga;
	}

	public void setMotivoDeroga(String motivoDeroga) {
		this.motivoDeroga = motivoDeroga;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
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
