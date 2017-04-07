package db.entities;

import java.io.Serializable;

public class Calendario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String data = ""; 
    private String tipo_calendario = ""; 
    private String descrizione_titolo_calendario = ""; 
    private String piattaforma = ""; 
    private String sistema_informativo = ""; 
    private String abilit_collaudo = ""; 
    private String abilit_produzione_deroga = ""; 
    private String abilit_produzione_ordinaria = "";
    private String abilit_blocco_change = "";
    private String cod_livello_autorizzativo = "";

	public Calendario() {
		super();
	}

	public Calendario(
			String data, 
			String tipo_calendario, 
			String descrizione_titolo_calendario, 
			String piattaforma, 
			String sistema_informativo, 
			String abilit_collaudo, 
			String abilit_produzione_deroga, 
			String abilit_produzione_ordinaria,
			String abilit_blocco_change, 
			String cod_livello_autorizzativo
			) {
		super();
		this.data = data;
		this.sistema_informativo = sistema_informativo;
		this.piattaforma = piattaforma;
		this.tipo_calendario = tipo_calendario;
		this.descrizione_titolo_calendario = descrizione_titolo_calendario;
		this.abilit_collaudo = abilit_collaudo;
		this.abilit_produzione_deroga = abilit_produzione_deroga;
		this.abilit_produzione_ordinaria = abilit_produzione_ordinaria;
		this.abilit_blocco_change = abilit_blocco_change;
		this.cod_livello_autorizzativo = cod_livello_autorizzativo;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}

	public String getTipo_calendario() {
		return tipo_calendario;
	}
	
	public void setTipo_calendario(String tipo_calendario) {
		this.tipo_calendario = tipo_calendario;
	}

	public String getDescrizione_titolo_calendario() {
		return descrizione_titolo_calendario;
	}

	public void setDescrizione_titolo_calendario(
			String descrizione_titolo_calendario) {
		this.descrizione_titolo_calendario = descrizione_titolo_calendario;
	}

	public String getPiattaforma() {
		return piattaforma;
	}

	public void setPiattaforma(String piattaforma) {
		this.piattaforma = piattaforma;
	}

	public String getSistema_informativo() {
		return sistema_informativo;
	}

	public void setSistema_informativo(String sistema_informativo) {
		this.sistema_informativo = sistema_informativo;
	}

	public String getAbilit_collaudo() {
		return abilit_collaudo;
	}

	public void setAbilit_collaudo(String abilit_collaudo) {
		this.abilit_collaudo = abilit_collaudo;
	}

	public String getAbilit_produzione_deroga() {
		return abilit_produzione_deroga;
	}

	public void setAbilit_produzione_deroga(String abilit_produzione_deroga) {
		this.abilit_produzione_deroga = abilit_produzione_deroga;
	}
	
	public String getAbilit_produzione_ordinaria() {
		return abilit_produzione_ordinaria;
	}

	public void setAbilit_produzione_ordinaria(String abilit_produzione_ordinaria) {
		this.abilit_produzione_ordinaria = abilit_produzione_ordinaria;
	}

	public String getAbilit_blocco_change() {
		return abilit_blocco_change;
	}

	public void setAbilit_blocco_change(String abilit_blocco_change) {
		this.abilit_blocco_change = abilit_blocco_change;
	}

	public String getCod_livello_autorizzativo() {
		return cod_livello_autorizzativo;
	}

	public void setCod_livello_autorizzativo(String cod_livello_autorizzativo) {
		this.cod_livello_autorizzativo = cod_livello_autorizzativo;
	}
}
