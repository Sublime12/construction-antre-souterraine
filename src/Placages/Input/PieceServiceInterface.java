package src.Placages.Input;

import java.util.ArrayList;

import src.Piece;

/**
 * Interface de base pour tous les services permettant de
 * recuperer des pieces
 */
public interface PieceServiceInterface {

    /**
     * Retourne toutes les pieces
     * @return
     */
    public ArrayList<Piece> getPieces();

    /**
     * @param index
     * @return Une piece a l'indice <code>index<code/>
     */
    public Piece getPiece(int index);

    /**
     * 
     * @return la piece principale
     */
    public Piece getPiecePrincipale();
}
