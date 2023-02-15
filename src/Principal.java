package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import src.Exceptions.CantAddPieceInEntreSouterraineException;
import src.Placages.Input.EntreSouterraineFromFileService;
import src.Placages.Input.EntreSouterraineServiceInterface;
import src.Placages.Input.PieceServiceFromFile;
import src.Placages.Input.PieceServiceInterface;
import src.Service.PlacagePieceService;

public class Principal {
    public static  String nomDuFichier;
    


    public static void main(String[] args) {
         int compteurEchec = 0;
        System.out.println("veillez saisir le nom du fichier");
        Scanner scanner = new Scanner(System.in);
           nomDuFichier = scanner.nextLine();  
         EntreSouterraine entreSouterraine = null ;   
        
        var random = new Random();
        var placagePieceService = new PlacagePieceService(random);
        PieceServiceInterface pieceService = null;
        EntreSouterraineServiceInterface entreSouterraineService = null;
        try{
            var file = new File(nomDuFichier);
            entreSouterraineService = new EntreSouterraineFromFileService(
                file,
                placagePieceService,
                random
            );
         pieceService = new PieceServiceFromFile(file);

        } catch(RuntimeException e) {
            System.out.println(" ce fichier n'exsite pas ");
            return;
        }

        try {
            entreSouterraine = entreSouterraineService.getEntreSouterraine();
            entreSouterraine.ajouterPieceCentrale(pieceService.getPiecePrincipale()); 
        } catch (CantAddPieceInEntreSouterraineException e) {
            System.out.println(entreSouterraine);
            return;
        }
        

        var pieces = pieceService.getPieces();
        pieces = new ArrayList<>(pieces);
        pieces.remove(0);
        int i = 0 ;
        Piece randomPiece = null ;
        while (i < 100 && !pieces.isEmpty()) {
            int randomIndex = random.nextInt(pieces.size());
            randomPiece = pieces.get(randomIndex);
            try {
                entreSouterraine.ajouterPiece(randomPiece);
                pieces.remove(randomPiece);
            } catch (CantAddPieceInEntreSouterraineException e) {
                i++;
            }
        }
        

        System.out.println(entreSouterraine);
        

   }
}
