package autudc.chiamaBS.ude.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;

public class DatiPesatura extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codiceStato = "";
	private String descrizioneStato = "";
	private String codiceEsito = "";
	private String tempoStimato = "";
	private String tempoDisp = "";
	private String numOggettiPesati = "";
	private String numPacchettiPesati = "";
	private String numRicicli = "";
	private String dataUltimaPesatura = "";
	private String oraUltimaPesatura = "";

	public DatiPesatura() {}
	
	public DatiPesatura(
			String codiceStato, 
			String descrizioneStato, 
			String codiceEsito,
			String tempoStimato, 
			String tempoDisp, 
			String numOggettiPesati,
			String numPacchettiPesati, 
			String numRicicli,
			String dataUltimaPesatura, 
			String oraUltimaPesatura
	) {
		super();
		this.codiceStato = codiceStato;
		this.descrizioneStato = descrizioneStato;
		this.codiceEsito = codiceEsito;
		this.tempoStimato = tempoStimato;
		this.tempoDisp = tempoDisp;
		this.numOggettiPesati = numOggettiPesati;
		this.numPacchettiPesati = numPacchettiPesati;
		this.numRicicli = numRicicli;
		this.dataUltimaPesatura = dataUltimaPesatura;
		this.oraUltimaPesatura = oraUltimaPesatura;
	}

	public String getCodiceStato() {
		return codiceStato;
	}

	public String getDescrizioneStato() {
		return descrizioneStato;
	}

	public String getCodiceEsito() {
		return codiceEsito;
	}

	public String getTempoStimato() {
		return tempoStimato;
	}

	public String getTempoDisp() {
		return tempoDisp;
	}

	public String getNumOggettiPesati() {
		return numOggettiPesati;
	}

	public String getNumPacchettiPesati() {
		return numPacchettiPesati;
	}

	public String getNumRicicli() {
		return numRicicli;
	}

	public String getDataUltimaPesatura() {
		return dataUltimaPesatura;
	}

	public String getOraUltimaPesatura() {
		return oraUltimaPesatura;
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
