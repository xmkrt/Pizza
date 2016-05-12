
public class Angestellter extends PersonVO {
    protected int urlaubsTage;
    protected float gehalt;
    protected String personalNummer;
    protected KundeVO aktuellerKunde;

    public Angestellter(String nachname, String vorname, String strasse, int hausNr, String personalNummer) {
        super(nachname, vorname, strasse, hausNr);
        this.personalNummer = personalNummer;
    }

    public Angestellter(String nachname, String vorname) {
        super(nachname, vorname);
    }

    public Angestellter() {
        super();
    }

    public String arbeitetfuerKunde(KundeVO kunde){
        aktuellerKunde = kunde;
        return aktuellerKunde + arbeiten();
    }

    public String arbeiten(){
        return "Dienstleistung aus Angestellter";
    }

    @Override
    public String toString() {
        return super.toString() +"Urlaubstage: " + urlaubsTage +  "\nGehalt: " + gehalt + "Personalnummer: " + personalNummer + "\nAktueller Kunde: " + aktuellerKunde;
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
