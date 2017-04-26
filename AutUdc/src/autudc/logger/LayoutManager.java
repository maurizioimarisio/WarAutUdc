package autudc.logger;

import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;

/**
 * Gestore delle varie forme di Layout.
 */
abstract class LayoutManager {

  /**
   * Crea un'istanza di <code>org.apache.log4j.Layout</code> avente un
   * pattern di defalt cosè composto:<br>
   * <b>
   * %d{dd MMM yyyy HH:mm:ss,SSS} [%t] %-5p %c %m %n
   * </b>
   * <br>
   * producente un output siffatto:<br>
   * <b>
   *   06 apr 2003 15:29:17,910 [main] DEBUG it  BANCA: BANCA1 FILIALE: FILIALE1 UTENTE: UTENTE1 MESSAGGIO: messaggio bla bla
   * </b>
   * @return L'istanza di <code>org.apache.log4j.Layout</code> creata.
   */
  static Layout getDefaultLayout(){
    return new PatternLayout(defaultPattern);
  }

  /**
   * Crea un'istanza di <code>org.apache.log4j.Layout</code> avente un
   * pattern quello dato.
   *
   * @param pattern Il pattern di layout.
   *
   * @return L'istanza di <code>org.apache.log4j.Layout</code> creata.
   *
   */
  static Layout getLayout(String pattern){
    return new PatternLayout(defaultPattern);
  }

  private static final String defaultPattern =
      "%d{dd MMM yyyy HH:mm:ss,SSS} [%t] %-5p %c %m %n";
}
