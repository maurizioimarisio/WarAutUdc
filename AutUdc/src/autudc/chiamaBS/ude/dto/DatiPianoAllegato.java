package autudc.chiamaBS.ude.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;


public class DatiPianoAllegato extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean isPresente = false;
	private String nome = "";
	private String descrizione = "";
	private String data = "";
	private String id = "";
	
	public DatiPianoAllegato(){}
	
	public DatiPianoAllegato(
			boolean isPresente, 
			String nome,
			String descrizione, 
			String data, 
			String id) {
		super();
		this.isPresente = isPresente;
		this.nome = nome;
		this.descrizione = descrizione;
		this.data = data;
		this.id = id;
	}

	public boolean isPresente() {
		return isPresente;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getData() {
		return data;
	}

	public String getId() {
		return id;
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
