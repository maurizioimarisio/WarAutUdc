package autudc.chiamaBS.travasoemergenza.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;

public class ControlliTestata extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codiceSistemaInformativo = "";
	private String descrSistemaInformativo = "";
	
	private DatiRichiesta datiRichiesta = new DatiRichiesta();
	private DatiIncidente datiIncidente = new DatiIncidente();
	private DatiUtente datiRichiedente = new DatiUtente();
	private DatiUtente datiPubblicatore = new DatiUtente();
	private Abilitazioni abilitazioni = new Abilitazioni();
	private DatiDuplicazione datiDuplicazione = new DatiDuplicazione();
	private DatiAutorizzazione datiAutorizzazione = new DatiAutorizzazione();
	
	public ControlliTestata() {}
		
	public ControlliTestata(
			String codiceSistemaInformativo,
			String descrSistemaInformativo, 
			DatiRichiesta datiRichiesta,
			DatiIncidente datiIncidente, 
			DatiUtente datiRichiedente,
			DatiUtente datiPubblicatore, 
			Abilitazioni abilitazioni,
			DatiAutorizzazione datiAutorizzazione) {
		this.codiceSistemaInformativo = codiceSistemaInformativo.toUpperCase();
		this.descrSistemaInformativo = descrSistemaInformativo.toUpperCase();
		this.datiRichiesta = datiRichiesta;
		this.datiIncidente = datiIncidente;
		this.datiRichiedente = datiRichiedente;
		this.datiPubblicatore = datiPubblicatore;
		this.abilitazioni = abilitazioni;
		this.datiAutorizzazione = datiAutorizzazione;
	}

	public String getCodiceSistemaInformativo() {
		return codiceSistemaInformativo;
	}

	public void setCodiceSistemaInformativo(String codiceSistemaInformativo) {
		this.codiceSistemaInformativo = codiceSistemaInformativo;
	}

	public String getDescrSistemaInformativo() {
		return descrSistemaInformativo;
	}

	public void setDescrSistemaInformativo(String descrSistemaInformativo) {
		this.descrSistemaInformativo = descrSistemaInformativo;
	}

	public DatiRichiesta getDatiRichiesta() {
		return datiRichiesta;
	}

	public void setDatiRichiesta(DatiRichiesta datiRichiesta) {
		this.datiRichiesta = datiRichiesta;
	}

	public DatiIncidente getDatiIncidente() {
		return datiIncidente;
	}

	public void setDatiIncidente(DatiIncidente datiIncidente) {
		this.datiIncidente = datiIncidente;
	}

	public DatiUtente getDatiRichiedente() {
		return datiRichiedente;
	}

	public void setDatiRichiedente(DatiUtente datiRichiedente) {
		this.datiRichiedente = datiRichiedente;
	}

	public DatiUtente getDatiPubblicatore() {
		return datiPubblicatore;
	}

	public void setDatiPubblicatore(DatiUtente datiPubblicatore) {
		this.datiPubblicatore = datiPubblicatore;
	}

	public Abilitazioni getAbilitazioni() {
		return abilitazioni;
	}

	public void setAbilitazioni(Abilitazioni abilitazioni) {
		this.abilitazioni = abilitazioni;
	}

	public DatiDuplicazione getDatiDuplicazione() {
		return datiDuplicazione;
	}

	public void setDatiDuplicazione(DatiDuplicazione datiDuplicazione) {
		this.datiDuplicazione = datiDuplicazione;
	}

	public DatiAutorizzazione getDatiAutorizzazione() {
		return datiAutorizzazione;
	}

	public void setDatiAutorizzazione(DatiAutorizzazione datiAutorizzazione) {
		this.datiAutorizzazione = datiAutorizzazione;
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