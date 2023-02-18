package src.Exceptions;

/**
 * Cette exception permet de signaler qu'une piece n'a pas pu
 * etre placee
 * (info-----J'ai choisi d'utiliser le RuntimeException
 * parce que cela donne beaucoup plus de flexibilite
 * sur la gestion d'exception-----)
 * 
 * @author TSHIMPANGILA TSHIMANGA, Sublime (TSHS91260100)
 * @author OGUNA Chukwudi Antonio (OGUC74290400)
 */
public class CantAddPieceInAntreSouterraineException extends RuntimeException {
    public CantAddPieceInAntreSouterraineException(String message) {
        super(message);
    }

    public CantAddPieceInAntreSouterraineException(String message, Throwable e) {
        super(message, e);   
    }

}
