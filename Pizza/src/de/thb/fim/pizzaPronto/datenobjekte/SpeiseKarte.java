package de.thb.fim.pizzaPronto.datenobjekte;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

public class SpeiseKarte {
    private ArrayList<GerichtVO> speisen;

    public SpeiseKarte() {
        initSpeisekarte();
    }

    public SpeiseKarte(ArrayList<GerichtVO> speisen) {
        this.speisen = new ArrayList<GerichtVO>(speisen);
    }

    public SpeiseKarte(LinkedList<GerichtVO> speisen) {
        this.speisen = new ArrayList<GerichtVO>(speisen);
    }

    public int getAnzGerichte(){
        return speisen.size();
    }

    private void initSpeisekarte() {
        speisen = new ArrayList<GerichtVO>();
        ArrayList<String> zutaten = new ArrayList<String>();
        zutaten.add("Schinken");
        zutaten.add("Spinat");
        zutaten.add("Champignons");
        zutaten.add("Ei");
        speisen.add(new PizzaVO(30, "Popeye", zutaten, 7.00f, 1));
        speisen.add(new PizzaVO(30, "Popeye", zutaten, 8.90f, 2));
        zutaten = new ArrayList<String>();
        zutaten.add("Schinken");
        zutaten.add("Ananas");
        zutaten.add("Curry");
        speisen.add(new PizzaVO(31, "Hawaii", zutaten, 5.80f, 1));
        speisen.add(new PizzaVO(31, "Hawaii", zutaten, 7.40f, 2));
        zutaten = new ArrayList<String>();
        zutaten.add("Schinken");
        zutaten.add("Salami");
        zutaten.add("Zwiebeln");
        zutaten.add("Ei");
        speisen.add(new PizzaVO(32, "Prima", zutaten, 7.00f, 1));
        speisen.add(new PizzaVO(32, "Prima", zutaten, 8.90f, 2));
        zutaten = new ArrayList<String>();
        zutaten.add("Tomatensauce");
        speisen.add(new PastaVO(11, "Napoli", zutaten, 5.60f, 4));
        speisen.add(new PastaVO(11, "Napoli", zutaten, 5.60f, 5));
        speisen.add(new PastaVO(11, "Napoli", zutaten, 5.60f, 6));
        zutaten = new ArrayList<String>();
        zutaten.add("Hackfleischsauce");
        speisen.add(new PastaVO(12, "Bolognese", zutaten, 6.40f, 4));
        speisen.add(new PastaVO(12, "Bolognese", zutaten, 6.40f, 5));
        speisen.add(new PastaVO(12, "Bolognese", zutaten, 6.40f, 6));
        zutaten = new ArrayList<String>();
        zutaten.add("Schinken");
        zutaten.add("Sahne");
        speisen.add(new PastaVO(13, "alla Panna", zutaten, 6.40f, 4));
        speisen.add(new PastaVO(13, "alla Panna", zutaten, 6.40f, 5));
        speisen.add(new PastaVO(13, "alla Panna", zutaten, 6.40f, 6));
        speisen.add(new DessertVO(21, "Hausgemachter Obstsalat", 2.30f));
        speisen.add(new DessertVO(22, "Hausgemachte Pannacotta", 2.60f));
        speisen.add(new DessertVO(23, "Hausgemachtes Tiramisu", 2.80f));
    }

    public GerichtVO getGericht(int n) {
        if (n >= 0 && n <= speisen.size())
            return speisen.get(n);
        return null;
    }

    @Override
    public String toString() {
        StringBuilder ausgabe = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.00€");
        ausgabe.append("SPEISEKARTE PIZZA PRONTO\n\n");
        ausgabe.append("Prima Spezialpizzen:\n  1 normal (Durchmesser ca. 26 cm) und \n  2 grande (Durchmesser ca. 32 cm)\n");

        int i = 0;

        do {
            ausgabe.append(speisen.get(i).getNummer() + "\t");
            ausgabe.append(speisen.get(i).getName() + "\t\t\t");
            ausgabe.append(speisen.get(i).toStringZutaten());
            ausgabe.append("\n\tPreis:\t\t\t" + df.format(speisen.get(i).getPreis()));
            // Unterschiedliche Größe direkt anhängen
            if (speisen.get(i).getNummer() == speisen.get(i + 1).getNummer()) {
                ausgabe.append("\n\tPreis:\t\t\t" + df.format(speisen.get(i + 1).getPreis()));
                ausgabe.append("\n");
                i += 2;
            }
           // i += 1;
        } while (i < speisen.size() && speisen.get(i) instanceof PizzaVO);

        ausgabe.append("\n\nPrima Spezial Nudelgerichte:\n4 Spaghetti\n5 Tortellini\n6 Gnocchi\n");

        do {
            ausgabe.append(" ");
            ausgabe.append(speisen.get(i).getNummer() + "\t");
            ausgabe.append(speisen.get(i).getName() + "\t\t");
            ausgabe.append(speisen.get(i).toStringZutaten());
            ausgabe.append("\n\tPreis:\t\t\t" + df.format(speisen.get(i).getPreis()) + "\n");
            i += 3;
        } while (i < speisen.size() && speisen.get(i) instanceof PastaVO);

        ausgabe.append("\n\nPrima Desserts\n");

        do {
            ausgabe.append(speisen.get(i).getNummer() + "\t");
            ausgabe.append(speisen.get(i).getName() + "\t");
            ausgabe.append("\n\tPreis:\t\t\t" + df.format(speisen.get(i).getPreis()) + "\n");
            i += 1;
        } while (i < speisen.size() && speisen.get(i) instanceof DessertVO);

        return ausgabe.toString();
    }
}
