package autudc.chiamaBS.ude.dto;

import java.io.Serializable;

import autudc.chiamaBS.CommonDto;

public class DateDiDistribuzione extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String dataCreazione = "";
	private String dataSt = "";
	private String dataIt = "";
	private String dataDistrAddestr = "";
	private String dataPp = "";
	private String dataPpPila1 = "";
	private String distribuzioneDifferita = "";
	private String dataDifferita = "";
	private String codiceVarDataPp = "";
	private String descrVarDataPp = "";
	private String ggInizioTest = "";
	private String dataEsitoDistr = "";
	private String oraLimiteEsito = "";
	private String dataLimitePassaggioProduzione = "";
	private String dataPrimoColl = "";
	private String dataPrimoIt = "";
	
	public DateDiDistribuzione(){}

	public DateDiDistribuzione(
			String dataCreazione, 
			String dataSt,
			String dataIt, 
			String dataDistrAddestr, 
			String dataPp,
			String dataPpPila1, 
			String distribuzioneDifferita,
			String dataDifferita, 
			String codiceVarDataPp,
			String descrVarDataPp, 
			String ggInizioTest, 
			String dataEsitoDistr,
			String oraLimiteEsito, 
			String dataLimitePassaggioProduzione,
			String dataPrimoColl, 
			String dataPrimoIt
		) {
		super();
		this.dataCreazione = dataCreazione;
		this.dataSt = dataSt;
		this.dataIt = dataIt;
		this.dataDistrAddestr = dataDistrAddestr;
		this.dataPp = dataPp;
		this.dataPpPila1 = dataPpPila1;
		this.distribuzioneDifferita = distribuzioneDifferita;
		this.dataDifferita = dataDifferita;
		this.codiceVarDataPp = codiceVarDataPp;
		this.descrVarDataPp = descrVarDataPp;
		this.ggInizioTest = ggInizioTest;
		this.dataEsitoDistr = dataEsitoDistr;
		this.oraLimiteEsito = oraLimiteEsito;
		this.dataLimitePassaggioProduzione = dataLimitePassaggioProduzione;
		this.dataPrimoColl = dataPrimoColl;
		this.dataPrimoIt = dataPrimoIt;
	}

	public String getDataCreazione() {
		return dataCreazione;
	}

	public String getDataSt() {
		return dataSt;
	}

	public String getDataIt() {
		return dataIt;
	}

	public String getDataDistrAddestr() {
		return dataDistrAddestr;
	}

	public String getDataPp() {
		return dataPp;
	}

	public String getDataPpPila1() {
		return dataPpPila1;
	}

	public String getDistribuzioneDifferita() {
		return distribuzioneDifferita;
	}

	public String getDataDifferita() {
		return dataDifferita;
	}

	public String getCodiceVarDataPp() {
		return codiceVarDataPp;
	}

	public String getDescrVarDataPp() {
		return descrVarDataPp;
	}

	public String getGgInizioTest() {
		return ggInizioTest;
	}

	public String getDataEsitoDistr() {
		return dataEsitoDistr;
	}

	public String getOraLimiteEsito() {
		return oraLimiteEsito;
	}

	public String getDataLimitePassaggioProduzione() {
		return dataLimitePassaggioProduzione;
	}

	public String getDataPrimoColl() {
		return dataPrimoColl;
	}

	public String getDataPrimoIt() {
		return dataPrimoIt;
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
