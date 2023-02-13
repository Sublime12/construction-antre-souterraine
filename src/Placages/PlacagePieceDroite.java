package src.Placages;

import java.util.Random;

import src.Case;
import src.Coordonnee;
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
        // 3. Calcul de la position de la position au hasard
        // Si la porte choisi est gauche alors notre x ne bouge que de -1
        // Alors que y bouge de yHautPiece vers yBas
        int x = placementPieceDeDepart.getCoordonneePointHautGauchePiece().getX() + placementPieceDeDepart.getPiece().getBase();
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

        // TODO A enlever
        System.out.println("PlacagePieceDroite.placerPiece()");
        System.out.println(placementDeLaPiece);
        
        // placementsPieces.add(placementDeLaPiece);
        remplirEntreAvecPiece(
            tabEntreSouterraine,
            placementDeLaPiece
        );
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
