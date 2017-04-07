package autudc.chiamaBS.ude.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;

public class DatiManifestoDiCollaudo extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeMdc = "";
	private String utenteMdc = "";
	private String nomeUtente = "";

	public DatiManifestoDiCollaudo() {
	}

	public DatiManifestoDiCollaudo(
			String nomeMdc, 
			String utenteMdc,
			String nomeUtente) {
		super();
		this.nomeMdc = nomeMdc;
		this.utenteMdc = utenteMdc;
		this.nomeUtente = nomeUtente;
	}

	public String getNomeMdc() {
		return nomeMdc;
	}

	public String getUtenteMdc() {
		return utenteMdc;
	}

	public String getNomeUtente() {
		return nomeUtente;
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
