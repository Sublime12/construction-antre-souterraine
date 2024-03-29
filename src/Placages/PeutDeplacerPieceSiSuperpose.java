package src.Placages;

import src.Case;
import src.Coordonnee;
import src.Cote;
import src.Piece;
import src.PlacementPiece;
import src.Exceptions.CantAddPieceInAntreSouterraineException;


/**
 * Elle permet de definir un comportement sur un element
 * si cette element se retrouvait superposer sur
 * un autre
 * 
 * @author TSHIMPANGILA TSHIMANGA, Sublime (TSHS91260100)
 * @author OGUNA Chukwudi Antonio (OGUC74290400)
 */
public interface PeutDeplacerPieceSiSuperpose {

    /**
     * Determine si on peut remplir la piece (!LE PLACEMENTPIECE) dans notre tableau (-- antre)
     * @param tabEntreSouterraine
     * @param placementPiece
     * @return true si possible false sinon 
     */
    public boolean peutRemplirPiece(
        Case[][] tabEntreSouterraine,
        PlacementPiece placementPiece
    );

    /**
     * Deplace la piece pour lui permettre d'etre placee
     * sur un tableau de deux dimensions
     * @param tabEntreSouterraine
     * @param piece
     * @param coordonneePorte
     * @param cote
     * @return la nouvelle coordonnee du point haut gauche ou la piece
     * pourrait etre placee
     * 
     * @throws CantAddPieceInAntreSouterraineException si on ne peut pas deplacer
     * la piece
     */
    default PlacementPiece deplacerPiece(
        Case[][] tabEntreSouterraine, 
        Piece piece,
        Coordonnee coordonneePorte,
        Cote cote
    ) {
        PlacementPiece placementDeplacementDeLaPiece = null;
        Coordonnee coordonneePointDeDepart = null;

        boolean peutDeplacerPiece = false;
        
        
        if (cote == Cote.GAUCHE || cote == Cote.DROITE) {
            int x = cote == Cote.GAUCHE ? 
                coordonneePorte.getX() - piece.getBase() : 
                coordonneePorte.getX() + 1
            ;
            
            for (
                int y = coordonneePorte.getY() - piece.getHauteur() + 1; 
                y < coordonneePorte.getY() + 1 && !peutDeplacerPiece; 
                y++
            ) {
                coordonneePointDeDepart = new Coordonnee(x, y);

                placementDeplacementDeLaPiece = new PlacementPiece(piece, coordonneePointDeDepart);
                if (peutRemplirPiece(tabEntreSouterraine, placementDeplacementDeLaPiece)) {
                    peutDeplacerPiece = true;
                }
            }
        }
        
        if (cote == Cote.BAT || cote == Cote.HAUT) {
            int y = cote == Cote.HAUT ? 
                coordonneePorte.getY() - piece.getHauteur() :
                coordonneePorte.getY() + 1
            ;

            for (
                int x = coordonneePorte.getX() - piece.getBase() + 1; 
                x < coordonneePorte.getX() + 1 && !peutDeplacerPiece; 
                x++
            ) {
                coordonneePointDeDepart = new Coordonnee(x, y);

                placementDeplacementDeLaPiece = new PlacementPiece(piece, coordonneePointDeDepart);
                if (peutRemplirPiece(tabEntreSouterraine, placementDeplacementDeLaPiece)) {
                    peutDeplacerPiece = true;
                }   
            }
        }

        if ( !peutDeplacerPiece) {
            throw new CantAddPieceInAntreSouterraineException("La piece n'a pas pu etre deplace");
        }
        return placementDeplacementDeLaPiece;
    }
    
}
