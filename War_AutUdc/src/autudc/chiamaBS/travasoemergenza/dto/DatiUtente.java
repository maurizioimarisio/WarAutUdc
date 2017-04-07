package autudc.chiamaBS.travasoemergenza.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;


public class DatiUtente extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String utente = "";
	private String nome = "";
	private String telefono = "";
	private String mail = "";

	public DatiUtente(){}
	
	public DatiUtente(
			String utente, 
			String nome, 
			String telefono, 
			String mail) {
		this.utente = utente.toUpperCase();
		this.nome = nome.toUpperCase();
		this.telefono = telefono.toUpperCase();
		this.mail = mail.toUpperCase();
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
	
	public String getMail() {
		return mail;
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
