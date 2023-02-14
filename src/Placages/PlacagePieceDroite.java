package src.Placages;

import java.util.Random;

import src.Case;
import src.Coordonnee;
import src.Cote;
import src.Piece;
import src.PlacementPiece;
import src.Exceptions.CantAddPieceInEntreSouterraineException;

public class PlacagePieceDroite
    extends AbstractPlacagePiece
    implements PlacagePieceInterface {
    
    private Random random;

    public PlacagePieceDroite(Random random) {
        this.random = random;
    }


    @Override
    public PlacementPiece placerPiece(
        Case[][] tabEntreSouterraine, 
        Piece piece, 
        PlacementPiece placementPieceDeDepart
    ) {

        // x et y sont les coordonnees de la porte
        int x = 
            placementPieceDeDepart.getCoordonneePointHautGauchePiece().getX() + 
            placementPieceDeDepart.getPiece().getBase()
        ;

        int y = random.nextInt(
            placementPieceDeDepart.getCoordonneePointHautGauchePiece().getY(),
            placementPieceDeDepart.getCoordonneePointHautGauchePiece().getY() + 
                placementPieceDeDepart.getPiece().getHauteur() 
        );
        var coordonneePorte = new Coordonnee(x, y);

        int deplacementAletoireSurPiece = random.nextInt(piece.getHauteur());

        var coordonneePointDepartNouvellePiece = new Coordonnee(
            x + 1, 
            y + 1 - piece.getHauteur() + deplacementAletoireSurPiece
        );

        var placementDeLaPiece = new PlacementPiece(
            piece, 
            coordonneePointDepartNouvellePiece
        );

        try {
            remplirEntreAvecPiece(
                tabEntreSouterraine,
                placementDeLaPiece
            );

        } catch (CantAddPieceInEntreSouterraineException e) {

            placementDeLaPiece = deplacerPiece(tabEntreSouterraine, piece, coordonneePorte, Cote.DROITE);
            remplirEntreAvecPiece(tabEntreSouterraine, placementDeLaPiece);    
        }

        try {
            tabEntreSouterraine[x][y] = Case.PORTE_VERTICALE;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CantAddPieceInEntreSouterraineException("La porte n'a pas pu etre ajoute" ,e);
        }

        placementPieceDeDepart.setPorteDroite(coordonneePorte);
        placementDeLaPiece.setPorteGauche(coordonneePorte);

        return placementDeLaPiece;
    }
    
}
