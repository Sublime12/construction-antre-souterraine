package src.Exceptions;

/**
 * nom: TSHIMPANGILA TSHIMANGA Sublime
 * code Permaent: TSHS91260100
 * nom: OGUNA Chukwudi Antonio
 * code Permanent: OGUC74290400
 * Cette exception permet de signaler qu'une piece n'a pas pu
 * etre placee
 * (info-----J'ai choisi d'utiliser le RuntimeException
 * parce que cela donne beaucoup plus de flexibilite
 * sur la gestion d'exception-----)
 */
public class CantAddPieceInAntreSouterraineException extends RuntimeException {
    public CantAddPieceInAntreSouterraineException(String message) {
        super(message);
    }

    public CantAddPieceInAntreSouterraineException(String message, Throwable e) {
        super(message, e);   
    }

}
