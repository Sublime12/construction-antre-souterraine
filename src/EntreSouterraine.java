package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class EntreSouterraine extends AbstractEntreSouterraine {
    private int base;

    private int hauteur;

    private int tentativeAjout = 0;

    private Case[][] tabEntreSouterraine;

    private ArrayList<PlacementPiece> placementsPieces = new ArrayList<>();

    private boolean estPieceCentraleAjoutee = false;

    private Random random;

    public EntreSouterraine(int base, int hauteur, Random random) {
        setBase(base);
        setHauteur(hauteur);
        this.random = random;
        tabEntreSouterraine = new Case[base][hauteur];
        remplirEntreSouterraine();
    }

    private void remplirEntreSouterraine() {
        for (int i = 0; i < base; i++) {
            for (int j = 0; j < hauteur; j++) {
                tabEntreSouterraine[i][j] = Case.ESPACE_PLEIN;
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
        // TODO
        /**
         * Premiere etape choisir une piece(PlacementPiece au hasard)
         */
        var randomPlacementPiece = placementsPieces.get(
            random.nextInt(
                placementsPieces.size()
            )
        );

        //2. Choisir un cote au hasard
        Cote cote = Cote.getCoteHasard();
        cote = Cote.GAUCHE;
        if (cote == Cote.GAUCHE) {
            //3. Calcul de la position de la position au hasard
            // Si la porte choisi est gauche alors notre x ne bouge que de -1
            // Alors que y bouge de yHautPiece vers yBas
            int x = randomPlacementPiece.getCoordonneePointHautPiece().getX() - 1;
            int y = random.nextInt(
                randomPlacementPiece.getCoordonneePointHautPiece().getY(),
                randomPlacementPiece.getCoordonneePointHautPiece().getY() + 
                    randomPlacementPiece.getPiece().getHauteur() 
            );
            var coordonneePorte = new Coordonnee(x, y);

            var coordonneePointDepartNouvellePiece = new Coordonnee(
                x - piece.getBase(), 
                y + 1 - piece.getHauteur()
            );

            var placementDeLaPiece = new PlacementPiece(
                piece, 
                coordonneePointDepartNouvellePiece
            );

            // TODO A enlever
            System.out.println("EntreSouterraine.ajouterPiece()");
            System.out.println(placementDeLaPiece);
            
            placementsPieces.add(placementDeLaPiece);
            remplirEntreAvecPiece(placementDeLaPiece);

            randomPlacementPiece.setPorteGauche(coordonneePorte);

            tabEntreSouterraine[x][y] = Case.PORTE_VERTICALE;
            // TODO A enlever
            // System.out.printf("(%d, %d)\n\n", x, y);
        
            //4. Placement de la nouvelle piece
            // Pour placer le bout de la piece juste a cote 
            // de la piece il faut retrancher la base 
            //      (et -1 pour se decaler de la porte) de x et
            // la hauteur de y par rapport aux coordonnes de la porte selectionne

        }

        // TODO Auto-generated method stub

    }

    @Override
    public void ajouterPieceCentrale(Piece piece) {
        // if (estPieceCentraleAjoutee()) {
        //     throw new RuntimeException("La piece centrale n'est pas ajoute");
        // }
        /**
         * Pour trouver la position de la piece centrale
         * je vais faire la soustraction de la taille de l'entre et de la piece qu'on vient de nous donner
         * et je divise le resultat par 2
         * Genre
         */
        if (piece.getBase() > base || piece.getHauteur() > hauteur) {
            System.out.println("La piece centrale est plus grande que la taille de l'entre");
            // TODO cas a gerer
        }

        int debutBaseDansEntre = (base - piece.getBase()) / 2;
        int finBaseDansEntre = debutBaseDansEntre + piece.getBase();
        int debutHauteurDansEntre = (hauteur - piece.getHauteur()) / 2;
        int finHauteurDansEntre = debutHauteurDansEntre + piece.getHauteur();

        // Placement piece nous permet de suivre les pieces qui
        // qui sont deja ajoutes dans l'entre
        placementsPieces.add(new PlacementPiece(piece, new Coordonnee(debutBaseDansEntre, debutHauteurDansEntre)));

        for (int i = debutBaseDansEntre; i < finBaseDansEntre; i++) {
            for (int j = debutHauteurDansEntre; j < finHauteurDansEntre; j++) {
                tabEntreSouterraine[i][j] = Case.ESPACE_VIDE;
            }
        }

        // TODO Implementation piece centrale
        estPieceCentraleAjoutee = true;
    }

    private void remplirEntreAvecPiece(PlacementPiece placementPiece) {
        System.out.println("EntreSouterraine.remplirEntreAvecPiece()");
        System.out.println(placementPiece);
        for (int i = placementPiece.getCoordonneePointHautPiece().getX(); i < placementPiece.getCoordonneePointHautPiece().getX() + placementPiece.getPiece().getBase(); i++) {
            for (int j = placementPiece.getCoordonneePointHautPiece().getY(); j < placementPiece.getCoordonneePointHautPiece().getY() + placementPiece.getPiece().getHauteur(); j++) {
                // System.out.println("EntreSouterraine.remplirEntreAvecPiece()");
                // System.out.println("i = " + i + " j = " + j);
                tabEntreSouterraine[i][j] = Case.ESPACE_VIDE;
            }
        }
    }

    private boolean peutRemplirPiece(PlacementPiece placementPiece) {
        // TODO Implementer la methode de validation du
        // du remplissage de la piece
        return false;
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
