package src;


/**
 * Represente une coordonnee pour un point
 * dans un tableau a deux dimensions
 */
public class Coordonnee {
    private int x;
    private int y;

    public Coordonnee(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        
        return String.format("(%d, %d)", x, y);
    }
}
