package de.thb.fim.pizzaPronto.logik.io;


import de.thb.fim.pizzaPronto.datenobjekte.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

public class SpeisekarteImporter {

    private String line;
    private String words[];
    private GerichtVO aktSpeise;
    private LinkedList<GerichtVO> speisen = new LinkedList<GerichtVO>();
    private int nummer;


    public SpeisekarteImporter() {

    }

    public URL getResourcePath(String name) {
        return getClass().getClassLoader().getResource(name);
    }

    public SpeiseKarte leseSpeisekarte(String datei) throws IOException {

        BufferedReader textFile = new BufferedReader(new FileReader(datei));
        ArrayList<String> zutaten = null;
        while ((line = textFile.readLine()) != null) {

            words = line.split(":");
            if (words[0].equals("speise.art")) {
                zutaten = new ArrayList<String>();
                if (words[1].equals("Pizza")) {
                    aktSpeise = new PizzaVO();
                    speisen.add(aktSpeise);
                } else if (words[1].equals("Pasta")) {
                    aktSpeise = new PastaVO();
                    speisen.add(aktSpeise);
                } else if (words[1].equals("Dessert")) {
                    aktSpeise = new DessertVO();
                    speisen.add(aktSpeise);
                }
            }

            if (words[0].equals("speise.name")) {
                aktSpeise.setName(words[1]);
            } else if (words[0].equals("speise.nr")) {
                nummer = Integer.parseInt(words[1]);
            } else if (words[0].equals("speise.sorte")) {
                aktSpeise.setNummer(nummer + Integer.parseInt(words[1]));
            } else if (words[0].equals("speise.groesse")) {
                aktSpeise.setNummer(nummer + Integer.parseInt(words[1]));
            } else if (words[0].equals("speise.sorte")) {
                aktSpeise.setNummer(nummer + Integer.parseInt(words[1]));
            } else if (words[0].equals("speise.preis")) {
                aktSpeise.setPreis(Float.parseFloat(words[1]));
            } else if (words[0].equals("speise.zutat")) {
                zutaten.add(words[1]);
                aktSpeise.setZutaten(zutaten);
            }
        }
        return new SpeiseKarte(speisen);
    }
}


