package db.entities.struct;

import java.io.Serializable;

public class ElencoAcronimi implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codiceAcronimo = "";
	private String sistemaInformativo = "";
	private String descrizioneAcronimo = "";
	private String codiceFascia = "";
	private String ssa = "";
	private String pila = "";
	private String distribuzioneDifferita = "";
	private String dataDifferita = "";
	private String codiceArchitettura = "";
	private String descrizioneArchitettura = "";
	private String codiceZona = "";
	private String codiceSO = "";
	private String codiceCanaleMW = "";
	private String nomeClusterProd = "";
	private String tipoDb = "";
	private String nomeDb = "";
	private String istanza = "";
	private String dataPila1 = "";
	private String idCmdAttivaPrf2px = "";
	private String idCmdAttivaDiffP1 = "";
	private String codiceCalendario = "";
	private String criterio = "";
	private String criterioDefault = "";
	private String criterioDescrizione = "";
	private String flagEmergSpeciale = ""; // mg 23-02-2012 flag aggiunto per il trattamento UDC Finannce
	
	public ElencoAcronimi() {}

	public String getCodiceAcronimo() {
		return codiceAcronimo;
	}

	public void setCodiceAcronimo(String codiceAcronimo) {
		this.codiceAcronimo = codiceAcronimo;
	}

	public String getSistemaInformativo() {
		return sistemaInformativo;
	}

	public void setSistemaInformativo(String sistemaInformativo) {
		this.sistemaInformativo = sistemaInformativo;
	}

	public String getDescrizioneAcronimo() {
		return descrizioneAcronimo;
	}

	public void setDescrizioneAcronimo(String descrizioneAcronimo) {
		this.descrizioneAcronimo = descrizioneAcronimo;
	}

	public String getCodiceFascia() {
		return codiceFascia;
	}

	public void setCodiceFascia(String codiceFascia) {
		this.codiceFascia = codiceFascia;
	}

	public String getSsa() {
		return ssa;
	}

	public void setSsa(String ssa) {
		this.ssa = ssa;
	}

	public String getPila() {
		return pila;
	}

	public void setPila(String pila) {
		this.pila = pila;
	}

	public String getDistribuzioneDifferita() {
		return distribuzioneDifferita;
	}

	public void setDistribuzioneDifferita(String distribuzioneDifferita) {
		this.distribuzioneDifferita = distribuzioneDifferita;
	}

	public String getDataDifferita() {
		return dataDifferita;
	}

	public void setDataDifferita(String dataDifferita) {
		this.dataDifferita = dataDifferita;
	}

	public String getCodiceArchitettura() {
		return codiceArchitettura;
	}

	public void setCodiceArchitettura(String codiceArchitettura) {
		this.codiceArchitettura = codiceArchitettura;
	}

	public String getDescrizioneArchitettura() {
		return descrizioneArchitettura;
	}

	public void setDescrizioneArchitettura(String descrizioneArchitettura) {
		this.descrizioneArchitettura = descrizioneArchitettura;
	}

	public String getCodiceZona() {
		return codiceZona;
	}

	public void setCodiceZona(String codiceZona) {
		this.codiceZona = codiceZona;
	}

	public String getCodiceSO() {
		return codiceSO;
	}

	public void setCodiceSO(String codiceSO) {
		this.codiceSO = codiceSO;
	}

	public String getCodiceCanaleMW() {
		return codiceCanaleMW;
	}

	public void setCodiceCanaleMW(String codiceCanaleMW) {
		this.codiceCanaleMW = codiceCanaleMW;
	}

	public String getNomeClusterProd() {
		return nomeClusterProd;
	}

	public void setNomeClusterProd(String nomeClusterProd) {
		this.nomeClusterProd = nomeClusterProd;
	}

	public String getTipoDb() {
		return tipoDb;
	}

	public void setTipoDb(String tipoDb) {
		this.tipoDb = tipoDb;
	}

	public String getNomeDb() {
		return nomeDb;
	}

	public void setNomeDb(String nomeDb) {
		this.nomeDb = nomeDb;
	}

	public String getIstanza() {
		return istanza;
	}

	public void setIstanza(String istanza) {
		this.istanza = istanza;
	}

	public String getDataPila1() {
		return dataPila1;
	}

	public void setDataPila1(String dataPila1) {
		this.dataPila1 = dataPila1;
	}

	public String getIdCmdAttivaPrf2px() {
		return idCmdAttivaPrf2px;
	}

	public void setIdCmdAttivaPrf2px(String idCmdAttivaPrf2px) {
		this.idCmdAttivaPrf2px = idCmdAttivaPrf2px;
	}

	public String getIdCmdAttivaDiffP1() {
		return idCmdAttivaDiffP1;
	}

	public void setIdCmdAttivaDiffP1(String idCmdAttivaDiffP1) {
		this.idCmdAttivaDiffP1 = idCmdAttivaDiffP1;
	}

	public String getCodiceCalendario() {
		return codiceCalendario;
	}

	public void setCodiceCalendario(String codiceCalendario) {
		this.codiceCalendario = codiceCalendario;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public String getCriterioDefault() {
		return criterioDefault;
	}

	public void setCriterioDefault(String criterioDefault) {
		this.criterioDefault = criterioDefault;
	}

	public String getCriterioDescrizione() {
		return criterioDescrizione;
	}

	public void setCriterioDescrizione(String criterioDescrizione) {
		this.criterioDescrizione = criterioDescrizione;
	}

	public String getFlagEmergSpeciale() {
		return flagEmergSpeciale;
	}

	public void setFlagEmergSpeciale(String flagEmergSpeciale) {
		this.flagEmergSpeciale = flagEmergSpeciale;
	}
	
}
