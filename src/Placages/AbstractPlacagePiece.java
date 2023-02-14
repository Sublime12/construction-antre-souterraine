package src.Placages;

import src.Case;
import src.PlacementPiece;
import src.Exceptions.CantAddPieceInEntreSouterraineException;

public abstract class AbstractPlacagePiece implements PeutDeplacerPieceSiSuperpose {
    
    
    public boolean peutRemplirPiece(
        Case[][] tabEntreSouterraine,
        PlacementPiece placementPiece
    ) {
        // On va boucler sur toute la zone ou on devrait ajouter la piece
        // Si on trouve sur l'une des cases un element qui est != de Cote.ESPACE_PLEIN
        // On doit retourner false sinon on retourne vrai;
        boolean peutEtreRempli = true;

        // Avoir une exception du type ArrayIndexOutOfBoundsException
        // revient a dire que la piece n'entre pas totalement dans l'entre
        try {
            for (int x = placementPiece.getCoordonneePointHautGauchePiece().getX(); x < placementPiece.getCoordonneePointHautDroitePiece().getX() + 1 && peutEtreRempli; x++) {
                for (int y = placementPiece.getCoordonneePointHautGauchePiece().getY(); y < placementPiece.getCoordonneePointBasGauchePiece().getY() + 1 && peutEtreRempli; y++) {
                    if (
                        tabEntreSouterraine[x][y] != Case.ESPACE_PLEIN_DE_DEPART
                    ) {
                        peutEtreRempli = false;
                        // System.out.println("EntreSouterraine.peutRemplirPiece()");
                        // System.out.println("i = " + i + " j = " + j);
                    }
                }
            }
    
        } catch (ArrayIndexOutOfBoundsException e) {
            peutEtreRempli = false;
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
            throw new CantAddPieceInEntreSouterraineException("La piece ne peut etre ajoutee");
        }
        
        System.out.println("EntreSouterraine.remplirEntreAvecPiece()");
        System.out.println(placementPiece);
        for (int i = placementPiece.getCoordonneePointHautGauchePiece().getX(); i < placementPiece.getCoordonneePointHautDroitePiece().getX() + 1; i++) {
            for (int j = placementPiece.getCoordonneePointHautGauchePiece().getY(); j < placementPiece.getCoordonneePointBasGauchePiece().getY() + 1; j++) {
                // System.out.println("EntreSouterraine.remplirEntreAvecPiece()");
                System.out.println("x = " + i + " y = " + j);
                tabEntreSouterraine[i][j] = Case.ESPACE_VIDE;
            }
        }
    }
}
