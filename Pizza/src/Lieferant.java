
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
        return vorname + " " + nachname + "liefert aus";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
