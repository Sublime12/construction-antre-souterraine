package src;

public enum Case {
    ESPACE_VIDE,
    PORTE_HORIZONTALE,
    ESPACE_PLEIN,
    ESPACE_PLEIN_DE_DEPART,    
    PORTE_VERTICALE,
    ;

    public char getCharRepresentation() {
        if (this == ESPACE_PLEIN) {
            return 'x';
        } else if (this == ESPACE_VIDE) {
            return '.';
        } else if (this == PORTE_HORIZONTALE) {
            return '-';
        } else if  (this == PORTE_VERTICALE) {
            return '|';
        } else if (this == ESPACE_PLEIN_DE_DEPART) {
            return 'o';
        } else {
            return ' ';
        }
    }

    @Override
    public String toString() {
        return "" + getCharRepresentation();
    }
}
