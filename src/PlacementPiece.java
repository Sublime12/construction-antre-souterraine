package src;

public class PlacementPiece {
    private Piece piece;

    private Coordonnee pointHautGaucheDeLaPiece;

    public PlacementPiece(Piece piece, Coordonnee pointHautGaucheDeLaPiece) {
        this.piece = piece;
        this.pointHautGaucheDeLaPiece = pointHautGaucheDeLaPiece;
    }

    public Coordonnee getCoordonnee() {

        // En donnant une copie des coordonnee on s'evite e
        // que quelqu'un puisse les modifier sans notre consentement
        return new Coordonnee(
            pointHautGaucheDeLaPiece.getX(), 
            pointHautGaucheDeLaPiece.getY()
        );
    }
}
