package Logica;

public class Article_Flors extends Article{
    // atributs
    String color;

    // constructors
    public Article_Flors(String color, double preu) {
        super(preu);
        this.color = color;
        idArticle = "F" + idArticle;
    }

   // getters i setters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id. ")
                .append(super.getIdArticle());
        sb.append(", color: ")
                .append(color);
        sb.append(", preu: ")
                .append(super.getPreu());
        sb.append(".");
        return sb.toString();
    }
}
