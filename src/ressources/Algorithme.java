package ressources;

/**
 * Interface Algorithme permettant de définir les méthodes communes aux deux algorithmes.
 */
public interface Algorithme {
    public Valeur resoudre(Graphe g, String depart);
}
