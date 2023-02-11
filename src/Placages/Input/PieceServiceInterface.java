package src.Placages.Input;

import java.util.ArrayList;

import src.Piece;

public interface PieceServiceInterface {

    public ArrayList<Piece> getPieces();

    public Piece getPiece(int index);

    public Piece getPiecePrincipale();
}
