package src;

import java.util.Random;

import src.Exceptions.CantAddPieceInEntreSouterraineException;
import src.Service.PlacagePieceService;

public class Principal {


    public static void main(String[] args) {

        var random = new Random();
        var placagePieceService = new PlacagePieceService(random);
        // var entreSouterraine = new EntreSouterraine(50, 30, random);
        var entreSouterraine = new EntreSouterraine(
            80, 
            50, 
            random, 
            placagePieceService
        );

        entreSouterraine.ajouterPieceCentrale(new Piece(14, 13));
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

        for (int i = 0; i < 200000; i++) {
            try {
                // System.out.println(4 - i);
                // entreSouterraine.ajouterPiece(new Piece(1, 1));
                entreSouterraine.ajouterPiece(new Piece( random.nextInt(12) + 1, random.nextInt(12) + 1));
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
