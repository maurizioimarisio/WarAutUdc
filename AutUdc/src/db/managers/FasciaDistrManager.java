package db.managers;

import java.util.Iterator;
import java.util.Vector;

import persistence.EntityManagerFactory;
import persistence.PersistenceProvider;
import persistence.Query;
import acronimi.Fascia_Distr;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;

public class FasciaDistrManager {
	
	private Logger logger = null;
	public FasciaDistrManager() {
		super();
		try {
            if (logger == null) {
                String pkgName = this.getClass().getPackage().getName();
                logger = LoggerFactory.getLogger(pkgName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.debug("Costruttore FasciaDistrManager");
	}
	
	public String getDescrizioneFasciaDistr(String owner, String sistemaInformativo, String codiceFascia){
		logger.debug(owner + " | FasciaDistrManager.getFascieDistrXSIECodiceFascia - inizio");
		String retValue = "";
		
		EntityManagerFactory myEntManFact = PersistenceProvider.createEntityManagerFactory(Fascia_Distr.class .getPackage().getName(), null);
		persistence.EntityManager myEntityManager = myEntManFact.createEntityManager();
		String strQuery = "From Fascia_Distr fd " +
				" WHERE fd.sistema_informativo='"+sistemaInformativo+"'" +
				" AND cod_fascia_distr='"+codiceFascia+"'" ;
		
		Query myQuery = myEntityManager.createQuery(strQuery);
		
		logger.debug(owner	+ " | FasciaDistrManager.getFascieDistrXSIECodiceFascia str4Query: " + strQuery);
		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();
			myEntityManager.close();
	 			
			while (myIter.hasNext()) {
				Fascia_Distr row = (Fascia_Distr) myIter.next();	
				retValue = row.getDescr_fascia();
			}
		} catch (Exception e) {
			myEntityManager.close();
			logger.error(owner+ " | FasciaDistrManager.getFascieDistrXSI Exception = "	+ e.getMessage());
		}
		logger.debug(owner + " | FasciaDistrManager.getFascieDistrXSIECodiceFascia - fine");
		return retValue;
	}
	
	public Vector getFascieDistrXSI(String owner, String sistemaInformativo){
		logger.debug(owner + " | FasciaDistrManager.getFascieDistrXSI - inizio");
		Vector vec = new Vector();
		EntityManagerFactory myEntManFact = PersistenceProvider.createEntityManagerFactory(Fascia_Distr.class .getPackage().getName(), null);
		persistence.EntityManager myEntityManager = myEntManFact.createEntityManager();
		String strQuery = "From Fascia_Distr fd " +
				" WHERE fd.sistema_informativo='"+sistemaInformativo+"'" +
				" ORDER BY	cod_fascia_distr " ;
		
		Query myQuery = myEntityManager.createQuery(strQuery);
		
		logger.debug(owner	+ " | FasciaDistrManager.getFascieDistrXSI str4Query: " + strQuery);
		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();
			myEntityManager.close();
	 			
			while (myIter.hasNext()) {
				Fascia_Distr row = (Fascia_Distr) myIter.next();			
				vec.add(row);			
			}
		} catch (Exception e) {
			myEntityManager.close();
			logger.error(owner+ " | FasciaDistrManager.getFascieDistrXSI Exception = "	+ e.getMessage());
		}
		logger.debug(owner + " | FasciaDistrManager.getFascieDistrXSI - fine");
		return vec;
	}
	
	public Vector getAllFascie(String owner){
		logger.debug(owner + " | FasciaDistrManager.getAllFascie - inizio");
		Vector vec = new Vector();
		EntityManagerFactory myEntManFact = PersistenceProvider.createEntityManagerFactory(Fascia_Distr.class .getPackage().getName(), null);
		persistence.EntityManager myEntityManager = myEntManFact.createEntityManager();
		String strQuery = "From Fascia_Distr fd " ;
		
		Query myQuery = myEntityManager.createQuery(strQuery);
		
		logger.debug(owner	+ " | FasciaDistrManager.getAllFascie str4Query: " + strQuery);
		Iterator myIter = null;
		try {
			myIter = myQuery.list().listIterator();
			myEntityManager.close();
	 			
			while (myIter.hasNext()) {
				Fascia_Distr row = (Fascia_Distr) myIter.next();			
				vec.add(row);			
			}
		} catch (Exception e) {
			myEntityManager.close();
			logger.error(owner+ " | FasciaDistrManager.getAllFascie Exception = "	+ e.getMessage());
		}
		logger.debug(owner + " | FasciaDistrManager.getAllFascie - fine");
		return vec;
	}
}
