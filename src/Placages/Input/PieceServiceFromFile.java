package src.Placages.Input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import src.Piece;

/**
 * nom: TSHIMPANGILA TSHIMANGA Sublime
 * code Permaent: TSHS91260100
 * nom: OGUNA Chukwudi Antonio
 * code Permanent: OGUC74290400
 * Service qui permet de creer les differentes pieces 
 * en recuperant les informations dans un fichier
 * txt
 * 
 * @{inheritDoc}
 */
public class PieceServiceFromFile implements PieceServiceInterface {

    private ArrayList<Piece> pieces = new ArrayList<>();

    private File file;

    public PieceServiceFromFile instance;

    /**
     * Le fichier a lire
     * @param file
     */
    public PieceServiceFromFile(File file) {
        this.file = file;
        registerPiece();
    }

    private void registerPiece() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            scanner.nextLine();
            while(scanner.hasNext()){
                int base =  scanner.nextInt();
                int hauteur = scanner.nextInt();
                pieces.add(new Piece(base, hauteur));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier \"" + file.getAbsoluteFile() + "\"");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }   
        
    };

    /**
     * Permet de recuperer une piece a un indice donnee
     * @return la piece a l'indice <code>index<code/> ou null
     */
    public Piece getPiece(int index) {
        return pieces.get(index);
    }

    @Override
    /**
     * @return la piece principale
     */
    public Piece getPiecePrincipale() {
        return getPiece(0);
    }

    @Override
    /**
     * Retourne toutes les pieces
     */
    public ArrayList<Piece> getPieces() {
        return pieces;
    }

}
