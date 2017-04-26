package autudc.logger;

/**
 * Interfaccia che riassume il comportamento di un logger.
 * Un logger deve dare la possibilitè al chiamente di effettuare 5 tipi di
 * scrittura, una per ogni livello di log.
 * Di seguito viene riportato l'ordine crescente di gravitè del
 * livello e la sua corrispettiva operazione di scrittura.
 * <ul>
 *    <li> DEBUG .debug(....)
 *    <li> INFO .info(....)
 *    <li> WARNING .warn(....)
 *    <li> ERROR .error(....)
 *    <li> FATAL .fatal(....)
 * </ul>
 */
public interface Logger {

  /**
   * Fornisce la possibilitè di effettuare una scrittura di log con un livello
   * DEBUG.
   *
   * @param dati  Istanza del contenitore dei dati di log.
   */
  void debug(String message);


  /**
   * Fornisce la possibilitè di effettuare una scrittura di log con un livello
   * INFO.
   *
   * @param dati  Istanza del contenitore dei dati di log.
   */
  void info(String message);


  /**
   * Fornisce la possibilitè di effettuare una scrittura di log con un livello
   * WARNING.
   *
   * @param dati  Istanza del contenitore dei dati di log.
   */
  void warn(String message);


  /**
   * Fornisce la possibilitè di effettuare una scrittura di log con un livello
   * ERROR.
   *
   * @param dati  Istanza del contenitore dei dati di log.
   */
  void error(String message);


  /**
   * Fornisce la possibilitè di effettuare una scrittura di log con un livello
   * FATAL.
   *
   * @param dati  Istanza del contenitore dei dati di log.
   */
  void fatal(String message);
}
