package Logica;

import java.util.*;

public class Floristeria {
    // atributs
    private String nom = "";
    private List<Article> estoc;

    // constructor privat --> Singleton
    public static Floristeria instancia;
    
    private Floristeria() {
        this.estoc = new ArrayList<>();
    }
    
    public static Floristeria getInstancia() {
        if (instancia == null) {
            instancia = new Floristeria();
        }
        return instancia;
    }

    // getters i setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Article> getEstoc() {
        return estoc;
    }

    public void setEstoc(List<Article> estoc) {
        this.estoc = estoc;
    }

    /**
     * CREA UNA LLISTA PER A CADA TIPUS D'ARTICLE
     * Floristeria.mostrarEstoc() fa servir aquest mètode.
     * @return List[]<Article>
     */
    public List[] classificarCategories(){
        List<Article> arbres = new ArrayList<>();
        List<Article> flors = new ArrayList<>();
        List<Article> decoracions = new ArrayList<>();

        for (Article art : this.estoc) {
            if (art instanceof Article_Arbre) {
                arbres.add(art);
            } else if (art instanceof Article_Flors) {
                flors.add(art);
            } else {
                decoracions.add(art);
            }
        }
        
        List<Article>[] artPerCateg = new List[3];
        artPerCateg[0] = arbres;
        artPerCateg[1] = flors;
        artPerCateg[2] = decoracions;
        
        return artPerCateg;
    }

    /**
     * MOSTRA L'ESTOC PER CONSOLA
     * Rep l'estoc distribuit en llistes segons el tipus gràcies a Floristeria.classificarCategories().
     * N'imprimeix el contingut distribuit en categories.
     * 
     */
    public void mostrarEstoc() {
        List<Article>[] categ = classificarCategories();
        String buit = "sense estoc.";

        System.out.println("ARBRES: ");
        if (categ[0].isEmpty()) {
            System.out.println("\t" + buit);
        } else {
            for (Article arbre : categ[0]) {
                System.out.println("\t" + arbre.toString());
            }
        }
        System.out.println("FLORS: ");
        if (categ[1].isEmpty()) {
            System.out.println("\t" + buit);
        } else {
            for (Article flor : categ[1]) {
                System.out.println("\t" + flor.toString());
            }
        }
        System.out.println("DECORACIÓ: ");
        if (categ[2].isEmpty()) {
            System.out.println("\t" + buit);
        } else {
            for (Article deco : categ[2]) {
                System.out.println("\t" + deco.toString());
            }
        }
    }
    
    /**
     * MOSTRA QUANTES UNITATS HI HA PER TIPUS D'ARTICLE
     * Rep l'estoc distribuit en llistes segons tipus gràcies a Floristeria.classificarCategories().
     * S'itera cada llista amb un autoincrementat, que es neteja al final de cada interació.
     * 
     */
    public void mostraEstocQtt(){
        List<Article>[] categ = classificarCategories();
        
        int qtt = 0;
        System.out.print("ARBRES: ");
        for (Article arbre : categ[0]) {
            qtt++;
        }
        System.out.println(qtt + " unitats.");
        qtt=0;
        
        System.out.print("FLORS: ");
        for (Article flor : categ[1]) {
            qtt++;
        }
        System.out.println(qtt + " unitats.");
        qtt=0;
        
        System.out.print("DECORACIÓ: ");
        for (Article deco : categ[2]) {
            qtt++;
        }
        System.out.println(qtt + " unitats.");
        qtt=0;
    }
    
    /**
     * MOSTRA EL VALOR TOTAL DE L'ESTOC
     * @return double
     */
    public double mostrarValorEstoc() {
        double preu = 0;
        for (Article article : estoc) {
            preu += article.getPreu();
        }
        return preu;
    }

    /**
     * AFEGEIX ARTÍCLE A L'ESTOC
     * @param article 
     */
    public void afegirArticle(Article article) {
        this.estoc.add(article);
    }

    /**
     * RETIRA L'ARTICLE DE L'ESTOC
     * Recórra l'estoc buscant un id que coincideixi amb el paràmetre,
     * i si troba coincidència esborra l'article d'aquella posició
     * @param id 
     */
    public void retirarArticle(String id) {
        boolean trobat = false;
        
        for (int i = 0; i < this.estoc.size(); i++) {
            if (estoc.get(i).getIdArticle().equalsIgnoreCase(id)) {
                this.estoc.remove(i);
                trobat = true;
            }
        }
        if(!trobat){
            System.out.println("Aquest article no es troba en estoc.");
        }
    }

    @Override
    public String toString() {
        return "Floristeria{" + "nom=" + nom + ", estoc=" + estoc + '}';
    }
}
