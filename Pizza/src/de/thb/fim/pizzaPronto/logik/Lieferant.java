package de.thb.fim.pizzaPronto.logik;

import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Lieferant extends Angestellter implements Fahrer {

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
        StringBuilder ausgabe = new StringBuilder();
        if (aktuellerKunde == null || aktuellerKunde.getBestellung() == null) {
            ausgabe.append("Dienstleistung von Lieferant ");
            ausgabe.append(personalNummer);
            ausgabe.append(": Keine Bestellung vorhanden.");
            return ausgabe.toString();
        }
        if (aktuellerKunde.getBestellung().getStatus() != "fertig") {
            ausgabe.append("Dienstleistung von Lieferant ");
            ausgabe.append(personalNummer);
            ausgabe.append(": Keine Bestellung zum Abarbeiten vorhanden.");
            return ausgabe.toString();
        } else {
            aktuellerKunde.getBestellung().setStatus("ausgeliefert");
            int x = fahreFahrzeug();
            //Auslieferungszeit setzen
            aktuellerKunde.getBestellung().setZeitstempelAuslieferung(aktuellerKunde.getBestellung().getZeitstempelBestellung().plusMinutes(x));
            String auszeit = (aktuellerKunde.getBestellung().getZeitstempelAuslieferung().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")));
            ausgabe.append("Fahre zu Kunde ");
            ausgabe.append(aktuellerKunde.getVorname());
            ausgabe.append(" ");
            ausgabe.append(aktuellerKunde.getNachname());
            ausgabe.append("\nFahrtzeit: ");
            ausgabe.append(x);
            ausgabe.append(" Minuten\nDienstleistung vom Lieferant ");
            ausgabe.append(personalNummer);
            ausgabe.append(": Bestellung fertig um ");
            ausgabe.append(auszeit);
            ausgabe.append(" Uhr");
            return ausgabe.toString();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int fahreFahrzeug() {
        return new Random().nextInt(MAX_FAHRZEIT);
    }
}
