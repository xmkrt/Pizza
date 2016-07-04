package de.thb.fim.pizzaPronto.datenobjekte;

public enum GerichtArt {
    PIZZA("Pizza"), PASTA("Pasta");

    private final String bezeichnung;

    GerichtArt(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public String toString() {
        return bezeichnung + " ";
    }
}


