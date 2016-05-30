import java.awt.Color;import java.lang.reflect.Field;import java.lang.reflect.Method;import java.lang.reflect.Modifier;import java.time.LocalDate;import java.time.LocalDateTime;import org.junit.BeforeClass;import org.junit.Test;import static org.junit.Assert.*;/** * Aerzte und stelleDiagnose wird getestet. *  * Voraussetzung Messung und SchlaflaborMessung sind ok *  * Zum Testen werden spezielle Assert-Befehle eingesetzt <br> *  * @author Gabriele Schmidt * @version 1.0 11.05.2015 */public class JUnitTestAngestellteArbeitenBestellung {	private static Class<Bestellung> myBestellungClass;	private static Bestellung myBestellung;	private static Angestellter[] angestellte = new Angestellter[2];	private static KundeVO kunde;	private static SpeiseKarte speisekarte;	private static Field warenkorb;	@BeforeClass	public static void setUpBeforeClass() throws Exception {		// Koch erstellen		angestellte[0] = new Koch("Bocuse", "Bruno", "Schlemmerweg", 13,				"Koch007");		// Lieferant erstellen		angestellte[1] = new Lieferant("Rasender", "Rudi", "Schnellstra�e",				200, "Lieferant01");		// Kunde		kunde = new KundeVO("Genuss", "Gini", "Haribostra�e", 32, "weiblich",				LocalDate.of(1995, 8, 8), null);		myBestellung = new Bestellung(LocalDateTime.now(), kunde);		speisekarte = new SpeiseKarte();		myBestellungClass = Bestellung.class;		warenkorb = myBestellungClass.getDeclaredField("warenkorb");	}	@Test	public void testBestellungWarenkorbDatentyp() {		assertTrue("Warenkorb in Bestellung ist Array und vom Typ GerichtVO",				warenkorb.getType().toString().equals("class [LGerichtVO;"));	}	@Test	public void testBestellungStatusAufgegeben() {		myBestellung = new Bestellung(LocalDateTime.now(), kunde);		assertEquals("Bestellungsstatus nach Objekterzeugung ist aufgegeben",				"aufgegeben", myBestellung.getStatus());	}	@Test	public void testBestellungHinzufuegenGericht() {		myBestellung = new Bestellung(LocalDateTime.now(), kunde);		// Ein Gericht der SpeiseKarte hinzuf�gen		myBestellung.hinzufuegenGericht(speisekarte.getGericht(0));		assertEquals("Anzahl der Gerichte in Bestellung: 1", 1,				myBestellung.getAnzGerichte());	}	@Test	public void testBestellungHinzufuegenZuVieleGerichte() {		myBestellung = new Bestellung(LocalDateTime.now(), kunde);		// Alle 18 Gerichte der SpeiseKarte hinzuf�gen		for (int i = 0; i <  Bestellung.getMAX_GERICHTE(); i++) {			myBestellung.hinzufuegenGericht(speisekarte.getGericht(i % speisekarte.getAnzGerichte()));		}		assertEquals("Anzahl der Gerichte in Bestellung: MAX_GERICHTE",				Bestellung.getMAX_GERICHTE(), myBestellung.getAnzGerichte());		myBestellung.hinzufuegenGericht(speisekarte.getGericht(0));			assertEquals(					"Index in Bestellung gleich der Anzahl der Gerichte",					myBestellung.getAnzGerichte(), myBestellung.getIndex());		}		@Test	public void testBestellungHinzufuegenAlleGerichte() {		myBestellung = new Bestellung(LocalDateTime.now(), kunde);		// Alle 18 Gerichte der SpeiseKarte hinzuf�gen		for (int i = 0; i < speisekarte.getAnzGerichte(); i++) {			myBestellung.hinzufuegenGericht(speisekarte.getGericht(i));		}		assertEquals("Anzahl der Gerichte in Bestellung: MAX_GERICHTE",				Bestellung.getMAX_GERICHTE(), myBestellung.getAnzGerichte());		for (int i = 0; i < myBestellung.getAnzGerichte(); i++) {			assertEquals(					"Gerichte in Bestellung gleich der ersten 10 Gerichte auf SpeiseKarte",					myBestellung.getGericht(i), speisekarte.getGericht(i));		}	}	@Test	public void testBestellungberechenGesamtPreis() {		float preis = 7.00f;		myBestellung = new Bestellung(LocalDateTime.now(), kunde);		// Alle 18 Gerichte der SpeiseKarte hinzuf�gen		for (int i = 0; i < Bestellung.getMAX_GERICHTE(); i++) {			myBestellung.hinzufuegenGericht(new PizzaVO(30, "Popeye",					new String[] { "Schinken", "Spinat", "Champignon", "Ei" },					preis, 1));		}		assertTrue("Gesamtpreis wird korrekt berechnet ",				(preis * Bestellung.getMAX_GERICHTE()) == myBestellung						.berechneGesamtPreis());	}	@Test	public void testBestellungloescheLetztesGericht() {		int index;		myBestellung = new Bestellung(LocalDateTime.now(), kunde);		index = myBestellung.getIndex();		myBestellung.hinzufuegenGericht(speisekarte.getGericht(0));		myBestellung.loescheLetztesGericht();		assertEquals(				"Nach Methode loescheLetztesGericht() in Bestellung ist Index erniedrigt",				index, myBestellung.getIndex());	}	public void testLoescheLetztesGerichtZuViel() {		int index;		myBestellung = new Bestellung(LocalDateTime.now(), kunde);		index = myBestellung.getIndex();		assertEquals(" Index ist 0", index, 0);		myBestellung.loescheLetztesGericht();		assertEquals(				"Nach Methode loescheLetztesGericht() in Bestellung ohne, dass ein Gericht da war, ist Index immer noch 0",				index, myBestellung.getIndex());	}	@Test	public void testAngestellteArbeitenFuerOhneBestellung() {		assertTrue(				"ArbeitenFuer Koch: Keine Bestellung vorhanden",				angestellte[0].arbeitetFuerKunde(kunde).contains(						"Keine Bestellung vorhanden"));		assertTrue(				"ArbeitenFuer Lieferant: Keine Bestellung vorhanden",				angestellte[1].arbeitetFuerKunde(kunde).contains(						"Keine Bestellung vorhanden"));	}	@Test	public void testAngestellteArbeitenFuerPersonalnummer() {		assertTrue(				"ArbeitenFuer Koch: Personalnummer in Ausgabe",				angestellte[0].arbeitetFuerKunde(kunde).contains(						angestellte[0].getPersonalNummer()));		assertTrue(				"ArbeitenFuer Lieferant: Personalnummer in Ausgabe",				angestellte[1].arbeitetFuerKunde(kunde).contains(						angestellte[1].getPersonalNummer()));	}	@Test	public void testAngestellteArbeitenFuerOhneKunde() {		assertTrue(				"ArbeitenFuer ohne Kunde Koch: Keine Bestellung vorhanden",				angestellte[0].arbeitetFuerKunde(null).contains(						"Keine Bestellung vorhanden"));		assertTrue(				"ArbeitenFuer ohne Kunde Lieferant: keine Bestellung vorhanden",				angestellte[1].arbeitetFuerKunde(null).contains(						"Keine Bestellung vorhanden"));	}	@Test	public void testKochArbeitenBestellungAufgegeben() {		myBestellung = new Bestellung(LocalDateTime.now(), kunde);		kunde.setBestellung(myBestellung);		myBestellung.setStatus("aufgegeben");		assertTrue("Koch arbeitet: Bestellung fertig", angestellte[0]				.arbeitetFuerKunde(kunde).contains("Bestellung fertig"));		assertEquals("Bestellungsstatus nach Koch arbeitet", "fertig",				myBestellung.getStatus());	}	@Test	public void testKochArbeitenBestellungFertig() {		myBestellung = new Bestellung(LocalDateTime.now(), kunde);		kunde.setBestellung(myBestellung);		myBestellung.setStatus("fertig");		assertTrue(				"Koch arbeitet: Keine Bestellung zum Abarbeiten",				angestellte[0].arbeitetFuerKunde(kunde).contains(						"Keine Bestellung zum Abarbeiten"));		assertEquals("Bestellungsstatus immer noch fertig", "fertig",				myBestellung.getStatus());	}	@Test	public void testKochArbeitenBestellungAusgeliefert() {		myBestellung = new Bestellung(LocalDateTime.now(), kunde);		kunde.setBestellung(myBestellung);		myBestellung.setStatus("ausgeliefert");		assertTrue(				"Koch arbeitet: Keine Bestellung zum Abarbeiten",				angestellte[0].arbeitetFuerKunde(kunde).contains(						"Keine Bestellung zum Abarbeiten"));	}	@Test	public void testLieferantArbeitenBestellungAufgegeben() {		myBestellung = new Bestellung(LocalDateTime.now(), kunde);		kunde.setBestellung(myBestellung);		myBestellung.setStatus("aufgegeben");		assertTrue(				"Lieferant arbeitet:  Keine Bestellung zum Abarbeiten",				angestellte[1].arbeitetFuerKunde(kunde).contains(						"Keine Bestellung zum Abarbeiten"));		assertEquals("Bestellungsstatus immer noch aufgegeben", "aufgegeben",				myBestellung.getStatus());	}	@Test	public void testLieferantArbeitenBestellungFertig() {		myBestellung = new Bestellung(LocalDateTime.now(), kunde);		kunde.setBestellung(myBestellung);		myBestellung.setStatus("fertig");		System.out.println(myBestellung.getZeitstempelAuslieferung());		assertTrue("Bestellung Zeitstempel Auslieferung vorher null",				myBestellung.getZeitstempelAuslieferung() == null);		assertTrue("Lieferant arbeitet: Bestellung fertig um", angestellte[1]				.arbeitetFuerKunde(kunde).contains("Bestellung fertig um"));		assertEquals("Bestellungsstatus ausgeliefert", "ausgeliefert",				myBestellung.getStatus());		assertTrue("Bestellung Zeitstempel Auslieferung vorhanden",				myBestellung.getZeitstempelAuslieferung() != null);	}	@Test	public void testLieferantArbeitenBestellungAusgeliefert() {		myBestellung = new Bestellung(LocalDateTime.now(), kunde);		kunde.setBestellung(myBestellung);		myBestellung.setStatus("ausgeliefert");		assertTrue(				"Lieferant arbeitet: Keine Bestellung zum Abarbeiten",				angestellte[1].arbeitetFuerKunde(kunde).contains(						"Keine Bestellung zum Abarbeiten"));	}}