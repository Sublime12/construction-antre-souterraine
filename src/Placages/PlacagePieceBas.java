package src.Placages;

import java.util.Random;

import src.Case;
import src.Coordonnee;
import src.Cote;
import src.Piece;
import src.PlacementPiece;
import src.Exceptions.CantAddPieceInEntreSouterraineException;

public class PlacagePieceBas
    extends AbstractPlacagePiece
    implements PlacagePieceInterface 
{

    private Random random;

    public PlacagePieceBas(Random random) {
        this.random = random;
    }

    @Override
    public PlacementPiece placerPiece(
        Case[][] tabEntreSouterraine, 
        Piece piece, 
        PlacementPiece placementPieceDeDepart
    ) {
        
        // x et y representent les cordonnees de la porte
        int x = random.nextInt(
            placementPieceDeDepart.getCoordonneePointBasGauchePiece().getX(),
            placementPieceDeDepart.getCoordonneePointBasDroitePiece().getX() + 1
        );
        int y = placementPieceDeDepart.getCoordonneePointBasGauchePiece().getY() + 1;

        var coordonneePorte = new Coordonnee(x, y);

        int deplacementAletoireSurPiece = random.nextInt(piece.getBase());

        var coordonneePointDepartNouvellePiece = new Coordonnee(
            x - deplacementAletoireSurPiece, 
            y + 1
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
            placementDeLaPiece = deplacerPiece(tabEntreSouterraine, piece, coordonneePorte, Cote.BAT);
            remplirEntreAvecPiece(
                tabEntreSouterraine, 
                placementDeLaPiece
            );
        }
        try {
            tabEntreSouterraine[x][y] = Case.PORTE_HORIZONTALE;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CantAddPieceInEntreSouterraineException("La porte n'a pas pu etre ajoute" ,e);
        }

        placementPieceDeDepart.setPorteBas(coordonneePorte);
        placementDeLaPiece.setPorteHaut(coordonneePorte);

        return placementDeLaPiece;
    }
        

}
