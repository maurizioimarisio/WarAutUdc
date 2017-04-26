package autudc.logger;

import java.io.Serializable;

/**
 * Clase che rappesenta la configurazione di un nodo dell'albero dei logger di
 * applicazione.(precisazione necessaria un quanto differiscono dai logger di
 * log4j).
 * La seguente classe risulta essere l'aggregazione di tutte le caratteristiche
 * di un nodo, e nasce proprio al fine di poterle memorizzare aggregate.
 * Non risultano essere definiti comportamneti particolari o una gestione fine
 * dei costruttori in quanto il suo uso (come si puè notare dai modificatori) è
 * limitato al corrente package, con lo scopo di utility.
 */
class LogNodeConfiguration implements Serializable {

	private static final long serialVersionUID = 1L;
	
	  /** Implementazione interna del livello  */
	  private String level;
	  /** Implementazione interna del device  */
	  private String device;
	  /** Implementazione interna del path del file di output  */
	  private String pathFile;
	  /** Implementazione interna del pattern di layout  */
	  private String patternLayout;
	  /** Implementazione interna del S.O. */
	  private String operatingSystem;
	
	
/**
   * Costruttore di un'istanza della corrente classe, senza alcuna impostazione
   * dei propri attributi.
   * Risultano essere indispensabili al fine di un corretto utilizzo le varie
   * operazioni di set.
   */
  LogNodeConfiguration() {
    this.level                = null;
    this.device               = null;
    this.patternLayout        = null;
    this.operatingSystem      = null;
    this.operatingSystem      = null;
  }

  /**
   * Ritorna il valore rappresentante il device di output della corrente
   * configurazione.
   *
   * @return        Il device di output.
   */
  String getDevice() {
    return device;
  }

  /**
   * Ritorna il valore rappresentante il pattern di composizione dell'output
   * della corrente configurazione.
   *
   * @return        Il pattern di composizione dell'output.
   */
  String getPatternLayout() {
    return patternLayout;
  }


  /**
   * Ritorna il valore rappresentante il livello di log a partire dal quale
   * verrè effettuato il log di questo nodo.
   *
   * @return        Il livello di log.
   */
  String getLevel() {
    return level;
  }


  /**
   * Ritorna il valore rappresentante il tipo di sistema operativo sul quale la
   * corrente versione risulta intallata. Questa informazione permette le
   * segnalazioni sulla console di sistema.
   *
   * @return        Il tipo di sistema operativo.
   */
  String getOperatingSystem() {
    return operatingSystem;
  }


  /**
   * Ritorna il valore rappresentante il path del file di output.
   * Questa informazione risulta essere priva di valore se il tipo di device
   * appartenente alla corrente configurazione risulta essere diverso da
   * FILE.
   *
   * @return        Il path dell'eventuale file di output.
   */
  String getPathFile() {
    return pathFile;
  }


  /**
   * Effettua l'assegnazione del tipo di device scelto per questa configurazione.
   * Se il tipo di device scelto risulta essere <b>FILE</b>, è
   * indispensabile  al fine di un corretto utilizzo da parte di chi esaminerè
   * questa  configurazione,l'assegnazione del path del file attaverso l'operazione di
   * <code>setPathFile()</code>.
   *
   * @param device Il tipo device.
   */
  void setDevice(String device) {
    this.device = device;
  }

  /**
   * Effettua l'assegnazione del tipo d layout di output scelto per questa configurazione.
   *
   * @param patternLayout Il layout di output.
   */
  void setPatternLayout(String patternLayout) {
    this.patternLayout = patternLayout;
  }

  /**
   * Assegnazione del  valore rappresentante il livello di log a partire dal quale
   * verrè effettuato il log della corente configurazione.
   *
   * @param level Il livello di log.
   */
  void setLevel(String level) {
    this.level = level;
  }

  /**
   * Assegnazione del valore rappresentante il tipo di sistema operativo sul
   * quale la corrente versione risulta intallata. L'assegnagnazione di questa
   * informazione informazione permette la corretta identificazione della
   * console di sistema.
   *
   * @param operatingSystem Il tipo di S.O.
   */
  void setOperatingSystem(String operatingSystem) {
    this.operatingSystem = operatingSystem;
  }

  /**
   * Effettua l'assegnazione del valore rappresentante il path del file di output.
   * Questo setting risulta essere privo di valore se il tipo di device
   * appartenente alla corrente configurazione risulta essere diverso da
   * FILE.
   *
   * @param pathFile Il valore rappresentante il path del file di output.
   */
  void setPathFile(String pathFile) {
    this.pathFile = pathFile;
  }

}
