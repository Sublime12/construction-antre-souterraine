package src.Exceptions;

/**
 * nom: TSHIMPANGILA TSHIMANGA Sublime
 * code Permaent: TSHS91260100
 * nom: OGUNA Chukwudi Antonio
 * code Permanent: OGUC74290400
 * Exception lancee lorsqu'on essaye d'ajouter la piece centrale et que la
 * piece centrale ne peut pas etre placee ou lorsque l'on essaye de placer une 
 * autre piece sans placer la piece centrale 
 */
public class PieceCentraleNaPasEteAjouteeException extends RuntimeException {
    public PieceCentraleNaPasEteAjouteeException(String message) {
        super(message);
    }

    public PieceCentraleNaPasEteAjouteeException(String message, Throwable e) {
        super(message, e);
    }
}
