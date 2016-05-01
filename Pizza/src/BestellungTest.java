import java.time.LocalDateTime;

public class BestellungTest {

	public static void main(String[] args) {
		Bestellung meineBestellung = new Bestellung(LocalDateTime.now(),null);
		Bestellung nochEineBestellung = new Bestellung(null,null);
		
		meineBestellung.setZeitstempelAuslieferung(LocalDateTime.of(2016, 4, 27, 14, 37));
		
		nochEineBestellung.setZeitstempelBestellung(LocalDateTime.of(2016, 4, 27, 12, 12));
		nochEineBestellung.setZeitstempelAuslieferung(LocalDateTime.parse("2016-04-27T13:00"));
		
		if (!meineBestellung.equals(nochEineBestellung))
			System.out.println("Die Bestellungen sind underschiedlich");
		
		if (meineBestellung.equals(meineBestellung))
			System.out.println("Die Bestellungen sind gleich");
		
		System.out.println(meineBestellung);
		System.out.println(nochEineBestellung);
	}

}