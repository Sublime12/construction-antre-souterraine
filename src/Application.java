package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.function.Function;

/** 
 * nom: TSHIMPANGILA TSHIMANGA Sublime
 * code Permaent: TSHS91260100
 * nom: OGUNA Chukwudi Antonio
 * code Permanent: OGUC74290400
 * cette classe  contient la logique d'execution de notre application
 */ 
public class Application {
    public static void executer(Function function){
        try {
            function.run();

        } catch (Throwable e) {
            System.err.println("Exception inatendue lors de l'execution");
            System.err.println(e.getMessage());
        }
    }

    
    public interface Function {
        void run();
        
    }
}
