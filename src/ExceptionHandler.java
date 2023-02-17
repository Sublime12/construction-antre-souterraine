package src;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * nom: TSHIMPANGILA TSHIMANGA Sublime
 * code Permaent: TSHS91260100
 * nom: OGUNA Chukwudi Antonio
 * code Permanent: OGUC74290400
 * Gestionnaire d'exception de notre 
 * application, elle log les exceptions dans
 * un fichier dont la constante est
 * <code>ExceptionHandler.ERRORS_LOG_FILE<code/>
 */
public class ExceptionHandler {
    
    private static final String ERRORS_LOG_FILE = "errors.log";

    /**
     * Executer <code>fn<code/>
     * et capture toutes les exceptions
     * lancees
     * @param fn la fonction (callable) a executer
     */
    public void handle(Function fn) {
        try {
            fn.executer();

        } catch (Throwable e) {
            System.err.println("Exception : ");

            System.err.println(e.getMessage());
            essayerEcrireSurLogFile(e);
        }
    }

    private void essayerEcrireSurLogFile(Throwable e) {
        PrintWriter errorsFileStream  = null;
        
        /**
         * Logique d'ecriture dans le logfile
         */
        try {
            var errorsFile = new File(ERRORS_LOG_FILE);
            errorsFile.createNewFile();

            var fileWriter  = new FileWriter(errorsFile, true);

            errorsFileStream = new PrintWriter(fileWriter);
            errorsFileStream.append(e.toString() + "\n");

            errorsFileStream.append("Stack Trace : \n");
            for (var stackTrace : e.getStackTrace()) {
                errorsFileStream.append(stackTrace.toString() + "\n");
            }

            errorsFileStream.println();
            // e.(errorsFileStream);


            fileWriter.close();
            errorsFileStream.close();

        } catch (Exception exception) {
            System.err.println("Error dans lors de l'ecriture dans le log file");
        } 
    }

    @FunctionalInterface
    public interface Function {
        public void executer();
    }
}
