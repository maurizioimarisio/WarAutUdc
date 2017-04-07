package autudc.logger;

import java.io.*;

import org.apache.log4j.*;
import org.apache.log4j.nt.NTEventLogAppender;

/**
 * Gestore dei vari tipi di appender.
 * La seguente implementazione fornisce appender per i seguenti device:<BR>
 * <ul>
 *    <li><code>System.err</code>
 *    <li><code>System.out</code>
 *    <li><code>File</code>
 *    <li><code>Event log di windows</code>
 * </ul>
 **/
final  class AppenderManager {

  /**
   * Crea un appender la cui risorsa per l'output risulta essere il
   * <code>NTEventLog</code>.
   * Questo tipo di log puè essere utilizzato soltanto se il S.O.
   * risulta essere WINDOWS NT/2000.
   *
   * @return L'appender sincronizzato sul <code>NTEventLog</code>
   */
   static Appender createNTEventLogAppender(){
     return new NTEventLogAppender(DEFAULT_APPL_NAME);
   }


   /**
    * Crea un appender la cui risorsa per l'output risulta essere il
    * <code>NTEventLog</code>.
    * Questo tipo di logging puè essere utilizzato soltanto se il S.O.
    * risulta essere WINDOWS NT/2000.
    *
    * @param applicationName IL nome dell'applicazione che effettua il log.
    *
    * @return L'appender sincronizzato sul <code>NTEventLog</code>
    */
   static Appender createNTEventLogAppender(String applicationName){
     return new NTEventLogAppender(applicationName);
   }


   /**
    * Crea un appender la cui risorsa per l'output risulta essere il
    * <code>System.err</code>
    *
    * @return L'appender sincronizzato sul <code>System.err</code>
    */
   static Appender createSystemErrAppender(){
     return createSystemErrAppender();
   }


   /**
    *  Crea un appender la cui risorsa per l'output risulta essere il
    * <code>System.err</code>.
    *
    * @param aLayout     Il layout di scrittura.
    *
    * @return            L'appender sincronizzato sul <code>System.err</code>.
    */
   static Appender createSystemErrAppender(Layout aLayout){
     if (aLayout == null){
       throw new IllegalStateException(DEFAULT_LAYOUT_NOT_FOUND);
     }
     return new WriterAppender(aLayout, System.err);
   }


   /**
    * Crea un appender la cui risorsa per l'output risulta essere il
    * <code>System.out</code>
    *
    * @return L'appender sincronizzato sul <code>System.err</code>
    */
   static Appender createSystemOutAppender(){
     return createSystemOutAppender();
   }


   /**
    * Crea un appender la cui risorsa per l'output risulta essere il
    * <code>System.out</code>
    *
    * @param aLayout     Il layout di scrittura.
    *
    * @return            L'appender sincronizzato sul <code>System.out</code>
    */
   static Appender createSystemOutAppender(Layout aLayout){
     if (aLayout == null){
       throw new IllegalStateException(DEFAULT_LAYOUT_NOT_FOUND);
     }
     return new WriterAppender(aLayout, System.out);
   }



   /**
    * Crea un appender la cui risorsa per l'output risulta essere un file.
    *
    * @param pathOutputFile  Il path del file di output.
    * @param layout          Il layout di scrittura.
    * @return                L'appender sincronizzato sul file di output richiesto.
    */
   static Appender createFileAppender(String pathOutputFile,
                                      Layout layout){
     if (pathOutputFile  == null){
       throw new IllegalStateException(AppenderManager.FILE_NOT_FOUND);
     }

     File outputFile = new File(pathOutputFile);
     return  createFileAppender(outputFile, layout);
   }


   /**
    * Crea un appender la cui risorsa per l'output risulta essere un file.
    *
    * @param outputFile      Il file di output.
    * @param layout          Il layout di scrittura.
    *
    * @return                L'appender sincronizzato sul file di output richiesto.
    */
   static Appender createFileAppender(File outputFile, Layout layout) {
     DailyRollingFileAppender fapp = null;

     UniqueFileAppender ufa = UniqueFileAppender.getInstance();
     fapp = ufa.getAppender();

     if (fapp != null) { return fapp; }

     boolean fileExists = false;
     if (layout == null) {
       throw new IllegalStateException(DEFAULT_LAYOUT_NOT_FOUND);
     } else {
       if (outputFile != null) {
         System.out.println(" AAAAAA File di log: " + outputFile.getPath());
         fileExists = outputFile.exists();
         if (!fileExists) {
           try {
             outputFile.createNewFile();
             fileExists = true;
           }
           catch (IOException ioe) {
              System.out.println("!!Errore creazione file : " + outputFile.getPath());
              throw new IllegalStateException(AppenderManager.FILE_NOT_CREATE);
           }
         }
         if (!outputFile.canWrite())
           throw new IllegalStateException(AppenderManager.CAN_NOT_WRITE);
       }
     }

     try {
       // voglio il rolling giornaliero...
       // "'.'yyyy-MM-dd"       = pattern giornaliero
       // "'.'yyyy-MM-dd-HH-mm" = pattern con ore e minuti
       fapp = new DailyRollingFileAppender(layout,
                                           outputFile.getAbsolutePath(),
                                           "'.'yyyy-MM-dd");
       fapp.setAppend(true);
       // lo setto al livello piu' basso di priorita'...
       fapp.setThreshold(Level.ALL);
       // scrittura immediata, senza buffering
       fapp.setImmediateFlush(true);

       if (ufa.getAppender() == null) {
         ufa.setAppender(fapp);
       }

     } catch (FileNotFoundException fNFE) {
       throw new IllegalStateException(AppenderManager.FILE_NOT_FOUND);
     }
     catch (IOException ioe) {
       throw new IllegalStateException(AppenderManager.FILE_NOT_CREATE);
     }
     return fapp;
   }

   private static final  String  FILE_NOT_CREATE =
       "Errore in creazione file di output per il log.";
   private static final  String  FILE_NOT_FOUND =
       "Il file di output per il log non risulta essere definito.";
   private static final  String  CAN_NOT_WRITE =
       "Il file di output per il log non risulta essere abilitato per la"+
       " scrittura o non presente.";

   private static final  String  DEFAULT_APPL_NAME         =  " LOGGER ";
   private static final  String  DEFAULT_LAYOUT_NOT_FOUND  =
       "Non risulta essere definito alcun layout. Impossibile la formattazione" +
       " dei dati.Operazione di logging saltata.";
}
