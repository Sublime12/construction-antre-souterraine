package src.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

import src.EntreSouterraine;

public class EntreSouterraineFromFileService implements EntreSouterraineServiceInterface {

    private BufferedReader file;

    private EntreSouterraine entreSouterraine;

    public EntreSouterraineFromFileService(BufferedReader file) {
        this.file = file;
    }

    @Override
    public EntreSouterraine getEntreSouterraine() {
        int base = 0;
        int hauteur = 0;
        String premiereLigneFichier = "";
        String[] coordonneesEntreAsString;

        try {
            file.reset();
            // if (file.ready()) {
                premiereLigneFichier = file.readLine();
                coordonneesEntreAsString = premiereLigneFichier.trim().split(" ");

            // }

            base = Integer.parseInt(coordonneesEntreAsString[0]);
            hauteur = Integer.parseInt(coordonneesEntreAsString[1]);

        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture fichier : " + e);
        }


        if (entreSouterraine == null) {
            entreSouterraine = new EntreSouterraine(base, hauteur);
        }

        return entreSouterraine;
    }
}
