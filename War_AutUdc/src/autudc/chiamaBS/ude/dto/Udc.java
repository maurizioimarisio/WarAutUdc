package autudc.chiamaBS.ude.dto;

import java.io.Serializable;
import java.util.LinkedList;

import autudc.chiamaBS.CommonDto;

public class Udc  extends CommonDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Abilitazioni abilitazioni = new Abilitazioni();
	private DatiUdc datiUDc = new DatiUdc();
	private DatiContenutoUdc datiContenutoUdc = new DatiContenutoUdc();
	private DateDiDistribuzione dateDiDistribuzione = new  DateDiDistribuzione();
	private DatiImpatti datiImpatti = new DatiImpatti();
	private DatiUtente datiUtenteCreatore = new DatiUtente();
	private DatiUtente datiUtenteResponsabile = new DatiUtente();
	private DatiUtente datiUtenteGeco = new DatiUtente();
	private DatiDeroga datiDeroga = new DatiDeroga();
	private DatiPesatura datiPesatura = new DatiPesatura();
	private DatiManifestoDiCollaudo datiManifestoDiCollaudo = new DatiManifestoDiCollaudo();
	private LinkedList prerequisitiUdc = new LinkedList();
	private DatiPianoAllegato pianoDiAttivazione = new DatiPianoAllegato();
	private DatiPianoAllegato pianoDiRollback = new DatiPianoAllegato();
	private DatiEmergenza datiEmergenza = new DatiEmergenza();
		
	public Udc() {}

	public Udc(
			Abilitazioni abilitazioni,
			DatiUdc datiUDc, 
			DatiContenutoUdc datiContenutoUdc,
			DateDiDistribuzione dateDiDistribuzione,
			DatiImpatti datiImpatti,
			DatiUtente datiUtenteCreatore,
			DatiUtente datiUtenteResponsabile, 
			DatiUtente datiUtenteGeco,
			DatiDeroga datiDeroga, 
			DatiPesatura datiPesatura,
			DatiManifestoDiCollaudo datiManifestoDiCollaudo,
			LinkedList prerequisitiUdc,
			DatiPianoAllegato pianoDiAttivazione,
			DatiPianoAllegato pianoDiRollback,
			DatiEmergenza datiEmergenza 
		) {
		super();
		this.abilitazioni = abilitazioni;
		this.datiUDc = datiUDc;
		this.datiContenutoUdc = datiContenutoUdc;
		this.dateDiDistribuzione = dateDiDistribuzione;
		this.datiImpatti = datiImpatti;
		this.datiUtenteCreatore = datiUtenteCreatore;
		this.datiUtenteResponsabile = datiUtenteResponsabile;
		this.datiUtenteGeco = datiUtenteGeco;
		this.datiDeroga = datiDeroga;
		this.datiPesatura = datiPesatura;
		this.datiManifestoDiCollaudo = datiManifestoDiCollaudo;
		this.prerequisitiUdc = prerequisitiUdc;
		this.pianoDiAttivazione = pianoDiAttivazione;
		this.pianoDiRollback = pianoDiRollback;
		this.datiEmergenza = datiEmergenza;
	}

	public Abilitazioni getAbilitazioni() {
		return abilitazioni;
	}

	public DatiUdc getDatiUDc() {
		return datiUDc;
	}

	public DatiContenutoUdc getDatiContenutoUdc() {
		return datiContenutoUdc;
	}

	public DateDiDistribuzione getDateDiDistribuzione() {
		return dateDiDistribuzione;
	}

	public DatiImpatti getDatiImpatti() {
		return datiImpatti;
	}

	public DatiUtente getDatiUtenteCreatore() {
		return datiUtenteCreatore;
	}

	public DatiUtente getDatiUtenteResponsabile() {
		return datiUtenteResponsabile;
	}

	public DatiUtente getDatiUtenteGeco() {
		return datiUtenteGeco;
	}

	public DatiDeroga getDatiDeroga() {
		return datiDeroga;
	}

	public DatiPesatura getDatiPesatura() {
		return datiPesatura;
	}

	public DatiManifestoDiCollaudo getDatiManifestoDiCollaudo() {
		return datiManifestoDiCollaudo;
	}

	public LinkedList getPrerequisitiUdc() {
		return prerequisitiUdc;
	}

	public DatiPianoAllegato getPianoDiAttivazione() {
		return pianoDiAttivazione;
	}

	public DatiPianoAllegato getPianoDiRollback() {
		return pianoDiRollback;
	}

	public DatiEmergenza getDatiEmergenza() {
		return datiEmergenza;
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
