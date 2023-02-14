package src.Placages;

import src.Case;
import src.Coordonnee;
import src.Cote;
import src.Piece;
import src.PlacementPiece;
import src.Exceptions.CantAddPieceInEntreSouterraineException;

public interface PeutDeplacerPieceSiSuperpose {
    // public PlacementPiece deplacerPiece(
    //     Case[][] tabEntreSouterraine,
    //     Piece piece,
    //     Coordonnee coordonneePorte,
    //     Cote cote
    // );

    public boolean peutRemplirPiece(
        Case[][] tabEntreSouterraine,
        PlacementPiece placementPiece
    );

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
                    // System.out.println("!!!!La piece peut etre rempli");
                    // throw new RuntimeException("!!!!La piece peut etre rempli");
                    peutDeplacerPiece = true;
                    System.out.println("PlacagePieceGauche.deplacerPiece()");
                    System.out.println("La piece a pu etre deplacee");
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
                    System.out.println("PeutDeplacerPieceSiSuperpose.deplacerPiece()");
                    System.out.println("La piece a pu etre deplacee");
                    // throw new RuntimeException("La piece a pu etre deplacee");
                }   
            }
        }

        if ( !peutDeplacerPiece) {
            throw new CantAddPieceInEntreSouterraineException("La piece n'a pas pu etre deplace");
        }
        return placementDeplacementDeLaPiece;
    }
    
}
