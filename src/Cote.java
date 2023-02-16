package src;

import java.util.List;
import java.util.Random;

/**
 * Enum representant les 4 cotes qu'on peut
 * avoir
 * 
 */
public enum Cote {
    GAUCHE,
    DROITE,
    HAUT,
    BAT;

   
    /**
     * Methode qui permet de retourner un cote
     * au  hasard parmis les cotes passees en parametre
     * @param cotes
     * @return
     */
    public static Cote getCoteHasard(List<Cote> cotes) {
        var randomGenerator = new Random();
        /**
         * On va generer un nombre aleatoire entre 0 et 3 chacun de ces nombres va
         * correspondre a un type de coté
         */
        int randomNumber = randomGenerator.nextInt(cotes.size());
        
        return cotes.get(randomNumber);
    }
}
