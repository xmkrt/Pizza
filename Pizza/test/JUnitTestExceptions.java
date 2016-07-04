import java.lang.reflect.Field;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.BeforeClass;
import org.junit.Test;

import de.thb.fim.pizzaPronto.datenobjekte.Geschlecht;
import de.thb.fim.pizzaPronto.datenobjekte.KundeVO;
import de.thb.fim.pizzaPronto.datenobjekte.PizzaVO;
import de.thb.fim.pizzaPronto.datenobjekte.SpeiseKarte;
import de.thb.fim.pizzaPronto.datenobjekte.exceptions.KundeKeinGeburtsdatumException;
import de.thb.fim.pizzaPronto.datenobjekte.exceptions.KundeZuJungException;
import de.thb.fim.pizzaPronto.logik.Angestellter;
import de.thb.fim.pizzaPronto.logik.Bestellung;
import de.thb.fim.pizzaPronto.logik.Koch;
import de.thb.fim.pizzaPronto.logik.Lieferant;
import de.thb.fim.pizzaPronto.logik.exceptions.BestellungFalscherStatusException;
import de.thb.fim.pizzaPronto.logik.exceptions.KeinKundeException;
import de.thb.fim.pizzaPronto.logik.exceptions.KeineBestellungException;

import static org.junit.Assert.*;

/**
 * Exeptions werden getestet
 * 
 * Angestellte und arbeiten wird getestet.
 * 
 * Voraussetzung Bestellung und Speisekarte sind ok
 * 
 * Zum Testen werden spezielle Assert-Befehle eingesetzt <br>
 * 
 * @author Gabriele Schmidt
 * @version 1.0 11.05.2015
 */
public class JUnitTestExceptions {

	private static Class<Bestellung> myBestellungClass;
	private static Bestellung myBestellung;

	private static Angestellter[] angestellte = new Angestellter[2];

	private static KundeVO kundeDefault, kundeIni;
	private static KundeVO kunde;

	private static SpeiseKarte speisekarte;

	private static Field warenkorb;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Koch erstellen
		angestellte[0] = new Koch("Bocuse", "Bruno", "Schlemmerweg", 13,
				"Koch007");
		// Lieferant erstellen
		angestellte[1] = new Lieferant("Rasender", "Rudi", "Schnellstraﬂe",
				200, "Lieferant01");

		// Kunde
		kunde = new KundeVO("Genuss", "Gini", "Haribostraﬂe", 32, Geschlecht.W,
				LocalDate.of(1995, 8, 8), null);
		
		kundeDefault = new KundeVO("Nachname", "Vorname", Geschlecht.M,
				LocalDate.of(1980, 1, 31));

		kundeIni = new KundeVO("Nachname", "Vorname", Geschlecht.M,
				LocalDate.of(1980, 1, 31));

	

		myBestellung = new Bestellung(LocalDateTime.now(), kunde);

		speisekarte = new SpeiseKarte();

		myBestellungClass = Bestellung.class;
		warenkorb = myBestellungClass.getDeclaredField("warenkorb");

	}

	@Test
	public void isCheckedExceptions() {
		Class<KundeKeinGeburtsdatumException> kundeKeinGeburtsdatumException = KundeKeinGeburtsdatumException.class;
		assertTrue("KundeKeinGeburtsdatumException ist eine checked Exception", kundeKeinGeburtsdatumException.getSuperclass().toString().equals("class java.lang.Exception"));
		
		Class<KundeZuJungException> kundeZuJungException = KundeZuJungException.class;
		assertTrue("KundeZuJungException ist eine checked Exception", kundeZuJungException.getSuperclass().toString().equals("class java.lang.Exception"));
		

		Class<BestellungFalscherStatusException> bestellungFalscherStatusException = BestellungFalscherStatusException.class;
		assertTrue("BestellungFalscherStatusException ist eine checked Exception", bestellungFalscherStatusException.getSuperclass().toString().equals("class java.lang.Exception"));
	
		Class<KeineBestellungException> keineBestellungException = KeineBestellungException.class;
		assertTrue("KeineBestellungException ist eine checked Exception", keineBestellungException.getSuperclass().toString().equals("class java.lang.Exception"));
		
		Class<KeinKundeException> keinKundeException = KeinKundeException.class;
		assertTrue("KeinKundeException ist eine checked Exception", keinKundeException.getSuperclass().toString().equals("class java.lang.Exception"));	
	}
	
	@Test
	public void superAufrufinExceptions() {
		String text = "Test";
		KundeKeinGeburtsdatumException kundeKeinGeburtsdatumException = new KundeKeinGeburtsdatumException(text);
		assertEquals("super-Aufruf im Initialisierungskonstruktor in Exception",text,kundeKeinGeburtsdatumException.getMessage());
		
		
		KundeZuJungException kundeZuJungException = new KundeZuJungException(text);
		assertEquals("super-Aufruf im Initialisierungskonstruktor in Exception",text,kundeZuJungException.getMessage());
		
		BestellungFalscherStatusException bestellungFalscherStatusException = new BestellungFalscherStatusException(text);
		assertEquals("super-Aufruf im Initialisierungskonstruktor in Exception",text,bestellungFalscherStatusException.getMessage());
		
		KeineBestellungException keineBestellungException = new KeineBestellungException(text);
		assertEquals("super-Aufruf im Initialisierungskonstruktor in Exception",text,keineBestellungException.getMessage());
		
		KeinKundeException keinKundeException = new KeinKundeException(text);
		assertEquals("super-Aufruf im Initialisierungskonstruktor in Exception",text,keinKundeException.getMessage());
		
	}
	

	@Test(expected = KeineBestellungException.class)
	public void testAngestellteArbeitenFuerOhneBestellung() throws KeinKundeException, KeineBestellungException, BestellungFalscherStatusException {

		assertTrue(
				"ArbeitenFuer Koch: Keine Bestellung vorhanden",
				angestellte[0].arbeitetFuerKunde(kunde).contains(
						"Keine Bestellung vorhanden"));
		assertTrue(
				"ArbeitenFuer Lieferant: Keine Bestellung vorhanden",
				angestellte[1].arbeitetFuerKunde(kunde).contains(
						"Keine Bestellung vorhanden"));

	}

	@Test(expected = BestellungFalscherStatusException.class)
	public void testAngestellteArbeitenFuerPersonalnummer() throws KeinKundeException, KeineBestellungException, BestellungFalscherStatusException {
		assertTrue(
				"ArbeitenFuer Koch: Personalnummer in Ausgabe",
				angestellte[0].arbeitetFuerKunde(kunde).contains(
						angestellte[0].getPersonalNummer()));
		assertTrue(
				"ArbeitenFuer Lieferant: Personalnummer in Ausgabe",
				angestellte[1].arbeitetFuerKunde(kunde).contains(
						angestellte[1].getPersonalNummer()));
	}

	@Test(expected = KeinKundeException.class)
	public void testAngestellteArbeitenFuerOhneKunde() throws KeinKundeException, KeineBestellungException, BestellungFalscherStatusException {
		assertTrue(
				"ArbeitenFuer ohne Kunde Koch: Keine Bestellung vorhanden",
				angestellte[0].arbeitetFuerKunde(null).contains(
						"Keine Bestellung vorhanden"));
		assertTrue(
				"ArbeitenFuer ohne Kunde Lieferant: keine Bestellung vorhanden",
				angestellte[1].arbeitetFuerKunde(null).contains(
						"Keine Bestellung vorhanden"));

	}

	@Test
	public void testKochArbeitenBestellungAufgegeben() throws KeinKundeException, KeineBestellungException, BestellungFalscherStatusException {
		myBestellung = new Bestellung(LocalDateTime.now(), kunde);
		kunde.setBestellung(myBestellung);
		myBestellung.setStatus("aufgegeben");
		assertTrue("Koch arbeitet: Bestellung fertig", angestellte[0]
				.arbeitetFuerKunde(kunde).contains("Bestellung fertig"));
		assertEquals("Bestellungsstatus nach Koch arbeitet", "fertig",
				myBestellung.getStatus());
	}

	@Test(expected = BestellungFalscherStatusException.class)
	public void testKochArbeitenBestellungFertig() throws KeinKundeException, KeineBestellungException, BestellungFalscherStatusException {
		myBestellung = new Bestellung(LocalDateTime.now(), kunde);
		kunde.setBestellung(myBestellung);
		myBestellung.setStatus("fertig");
		assertTrue(
				"Koch arbeitet: Keine Bestellung zum Abarbeiten",
				angestellte[0].arbeitetFuerKunde(kunde).contains(
						"Keine Bestellung zum Abarbeiten"));
		assertEquals("Bestellungsstatus immer noch fertig", "fertig",
				myBestellung.getStatus());

	}

	@Test(expected = BestellungFalscherStatusException.class)
	public void testKochArbeitenBestellungAusgeliefert() throws KeinKundeException, KeineBestellungException, BestellungFalscherStatusException {
		myBestellung = new Bestellung(LocalDateTime.now(), kunde);
		kunde.setBestellung(myBestellung);
		myBestellung.setStatus("ausgeliefert");
		assertTrue(
				"Koch arbeitet: Keine Bestellung zum Abarbeiten",
				angestellte[0].arbeitetFuerKunde(kunde).contains(
						"Keine Bestellung zum Abarbeiten"));

	}

	@Test(expected = BestellungFalscherStatusException.class)
	public void testLieferantArbeitenBestellungAufgegeben() throws KeinKundeException, KeineBestellungException, BestellungFalscherStatusException {
		myBestellung = new Bestellung(LocalDateTime.now(), kunde);
		kunde.setBestellung(myBestellung);
		myBestellung.setStatus("aufgegeben");
		assertTrue(
				"Lieferant arbeitet:  Keine Bestellung zum Abarbeiten",
				angestellte[1].arbeitetFuerKunde(kunde).contains(
						"Keine Bestellung zum Abarbeiten"));
		assertEquals("Bestellungsstatus immer noch aufgegeben", "aufgegeben",
				myBestellung.getStatus());
	}

	@Test
	public void testLieferantArbeitenBestellungFertig() throws KeinKundeException, KeineBestellungException, BestellungFalscherStatusException {
		myBestellung = new Bestellung(LocalDateTime.now(), kunde);
		kunde.setBestellung(myBestellung);
		myBestellung.setStatus("fertig");
	//	System.out.println(myBestellung.getZeitstempelAuslieferung());
		assertTrue("Bestellung Zeitstempel Auslieferung vorher null",
				myBestellung.getZeitstempelAuslieferung() == null);
		assertTrue("Lieferant arbeitet: Bestellung fertig um", angestellte[1]
				.arbeitetFuerKunde(kunde).contains("Bestellung fertig um"));
		assertEquals("Bestellungsstatus ausgeliefert", "ausgeliefert",
				myBestellung.getStatus());
		assertTrue("Bestellung Zeitstempel Auslieferung vorhanden",
				myBestellung.getZeitstempelAuslieferung() != null);

	}

	@Test(expected = BestellungFalscherStatusException.class)
	public void testLieferantArbeitenBestellungAusgeliefert() throws KeinKundeException, KeineBestellungException, BestellungFalscherStatusException {
		myBestellung = new Bestellung(LocalDateTime.now(), kunde);
		kunde.setBestellung(myBestellung);
		myBestellung.setStatus("ausgeliefert");
		assertTrue(
				"Lieferant arbeitet: Keine Bestellung zum Abarbeiten",
				angestellte[1].arbeitetFuerKunde(kunde).contains(
						"Keine Bestellung zum Abarbeiten"));

	}
	
	
	@Test
	public void setGeburtsdatumInKundeVO() throws KundeKeinGeburtsdatumException, KundeZuJungException {

		int alter = 31;
		LocalDate heute = LocalDate.now();
		int jahr = heute.getYear();
		int monat = heute.getMonthValue();
		int tag = heute.getDayOfMonth();

		kundeIni.setGeburtsdatum(LocalDate.of(jahr - alter, monat, tag));
		assertEquals(kundeIni.getClass() + " hat im Geburtsdatum das Jahr "
				+ (jahr - alter), jahr - alter, kundeIni.getGeburtsdatum()
				.getYear());
		assertEquals(kundeIni.getClass() + " hat  im Geburtsdatum den Monat "
				+ monat, monat, kundeIni.getGeburtsdatum().getMonthValue());
		assertEquals(kundeIni.getClass() + " hat  im Geburtsdatum den Tag "
				+ tag, tag, kundeIni.getGeburtsdatum().getDayOfMonth());

		// Monat Januar
		kundeIni.setGeburtsdatum(LocalDate.of(jahr - alter, 1, tag));
		assertEquals(kundeIni.getClass()
				+ " hat  im Geburtsdatum den Monat Januar", 1, kundeIni
				.getGeburtsdatum().getMonthValue());
	}

	@Test(expected = KundeZuJungException.class)
		public void setGeburtsdatumZuJungInKundeVO() throws KundeKeinGeburtsdatumException, KundeZuJungException {
		// Setzt ein zu junges Datum
			int alter = 12;
			LocalDate heute = LocalDate.now();
			int jahr = heute.getYear();
			int monat = heute.getMonthValue();
			int tag = heute.getDayOfMonth();
		kundeIni.setGeburtsdatum(LocalDate.of(jahr - alter, monat, tag));
		assertEquals(kundeIni.getClass() + " hat im Geburtsdatum das Jahr "
				+ (jahr - alter) + ", ist zu jung und daher kein Objekt", null,
				kundeIni.getGeburtsdatum());

		// Setzt Alter 60
		alter = 60;
		kundeIni.setGeburtsdatum(LocalDate.of(jahr - alter, monat, tag));
		assertEquals(kundeIni.getClass() + " hat im Geburtsdatum das Jahr "
				+ (jahr - alter) + ", ist zu alt und daher kein Objekt",LocalDate.of(jahr - alter, monat, tag),
				kundeIni.getGeburtsdatum());
		}
		

	

	// Setzte falsches Datum, z. B. 30.2.1967
	//Erwarte Exception
	@Test(expected = DateTimeException.class)
	public void setGeburtsdatumInKundeVOMitException() throws KundeKeinGeburtsdatumException, KundeZuJungException {
		// Setzt ein Schaltjahr

				kundeIni.setGeburtsdatum(LocalDate.of(1964, 2, 29));
				assertNotNull(
						kundeIni.getClass()
								+ " hat ein Geburtsdatum 29. Februar 1964, ist daher eein Objekt",
						kundeIni.getGeburtsdatum());
		kundeIni.setGeburtsdatum(LocalDate.of(1967, 2, 30));
	}

	@Test(expected = KundeKeinGeburtsdatumException.class)
	public void berechneAlterKeinGeburtsdatumInKundeVO() throws KundeKeinGeburtsdatumException, KundeZuJungException {
		int alter;
		LocalDate heute = LocalDate.now();
		int jahr = heute.getYear();
		int monat = heute.getMonthValue();
		int tag = heute.getDayOfMonth();
		
		// Geburtsdatum ist null, alter -1
				alter = 16;
				kundeIni.setGeburtsdatum(null);
				assertEquals(kundeIni.getClass() + " hat das Alter " + alter, -1,
						kundeIni.berechneAlter());
				assertEquals(kundeIni.getClass()
						+ " Geburtsdaum wurde auf null gesetzt ", null,
						kundeIni.getGeburtsdatum());
	}

	@Test(expected = KundeZuJungException.class)
	public void berechneAlterNichtVolljaehrigInKundeVO() throws KundeKeinGeburtsdatumException, KundeZuJungException {
		int alter;
		LocalDate heute = LocalDate.now();
		int jahr = heute.getYear();
		int monat = heute.getMonthValue();
		int tag = heute.getDayOfMonth();
		// 16 Jahre alt
		alter = 16;
		kundeIni.setGeburtsdatum(LocalDate.of(jahr - alter, monat, tag));
		assertEquals(kundeIni.getClass() + " hat das Alter " + alter, 16,
				kundeIni.berechneAlter());
		assertEquals(kundeIni.getClass()
				+ " Geburtsdaum wurde auf null gesetzt ", null,
				kundeIni.getGeburtsdatum());
		
		// genau 18 Jahre minus 1 Tag alt
				alter = 18;
				kundeIni.setGeburtsdatum(LocalDate.of(jahr - alter, monat, tag + 1));
				assertEquals(kundeIni.getClass()
						+ " hat das Alter 1 Tag vor dem 18. Geb.", 17,
						kundeIni.berechneAlter());

	}
		
		@Test
		public void berechneAlterInKundeVO() throws KundeKeinGeburtsdatumException, KundeZuJungException {
			int alter;
			LocalDate heute = LocalDate.now();
			int jahr = heute.getYear();
			int monat = heute.getMonthValue();
			int tag = heute.getDayOfMonth();

		// genau 18 Jahre alt
		alter = 18;
		kundeIni.setGeburtsdatum(LocalDate.of(jahr - alter, monat, tag));
		assertEquals(kundeIni.getClass() + " hat genau das Alter " + alter,
				18, kundeIni.berechneAlter());

		
		// genau 60 Jahre alt
		alter = 60;
		kundeIni.setGeburtsdatum(LocalDate.of(jahr - alter, monat, tag));
		assertEquals(kundeIni.getClass() + " hat genau das Alter " + alter,
				alter, kundeIni.berechneAlter());

		// genau 60 Jahre minus 1 Tag alt
		alter = 60;
		kundeIni.setGeburtsdatum(LocalDate.of(jahr - alter, monat, tag + 1));
		assertEquals(kundeIni.getClass()
				+ " hat das Alter 1 Tag vor dem 60. Geb.", 59,
				kundeIni.berechneAlter());

		// 71 Jahre alt
		alter = 71;
		kundeIni.setGeburtsdatum(LocalDate.of(jahr - alter, monat, tag));
		assertEquals(kundeIni.getClass() + " hat das Alter " + alter, alter,
				kundeIni.berechneAlter());
	}


}