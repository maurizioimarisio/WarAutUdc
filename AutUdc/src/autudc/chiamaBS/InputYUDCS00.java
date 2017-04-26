/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autudc.chiamaBS;

import java.io.Serializable;

/**
 *
 * @author GF001126
 */
public class InputYUDCS00 extends CommonDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String user = null;
    private String codiceSistemaInformativo = null;  //Sistema Informativo
    private String tipoAutorizzazione = null; // "DER", funzione BS
    private String idDaAutorizzare = null; // ID Deroga
    private String esitoAutorizzaz = null;

    public InputYUDCS00() {}

    public InputYUDCS00(String user, String codiceSistemaInformativo, String tipoAutorizzazione, String idDaAutorizzare, String esitoAutorizzaz) {

    	this.user = user;
    	this.codiceSistemaInformativo = codiceSistemaInformativo;
    	this.tipoAutorizzazione = tipoAutorizzazione;
    	this.idDaAutorizzare = idDaAutorizzare;
    	this.esitoAutorizzaz = esitoAutorizzaz;
    }

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCodiceSistemaInformativo() {
		return codiceSistemaInformativo;
	}

	public void setCodiceSistemaInformativo(String codiceSistemaInformativo) {
		this.codiceSistemaInformativo = codiceSistemaInformativo;
	}

	public String getTipoAutorizzazione() {
		return tipoAutorizzazione;
	}

	public void setTipoAutorizzazione(String tipoAutorizzazione) {
		this.tipoAutorizzazione = tipoAutorizzazione;
	}

	public String getIdDaAutorizzare() {
		return idDaAutorizzare;
	}

	public void setIdDaAutorizzare(String idDaAutorizzare) {
		this.idDaAutorizzare = idDaAutorizzare;
	}

	public String getEsitoAutorizzaz() {
		return esitoAutorizzaz;
	}

	public void setEsitoAutorizzaz(String esitoAutorizzaz) {
		this.esitoAutorizzaz = esitoAutorizzaz;
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
