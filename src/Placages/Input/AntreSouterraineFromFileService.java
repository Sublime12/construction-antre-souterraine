package src.Placages.Input;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import src.AntreSouterraine;
import src.Service.PlacagePieceServiceInterface;


/**
 * Service qui va permettre de creer une antre souterraine grace aux informations se
 * trouvant dans un fichier txt
 * @{inheritDoc}
 * 
 * @author TSHIMPANGILA TSHIMANGA, Sublime (TSHS91260100)
 * @author OGUNA Chukwudi Antonio (OGUC74290400)
 * 
 */
public class AntreSouterraineFromFileService implements AntreSouterraineServiceInterface {

    private File file;

    private AntreSouterraine entreSouterraine;

    private PlacagePieceServiceInterface placagePieceService;

    private Random random;

    /**
     * @param file le fichier a consulter
     * @param placagePieceService le service qui contient les methodes de placement des pieces
     * dans notre antre
     * @param random Un generateur qui va nous permettre d'avoir des nombres aleatoires
     */
    public AntreSouterraineFromFileService(
        File file,
        PlacagePieceServiceInterface placagePieceService,
        Random random
    ) {
        this.file = file;
        this.placagePieceService = placagePieceService;
        this.random = random;
    }

    @Override
    /**
     * @{inheritDoc}
     */
    public AntreSouterraine getEntreSouterraine() {
        int base = 0;
        int hauteur = 0;
        Scanner scanner = null;

        if (entreSouterraine == null) {
            try {
                scanner = new Scanner(file);
                base = scanner.nextInt();
                hauteur = scanner.nextInt();
                entreSouterraine = new AntreSouterraine(base, hauteur, random, placagePieceService);
     
            } catch (IOException e) {
                throw new RuntimeException("Erreur lors de la lecture fichier : " + file.getAbsolutePath());
            } finally {
                if (scanner != null) {
                    scanner.close();
                }
            }
        }

        return entreSouterraine;
    }
}
