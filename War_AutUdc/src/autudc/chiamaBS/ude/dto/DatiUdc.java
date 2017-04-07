package autudc.chiamaBS.ude.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;

//TODO [VS] 
public class DatiUdc extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sistemaInformativo = "";
	private String codiceUdc = "";
	private String codiceProgetto = "";
	private String codiceAttivita = "";
	private String codiceIncidente = "";
	private String codiceTipoUdc = "";
	private String descrizioneTipoUdc = "";
	private String codiceStatoUdc = "";
	private String descrizioneStatoUdc = "";
	private String codiceStatoTecnicoUdc = "";
	private String titolo = "";

	private String codiceTipoPiattaforma = "";
	private String codiceZona = "";
	private String codiceFamiglia = "";
	private String descrFamiglia = "";
	private String nomeRelease = "";
	private String obiettiviRelease = "";

	private String timestUpdate = "";
	private String timestStorico = "";
	private String nomeFile = "";

	private String modificaColl = "";
	private String modificaProd = "";
	private String modificaIt = "";
	private String modificaAltri = "";
	private String modificaAddestr = "";
	
	private String modificabile = "";
	
	private String procedureRipartenza = "";
	private String recuperoDati = "";
	
	private String codCalendario = "";
	private String criterio = "";
	private String  minTimeSTCutOff ="";

	public String getMinTimeSTCutOff() {
		return minTimeSTCutOff;
	}



	public void setMinTimeSTCutOff(String minTimeSTCutOff) {
		this.minTimeSTCutOff = minTimeSTCutOff;
	}



	public DatiUdc() {}
	


	public DatiUdc(
			String sistemaInformativo, 
			String codiceUdc,
			String codiceProgetto, 
			String codiceAttivita,
			String codiceIncidente, 
			String codiceTipoUdc,
			String descrizioneTipoUdc, 
			String codiceStatoUdc,
			String descrizioneStatoUdc, 
			String codiceStatoTecnicoUdc,
			String titolo, 
			String codiceTipoPiattaforma, 
			String codiceZona,
			String codiceFamiglia, 
			String descrFamiglia, 
			String nomeRelease,
			String obiettiviRelease, 
			String timestUpdate, 
			String timestStorico,
			String nomeFile, 
			String modificaColl, 
			String modificaProd,
			String modificaIt, 
			String modificaAltri, 
			String modificaAddestr,
			String modificabile, 
			String procedureRipartenza,
			String recuperoDati, 
			String codCalendario, 
			String criterio) {
		super();
		this.sistemaInformativo = sistemaInformativo;
		this.codiceUdc = codiceUdc;
		this.codiceProgetto = codiceProgetto;
		this.codiceAttivita = codiceAttivita;
		this.codiceIncidente = codiceIncidente;
		this.codiceTipoUdc = codiceTipoUdc;
		this.descrizioneTipoUdc = descrizioneTipoUdc;
		this.codiceStatoUdc = codiceStatoUdc;
		this.descrizioneStatoUdc = descrizioneStatoUdc;
		this.codiceStatoTecnicoUdc = codiceStatoTecnicoUdc;
		this.titolo = titolo;
		this.codiceTipoPiattaforma = codiceTipoPiattaforma;
		this.codiceZona = codiceZona;
		this.codiceFamiglia = codiceFamiglia;
		this.descrFamiglia = descrFamiglia;
		this.nomeRelease = nomeRelease;
		this.obiettiviRelease = obiettiviRelease;
		this.timestUpdate = timestUpdate;
		this.timestStorico = timestStorico;
		this.nomeFile = nomeFile;
		this.modificaColl = modificaColl;
		this.modificaProd = modificaProd;
		this.modificaIt = modificaIt;
		this.modificaAltri = modificaAltri;
		this.modificaAddestr = modificaAddestr;
		this.modificabile = modificabile;
		this.procedureRipartenza = procedureRipartenza;
		this.recuperoDati = recuperoDati;
		this.codCalendario = codCalendario;
		this.criterio = criterio;
	}

	public String getSistemaInformativo() {
		return sistemaInformativo;
	}

	public String getCodiceUdc() {
		return codiceUdc;
	}

	public String getCodiceProgetto() {
		return codiceProgetto;
	}

	public String getCodiceAttivita() {
		return codiceAttivita;
	}

	public String getCodiceIncidente() {
		return codiceIncidente;
	}

	public String getCodiceTipoUdc() {
		return codiceTipoUdc;
	}

	public String getDescrizioneTipoUdc() {
		return descrizioneTipoUdc;
	}

	public String getCodiceStatoUdc() {
		return codiceStatoUdc;
	}

	public String getDescrizioneStatoUdc() {
		return descrizioneStatoUdc;
	}

	public String getCodiceStatoTecnicoUdc() {
		return codiceStatoTecnicoUdc;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getCodiceTipoPiattaforma() {
		return codiceTipoPiattaforma;
	}

	public String getCodiceZona() {
		return codiceZona;
	}

	public String getCodiceFamiglia() {
		return codiceFamiglia;
	}

	public String getDescrFamiglia() {
		return descrFamiglia;
	}

	public String getNomeRelease() {
		return nomeRelease;
	}

	public String getObiettiviRelease() {
		return obiettiviRelease;
	}

	public String getTimestUpdate() {
		return timestUpdate;
	}

	public String getTimestStorico() {
		return timestStorico;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public String getModificaColl() {
		return modificaColl;
	}

	public String getModificaProd() {
		return modificaProd;
	}

	public String getModificaIt() {
		return modificaIt;
	}

	public String getModificaAltri() {
		return modificaAltri;
	}

	public String getModificaAddestr() {
		return modificaAddestr;
	}

	public String getModificabile() {
		return modificabile;
	}

	public String getProcedureRipartenza() {
		return procedureRipartenza;
	}

	public String getRecuperoDati() {
		return recuperoDati;
	}

	public String getCodCalendario() {
		return codCalendario;
	}

	public String getCriterio() {
		return criterio;
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
