package src;

public class Principal {


    public static void main(String[] args) {

        var entreSouterraine = new EntreSouterraine(20, 14);

        entreSouterraine.ajouterPieceCentrale(new Piece(8, 4));
        entreSouterraine.ajouterPiece(new Piece(4, 4));

        System.out.println(entreSouterraine);

        // Cote.getCoteHasard();
    }
}
