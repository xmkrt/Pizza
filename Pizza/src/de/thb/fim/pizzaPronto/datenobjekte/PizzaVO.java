package de.thb.fim.pizzaPronto.datenobjekte;

import java.util.ArrayList;

public class PizzaVO extends GerichtVO implements Cloneable {

    private int groesse;

    public PizzaVO() {
        this(0, null, null, 0.0f);
    }

    public PizzaVO(int nummer, String name, ArrayList<String> zutaten, float preis) {
        this(nummer, name, zutaten, preis, 0);
    }

    public PizzaVO(int nummer, String name, ArrayList<String> zutaten, float preis, int groesse) {
        super(nummer, name, zutaten, preis);
        this.groesse = groesse;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PizzaVO pizzaVO = (PizzaVO) o;

        return groesse == pizzaVO.groesse;

    }

    public int getGroesse() {
        return groesse;
    }

    public void setGroesse(int groesse) {
        this.groesse = groesse;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + groesse;
        return result;
    }

    @Override
    public int getGerichtNummer() {
        return nummer * 10 + groesse;
    }

    @Override
    public String getGerichtName() {
        StringBuilder ausgabe = new StringBuilder();
        ausgabe.append(GerichtArt.PIZZA.toString());
        ausgabe.append(name);
        if (groesse == 1)
            ausgabe.append(" Normal");
        if (groesse == 2)
            ausgabe.append(" Grande");

        return ausgabe.toString();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != "")
            this.name = name;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        if (preis >= 0)
            this.preis = preis;
    }

    public String toString() {
        return super.toString() + " " + groesse;
    }


}
