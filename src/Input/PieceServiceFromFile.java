package src.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import src.Piece;

public class PieceServiceFromFile implements PieceServiceInterface {

    private ArrayList<Piece> pieces = new ArrayList<>();

    private BufferedReader file;

    public PieceServiceFromFile instance;

    public PieceServiceFromFile(BufferedReader file) {
        this.file = file;
        registerPiece();
    }

    private void registerPiece() {
        try {
            file.reset();
            file.readLine();
            while (file.ready()) {
                String[] coordonneesPiece = file.readLine().trim().split(" ");
                int basePiece = Integer.parseInt(coordonneesPiece[0]);
                int hauteurPiece = Integer.parseInt(coordonneesPiece[1]);

                pieces.add(new Piece(basePiece, hauteurPiece));
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du traitement du fichier en entrée: " + e);
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
