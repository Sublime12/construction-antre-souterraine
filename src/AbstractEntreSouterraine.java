package src;

import src.Exceptions.CantAddPieceInEntreSouterraineException;

public abstract class AbstractEntreSouterraine {

    /**
     * 
     * @param piece
     * @throws CantAddPieceInEntreSouterraineException 
     * <description>
     *      Lorsque la piece centrale est plus grande que l'antre
     * <description/>
     */
    public abstract void ajouterPieceCentrale(Piece piece);

    public abstract void ajouterPiece(Piece piece);

    public abstract boolean estPieceCentraleAjoutee();

}
