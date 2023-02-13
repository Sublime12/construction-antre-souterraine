package src.Placages;

import java.util.Random;

import src.Case;
import src.Coordonnee;
import src.Piece;
import src.PlacementPiece;

public class PlacagePieceGauche 
    extends AbstractPlacagePiece 
    implements PlacagePieceInterface {


    private Random random;

    public PlacagePieceGauche(Random random) {
        this.random = random;
    }

    @Override
    public PlacementPiece placerPiece(
        Case[][] tabEntreSouterraine, 
        Piece piece,
        PlacementPiece placementPieceDeDepart
    ) {
        int x = placementPieceDeDepart.getCoordonneePointHautGauchePiece().getX() - 1;
        int y = random.nextInt(
            placementPieceDeDepart.getCoordonneePointHautGauchePiece().getY(),
            placementPieceDeDepart.getCoordonneePointHautGauchePiece().getY() + 
                placementPieceDeDepart.getPiece().getHauteur() 
        );
        var coordonneePorte = new Coordonnee(x, y);

        // Le deplacement aletoire permet de decider si on peut placer la piece
        int deplacementAletoireSurPiece = random.nextInt(piece.getHauteur());

        var coordonneePointDepartNouvellePiece = new Coordonnee(
            x - piece.getBase(), 
            y + 1 - piece.getHauteur() + deplacementAletoireSurPiece
        );

        var placementDeLaPiece = new PlacementPiece(
            piece, 
            coordonneePointDepartNouvellePiece
        );

        // TODO A enlever
        // System.out.println("EntreSouterraine.ajouterPiece()");
        // System.out.println(placementDeLaPiece);
        
        // placementsPieces.add(placementDeLaPiece);
        remplirEntreAvecPiece(
            tabEntreSouterraine,
            placementDeLaPiece
        );

        placementPieceDeDepart.setPorteGauche(coordonneePorte);
        placementDeLaPiece.setPorteDroite(coordonneePorte);

        tabEntreSouterraine[x][y] = Case.PORTE_VERTICALE;
        // TODO A enlever
        // System.out.printf("(%d, %d)\n\n", x, y);
    
        //4. Placement de la nouvelle piece
        // Pour placer le bout de la piece juste a cote 
        // de la piece il faut retrancher la base 
        //      (et -1 pour se decaler de la porte) de x et
        // la hauteur de y par rapport aux coordonnes de la porte selectionne

    
        // TODO Auto-generated method stub
        return placementDeLaPiece;
    }

    
}
