import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public abstract class Angestellter extends PersonVO {
    protected int urlaubsTage;
    protected float gehalt;
    protected String personalNummer;
    protected KundeVO aktuellerKunde;

    public Angestellter() {
        this("", "");
    }

    public Angestellter(String nachname, String vorname) {
        this(nachname, vorname, "", 0, "");
    }

    public Angestellter(String nachname, String vorname, String strasse, int hausNr, String personalNummer) {
        super(nachname, vorname, strasse, hausNr);
        this.personalNummer = personalNummer;
    }

    public String arbeitetfuerKunde(KundeVO kunde) {
        aktuellerKunde = kunde;
        return arbeiten() + aktuellerKunde;
    }

    public abstract String arbeiten();

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00€");
        StringBuilder ausgabe = new StringBuilder();
        ausgabe.append(super.toString());
        ausgabe.append("\nUrlaubstage: ");
        ausgabe.append(urlaubsTage);
        ausgabe.append("\nGehalt: ");
        ausgabe.append(df.format(gehalt));
        ausgabe.append("\nPersonalnummer: ");
        ausgabe.append(personalNummer);
        ausgabe.append("\narbeitet für ID: ");
        if (aktuellerKunde != null) {
            ausgabe.append(aktuellerKunde.getId());
            ausgabe.append("\nName: ");
            ausgabe.append(aktuellerKunde.getVorname());
            ausgabe.append(" ");
            ausgabe.append(aktuellerKunde.getNachname());
            ausgabe.append("\n");
            ausgabe.append(aktuellerKunde.getStrasse());
            ausgabe.append(" ");
            ausgabe.append(aktuellerKunde.getHausNr());
            ausgabe.append("\nAlter: ");
            ausgabe.append(aktuellerKunde.berechneAlter());
            if (aktuellerKunde.getBestellung() != null) {
                ausgabe.append("\nBestellung vom: ");
                ausgabe.append(aktuellerKunde.getBestellung().getZeitstempelBestellung().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")));
            }
        }

        return ausgabe.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Angestellter that = (Angestellter) o;

        return personalNummer.equals(that.personalNummer);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + personalNummer.hashCode();
        return result;
    }

    public int getUrlaubsTage() {
        return urlaubsTage;
    }

    public void setUrlaubsTage(int urlaubsTage) {
        this.urlaubsTage = urlaubsTage;
    }

    public float getGehalt() {
        return gehalt;
    }

    public void setGehalt(float gehalt) {
        this.gehalt = gehalt;
    }

    public String getPersonalNummer() {
        return personalNummer;
    }

    public void setPersonalNummer(String personalNummer) {
        this.personalNummer = personalNummer;
    }

    public KundeVO getAktuellerKunde() {
        return aktuellerKunde;
    }
}
