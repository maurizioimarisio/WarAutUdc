package db.entities;

import java.io.Serializable;

public class Allegati implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private String  idAllegati = "";
	private String  codPiattaforma = "";
	private String  codUdc = "";
	private String  fileName = "";
	private String  descrizione = "";
	private String  contentType = "";
	private String  tipoAllegato = "";
	private String  allegato = "";
	private String  dataInerimento = "";
	private String  stato = "";
	private String  numRichiesta = "";
	private String  numDettaglio = "";
	
	public Allegati(){}
	
	public Allegati(
			String codPiattaforma, 
			String codUdc, 
			String tipoAllegato) {
		super();
		this.codPiattaforma = codPiattaforma;
		this.codUdc = codUdc;
		this.tipoAllegato = tipoAllegato;
	}

	public Allegati(
		String idAllegati, 
		String codPiattaforma, 
		String codUdc,
		String fileName, 
		String descrizione, 
		String contentType,
		String tipoAllegato, 
		String allegato, 
		String dataInerimento,
		String stato, 
		String numRichiesta, 
		String numDettaglio
	) {
		super();
		this.idAllegati = idAllegati;
		this.codPiattaforma = codPiattaforma;
		this.codUdc = codUdc;
		this.fileName = fileName;
		this.descrizione = descrizione;
		this.contentType = contentType;
		this.tipoAllegato = tipoAllegato;
		this.allegato = allegato;
		this.dataInerimento = dataInerimento;
		this.stato = stato;
		this.numRichiesta = numRichiesta;
		this.numDettaglio = numDettaglio;
	}

	public String getIdAllegati() {
		return idAllegati;
	}

	public String getCodPiattaforma() {
		return codPiattaforma;
	}

	public String getCodUdc() {
		return codUdc;
	}

	public String getFileName() {
		return fileName;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getContentType() {
		return contentType;
	}

	public String getTipoAllegato() {
		return tipoAllegato;
	}

	public String getAllegato() {
		return allegato;
	}

	public String getDataInerimento() {
		return dataInerimento;
	}

	public String getStato() {
		return stato;
	}

	public String getNumRichiesta() {
		return numRichiesta;
	}

	public String getNumDettaglio() {
		return numDettaglio;
	}

}
