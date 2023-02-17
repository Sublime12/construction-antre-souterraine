package src.Placages;

import java.util.Random;

import src.Case;
import src.Coordonnee;
import src.Cote;
import src.Piece;
import src.PlacementPiece;
import src.Exceptions.CantAddPieceInEntreSouterraineException;

public class PlacagePieceGauche 
    extends AbstractPlacagePiece 
    implements PlacagePieceInterface {


    private Random random;

    public PlacagePieceGauche(Random random) {
        this.random = random;
    }

    @Override
    /**
     * @{inheritDoc}
     */
    public PlacementPiece placerPiece(
        Case[][] tabEntreSouterraine, 
        Piece piece,
        PlacementPiece placementPieceDeDepart
    ) {

        // x et y sont les coordonnees de la porte
        int x = placementPieceDeDepart.getCoordonneePointHautGauchePiece().getX() - 1;
        int y = random.nextInt(
            placementPieceDeDepart.getCoordonneePointHautGauchePiece().getY(),
            placementPieceDeDepart.getCoordonneePointHautGauchePiece().getY() + 
                placementPieceDeDepart.getPiece().getHauteur() 
        );
        var coordonneePorte = new Coordonnee(x, y);

        int deplacementAletoireSurPiece = random.nextInt(piece.getHauteur());

        var coordonneePointDepartNouvellePiece = new Coordonnee(
            x - piece.getBase(), 
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

            // Si on ne peut pas ajouter la piece par random
            // on applique notre algorithme de deplacement
            // dont la signature est definie dans l'interface
            // PeutDeplacerPieceSiSuperpose
            // Si apres application deplacement
            // on ne peut toujours pas ajouter la piece
            // on va lancer un CantAddPieceInEntreSouterraineException
            // pour dire qu'on ne peut pas deplacer la piece
        } catch (CantAddPieceInEntreSouterraineException e) {
            // Peut lancer une exception si n'arrive pas a deplacer la piece
            placementDeLaPiece = deplacerPiece(tabEntreSouterraine, piece, coordonneePorte, Cote.GAUCHE);

            remplirEntreAvecPiece(tabEntreSouterraine, placementDeLaPiece);
        }
        
        placementPieceDeDepart.setPorteGauche(coordonneePorte);
        placementDeLaPiece.setPorteDroite(coordonneePorte);
        
        tabEntreSouterraine[x][y] = Case.PORTE_VERTICALE;
    
        return placementDeLaPiece;
    }
    
}
