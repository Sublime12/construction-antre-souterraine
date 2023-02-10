package src.Placages;

import java.util.Random;

import src.Case;
import src.Coordonnee;
import src.Piece;
import src.PlacementPiece;

public class PlacagePieceBas
    extends AbstractPlacagePiece
    implements PlacagePieceInterface 
{

    private Random random;

    public PlacagePieceBas(Random random) {
        this.random = random;
    }

    @Override
    public boolean placerPiece(
        Case[][] tabEntreSouterraine, 
        Piece piece, 
        PlacementPiece placementPieceDeDepart
    ) {
        

        // 3. Calcul de la position de la porte au hasard
        // Si la porte choisi est en bas alors notre x ne bouge que de -1
        // Alors que y bouge de yHautPiece vers yBas
        // int x = placementPieceDeDepart.getCoordonneePointHautGauchePiece().getX() + placementPieceDeDepart.getPiece().getBase() ;
        // int y = random.nextInt(
        //     placementPieceDeDepart.getCoordonneePointHautGauchePiece().getY(),
        //     placementPieceDeDepart.getCoordonneePointHautGauchePiece().getY() + 
        //         placementPieceDeDepart.getPiece().getHauteur() 
        // );

        // x et y representent les cordonnees de la porte
        int x = random.nextInt(
            placementPieceDeDepart.getCoordonneePointBasGauchePiece().getX(),
            placementPieceDeDepart.getCoordonneePointBasDroitePiece().getX()
        );
        int y = placementPieceDeDepart.getCoordonneePointBasDroitePiece().getX() + 1;

        var coordonneePorte = new Coordonnee(x, y);

        var coordonneePointDepartNouvellePiece = new Coordonnee(
            x, 
            y + 1
        );

        var placementDeLaPiece = new PlacementPiece(
            piece, 
            coordonneePointDepartNouvellePiece
        );

        // TODO A enlever
        // System.out.println("EntreSouterraine.ajouterPiece()");
        System.out.println(placementDeLaPiece);

        System.out.println("Voici les coordonnes de la porte bas : " + coordonneePorte);

        tabEntreSouterraine[x][y] = Case.PORTE_HORIZONTALE;

        
        // placementsPieces.add(placementDeLaPiece);
        remplirEntreAvecPiece(
            tabEntreSouterraine,
            placementDeLaPiece
        );

        placementPieceDeDepart.setPorteBas(coordonneePorte);
        placementDeLaPiece.setPorteHaut(coordonneePorte);

        // if ( !placementPieceDeDepart.aMursDisponible()) {
        //     placementsPieces.remove(placementPieceDeDepart);
        // }


    
    // TODO Auto-generated method stub
        return false;
    }
        

}