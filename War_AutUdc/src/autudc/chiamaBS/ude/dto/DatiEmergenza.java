package autudc.chiamaBS.ude.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;


public class DatiEmergenza extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String flagSbloccoUde = "";
	private String orario = "";
	private String idEmergenza = "";
	private String profiloUtente = "";
	private String idAllegatoAutorizzazione = "";
	private String noteAutorizzazione = "";
	private String flagImpattoDiBusiness = "";
	private String flagErroreBloccante = "";
	private String codiceMotivoEmergenza = "";
	private String descrizioneMotivoEmergenza = "";
	
	public DatiEmergenza(){}
	
	public DatiEmergenza(
			String flagSbloccoUde, 
			String orario,
			String idEmergenza, 
			String profiloUtente,
			String idAllegatoAutorizzazione, 
			String noteAutorizzazione,
			String flagImpattoDiBusiness,
			String flagErroreBloccante, 
			String codiceMotivoEmergenza,
			String descrizioneMotivoEmergenza
	) {
		super();
		this.flagSbloccoUde = flagSbloccoUde;
		this.orario = orario;
		this.idEmergenza = idEmergenza;
		this.profiloUtente = profiloUtente;
		this.idAllegatoAutorizzazione = idAllegatoAutorizzazione;
		this.noteAutorizzazione = noteAutorizzazione;
		this.flagImpattoDiBusiness = flagImpattoDiBusiness;
		this.flagErroreBloccante = flagErroreBloccante;
		this.codiceMotivoEmergenza = codiceMotivoEmergenza;
		this.descrizioneMotivoEmergenza = descrizioneMotivoEmergenza;
	}

	public String getFlagSbloccoUde() {
		return flagSbloccoUde;
	}

	public String getOrario() {
		return orario;
	}

	public String getIdEmergenza() {
		return idEmergenza;
	}

	public String getProfiloUtente() {
		return profiloUtente;
	}

	public String getIdAllegatoAutorizzazione() {
		return idAllegatoAutorizzazione;
	}

	public String getNoteAutorizzazione() {
		return noteAutorizzazione;
	}

	public String getFlagImpattoDiBusiness() {
		return flagImpattoDiBusiness;
	}

	public String getFlagErroreBloccante() {
		return flagErroreBloccante;
	}

	public String getCodiceMotivoEmergenza() {
		return codiceMotivoEmergenza;
	}

	public String getDescrizioneMotivoEmergenza() {
		return descrizioneMotivoEmergenza;
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
