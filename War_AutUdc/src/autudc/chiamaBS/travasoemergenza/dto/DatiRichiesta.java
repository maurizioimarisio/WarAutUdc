package autudc.chiamaBS.travasoemergenza.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;


public class DatiRichiesta extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codiceSsa = "";
	private String tipoRichiesta = "";
	private String numeroRichiesta = "";
	private String codiceStatoRichiesta = "";
	private String descrStatoRichiestaBreve = "";
	private String descrStatoRichiestaEsteso = "";
	private String dataRichiesta = "";
	private String descrRichiesta = "";
	private String timeStampUltimoAggiornamento = "";
	private MotivoEmergenza motivoEmergenza = new MotivoEmergenza();
	
	public DatiRichiesta(){}

	public DatiRichiesta(
			String codiceSsa, 
			String tipoRichiesta,
			String numeroRichiesta, 
			String codiceStatoRichiesta,
			String descrStatoRichiestaBreve, 
			String descrStatoRichiestaEsteso, 
			String dataRichiesta,
			String descrRichiesta,
			String timeStampUltimoAggiornamento,
			MotivoEmergenza motivoEmergenza) {

		this.codiceSsa = codiceSsa.toUpperCase();
		this.tipoRichiesta = tipoRichiesta.toUpperCase();
		this.numeroRichiesta = numeroRichiesta.toUpperCase();
		this.codiceStatoRichiesta = codiceStatoRichiesta.toUpperCase();
		this.descrStatoRichiestaBreve = descrStatoRichiestaBreve.toUpperCase();
		this.descrStatoRichiestaEsteso = descrStatoRichiestaEsteso.toUpperCase();
		this.dataRichiesta = dataRichiesta.toUpperCase();
		this.descrRichiesta = descrRichiesta.toUpperCase();
		this.timeStampUltimoAggiornamento = timeStampUltimoAggiornamento.toUpperCase();
		this.motivoEmergenza = motivoEmergenza;
	}	

	public String getCodiceSsa() {
		return codiceSsa;
	}
	
	public void setCodiceSsa(String codiceSsa) {
		this.codiceSsa = codiceSsa.toUpperCase();
	}
	
	public String getTipoRichiesta() {
		return tipoRichiesta;
	}
	
	public void setTipoRichiesta(String tipoRichiesta) {
		this.tipoRichiesta = tipoRichiesta.toUpperCase();
	}
	
	public String getNumeroRichiesta() {
		return numeroRichiesta;
	}
	
	public void setNumeroRichiesta(String numeroRichiesta) {
		this.numeroRichiesta = numeroRichiesta.toUpperCase();
	}
	
	public String getCodiceStatoRichiesta() {
		return codiceStatoRichiesta;
	}
	
	public void setCodiceStatoRichiesta(String codiceStatoRichiesta) {
		this.codiceStatoRichiesta = codiceStatoRichiesta.toUpperCase();
	}
	
	public String getDescrStatoRichiestaBreve() {
		return descrStatoRichiestaBreve;
	}
	
	public void setDescrStatoRichiestaBreve(String descrStatoRichiestaBreve) {
		this.descrStatoRichiestaBreve = descrStatoRichiestaBreve.toUpperCase();
	}
	
	public String getDescrStatoRichiestaEsteso() {
		return descrStatoRichiestaEsteso;
	}
	
	public void setDescrStatoRichiestaEsteso(String descrStatoRichiestaEsteso) {
		this.descrStatoRichiestaEsteso = descrStatoRichiestaEsteso.toUpperCase();
	}
	
	public String getDataRichiesta() {
		return dataRichiesta;
	}
	
	public void setDataRichiesta(String dataRichiesta) {
		this.dataRichiesta = dataRichiesta.toUpperCase();
	}
	
	public String getDescrRichiesta() {
		return descrRichiesta;
	}
	
	public void setDescrRichiesta(String descrRichiesta) {
		this.descrRichiesta = descrRichiesta.toUpperCase();
	}
	
	public String getTimeStampUltimoAggiornamento() {
		return timeStampUltimoAggiornamento;
	}
	
	public MotivoEmergenza getMotivoEmergenza() {
		return motivoEmergenza;
	}

	public void setCodiceMotivoEmergenza(MotivoEmergenza motivoEmergenza) {
		this.motivoEmergenza = motivoEmergenza;
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
