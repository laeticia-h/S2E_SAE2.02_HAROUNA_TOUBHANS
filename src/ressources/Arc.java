package ressources;

import java.util.*;

/**
 * Classe Arc permettant de créer un arc
 */
public class Arc{

//attributs
private String dest;
private double cout;


    /**
     * Constructeur de la classe Arc
     * @param destination nom du noeud de destination
     * @param c cout de l'arc
     */
    public Arc(String destination, double c){

    dest = destination;
    if(c < 0)c = 0;
    cout = c;

}

    /**
     * Retourne le nom du noeud de destination
     * @return nom du noeud de destination
     */
    public String getDestination(){
    return this.dest;
}

    /**
     * Retourne le cout de l'arc
     * @return cout de l'arc
     */
    public double getCout(){
    return this.cout;
}

}