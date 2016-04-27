import java.awt.Color;
import java.time.LocalDate;

public class PizzaTest {

	public static void main(String[] args) {
		LocalDate geb = LocalDate.of(1985, 1, 16);
		
		KundeVO einKunde = new KundeVO("Müller", "Gerd", "männlich", geb);
		KundeVO nocheinKunde = new KundeVO("TT", "AS", "weiblich", geb);
		if (einKunde.equals(nocheinKunde))
			System.out.println("sdsdasdasd");
		System.out.println(einKunde);
		System.out.println(nocheinKunde);
		
		KochVO einKoch = new KochVO("Dampf", "Voll", Color.BLACK);
		System.out.println(einKoch.getVorname() + einKoch.getNachname());
		
		
		String[] zutaten = {"Tomatensauce","Käse","Peperoni"};
		PizzaVO meinePizza = new PizzaVO("Peperoni",zutaten,8.5F);
		PizzaVO test = (PizzaVO)meinePizza.clone();
		String[] a = {"Zeug", "Nuss", "Eis"};
		test.setZutaten(a);
		
		System.out.println(test);
		
		
		System.out.println(meinePizza);
		
		
		
		

	}

}
