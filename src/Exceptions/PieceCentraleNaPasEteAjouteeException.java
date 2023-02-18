package src.Exceptions;

/**
 * Exception lancee lorsqu'on essaye d'ajouter la piece centrale et que la
 * piece centrale ne peut pas etre placee ou lorsque l'on essaye de placer une 
 * autre piece sans placer la piece centrale 
 * 
 * @author TSHIMPANGILA TSHIMANGA, Sublime (TSHS91260100)
 * @author OGUNA Chukwudi Antonio (OGUC74290400)
 */
public class PieceCentraleNaPasEteAjouteeException extends RuntimeException {
    public PieceCentraleNaPasEteAjouteeException(String message) {
        super(message);
    }

    public PieceCentraleNaPasEteAjouteeException(String message, Throwable e) {
        super(message, e);
    }
}
