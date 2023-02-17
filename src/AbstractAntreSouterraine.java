package src;

import src.Exceptions.CantAddPieceInAntreSouterraineException;

public abstract class AbstractAntreSouterraine {

    /**
     * 
     * @param piece
     * @throws CantAddPieceInAntreSouterraineException 
     * <description>
     *      Lorsque la piece centrale est plus grande que l'antre
     * <description/>
     */
    public abstract void ajouterPieceCentrale(Piece piece);

    /**
     * Methode permettant de placer une piece dans une antre
     * souterraine
     * 
     * @param piece La piece a placer
     */
    public abstract void ajouterPiece(Piece piece);

    /**
     * Permet de verifier si la piece centrale est deja ajoute
     * dans l'antre
     * @return
     */
    public abstract boolean estPieceCentraleAjoutee();

}