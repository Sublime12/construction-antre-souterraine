package src;

public enum Case {
    ESPACE_VIDE,
    PORTE_HORIZONTALE,
    ESPACE_PLEIN,
    PORTE_VERTICALE
    ;

    public char getCharRepresentation() {
        if (this == ESPACE_PLEIN) {
            return 'O';
        } else if (this == ESPACE_VIDE) {
            return '.';
        } else if (this == PORTE_HORIZONTALE) {
            return '|';
        } else if  (this == PORTE_VERTICALE) {
            return '-';
        } else {
            return ' ';
        }
    }

    @Override
    public String toString() {
        return "" + getCharRepresentation();
    }
}
