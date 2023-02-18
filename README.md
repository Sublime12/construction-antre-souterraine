# TP 01 ANTRE SOUTERRAINE
Ce programme est fait par les etudiants :  

    - Antonio, OGUNA CHUKWUDI (OGUC74290400) et
    - Sublime, TSHIMPANGILA TSHIMANGA (TSHS91260100)

Jeu de creation d'antre souterraine en Java
Le but du jeu est de placer des pieces dans une 
carte (qu'on va appeler ici antre `souterraine`)

Pour cela on a eu a creer des classes (classe englobe ici classe, interface et enum) pour nous permettre d'avoir un programme plus structure

Quelques uns sont `AntreSouterraine`, `Piece`, `PlacementPiece` et `Coordonnee`.
    
- `AntreSouterraine` : Son role est de permettre d'ajouter plusieurs pieces dans une antre (typiquement dans un tableau 2D), elle a une methode `ajouterPieceCentrale(...)` qui va permettre de placer la premiere piece au centre. elle contient aussi les methodes `ajouterPiece(...)` et `estPieceCentraleAjoutee()`.

- `Piece` : Classe qui contient la base et la hauteur de la piece

- `PlacementPiece` : Qui ajoute a une `Piece` les differentes coordonnees lorsqu'elle est placee

- `Coordonnee` : Elle encapsule les differentes coordonnees qu'on pourrait avoir dans le programme

- `ExceptionHandler` : Gestionnaire d'exception qui permet d'afficher les differents
messages d'erreur et de sauvegarder les erreurs dans un fichier errors.log (qui correspond a la constante ExceptionHandler.ERRORS_LOG_FILE)

- `Differentes exceptions` : Qui permettent d'exprimer les differentes erreurs(situations exceptionnelles) que l'on peut avoir dans le code

- `PlacageServiceInterface` : Interface de base pour differentes implementations de placement des pieces par rapport aux differents cotes (Enum `Cote`) qui existent.

- `Differents services` : Qui permettent de creer les objects de facon centralisee pour notre programme. 


###### Ce qui suit peut etre ignore, elle a servit d'etapes globales lors de la conception du programme

### Etapes globales a suivre lors de l'ecriture du programme
1) saisi du fichier
2) choix aleatoire de la piece a placer par rapport a la porte
3) deplacement d'une piece
4) condition d'arret du programme