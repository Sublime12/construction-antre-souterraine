package src;

import java.util.ArrayList;
import java.util.Random;

import src.Exceptions.CantAddPieceInAntreSouterraineException;
import src.Exceptions.PieceCentraleNaPasEteAjouteeException;
import src.Placages.PlacagePieceInterface;
import src.Service.PlacagePieceServiceInterface;

/**
 * Classe qui represente l'antre souterraine 
 * Elle permet d'ajouter la piece centrale
 * et les autres pieces
 * 
 * @author TSHIMPANGILA TSHIMANGA, Sublime (TSHS91260100)
 * @author OGUNA Chukwudi Antonio (OGUC74290400)
 */
public class AntreSouterraine extends AbstractAntreSouterraine {
    private int base;

    private int hauteur;

    // private int tentativeAjout = 0;

    private Case[][] tabEntreSouterraine;

    private ArrayList<PlacementPiece> placementsPieces = new ArrayList<>();

    // private ArrayList<PlacementPiece> placementPiecesPleines = new ArrayList<>();

    private boolean estPieceCentraleAjoutee = false;

    private Random random;

    private PlacagePieceServiceInterface placagePieceService;

    /**
     * Constructeur de l'antre souterraine 
     * Celle-ci recoit la base, la hauteur, un object Random
     * et un service PlacagePieceServiceInterface 
     * 
     * @param base represente la base qu'aura notre antre souterraine
     * @param hauteur represente la hauteur qu'aura notre antre souterraine
     * @param random object (dependance) qui va nous permettre de generer
     * des nombres aleatoires dans notre antre
     * @param placagePieceService Service permettant de determiner l'algorithme 
     * a appliquer par rapport aux differents types de murs qu'a une piece
     */
    public AntreSouterraine(
        int base, 
        int hauteur, 
        Random random, 
        PlacagePieceServiceInterface placagePieceService
    ) {
        setBase(base);
        setHauteur(hauteur);
        this.random = random;
        this.placagePieceService = placagePieceService;
        tabEntreSouterraine = new Case[base][hauteur];
        remplirEntreSouterraine();
    }

    private void remplirEntreSouterraine() {
        for (int i = 0; i < base; i++) {
            for (int j = 0; j < hauteur; j++) {
                tabEntreSouterraine[i][j] = Case.ESPACE_PLEIN_DE_DEPART;
            }
        }
    }

    private void setBase(int base) {
        if (base > 0) {
            this.base = base;
        }
    }

    private void setHauteur(int hauteur) {
        if (base > 0) {
            this.hauteur = hauteur;
        }

    }

    @Override
    /**
     * @{inheritDoc}
     */
    public void ajouterPiece(Piece piece) {

        /**
         * Premiere etape choisir une piece(PlacementPiece au hasard)
         */
        var randomPlacementPiece = placementsPieces.get(
            random.nextInt(
                placementsPieces.size()
            )
        );
        
        //2. Choisir un cote au hasard
        var cotesDisponibles =  randomPlacementPiece.getCotesDisponibles();
        Cote cote = Cote.getCoteHasard(cotesDisponibles);

        PlacagePieceInterface placagePiece = placagePieceService.getPlacagePiece(cote);
        PlacementPiece placementDeLaPiece = placagePiece.placerPiece(
            tabEntreSouterraine, 
            piece, 
            randomPlacementPiece
        );

        entourerPlacementPiece(placementDeLaPiece);
        placementsPieces.add(placementDeLaPiece);
        
        if ( !randomPlacementPiece.aMursDisponible()) {
            placementsPieces.remove(randomPlacementPiece);
        }

        if (placementDeLaPiece != null) {
            entourerPlacementPiece(placementDeLaPiece);
        }

    }

    private void entourerPlacementPiece(PlacementPiece placementPiece) {
        for (
            int x = placementPiece.getCoordonneePointHautGauchePiece().getX() - 1; 
            x < placementPiece.getCoordonneePointHautDroitePiece().getX() + 2; 
            x++
        ) {
            int yHaut = placementPiece.getCoordonneePointHautGauchePiece().getY() - 1;
            int yBas = placementPiece.getCoordonneePointBasGauchePiece().getY() + 1;

            if (
                (yHaut >= 0 && yHaut < hauteur) && (x >= 0 && x < base) && 
                (tabEntreSouterraine[x][yHaut] == Case.ESPACE_PLEIN_DE_DEPART)
            ) {
                tabEntreSouterraine[x][yHaut] = Case.ESPACE_PLEIN;
            }

            if (
                (yBas < hauteur && yBas >= 0) && (x >= 0 && x < base) &&
                (tabEntreSouterraine[x][yBas] == Case.ESPACE_PLEIN_DE_DEPART)
            ) {
                tabEntreSouterraine[x][yBas] = Case.ESPACE_PLEIN;
            }
        }

        for (
            int y = placementPiece.getCoordonneePointHautGauchePiece().getY(); 
            y < placementPiece.getCoordonneePointBasGauchePiece().getY() + 1; 
            y++
        ) {
            int xGauche = placementPiece.getCoordonneePointHautGauchePiece().getX() - 1;
            int xDroite = placementPiece.getCoordonneePointHautDroitePiece().getX() + 1;

            if (
                ((xGauche >= 0 && xGauche < base) && (y >= 0 && y < hauteur)) &&
                (tabEntreSouterraine[xGauche][y] == Case.ESPACE_PLEIN_DE_DEPART)
            ) {
                tabEntreSouterraine[xGauche][y] = Case.ESPACE_PLEIN;
            }

            if (
                ((xDroite < base && xDroite >= 0) && (y >= 0 && y < hauteur)) &&
                (tabEntreSouterraine[xDroite][y] == Case.ESPACE_PLEIN_DE_DEPART)
            ) {
                tabEntreSouterraine[xDroite][y] = Case.ESPACE_PLEIN;
            }

        }
    } 

    @Override
    /**
     * {@inheritDoc}
     */
    public void ajouterPieceCentrale(Piece piece) {
        if (estPieceCentraleAjoutee()) {
            throw new CantAddPieceInAntreSouterraineException("La piece centrale est deja ajoute");
        }
        
        if (piece.getBase() > base || piece.getHauteur() > hauteur) {
            throw new PieceCentraleNaPasEteAjouteeException(
                "On ne peut pas ajouter la piece centrale, la piece donnee est trop grande; piece : " + piece + "\n" +
                "Taille antre : [base = " + base + ", hauteur = " + hauteur 
            );
        }
            
        /**
         * Pour trouver la position de la piece centrale
         * je vais faire la soustraction de la taille de l'entre et de la piece qu'on vient de nous donner
         * et je divise le resultat par 2
         * Genre
         */
        int debutBaseDansEntre = (base - piece.getBase()) / 2;
        int finBaseDansEntre = debutBaseDansEntre + piece.getBase();
        int debutHauteurDansEntre = (hauteur - piece.getHauteur()) / 2;
        int finHauteurDansEntre = debutHauteurDansEntre + piece.getHauteur();

        PlacementPiece placementPiece = new PlacementPiece(piece, new Coordonnee(debutBaseDansEntre, debutHauteurDansEntre));
        placementsPieces.add(placementPiece);

        for (int i = debutBaseDansEntre; i < finBaseDansEntre; i++) {
            for (int j = debutHauteurDansEntre; j < finHauteurDansEntre; j++) {
                tabEntreSouterraine[i][j] = Case.ESPACE_VIDE;
            }
        }

        entourerPlacementPiece(placementPiece);
        estPieceCentraleAjoutee = true;
    }

    @Override
    /**
     * @{inheritDoc}
     */
    public boolean estPieceCentraleAjoutee() {
        return estPieceCentraleAjoutee;
    }

    @Override
    /**
     * Retourne la representation de notre antre 
     * sous forme d'une chaine qui va ressembler a 
     * un dessin d'un tableau
     */
    public String toString() {
        String entreAsText = "";
        for (int j = 0; j < hauteur; j++) {
            for (int i = 0; i < base; i++) {
                entreAsText += tabEntreSouterraine[i][j] + " ";
            }
            entreAsText += "\n";
        }

        return entreAsText;
    }

}
