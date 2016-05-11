import java.time.LocalDate;
import java.time.LocalDateTime;

public class BestellungTest {

	public static void main(String[] args) {
		LocalDate geb = LocalDate.of(1988, 1, 16);
		KundeVO einKunde = new KundeVO("Müller", "Gerd", "männlich", geb, null);
		KundeVO nocheinKunde = new KundeVO("TT", "AS", "weiblich", geb, null);
		
		String[] zutaten = {"Tomatensauce","Käse","Peperoni"};
		PizzaVO meinePizza = new PizzaVO("Peperoni",zutaten,8.5F);
		PizzaVO test = (PizzaVO)meinePizza.clone();
		String[] a = {"Zeug", "Nuss", "Eis"};
		test.setZutaten(a);
		
		Bestellung meineBestellung = new Bestellung(LocalDateTime.now(),LocalDateTime.of(2016, 5, 6, 12, 30), einKunde);
		Bestellung nochEineBestellung = new Bestellung(null,null,null);
		
		meineBestellung.hinzufuegenGericht(meinePizza);
		System.out.println(meineBestellung);
		
		
		nochEineBestellung.setZeitstempelBestellung(LocalDateTime.of(2016, 4, 27, 12, 12));
		nochEineBestellung.setZeitstempelAuslieferung(LocalDateTime.parse("2016-04-27T13:00"));
		
		if (!meineBestellung.equals(nochEineBestellung))
			System.out.println("Die Bestellungen sind underschiedlich");
		System.out.println(meineBestellung);
	}

}
