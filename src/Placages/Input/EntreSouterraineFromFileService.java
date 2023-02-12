package src.Placages.Input;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import src.EntreSouterraine;
import src.Service.PlacagePieceServiceInterface;

public class EntreSouterraineFromFileService implements EntreSouterraineServiceInterface {

    private File file;

    private EntreSouterraine entreSouterraine;

    private PlacagePieceServiceInterface placagePieceService;

    private Random random;

    public EntreSouterraineFromFileService(
        File file,
        PlacagePieceServiceInterface placagePieceService,
        Random random
    ) {
        this.file = file;
        this.placagePieceService = placagePieceService;
        this.random = random;
    }

    @Override
    public EntreSouterraine getEntreSouterraine() {
        int base = 0;
        int hauteur = 0;
        Scanner scanner = null;

        if (entreSouterraine == null) {
            try {
                scanner = new Scanner(file);
                // scanner.nextLine();
                 base = scanner.nextInt();
                 hauteur = scanner.nextInt();
                 entreSouterraine = new EntreSouterraine(base, hauteur, random, placagePieceService);
     
             } catch (IOException e) {
                 System.out.println("Erreur lors de la lecture fichier : " + e);
                 throw new RuntimeException(e);
             } finally {
                 if (scanner != null) {
                     scanner.close();
                 }
            }
        }

        return entreSouterraine;
    }
}
