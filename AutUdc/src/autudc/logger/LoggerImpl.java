package autudc.logger;

import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * Implementazione di un logger.
 * La seguente implementazione sfrutta <b>log4j</b> come motore di logging.
 * Da log4j eredita i criteri riguardanti le discriminazioni di gravitè, in
 * sequenza dalla meno rigida: DEBUG, INFO, WARN, ERROR, e FATAL.
 * <br>
 * A queste discriminanti ne vengonmo di seguito aggiunte altre.
 * <ul>
 *    <li> DEBUG.
 *    <li> INFO.
 *    <li> WARN.
 *    <li> ERROR.
 *    <li> FATAL.
 * </ul>
 **/
class LoggerImpl implements autudc.logger.Logger, Serializable {


	private static final long serialVersionUID = 1L;
	

    /**
     * Istanza dei un logger di log4J, rappresentante il motore di log.per questa
     * implementazione
     **/
    private Logger adaptee;

    // Costanti di utilitè interna
    private static final String EMTPY = "";
    private static final String MY_CLASS_NAME = "Class LoggerImpl.";
    private static final String NOT_FORMALLY_CORRECT = "Paramentri mancanti di correttezza formale.";
    
    private static boolean isLimited = false;

	/**
     * Costruttore di un istanza della classe corrente, basata su un Logger di
     * log4j e identificabile da un nome. Non vi è alcun vincolo sulla presenza
     * di banca, filiale, o operatore per il logging.
     *
     * @param name        Il nome della corrente istanza.
     * @param adaptee     Il logger effettuvo della corrente istanza.
     *
     */
    LoggerImpl(String name,
               final org.apache.log4j.Logger adaptee) {
        if (this.isValid(name) && adaptee != null) {
            this.adaptee = adaptee;
            LoggerImpl.isLimited = false;

        } else {
            throw new IllegalArgumentException(LoggerImpl.MY_CLASS_NAME +
                                               LoggerImpl.NOT_FORMALLY_CORRECT);
        }
    }

    /**
     * Costruttore di un istanza della classe corrente, basata su un Logger di
     * log4j e identificabile da un nome.
     *
     * @param name          Il nome della corrente istanza.
     * @param adaptee       Il logger effettuvo della corrente istanza.
     * @param filtroBanca   Esprime la necessitè che la banca sia valorizzata per
     *                      effettuare il log.
     * @param filtroFiliale Esprime la necessitè che la filiale sia valorizzata per
     *                      effettuare il log.
     * @param filtroUtente  Esprime la necessitè che l'utente sia valorizzato per
     *                      effettuare il log.
     */


    /**
     * Fornisce la possibilitè di effettuare una scrittura di log con un livello
     * DEBUG.
     *
     * @param dati  Istanza del contenitore dei dati di cui si necessita per la
     *              scrittura
     */
    public void debug(String message) {
        if (isValid(message)) {
            this.adaptee.debug(message);
        }
    }

    /**
     * Fornisce la possibilitè di effettuare una scrittura di log con un livello
     * WARNING.
     *
     * @param dati  Istanza del contenitore dei dati di cui si necessita per la
     *              scrittura
     */
    public void warn(String message) {
        if (isValid(message)) {
            this.adaptee.warn(message);
        }

    }

    /**
     * Fornisce la possibilitè di effettuare una scrittura di log con un livello
     * INFO.
     *
     * @param dati  Istanza del contenitore dei dati di cui si necessita per la
     *              scrittura
     */
    public void info(String message) {
        if (isValid(message)) {
            this.adaptee.info(message);
        }
    }


    /**
     * Fornisce la possibilitè di effettuare una scrittura di log con un livello
     * di ERROR.
     *
     * @param dati  Istanza del contenitore dei dati di cui si necessita per la
     *              scrittura
     */
    public void error(String message) {
        if (isValid(message)) {
            this.adaptee.error(message);
        }
    }


    /**
     * Fornisce la possibilitè di effettuare una scrittura di log con un livello
     * di FATAL.
     *
     * @param dati  Istanza del contenitore dei dati di cui si necessita per la
     *              scrittura
     */
    public void fatal(String message) {
        if (isValid(message)) {
            this.adaptee.fatal(message);
        }
    }


    /**
     * Controlla gli aspetti di coerenza formale interessanti per questa classe,
     * su parametri di tipo <code>java.lang.String</code>.
     *
     * @param param Il paramentro di tipo <code>java.lang.String</code> del quale
     *              si necessita il controllo formale.
     * @return      Ritorna <code>true</code> se il parametro è formalmente
     *              corretto, <code>false</code> altrimenti.
     **/
    private boolean isValid(String param) {
        return (param != null && !(param.trim().equals(LoggerImpl.EMTPY))) ? true : false;
    }

}
