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
public class InputYUDAS00 extends CommonDto implements Serializable{

	private static final long serialVersionUID = 1L;

    private String user = "";
    private String sistemaInformativo = "";
    private String codUdc = "";
    private String codTipoPiattaforma = "";
    private String codProgetto = "";
    private String dataProduzione = "";
    private String idDeroga = "";

    public InputYUDAS00() {}

    public InputYUDAS00(
    		String user, 
    		String idDeroga) {
        this.user = user;
        this.idDeroga = idDeroga;
   }

    public InputYUDAS00(
    		String user, 
    		String idDeroga, 
    		String sistemaInformativo) {
    	this.user = user;
    	this.sistemaInformativo = sistemaInformativo;
    	this.idDeroga = idDeroga;
   }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
    	this.user = user;
    }

    public String getCodSistemaInformativo() {
        return sistemaInformativo;
    }
    public void setCodSistemaInformativo(String sistemaInformativo) {
    	this.sistemaInformativo = sistemaInformativo;
    }

    public String getCodUdc() {
        return codUdc;
    }
    public void setCodUdc(String codUdc) {
    	this.codUdc = codUdc;
    }

        public String getCodTipoPiattaforma() {
        return codTipoPiattaforma;
    }
    public void setCodTipoPiattaforma(String codTipoPiattaforma) {
    	this.codTipoPiattaforma = codTipoPiattaforma;
    }

    public String getCodProgetto() {
        return codProgetto;
    }
    public void setProgetto(String codProgetto) {
    	this.codProgetto = codProgetto;
    }

    public String getDataProduzione() {
        return dataProduzione;
    }
    public void setDataProduzione(String dataProduzione) {
    	this.dataProduzione = dataProduzione;
    }

    public String getIdDeroga() {
        return this.idDeroga;
    }
    public void setIdDeroga(String idDeroga) {
    	this.idDeroga = idDeroga;
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
