package src.Exceptions;

/**
 * J'ai choisi d'utiliser le RuntimeException
 * parce que cela donne beaucoup plus de flexibilite
 * Cette exception permet de signaler qu'une piece n'a pas pu
 * etre placee
 */
public class CantAddPieceInEntreSouterraineException extends RuntimeException {
    public CantAddPieceInEntreSouterraineException(String message) {
        super(message);
    }

    public CantAddPieceInEntreSouterraineException(String message, Throwable e) {
        super(message, e);   
    }

}
