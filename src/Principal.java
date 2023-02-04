package src;

import java.util.Random;

public class Principal {


    public static void main(String[] args) {

        var entreSouterraine = new EntreSouterraine(20, 14, new Random());

        entreSouterraine.ajouterPieceCentrale(new Piece(8, 4));

        entreSouterraine.ajouterPiece(new Piece(4, 4));
        // var randomGenerator  = new Random();

        // for (int i = 0; i < 100; i++) {
        //     entreSouterraine.ajouterPiece(new Piece( randomGenerator.nextInt(4) + 1, randomGenerator.nextInt(4) + 1));
        // }
        System.out.println(entreSouterraine);

        // Cote.getCoteHasard();
    }
}
