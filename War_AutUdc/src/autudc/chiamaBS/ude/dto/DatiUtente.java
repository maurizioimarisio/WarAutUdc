package autudc.chiamaBS.ude.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;


public class DatiUtente extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String utente = "";
	private String nome = "";
	private String prefisso = "";
	private String telefono = "";
	private String telefonoCompleto = "";
	
	public DatiUtente(){}
	
	public DatiUtente(
			String utente, 
			String nome, 
			String prefisso,
			String telefono) {
		this.utente = utente.toUpperCase();
		this.nome = nome.toUpperCase();
		this.prefisso = prefisso.toUpperCase();
		this.telefono = telefono.toUpperCase();
		this.telefonoCompleto = prefisso+telefono;
	}

	public String getUtente() {
		return utente;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public String getPrefisso() {
		return prefisso;
	}
	
	public String getTelefonoCompleto() {
		return telefonoCompleto;
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
