/*
 * Copyright Banksiel S.p.A.
 */
package autudc.logger;

import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.Context;
import java.io.File;

/**
 * La classe gestisce il file di Properties dell'applicazione
 * <code>applicazione.properties</code>. E' un'implementazione del Pattern
 * Singleton, per cui il file e' letto solo al momento della creazione dell'istanza
 * della classe.
 * <BR> Mentre il nome del file di properties e' fisso
 * <b>applicazione.properties</b>), il path e' determinato come segue:
 * <BR>- in prima istanza si cerca la variabile di <i>JVM</i> con nome
 * <b>applicazione_properties_path</b>;
 * <BR>- se la variabile non viene trovata, si cerca il path tramite una variabile
 * di ambiente del <code>Context</code> sempre con nome
 * <b>applicazione_properties_path</b>;
 * <BR>- se anche la variabile del <code>Context</code> non esiste, il path di
 * default è <b>/</b> (root).
 * @author Banksiel S.p.A.
 */
public class LoggerPropertiesManager extends AbstractPropertiesManager {

    /**
     * Costruttore della classe. Acquisisce i parametri dal file di Properties.
     * @exception java.io.IOException*/
    protected LoggerPropertiesManager() throws IOException {
        String filename = getPropertiesFilename();
        readFileProperties(filename);
    }

    /**
     * Costruttore della classe. Acquisisce i parametri dal file di Properties
     * identificato da propertiesPath
     * @param propertiesPath File
     * @throws java.io.IOException
     */
    protected LoggerPropertiesManager(File propertiesPath) throws IOException {
        super();
        readFileProperties(propertiesPath);
    }

    /**
     * Il metodo ritorna la stringa contenente il <i>path</i> ed il nome del file
     * di <code>properties</code>. Il nome del file di properties e' determinato
     * come segue:
     * <BR>-  in prima istanza si cerca il path nella variabile di <i>JVM</i> con
     *        nome <b>applicazione_properties_path</b>;
     * <BR>-  se la variabile non viene trovata, si cerca il path tramite una
     *        variabile di ambiente del <code>Context</code> sempre con nome
     *        <b>applicazione_properties_path</b>;
     * <BR>-  se anche la variabile del <code>Context</code> non esiste, il path
     *        di default e' <b>/</b> (root del classloader).
     * <BR>-  poi si cerca il filename nella variabile di <i>JVM</i> con nome <b>
     *        applicazione_properties_filename</b>;
     * <BR>-  se la variabile non viene trovata, si cerca il filename tramite una
     *        variabile di ambiente del <code>Context</code> sempre con nome
     *        <b>applicazione_properties_filename</b>;
     * <BR>-  se anche la variabile del <code>Context</code> non esiste, il
     *        filename di default e' <b>logger.properties</b>.
     *
     * @return String filename
     */
    protected String getPropertiesFilename() {
        String filepath = null;
        String filename = null;
        InitialContext ic = null;
        Context ctx = null;


        // ricerca del filepath...
        try {
            // ricerca del filepath come parametro della JVM (-d ...)

            filepath = System.getProperty("configfile.path");
            System.out.println(" ho letto la system property configfile.path: " + filepath);
            if (filepath == null) {
                // ricerca nel context JNDI
                ic = new InitialContext();
                ctx = (Context) ic.lookup("java:comp/env");
                filepath = (String) ctx.lookup(FILEPATH_PROPERTYNAME_JNDI);

                //System.out.println(" File Property utilizzato: " + FILEPATH_PROPERTYNAME_JNDI);
                if (filepath == null) {
                    filepath = DEFAULT_PATH;
                }
            } else {
                filename = System.getProperty(FILEPATH_PROPERTYNAME);
                if (filename == null) {

                    filename = DEFAULT_FILENAME;
                }
                filepath = filepath + filename;
            }

        } catch (Exception e) {
            // in caso di errore di assegna il default
            filepath = DEFAULT_PATH;
        }
        System.out.println("autudc.logger - File log properties: " + filepath);
        return filepath;
    }

    public static LoggerPropertiesManager getInstance() throws IOException {
        if (instance == null) {
            synchronized (LoggerPropertiesManager.class) {
                if (instance == null) {
                    instance = new LoggerPropertiesManager();
                }
            }
        }
        return instance;
    }

    /**
     * Il metodo restituisce il valore della property identificata dal parametro <code>key</code>. Se la property non esiste, il metodo ritorna <code>null</code>.
     * @param key String
     * @return java.lang.String
     */
    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    /**
     * Il metodo restituisce il valore della property identificata dal parametro <code>key</code>. Se la property non esiste, il metodo ritorna il parametro <code>defaultValue</code>.
     * @param key String
     * @param defaultValue String
     * @return java.lang.String
     */
    public String getProperty(String key, String defaultValue) {
        return prop.getProperty(key, defaultValue);
    }
    /**
     * @link
     * @shapeType PatternLink
     * @pattern Singleton
     * @supplierRole Singleton factory
     */
    /*# private LoggerPropertiesManager _LoggerPropertiesManager; */
    private static LoggerPropertiesManager instance = null;
    // path di default del file di properties
    private static final String DEFAULT_FILENAME = "logger.properties";
    private static final String DEFAULT_PATH = "./logger.properties";
    // nome della property contenente il path del file di properties
    private static final String FILEPATH_PROPERTYNAME = "FILEPATH_PROPERTYNAME";
    private static final String FILEPATH_PROPERTYNAME_JNDI = "mqar/logger_properties_path";
}
