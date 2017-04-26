package db.entities.struct;

import java.io.Serializable;

public class DatiAcronimoApplicazione implements Serializable{

	private static final long serialVersionUID = 1L;
	private String acronimoApplicazione = "";
	private String descrizioneApplicazione = "";
	private String codiceSSA = "";
	private String nomeVob = "";
	private String codiceZona = "";
	private String codiceArchitettura = "";
	private String codiceSistemaInformativo = "";
	private String codiceFasciaDistribuzione = "";
	private String tipoDistribuzione = "";
	private String flagDistrAPile = "";
	private String codiceCanaleMW = "";
	private String acronimoPrincipale  = "";
	private String sistemaInformativo  = "";
	

	public DatiAcronimoApplicazione(String acronimoApplicazione, String descrizioneApplicazione,
			String codiceSSA, String nomeVob, String codiceZona,
			String codiceArchitettura, String codiceSistemaInformativo,
			String codiceFasciaDistribuzione, String tipoDistribuzione, String flagDistrAPile,
			String codiceCanaleMW, String acronimoPrincipale,
			String sistemaInformativo) {
		super();
		this.acronimoApplicazione = acronimoApplicazione;
		this.descrizioneApplicazione = descrizioneApplicazione;
		this.codiceSSA = codiceSSA;
		this.nomeVob = nomeVob;
		this.codiceZona = codiceZona;
		this.codiceArchitettura = codiceArchitettura;
		this.codiceSistemaInformativo = codiceSistemaInformativo;
		this.codiceFasciaDistribuzione = codiceFasciaDistribuzione;
		this.tipoDistribuzione = tipoDistribuzione;
		this.flagDistrAPile = flagDistrAPile;
		this.codiceCanaleMW = codiceCanaleMW;
		this.acronimoPrincipale = acronimoPrincipale;
		this.sistemaInformativo = sistemaInformativo;
	}

	public String getAcronimoApplicazione() {
		return acronimoApplicazione;
	}

	public void setAcronimoApplicazione(String acronimoApplicazione) {
		this.acronimoApplicazione = acronimoApplicazione;
	}

	public String getDescrizioneApplicazione() {
		return descrizioneApplicazione;
	}

	public void setDescrizioneApplicazione(String descrizioneApplicazione) {
		this.descrizioneApplicazione = descrizioneApplicazione;
	}

	public String getCodiceSSA() {
		return codiceSSA;
	}

	public void setCodiceSSA(String codiceSSA) {
		this.codiceSSA = codiceSSA;
	}

	public String getNomeVob() {
		return nomeVob;
	}

	public void setNomeVob(String nomeVob) {
		this.nomeVob = nomeVob;
	}

	public String getCodiceZona() {
		return codiceZona;
	}

	public void setCodiceZona(String codiceZona) {
		this.codiceZona = codiceZona;
	}

	public String getCodiceArchitettura() {
		return codiceArchitettura;
	}

	public void setCodiceArchitettura(String codiceArchitettura) {
		this.codiceArchitettura = codiceArchitettura;
	}

	public String getCodiceSistemaInformativo() {
		return codiceSistemaInformativo;
	}

	public void setCodiceSistemaInformativo(String codiceSistemaInformativo) {
		this.codiceSistemaInformativo = codiceSistemaInformativo;
	}

	public String getCodiceFasciaDistribuzione() {
		return codiceFasciaDistribuzione;
	}

	public void setCodiceFasciaDistribuzione(String codiceFasciaDistribuzione) {
		this.codiceFasciaDistribuzione = codiceFasciaDistribuzione;
	}

	public String getTipoDistribuzione() {
		return tipoDistribuzione;
	}

	public void setTipoDistribuzione(String tipoDistribuzione) {
		this.tipoDistribuzione = tipoDistribuzione;
	}

	public String getFlagDistrAPile() {
		return flagDistrAPile;
	}

	public void setFlagDistrAPile(String flagDistrAPile) {
		this.flagDistrAPile = flagDistrAPile;
	}

	public String getCodiceCanaleMW() {
		return codiceCanaleMW;
	}

	public void setCodiceCanaleMW(String codiceCanaleMW) {
		this.codiceCanaleMW = codiceCanaleMW;
	}

	public String getAcronimoPrincipale() {
		return acronimoPrincipale;
	}

	public void setAcronimoPrincipale(String acronimoPrincipale) {
		this.acronimoPrincipale = acronimoPrincipale;
	}

	public String getSistemaInformativo() {
		return sistemaInformativo;
	}

	public void setSistemaInformativo(String sistemaInformativo) {
		this.sistemaInformativo = sistemaInformativo;
	}

}
