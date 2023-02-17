package src.Service;

import src.Cote;
import src.Placages.PlacagePieceInterface;


/**
 * Interface de base pour toute classe (service) qui
 * va permettre de recuperer une implementation
 * du placement du piece
 */
public interface PlacagePieceServiceInterface {

    /**
     * @param cote
     * @return object permettant de placer une piece
     */
    public PlacagePieceInterface getPlacagePiece(Cote cote);
}
