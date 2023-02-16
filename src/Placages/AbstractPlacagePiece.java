package src.Placages;

import src.Case;
import src.PlacementPiece;
import src.Exceptions.CantAddPieceInEntreSouterraineException;


/**
 * Cette classe contient les methodes de utilitaires
 * pour nous permettre de placer une piece
 * Le mot <code>Placage</code> (a la place de Placement)  dans <code>AbstractPlacagePiece</code>
 * a ete choisi pour signifier 
 * que AbstractPlacagePiece va definir une action sur le placement des pieces
 * tandisque PlacementPiece lui va stocker les donnees sur 
 * les placements de piece
 */
public abstract class AbstractPlacagePiece implements PeutDeplacerPieceSiSuperpose {
    
    /**
     * Determine si la piece peut entrer dans 
     * l'antre a la position donnee de PlacementPiece
     */
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
            for (
                int x = placementPiece.getCoordonneePointHautGauchePiece().getX(); 
                x < placementPiece.getCoordonneePointHautDroitePiece().getX() + 1 && peutEtreRempli; 
                x++
            ) {
                for (
                    int y = placementPiece.getCoordonneePointHautGauchePiece().getY(); 
                    y < placementPiece.getCoordonneePointBasGauchePiece().getY() + 1 && peutEtreRempli; 
                    y++
                ) {
                    if (
                        tabEntreSouterraine[x][y] != Case.ESPACE_PLEIN_DE_DEPART
                    ) {
                        peutEtreRempli = false;
                    }
                }
            }
    
        } catch (ArrayIndexOutOfBoundsException e) {
            peutEtreRempli = false;
        }
        return peutEtreRempli;
    }

    /**
     * Remplit tabEntreSouterraine avec le placementPiece
     * @param tabEntreSouterraine
     * @param placementPiece
     */
    protected void remplirEntreAvecPiece(
        Case[][] tabEntreSouterraine , 
        PlacementPiece placementPiece
    ) {
        if ( 
            !peutRemplirPiece(
                tabEntreSouterraine, 
                placementPiece
            )
        ) {
            throw new CantAddPieceInEntreSouterraineException(
                "La piece " + placementPiece + "ne peut etre ajoutee"
            );
        }
        
        for (
            int x = placementPiece.getCoordonneePointHautGauchePiece().getX(); 
            x < placementPiece.getCoordonneePointHautDroitePiece().getX() + 1; 
            x++
        ) {
            for (
                int y = placementPiece.getCoordonneePointHautGauchePiece().getY(); 
                y < placementPiece.getCoordonneePointBasGauchePiece().getY() + 1; 
                y++
            ) {
                tabEntreSouterraine[x][y] = Case.ESPACE_VIDE;
            }
        }
    }
}
