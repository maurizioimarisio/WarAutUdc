package autudc.chiamaBS.ude.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;

public class DatiImpatti extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codiceRischio = "";
	private String modFunzionali = "";
	private String descrVincoli = "";
	private String numOggHost = "";
	private String numOggOpen = "";
	private String dimOggOpen = "";
	private String descrImpConsumi = "";
	private String flagUtenzaFiliali = "N";
	private String flagUtenzaCanali = "N";
	private String flagUtenzaEntiC = "N";
	private String flagUtenzaAltro = "N";
	private String flagTipoSwTp = "N";
	private String flagTipoSwBatch = "N";
	private String flagTipoSwAltro = "N";
	private String flagImpConsumi = "N";
	private String flagApplNuova = "N";
	private String impattiGestiti = "";

	public DatiImpatti() {}

	public DatiImpatti(
			String codiceRischio, 
			String modFunzionali,
			String descrVincoli, 
			String numOggHost, 
			String numOggOpen,
			String dimOggOpen, 
			String flagUtenzaFiliali, 
			String flagUtenzaCanali,
			String flagUtenzaEntiC, 
			String flagUtenzaAltro,
			String flagTipoSwTp, 
			String flagTipoSwBatch,
			String flagTipoSwAltro, 
			String flagImpConsumi, 
			String descrImpConsumi,
			String flagApplNuova,
			String impattiGestiti
	) {
		super();
		this.codiceRischio = codiceRischio;
		this.modFunzionali = modFunzionali;
		this.descrVincoli = descrVincoli;
		this.numOggHost = numOggHost;
		this.numOggOpen = numOggOpen;
		this.dimOggOpen = dimOggOpen;
		this.flagUtenzaFiliali = flagUtenzaFiliali;
		this.flagUtenzaCanali = flagUtenzaCanali;
		this.flagUtenzaEntiC = flagUtenzaEntiC;
		this.flagUtenzaAltro = flagUtenzaAltro;
		this.flagTipoSwTp = flagTipoSwTp;
		this.flagTipoSwBatch = flagTipoSwBatch;
		this.flagTipoSwAltro = flagTipoSwAltro;
		this.flagImpConsumi = flagImpConsumi;
		this.descrImpConsumi = descrImpConsumi;
		this.flagApplNuova = flagApplNuova;
		this.impattiGestiti = impattiGestiti;
	}

	public String getCodiceRischio() {
		return codiceRischio;
	}

	public String getModFunzionali() {
		return modFunzionali;
	}

	public String getDescrVincoli() {
		return descrVincoli;
	}

	public String getNumOggHost() {
		return numOggHost;
	}

	public String getNumOggOpen() {
		return numOggOpen;
	}

	public String getDimOggOpen() {
		return dimOggOpen;
	}

	public String getDescrImpConsumi() {
		return descrImpConsumi;
	}

	public String getFlagUtenzaFiliali() {
		return flagUtenzaFiliali;
	}

	public String getFlagUtenzaCanali() {
		return flagUtenzaCanali;
	}

	public String getFlagUtenzaEntiC() {
		return flagUtenzaEntiC;
	}

	public String getFlagUtenzaAltro() {
		return flagUtenzaAltro;
	}

	public String getFlagTipoSwTp() {
		return flagTipoSwTp;
	}

	public String getFlagTipoSwBatch() {
		return flagTipoSwBatch;
	}

	public String getFlagTipoSwAltro() {
		return flagTipoSwAltro;
	}

	public String getFlagImpConsumi() {
		return flagImpConsumi;
	}

	public String getFlagApplNuova() {
		return flagApplNuova;
	}

	public String getImpattiGestiti() {
		return impattiGestiti;
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
