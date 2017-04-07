package autudc.chiamaBS.travasoemergenza.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;

public class Abilitazioni extends CommonDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String abilitaConferma = "N";
	private String abilitaPubblica = "N";
	private String abilitaReset = "N";
	private String abilitaVaria = "N";
	private String abilitaNuovo = "N";
	private String abilitaDuplica = "N";
	private String abilitaCancella = "N";
	private String indicatorePrevNext = "";
	private String abilitaVariaDettaglio = "N";
	private String abilitaRiepilogoTravasoGae = "N";
	private String abilitaAutorizzAttuatore = "N";
	private String abilitaAutorizzResponsabile = "N";
	
	public Abilitazioni(){}
	
	public Abilitazioni(
			String abilitaConferma, 
			String abilitaPubblica,
			String abilitaReset, 
			String abilitaVaria,
			String abilitaNuovo, 
			String abilitaDuplica,
			String abilitaCancella,
			String abilitaRiepilogoTravasoGae,
			String abilitaAutorizzAttuatore,
			String abilitaAutorizzResponsabile) {
		this.abilitaConferma = abilitaConferma;
		this.abilitaPubblica = abilitaPubblica;
		this.abilitaReset = abilitaReset;
		this.abilitaVaria = abilitaVaria;
		this.abilitaNuovo = abilitaNuovo;
		this.abilitaDuplica = abilitaDuplica;
		this.abilitaCancella = abilitaCancella;
		this.abilitaRiepilogoTravasoGae = abilitaRiepilogoTravasoGae;
		this.abilitaAutorizzAttuatore = abilitaAutorizzAttuatore;
		this.abilitaAutorizzResponsabile = abilitaAutorizzResponsabile;
	}

	public String getAbilitaConferma() {
		return abilitaConferma;
	}

	public void setAbilitaConferma(String abilitaConferma) {
		this.abilitaConferma = abilitaConferma;
	}

	public String getAbilitaPubblica() {
		return abilitaPubblica;
	}

	public void setAbilitaPubblica(String abilitaPubblica) {
		this.abilitaPubblica = abilitaPubblica;
	}

	public String getAbilitaReset() {
		return abilitaReset;
	}

	public void setAbilitaReset(String abilitaReset) {
		this.abilitaReset = abilitaReset;
	}

	public String getAbilitaVaria() {
		return abilitaVaria;
	}

	public void setAbilitaVaria(String abilitaVaria) {
		this.abilitaVaria = abilitaVaria;
	}

	public String getAbilitaNuovo() {
		return abilitaNuovo;
	}

	public void setAbilitaNuovo(String abilitaNuovo) {
		this.abilitaNuovo = abilitaNuovo;
	}

	public String getAbilitaDuplica() {
		return abilitaDuplica;
	}

	public void setAbilitaDuplica(String abilitaDuplica) {
		this.abilitaDuplica = abilitaDuplica;
	}

	public String getAbilitaCancella() {
		return abilitaCancella;
	}

	public void setAbilitaCancella(String abilitaCancella) {
		this.abilitaCancella = abilitaCancella;
	}

	public String getIndicatorePrevNext() {
		return indicatorePrevNext;
	}

	public void setIndicatorePrevNext(String indicatorePrevNext) {
		this.indicatorePrevNext = indicatorePrevNext;
	}

	public String getAbilitaVariaDettaglio() {
		return abilitaVariaDettaglio;
	}

	public void setAbilitaVariaDettaglio(String abilitaVariaDettaglio) {
		this.abilitaVariaDettaglio = abilitaVariaDettaglio;
	}

	public String getAbilitaRiepilogoTravasoGae() {
		return abilitaRiepilogoTravasoGae;
	}

	public void setAbilitaRiepilogoTravasoGae(String abilitaRiepilogoTravasoGae) {
		this.abilitaRiepilogoTravasoGae = abilitaRiepilogoTravasoGae;
	}

	public String getAbilitaAutorizzAttuatore() {
		return abilitaAutorizzAttuatore;
	}

	public void setAbilitaAutorizzAttuatore(String abilitaAutorizzAttuatore) {
		this.abilitaAutorizzAttuatore = abilitaAutorizzAttuatore;
	}

	public String getAbilitaAutorizzResponsabile() {
		return abilitaAutorizzResponsabile;
	}

	public void setAbilitaAutorizzResponsabile(String abilitaAutorizzResponsabile) {
		this.abilitaAutorizzResponsabile = abilitaAutorizzResponsabile;
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
