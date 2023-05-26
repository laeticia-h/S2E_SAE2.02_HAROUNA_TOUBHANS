package ressources;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

/**
 * Classe GrapheListe permettant de créer un graphe sous forme de liste
 */
public class GrapheListe implements Graphe {

    // Attributs
    private ArrayList<String> ensNom;
    private ArrayList<Noeud> ensNoeuds;

    /**
     * Constructeur par défaut
     */
    public GrapheListe() {

        ensNom = new ArrayList<>();
        ensNoeuds = new ArrayList<>();

    }


    /**
     * Constructeur avec paramètre
     *
     * @param fichier nom du fichier
     */

    public GrapheListe(String fichier) {
        ensNom = new ArrayList<>();
        ensNoeuds = new ArrayList<>();

        try {

            String ligne = "";
            BufferedReader br = new BufferedReader(new FileReader(fichier));

            while ((ligne = br.readLine()) != null) {

                String[] elements = ligne.split("\t");
                String depart = elements[0];
                String destination = elements[1];
                double cout = Double.parseDouble(elements[2]);

                ajouterArc(depart, destination, cout);

            }

        } catch (IOException e) {
            throw new Error("Input / Output exception");
        }
    }

    /**
     * Méthode permettant d'ajouter un arc
     *
     * @param depart
     * @param destination
     * @param cout
     */
    public void ajouterArc(String depart, String destination, double cout) {

        boolean existant = false;
        boolean dest = false;

        for (Noeud noeud : ensNoeuds) {

            if (destination.equals(noeud.getNom())) dest = true;
            if (depart.equals(noeud.getNom())) {

                noeud.ajouterArc(destination, cout);
                existant = true;

            }
        }

        if (!dest) {

            this.ensNom.add(destination);
            this.ensNoeuds.add(new Noeud(destination));

        }

        if (!existant) {

            this.ensNom.add(depart);
            Noeud n = new Noeud(depart);
            n.ajouterArc(destination, cout);
            this.ensNoeuds.add(n);

        }
    }


    public ArrayList<String> listeNoeuds() {

        return this.ensNom;

    }

    /**
     * Méthode permettant de récupérer les noeuds suivants
     *
     * @param n nom du noeud
     * @return les noeuds suivants
     */
    public ArrayList<Arc> suivants(String n) {

        for (Noeud noeud : ensNoeuds) {

            if (n.equals(noeud.getNom())) return noeud.getArc();

        }

        return null;

    }

    /**
     * Méthode permettant d'afficher le graphe
     *
     * @return le graphe
     */

    public String toString() {

        String res = "";

        for (Noeud noeud : ensNoeuds) {

            res += noeud.getNom() + " -> ";

            for (Arc arc : noeud.getArc()) {

                res += arc.getDestination() + "(" + Math.round(arc.getCout()) + ") ";

            }

            res += "\n";
        }

        return res;

    }

    /**
     * Méthode permettant de générer un fichier GraphViz
     *
     * @return le fichier GraphViz
     */
    public String toGraphViz() {

        String res = "digraph G {\n";

        for (Noeud noeud : ensNoeuds) {

            for (Arc arc : noeud.getArc()) {

                res += noeud.getNom() + " -> " + arc.getDestination() + " [label = " + Math.round(arc.getCout()) + "]\n";

            }
        }

        return res + "}";

    }


    public static void convertirMatriceEnListe(String fichierEntree, String fichierSortie) {

        try {

            String ligne = "";
            String[] noms = null;
            int indexLigne = 0;
            BufferedReader br = new BufferedReader(new FileReader(fichierEntree));
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichierSortie));

            while ((ligne = br.readLine()) != null) {

                if (indexLigne == 0) {
                    noms = ligne.split("\t");

                } else {

                    String[] valeurs = ligne.split("\t");
                    String depart = noms[indexLigne - 1];

                    for (int i = 1; i < valeurs.length; i++) {

                        if (!valeurs[i].equals("0.")) {

                            String destination = noms[i - 1];
                            double cout = Double.parseDouble(valeurs[i]);
                            String arc = depart + "\t" + destination + "\t" + Math.round(cout);
                            bw.write(arc);
                            bw.newLine();

                        }
                    }
                }
                indexLigne++;
            }

            br.close();
            bw.close();

        } catch (IOException e) {
            throw new Error("Erreur d'input / output");
        }
    }


    public void setNom(String a) {
        this.ensNom.add(a);
    }

    public List<Noeud> getEnsNoeuds() {
        return this.ensNoeuds;
    }


    /**
     * Verifie si un noeud est le successeur d'un arc du graphe
     *
     * @param noeudP noeud a trouver comme successeur
     * @return booleen representant l'etat de successeur du noeud
     */
    private boolean arcContient(String noeudP) {
        for (Noeud n : this.ensNoeuds)
            for (Arc a : n.getArc())
                if (a.getDestination().equals(noeudP))
                    return true;
        return false;
    }

    /**
     * Teste si un noeud a un attribut arc qui est vide
     *
     * @param noeudP noeud a tester
     * @return si le noeud possede des arcs
     */
    private boolean arcVide(String noeudP) {
        return suivants(noeudP).size() == 0;
    }

    /**
     * Teste si un noeud est deja relie a un autre
     *
     * @param nomNoeudDepartP  noeud de depart
     * @param nomNoeudArriveeP noeud d'arrivee
     * @return si l'arc existe deja
     */
    private boolean relie(String nomNoeudDepartP, String nomNoeudArriveeP) {
        for (Noeud n : this.ensNoeuds)
            if (n.getNom().equals(nomNoeudDepartP))
                for (Arc a : n.getArc())
                    if (a.getDestination().equals(nomNoeudArriveeP))
                        return true;
        return false;
    }

    /**
     * Verifie si un noeud identique (meme nom) existe deja dans le graphe. Si c'est un nouveau
     * noeud, la methode ajoute le nom du noeud dans ensNom, cree un objet noeud correspondant
     * et l'ajoute dans ensNoeuds.
     *
     * @param noeudP nom du noeud a ajouter
     */
    private void ajouterNoeud(String noeudP) {
        boolean present = false;

        for (String nL : ensNom)
            if (nL.equals(noeudP)) {
                present = true;
                break;
            }

        if (!present) {
            ensNom.add(noeudP);
            Noeud n = new Noeud(noeudP);
            ensNoeuds.add(n);
        }
    }

    /**
     * Genere un graphe aleatoire de taille donnee
     *
     * @param taille taille du graphe
     */
    public void genererGraphe(int taille) {
        for (int i = 1; i <= taille; i++)
            this.ajouterNoeud(String.valueOf(i));

        if (taille == 1) {
            return;
        }

        boolean grapheFini = false;
        while (!grapheFini) {
            grapheFini = true;

            int coutArc = (int) Math.round(Math.random() * 100);
            String noeudDepart, noeudArrivee;

            noeudDepart = String.valueOf(Math.round(Math.random() * (taille - 1)) + 1);
            noeudArrivee = String.valueOf(Math.round(Math.random() * (taille - 1)) + 1);

            while (relie(noeudDepart, noeudArrivee) || noeudDepart.equals(noeudArrivee)) {
                noeudDepart = String.valueOf(Math.round(Math.random() * (taille - 1)) + 1);
                noeudArrivee = String.valueOf(Math.round(Math.random() * (taille - 1)) + 1);
            }

            this.ajouterArc(noeudDepart, noeudArrivee, coutArc); //Cree l'arc

            //La boucle se relance tant que tous les noeuds ne sont pas antecedents ou successeurs d'un autre noeud
            boolean grapheNonFini = false;
            for (int i = 1; i <= taille; i++) {
                if (arcVide(String.valueOf(i)) || !arcContient(String.valueOf(i))) {
                    grapheNonFini = true;
                    break;
                }
            }
            grapheFini = !grapheNonFini;
        }
    }
}

