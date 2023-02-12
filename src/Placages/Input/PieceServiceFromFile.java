package src.Placages.Input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        try {
        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        while(scanner.hasNext()){
            int base =  scanner.nextInt();
            int hauteur = scanner.nextInt();
            pieces.add(new Piece(base, hauteur));

        }
        } catch (Exception e) {
        throw new RuntimeException(e);
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
