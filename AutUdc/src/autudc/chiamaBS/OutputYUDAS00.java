/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autudc.chiamaBS;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author GF001126
 */
public class OutputYUDAS00 extends CommonDto implements Serializable{

	private static final long serialVersionUID = 1L;

    private RichiestaDeroga richiestaDeroga = new RichiestaDeroga();
    private LinkedList elencoUdc = new LinkedList();

    public OutputYUDAS00() {}

    public RichiestaDeroga getRichiestaDeroga() {
        return richiestaDeroga;
    }
    public void setRichiestaDeroga(RichiestaDeroga richiestaDeroga) {
    	this.richiestaDeroga = richiestaDeroga;
    }
    
    public LinkedList getElencoUdc() {
        return elencoUdc;
    }
    
    public void setElencoUdc(LinkedList eu) {
        elencoUdc = eu;
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
