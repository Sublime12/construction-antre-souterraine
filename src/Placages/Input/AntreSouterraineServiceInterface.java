package src.Placages.Input;

import src.AntreSouterraine;

/**
 * Interface de base pour le service
 *  qui devrait permettre de recuperer une entre souterraine
 * 
 * @author TSHIMPANGILA TSHIMANGA, Sublime (TSHS91260100)
 * @author OGUNA Chukwudi Antonio (OGUC74290400)
 */
public interface AntreSouterraineServiceInterface {

    /**
     * 
     * @return Retourne un object entre souterraine cree depuis ce service
     */
    public AntreSouterraine getEntreSouterraine();
}
