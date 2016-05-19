import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PizzaTest {

    public static void main(String[] args) {
        LocalDate geb = LocalDate.of(1985, 1, 16);

        KundeVO einKunde = new KundeVO("Müller", "Gerd", "Sackgasse", 10, "männlich", geb, null);
        KundeVO nocheinKunde = new KundeVO("Maier", "Sabine", "Holzweg", 5, "weiblich", geb, null);
        if (einKunde.equals(nocheinKunde))
            System.out.println("Kunde gleich");
        else
            System.out.println("Kunde unterschiedlich");
        System.out.println(einKunde);
        System.out.println(nocheinKunde);

        Koch einKoch = new Koch("Dampf", "Voll", Color.BLACK);
        System.out.println(einKoch.getVorname() + einKoch.getNachname());


        String[] zutaten = {"Tomatensauce", "Käse", "Peperoni"};
        PizzaVO meinePizza = new PizzaVO(0, "Peperoni", zutaten, 8.5F);
        PizzaVO test = (PizzaVO) meinePizza.clone();
        test.setZutaten(new String[]{"Tomatensauce", "Käse", "Champignons"});

        System.out.println(test);
        System.out.println(meinePizza);

        PizzaVO Peperoni = new PizzaVO(1, "Peperoni", zutaten, 8.5f);
        PizzaVO Sucuk = new PizzaVO(2, "Sucuk", new String[]{"Tomatensauce", "Käse", "Sucuk", "Zwiebeln"}, 9.0f);

        //System.out.println(einKunde);

        Bestellung meineBestellung = new Bestellung(LocalDateTime.now(), LocalDateTime.of(2016, 5, 6, 12, 30), einKunde);
        Bestellung nochEineBestellung = new Bestellung(null, null, null);

        meineBestellung.hinzufuegenGericht(Peperoni);
        meineBestellung.hinzufuegenGericht(Sucuk);
        einKunde.setBestellung(meineBestellung);
        System.out.println(einKunde);


        nochEineBestellung.setZeitstempelBestellung(LocalDateTime.of(2016, 4, 27, 12, 12));
        nochEineBestellung.setZeitstempelAuslieferung(LocalDateTime.parse("2016-04-27T13:00"));

        Angestellter[] mitarbeiter = new Angestellter[3];

        mitarbeiter[0] = new Lieferant("Swag", "Yolo","Horstplatz" ,58, "2");
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

        for (Angestellter o:mitarbeiter)
            System.out.println(o.toString());

        DessertVO mjam = new DessertVO(0, "Schleck", 4.50f);
        System.out.println(mjam.getPreis());

        Speisekarte meineKarte = new Speisekarte();

        System.out.println(meineKarte);


	/*
    if (!meineBestellung.equals(nochEineBestellung))
	System.out.println("Die Bestellungen sind underschiedlich");
	*/


    }

}
