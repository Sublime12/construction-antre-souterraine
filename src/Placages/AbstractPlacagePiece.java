package src.Placages;

import src.CantAddPieceInEntreSouterraineException;
import src.Case;
import src.PlacementPiece;

public class AbstractPlacagePiece {
    
    
    protected boolean peutRemplirPiece(
        Case[][] tabEntreSouterraine,
        PlacementPiece placementPiece
    ) {
        // On va boucler sur toute la zone ou on devrait ajouter la piece
        // Si on trouve sur l'une des cases un element qui est != de Cote.ESPACE_PLEIN
        // On doit retourner false sinon on retourne vrai;
        boolean peutEtreRempli = true;
        for (int x = placementPiece.getCoordonneePointHautGauchePiece().getX(); x < placementPiece.getCoordonneePointHautDroitePiece().getX() && peutEtreRempli; x++) {
            for (int y = placementPiece.getCoordonneePointHautGauchePiece().getY(); y < placementPiece.getCoordonneePointBasGauchePiece().getY() && peutEtreRempli; y++) {
                if (tabEntreSouterraine[x][y] != Case.ESPACE_PLEIN_DE_DEPART) {
                    peutEtreRempli = false;
                    // System.out.println("EntreSouterraine.peutRemplirPiece()");
                    // System.out.println("i = " + i + " j = " + j);
                }
            }
        }

        return peutEtreRempli;
    }

    protected void remplirEntreAvecPiece(
        Case[][] tabEntreSouterraine , 
        PlacementPiece placementPiece
    ) {
        // System.out.println(
        //     placementPiece.getCoordonneePointHautGauchePiece()
        // );
        if ( 
            !peutRemplirPiece(
                tabEntreSouterraine,
                placementPiece
            )
        ) {
            throw new CantAddPieceInEntreSouterraineException(
                "La piece ne peut etre ajoutee"
            );
        }
        
        System.out.println("EntreSouterraine.remplirEntreAvecPiece()");
        System.out.println(placementPiece);
        for (int i = placementPiece.getCoordonneePointHautGauchePiece().getX(); i < placementPiece.getCoordonneePointHautGauchePiece().getX() + placementPiece.getPiece().getBase(); i++) {
            for (int j = placementPiece.getCoordonneePointHautGauchePiece().getY(); j < placementPiece.getCoordonneePointHautGauchePiece().getY() + placementPiece.getPiece().getHauteur(); j++) {
                // System.out.println("EntreSouterraine.remplirEntreAvecPiece()");
                // System.out.println("i = " + i + " j = " + j);
                tabEntreSouterraine[i][j] = Case.ESPACE_VIDE;
            }
        }
    }

}
