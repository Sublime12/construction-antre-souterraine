package src.Placages.Input;

import java.util.ArrayList;

import src.Piece;

/**
 * Interface de base pour tous les services permettant de
 * recuperer des pieces
 * 
 * @author TSHIMPANGILA TSHIMANGA, Sublime (TSHS91260100)
 * @author OGUNA Chukwudi Antonio (OGUC74290400)
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
