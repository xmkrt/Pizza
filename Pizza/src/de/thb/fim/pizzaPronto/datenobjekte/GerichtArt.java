package de.thb.fim.pizzaPronto.datenobjekte;

public enum GerichtArt {
    PIZZA("Pizza"), PASTA("Pasta");

    String bezeichnung;

    GerichtArt(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public String toString() {
        return bezeichnung + " ";
    }
}


