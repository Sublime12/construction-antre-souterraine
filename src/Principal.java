package src;

import java.util.Random;

public class Principal {


    public static void main(String[] args) {

        var entreSouterraine = new EntreSouterraine(40, 32, new Random());

        entreSouterraine.ajouterPieceCentrale(new Piece(15, 10));
        System.out.println(entreSouterraine);

        entreSouterraine.ajouterPiece(new Piece(7, 9));
        System.out.println(entreSouterraine);

        entreSouterraine.ajouterPiece(new Piece(3, 8));
        System.out.println(entreSouterraine);

        // var randomGenerator  = new Random();

        // for (int i = 0; i < 100; i++) {
        //     entreSouterraine.ajouterPiece(new Piece( randomGenerator.nextInt(4) + 1, randomGenerator.nextInt(4) + 1));
        // }
        System.out.println(entreSouterraine);

        // Cote.getCoteHasard();
    }
}
