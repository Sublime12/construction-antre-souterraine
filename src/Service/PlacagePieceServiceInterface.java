package src.Service;

import src.Cote;
import src.Placages.PlacagePieceInterface;


/**
 * nom: TSHIMPANGILA TSHIMANGA Sublime
 * code Permaent: TSHS91260100
 * nom: OGUNA Chukwudi Antonio
 * code Permanent: OGUC74290400
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
