package src.Placages.Input;

import src.EntreSouterraine;

/**
 * Interface de base pour le service
 *  qui devrait permettre de recuperer une entre souterraine
 */
public interface EntreSouterraineServiceInterface {

    /**
     * 
     * @return Retourne un object entre souterraine cree depuis ce service
     */
    public EntreSouterraine getEntreSouterraine();
}
