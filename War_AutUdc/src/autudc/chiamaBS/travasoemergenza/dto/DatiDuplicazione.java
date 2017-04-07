package autudc.chiamaBS.travasoemergenza.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;


public class DatiDuplicazione extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codiceIncidente = "";
	private String numeroRichiesta = "";
	
	public DatiDuplicazione(){}
	
	public DatiDuplicazione(
			String codiceIncidente, 
			String numeroRichiesta) {
		this.codiceIncidente = codiceIncidente;
		this.numeroRichiesta = numeroRichiesta;
	}

	public String getCodiceIncidente() {
		return codiceIncidente;
	}
	
	public String getNumeroRichiesta() {
		return numeroRichiesta;
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
