package de.thb.fim.pizzaPronto.datenobjekte;

import java.text.DecimalFormat;
import java.util.Arrays;

public abstract class GerichtVO {
    protected int nummer;
    protected String name;
    protected String[] zutaten;
    protected float preis;

    public GerichtVO() {
        this(0, null, 0.0f);
    }

    public GerichtVO(int nummer, String name, float preis) {
        this(nummer, name, null, preis);
    }

    public GerichtVO(int nummer, String name, String[] zutaten, float preis) {
        this.nummer = nummer;
        this.name = name;
        this.zutaten = zutaten;
        this.preis = preis;
    }

   /*  @Override
    public Object clone() {
        de.thb.fim.pizzaPronto.datenobjekte.GerichtVO neuesGericht = new de.thb.fim.pizzaPronto.datenobjekte.GerichtVO(this.nummer, this.name, this.zutaten, this.preis);
        return neuesGericht;
    }*/


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
        if (!name.equals(gerichtVO.name)) return false;
        return Arrays.equals(zutaten, gerichtVO.zutaten);
    }

    @Override
    public int hashCode() {
        int result = nummer;
        result = 31 * result + name.hashCode();
        result = 31 * result + Arrays.hashCode(zutaten);
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
        return Arrays.toString(zutaten).replaceAll("[\\[\\]]", "");

    }

    public abstract int getGerichtNummer();

    public abstract String getGerichtName();

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String[] getZutaten() {
        return zutaten;
    }

    public void setZutaten(String[] zutaten) {
        this.zutaten = zutaten;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }
}
