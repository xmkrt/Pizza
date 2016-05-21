import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class KundeVO extends PersonVO {
    private static int naechsteID = 0;
    private int id;
    private String geschlecht;
    private LocalDate geburtsdatum;
    private Bestellung bestellung;

    public KundeVO() {
        this(null, null, null, 0, null, null, null);
    }

    public KundeVO(String nachname, String vorname) {
        this(nachname, vorname, null, 0, null, null, null);
    }

    public KundeVO(String nachname, String vorname, String geschlecht) {
        this(nachname, vorname, null, 0, geschlecht, null, null);
    }

    public KundeVO(String nachname, String vorname, String strasse, int hausNr, String geschlecht, LocalDate geburtsdatum, Bestellung bestellung) {
        super(nachname, vorname, strasse, hausNr);
        id = naechsteID++;
        this.geschlecht = geschlecht;
        this.bestellung = bestellung;
        setGeburtsdatum(geburtsdatum);
    }

    @Override
    public int hashCode() {
        final int hashMultiplier = 47;
        int hc = 1;
        hc = super.hashCode();
        hc = hashMultiplier * hc + ((geburtsdatum == null) ? 0 : geburtsdatum.hashCode());
        hc = hashMultiplier * hc + ((geschlecht == null) ? 0 : geschlecht.hashCode());
        return hc;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KundeVO check;
        check = (KundeVO) obj;
        return (this.id == check.getId());
    }

    public boolean hasBestellung() {
        return (bestellung != null);
    }

    public Bestellung getBestellung() {
        return bestellung;
    }

    public void setBestellung(Bestellung bestellung) {
        this.bestellung = bestellung;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
        short alter = this.berechneAlter();
        if (alter < 18)
            this.geburtsdatum = null;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        if (geschlecht == "männlich" || geschlecht == "weiblich")
            this.geschlecht = geschlecht;
    }

    private String getGeburtsdatumStr() {
        if (geburtsdatum != null)
            return geburtsdatum.format(DateTimeFormatter.ofPattern("dd.MMM.yyyy"));
        else
            return "";
    }

    public int getId() {
        return id;
    }

    public short berechneAlter() {
        if (this.getGeburtsdatum() != null) {
            Period zeit = Period.between(this.getGeburtsdatum(), LocalDate.now());
            if (!zeit.isNegative())
                if ((short) zeit.getYears() < 18)
                    return -1;
                else
                    return (short) zeit.getYears();
            return -1;
        } else
            return -1;
    }

    @Override
    public String toString() {
        StringBuilder ausgabe = new StringBuilder();
        ausgabe.append("ID: ");
        ausgabe.append(id);
        ausgabe.append("\n");
        ausgabe.append(super.toString());
        ausgabe.append("\n");
        ausgabe.append("Alter: ");
        ausgabe.append(berechneAlter());

        if (!hasBestellung())
            ausgabe.append("\nkeine Bestellung vorhanden");
        else {
            ausgabe.append("\nBestellung vorhanden\nBestellung vom ");
            ausgabe.append(bestellung.getZeitstempelBestellung().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")));
        }
        return ausgabe.toString();
    }

    public static int getNaechsteID() {
        return naechsteID;
    }

}