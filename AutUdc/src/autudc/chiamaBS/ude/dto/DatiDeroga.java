package autudc.chiamaBS.ude.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;

public class DatiDeroga extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codice = "";
	private String descrizione = "";
	private String motivo = "";
	private String vincoliDeroga = "";
	private String descrizioneVincoli = "";
	private DatiUtente datiUtenteDeroga = new DatiUtente();

	public DatiDeroga() {}

	public DatiDeroga(
			String codice, 
			String descrizione, 
			String motivo,
			String vincoliDeroga,
			String descrizioneVincoli,
			DatiUtente datiUtenteDeroga) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.motivo = motivo;
		this.vincoliDeroga = vincoliDeroga;
		this.descrizioneVincoli = descrizioneVincoli;
		this.datiUtenteDeroga = datiUtenteDeroga;
	}

	public String getCodice() {
		return codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getMotivo() {
		return motivo;
	}

	public String getVincoliDeroga() {
		return vincoliDeroga;
	}

	public String getDescrizioneVincoli() {
		return descrizioneVincoli;
	}

	public DatiUtente getDatiUtenteDeroga() {
		return datiUtenteDeroga;
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
