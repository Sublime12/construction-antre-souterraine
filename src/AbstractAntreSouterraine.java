package src;

import src.Exceptions.CantAddPieceInAntreSouterraineException;

/**
 * Classe abstraite definissant le contract que devrait avoir 
 * une AntreSouterraine
 * 
 * @author TSHIMPANGILA TSHIMANGA, Sublime (TSHS91260100)
 * @author OGUNA Chukwudi Antonio (OGUC74290400)
 */
public abstract class AbstractAntreSouterraine {

    /**
     * Permet d'ajouter la piece centrale
     * 
     * @param piece
     * @throws CantAddPieceInAntreSouterraineException 
     * <description>
     *  Lorsque la piece centrale est plus grande que l'antre
     * <description/>
     
     */
    public abstract void ajouterPieceCentrale(Piece piece);

    /**
     * Permet de placer une piece dans une antre
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
