package src;

import java.util.Random;

public class Principal {


    public static void main(String[] args) {

        var random = new Random();
        // var entreSouterraine = new EntreSouterraine(50, 30, random);
        var entreSouterraine = new EntreSouterraine(50, 50, random);

        entreSouterraine.ajouterPieceCentrale(new Piece(8, 10));
        System.out.println(entreSouterraine);

        // entreSouterraine.ajouterPiece(new Piece(4, 5));
        // System.out.println(entreSouterraine);

        // entreSouterraine.ajouterPiece(new Piece(3, 4));
        // System.out.println(entreSouterraine);

        // entreSouterraine.ajouterPiece(new Piece(4, 2));
        // System.out.println(entreSouterraine);

        // entreSouterraine.ajouterPiece(new Piece(3, 5));
        // System.out.println(entreSouterraine);

        // entreSouterraine.ajouterPiece(new Piece(5, 3));
        // System.out.println(entreSouterraine);


        // var randomGenerator  = new Random();

        for (int i = 0; i < 2000; i++) {
            try {
                System.out.println(4 - i);
                // entreSouterraine.ajouterPiece(new Piece(4, 4));
                entreSouterraine.ajouterPiece(new Piece( random.nextInt(6) + 2, random.nextInt(6) + 2));
            } catch (CantAddPieceInEntreSouterraineException e) {
                System.out.println("Exception : " + e);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("La valeur de i lors de l'exception est : " + i);
                throw e;
            }
        }
        System.out.println(entreSouterraine);

        // Cote.getCoteHasard();
    }
}
