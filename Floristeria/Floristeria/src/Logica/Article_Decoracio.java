package Logica;

public class Article_Decoracio extends Article {
    // enumeració per l'atribut de classe
    public enum Material {
    FUSTA,
    PLASTIC
}
    // atribut
    static private Material material;

    // constructors
    public Article_Decoracio(Material material, double preu) {
        super(preu);
        this.material = material;
        idArticle = "D" + idArticle;
    }

    // getters i setters
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterialFusta(Material material) {
        Article_Decoracio.material = material;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id. ")
                .append(super.getIdArticle());
        sb.append(", material: ")
                .append(material.name().toLowerCase());
        sb.append(", preu: ")
                .append(super.getPreu());
        sb.append(".");
        return sb.toString();
    }
}
