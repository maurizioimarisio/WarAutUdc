package autudc.logger;

import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;




public class LoggerFactory {
	
	   private static Map                     configurationsNode     = new Hashtable();
	   private static LoggerPropertiesManager propertiesManager;
	   private static final String            MY_DEFAULT_LEVEL           = "ERROR";
	   private static final String            ERROR_APPENDER_NAME        =
	       "12ab34cd56ef78gh90il12mn34op56qr78st90uvz";
	   private static final String            MY_DEFAULT_ERROR_DEVICE    =
	       EnvKey.LOG_SYSTEM_NT_CONSOLE_DEVICE;
	   //private static final String            MY_DEFAULT_DEVICE          = "SYSOUT";
	   private static final String            MY_DEFAULT_DEVICE          = "FILE";
	   private static final boolean           MY_DEFAULT_ROOT_ADDITIVITY = false;
	   private static final String            MY_PATH_FILE               = "/LoggerError.log";
	   private static final String            MY_DEFAULT_PATTERN_LAYOUT  =
	       " %d{dd MMM yyyy HH:mm:ss,SSS} [%t] %-5p %c %m %n";
	   private static final String  MY_DEFAULT_SYSTEM          = "WIN";
	   private static org.apache.log4j.Logger errorLogger      = null;

  /** @link dependency
   * @clientRole factory*/
    /*# Logger lnkLogger; */
  public static Logger getLogger(String loggerName) throws Exception {
    Logger logger = null;
    LoggerFactory.init();

    // Test sulla cache
    if (!existInstance(loggerName)){
      logger = LoggerFactory.makeLog4JWrapper(loggerName);
      loggerCache.put(loggerName,logger);
    }else{
      logger = (Logger) loggerCache.get(loggerName);
    }

    return logger;
  }


  public static void init() throws Exception {
    if (LoggerFactory.propertiesManager == null){
      // Creazione del logger interno per la gestione degli errori.
      LoggerFactory.errorLogger = makeErrorLogger();
      LoggerFactory.propertiesManager = LoggerPropertiesManager.getInstance();

      // Inizializzazione del contenitore di configurazioni.
      LoggerFactory.configurationsNode = new Hashtable();

      // Estrazione e caching della configurazione del nodo di root.
      LogNodeConfiguration rootConfiguration = getRootConfiguration();
      LoggerFactory.addRootConfiguration(rootConfiguration);
      LoggerFactory.configurationsNode.put(EnvKey.LOG_ROOT_NAME,rootConfiguration);

       // Estrazione e caching dei vari altri tipi di configurazione.
      LoggerFactory.configurationsNode.putAll(
          LoggerFactory.makeAliasConfigurations(LoggerFactory.extractConfigNames()));

    }
  }

  /**
   *
   * @param rootConf
   * @return
   */
  private static org.apache.log4j.Logger
  addRootConfiguration(LogNodeConfiguration rootConf){
    return addNodeConfiguration(rootConf, LogManager.getRootLogger());
  }


  /**
   *
   * @param nodeConf
   * @param logger
   * @return
   */
  private static org.apache.log4j.Logger
  addNodeConfiguration(LogNodeConfiguration     nodeConf,
                       org.apache.log4j.Logger  logger){

    String          pattern             = nodeConf.getPatternLayout();
    Layout          outputLayout        = LayoutManager.getLayout(pattern);
    Appender        rootAppender        = null;
    // Settings dell'appender appropriato.
    if (nodeConf.getDevice().equals(EnvKey.LOG_FILE_DEVICE )){
      rootAppender  = AppenderManager.createFileAppender(nodeConf.getPathFile(),
                                                         outputLayout);
    }else if(nodeConf.getDevice().equals(EnvKey.LOG_SYSTEM_OUT_DEVICE)){
      rootAppender  = AppenderManager.createSystemOutAppender(outputLayout);
    }else if(nodeConf.getDevice().equals(EnvKey.LOG_SYSTEM_ERR_DEVICE)){
      rootAppender  = AppenderManager.createSystemErrAppender(outputLayout);
    }

    logger.addAppender(rootAppender);
    logger.setLevel(Level.toLevel(nodeConf.getLevel()));
    logger.setAdditivity(LoggerFactory.MY_DEFAULT_ROOT_ADDITIVITY);

    return logger;
  }

  /**
   *
   * @param loggerName
   * @return
   */
  private  static Logger makeLog4JWrapper(String loggerName){
      LogNodeConfiguration    nodeConf      = null;
      String                  conf          = null;
      org.apache.log4j.Logger logger4j      = null;
      String                  pattern       = null;
      Layout                  outputLayout  = null;
      Appender                nodeAppender  = null;

      logger4j      = LogManager.getLogger(loggerName);
      conf          = LoggerFactory.getConfigurationByLoggerName(loggerName);
      nodeConf      = (LogNodeConfiguration)
                        LoggerFactory.configurationsNode.get(
                          conf != null ? conf : EnvKey.LOG_ROOT_NAME);
      pattern       = nodeConf.getPatternLayout();
      outputLayout  = LayoutManager.getLayout(pattern);

      // Settings dell'appender appropriato.
      if (nodeConf.getDevice().equals(EnvKey.LOG_FILE_DEVICE)) {
          nodeAppender = AppenderManager.createFileAppender(nodeConf.getPathFile(),
                  outputLayout);
      } else if (nodeConf.getDevice().equals(EnvKey.LOG_SYSTEM_OUT_DEVICE)) {
          nodeAppender = AppenderManager.createSystemOutAppender(outputLayout);
      } else if (nodeConf.getDevice().equals(EnvKey.LOG_SYSTEM_ERR_DEVICE)) {
          nodeAppender = AppenderManager.createSystemErrAppender(outputLayout);
      } else if (nodeConf.getDevice().equals(EnvKey.LOG_SYSTEM_NT_CONSOLE_DEVICE)) {
          nodeAppender = AppenderManager.createNTEventLogAppender();
      }
      logger4j.addAppender(nodeAppender);
      logger4j.setLevel(Level.toLevel(nodeConf.getLevel()));
      logger4j.setAdditivity(LoggerFactory.MY_DEFAULT_ROOT_ADDITIVITY);
      // Creazione del wrapper.
      return new LoggerImpl(loggerName, logger4j);
  }



    /**
     *
     * @param loggerName
     * @return
     */
    static boolean existInstance(String loggerName){
      return LoggerFactory.loggerCache.containsKey(loggerName);
    }



    /**
     * Collezione di cache. Contiene come chiavi i nomi dei logger giè richiesti.
     * @associates Logger
     */
    private static Map loggerCache     = new Hashtable();






    private static String[] extractConfigNames(){
      String          aliasNames    = getPropertyConfigurationNames();
      StringTokenizer sTAliasConfig = new StringTokenizer(aliasNames,
                                        EnvKey.LOG_DEFAULT_DELIMITER);
      String[] names = new String[sTAliasConfig.countTokens()];
      int currentNamePosition = 0;
      while (sTAliasConfig.hasMoreTokens()) {
        names[currentNamePosition++] = sTAliasConfig.nextToken();
      }
      return names;
    }


   /**
    * Effettua l'estrazione dal file di properties delle configurazioni
    * inserendole nella cache interna.
    *
    * @param aliasNames La stringa del file di properties comprendente tutti i
    *                   nomi delle configurazioni.
    */
   private static Hashtable makeAliasConfigurations(String[] configurationNames){
     Hashtable configuration      = new Hashtable();
     String  configurationName    = null;
     LogNodeConfiguration  config = null;
     boolean existsRoot           = false;
     for (int i = 0; i < configurationNames.length; i++) {
       configurationName = configurationNames[i];
       // Verifica della presenza del nodo di root.
       existsRoot |= (configurationName.equals(EnvKey.LOG_ROOT_NAME))? true:false;
       // Creazione della configurazione in esame.
       config = makeConfigByName (configurationName);
       // Agiunta della configurazione estratta alla cache delle configurazioni.
       configuration.put(configurationName,
                              config);
     }

     return configuration;
   }

   private static LogNodeConfiguration getRootConfiguration(){
      return  LoggerFactory.makeConfigByName (EnvKey.LOG_ROOT_NAME);
   }

   /**
    *
    * @param configurationName
    * @return
    */
   private static LogNodeConfiguration makeConfigByName(String configurationName){
     LogNodeConfiguration config = new LogNodeConfiguration();


     config.setDevice(LoggerFactory.getPropertyDefaultDevice(configurationName));
     String path = System.getProperty("DD_CHCI0_PATHFILELOG");
     if (path == null){
        config.setPathFile(LoggerFactory.getPropertyPathFile(configurationName));
     }else{
        config.setPathFile(path+LoggerFactory.getPropertyPathFile(configurationName)); 
     }
         
     config.setPatternLayout(LoggerFactory.getPropertyPatternLayout(configurationName));
     config.setLevel(LoggerFactory.getLogLevel(configurationName));
     config.setOperatingSystem(LoggerFactory.getPropertyOperatingSystem(configurationName));

     return config;
   }


  private static String getConfigurationByLoggerName(String loggerName){
    return LoggerFactory.propertiesManager.
                 getStringProperty(loggerName);
   }

   /**
    * Estrazione e assegnazione del livello di Logging se presente, altresè il
    * default.
    *
    * @param configurationName    Il nome della configurazione alla quale
    *                             appartiene il parametro richiesto.
    * @return                     Il livello di Logging
    */
   private static String getPropertyConfigurationNames(){
     return LoggerFactory.propertiesManager.
                  getStringProperty(EnvKey.LOG_ALIAS_NAMES,
                                    MY_DEFAULT_LEVEL);
   }


   /**
    * Estrazione e assegnazione del tipo di log se presente, altresè il default.
    *
    * @param configurationName    Il nome della configurazione alla quale
    *                             appartiene il parametro richiesto.
    * @return                     Il tipo di log.
    */
   private static String getPropertyDefaultDevice(String configurationName){
     return  LoggerFactory.propertiesManager.
                    getStringProperty(configurationName + EnvKey.LOG_DEFAULT_DEVICE,
                                      MY_DEFAULT_DEVICE);
   }


   /**
    * Estrazione e assegnazione dell'eventuale file se scelto il log su file,
    * altresè il default anche se non obbligatorio.
    *
    * @param configurationName    Il nome della configurazione alla quale
    *                             appartiene il parametro richiesto.
    * @return                     Il path del file di output.
    */
   private static String getPropertyPathFile(String configurationName){
   	
            return LoggerFactory.propertiesManager.
                    getStringProperty(configurationName + EnvKey.LOG_PATH_FILE,
                                      MY_PATH_FILE);
   }
       


   /**
    * Estrazione e assegnazione del livello di logging del nodo,
    * altrimenti il default.
    *
    * @param configurationName    Il nome della configurazione alla quale
    *                             appartiene il parametro richiesto.
    * @return                     Il livello di logging.
    */
   private static String getLogLevel(String configurationName){
     return LoggerFactory.propertiesManager.
                    getStringProperty(configurationName + EnvKey.LOG_LEVEL,
                                      MY_DEFAULT_LEVEL);
   }



   /**
    * Estrazione e assegnazione del tipo layout di output, altresè il default.
    *
    * @param configurationName    Il nome della configurazione alla quale
    *                             appartiene il parametro richiesto.
    *
    * @return                     Il tipo layout di output.
    */
   private static String getPropertyPatternLayout(String configurationName){
     return LoggerFactory.propertiesManager.
                    getStringProperty(configurationName + EnvKey.LOG_DEFAULT_PATTERN_LAYOUT,
                                      MY_DEFAULT_PATTERN_LAYOUT);
   }

   /**
    * Estrazione e assegnazione del sistema operativo, altresè il default.
    *
    * @param configurationName    Il nome della configurazione alla quale
    *                             appartiene il parametro richiesto.
    * @return                     Il sistema operativo
    */
   private static String getPropertyOperatingSystem(String configurationName){
     return LoggerFactory.propertiesManager.
                getStringProperty(configurationName + EnvKey.LOG_OPERATING_SYSTEM,
                                  MY_DEFAULT_SYSTEM);
   }



   private static org.apache.log4j.Logger  makeErrorLogger(){
     org.apache.log4j.Logger logger4j    =
         LogManager.getLogger(LoggerFactory.ERROR_APPENDER_NAME);

     String                pattern       = LoggerFactory.MY_DEFAULT_PATTERN_LAYOUT;
     Layout                outputLayout  = LayoutManager.getLayout(pattern);
     Appender              nodeAppender  = null;
     // Settings dell'appender appropriato.
     if (LoggerFactory.MY_DEFAULT_ERROR_DEVICE.equals(EnvKey.LOG_FILE_DEVICE )){
       try {
         nodeAppender  = AppenderManager.createFileAppender(LoggerFactory.MY_PATH_FILE,
             outputLayout);
       }catch (Throwable ex) {
         nodeAppender  = AppenderManager.createSystemErrAppender(outputLayout);
       }
     }else if(LoggerFactory.MY_DEFAULT_ERROR_DEVICE.equals(EnvKey.LOG_SYSTEM_OUT_DEVICE)){
       nodeAppender  = AppenderManager.createSystemOutAppender(outputLayout);
     }else if(LoggerFactory.MY_DEFAULT_ERROR_DEVICE.equals(EnvKey.LOG_SYSTEM_ERR_DEVICE)){
       nodeAppender  = AppenderManager.createSystemErrAppender(outputLayout);
     }else if(LoggerFactory.MY_DEFAULT_ERROR_DEVICE.equals(EnvKey.LOG_SYSTEM_NT_CONSOLE_DEVICE)){
       try {
         nodeAppender  = AppenderManager.createNTEventLogAppender();
       }catch (Throwable ex) {
         nodeAppender  = AppenderManager.createSystemErrAppender(outputLayout);
       }
     }

     logger4j.addAppender(nodeAppender);
     logger4j.setLevel(Level.ALL);
     logger4j.setAdditivity(LoggerFactory.MY_DEFAULT_ROOT_ADDITIVITY);

     return logger4j;
   }


}
