package src;

/**
 * J'ai choisi d'utiliser le RuntimeException
 * parce que cela donne beaucoup plus de flexibilite
 */
public class CantAddPieceInEntreSouterraineException extends RuntimeException {
    public CantAddPieceInEntreSouterraineException(String message) {
        super(message);
    }

}
