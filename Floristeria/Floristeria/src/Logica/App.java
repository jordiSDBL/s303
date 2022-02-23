package Logica;

import Logica.Article_Decoracio.Material;
import Persistencia.ControladoraText;
import Utilities.Teclat;
import java.io.*;
import java.util.*;
import java.util.logging.*;

public class App {
    
    public static void main(String[] args) throws IOException {
        Floristeria floristeria = Floristeria.getInstancia(); // inicia la instància de Floristeria
        Tiquet tiquet = Tiquet.getInstancia(); // inicia la instància de Tiquet
        ControladoraText controlTxt = null;

        boolean sortir = false;

        do {
            switch (menu()) {
                case 1:
                    // si no s'ha nombrat la instància de Floristeria...
                    if (floristeria.getNom().equals("")) {
                        String nom = Teclat.leerString("Quin nom té la Floristeria?\n");
                        floristeria.setNom(nom);
                        System.out.println("S'està treballant amb la floristeria '" + floristeria.getNom() + "'.");
                        controlTxt = new ControladoraText(); // controladora de persistència .txt
                        // si teníem estoc, carregar-ho al programa
                        if(controlTxt.getArxiuEstoc().exists()){
                            controlTxt.abocarEstocDesdeTxt();
                        }
                        // si teníem vendes antigues, carregar-ho al programa
                        if(controlTxt.getArxiuTiquets().exists()){
                            controlTxt.abocarVendesDesdeTxt();
                        } 
                    }
                    // si ja s'ha posat nom a la instància de Floristeria...
                    else {
                        System.out.println("Ja hi ha una floristeria creada. El seu nom és '" + floristeria.getNom() + "'.");
                    }
                    break;
                case 2:
                    Article arbre;
                    System.out.println("**Especificacions**");
                    double alcada = Teclat.leerDouble("Alçada:\n");
                    double preu = Teclat.leerDouble("Preu:\n");
                    // es crea l'objecte i s'afegeix a l'estoc i al txt
                    arbre = new Article_Arbre(alcada, preu);
                    floristeria.afegirArticle(arbre);
                    controlTxt.afegirArticleTxt(arbre);
                    break;
                case 3:
                    Article flor;
                    System.out.println("**Especificacions**");
                    String color = Teclat.leerString("Color:\n");
                    preu = Teclat.leerDouble("Preu:\n");
                    // es crea l'objecte i s'afegeix a l'estoc i al txt
                    flor = new Article_Flors(color, preu);
                    floristeria.afegirArticle(flor);
                    controlTxt.afegirArticleTxt(flor);
                    break;
                case 4:
                    Article deco;
                    System.out.println("**Especificacions**");
                    int material = Teclat.leerInt("De quin material és la decoració?\n"
                            + "1. Fusta\n"
                            + "2. Plàstic\n");
                    preu = Teclat.leerDouble("Quin preu té l'article?\n");
                    // es crea l'objecte i s'afegeix a l'estoc i al txt
                    if (material == 1) {
                        deco = new Article_Decoracio(Material.FUSTA, preu);
                    } else {
                        deco = new Article_Decoracio(Material.PLASTIC, preu);
                    }
                    floristeria.afegirArticle(deco);
                    controlTxt.afegirArticleTxt(deco);
                    break;
                case 5:
                    floristeria.mostrarEstoc();
                    break;
                case 6,7,8:
                    String id = Teclat.leerString("Quin és l'id de l'article a retirar?\n");
                    floristeria.retirarArticle(id); // l'esborrem del programa
                        try {
                            controlTxt.retirarArticleTxt(id); // i l'esborrem de la persistència
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    break;
                case 9:
                    floristeria.mostraEstocQtt();
                    break;
                case 10:
                    double valor;
                    valor = floristeria.mostrarValorEstoc();
                    System.out.println("El valor de l'estoc actual és de: " + valor + " euros.");
                    break;
                case 11:
                    List<Article> compra = new ArrayList();
                    boolean trobat = false;
                    do {
                        String idArt = Teclat.leerString("Introdueix l'id de l'article:\n");
                        for (Article art : floristeria.getEstoc()) {
                            if (art.getIdArticle().equalsIgnoreCase(idArt)) {
                                compra.add(art); // si està en estoc, s'afegeix a la compra
                                System.out.println("L'article amb id " + idArt + " s'ha afegit a la compra.");
                                trobat = true;
                            }
                        }
                        if (trobat) { //si s'ha afegit a la compra, retirar de l'estoc
                            floristeria.retirarArticle(idArt);
                            trobat = false;
                        } else {
                            System.out.println("No s'ha trobat l'article a l'estoc de la floristeria.");
                        }
                        sortir = Teclat.leerSiNo("Vols afegir més articles? (S/N)\n");
                    } while (sortir);
                    // desem la compra, persistim i netegem
                    tiquet.afegirCompraALlista(compra);
                    controlTxt.desartiquet(compra);
                    compra = null;
                    break;
                case 12:
                    if(tiquet.toString().isEmpty()){
                        System.out.println("Encara no hi ha vendes registrades.");
                    } else {
                      System.out.println(tiquet.toString());  
                    }
                    break;
                case 13:
                    double guanysTotals = 0;
                    
                    if(tiquet.toString().isEmpty()){
                        System.out.println("Encara no hi ha vendes registrades.");
                    } else {
                      for (int i = 0; i < tiquet.getLlistaCompres().size(); i++) {
                        for (int j = 0; j < tiquet.getLlistaCompres().get(i).size(); j++) {
                            guanysTotals += tiquet.getLlistaCompres().get(i).get(j).getPreu();
                        }
                    }
                    System.out.println("S'han facturat " + guanysTotals + " euros en total."); 
                    }
                    break;
                case 0:
                    System.out.println("Gràcies per utilitzar l'aplicació");
                    sortir = true;
                    break;
            }
        } while (!sortir);
    }

    public static int menu() {
        Scanner entrada = new Scanner(System.in);
        int opcio;
        final int MINIM = 0;
        final int MAXIM = 13;

        do {
            System.out.println("\nMENÚ PRINCIPAL");
            System.out.println("1.  Crear/accedir floristeria.");
            System.out.println("-----------------------------");
            System.out.println("2.  Afegir Arbre.");
            System.out.println("3.  Afegir Flor.");
            System.out.println("4.  Afegir Decoració.");
            System.out.println("-----------------------------");
            System.out.println("5.  Mostra l'estoc actual.");
            System.out.println("-----------------------------");
            System.out.println("6.  Retirar Arbre.");
            System.out.println("7.  Retirar Flor.");
            System.out.println("8.  Retirar Decoració.");
            System.out.println("-----------------------------");
            System.out.println("9.  Mostra l'estoc per quantitats.");
            System.out.println("10. Mostra valor total floristeria.");
            System.out.println("-----------------------------");
            System.out.println("11. Crear un tiquet de compra.");
            System.out.println("12. Mostra llista de compres antigues.");
            System.out.println("13. Diners guanyats en totes les vendes.");
            System.out.println("-----------------------------");
            System.out.println("0. Sortir de l'aplicació.\n");
            opcio = entrada.nextInt();
            if (opcio < MINIM || opcio > MAXIM) {
                System.out.println("Escull una opció vàlida");
            } else if (Floristeria.getInstancia().getNom().isEmpty() && opcio != 1 && opcio != 0) {
                System.out.println("Primer crea una floristeria amb què treballar.");
                return -1;
            }
        } while (opcio < MINIM || opcio > MAXIM);
        return opcio;
    }
}
