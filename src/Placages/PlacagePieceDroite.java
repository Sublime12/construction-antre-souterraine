package src.Placages;

import java.util.Random;

import src.Case;
import src.Coordonnee;
import src.Cote;
import src.Piece;
import src.PlacementPiece;
import src.Exceptions.CantAddPieceInAntreSouterraineException;
/**
 * nom: TSHIMPANGILA TSHIMANGA Sublime
 * code Permaent: TSHS91260100
 * nom: OGUNA Chukwudi Antonio
 * code Permanent: OGUC74290400
 * cette classe permet de placer la piece droite
 */
public class PlacagePieceDroite
    extends AbstractPlacagePiece
    implements PlacagePieceInterface {
    
    private Random random;

    public PlacagePieceDroite(Random random) {
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

        } catch (CantAddPieceInAntreSouterraineException e) {

            // Si on ne peut pas ajouter la piece par random
            // on applique notre algorithme de deplacement
            // dont la signature est definie dans l'interface
            // PeutDeplacerPieceSiSuperpose
            // Si apres application deplacement
            // on ne peut toujours pas ajouter la piece
            // on va lancer un CantAddPieceInEntreSouterraineException
            // pour dire qu'on ne peut pas deplacer la piece
            placementDeLaPiece = deplacerPiece(tabEntreSouterraine, piece, coordonneePorte, Cote.DROITE);
            remplirEntreAvecPiece(tabEntreSouterraine, placementDeLaPiece);    
        }

        try {
            tabEntreSouterraine[x][y] = Case.PORTE_VERTICALE;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CantAddPieceInAntreSouterraineException("La porte n'a pas pu etre ajoute" ,e);
        }

        placementPieceDeDepart.setPorteDroite(coordonneePorte);
        placementDeLaPiece.setPorteGauche(coordonneePorte);

        return placementDeLaPiece;
    }
    
}
