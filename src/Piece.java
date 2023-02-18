package src;

/**
 * Classe qui represente une piece (et par definition la piece centrale 
 * est aussi une piece)
 * 
 * @author TSHIMPANGILA TSHIMANGA, Sublime (TSHS91260100)
 * @author OGUNA Chukwudi Antonio (OGUC74290400)
 */
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
}
