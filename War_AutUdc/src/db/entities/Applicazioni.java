package db.entities;

public class Applicazioni {
	private String acronimo="";
	private String ssa = "";
	private String padre =""; // vale P=solo padre, F= figlio, X= padre con figli
	private String acronimoPadre ="";
	private String flagDistribPile="";
	
	public Applicazioni(String acronimo, String ssa, String padre,
			String acronimoPadre, String flagDistribPile) {
		super();
		this.acronimo = acronimo;
		this.ssa = ssa;
		this.padre = padre;
		this.acronimoPadre = acronimoPadre;
		this.flagDistribPile = flagDistribPile;
	}
	public String getAcronimo() {
		return acronimo;
	}
	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}
	public String getSsa() {
		return ssa;
	}
	public void setSsa(String ssa) {
		this.ssa = ssa;
	}
	public String getPadre() {
		return padre;
	}
	public void setPadre(String padre) {
		this.padre = padre;
	}
	public String getFlagDistribPile() {
		return flagDistribPile;
	}
	public void setFlagDistribPile(String flagDistribPile) {
		this.flagDistribPile = flagDistribPile;
	}
	public String getAcronimoPadre() {
		return acronimoPadre;
	}
	public void setAcronimoPadre(String acronimoPadre) {
		this.acronimoPadre = acronimoPadre;
	}



}
