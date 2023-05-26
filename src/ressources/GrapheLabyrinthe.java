package ressources;

import laby.Labyrinthe;

import java.util.ArrayList;

/**
 * Class GrapheLabyrinthe correspondant à un adaptateur entre un graphe et un labyrinthe.
 */
public class GrapheLabyrinthe implements Graphe{

    
    private Labyrinthe laby;

/**
 * Constructeur de l'adaptateur GrapheLabyrinthe
 * @param lab Labyrinthe qui sera adapté en graphe.
 */
public GrapheLabyrinthe(Labyrinthe lab){

    this.laby = lab;

}

/**
 * Méthode permettant de récupérer tous les noeuds du labyrinthe?
 * @return List de string correspondant aux noeuds du labyrinthe.
 */
public ArrayList<String> listeNoeuds(){

    ArrayList<String> liste = new ArrayList<String>();

    //On parcourt le labyrinthe et pour chaque coordonnées on insère un noeud correspondant dans la liste.
    for(int i = 0; i < laby.getLength(); i++){
        for(int j = 0; j < laby.getLengthY(); j++){

            String nom = "["+i+"/"+j+"]";
            liste.add(nom);
        }
    }
    // On retourne la liste.
    return liste;
}

/**
 * Permet de récupérer les arcs depuis le nom d'un noeud pour ce noeud.
 * @return List correspondant aux arcs possible depuis un noeud (par son nom) dans un labyrinthe.
 */
public ArrayList<Arc> suivants(String n){

    ArrayList<Arc> liste = new ArrayList<>();

    //On récupère les coordonnées depuis le nom du noeud.
    String[] noeud = n.substring(1,4).split("/");
    int i = Integer.parseInt(noeud[0]);
    int j = Integer.parseInt(noeud[1]);

    //On récupère les différents mouvements possible afin d'itérer dessus.
    String[] Mouvement = {Labyrinthe.HAUT, Labyrinthe.BAS, Labyrinthe.GAUCHE, Labyrinthe.DROITE};

    //On regarde pour chaque mouvement si un arc est possible.
    for (String mouv : Mouvement) {

        int[] mouvpos = Labyrinthe.getSuivant(i, j, mouv);
        int xmouv = mouvpos[0];
        int ymouv = mouvpos[1];

        //Si le mouvement est possible, on créer un arc et on l'ajoute à la liste des arcs.
        if (xmouv >= 0 && ymouv >= 0 && xmouv < laby.getLength() && ymouv < laby.getLengthY() && !laby.getMur(xmouv, ymouv)) {

            String mouvnoeud = "[" + xmouv + "/" + ymouv + "]";
            Arc arc = new Arc(mouvnoeud, 1);
            liste.add(arc);
        }

    }

    //On retourne la liste des arcs possible.
    return liste;

}

}