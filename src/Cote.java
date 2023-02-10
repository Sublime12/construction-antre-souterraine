package src;

import java.util.List;
import java.util.Random;

public enum Cote {
    GAUCHE,
    DROITE,
    HAUT,
    BAT;

    public static Cote getCoteHasard(List<Cote> cotes) {
        // Cote cote = null;
        var randomGenerator = new Random();
        /**
         * On va generer un nombre aleatoire entre 0 et 3 chacun de ces nombres va
         * correspondre a un type de coté
         */
        int randomNumber = randomGenerator.nextInt(cotes.size());
        // if (randomNumber == 0) {
        //     cote = GAUCHE;
        // } else if (randomNumber == 1) {
        //     cote = DROITE;
        // } else if (randomNumber == 2) {
        //     // cote = HAUT;
        //     cote = DROITE;
        // } else if (randomNumber == 3) {
        //     // cote = BAT;
        //     cote = GAUCHE;
        // }
        
        return cotes.get(randomNumber);
    }
}
