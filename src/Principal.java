package src;

public class Principal {


    public static void main(String[] args) {

        var entreSouterraine = new EntreSouterraine(15, 15);

        entreSouterraine.ajouterPieceCentrale(new Piece(5, 5));
        entreSouterraine.ajouterPiece(new Piece(4, 4));

        // System.out.println(entreSouterraine);

        Cote.getCoteHasard();
    }
}
