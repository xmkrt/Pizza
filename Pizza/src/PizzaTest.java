import java.awt.Color;
import java.time.LocalDate;

public class PizzaTest {

	public static void main(String[] args) {
		LocalDate heute = LocalDate.now();
		LocalDate geb = LocalDate.of(1988, 1, 16);
		
		KundeVO einKunde = new KundeVO("Müller", "Gerd", "männlich", geb);
		System.out.println(einKunde.toString());
		
		KochVO einKoch = new KochVO("Dampf", "Voll", Color.BLACK);
		System.out.println(einKoch.getVorname() + einKoch.getNachname());
		
		
		String[] zutaten = {"Tomatensauce","Käse","Peperoni"};
		PizzaVO meinePizza = new PizzaVO("Peperoni", 8.5F,zutaten);
		PizzaVO test = (PizzaVO)meinePizza.clone();
		
		
		System.out.println(meinePizza.toString());
		
		
		
		

	}

}
