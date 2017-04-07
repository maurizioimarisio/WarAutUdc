package autudc.chiamaBS.ude.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;

public class DatiContenutoUdc extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ssaPrinc = "";
	private String ssaAltri = "";
	private String elencoAcronimi = "";
	private String applicazioni = "";
	
	public DatiContenutoUdc(){}
	
	public DatiContenutoUdc(String ssaPrinc, String ssaAltri,
			String elencoAcronimi, String applicazioni) {
		super();
		this.ssaPrinc = ssaPrinc;
		this.ssaAltri = ssaAltri;
		this.elencoAcronimi = elencoAcronimi;
		this.applicazioni = applicazioni;
	}

	public String getSsaPrinc() {
		return ssaPrinc;
	}

	public String getSsaAltri() {
		return ssaAltri;
	}

	public String getElencoAcronimi() {
		return elencoAcronimi;
	}

	public String getApplicazioni() {
		return applicazioni;
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
