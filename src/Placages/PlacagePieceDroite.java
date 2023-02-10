package src.Placages;

import java.util.Random;

import src.Case;
import src.Coordonnee;
import src.Piece;
import src.PlacementPiece;

public class PlacagePieceDroite
    extends AbstractPlacagePiece
    implements PlacagePieceInterface {
    
    private Random random;

    public PlacagePieceDroite(Random random) {
        this.random = random;
    }


    @Override
    public boolean placerPiece(
        Case[][] tabEntreSouterraine, 
        Piece piece, 
        PlacementPiece placementPieceDeDepart
    ) {
        // 3. Calcul de la position de la position au hasard
        // Si la porte choisi est gauche alors notre x ne bouge que de -1
        // Alors que y bouge de yHautPiece vers yBas
        int x = placementPieceDeDepart.getCoordonneePointHautGauchePiece().getX() + placementPieceDeDepart.getPiece().getBase() ;
        int y = random.nextInt(
            placementPieceDeDepart.getCoordonneePointHautGauchePiece().getY(),
            placementPieceDeDepart.getCoordonneePointHautGauchePiece().getY() + 
                placementPieceDeDepart.getPiece().getHauteur() 
        );
        var coordonneePorte = new Coordonnee(x, y);

        var coordonneePointDepartNouvellePiece = new Coordonnee(
            x + 1, 
            y + 1 - piece.getHauteur()
        );

        var placementDeLaPiece = new PlacementPiece(
            piece, 
            coordonneePointDepartNouvellePiece
        );

        // TODO A enlever
        System.out.println("PlacagePieceDroite.placerPiece()");
        System.out.println(placementDeLaPiece);
        
        // placementsPieces.add(placementDeLaPiece);
        remplirEntreAvecPiece(
            tabEntreSouterraine,
            placementDeLaPiece
        );

        placementPieceDeDepart.setPorteDroite(coordonneePorte);
        placementDeLaPiece.setPorteGauche(coordonneePorte);

        tabEntreSouterraine[x][y] = Case.PORTE_VERTICALE;

    
    // TODO Auto-generated method stub
        return false;
    }

        
    
}
