package src.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import src.Cote;
import src.Placages.PlacagePieceBas;
import src.Placages.PlacagePieceDroite;
import src.Placages.PlacagePieceGauche;
import src.Placages.PlacagePieceHaut;
import src.Placages.PlacagePieceInterface;


/**
 * Service qui permet de recuperer l'implementation 
 * de la methode de remplissage d'une piece 
 * par rapport a un cote qui a ete donne
 */
public class PlacagePieceService implements PlacagePieceServiceInterface {

    public Random random;

    public PlacagePieceService(Random random) {
        this.random = random;
    }

    @Override
    public PlacagePieceInterface getPlacagePiece(Cote cote) {
        // PlacagePieceInterface placagePieceInterface = null;
        var placagesMap = new HashMap<Cote, PlacagePieceInterface>();

        placagesMap.put(Cote.GAUCHE, new PlacagePieceGauche(random));
        placagesMap.put(Cote.DROITE, new PlacagePieceDroite(random));
        placagesMap.put(Cote.HAUT, new PlacagePieceHaut(random));
        placagesMap.put(Cote.BAT, new PlacagePieceBas(random));

        // cote = Cote.BAT;
        // TODO Auto-generated method stub
        return placagesMap.get(cote);
    }
    
    
}
