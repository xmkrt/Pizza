package de.thb.fim.pizzaPronto.datenobjekte;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SpeiseKarte {
    private ArrayList<GerichtVO> speisen;

    public SpeiseKarte() {
        initSpeisekarte();
    }

    private void initSpeisekarte() {
        speisen = new ArrayList<GerichtVO>();
        ArrayList<String> zutatenPopeye = new ArrayList<String>();
        ArrayList<String> zutatenHawaii = new ArrayList<String>();
        ArrayList<String> zutatenPrima = new ArrayList<String>();
        ArrayList<String> zutatenNapoli = new ArrayList<String>();
        ArrayList<String> zutatenBolognese = new ArrayList<String>();
        ArrayList<String> zutatenPanna = new ArrayList<String>();
        zutatenPopeye.add("Schinken");
        zutatenPopeye.add("Spinat");
        zutatenPopeye.add("Champignon");
        zutatenPopeye.add("Ei");
        speisen.add(new PizzaVO(30, "Popeye", zutatenPopeye, 7.00f, 1));
        speisen.add(new PizzaVO(30, "Popeye", zutatenPopeye, 8.90f, 2));
        zutatenHawaii.add("Schinken");
        zutatenHawaii.add("Ananas");
        zutatenHawaii.add("Curry");
        speisen.add(new PizzaVO(31, "Hawaii", zutatenHawaii, 5.80f, 1));
        speisen.add(new PizzaVO(31, "Hawaii", zutatenHawaii, 7.40f, 2));
        zutatenPrima.add("Schinken");
        zutatenPrima.add("Salami");
        zutatenPrima.add("Zwiebeln");
        zutatenPrima.add("Ei");
        speisen.add(new PizzaVO(32, "Prima", zutatenPrima, 7.00f, 1));
        speisen.add(new PizzaVO(32, "Prima", zutatenPrima, 8.90f, 2));
        zutatenNapoli.add("Tomatensauce");
        speisen.add(new PastaVO(11, "Napoli", zutatenNapoli, 5.60f, 4));
        speisen.add(new PastaVO(11, "Napoli", zutatenNapoli, 5.60f, 5));
        speisen.add(new PastaVO(11, "Napoli", zutatenNapoli, 5.60f, 6));
        zutatenBolognese.add("Hackfleischsauce");
        speisen.add(new PastaVO(12, "Bolognese", zutatenBolognese, 6.40f, 4));
        speisen.add(new PastaVO(12, "Bolognese", zutatenBolognese, 6.40f, 5));
        speisen.add(new PastaVO(12, "Bolognese", zutatenBolognese, 6.40f, 6));
        zutatenPanna.add("Schinken");
        zutatenPanna.add("Sahne");
        speisen.add(new PastaVO(13, "alla Panna", zutatenPanna, 6.40f, 4));
        speisen.add(new PastaVO(13, "alla Panna", zutatenPanna, 6.40f, 5));
        speisen.add(new PastaVO(13, "alla Panna", zutatenPanna, 6.40f, 6));
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
