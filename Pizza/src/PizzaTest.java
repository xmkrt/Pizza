import java.awt.Color;
import java.time.LocalDate;

public class PizzaTest {

	public static void main(String[] args) {
		LocalDate heute = LocalDate.now();
		LocalDate geb = LocalDate.of(1988, 1, 16);
		
		KundeVO einKunde = new KundeVO("Müller", "Gerd", "männlich", geb);
		System.out.println(einKunde.getGeburtsdatumStr());
		
		KochVO einKoch = new KochVO("Dampf", "Voll", Color.BLACK);
		System.out.println(einKoch.getVorname() + einKoch.getNachname());
		
		
		
		

	}

}
