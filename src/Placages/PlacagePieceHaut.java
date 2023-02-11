package src.Placages;

import java.util.Random;

import src.CantAddPieceInEntreSouterraineException;
import src.Case;
import src.Coordonnee;
import src.Piece;
import src.PlacementPiece;

public class PlacagePieceHaut
    extends AbstractPlacagePiece
    implements PlacagePieceInterface {

    private Random random;

    public PlacagePieceHaut(Random random) {
        this.random = random;
    }

    @Override
    public PlacementPiece placerPiece(
        Case[][] tabEntreSouterraine, 
        Piece piece,
        PlacementPiece placementPieceDeDepart
    ) {
       
        int x = random.nextInt(
            placementPieceDeDepart.getCoordonneePointBasGauchePiece().getX(),
            placementPieceDeDepart.getCoordonneePointBasDroitePiece().getX() + 1
        );
        int y = placementPieceDeDepart.getCoordonneePointHautGauchePiece().getY() - 1;

        var coordonneePorte = new Coordonnee(x, y);

        var coordonneePointDepartNouvellePiece = new Coordonnee(
            x, 
            y - piece.getHauteur()
        );

        var placementDeLaPiece = new PlacementPiece(
            piece, 
            coordonneePointDepartNouvellePiece
        );

        // TODO A enlever
        // System.out.println("EntreSouterraine.ajouterPiece()");
        System.out.println(placementDeLaPiece);

        
        
        System.out.println("Voici les coordonnes de la porte bas : " + coordonneePorte);
        
        remplirEntreAvecPiece(
            tabEntreSouterraine,
            placementDeLaPiece
        );

        // Attraper une ArrayOutBoundException ici
        // revient a dire que la porte ne peut pas etre
        // placee
        try {
            tabEntreSouterraine[x][y] = Case.PORTE_HORIZONTALE;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CantAddPieceInEntreSouterraineException("La porte n'a pas pu etre ajoute" ,e);
        }
        // placementsPieces.add(placementDeLaPiece);
        
        // System.out.println(this);
        placementPieceDeDepart.setPorteHaut(coordonneePorte);
        placementDeLaPiece.setPorteBas(coordonneePorte);

        return placementDeLaPiece;
    }

        
    
}
