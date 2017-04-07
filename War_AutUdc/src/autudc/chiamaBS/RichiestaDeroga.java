package autudc.chiamaBS;

import java.io.Serializable;



public class RichiestaDeroga extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codSistemaInformativo = "";
	private String codPiattaforma = "";
	private String codUdc = "";
	private String codProgetto = "";
	private String descrizioneProgetto = "";
	private String dataProduzione = "";
	
	private String userIdDesctinatario = "";
	private String destinatario = "";
	private String livelloAutorizzativo = "";
	
	
	private String userIdCapoAttivita = "";
	private String capoAttivita = "";
	private String emailCapoAttivita = "";
	private String emailRichiedente = "";
	
	private String dataDeroga = "";
	
	public RichiestaDeroga() {
		super();
	}

	public RichiestaDeroga(
			String userIdDesctinatario, 
			String destinatario,
			String livelloAutorizzativo) {
		super();
		this.userIdDesctinatario = userIdDesctinatario;
		this.destinatario = destinatario;
		this.livelloAutorizzativo = livelloAutorizzativo;
	}

	public RichiestaDeroga(String codSistemaInformativo, String codPiattaforma,
			String codUdc, String codProgetto, String dataProduzione) {
		super();
		this.codSistemaInformativo = codSistemaInformativo;
		this.codPiattaforma = codPiattaforma;
		this.codUdc = codUdc;
		this.codProgetto = codProgetto;
		this.dataProduzione = dataProduzione;
	}
	
	public String getCodSistemaInformativo() {
		return codSistemaInformativo;
	}
	
	public void setCodSistemaInformativo(String codSistemaInformativo) {
		this.codSistemaInformativo = codSistemaInformativo;
	}
	
	public String getCodPiattaforma() {
		return codPiattaforma;
	}

	public void setCodPiattaforma(String codPiattaforma) {
		this.codPiattaforma = codPiattaforma;
	}

	public String getCodUdc() {
		return codUdc;
	}
	
	public void setCodUdc(String codUdc) {
		this.codUdc = codUdc;
	}
	
	public String getCodProgetto() {
		return codProgetto;
	}
	
	public void setCodProgetto(String codProgetto) {
		this.codProgetto = codProgetto;
	}
	
	public String getDataProduzione() {
		return dataProduzione;
	}
	
	public void setDataProduzione(String dataProduzione) {
		this.dataProduzione = dataProduzione;
	}
	
	public String getUserIdDesctinatario() {
		return userIdDesctinatario;
	}
	
	public void setUserIdDesctinatario(String userIdDesctinatario) {
		this.userIdDesctinatario = userIdDesctinatario;
	}
	
	public String getDestinatario() {
		return destinatario;
	}
	
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	
	public String getLivelloAutorizzativo() {
		return livelloAutorizzativo;
	}
	
	public void setLivelloAutorizzativo(String livelloAutorizzativo) {
		this.livelloAutorizzativo = livelloAutorizzativo;
	}

	public String getUserIdCapoAttivita() {
		return userIdCapoAttivita;
	}

	public void setUserIdCapoAttivita(String userIdCapoAttivita) {
		this.userIdCapoAttivita = userIdCapoAttivita;
	}

	public String getCapoAttivita() {
		return capoAttivita;
	}

	public void setCapoAttivita(String capoAttivita) {
		this.capoAttivita = capoAttivita;
	}

	public String getDescrizioneProgetto() {
		return descrizioneProgetto;
	}

	public void setDescrizioneProgetto(String descrizioneProgetto) {
		this.descrizioneProgetto = descrizioneProgetto;
	}

	public String getEmailCapoAttivita() {
		return emailCapoAttivita;
	}

	public void setEmailCapoAttivita(String emailCapoAttivita) {
		this.emailCapoAttivita = emailCapoAttivita;
	}

	public String getEmailRichiedente() {
		return emailRichiedente;
	}

	public void setEmailRichiedente(String emailRichiedente) {
		this.emailRichiedente = emailRichiedente;
	}

	public String getDataDeroga() {
		return dataDeroga;
	}

	public void setDataDeroga(String dataDeroga) {
		this.dataDeroga = dataDeroga;
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
