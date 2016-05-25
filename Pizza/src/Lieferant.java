import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Lieferant extends Angestellter {

    public Lieferant() {
        this(null, null);
    }

    public Lieferant(String nachname, String vorname) {
        this(nachname, vorname, null, 0, null);
    }

    public Lieferant(String nachname, String vorname, String strasse, int hausNr, String personalNummer) {
        super(nachname, vorname, strasse, hausNr, personalNummer);
    }

    @Override
    public String arbeiten() {
        Random zufall = new Random();
        if (aktuellerKunde == null)
            return "Dienstleistung von Lieferant " + personalNummer + ": Keine Bestellung vorhanden.";
        if (aktuellerKunde.getBestellung().getStatus() != "fertig")
            return "Dienstleistung von Lieferant " + personalNummer + ": Keine Bestellung zum Abarbeiten vorhanden.";
        else {
            aktuellerKunde.getBestellung().setStatus("ausgeliefert");
            int z = zufall.nextInt(60);
            aktuellerKunde.getBestellung().setZeitstempelAuslieferung(aktuellerKunde.getBestellung().getZeitstempelBestellung().plusMinutes(z));
            return "Fahre zu Kunde " + aktuellerKunde.getNachname() + "\nFahrtzeit: " + z + " Minuten\nDienstleistung vom Lieferant " + personalNummer + ": Bestellung fertig um "
                    + (aktuellerKunde.getBestellung().getZeitstempelBestellung().plusMinutes(z).format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"))) + " Uhr";
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
