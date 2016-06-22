package de.thb.fim.pizzaPronto.datenobjekte;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public abstract class GerichtVO implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    protected int nummer;
    protected String name;
    protected ArrayList<String> zutaten;
    protected float preis;

    public GerichtVO() {
        this(0, null, 0.0f);
    }

    public GerichtVO(int nummer, String name, float preis) {
        this(nummer, name, null, preis);
    }

    public GerichtVO(int nummer, String name, ArrayList<String> zutaten, float preis) {
        this.nummer = nummer;
        this.name = name;
        this.zutaten = zutaten;
        this.preis = preis;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getNummer() {
        return nummer;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GerichtVO gerichtVO = (GerichtVO) o;

        if (nummer != gerichtVO.nummer) return false;
        if (Float.compare(gerichtVO.preis, preis) != 0) return false;
        if (name != null ? !name.equals(gerichtVO.name) : gerichtVO.name != null) return false;
        return zutaten != null ? zutaten.equals(gerichtVO.zutaten) : gerichtVO.zutaten == null;

    }

    @Override
    public int hashCode() {
        int result = nummer;
        result = 31 * result + name.hashCode();
        result = 31 * result + zutaten.hashCode();
        result = 31 * result + (preis != +0.0f ? Float.floatToIntBits(preis) : 0);
        return result;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00€");
        StringBuilder ausgabe = new StringBuilder();
        ausgabe.append(name);
        ausgabe.append("\nPreis: ");
        ausgabe.append(df.format(preis));
        ausgabe.append("\n");
        ausgabe.append(toStringZutaten());
        return ausgabe.toString();
    }

    public String toStringZutaten() {
        //entfernt die Klammern bei der Ausgabe des Array
        return zutaten.toString().replaceAll("[\\[\\]]", "");

    }

    public abstract int getGerichtNummer();

    public abstract String getGerichtName();

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }



    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getZutaten() {
        return zutaten;
    }

    public void setZutaten(ArrayList<String> zutaten) {
        this.zutaten = zutaten;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }
}
