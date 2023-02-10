package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class EntreSouterraine extends AbstractEntreSouterraine {
    private int base;

    private int hauteur;

    private int tentativeAjout = 0;

    private Case[][] tabEntreSouterraine;

    private ArrayList<PlacementPiece> placementsPieces = new ArrayList<>();

    private ArrayList<PlacementPiece> placementPiecesPleines = new ArrayList<>();

    private boolean estPieceCentraleAjoutee = false;

    private Random random;

    public EntreSouterraine(int base, int hauteur, Random random) {
        setBase(base);
        setHauteur(hauteur);
        this.random = random;
        tabEntreSouterraine = new Case[base][hauteur];
        remplirEntreSouterraine();
    }

    private void remplirEntreSouterraine() {
        for (int i = 0; i < base; i++) {
            for (int j = 0; j < hauteur; j++) {
                tabEntreSouterraine[i][j] = Case.ESPACE_PLEIN_DE_DEPART;
            }
        }
    }

    private void setBase(int base) {
        if (base > 0) {
            this.base = base;
        }
    }

    private void setHauteur(int hauteur) {
        if (base > 0) {
            this.hauteur = hauteur;
        }

    }

    @Override
    public void ajouterPiece(Piece piece) {
        System.out.println("EntreSouterraine.ajouterPiece()");
        System.out.println("Le nb des pieces placees avec murs disponibles : " + placementsPieces.size());

        // TODO
        /**
         * Premiere etape choisir une piece(PlacementPiece au hasard)
         */
        var randomPlacementPiece = placementsPieces.get(
            random.nextInt(
                placementsPieces.size()
            )
        );
        // randomPlacementPiece = placementsPieces.get(0);
        PlacementPiece placementDeLaPiece = null;

        //2. Choisir un cote au hasard
        var cotesDisponibles =  randomPlacementPiece.getCotesDisponibles();
        Cote cote = Cote.getCoteHasard(cotesDisponibles);
        // cote = Cote.HAUT;
        if (cote == Cote.GAUCHE) {
            System.out.println("GAUCHE");
            //3. Calcul de la position de la position au hasard
            // Si la porte choisi est gauche alors notre x ne bouge que de -1
            // Alors que y bouge de yHautPiece vers yBas
            int x = randomPlacementPiece.getCoordonneePointHautGauchePiece().getX() - 1;
            int y = random.nextInt(
                randomPlacementPiece.getCoordonneePointHautGauchePiece().getY(),
                randomPlacementPiece.getCoordonneePointHautGauchePiece().getY() + 
                    randomPlacementPiece.getPiece().getHauteur() 
            );
            var coordonneePorte = new Coordonnee(x, y);

            var coordonneePointDepartNouvellePiece = new Coordonnee(
                x - piece.getBase(), 
                y + 1 - piece.getHauteur()
            );

            placementDeLaPiece = new PlacementPiece(
                piece, 
                coordonneePointDepartNouvellePiece
            );

            // TODO A enlever
            // System.out.println("EntreSouterraine.ajouterPiece()");
            // System.out.println(placementDeLaPiece);
            
            remplirEntreAvecPiece(placementDeLaPiece);
            try {
                tabEntreSouterraine[x][y] = Case.PORTE_VERTICALE;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CantAddPieceInEntreSouterraineException("La porte n'a pas pu etre ajoute" ,e);
            }
            placementsPieces.add(placementDeLaPiece);

            randomPlacementPiece.setPorteGauche(coordonneePorte);
            placementDeLaPiece.setPorteDroite(coordonneePorte);
            // TODO A enlever
            // System.out.printf("(%d, %d)\n\n", x, y);
        
            //4. Placement de la nouvelle piece
            // Pour placer le bout de la piece juste a cote 
            // de la piece il faut retrancher la base 
            //      (et -1 pour se decaler de la porte) de x et
            // la hauteur de y par rapport aux coordonnes de la porte selectionne

        } else if (cote == Cote.DROITE) {
            System.out.println("DROITE");
            // 3. Calcul de la position de la position au hasard
            // Si la porte choisi est gauche alors notre x ne bouge que de -1
            // Alors que y bouge de yHautPiece vers yBas
            int x = randomPlacementPiece.getCoordonneePointHautGauchePiece().getX() + randomPlacementPiece.getPiece().getBase() ;
            int y = random.nextInt(
                randomPlacementPiece.getCoordonneePointHautGauchePiece().getY(),
                randomPlacementPiece.getCoordonneePointHautGauchePiece().getY() + 
                    randomPlacementPiece.getPiece().getHauteur() 
            );
            var coordonneePorte = new Coordonnee(x, y);

            var coordonneePointDepartNouvellePiece = new Coordonnee(
                x + 1, 
                y + 1 - piece.getHauteur()
            );

            placementDeLaPiece = new PlacementPiece(
                piece, 
                coordonneePointDepartNouvellePiece
            );

            // TODO A enlever
            System.out.println("EntreSouterraine.ajouterPiece()");
            System.out.println(placementDeLaPiece);
            
            remplirEntreAvecPiece(placementDeLaPiece);
            try {
                tabEntreSouterraine[x][y] = Case.PORTE_VERTICALE;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CantAddPieceInEntreSouterraineException("La porte n'a pas pu etre ajoute" ,e);
            }

            placementsPieces.add(placementDeLaPiece);

            randomPlacementPiece.setPorteDroite(coordonneePorte);
            placementDeLaPiece.setPorteGauche(coordonneePorte);
            
        } else if (cote == Cote.BAT) {
            System.out.println("BAT");


            // 3. Calcul de la position de la porte au hasard
            // Si la porte choisi est en bas alors notre x ne bouge que de -1
            // Alors que y bouge de yHautPiece vers yBas
            // int x = randomPlacementPiece.getCoordonneePointHautGauchePiece().getX() + randomPlacementPiece.getPiece().getBase() ;
            // int y = random.nextInt(
            //     randomPlacementPiece.getCoordonneePointHautGauchePiece().getY(),
            //     randomPlacementPiece.getCoordonneePointHautGauchePiece().getY() + 
            //         randomPlacementPiece.getPiece().getHauteur() 
            // );

            // x et y representent les cordonnees de la porte
            int x = random.nextInt(
                randomPlacementPiece.getCoordonneePointBasGauchePiece().getX(),
                randomPlacementPiece.getCoordonneePointBasDroitePiece().getX()
            );
            
            System.out.println("EntreSouterraine.ajouterPiece()");
            System.out.println("Point bas gauche");
            System.out.println(randomPlacementPiece.getCoordonneePointBasGauchePiece());

            System.out.println("point bas droit");
            System.out.println(randomPlacementPiece.getCoordonneePointBasDroitePiece());

            int y = randomPlacementPiece.getCoordonneePointBasGauchePiece().getY() + 1;

            var coordonneePorte = new Coordonnee(x, y);

            var coordonneePointDepartNouvellePiece = new Coordonnee(
                x, 
                y + 1
            );

            placementDeLaPiece = new PlacementPiece(
                piece, 
                coordonneePointDepartNouvellePiece
            );

            // TODO A enlever
            // System.out.println("EntreSouterraine.ajouterPiece()");
            System.out.println(placementDeLaPiece);

            System.out.println("Voici les coordonnes de la porte bas : " + coordonneePorte);

            
            remplirEntreAvecPiece(placementDeLaPiece);
            try {
                tabEntreSouterraine[x][y] = Case.PORTE_HORIZONTALE;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CantAddPieceInEntreSouterraineException("La porte n'a pas pu etre ajoute" ,e);
            }
            placementsPieces.add(placementDeLaPiece);
            
            // System.out.println(this);

            randomPlacementPiece.setPorteBas(coordonneePorte);
            placementDeLaPiece.setPorteHaut(coordonneePorte);

            // tabEntreSouterraine[x][y] = Case.PORTE_VERTICALE;
        } else if (cote == Cote.HAUT) {
            System.out.println("HAUT");

            // 3. Calcul de la position de la porte au hasard
            // Si la porte choisi est en bas alors notre x ne bouge que de -1
            // Alors que y bouge de yHautPiece vers yBas
            // int x = randomPlacementPiece.getCoordonneePointHautGauchePiece().getX() + randomPlacementPiece.getPiece().getBase() ;
            // int y = random.nextInt(
            //     randomPlacementPiece.getCoordonneePointHautGauchePiece().getY(),
            //     randomPlacementPiece.getCoordonneePointHautGauchePiece().getY() + 
            //         randomPlacementPiece.getPiece().getHauteur() 
            // );

            // x et y representent les cordonnees de la porte
            int x = random.nextInt(
                randomPlacementPiece.getCoordonneePointBasGauchePiece().getX(),
                randomPlacementPiece.getCoordonneePointBasDroitePiece().getX()
            );
            int y = randomPlacementPiece.getCoordonneePointHautGauchePiece().getY() - 1;

            var coordonneePorte = new Coordonnee(x, y);

            var coordonneePointDepartNouvellePiece = new Coordonnee(
                x, 
                y - piece.getHauteur()
            );

            placementDeLaPiece = new PlacementPiece(
                piece, 
                coordonneePointDepartNouvellePiece
            );

            // TODO A enlever
            // System.out.println("EntreSouterraine.ajouterPiece()");
            System.out.println(placementDeLaPiece);

            
            
            System.out.println("Voici les coordonnes de la porte bas : " + coordonneePorte);
            
            remplirEntreAvecPiece(placementDeLaPiece);
            try {
                tabEntreSouterraine[x][y] = Case.PORTE_HORIZONTALE;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CantAddPieceInEntreSouterraineException("La porte n'a pas pu etre ajoute" ,e);
            }
            placementsPieces.add(placementDeLaPiece);
            
            // System.out.println(this);
            randomPlacementPiece.setPorteHaut(coordonneePorte);
            placementDeLaPiece.setPorteBas(coordonneePorte);

            

        }
        
        if ( !randomPlacementPiece.aMursDisponible()) {
            System.out.println("Une piece est retiree!!");
            placementsPieces.remove(randomPlacementPiece);
        }

        if (placementDeLaPiece != null) {
            entourerPlacementPiece(placementDeLaPiece);
        }

        // TODO Auto-generated method stub

    }

    private void entourerPlacementPiece(PlacementPiece placementPiece) {
        for (int x = placementPiece.getCoordonneePointHautGauchePiece().getX() - 1; x < placementPiece.getCoordonneePointHautDroitePiece().getX() + 2; x++) {
            int yHaut = placementPiece.getCoordonneePointHautGauchePiece().getY() - 1;
            int yBas = placementPiece.getCoordonneePointBasGauchePiece().getY() + 1;

            if (
                (yHaut >= 0 && yHaut < hauteur) &&
                (x >= 0 && x < base)
            ) {
                if (tabEntreSouterraine[x][yHaut] == Case.ESPACE_PLEIN_DE_DEPART) {
                    tabEntreSouterraine[x][yHaut] = Case.ESPACE_PLEIN;
                }
            }

            if (
                (yBas < hauteur && yBas >= 0) &&
                (x >= 0 && x < base)
            ) {
                if (tabEntreSouterraine[x][yBas] == Case.ESPACE_PLEIN_DE_DEPART) {
                    tabEntreSouterraine[x][yBas] = Case.ESPACE_PLEIN;
                }
            }
        }

        for (int y = placementPiece.getCoordonneePointHautGauchePiece().getY(); y < placementPiece.getCoordonneePointBasGauchePiece().getY() + 1; y++) {
            int xGauche = placementPiece.getCoordonneePointHautGauchePiece().getX() - 1;
            int xDroite = placementPiece.getCoordonneePointHautDroitePiece().getX() + 1;

            if (
                (
                    (xGauche >= 0 && xGauche < base) &&
                    (y >= 0 && y < hauteur)

                ) &&
                tabEntreSouterraine[xGauche][y] == Case.ESPACE_PLEIN_DE_DEPART
            ) {
                tabEntreSouterraine[xGauche][y] = Case.ESPACE_PLEIN;
            }

            if (
                (
                    (xDroite < base && xDroite >= 0) &&
                    (y >= 0 && y < hauteur)
                ) &&
                tabEntreSouterraine[xDroite][y] == Case.ESPACE_PLEIN_DE_DEPART
            ) {
                tabEntreSouterraine[xDroite][y] = Case.ESPACE_PLEIN;
            }

        }
    } 

    @Override
    public void ajouterPieceCentrale(Piece piece) {
        // if (estPieceCentraleAjoutee()) {
        //     throw new RuntimeException("La piece centrale n'est pas ajoute");
        // }
        /**
         * Pour trouver la position de la piece centrale
         * je vais faire la soustraction de la taille de l'entre et de la piece qu'on vient de nous donner
         * et je divise le resultat par 2
         * Genre
         */
        if (piece.getBase() > base || piece.getHauteur() > hauteur) {
            System.out.println("La piece centrale est plus grande que la taille de l'entre");
            // TODO cas a gerer
        }

        int debutBaseDansEntre = (base - piece.getBase()) / 2;
        int finBaseDansEntre = debutBaseDansEntre + piece.getBase();
        int debutHauteurDansEntre = (hauteur - piece.getHauteur()) / 2;
        int finHauteurDansEntre = debutHauteurDansEntre + piece.getHauteur();

        // Placement piece nous permet de suivre les pieces qui
        // qui sont deja ajoutes dans l'entre
        PlacementPiece placementPiece = new PlacementPiece(piece, new Coordonnee(debutBaseDansEntre, debutHauteurDansEntre));
        placementsPieces.add(placementPiece);

        for (int i = debutBaseDansEntre; i < finBaseDansEntre; i++) {
            for (int j = debutHauteurDansEntre; j < finHauteurDansEntre; j++) {
                tabEntreSouterraine[i][j] = Case.ESPACE_VIDE;
            }
        }


        // TODO Implementation piece centrale
        entourerPlacementPiece(placementPiece);
        estPieceCentraleAjoutee = true;
    }

    private void remplirEntreAvecPiece(PlacementPiece placementPiece) {
        // System.out.println(
        //     placementPiece.getCoordonneePointHautGauchePiece()
        // );
        if ( !peutRemplirPiece(placementPiece)) {
            throw new CantAddPieceInEntreSouterraineException("La piece ne peut etre ajoutee");
        }
        
        System.out.println("EntreSouterraine.remplirEntreAvecPiece()");
        System.out.println(placementPiece);
        for (int i = placementPiece.getCoordonneePointHautGauchePiece().getX(); i < placementPiece.getCoordonneePointHautDroitePiece().getX() + 1; i++) {
            for (int j = placementPiece.getCoordonneePointHautGauchePiece().getY(); j < placementPiece.getCoordonneePointBasGauchePiece().getY() + 1; j++) {
                // System.out.println("EntreSouterraine.remplirEntreAvecPiece()");
                System.out.println("x = " + i + " y = " + j);
                tabEntreSouterraine[i][j] = Case.ESPACE_VIDE;
            }
        }
    }

    private boolean peutRemplirPiece(PlacementPiece placementPiece) {
        // TODO Implementer la methode de validation du
        // du remplissage de la piece
        // On va boucler sur toute la zone ou on devrait ajouter la piece
        // Si on trouve sur l'une des cases un element qui est != de Cote.ESPACE_PLEIN
        // On doit retourner false sinon on retourne vrai;
        boolean peutEtreRempli = true;

        // Avoir une exception du type ArrayIndexOutOfBoundsException
        // revient a dire que la piece n'entre pas
        try {
            for (int x = placementPiece.getCoordonneePointHautGauchePiece().getX(); x < placementPiece.getCoordonneePointHautDroitePiece().getX() + 1 && peutEtreRempli; x++) {
                for (int y = placementPiece.getCoordonneePointHautGauchePiece().getY(); y < placementPiece.getCoordonneePointBasGauchePiece().getY() + 1 && peutEtreRempli; y++) {
                    if (
                        tabEntreSouterraine[x][y] != Case.ESPACE_PLEIN_DE_DEPART
                    ) {
                        peutEtreRempli = false;
                        // System.out.println("EntreSouterraine.peutRemplirPiece()");
                        // System.out.println("i = " + i + " j = " + j);
                    }
                }
            }
    
        } catch (ArrayIndexOutOfBoundsException e) {
            peutEtreRempli = false;
        }
        return peutEtreRempli;
        // return peutEtreRempli;
    }

    @Override
    public boolean estPieceCentraleAjoutee() {
        return estPieceCentraleAjoutee;
    }

    @Override
    public String toString() {
        String entreAsText = "";
        for (int j = 0; j < hauteur; j++) {
            for (int i = 0; i < base; i++) {
                entreAsText += tabEntreSouterraine[i][j] + " ";
            }
            entreAsText += "\n";
        }

        return entreAsText;
    }


}
