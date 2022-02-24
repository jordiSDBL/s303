package Persistencia;

import Logica.*;
import java.io.*;
import java.util.*;

public class ControladoraText {
    // atributs
    String nomFloristeria;
    String nomArxiuEstoc, nomArxiuTiquets;
    File arxiuEstoc, arxiuTiquets;

    // constructors
    public ControladoraText() {
        nomFloristeria = Floristeria.getInstancia().getNom();
        nomArxiuEstoc = nomFloristeria.concat(".persistenciaEstoc.txt");
        arxiuEstoc = new File(nomArxiuEstoc);
        nomArxiuTiquets = nomFloristeria.concat(".persistenciaTiquets.txt");
        arxiuTiquets = new File(nomArxiuTiquets);
    }

    // getters i setters
    public String getNomFloristeria() {
        return nomFloristeria;
    }

    public void setNomFloristeria(String nomFloristeria) {
        this.nomFloristeria = nomFloristeria;
    }

    public String getNomArxiuEstoc() {
        return nomArxiuEstoc;
    }

    public void setNomArxiuEstoc(String nomArxiuEstoc) {
        this.nomArxiuEstoc = nomArxiuEstoc;
    }

    public File getArxiuEstoc() {
        return arxiuEstoc;
    }

    public void setArxiuEstoc(File arxiuEstoc) {
        this.arxiuEstoc = arxiuEstoc;
    }

    public String getNomArxiuTiquets() {
        return nomArxiuTiquets;
    }

    public void setNomArxiuTiquets(String nomArxiuTiquets) {
        this.nomArxiuTiquets = nomArxiuTiquets;
    }

    public File getArxiuTiquets() {
        return arxiuTiquets;
    }

    public void setArxiuTiquets(File arxiuTiquets) {
        this.arxiuTiquets = arxiuTiquets;
    }
    
    
    /**
     * MÈTODE QUE LLEGIRÀ ARTICLES DEL TXT I ELS PASSARÀ AL PROGRAMA
     * Fent servir el començament del toString dels articles anirà
     * destriant la informació per recrear objectes del tipus que 
     * correspongui.
     */
    public void abocarEstocDesdeTxt(){
        Article arbre;
        Article flor;
        Article deco;
        /**
         * Texts del toString dels articles que serviran
         * per trobar les dades necssàries.
         */
        String buscaAlcada = "alçada: ";
        String buscaPreu = "preu: ";
        String buscaColor = "color: ";
        String buscaMaterial = "material: ";
        String buscaIdArbre = "id. A";
        String buscaIdFlor = "id. F";
        String buscaIdDeco = "id. D";
        
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(this.arxiuEstoc));
            String lectura = entrada.readLine();

            while (lectura != null) {
                int inici, fi;

                if (lectura.startsWith(buscaIdArbre)) {
                    //id
                    inici = lectura.indexOf(buscaIdArbre);
                    fi = lectura.indexOf(",", inici);
                    int id = Integer.valueOf(lectura.substring(inici + buscaIdArbre.length(), fi));
                    Article.id = id;
                    // alçada
                    inici = lectura.indexOf(buscaAlcada);
                    fi = lectura.indexOf(",", inici);
                    String alcadaStr = lectura.substring(inici + buscaAlcada.length(), fi);
                    double alcada = Double.valueOf(alcadaStr);
                    // preu
                    inici = lectura.indexOf(buscaPreu);
                    fi = lectura.indexOf(".", inici);
                    String preuStr = lectura.substring(inici + buscaPreu.length(), fi);
                    double preu = Double.valueOf(preuStr);
                    // creem l'objecte i carreguem a l'estoc
                    arbre = new Article_Arbre(alcada, preu);
                    Floristeria.getInstancia().afegirArticle(arbre);

                } else if (lectura.startsWith(buscaIdFlor)) {
                    //id
                    inici = lectura.indexOf(buscaIdFlor);
                    fi = lectura.indexOf(",", inici);
                    int id = Integer.valueOf(lectura.substring(inici + buscaIdFlor.length(), fi));
                    Article.id = id;
                    // color
                    inici = lectura.indexOf(buscaColor);
                    fi = lectura.indexOf(",", inici);
                    String color = lectura.substring(inici + buscaColor.length(), fi);
                    // preu
                    inici = lectura.indexOf(buscaPreu);
                    fi = lectura.indexOf(".", inici);
                    String preuStr = lectura.substring(inici + buscaPreu.length(), fi);
                    double preu = Double.valueOf(preuStr);
                    // creem l'objecte i carreguem a l'estoc
                    flor = new Article_Flors(color, preu);
                    Floristeria.getInstancia().afegirArticle(flor);

                } else if (lectura.startsWith(buscaIdDeco)) {
                    //id
                    inici = lectura.indexOf(buscaIdDeco);
                    fi = lectura.indexOf(",", inici);
                    int id = Integer.valueOf(lectura.substring(inici + 5, fi));
                    Article.id = id;
                    // material
                    inici = lectura.indexOf(buscaMaterial);
                    fi = lectura.indexOf(",", inici);
                    String materialStr = lectura.substring(inici + buscaMaterial.length(), fi);
                    // preu
                    inici = lectura.indexOf(buscaPreu);
                    fi = lectura.indexOf(".", inici);
                    String preuStr = lectura.substring(inici + buscaPreu.length(), fi);
                    double preu = Double.valueOf(preuStr);
                    // creem l'objecte i carreguem a l'estoc
                    if (materialStr.equalsIgnoreCase("FUSTA")) {
                        deco = new Article_Decoracio(Article_Decoracio.Material.FUSTA, preu);
                    } else {
                        deco = new Article_Decoracio(Article_Decoracio.Material.PLASTIC, preu);
                    }
                    Floristeria.getInstancia().afegirArticle(deco);
                }
                lectura = entrada.readLine();
            }
            entrada.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    /**
     * MÈTODE QUE LLEGIRÀ ARTICLES DEL TXT I ELS PASSARÀ AL PROGRAMA, 
     * ELS AFEGIRÀ AGRUPATS PER COMPRES DINS DEL TIQUET.
     * Fent servir el començament del toString dels articles anirà
     * destriant la informació per recrear objectes del tipus que 
     * correspongui.
     * Cada vegada que recorri una línia del txt on no hi ha artícles
     * guardarà els articles anteriorment llegit dins d'una
     * mateixa compra i ho enviarà a la llista de llistes d'articles de Tiquet.
     */
    public void abocarVendesDesdeTxt(){
        Article arbre;
        Article flor;
        Article deco;
        /**
         * Texts del toString dels articles que serviran
         * per trobar les dades necssàries.
         */
        String buscaAlcada = "alçada: ";
        String buscaPreu = "preu: ";
        String buscaColor = "color: ";
        String buscaMaterial = "material: ";
        String buscaIdArbre = "id. A";
        String buscaIdFlor = "id. F";
        String buscaIdDeco = "id. D";
        
        List<Article> compra = new ArrayList();
        
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(this.arxiuTiquets));
            String lectura = entrada.readLine();
            
            while (lectura != null) {
                int inici, fi;

                if (lectura.startsWith(buscaIdArbre)) {
                    //id
                    inici = lectura.indexOf(buscaIdArbre);
                    fi = lectura.indexOf(",", inici);
                    int id = Integer.valueOf(lectura.substring(inici + buscaIdArbre.length(), fi));
                    Article.id = id;
                    // alçada
                    inici = lectura.indexOf(buscaAlcada);
                    fi = lectura.indexOf(",", inici);
                    String alcadaStr = lectura.substring(inici + buscaAlcada.length(), fi);
                    double alcada = Double.valueOf(alcadaStr);
                    // preu
                    inici = lectura.indexOf(buscaPreu);
                    fi = lectura.indexOf(".", inici);
                    String preuStr = lectura.substring(inici + buscaPreu.length(), fi);
                    double preu = Double.valueOf(preuStr);
                    // creem l'objecte i desem a la compra
                    arbre = new Article_Arbre(alcada, preu);
                    compra.add(arbre);

                } else if (lectura.startsWith(buscaIdFlor)) {
                    //id
                    inici = lectura.indexOf(buscaIdFlor);
                    fi = lectura.indexOf(",", inici);
                    int id = Integer.valueOf(lectura.substring(inici + buscaIdFlor.length(), fi));
                    Article.id = id;
                    // color
                    inici = lectura.indexOf(buscaColor);
                    fi = lectura.indexOf(",", inici);
                    String color = lectura.substring(inici + buscaColor.length(), fi);
                    // preu
                    inici = lectura.indexOf(buscaPreu);
                    fi = lectura.indexOf(".", inici);
                    String preuStr = lectura.substring(inici + buscaPreu.length(), fi);
                    double preu = Double.valueOf(preuStr);
                    // creem l'objecte i carreguem a l'estoc
                    flor = new Article_Flors(color, preu);
                    compra.add(flor);

                } else if (lectura.startsWith(buscaIdDeco)) {
                    //id
                    inici = lectura.indexOf(buscaIdDeco);
                    fi = lectura.indexOf(",", inici);
                    int id = Integer.valueOf(lectura.substring(inici + 5, fi));
                    Article.id = id;
                    // material
                    inici = lectura.indexOf(buscaMaterial);
                    fi = lectura.indexOf(",", inici);
                    String materialStr = lectura.substring(inici + buscaMaterial.length(), fi);
                    // preu
                    inici = lectura.indexOf(buscaPreu);
                    fi = lectura.indexOf(".", inici);
                    String preuStr = lectura.substring(inici + buscaPreu.length(), fi);
                    double preu = Double.valueOf(preuStr);
                    // creem l'objecte i desem a la compra
                    if (materialStr.equalsIgnoreCase("FUSTA")) {
                        deco = new Article_Decoracio(Article_Decoracio.Material.FUSTA, preu);
                    } else {
                        deco = new Article_Decoracio(Article_Decoracio.Material.PLASTIC, preu);
                    }
                    compra.add(deco);
                } else {
                    if(!compra.isEmpty()){
                        // no hi havia més articles, desa la compra al llistat de compres
                        Tiquet.getInstancia().getLlistaCompres().add(compra);
                        // reinicialitzem
                        compra = new ArrayList();
                    }
                }
                lectura = entrada.readLine();
            }
            entrada.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
    /**
     * MÈTODE PER PERSISTIR TOSTRING D'ARTICLE AL TXT
     * 
     * @param art 
     */
    public void afegirArticleTxt(Article art) {
        try {
            PrintWriter sortida = new PrintWriter(new FileWriter(this.arxiuEstoc, true));
            sortida.println(art.toString());
            sortida.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) { // ens obliga pel FileWriter()
            ex.printStackTrace(System.out);
        }
    }
    
    /**
     * MÈTODE PER ELIMINAR UNA ENTRADA DEL TXT
     * Si troba l'id passat per paràmetre al txt,
     * crea un arxiu temporal, hi passa totes les línies de
     * l'arxiu original, excepte la que volem eliminar.
     * Després es torna a passar la info del temporal a l'original i el 
     * temporal s'esborra.
     * @param idArt
     * @throws FileNotFoundException 
     */
    public void retirarArticleTxt(String idArt) throws FileNotFoundException {
        File arxiuTemporal = new File(nomArxiuEstoc + ".tmp");
        String artAeliminar = "id. " + idArt;
        PrintWriter sortidaTmp = new PrintWriter(arxiuTemporal);
        boolean trobat = false;

        try {
            BufferedReader entradaArxiuOriginal = new BufferedReader(new FileReader(this.arxiuEstoc));
            String lectura = entradaArxiuOriginal.readLine();

            while (lectura != null) {
                if (!lectura.startsWith(artAeliminar)) {
                    sortidaTmp.println(lectura);
                }else{
                    trobat = true;
                }
                lectura = entradaArxiuOriginal.readLine();
            }
            entradaArxiuOriginal.close();
            sortidaTmp.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        PrintWriter sortidaArxiuActualitzat = new PrintWriter(this.arxiuEstoc);
        try {
            BufferedReader entradaArxiuTemp = new BufferedReader(new FileReader(arxiuTemporal));
            String lectura = entradaArxiuTemp.readLine();

            while (lectura != null) {
                sortidaArxiuActualitzat.println(lectura);
                lectura = entradaArxiuTemp.readLine();
            }
            entradaArxiuTemp.close();
            sortidaArxiuActualitzat.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        arxiuTemporal.delete();
        if(trobat){
            System.out.println("Article esborrat.");
        }
    }
    
    /**
     * MÈTODE PER PERSISTIR LES COMPRES EN UN FITXER TXT ESPECÍFIC
     * S'itera la llista d'articles i es va desant la info a l'arxiu.
     * Al mateix temps, els va enretirant de l'estoc persistit.
     * @param compra 
     */
    public void desartiquet(List<Article> compra) {
        try {
            PrintWriter sortida = new PrintWriter(new FileWriter(this.arxiuTiquets, true));
            sortida.println("COMPRA: ");
            for (Article article : compra) {
                sortida.println(article.toString());
                retirarArticleTxt(article.getIdArticle());
            }
            sortida.println();
            sortida.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) { // ens obliga pel FileWriter()
            ex.printStackTrace(System.out);
        }
    }
}
