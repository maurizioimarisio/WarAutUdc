package autudc.chiamaBS.travasoemergenza.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;


public class MotivoEmergenza extends CommonDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codiceEmergenza = "";
	private String descrEmergenza = "";


	public MotivoEmergenza(){}
	
	public MotivoEmergenza(
			String codiceEmergenza, 
			String descrEmergenza) {
		this.codiceEmergenza = codiceEmergenza;
		this.descrEmergenza = descrEmergenza;
	}

	public String getCodiceEmergenza() {
		return codiceEmergenza;
	}

	public String getDescrEmergenza() {
		return descrEmergenza;
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
