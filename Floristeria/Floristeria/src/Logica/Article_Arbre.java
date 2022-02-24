package Logica;

public class Article_Arbre extends Article{
    // atributs
    private double alcada;

    // constructors
    public Article_Arbre(double alcada, double preu) {
        super(preu);
        this.alcada = alcada;
        idArticle = "A" + idArticle;
    }

    // getters i setters
    public double getAlcada() {
        return alcada;
    }

    public void setAlcada(double alcada) {
        this.alcada = alcada;
    }

    @Override
    public String getIdArticle() {
        return idArticle;
    }

    @Override
    public void setIdArticle(String idArticle) {
        this.idArticle = idArticle;
    }

    @Override
    public double getPreu() {
        return preu;
    }

    @Override
    public void setPreu(double preu) {
        this.preu = preu;
    }

    // toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id. ")
                .append(super.getIdArticle());
        sb.append(", alçada: ")
                .append(alcada);
        sb.append(", preu: ")
                .append(super.getPreu());
        sb.append(".");
        return sb.toString();
    }
}
