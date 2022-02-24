package Logica;

import java.util.*;

public class Tiquet {
    // atribut
    private List<List<Article>> llistaCompres;
    
    // constructor privat --> Singleton
    public static Tiquet instancia;
    
    private Tiquet() {
        llistaCompres = new ArrayList();
    }
    
    public static Tiquet getInstancia() {
        if (instancia == null) {
            instancia = new Tiquet();
        }
        return instancia;
    }
    
    // getters i setters
    public List<List<Article>> getLlistaCompres() {
        return llistaCompres;
    }

    public void setLlistaCompres(List<List<Article>> compres) {
        this.llistaCompres = compres;
    }
    
    /**
     * AFEGIR COMPRA A LA LLISTA DE TIQUET
     * @param compra 
     */
    public void afegirCompraALlista(List<Article> compra) {
        llistaCompres.add(compra);
    }

    @Override
    public String toString() {
        String retorn = "";

        for (int i = 0; i < this.llistaCompres.size(); i++) {
            retorn += "COMPRA:\n";
            for (Article article : llistaCompres.get(i)) {
                retorn += "\t" + article.toString() + "\n";
            }
        }
        return retorn;
    }
}
