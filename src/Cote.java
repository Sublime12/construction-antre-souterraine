package src;

import java.util.List;
import java.util.Random;

public enum Cote {
    GAUCHE,
    DROITE,
    HAUT,
    BAT;

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
