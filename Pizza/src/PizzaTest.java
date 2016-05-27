import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class PizzaTest {

    public static void main(String[] args) {

        KundeVO kunde1 = new KundeVO("Müller", "Gerd", "Sackgasse", 10, "männlich", LocalDate.of(1982, 4, 19), null);
        KundeVO kunde2 = new KundeVO("Maier", "Sabine", "Holzweg", 5, "weiblich", LocalDate.of(1992, 3, 20), null);

        System.out.println(kunde1);
        System.out.println(kunde2);

        /*String[] zutaten = {"Tomatensauce", "Käse", "Peperoni"};
        PizzaVO meinePizza = new PizzaVO(0, "Peperoni", zutaten, 8.5F);
        PizzaVO test = (PizzaVO) meinePizza.clone();
        test.setZutaten(new String[]{"Tomatensauce", "Käse", "Champignons"});

        System.out.println(test);
        System.out.println(meinePizza);

        PizzaVO Peperoni = new PizzaVO(1, "Peperoni", zutaten, 8.5f);
        PizzaVO Sucuk = new PizzaVO(2, "Sucuk", new String[]{"Tomatensauce", "Käse", "Sucuk", "Zwiebeln"}, 9.0f);

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
        mitarbeiter[1].arbeitetfuerKunde(kunde1);
        mitarbeiter[2].arbeitetfuerKunde(kunde2);

        System.out.println(bestell1 + "\n\n" + bestell2 + "\n\n");

        kunde2.getBestellung().setStatus("fertig");

        for (Angestellter o : mitarbeiter) {
            System.out.println(o);
            System.out.println(o.arbeiten());
        }
    }
}
