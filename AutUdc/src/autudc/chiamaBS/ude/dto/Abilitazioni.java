package autudc.chiamaBS.ude.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;

public class Abilitazioni extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String flagRichiestaIt = "N";
	private String flagEsitoIt = "N";
	private String flagAutorizzazioneSw = "N";
	private String flagImpattoRil = "N";
	private String flagImprocrast = "N";
	private String flagVincoli = "N";
	private String flagAutorizzazioneGeco = "N";
	private String flagRichiestaDeroga = "N";
	private String flagAutorizzazioneDeroga = "N";
	private String flagCtrlPerformance = "N";
//	private String flagOverrideDataLimite = "N";
//	private String flgUdcEmergenzaSpeciale = "N";
	private String flagBloccoPromo = "N";
	private String flagUdcGiaPromosso = "N";
	private String flagErroreBloccante = "N";

	public Abilitazioni(){}
	
	public Abilitazioni(
			String flagRichiestaIt, 
			String flagEsitoIt,
			String flagAutorizzazioneSw, 
			String flagImpattoRil,
			String flagImprocrast, 
			String flagVincoli,
			String flagAutorizzazioneGeco, 
			String flagRichiestaDeroga,
			String flagAutorizzazioneDeroga, 
			String flagCtrlPerformance, 
//			String flagOverrideDataLimite,
//			String flgUdcEmergenzaSpeciale,
			String flagBloccoPromo,
			String flagUdcGiaPromosso,
			String flagErroreBloccante
	) {
		super();
		this.flagRichiestaIt = flagRichiestaIt;
		this.flagEsitoIt = flagEsitoIt;
		this.flagAutorizzazioneSw = flagAutorizzazioneSw;
		this.flagImpattoRil = flagImpattoRil;
		this.flagImprocrast = flagImprocrast;
		this.flagVincoli = flagVincoli;
		this.flagAutorizzazioneGeco = flagAutorizzazioneGeco;
		this.flagRichiestaDeroga = flagRichiestaDeroga;
		this.flagAutorizzazioneDeroga = flagAutorizzazioneDeroga;
		this.flagCtrlPerformance = flagCtrlPerformance;
//		this.flagOverrideDataLimite = flagOverrideDataLimite;
//		this.flgUdcEmergenzaSpeciale = flgUdcEmergenzaSpeciale;
		this.flagBloccoPromo = flagBloccoPromo;
		this.flagUdcGiaPromosso = flagUdcGiaPromosso;
		this.flagErroreBloccante = flagErroreBloccante;
	}

	public String getFlagRichiestaIt() {
		return flagRichiestaIt;
	}

	public String getFlagEsitoIt() {
		return flagEsitoIt;
	}

	public String getFlagAutorizzazioneSw() {
		return flagAutorizzazioneSw;
	}

	public String getFlagImpattoRil() {
		return flagImpattoRil;
	}

	public String getFlagImprocrast() {
		return flagImprocrast;
	}

	public String getFlagVincoli() {
		return flagVincoli;
	}

	public String getFlagAutorizzazioneGeco() {
		return flagAutorizzazioneGeco;
	}

	public String getFlagRichiestaDeroga() {
		return flagRichiestaDeroga;
	}

	public String getFlagAutorizzazioneDeroga() {
		return flagAutorizzazioneDeroga;
	}

	public String getFlagCtrlPerformance() {
		return flagCtrlPerformance;
	}

//	public String getFlagOverrideDataLimite() {
//		return flagOverrideDataLimite;
//	}
//
//	public String getFlgUdcEmergenzaSpeciale() {
//		return flgUdcEmergenzaSpeciale;
//	}

	public String getFlagBloccoPromo() {
		return flagBloccoPromo;
	}

	public String getFlagUdcGiaPromosso() {
		return flagUdcGiaPromosso;
	}

	public String getFlagErroreBloccante() {
		return flagErroreBloccante;
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
