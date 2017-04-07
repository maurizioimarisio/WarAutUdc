package db.managers;

import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import persistence.EntityManager;
import persistence.EntityManagerFactory;
import persistence.PersistenceProvider;
import persistence.Query;
import Utility.Utility;
import acronimi.Applicazioni;
import acronimi.TipoCalendarioCriterio;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import db.connection.OracleConnection;
import db.entities.struct.DatiAcronimoApplicazione;
import db.entities.struct.ElencoAcronimi;

public class ApplicazioniManager {
	private static Logger logger = null;

	public ApplicazioniManager() {
		super();
		try {
			if (logger == null) {
				String pkgName = this.getClass().getPackage().getName();
				logger = LoggerFactory.getLogger(pkgName);
			}
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
    	logger.debug("Costruttore AllegatiManager");
	}

	public Vector loadApplicazioniPadre(String utente, String sistemaInformativo){
		logger.debug(utente + " | ApplicazioniManager.loadApplicazioniPadre - inizio");
		Vector vet = new Vector();
		
		EntityManagerFactory emf = PersistenceProvider.createEntityManagerFactory(Applicazioni.class.getPackage().getName(), null);
		EntityManager entityMgr = emf.createEntityManager();
		String strQuery = "FROM Applicazioni " +
						"  WHERE acronimo_principale IS NULL " +
						"  AND sistema_informativo='"+sistemaInformativo+"' " +
						"  ORDER BY acronimo_appl";
		logger.debug(utente + " | ApplicazioniManager.loadApplicazioniPadre STRSQL (Prima): "+strQuery);
		Query query = entityMgr.createQuery(strQuery);
		
		logger.debug(utente + " | ApplicazioniManager.loadApplicazioniPadre STRSQL (Dopo): "+strQuery);
		
		Iterator myIter = null;

		myIter = query.list().listIterator();
		Applicazioni row = null;
		try{
			while (myIter.hasNext()) {
				row = (Applicazioni)myIter.next();
				vet.add(row);
			}
		} catch (Exception e) {
			logger.error(utente+ " | ApplicazioniManager.loadApplicazioniPadre   Exception = "	+ e.getMessage());
		}finally{
			entityMgr.close();			
		}	
		logger.debug(utente + " | ApplicazioniManager.loadApplicazioniPadre - fine");
		return vet;
	}
	
	public Hashtable allAcronimiHsh(String utente, String sistemaInformativo){
		logger.debug(utente + " | ApplicazioniManager.allAcronimiHsh - inizio");
		Hashtable hsh = new Hashtable();
		
		EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(Applicazioni.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		Query myQuery = myEntityManager.createQuery("SELECT applicazioni.acronimo_appl, applicazioni.desc_appl, applicazioni.cod_fascia_distr, "
				+ "applicazioni.cod_ssa, applicazioni.flag_distr_pile, applicazioni.cod_architettura, "
				+ "applicazioni.cod_zona, applicazioni.cod_sistema_operativo, applicazioni.cod_canale_mw, "
				+ "architetture.desc_architettura FROM Applicazioni as applicazioni, Architetture as architetture "
				+ "WHERE applicazioni.cod_architettura=architetture.cod_architettura and applicazioni.flag_appl_attiva<>? " 
				+ " and  applicazioni.sistema_informativo='"+sistemaInformativo+"' "
				+ "order by applicazioni.acronimo_appl");
	
		myQuery.setString(0,"N");
		logger.debug(utente + " | ApplicazioniManager.allAcronimiVect STRSQL: "+ myQuery.getQueryString());
  	
		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();
			
			while (myIter.hasNext()) {
				Object[] row = (Object[]) myIter.next();
				ElencoAcronimi elencoAcronimi = new ElencoAcronimi();

				elencoAcronimi.setCodiceAcronimo(Utility.checkNull((String) row[0]));
				elencoAcronimi.setDescrizioneAcronimo(Utility.checkNull((String) row[1]));
				elencoAcronimi.setCodiceFascia(Utility.checkNull((String) row[2]));
				elencoAcronimi.setSsa(Utility.checkNull((String) row[3]));
				elencoAcronimi.setPila(Utility.checkNull((String) row[4]));
				elencoAcronimi.setCodiceArchitettura(Utility.checkNull((String) row[5]));
				elencoAcronimi.setCodiceZona(Utility.checkNull((String) row[6]));
				elencoAcronimi.setCodiceSO(Utility.checkNull((String) row[7]));
				elencoAcronimi.setCodiceCanaleMW(Utility.checkNull((String) row[8]));
				elencoAcronimi.setDescrizioneArchitettura(Utility.checkNull((String) row[9]));
				elencoAcronimi.setNomeClusterProd(Utility.checkNull(""));

				hsh.put(elencoAcronimi.getCodiceAcronimo().toUpperCase(),elencoAcronimi.getSsa().toUpperCase());
				
			}
		} catch (Exception e) {			
			logger.error(utente+ " | ApplicazioniManager.allAcronimiHsh  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}

		logger.debug(utente + " | ApplicazioniManager.allAcronimiHsh - fine");

		return hsh;
	}
	
	public Vector allAcronimiVect(String utente, String sistemaInformativo){
		logger.debug(utente + " | ApplicazioniManager.allAcronimiVect - inizio");
		Vector vec = new Vector();
		
		EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(Applicazioni.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		String sql= "SELECT applicazioni.acronimo_appl, applicazioni.desc_appl, applicazioni.cod_fascia_distr, "+
					 "	applicazioni.cod_ssa, applicazioni.flag_distr_pile, applicazioni.cod_architettura, "+
					 "	applicazioni.cod_zona, applicazioni.cod_sistema_operativo, applicazioni.cod_canale_mw, "+
					 "	architetture.desc_architettura " +
					 " FROM Applicazioni as applicazioni, Architetture as architetture" +
					 "		WHERE applicazioni.cod_architettura=architetture.cod_architettura " +
					 " 			AND applicazioni.flag_appl_attiva<>? "+
					 " 			AND applicazioni.sistema_informativo=? "+
					 "	ORDER BY applicazioni.acronimo_appl";
		Query myQuery = myEntityManager.createQuery(sql);
	
		myQuery.setString(0,"N");
		myQuery.setString(1,sistemaInformativo);
		logger.debug(utente + " | ApplicazioniManager.allAcronimiVect STRSQL: "+ myQuery.getQueryString());
  	
		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();
			
			while (myIter.hasNext()) {
				Object[] row = (Object[]) myIter.next();
				ElencoAcronimi elencoAcronimi = new ElencoAcronimi();

				elencoAcronimi.setCodiceAcronimo(Utility.checkNull((String) row[0]));
				elencoAcronimi.setDescrizioneAcronimo(Utility.checkNull((String) row[1]));
				elencoAcronimi.setCodiceFascia(Utility.checkNull((String) row[2]));
				elencoAcronimi.setSsa(Utility.checkNull((String) row[3]));
				elencoAcronimi.setPila(Utility.checkNull((String) row[4]));
				elencoAcronimi.setCodiceArchitettura(Utility.checkNull((String) row[5]));
				elencoAcronimi.setCodiceZona(Utility.checkNull((String) row[6]));
				elencoAcronimi.setCodiceSO(Utility.checkNull((String) row[7]));
				elencoAcronimi.setCodiceCanaleMW(Utility.checkNull((String) row[8]));
				elencoAcronimi.setDescrizioneArchitettura(Utility.checkNull((String) row[9]));
				elencoAcronimi.setNomeClusterProd(Utility.checkNull(""));
				
				vec.add(elencoAcronimi);
			}
		} catch (Exception e) {			
			logger.error(utente+ " | ApplicazioniManager.allAcronimiVect Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}
		logger.debug(utente + " | ApplicazioniManager.allAcronimiVect - fine");

		return vec;
	}
	public Vector acronimi4SSA(
			String utente, 
			String SSAPrincipale, 
			String AltriSSA, 
			String sistemaInformativo, 
			String piattaforma, 
			String codCalendario,
			String criterio){
		logger.debug(utente + " | ApplicazioniManager.acronimi4SSA - inizio");
		logger.debug(utente+" SSAPrincipale "+SSAPrincipale+" AltriSSA "+AltriSSA+" sistemaInformativo "+sistemaInformativo+" piattaforma "+piattaforma+" codCalendario "+codCalendario+" criterio "+criterio);
		Vector vec = new Vector();		
		String whereSSA = "";
		if ("".equals(AltriSSA.trim())){
			whereSSA+= "AND applicazioni.cod_ssa IN ('"+SSAPrincipale+"')";
		} else {
			whereSSA+= "AND applicazioni.cod_ssa IN ('"+SSAPrincipale+"','"+AltriSSA.replaceAll(" ", "','")+"')";
		}
		whereSSA+=" AND applicazioni.sistema_informativo=? 	AND "+
			"(SELECT count(*) FROM Distrib_Applicazione AS da "+
	 	 	"	 WHERE da.id.acronimo_appl=applicazioni.acronimo_appl "+
			"	 AND da.id.sistema_informativo=applicazioni.sistema_informativo)>0 ";
		
		EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(Applicazioni.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		
		String sqlQrery = "SELECT applicazioni.acronimo_appl, applicazioni.desc_appl, applicazioni.cod_fascia_distr, "
			+ " applicazioni.cod_ssa, applicazioni.flag_distr_pile, applicazioni.distribuzione_differita,applicazioni.data_differita, applicazioni.cod_architettura, "
			+ " applicazioni.cod_zona, applicazioni.cod_sistema_operativo, applicazioni.cod_canale_mw,  "
			+ " architetture.desc_architettura, applicazioni.sistema_informativo, " 
			+ " applicazioni.cod_calendario, criterio.criterio, criterio.criterio_default, piattaforma.descrizione, applicazioni.flag_emerg_speciale "
			+ " FROM Applicazioni as applicazioni, Architetture as architetture, " 
			+ " TipoCalendarioPiattaforma as piattaforma,  TipoCalendarioCriterio as criterio"
			+ " WHERE applicazioni.cod_architettura=architetture.cod_architettura " 
			+ " and applicazioni.flag_appl_attiva<>? " 
			+ " AND applicazioni.sistema_informativo = criterio.sistema_informativo "
			+ " AND applicazioni.piattaforma = criterio.piattaforma "
			+ " AND applicazioni.sistema_informativo = piattaforma.sistema_informativo "
			+ " AND applicazioni.piattaforma = piattaforma.piattaforma ";
			if (TipoCalendarioCriterio.CRITERIO_ACRONIMO.equals(criterio)) {
				sqlQrery = sqlQrery + " AND applicazioni.cod_calendario = piattaforma.cod_calendario ";
//			} else if (TipoCalendarioCriterio.CRITERIO_TIPO_UDC.equals(criterio)) {
//				" TipoUdc tipoUdc ";
//			} else if (TipoCalendarioCriterio.CRITERIO_FAMIGLIA.equals(criterio)) {
//				" Famiglia famiglia "
			} else if (TipoCalendarioCriterio.isCriterioDefault(criterio)){
				if (!"".equals(codCalendario.trim()) ){
					sqlQrery = sqlQrery + " AND criterio.criterio_default = '"+criterio+"'";
				}
			} else {
				if (!"".equals(codCalendario.trim()) ){
					sqlQrery = sqlQrery + " AND piattaforma.cod_calendario ='" +codCalendario+"'";
				}
			}
		sqlQrery = sqlQrery + whereSSA;
		
		if (!"".equals(piattaforma.trim())){
			sqlQrery = sqlQrery + " AND applicazioni.piattaforma = ?";
		}
		if (!"".equals(codCalendario.trim()) ){
			sqlQrery = sqlQrery + " AND piattaforma.cod_calendario = ?";
		}
		
		sqlQrery = sqlQrery + " order by applicazioni.acronimo_appl";
		
		Query myQuery = myEntityManager.createQuery(sqlQrery);
	
		myQuery.setString(0,"N");
		myQuery.setString(1,sistemaInformativo);
		if (!"".equals(piattaforma.trim()) && !"".equals(codCalendario.trim())){
			myQuery.setString(2,piattaforma);
			myQuery.setString(3,codCalendario);
		} else if (!"".equals(piattaforma.trim())){
			myQuery.setString(2,piattaforma);
		} else if (!"".equals(codCalendario.trim())){
			myQuery.setString(2,codCalendario);
		}
			
		logger.debug(utente + " | ApplicazioniManager.acronimi4SSA STRSQL: "+ myQuery.getQueryString());
  	
		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();

			while (myIter.hasNext()) {
				Object[] row = (Object[]) myIter.next();
				ElencoAcronimi elencoAcronimi = new ElencoAcronimi();

				elencoAcronimi.setCodiceAcronimo(Utility.checkNull((String) row[0]));
				elencoAcronimi.setDescrizioneAcronimo(Utility.checkNull((String) row[1]));
				elencoAcronimi.setCodiceFascia(Utility.checkNull((String) row[2]));
				elencoAcronimi.setSsa(Utility.checkNull((String) row[3]));
				elencoAcronimi.setPila(Utility.checkNull((String) row[4]));
				elencoAcronimi.setDistribuzioneDifferita(Utility.checkNull((String) row[5]));
				elencoAcronimi.setDataDifferita(Utility.checkNull((String) row[6]));
				elencoAcronimi.setCodiceArchitettura(Utility.checkNull((String) row[7]));
				elencoAcronimi.setCodiceZona(Utility.checkNull((String) row[8]));
				elencoAcronimi.setCodiceSO(Utility.checkNull((String) row[9]));
				elencoAcronimi.setCodiceCanaleMW(Utility.checkNull((String) row[10]));
				elencoAcronimi.setDescrizioneArchitettura(Utility.checkNull((String) row[11]));
				elencoAcronimi.setSistemaInformativo(Utility.checkNull((String) row[12]));
				elencoAcronimi.setCodiceCalendario(Utility.checkNull((String) row[13]));
				elencoAcronimi.setCriterio(Utility.checkNull((String) row[14]));
				elencoAcronimi.setCriterioDefault(Utility.checkNull((String) row[15]));
				elencoAcronimi.setCriterioDescrizione(Utility.checkNull((String) row[16]));
				elencoAcronimi.setNomeClusterProd(Utility.checkNull(""));
				elencoAcronimi.setFlagEmergSpeciale(Utility.checkNull((String)row[17]));

				vec.add(elencoAcronimi);
			}
		} catch (Exception e) {			
			logger.error(utente+ " | ApplicazioniManager.acronimi4SSA  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}

		logger.debug(utente + " | ApplicazioniManager.acronimi4SSA - fine");

		return vec;
	}
		
	public Vector acronimifigliDi(String utente, String acronimoPadre, String sistemaInformativo){
		logger.debug(utente + " | ApplicazioniManager.acronimifigliDi - inizio");
		Vector vec = new Vector();
			
		EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(Applicazioni.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		Query myQuery = myEntityManager.createQuery("FROM Applicazioni "
						+ "WHERE flag_appl_attiva<>? " 
						+ " and acronimo_principale='"+acronimoPadre.toUpperCase()+"' " 
						+ " and sistema_informativo='"+sistemaInformativo+"' " 
						+ "order by acronimo_appl");
	
		myQuery.setString(0,"N");
		logger.debug(utente + " | ApplicazioniManager.acronimifigliDi STRSQL: "+ myQuery.getQueryString());
  		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();

			while (myIter.hasNext()) {
				Applicazioni row = (Applicazioni) myIter.next();
				
				vec.add(Utility.checkNull(row.getAcronimo_appl()).trim());
			}
		} catch (Exception e) {			
			logger.error(utente+ " | ApplicazioniManager.acronimifigliDi  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}
		logger.debug(utente + " | ApplicazioniManager.acronimifigliDi - fine");
		return vec;
	}
	public Vector acronimi4SSA(String utente, String SSAPrincipale, String sistemaInformativo){
		logger.debug(utente + " | ApplicazioniManager.acronimi4SSA - inizio");
		Vector vec = new Vector();
			
		EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(Applicazioni.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		Query myQuery = myEntityManager.createQuery("FROM Applicazioni "
						+ "WHERE flag_appl_attiva<>? " 
						+ " and cod_ssa='"+SSAPrincipale+"' " 
						+ " and sistema_informativo='"+sistemaInformativo+"' " 
						+ "order by acronimo_appl");
	
		myQuery.setString(0,"N");
		logger.debug(utente + " | ApplicazioniManager.acronimi4SSA STRSQL: "+ myQuery.getQueryString());
  		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();

			while (myIter.hasNext()) {
				Applicazioni row = (Applicazioni) myIter.next();
				
				vec.add(new DatiAcronimoApplicazione(Utility.checkNull(row.getAcronimo_appl()).trim(),
						Utility.checkNull(row.getDesc_appl()).trim(),
						Utility.checkNull(row.getCod_ssa()).trim(),
						Utility.checkNull(row.getNome_vob()).trim(),
						Utility.checkNull(row.getCod_zona()).trim(),
						Utility.checkNull(row.getCod_architettura()).trim(),
						Utility.checkNull(row.getCod_sistema_operativo()).trim(),
						Utility.checkNull(row.getCod_fascia_distr()).trim(),
						"",
						Utility.checkNull(row.getFlag_distr_pile()).trim(),
						Utility.checkNull(row.getCod_canale_mw()).trim(),
						Utility.checkNull(row.getAcronimo_principale()).trim(),
						Utility.checkNull(row.getSistema_informativo()).trim()
						));
			}
		} catch (Exception e) {
			logger.error(utente+ " | ApplicazioniManager.acronimi4SSA  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}
		logger.debug(utente + " | ApplicazioniManager.acronimi4SSA - fine");

		return vec;
	}

	
	public Applicazioni datiAcronimo(String utente, String acronimo,String sistemaInformativo){
		logger.debug(utente + " | ApplicazioniManager.datiAcronimo - inizio" );
		Applicazioni applic=null;
		logger.debug(utente + " | ApplicazioniManager.datiAcronimo - parametri in entrata: Acronimo:" +acronimo +"||sistemaInformativo:" + sistemaInformativo );
		logger.debug("ApplicazioniManager.datiAcronimo valore di Applicazioni.class.getPackage().getName() "+ Applicazioni.class.getPackage().getName());
			
		EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(Applicazioni.class.getPackage().getName(), null);
		logger.debug(utente + " | ApplicazioniManager.datiAcronimo effettuate chiamata alla PersistenceProvider.createEntityManagerFactory" );

		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		String sql="FROM Applicazioni  " +
				" WHERE    acronimo_appl='"+acronimo.trim()+"' " +
				"	AND sistema_informativo='"+sistemaInformativo+"'";
		
		Query myQuery = myEntityManager.createQuery(sql);
	
		logger.debug(utente + " | ApplicazioniManager.datiAcronimo STRSQL: "+ myQuery.getQueryString());
  		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();
			if (myIter.hasNext()) {
				applic = (Applicazioni) myIter.next();
			}
		} catch (Exception e) {			
			logger.error(utente+ " | ApplicazioniManager.datiAcronimo  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}	

		logger.debug(utente + " | ApplicazioniManager.acronimi4SSA - fine");
		return applic;
	}
    public String zonaAcronimo(String utente, String acronimo, String sistemaInformativo){
    	logger.debug(utente + " | ApplicazioniManager.zonaAcronimo - inizio");
		String cod_zona="";
			
		EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(Applicazioni.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		String sql="FROM Applicazioni a " +
				" WHERE  a.acronimo_appl='"+acronimo+"' " +
				"	AND sistema_informativo='"+sistemaInformativo+"'";
		
		Query myQuery = myEntityManager.createQuery(sql);
	
		logger.debug(utente + " | ApplicazioniManager.zonaAcronimo STRSQL: "+ myQuery.getQueryString());
  		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();
			logger.debug(utente+ " | ApplicazioniManager.zonaAcronimo ");
			if (myIter.hasNext()) {
				Applicazioni row = (Applicazioni)myIter.next();
				cod_zona = Utility.checkNull(row.getCod_zona()).trim();				
			}
		} catch (Exception e) {
			logger.error(utente+ " | ApplicazioniManager.zonaAcronimo  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}		
		logger.debug(utente + " | ApplicazioniManager.zonaAcronimo - fine");		
		return cod_zona;
    }
    public Vector leggiTuttiFigli(String utente, String acronimoPadre, String sistemaInformativo){
    	logger.debug(utente + " | ApplicazioniManager.leggiTuttiFigli - inizio");
    	Vector vet = new Vector();
    	EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(Applicazioni.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		String sql= "FROM Applicazioni A " +
				" WHERE  A.acronimo_principale='"+acronimoPadre+"' " +
				"	AND sistema_informativo='"+sistemaInformativo+"'";
		Query myQuery = myEntityManager.createQuery(sql);
	
		logger.debug(utente + " | ApplicazioniManager.leggiTuttiFigli STRSQL: "+ myQuery.getQueryString());
  		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();
			logger.debug(utente+ " | ApplicazioniManager.leggiTuttiFigli  inizo lettura iteratore = ");
			while (myIter.hasNext()) {
				Applicazioni row = (Applicazioni)myIter.next();
				vet.add(Utility.checkNull(row.getAcronimo_appl()).trim());
			}			
		} catch (Exception e) {
			logger.error(utente+ " | ApplicazioniManager.leggiTuttiFigli  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}		
		logger.debug(utente + " | ApplicazioniManager.leggiTuttiFigli - fine");		
		return vet;
    }
    public String ssaAcronimo(String utente, String acronimo, String sistemaInformativo){
    	logger.debug(utente + " | ApplicazioniManager.ssaAcronimo - inizio");
    	String ssaAcronimo="";
    	EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(Applicazioni.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		String sql=" FROM Applicazioni " +
				" WHERE  acronimo_appl='"+acronimo+"' " +
				"	AND sistema_informativo='"+sistemaInformativo+"'";
		Query myQuery = myEntityManager.createQuery(sql);
	
		logger.debug(utente + " | ApplicazioniManager.ssaAcronimo STRSQL: "+ myQuery.getQueryString());
  		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();
			if(myIter.hasNext()) {
				Applicazioni row = (Applicazioni)myIter.next();
				ssaAcronimo=Utility.checkNull(row.getCod_ssa()).trim();
			}
		} catch (Exception e) {			
			logger.error(utente+ " | ApplicazioniManager.ssaAcronimo  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}		
		logger.debug(utente + " | ApplicazioniManager.ssaAcronimo - fine");		
		return ssaAcronimo;
    } 
    public String leggiAcronimoPadre(String utente, String acronimo, String sistemaInformativo){
    	logger.debug(utente + " | ApplicazioniManager.leggiAcronimoPadre - inizio");
    	String padre="";
    	EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(Applicazioni.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		String sql=" FROM Applicazioni " +
				" WHERE  acronimo_appl='"+acronimo+"' " +
				"	AND sistema_informativo='"+sistemaInformativo+"'";
		Query myQuery = myEntityManager.createQuery(sql);
	
		logger.debug(utente + " | ApplicazioniManager.leggiAcronimoPadre STRSQL: "+ myQuery.getQueryString());
  		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();
			if(myIter.hasNext()) {
				Applicazioni row = (Applicazioni)myIter.next();
				padre=Utility.checkNull(row.getAcronimo_principale()).trim();
			}
		} catch (Exception e) {			
			logger.error(utente+ " | ApplicazioniManager.leggiAcronimoPadre  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}		
		logger.debug(utente + " | ApplicazioniManager.leggiAcronimoPadre - fine");		
		return padre;
    }
    
    public String acronimoAPila(String utente, String acronimo, String sistemaInformativo){
    	//logger.debug(utente + " | ApplicazioniManager.acronimoAPila - inizio");
    	String tipoPila="";
    	EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(Applicazioni.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		String sqlSel="FROM Applicazioni ap " +
				"	WHERE  ap.acronimo_appl='"+acronimo+"'" +
				" 		AND ap.sistema_informativo='"+sistemaInformativo+"' ";
		Query myQuery = myEntityManager.createQuery(sqlSel);
		//logger.debug(utente + " | ApplicazioniManager.acronimoAPila STRSQL: "+ myQuery.getQueryString());
  		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();
			
			if(myIter.hasNext()) {				
				Applicazioni app =(Applicazioni)myIter.next();
				tipoPila=app.getFlag_distr_pile();
			}
		} catch (Exception e) {
			logger.error(utente+ " | ApplicazioniManager.acronimoAPila  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}		
		//logger.debug(utente + " | ApplicazioniManager.acronimoAPila - fine");		
		return tipoPila;
    }
    public String acronimoAPilaDifferita(String utente, String acronimo, String sistemaInformativo){
    	String distribuzioneDifferita="";
    	EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(Applicazioni.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		String sqlSel="FROM Applicazioni ap " +
				"	WHERE  ap.acronimo_appl='"+acronimo+"'" +
				" 		AND ap.sistema_informativo='"+sistemaInformativo+"' ";
		Query myQuery = myEntityManager.createQuery(sqlSel);
		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();
			
			if(myIter.hasNext()) {				
				Applicazioni app =(Applicazioni)myIter.next();
				distribuzioneDifferita=app.getDistribuzione_differita();
			}
		} catch (Exception e) {
			logger.error(utente+ " | ApplicazioniManager.acronimoAPila  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}				
		return distribuzioneDifferita;
    }
    
    public Vector acronimoPerSIeFascia(String utente, String sistemaInformativo, String fasciaDistribuzione){
    	logger.debug(utente + " | ApplicazioniManager.acronimoPerSIeFascia - inizio");
    	Vector vec=new Vector();
    	EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(Applicazioni.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		String sqlSel="FROM Applicazioni ap " +
				"	WHERE  ap.cod_fascia_distr='"+fasciaDistribuzione+"'" +
				" 	   AND ap.sistema_informativo='"+sistemaInformativo+"' ";
		Query myQuery = myEntityManager.createQuery(sqlSel);
		logger.debug(utente + " | ApplicazioniManager.acronimoPerSIeFascia STRSQL: "+ myQuery.getQueryString());
  		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();			
			if(myIter.hasNext()) {
				Applicazioni app =(Applicazioni)myIter.next();
				vec.add(app);
			}
		} catch (Exception e) {
			logger.error(utente+ " | ApplicazioniManager.acronimoPerSIeFascia  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}		
		logger.debug(utente + " | ApplicazioniManager.acronimoPerSIeFascia - fine");		
		return vec;
    }
    public String  recuperaFlagAppplicazioneCritica(String utente, String acronimo, String sistemaInformativo){
		logger.debug(utente + " | ApplicazioniManager.recuperaFLagAppplicazioneCritica - inizio");
		String flagApplicazioneCritica=null;
		String select ="SELECT FLAG_NO_MANAGEMENT " +
				"	FROM CHCI_OWN.T_ACR_APPLICAZIONI " +
				"	WHERE ACRONIMO_APPL='"+acronimo.trim()+"' " +
				"		AND SISTEMA_INFORMATIVO = '"+sistemaInformativo.trim()+"'";
		logger.debug(utente + " | ApplicazioniManager.recuperaFlagAppplicazioneCritica( - query: "+ select);
		
		OracleConnection con = new OracleConnection(utente);
		con.initConn();
		try {
			ResultSet rs = con.GetQuery(select);
			if (rs != null) {
				if (rs.next()) {					
					flagApplicazioneCritica= (Utility.checkNull((String)rs.getString("FLAG_NO_MANAGEMENT")));
				}
				
			}
		}catch(Exception e) {
			logger.error(" | ApplicazioniManager.recuperaFlagAppplicazioneCritica( Exception "+ e.getMessage());					
		}finally {
			con.close();
		}
		logger.debug(" | flagApplicazioneCritica   "+flagApplicazioneCritica);

		return flagApplicazioneCritica;
	}
    public String acronimoVobRTC(String utente, String acronimo, String sistemaInformativo){
    	logger.debug(utente + " | ApplicazioniManager.acronimoVobRTC - inizio");
		String vob_Rtc="";
			
		EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(Applicazioni.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		String sql="SELECT vob_rtc FROM Applicazioni a " +
				" WHERE  a.acronimo_appl='"+acronimo+"' " +
				"	AND sistema_informativo='"+sistemaInformativo+"'";
		
		Query myQuery = myEntityManager.createQuery(sql);
	
		logger.debug(utente + " | ApplicazioniManager.zonaAcronimo STRSQL: "+ myQuery.getQueryString());
  		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();
			logger.debug(utente+ " | ApplicazioniManager.acronimoVobRTC ");
			if (myIter.hasNext()) {
				String row = (String)myIter.next();
				vob_Rtc = Utility.checkNull(row).trim();
			}
		} catch (Exception e) {
			logger.error(utente+ " | ApplicazioniManager.acronimoVobRTC  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}		
		logger.debug(utente + " | ApplicazioniManager.acronimoVobRTC - fine");		
		return vob_Rtc;
    }
    
    // -- mg 20-02-2012 gestione del flag Emergenza speciale per UDC tipo Finance -- inizio
    public   String recuperaFlagEmergenzaSpeciale( String acronimo, String sistemaInformativo){
		logger.debug( " | ApplicazioniManager.recuperaFLagAppplicazioneCritica - inizio");
		String flagEmergenzaSpeciale=null;
		String select ="SELECT FLAG_EMERG_SPECIALE " +
				"	FROM CHCI_OWN.T_ACR_APPLICAZIONI " +
				"	WHERE ACRONIMO_APPL='"+acronimo.trim()+"' " +
				"		AND SISTEMA_INFORMATIVO = '"+sistemaInformativo.trim()+"'";
		logger.debug( " | ApplicazioniManager.recuperaFlagEmergenzaSpeciale( - query: "+ select);
		
		OracleConnection con = new OracleConnection("");
		con.initConn();
		try {
			ResultSet rs = con.GetQuery(select);
			if (rs != null) {
				if (rs.next()) {					
					flagEmergenzaSpeciale= (Utility.checkNull((String)rs.getString("FLAG_EMERG_SPECIALE")));
				}
				
			}
		}catch(Exception e) {
			logger.error(" | ApplicazioniManager.recuperaFlagEmergenzaSpeciale( Exception "+ e.getMessage());					
		}finally {
			con.close();
		}
		logger.debug(" |ApplicazioniManager.recuperaFlagEmergenzaSpeciale   "+flagEmergenzaSpeciale);

		return flagEmergenzaSpeciale;
	} 



 // -- mg 20-02-2012 gestione del flag Emergenza speciale per UDC tipo Finance -- fine
 }