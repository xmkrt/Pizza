package de.thb.fim.pizzaPronto.datenobjekte;

import de.thb.fim.pizzaPronto.datenobjekte.exceptions.KundeKeinGeburtsdatumException;
import de.thb.fim.pizzaPronto.datenobjekte.exceptions.KundeZuJungException;
import de.thb.fim.pizzaPronto.logik.Bestellung;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class KundeVO extends PersonVO {
    private static int naechsteID = 0;
    private int id;
    private Geschlecht geschlecht;
    private LocalDate geburtsdatum;
    private Bestellung bestellung;

/*    public KundeVO() {
        this(null, null, null, 0, null, null, null);
    }*/

    public KundeVO(String nachname, String vorname) throws KundeZuJungException, KundeKeinGeburtsdatumException {
        this(nachname, vorname, null, 0, null, null, null);
    }

    public KundeVO(String nachname, String vorname, Geschlecht geschlecht) throws KundeKeinGeburtsdatumException, KundeZuJungException {
        this(nachname, vorname, null, 0, geschlecht, null, null);
    }

    public KundeVO(String nachname, String vorname, String strasse, int hausNr, Geschlecht geschlecht, LocalDate geburtsdatum, Bestellung bestellung) throws KundeZuJungException, KundeKeinGeburtsdatumException {
        super(nachname, vorname, strasse, hausNr);
        id = naechsteID++;
        this.geschlecht = geschlecht;
        this.bestellung = bestellung;
        setGeburtsdatum(geburtsdatum);
    }

    @Override
    public int hashCode() {
        int hc = super.hashCode();
        hc = 31 * hc + id;
        hc = 31 * hc + (geschlecht != null ? geschlecht.hashCode() : 0);
        hc = 31 * hc + (geburtsdatum != null ? geburtsdatum.hashCode() : 0);
        hc = 31 * hc + (bestellung != null ? bestellung.hashCode() : 0);
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

    public void setGeburtsdatum(LocalDate geburtsdatum) throws KundeKeinGeburtsdatumException, KundeZuJungException {
        this.geburtsdatum = geburtsdatum;
        short alter = this.berechneAlter();
        if (alter < 18)
            throw new KundeZuJungException("Kunde zu jung");
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public Geschlecht getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(Geschlecht geschlecht) {
        this.geschlecht = geschlecht;
    }

    private String getGeburtsdatumStr() throws KundeKeinGeburtsdatumException {
            return geburtsdatum.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public int getId() {
        return id;
    }

    public short berechneAlter() throws KundeKeinGeburtsdatumException {

            Period zeit = Period.between(this.getGeburtsdatum(), LocalDate.now());
        //if (!zeit.isNegative())
        return (short) zeit.getYears();
    }

    @Override
    public String toString() {
        StringBuilder ausgabe = new StringBuilder();
        ausgabe.append("\nID: ");
        ausgabe.append(id);
        ausgabe.append(super.toString());
        ausgabe.append("\n");
        ausgabe.append("Alter: ");
        try {
            ausgabe.append(berechneAlter());
        } catch (KundeKeinGeburtsdatumException e) {
            System.out.println(e.getMessage());
        }


        if (!hasBestellung())
            ausgabe.append("\nBestellung vorhanden");
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