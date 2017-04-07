package autudc.chiamaBS.travasoemergenza.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;


public class DatiAutorizzazione extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idAllegato = "";
	private String nomeAllegato = "";
	private String noteAutorizzazioneAttuatore = "";
	private String impattoDiBusiness = "";
	private String erroreBloccante = "";
	private String idIAutorizzEmergenza = "";
	private String tipoAutorizzazione = "";
	
	public DatiAutorizzazione() {}
	
	public DatiAutorizzazione(
			String idAllegato, 
			String nomeAllegato,
			String noteAutorizzazioneAttuatore,
			String impattoDiBusiness, 
			String erroreBloccante,
			String idIAutorizzEmergenza,
			String tipoAutorizzazione) {
		this.idAllegato = idAllegato;
		this.nomeAllegato = nomeAllegato;
		this.noteAutorizzazioneAttuatore = noteAutorizzazioneAttuatore;
		this.impattoDiBusiness = impattoDiBusiness;
		this.erroreBloccante = erroreBloccante;
		this.idIAutorizzEmergenza = idIAutorizzEmergenza;
		this.tipoAutorizzazione = tipoAutorizzazione;
	}
	
	public boolean isPresent(){
		return (this.idAllegato!=null && this.idAllegato.trim().length()>0) 
			|| (this.noteAutorizzazioneAttuatore!=null && this.noteAutorizzazioneAttuatore.trim().length()>0) ;
	}

	public String getIdAllegato() {
		return idAllegato;
	}

	public String getNomeAllegato() {
		return nomeAllegato;
	}

	public String getNoteAutorizzazioneAttuatore() {
		return noteAutorizzazioneAttuatore;
	}

	public String getImpattoDiBusiness() {
		return impattoDiBusiness;
	}

	public String getErroreBloccante() {
		return erroreBloccante;
	}

	public String getIdIAutorizzEmergenza() {
		return idIAutorizzEmergenza;
	}

	public String getTipoAutorizzazione() {
		return tipoAutorizzazione;
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
