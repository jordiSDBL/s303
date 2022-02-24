package Logica;

/**
 * Classe abstracta pare del tipus d'articles
 * Id autoincrementat que formarà part de l'id de les classes filles
 * Atribut comú a totes les classes filles --> preu
 */
public abstract class Article {
    // atributs
    public static int id = 1;
    protected String idArticle;
    protected double preu;
    
    // constuctors
    public Article(double preu) {
        this.preu = preu;
        this.idArticle = String.valueOf(Article.id++);
    }

    // getter i setters
    public String getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(String idArticle) {
        this.idArticle = idArticle;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    // toString
    @Override
    public String toString() {
        return "Article{" + "idArticle=" + idArticle + ", preu=" + preu + '}';
    }
}
