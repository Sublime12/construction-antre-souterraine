package src;

public class Piece {
    private int base;
    private int hauteur;
    public Piece(int base, int hauteur) {
        this.base = base;
        this.hauteur = hauteur;
    }
    public int getBase() {
        return base;
    }
    public int getHauteur() {
        return hauteur;
    }
    @Override
    public String toString() {
        return "Piece [base=" + base + ", hauteur=" + hauteur + "]";
    }

    // public boolean isPiecePrincipale() {

    // }
    
}
