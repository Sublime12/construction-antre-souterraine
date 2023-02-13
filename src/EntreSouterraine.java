package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import src.Exceptions.CantAddPieceInEntreSouterraineException;
import src.Placages.PlacagePieceInterface;
import src.Service.PlacagePieceServiceInterface;

public class EntreSouterraine extends AbstractEntreSouterraine {
    private int base;

    private int hauteur;

    private int tentativeAjout = 0;

    private Case[][] tabEntreSouterraine;

    private ArrayList<PlacementPiece> placementsPieces = new ArrayList<>();

    private ArrayList<PlacementPiece> placementPiecesPleines = new ArrayList<>();

    private boolean estPieceCentraleAjoutee = false;

    private Random random;

    private PlacagePieceServiceInterface placagePieceService;

    public EntreSouterraine(
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
    public void ajouterPiece(Piece piece) {
        System.out.println("EntreSouterraine.ajouterPiece()");
        System.out.println("Le nb des pieces placees avec murs disponibles : " + placementsPieces.size());

        // TODO
        /**
         * Premiere etape choisir une piece(PlacementPiece au hasard)
         */
        var randomPlacementPiece = placementsPieces.get(
            random.nextInt(
                placementsPieces.size()
            )
        );
        // randomPlacementPiece = placementsPieces.get(0);
        
        //2. Choisir un cote au hasard
        var cotesDisponibles =  randomPlacementPiece.getCotesDisponibles();
        Cote cote = Cote.getCoteHasard(cotesDisponibles);
        cote = Cote.BAT;
        PlacagePieceInterface placagePiece = placagePieceService.getPlacagePiece(cote);
        PlacementPiece placementDeLaPiece = placagePiece.placerPiece(
            tabEntreSouterraine, 
            piece, 
            randomPlacementPiece
        );

        entourerPlacementPiece(placementDeLaPiece);
        placementsPieces.add(placementDeLaPiece);

        
        if ( !randomPlacementPiece.aMursDisponible()) {
            System.out.println("Une piece est retiree!!");
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
                (yHaut >= 0 && yHaut < hauteur) &&
                (x >= 0 && x < base)
            ) {
                if (tabEntreSouterraine[x][yHaut] == Case.ESPACE_PLEIN_DE_DEPART) {
                    tabEntreSouterraine[x][yHaut] = Case.ESPACE_PLEIN;
                }
            }

            if (
                (yBas < hauteur && yBas >= 0) &&
                (x >= 0 && x < base)
            ) {
                if (tabEntreSouterraine[x][yBas] == Case.ESPACE_PLEIN_DE_DEPART) {
                    tabEntreSouterraine[x][yBas] = Case.ESPACE_PLEIN;
                }
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
                tabEntreSouterraine[xGauche][y] == Case.ESPACE_PLEIN_DE_DEPART
            ) {
                tabEntreSouterraine[xGauche][y] = Case.ESPACE_PLEIN;
            }

            if (
                (
                    (xDroite < base && xDroite >= 0) &&
                    (y >= 0 && y < hauteur)
                ) &&
                tabEntreSouterraine[xDroite][y] == Case.ESPACE_PLEIN_DE_DEPART
            ) {
                tabEntreSouterraine[xDroite][y] = Case.ESPACE_PLEIN;
            }

        }
    } 

    @Override
    public void ajouterPieceCentrale(Piece piece) {
        if (estPieceCentraleAjoutee()) {
            throw new CantAddPieceInEntreSouterraineException("La piece centrale est deja ajoute");
        }
        /**
         * Pour trouver la position de la piece centrale
         * je vais faire la soustraction de la taille de l'entre et de la piece qu'on vient de nous donner
         * et je divise le resultat par 2
         * Genre
         */
        if (piece.getBase() > base || piece.getHauteur() > hauteur) {
            System.out.println("La piece centrale est plus grande que la taille de l'entre");
            throw new CantAddPieceInEntreSouterraineException("On ne peut pas ajouter la piece centrale");
        }

        int debutBaseDansEntre = (base - piece.getBase()) / 2;
        int finBaseDansEntre = debutBaseDansEntre + piece.getBase();
        int debutHauteurDansEntre = (hauteur - piece.getHauteur()) / 2;
        int finHauteurDansEntre = debutHauteurDansEntre + piece.getHauteur();

        // Placement piece nous permet de suivre les pieces qui
        // qui sont deja ajoutes dans l'entre
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
    public boolean estPieceCentraleAjoutee() {
        return estPieceCentraleAjoutee;
    }

    @Override
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
