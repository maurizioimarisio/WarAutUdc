package db.managers;

import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import persistence.EntityManager;
import persistence.EntityManagerFactory;
import persistence.PersistenceProvider;
import persistence.Query;
import Utility.DateTime;
import acronimi.TipoCalendario;
import acronimi.TipoCalendarioCriterio;
import acronimi.TipoCalendarioCross;
import acronimi.TipoCalendarioPiattaforma;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;

public class CalendarioManager {
	
	private Logger logger = null;
	
    public CalendarioManager() {
		super();
		try {
            if (logger == null) {
                String pkgName = this.getClass().getPackage().getName();
                logger = LoggerFactory.getLogger(pkgName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.debug("Costruttore CalendarioManager");
	}
    
	
	public TipoCalendarioPiattaforma getTipoCalendarioPiattaforma(
			String owner,
			String sistemaInformativo,
			String piattaforma,
			String calendario
		){
    	logger.debug(owner + " | CalendarioManager.getTipoCalendarioPiattaforma - inizio");
    	TipoCalendarioPiattaforma tipoCalendarioPiattaforma = new TipoCalendarioPiattaforma();
    	
    	EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(TipoCalendarioPiattaforma.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		
//		String sqlSel = "SELECT piattaforma.sistema_informativo, piattaforma.piattaforma, ";
//		sqlSel = sqlSel + " piattaforma.cod_calendario, piattaforma.descrizione, piattaforma.cod_colore ";
		String sqlSel =  " FROM TipoCalendarioPiattaforma piattaforma ";
		sqlSel = sqlSel + " WHERE piattaforma.sistema_informativo = ?";
		sqlSel = sqlSel + " AND piattaforma.piattaforma = ?";
		sqlSel = sqlSel + " AND piattaforma.cod_calendario = ?";
		sqlSel = sqlSel + " ORDER BY piattaforma.sistema_informativo, piattaforma.piattaforma, piattaforma.cod_calendario";

		Query myQuery = myEntityManager.createQuery(sqlSel);
		myQuery.setString(0, sistemaInformativo);
		myQuery.setString(1, piattaforma);
		myQuery.setString(2, calendario);

		logger.debug(owner + " | CalendarioManager.getTipoCalendarioPiattaforma STRSQL: "+ myQuery.getQueryString());

		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();			
			while(myIter.hasNext()) {
				tipoCalendarioPiattaforma = (TipoCalendarioPiattaforma)myIter.next();
			}
		} catch (Exception e) {
			tipoCalendarioPiattaforma = new TipoCalendarioPiattaforma();
			logger.error(owner+ " | CalendarioManager.getTipoCalendarioPiattaforma  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}		
		logger.debug(owner + " | CalendarioManager.getTipoCalendarioPiattaforma - fine");		
		return tipoCalendarioPiattaforma;
    }
	

	
	
	public String getCodiceCalendario(
			String owner, 
			String codiceSistemaInformativo, 
			String codicePiattaforma, 
			String codCalend,
			String criterio,
			String param
	){
		logger.debug(owner + " | CalendarioManager.getCodiceCalendario - inizio");
		
		String codiceCalendario = "";
		EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(TipoCalendarioCross.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		Query myQuery = getQueryForCodiceCalendario(myEntityManager, owner, codiceSistemaInformativo, codicePiattaforma, criterio, param);
		
		Iterator myIter = null;
		if (myQuery.list().size()>1){
			logger.error(owner+ " | CalendarioManager.getCodiceCalendario  ERRORE TROPPI RECORD = "	+ myQuery.list().size() 
					+" per codiceSistemaInformativo: "+codiceSistemaInformativo
					+" codicePiattaforma: "+codicePiattaforma
					+" criterio: "+criterio
					+" param: "+param);
		}
		try {
			myIter = myQuery.list().listIterator();			
			while(myIter.hasNext()) {
				codiceCalendario = (String) myIter.next();
			}
		} catch (Exception e) {
			codiceCalendario = "";
			logger.error(owner+ " | CalendarioManager.getCodiceCalendario  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}
		
		logger.debug(owner + " | CalendarioManager.getCodiceCalendario - fine");		
		
		return codiceCalendario;
	}
	
	private Query getQueryForCodiceCalendario(
			EntityManager myEntityManager, 
			String owner,  
			String codiceSistemaInformativo, 
			String codicePiattaforma, 
//			String codiceCalendario,
			String criterio,
			String param
	){
		logger.debug(owner + " | CalendarioManager.getCodiceCalendario - inizio");

		boolean isDefault  = TipoCalendarioCriterio.isCriterioDefault(criterio);
		String tipoCriterio = (isDefault ? "DEFAULT" : criterio.trim());
		String criterioDefault = (isDefault ? criterio.trim() : "");
		String piattaforma = "'"+codicePiattaforma.trim()+"'";
		
		String sqlSel = "";
		
		if (TipoCalendarioCriterio.CRITERIO_ACRONIMO.equals(tipoCriterio)){
			sqlSel = "SELECT DISTINCT applicazioni.cod_calendario "
			+ " FROM TipoCalendarioPiattaforma piattaforma, " 
			+ " TipoCalendarioCriterio criterio, " 
			+ " TipoCalendario tipoCalendario, "
			+ " Applicazioni applicazioni "
			+ " WHERE tipoCalendario.cod_calendario = piattaforma.cod_calendario "
			+ " AND piattaforma.sistema_informativo = criterio.sistema_informativo "
			+ " AND piattaforma.piattaforma = criterio.piattaforma  "
			+ " AND tipoCalendario.cod_calendario = applicazioni.cod_calendario "
			+ " AND piattaforma.piattaforma = applicazioni.piattaforma "
			+ " AND piattaforma.sistema_informativo = applicazioni.sistema_informativo ";
			if (param != null && !"".equals(param.trim())){
				StringTokenizer acronimo = new StringTokenizer(param.trim(), ",");
				sqlSel = sqlSel + "  AND applicazioni.acronimo_appl IN ('"+acronimo.nextToken(" ")+"')";
			}
			if (codiceSistemaInformativo!=null && !"".equals(codiceSistemaInformativo.trim())){
				sqlSel= sqlSel + " AND piattaforma.sistema_informativo = '"+codiceSistemaInformativo.trim()+"'";
			}
			if (piattaforma!=null && !"".equals(piattaforma.trim())){
				sqlSel= sqlSel + " AND piattaforma.piattaforma = " +piattaforma;
			}
			if (tipoCriterio!=null && !"".equals(tipoCriterio.trim())){
				sqlSel= sqlSel + " AND criterio.criterio = '"+ tipoCriterio +"'";
				if (TipoCalendarioCriterio.CRITERIO_DEFAULT.equals(tipoCriterio)){
					sqlSel= sqlSel + " AND criterio.criterio_default = '"+ criterioDefault +"'";
				}
			}
		} else if (TipoCalendarioCriterio.CRITERIO_FAMIGLIA.equals(tipoCriterio)){
			sqlSel = "SELECT DISTINCT famiglia.cod_calendario "
				+ " FROM TipoCalendarioPiattaforma piattaforma, " 
				+ " TipoCalendarioCriterio criterio, " 
				+ " TipoCalendario tipoCalendario, "
				+ " Famiglia famiglia "
				+ " WHERE tipoCalendario.cod_calendario = piattaforma.cod_calendario "
				+ " AND piattaforma.sistema_informativo = criterio.sistema_informativo "
				+ " AND piattaforma.piattaforma = criterio.piattaforma  "
				+ " AND famiglia.cod_famiglia = '"+param.trim()+"'"
				+ " AND famiglia.cod_calendario = tipoCalendario.cod_calendario";
			if (codiceSistemaInformativo!=null && !"".equals(codiceSistemaInformativo.trim())){
				sqlSel= sqlSel + " AND piattaforma.sistema_informativo = '"+codiceSistemaInformativo.trim()+"'";
			}
			if (piattaforma!=null && !"".equals(piattaforma.trim())){
				sqlSel= sqlSel + " AND piattaforma.piattaforma = " +piattaforma;
			}
			if (tipoCriterio!=null && !"".equals(tipoCriterio.trim())){
				sqlSel= sqlSel + " AND criterio.criterio = '"+ tipoCriterio +"'";
				if (TipoCalendarioCriterio.CRITERIO_DEFAULT.equals(tipoCriterio)){
					sqlSel= sqlSel + " AND criterio.criterio_default = '"+ param +"'";
				}
			}
		} else if (TipoCalendarioCriterio.CRITERIO_TIPO_UDC.equals(tipoCriterio)){
			sqlSel = "SELECT DISTINCT tipoUdc.cod_calendario "
				+ " FROM TipoCalendarioPiattaforma piattaforma, " 
				+ " TipoCalendarioCriterio criterio, " 
				+ " TipoCalendario tipoCalendario, "
				+ " TipoUdc tipoUdc "
				+ " WHERE tipoCalendario.cod_calendario = piattaforma.cod_calendario "
				+ " AND piattaforma.sistema_informativo = criterio.sistema_informativo "
				+  " AND piattaforma.sistema_informativo = tipoUdc.sistema_informativo "
				+ " AND tipoUdc.cod_calendario = tipoCalendario.cod_calendario"
				+ " AND tipoUdc.cod_tipo_udc = '"+param.trim()+"'";
			if (codiceSistemaInformativo!=null && !"".equals(codiceSistemaInformativo.trim())){
				sqlSel= sqlSel + " AND piattaforma.sistema_informativo = '"+codiceSistemaInformativo.trim()+"'";
			}
			if (tipoCriterio!=null && !"".equals(tipoCriterio.trim())){
				sqlSel= sqlSel + " AND criterio.criterio = '"+ tipoCriterio +"'";
				if (TipoCalendarioCriterio.CRITERIO_DEFAULT.equals(tipoCriterio)){
					sqlSel= sqlSel + " AND criterio.criterio_default = '"+ param +"'";
				}
			}
		} else if (TipoCalendarioCriterio.CRITERIO_DEFAULT.equals(tipoCriterio)){
			sqlSel = "SELECT DISTINCT piattaforma.cod_calendario "
				+" FROM TipoCalendarioPiattaforma piattaforma, " 
				+ " TipoCalendarioCriterio criterio, " 
				+ " TipoCalendario tipoCalendario "
				+ " WHERE tipoCalendario.cod_calendario = piattaforma.cod_calendario "
				+ " AND piattaforma.sistema_informativo = criterio.sistema_informativo ";
			if (codiceSistemaInformativo!=null && !"".equals(codiceSistemaInformativo.trim())){
				sqlSel= sqlSel + " AND piattaforma.sistema_informativo = '"+codiceSistemaInformativo.trim()+"'";
			}
			if (piattaforma!=null && !"".equals(piattaforma.trim())){
				sqlSel= sqlSel + " AND piattaforma.piattaforma = " +piattaforma;
			}
			if (tipoCriterio!=null && !"".equals(tipoCriterio.trim())){
				sqlSel= sqlSel + " AND criterio.criterio = '"+ tipoCriterio +"'";
				if (TipoCalendarioCriterio.CRITERIO_DEFAULT.equals(tipoCriterio)){
					sqlSel= sqlSel + " AND criterio.criterio_default = '"+ param +"'";
				}
			}
		}
		
		Query myQuery = myEntityManager.createQuery(sqlSel);

		logger.debug(owner + " | CalendarioManager.getQueryForCodiceCalendario STRSQL: "+ myQuery.getQueryString());
		return myQuery;
		
	}
	
	public String getDescrCodiceCalendario(
			String owner, 
			String codiceSistemaInformativo, 
			String codicePiattaforma, 
			String codCalendario){
		
		logger.debug(owner + " | CalendarioManager.getDescrCodiceCalendario - inizio");
		String descrizione = "";
		
		EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(TipoCalendarioPiattaforma.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		
		String sqlSel="FROM TipoCalendarioPiattaforma ";
		sqlSel = sqlSel + " WHERE piattaforma.sistema_informativo = ?";
		sqlSel = sqlSel + " AND piattaforma.piattaforma = ?";
		sqlSel = sqlSel + " AND piattaforma.cod_calendario = ?";
		
		Query myQuery = myEntityManager.createQuery(sqlSel);

		myQuery.setString(0, codiceSistemaInformativo);
		myQuery.setString(1, codicePiattaforma);
		myQuery.setString(2, codCalendario);
		logger.debug(owner + " | CalendarioManager.getDescrCodiceCalendario STRSQL: "+ myQuery.getQueryString());
  		Iterator myIter = null;
  		
		try {
			myIter = myQuery.list().listIterator();			
			while(myIter.hasNext()) {
				TipoCalendarioPiattaforma tipoCalendarioPiattaforma = (TipoCalendarioPiattaforma)myIter.next();
				descrizione = tipoCalendarioPiattaforma.getDescrizione();
			}
		} catch (Exception e) {
			logger.error(owner+ " | CalendarioManager.getDescrCodiceCalendario  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}		
		
		logger.debug(owner + " | CalendarioManager.getDescrCodiceCalendario - fine");		
		return descrizione;
	}
	
	private Vector loadTipoCalendarioCriterio(
			String owner
		){
    	logger.debug(owner + " | CalendarioManager.loadTipoCalendarioCriterio - inizio");
    	Vector vec=new Vector();
    	EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(TipoCalendarioCriterio.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		String sqlSel="FROM TipoCalendarioCriterio ";
		Query myQuery = myEntityManager.createQuery(sqlSel);
		logger.debug(owner + " | CalendarioManager.loadTipoCalendarioCriterio STRSQL: "+ myQuery.getQueryString());
  		Iterator myIter = null;
  		
		try {
			myIter = myQuery.list().listIterator();			
			while(myIter.hasNext()) {
				TipoCalendarioCriterio tipoCalendarioCriterio = (TipoCalendarioCriterio)myIter.next();
				vec.add(tipoCalendarioCriterio);
			}
		} catch (Exception e) {
			logger.error(owner+ " | CalendarioManager.loadTipoCalendarioCriterio  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}		
		logger.debug(owner + " | CalendarioManager.loadTipoCalendarioCriterio - fine");		
		return vec;
    }
	
	public String getCriterio(
			String owner, 
			String codiceSistemaInformativo, 
			String codicePiattaforma
	){
		logger.debug(owner + " | CalendarioManager.getCriterio - inizio");

		Vector vettTipoCalendario = loadTipoCalendarioCriterio(owner);
		String criterio = "";
		for (int i=0; i<vettTipoCalendario.size();i++){
			TipoCalendarioCriterio tipoCalendarioCriterioTemp = (TipoCalendarioCriterio) vettTipoCalendario.get(i);
			if (tipoCalendarioCriterioTemp.getSistema_informativo().equals(codiceSistemaInformativo)
					&& tipoCalendarioCriterioTemp.getPiattaforma().equals(codicePiattaforma) ){
				TipoCalendarioCriterio tipoCalendarioCriterio = tipoCalendarioCriterioTemp;
				if (TipoCalendarioCriterio.CRITERIO_DEFAULT.equals(tipoCalendarioCriterio.getCriterio())){
					criterio = tipoCalendarioCriterio.getCriterio_default();
				} else {
					criterio = tipoCalendarioCriterio.getCriterio();
				}
			}
		}
		
		logger.debug(owner + " | CalendarioManager.getCriterio - fine");		
		return criterio;
	}
	
	public String getGiornoStandard(
			String owner,
			String codCalendario
		){
    	logger.debug(owner + " | CalendarioManager.getGiornoStandard - inizio");
    	String giornoStandard = "";
    	EntityManagerFactory myEntityManagerFactory = PersistenceProvider.createEntityManagerFactory(TipoCalendario.class.getPackage().getName(), null);
		EntityManager myEntityManager = myEntityManagerFactory.createEntityManager();
		String sqlSel= " SELECT CASE " 
			+" WHEN calendario.frequenza = 'G' " 
				+" and calendario.distrib_lun = 'S' " 
				+" and calendario.distrib_mar = 'S' " 
				+" and calendario.distrib_mer = 'S' " 
				+" and calendario.distrib_gio = 'S' " 
				+" and calendario.distrib_ven = 'S' " 
			+" THEN 'ALL' "
			+" WHEN calendario.frequenza = 'G' " 
				+" and calendario.distrib_lun = 'N' " 
				+" and calendario.distrib_mar = 'N' " 
				+" and calendario.distrib_mer = 'N' " 
				+" and calendario.distrib_gio = 'N' " 
				+" and calendario.distrib_ven = 'N' " 
				+" THEN 'NONE' "
			+" WHEN calendario.distrib_lun = 'S' THEN '2' " 
			+" WHEN calendario.distrib_mar = 'S' THEN '3' "
			+" WHEN calendario.distrib_mer = 'S' THEN '4' "
			+" WHEN calendario.distrib_gio = 'S' THEN '5' "
			+" WHEN calendario.distrib_ven = 'S' THEN '6' "
			+" ELSE '' "
			+" END as giornoSettimana "
			+" FROM  TipoCalendario calendario "
			+" WHERE calendario.cod_calendario=? ";
		
		Query myQuery = myEntityManager.createQuery(sqlSel);
		myQuery.setString(0, codCalendario);
		
		logger.debug(owner + " | CalendarioManager.getGiornoStandard STRSQL: "+ myQuery.getQueryString());
  		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();			
			while(myIter.hasNext()) {
				String temp = (String)myIter.next();
				if (temp.trim().length()==1){
					Vector vec = DateTime.getGiorniDellaSettimana();
					giornoStandard = (String) vec.get(Integer.parseInt(temp));
				} else if ("ALL".equals(temp) || "NONE".equals(temp)){
					giornoStandard = temp;
				}
			}
		} catch (Exception e) {
			giornoStandard="";
			logger.error(owner+ " | CalendarioManager.getGiornoStandard  Exception = "	+ e.getMessage());
		}finally{
			myEntityManager.close();			
		}		
		logger.debug(owner + " | CalendarioManager.getGiornoStandard - fine");		
		return giornoStandard;
    }
			
}
