package de.thb.fim.pizzaPronto.datenobjekte;

public class DessertVO extends GerichtVO implements Cloneable {

    public DessertVO() {
        this(0, null, 0.0f);
    }

    public DessertVO(int nummer, String name, float preis) {
        super(nummer, name, preis);
    }

    @Override
    public int getGerichtNummer() {
        return nummer;
    }

    @Override
    public String getGerichtName() {
        return name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
