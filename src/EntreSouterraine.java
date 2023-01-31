package src;

import java.util.Arrays;

public class EntreSouterraine extends AbstractEntreSouterraine {
    private int base;

    private int hauteur;

    private int tentativeAjout = 0;

    private Case[][] tabEntreSouterraine;

    private boolean estPieceCentraleAjoutee = false;

    public EntreSouterraine(int base, int hauteur) {
        setBase(base);
        setHauteur(hauteur);
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
         * Premiere etape choisir un des quatres cotes au hasard
         */

        // TODO Auto-generated method stub

    }

    @Override
    public void ajouterPieceCentrale(Piece piece) {
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

        for (int i = debutBaseDansEntre; i < finBaseDansEntre; i++) {
            for (int j = debutHauteurDansEntre; j < finHauteurDansEntre; j++) {
                tabEntreSouterraine[i][j] = Case.ESPACE_VIDE;
            }
        }

        // TODO Implementation piece centrale
        estPieceCentraleAjoutee = true;
    }

    @Override
    public boolean estPieceCentraleAjoutee() {
        // TODO Auto-generated method stub
        return estPieceCentraleAjoutee;
    }

    @Override
    public String toString() {
        String entreAsText = "";
        for (Case[] ligne : tabEntreSouterraine) {
            for (Case cellule : ligne) {
                entreAsText += cellule + " ";
            }
            entreAsText += "\n";
        }

        return entreAsText;
    }


}
