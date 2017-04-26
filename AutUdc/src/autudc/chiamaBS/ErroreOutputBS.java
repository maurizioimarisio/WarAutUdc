package autudc.chiamaBS;

import java.io.Serializable;

/**
 *
 * @author U0G8898
 */
public class ErroreOutputBS extends CommonDto implements Serializable{

	private static final long serialVersionUID = 1L;

    private boolean esito = true;
    private String messaggioErrore = "";
    
    public ErroreOutputBS(){}

    public ErroreOutputBS(boolean esito, String messaggioErrore) {
        this.esito = esito;
        this.messaggioErrore = messaggioErrore;
    }

    public boolean isEsito() {
        return esito;
    }
    
    public void setEsito(boolean esito) {
    	this.esito = esito;
    }

    public String getMessaggioErrore() {
        return messaggioErrore;
    }
    
    public void setMessaggioErrore(String messaggioErrore) {
    	this.messaggioErrore = messaggioErrore;
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
