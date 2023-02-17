package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import src.Exceptions.CantAddPieceInAntreSouterraineException;
import src.Exceptions.PieceCentraleNaPasEteAjouteeException;
import src.Placages.Input.AntreSouterraineFromFileService;
import src.Placages.Input.AntreSouterraineServiceInterface;
import src.Placages.Input.PieceServiceFromFile;
import src.Placages.Input.PieceServiceInterface;
import src.Service.PlacagePieceService;

/**
 * Classe principale ou se trouve la methode main
 */
public class Principal {
    public static  String nomDuFichier;

    /**
     * Methode main du programme
     * Elle est le point d'entre de notre programme
     * Elle sert a instancier les differents services 
     * dont auront besoin les differentes classes
     * du programme et elle contient la logique d'execution
     * de l'algorithme de facon generale
     * @param args
     */
    public static void main(String[] args) {
        new ExceptionHandler().handle(() -> {
            var nomDuFichier = lireNomFichier();

            AntreSouterraine entreSouterraine = null ;   
            
            PieceServiceInterface pieceService = null;
            AntreSouterraineServiceInterface entreSouterraineService = null;
            var random = new Random();
            var placagePieceService = new PlacagePieceService(random);
    
            // try{
            var file = new File(nomDuFichier);
            entreSouterraineService = new AntreSouterraineFromFileService(
                file,
                placagePieceService,
                random
            );
            pieceService = new PieceServiceFromFile(file);
    
            // } catch(RuntimeException e) {
                // System.out.println(" ce fichier n'existe pas ");
                // return;
            // }
    
            try {
                entreSouterraine = entreSouterraineService.getEntreSouterraine();
                entreSouterraine.ajouterPieceCentrale(pieceService.getPiecePrincipale()); 
            } catch (PieceCentraleNaPasEteAjouteeException e) {
                // Afficher l'antre vide  si la piece centrale est trop grande
                System.out.println(entreSouterraine);
    
                // Relance l'exception pour permettre a ExceptionHandler d'afficher
                // le message d'erreur
                throw e;
            }
            
    
            var pieces = pieceService.getPieces();
            pieces = new ArrayList<>(pieces);
            pieces.remove(0);
            if (entreSouterraine.estPieceCentraleAjoutee()) {
                ajouterToutesLesPieces(entreSouterraine, random, pieces);
        
            }
    
            System.out.println(entreSouterraine);
    
    
        });
    }

    /**
     * Tente d'ajouter toutes les pieces dans l'antre 
     * Tente 100 fois et arrete si elle ne peut pas tout placer
     * 
     * @param entreSouterraine Antre qui doit les pieces
     * @param random Un generateur de nombre aleatoire
     * @param pieces Les pieces a ajouter dans l'antre
     */
    private static void ajouterToutesLesPieces(AntreSouterraine entreSouterraine, Random random, ArrayList<Piece> pieces) {
        int i = 0;
        Piece randomPiece = null;
        while (i < 100 && !pieces.isEmpty()) {
            int randomIndex = random.nextInt(pieces.size());
            randomPiece = pieces.get(randomIndex);
            try {
                entreSouterraine.ajouterPiece(randomPiece);
                pieces.remove(randomPiece);
            } catch (CantAddPieceInAntreSouterraineException e) {
                i++;
            }
        }
    }

    private static String lireNomFichier() {
        // Lecture du fichier
        System.out.println("veillez saisir le nom du fichier");
        Scanner scanner = new Scanner(System.in);
        nomDuFichier = scanner.nextLine();
        scanner.close();

        return nomDuFichier;
    }
}
