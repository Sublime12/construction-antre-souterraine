package src;

import java.util.ArrayList;
import java.util.List;

public class PlacementPiece {
    private Piece piece;

    private Coordonnee pointHautGaucheDeLaPiece;

    /** 
     * J'aurais pu creer une structure PlacementPorteDansMur
     * mais cela aurait creer une dependance transitive dans le code
     * plutard (peut-etre pas ...)
     * Si le besoin se fait resentir je vais remplacer 
     * les proprietes si-dessous par une classe qui a 
     * 4 proprietes booleenes
     */

    private Coordonnee porteGauche;
    private Coordonnee porteDroite;
    private Coordonnee porteBas;
    private Coordonnee porteHaut;

    public PlacementPiece(Piece piece, Coordonnee pointHautGaucheDeLaPiece) {
        this.piece = piece;
        this.pointHautGaucheDeLaPiece = pointHautGaucheDeLaPiece;
        // Pourrais introduire une dependance transitive dans cette classe
        // this.placementPorteDansMur = new PlacementPorteDansMur();
    }

    public Coordonnee getCoordonneePointHautGauchePiece() {

        // En donnant une copie des coordonnee on s'evite e
        // que quelqu'un puisse les modifier sans notre consentement
        return new Coordonnee(
            pointHautGaucheDeLaPiece.getX(), 
            pointHautGaucheDeLaPiece.getY()
        );
    }

    public Coordonnee getCoordonneePointHautDroitePiece() {
        return new Coordonnee(
            pointHautGaucheDeLaPiece.getX() + piece.getBase() - 1,
            pointHautGaucheDeLaPiece.getY() 
        );
    }

    public Coordonnee getCoordonneePointBasGauchePiece() {
        return new Coordonnee(
            pointHautGaucheDeLaPiece.getX(),
            pointHautGaucheDeLaPiece.getY() + piece.getHauteur() - 1
        );
    }

    public Coordonnee getCoordonneePointBasDroitePiece() {
        return new Coordonnee(
            pointHautGaucheDeLaPiece.getX() + piece.getBase() - 1,
            pointHautGaucheDeLaPiece.getY() + piece.getHauteur() - 1
        );
    }


    public List<Cote> getCotesDisponibles() {

        var cotes = new ArrayList<Cote>();
        
        if (porteGauche == null) {
            cotes.add(Cote.GAUCHE);
        }
        if (porteDroite == null) {
            cotes.add(Cote.DROITE);
        }
        if (porteHaut == null) {
            // cotes.add(Cote.HAUT);
        }
        
        if (porteBas == null) {
            // cotes.add(Cote.BAT);
        }

        // TODO A supprimer
        // cotes = new ArrayList<Cote>();
        // cotes.add(Cote.GAUCHE);
        // cotes.add(Cote.DROITE);

        return cotes;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPorteGauche(Coordonnee porteGauche) {
        this.porteGauche = porteGauche;
    }

    public void setPorteDroite(Coordonnee porteDroite) {
        this.porteDroite = porteDroite;
    }

    public void setPorteBas(Coordonnee porteBas) {
        this.porteBas = porteBas;
    }

    public void setPorteHaut(Coordonnee porteHaut) {
        this.porteHaut = porteHaut;
    }

    public boolean isPorteGauchePlacee() {
        return porteGauche != null;
    }

    public boolean isPorteDroitePlacee() {
        return porteDroite == null;
    }

    public boolean isPorteBasPlacee() {
        return porteBas == null;
    }

    public boolean isPorteHautPlacee() {
        return porteHaut == null;
    }

    @Override
    public String toString() {
        String text = "";

        text += pointHautGaucheDeLaPiece;

        return text;
    }

}
    