package de.thb.fim.pizzaPronto.controller;

import de.thb.fim.pizzaPronto.datenobjekte.Geschlecht;
import de.thb.fim.pizzaPronto.datenobjekte.KundeVO;
import de.thb.fim.pizzaPronto.datenobjekte.SpeiseKarte;
import de.thb.fim.pizzaPronto.datenobjekte.exceptions.KundeKeinGeburtsdatumException;
import de.thb.fim.pizzaPronto.datenobjekte.exceptions.KundeZuJungException;
import de.thb.fim.pizzaPronto.logik.*;
import de.thb.fim.pizzaPronto.logik.exceptions.BestellungFalscherStatusException;
import de.thb.fim.pizzaPronto.logik.exceptions.KeinKundeException;
import de.thb.fim.pizzaPronto.logik.exceptions.KeineBestellungException;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class PizzaTest {

    public static void main(String[] args) {
        KundeVO kunde1 = null;
        KundeVO kunde2 = null;
        try {
            kunde1 = new KundeVO("Müller", "Gerd", "Sackgasse", 10, Geschlecht.M, LocalDate.of(1982, 4, 19), null);
            kunde2 = new KundeVO("Maier", "Sabine", "Holzweg", 5, Geschlecht.W, LocalDate.of(1992, 3, 20), null);
        } catch (KundeZuJungException e) {
            System.err.println(e.getMessage());
        } catch (KundeKeinGeburtsdatumException e) {
            System.err.println(e.getMessage());
        }


        System.out.println(kunde1);
        System.out.println(kunde2);

        /*String[] zutaten = {"Tomatensauce", "Käse", "Peperoni"};
        de.thb.fim.pizzaPronto.datenobjekte.PizzaVO meinePizza = new de.thb.fim.pizzaPronto.datenobjekte.PizzaVO(0, "Peperoni", zutaten, 8.5F);
        de.thb.fim.pizzaPronto.datenobjekte.PizzaVO test = (de.thb.fim.pizzaPronto.datenobjekte.PizzaVO) meinePizza.clone();
        test.setZutaten(new String[]{"Tomatensauce", "Käse", "Champignons"});

        System.out.println(test);
        System.out.println(meinePizza);

        de.thb.fim.pizzaPronto.datenobjekte.PizzaVO Peperoni = new de.thb.fim.pizzaPronto.datenobjekte.PizzaVO(1, "Peperoni", zutaten, 8.5f);
        de.thb.fim.pizzaPronto.datenobjekte.PizzaVO Sucuk = new de.thb.fim.pizzaPronto.datenobjekte.PizzaVO(2, "Sucuk", new String[]{"Tomatensauce", "Käse", "Sucuk", "Zwiebeln"}, 9.0f);

        System.out.println(kunde1);*/

        Bestellung bestell1 = new Bestellung(LocalDateTime.now(), kunde1);
        Bestellung bestell2 = new Bestellung(LocalDateTime.now(), kunde2);


        /*bestell2.setZeitstempelBestellung(LocalDateTime.of(2016, 4, 27, 12, 12));
        bestell2.setZeitstempelAuslieferung(LocalDateTime.of(2016, 4, 27, 13, 00));*/

        Angestellter[] mitarbeiter = new Angestellter[3];

        mitarbeiter[0] = new Lieferant("Swag", "Yolo", "Horstplatz", 58, "2");
        mitarbeiter[1] = new Koch("Hitchkoch", "Alfred", Color.BLACK);
        mitarbeiter[2] = new Lieferant("Stellter", "Ange", "Dorfstraße", 66, "1");

        mitarbeiter[1].setStrasse("Vogelstraße");
        mitarbeiter[1].setHausNr(48);
        mitarbeiter[1].setPersonalNummer("3");

        mitarbeiter[0].setGehalt(1600.0f);
        mitarbeiter[1].setGehalt(1900.0f);
        mitarbeiter[2].setGehalt(1500.0f);

        mitarbeiter[0].setUrlaubsTage(24);
        mitarbeiter[1].setUrlaubsTage(25);
        mitarbeiter[2].setUrlaubsTage(30);

        SpeiseKarte speisekarte;
        speisekarte = ((Koch) mitarbeiter[1]).erstelltSpeisekarte();

        System.out.println(speisekarte);

        Random zufall = new Random();
        for (int i = 0; i < 4; i++) {
            bestell1.hinzufuegenGericht(speisekarte.getGericht(zufall.nextInt(18)));
            bestell2.hinzufuegenGericht(speisekarte.getGericht(zufall.nextInt(18)));
        }
        kunde1.setBestellung(bestell1);
        kunde2.setBestellung(bestell2);
        try {
            mitarbeiter[1].arbeitetFuerKunde(kunde1);
            mitarbeiter[2].arbeitetFuerKunde(kunde2);
        } catch (KeinKundeException e) {
            System.err.println(e.getMessage());
        } catch (KeineBestellungException e) {
            System.err.println(e.getMessage());
        } catch (BestellungFalscherStatusException e) {
            System.err.println(e.getMessage());
        }

        System.out.println(bestell1 + "\n\n" + bestell2 + "\n\n");

        kunde2.getBestellung().setStatus("fertig");

        for (Angestellter o : mitarbeiter) {
            System.out.println(o);
            try {
                System.out.println(o.arbeiten());
            } catch (KeinKundeException e) {
                System.err.println(e.getMessage());
            } catch (KeineBestellungException e) {
                System.err.println(e.getMessage());
            } catch (BestellungFalscherStatusException e) {
                System.err.println(e.getMessage());
            }
        }

        //Testat
        System.out.println("\n\n\n\n");

        Lieferant lieferant1 = new Lieferant("Banani", "Bruno");
        Fahrer lieferant2 = new Lieferant("Gak", "Hugo");
        lieferant1.setPersonalNummer("L1");
        ((Lieferant) lieferant2).setPersonalNummer("L2");

        KundeVO kunde = null;
        try {
            kunde = new KundeVO("Hungrig", "Herbert", "Horngasse", 43, Geschlecht.M, LocalDate.of(1977, 7, 23), null);
        } catch (KundeZuJungException e) {
            System.err.println(e.getMessage());
        } catch (KundeKeinGeburtsdatumException e) {
            System.err.println(e.getMessage());
        }

        Bestellung bestell = new Bestellung(LocalDateTime.now(), kunde);

        kunde.setBestellung(bestell);
        bestell.setStatus("fertig");
        try {
            System.out.println(lieferant1.arbeitetFuerKunde(kunde));
            System.out.println(((Lieferant) lieferant2).arbeitetFuerKunde(kunde));
        } catch (KeinKundeException e) {
            System.err.println(e.getMessage());
        } catch (KeineBestellungException e) {
            System.err.println(e.getMessage());
        } catch (BestellungFalscherStatusException e) {
            System.err.println(e.getMessage());
        }

    }
}
