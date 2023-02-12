package src;

import java.io.File;
import java.util.Random;

import src.Exceptions.CantAddPieceInEntreSouterraineException;
import src.Placages.Input.EntreSouterraineFromFileService;
import src.Placages.Input.EntreSouterraineServiceInterface;
import src.Placages.Input.PieceServiceFromFile;
import src.Placages.Input.PieceServiceInterface;
import src.Service.PlacagePieceService;

public class Principal {


    public static void main(String[] args) {

        var random = new Random();
        var placagePieceService = new PlacagePieceService(random);
        // var entreSouterraine = new EntreSouterraine(50, 30, random);
        // var entreSouterraine = new EntreSouterraine(
        //     10, 
        //     15, 
        //     random, 
        //     placagePieceService
        // );

        // entreSouterraine.ajouterPieceCentrale(new Piece(2, 3));
        // System.out.println(entreSouterraine);

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
        var file = new File("test1.txt");
        EntreSouterraineServiceInterface entreSouterraineService = new EntreSouterraineFromFileService(
            file,
            placagePieceService,
            random
        );
        PieceServiceInterface pieceService = new PieceServiceFromFile(file);

        var entreSouterraine = entreSouterraineService.getEntreSouterraine();
        // System.out.println(entreSouterraine);
 
        entreSouterraine.ajouterPieceCentrale(pieceService.getPiecePrincipale());

        var pieces = pieceService.getPieces();
        for (int i = 0; i < pieces.size(); i++) {
            if (i != 0) {
                try {
                    entreSouterraine.ajouterPiece(pieces.get(i));

                } catch (CantAddPieceInEntreSouterraineException e) {
                    System.out.println("La piece " + pieces.get(i) + " ne peut pas etre ajoutee");
                    // TODO: handle exception
                }
            }
        }

        System.out.println(entreSouterraine);
        // for (int i = 0; i < 2; i++) {
        //     try {
        //         // System.out.println(4 - i);
        //         // entreSouterraine.ajouterPiece(new Piece(1, 1));
        //         entreSouterraine.ajouterPiece(new Piece( random.nextInt(12) + 1, random.nextInt(12) + 1));
        //     } catch (CantAddPieceInEntreSouterraineException e) {
        //         System.out.println("Exception : " + e);
        //     } catch (ArrayIndexOutOfBoundsException e) {
        //         System.out.println("La valeur de i lors de l'exception est : " + i);
        //         throw e;
        //     }
        // }
        // System.out.println(entreSouterraine);

        // Cote.getCoteHasard();
    }
}
