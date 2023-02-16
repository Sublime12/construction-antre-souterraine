package src.Placages.Input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import src.Piece;

public class PieceServiceFromFile implements PieceServiceInterface {

    private ArrayList<Piece> pieces = new ArrayList<>();

    private File file;

    public PieceServiceFromFile instance;

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
            throw new RuntimeException(e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }   
        
    };

    public Piece getPiece(int index) {
        return pieces.get(index);
    }

    @Override
    public Piece getPiecePrincipale() {
        return getPiece(0);
    }

    @Override
    public ArrayList<Piece> getPieces() {
        return pieces;
    }
    


}
