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
 * cette class permet de placer les pieces en bas
 */
public class PlacagePieceBas
    extends AbstractPlacagePiece
    implements PlacagePieceInterface 
{

    private Random random;

    /**
     * Recoit random pour le permettre de
     * generer des nombres aleatoires
     * @param random
     */
    public PlacagePieceBas(Random random) {
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
        } catch (CantAddPieceInAntreSouterraineException e) {

            // Si on ne peut pas ajouter la piece par random
            // on applique notre algorithme de deplacement
            // dont la signature est definie dans l'interface
            // PeutDeplacerPieceSiSuperpose
            // Si apres application deplacement
            // on ne peut toujours pas ajouter la piece
            // on va lancer un CantAddPieceInEntreSouterraineException
            // pour dire qu'on ne peut pas deplacer la piece

            placementDeLaPiece = deplacerPiece(tabEntreSouterraine, piece, coordonneePorte, Cote.BAT);
            remplirEntreAvecPiece(
                tabEntreSouterraine, 
                placementDeLaPiece
            );
        }
        try {
            tabEntreSouterraine[x][y] = Case.PORTE_HORIZONTALE;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CantAddPieceInAntreSouterraineException("La porte n'a pas pu etre ajoute" ,e);
        }

        placementPieceDeDepart.setPorteBas(coordonneePorte);
        placementDeLaPiece.setPorteHaut(coordonneePorte);

        return placementDeLaPiece;
    }
        

}
