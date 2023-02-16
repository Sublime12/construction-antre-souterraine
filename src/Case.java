package src;

/**
 * Definit les differents types de cases qu'on peut
 * avoir dans l'antre
 */
public enum Case {

    /**
     * Definit un espace vide qu'une piece occupe
     * 
     */
    ESPACE_VIDE,

    /**
     * Definit une porte horizontale
     * 
     */
    PORTE_HORIZONTALE,

    /**
     * Definit un espace plein qu'un mur occupe
     * 
     */
    ESPACE_PLEIN,

    /**
     * Definit un espace plein de depart
     * lorsque aucun autre element vient
     * overrider sur cette case, elle
     * donne l'information si une case a deja 
     * ete modifie
     * 
     */
    ESPACE_PLEIN_DE_DEPART,
    
    /**
     * Definit une porte verticale
     */
    PORTE_VERTICALE,
    ;

    /**
     * Donne la representation de l'enum
     * sous forme de char
     * 
     * @return la representation sous forme de char
     */
    public char getCharRepresentation() {
        if (this == ESPACE_PLEIN) {
            return 'x';
        } else if (this == ESPACE_VIDE) {
            return ' ';
        } else if (this == PORTE_HORIZONTALE) {
            return '-';
        } else if  (this == PORTE_VERTICALE) {
            return '|';
        } else if (this == ESPACE_PLEIN_DE_DEPART) {
            return 'o';
        } else {
            return 'E';
        }
    }

    @Override
    public String toString() {
        return "" + getCharRepresentation();
    }
}
