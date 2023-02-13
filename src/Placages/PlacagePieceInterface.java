package src.Placages;

import src.Case;
import src.Piece;
import src.PlacementPiece;

public interface PlacagePieceInterface {

    /**
     * 
     * @param entreSouterraineTab Un tableau de 2 dimensions qui contient notre tableau
     * Le type choisi n'est peut-etre pas le bon mais pour l'instant
     * c'est le mieux qui permette de travailler avec notre entre
     * @param placementPiece
     * @return <code>PlacementPiece<code/>
     */
    public PlacementPiece placerPiece(
        Case[][] tabEntreSouterraine, 
        Piece piece,
        PlacementPiece placementPieceDeDepart
    );


}
