package db.managers;

import java.sql.ResultSet;
import java.util.LinkedList;

import Utility.Utility;
import autudc.logger.Logger;
import autudc.logger.LoggerFactory;
import db.connection.OracleConnection;
import db.entities.MotivoEmergenzaHost;

public class MotivoEmergenzaHostManager {
	
	private Logger logger = null;
	
    public MotivoEmergenzaHostManager() {
    	super();
		try {
            if (logger == null) {
                String pkgName = this.getClass().getPackage().getName();
                logger = LoggerFactory.getLogger(pkgName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.debug("Costruttore MotivoEmergenzaHostManager()");
	}
    
    public LinkedList getMotiviEmergenzaHost(
    		OracleConnection oracleConnection,
    		String owner,
    		String sistemaInformativo
    	){
    	
    	logger.debug(owner + " | MotivoEmergenzaHostManager.getMotiviEmergenzaHost - inizio");

		String sqlQuery = "SELECT sistema_informativo, cod_emergenza , descrizione ";
		sqlQuery = sqlQuery + " FROM CHCI_OWN.T_CHCI_MOTIVO_EMERGENZA_HOST ";
		sqlQuery = sqlQuery + " WHERE sistema_informativo = '"+sistemaInformativo+"' ";
		sqlQuery = sqlQuery + " ORDER BY cod_emergenza ";
		
		LinkedList motiviEmergenzaHost = new LinkedList();
		try {
			if (!"".equals(sqlQuery)){
				ResultSet rs = oracleConnection.GetQuery(sqlQuery);
				logger.debug(owner + " | MotivoEmergenzaHostManager.getMotiviEmergenzaHost STRSQL: "+sqlQuery);
						
				if (rs != null) {
					while (rs.next()) {	
						MotivoEmergenzaHost motivoEmergenzaHost = new MotivoEmergenzaHost();
						motivoEmergenzaHost.setSistemaInformativo(Utility.checkNull(rs.getString("SISTEMA_INFORMATIVO")));
						motivoEmergenzaHost.setCodMotivoEmergenza(Utility.checkNull(rs.getString("COD_EMERGENZA")));
						motivoEmergenzaHost.setDescrMotivoEmergenza(Utility.checkNull(rs.getString("DESCRIZIONE")));
						
						motiviEmergenzaHost.add(motivoEmergenzaHost);
					}
				}
			} 
		} catch (Exception e) {
			motiviEmergenzaHost = new LinkedList();
			logger.error(owner+ " | MotivoEmergenzaHostManager.getMotiviEmergenzaHost  Exception = "	+ e.getMessage());
		}		
		logger.debug(owner + " | MotivoEmergenzaHostManager.getMotiviEmergenzaHost - fine");		
		return motiviEmergenzaHost;
    }
    
    public MotivoEmergenzaHost getMotivoEmergenzaHost(
    		OracleConnection oracleConnection,
    		String owner,
    		String sistemaInformativo,
    		String codiceEmergenza
    	){
    	
    	logger.debug(owner + " | MotivoEmergenzaHostManager.getMotivoEmergenzaHost - inizio");

		String sqlQuery = "SELECT sistema_informativo, cod_emergenza , descrizione ";
		sqlQuery = sqlQuery + " FROM CHCI_OWN.T_CHCI_MOTIVO_EMERGENZA_HOST ";
		sqlQuery = sqlQuery + " WHERE sistema_informativo = '"+sistemaInformativo+"' ";
		sqlQuery = sqlQuery + " AND cod_emergenza= '"+codiceEmergenza+"' ";
		
		MotivoEmergenzaHost motivoEmergenzaHost = new MotivoEmergenzaHost();
		try {
			if (!"".equals(sqlQuery)){
				ResultSet rs = oracleConnection.GetQuery(sqlQuery);
				logger.debug(owner + " | MotivoEmergenzaHostManager.getMotivoEmergenzaHost STRSQL: "+sqlQuery);
						
				if (rs != null) {
					rs.next();
					motivoEmergenzaHost.setSistemaInformativo(Utility.checkNull(rs.getString("SISTEMA_INFORMATIVO")));
					motivoEmergenzaHost.setCodMotivoEmergenza(Utility.checkNull(rs.getString("COD_EMERGENZA")));
					motivoEmergenzaHost.setDescrMotivoEmergenza(Utility.checkNull(rs.getString("DESCRIZIONE")));

				}
			} 
		} catch (Exception e) {
			motivoEmergenzaHost = new MotivoEmergenzaHost();
			logger.error(owner+ " | MotivoEmergenzaHostManager.getMotivoEmergenzaHost  Exception = "	+ e.getMessage());
		}		
		logger.debug(owner + " | MotivoEmergenzaHostManager.getMotivoEmergenzaHost - fine");		
		return motivoEmergenzaHost;
    }
			
}
