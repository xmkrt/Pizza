package de.thb.fim.pizzaPronto.logik;

import de.thb.fim.pizzaPronto.datenobjekte.SpeiseKarte;
import de.thb.fim.pizzaPronto.logik.exceptions.BestellungFalscherStatusException;
import de.thb.fim.pizzaPronto.logik.exceptions.KeinKundeException;
import de.thb.fim.pizzaPronto.logik.exceptions.KeineBestellungException;

import java.awt.*;

public class Koch extends Angestellter {
    private Color farbeSchuerze;

    public Koch() {
        this(null, null, null);
    }

    public Koch(String nachname, String vorname, Color farbeSchuerze) {
        this(nachname, vorname, null, 0, null, farbeSchuerze);
    }

    public Koch(String nachname, String vorname, String strasse, int hausNr, String personalNummer) {
        this(nachname, vorname, strasse, hausNr, personalNummer, Color.BLACK);
    }

    public Koch(String nachname, String vorname, String strasse, int hausNr, String personalNummer, Color farbeSchuerze) {
        super(nachname, vorname, strasse, hausNr, personalNummer);
        this.farbeSchuerze = farbeSchuerze;
    }

    public String arbeiten() throws KeineBestellungException, BestellungFalscherStatusException, KeinKundeException {
        if (aktuellerKunde == null)
            throw new KeinKundeException("Kein Kunde!");
        if (getAktuellerKunde().getBestellung() == null)
            throw new KeineBestellungException();
        if (aktuellerKunde.getBestellung().getStatus() != "aufgegeben")
            throw new BestellungFalscherStatusException("Bestellung hat den falschen Status!");

        aktuellerKunde.getBestellung().setStatus("fertig");
        return "Dienstleistung vom Koch " + personalNummer + ": Bestellung fertig";

    }

    public SpeiseKarte erstelltSpeisekarte() {
        return new SpeiseKarte();
    }

    public void setFarbeSchuerze(Color farbeSchuerze) {
        if (farbeSchuerze != null)
            this.farbeSchuerze = farbeSchuerze;
    }

    public Color getFarbeSchuerze() {
        return farbeSchuerze;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSchürzenfarbe: " + farbeSchuerze;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + farbeSchuerze.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Koch koch = (Koch) o;

        return farbeSchuerze.equals(koch.farbeSchuerze);

    }
}

