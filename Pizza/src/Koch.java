import java.awt.*;

public class Koch extends Angestellter {
    private Color farbeSchuerze;

    public Koch() {
        this(null, null, null);
    }

    public Koch(String nachname, String vorname, Color farbeSchuerze) {
        super(nachname, vorname);
        this.farbeSchuerze = farbeSchuerze;
    }

    public String arbeiten() {
        if (aktuellerKunde == null)
            return "Dienstleistung vom Koch " + personalNummer + ": Keine Bestellung vorhanden.";
        if (aktuellerKunde.getBestellung().getStatus() != "aufgegeben")
            return "Dienstleistung vom Koch " + personalNummer + ": Keine Bestellung zum Abarbeiten vorhanden.";
        else {
            aktuellerKunde.getBestellung().setStatus("aufgegeben");
            return "Dienstleistung vom Koch " + personalNummer + ": Bestellung fertig";
        }
    }

    public Speisekarte erstelltSpeisekarte() {
        return new Speisekarte();
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

