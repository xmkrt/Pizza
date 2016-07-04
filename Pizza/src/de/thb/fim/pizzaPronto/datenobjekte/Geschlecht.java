package de.thb.fim.pizzaPronto.datenobjekte;

public enum Geschlecht {
    M(1), W(2), I(3), U(4);

    private final int nummer;

    Geschlecht(int nummer) {
        this.nummer = nummer;
    }

    @Override
    public String toString() {
        switch (nummer) {
            case 1:
                return "männlich";
            case 2:
                return "weiblich";
            case 3:
                return "intersexuell";
            default:
                return "unbekannt";
        }
    }

    public int toNumber() {
        return nummer;
    }
}
