package de.thb.fim.pizzaPronto.logik;

import de.thb.fim.pizzaPronto.datenobjekte.GerichtVO;
import de.thb.fim.pizzaPronto.datenobjekte.KundeVO;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Bestellung implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime zeitstempelBestellung;
    private LocalDateTime zeitstempelAuslieferung;
    private LinkedList<GerichtVO> warenkorb;
    private KundeVO kunde;
    private String status;

    public Bestellung() {
        this(null, null);
    }

    public Bestellung(LocalDateTime bestellung, KundeVO kunde) {
        this.kunde = kunde;
        setZeitstempelBestellung(bestellung);
        status = "aufgegeben";
        warenkorb = new LinkedList<GerichtVO>();
    }

    public void hinzufuegenGericht(GerichtVO gericht) {
        warenkorb.add(gericht);
    }

    public void loescheGericht(GerichtVO gericht) {
        warenkorb.remove(gericht);
    }

    public GerichtVO getGericht(int index) {
        return warenkorb.get(index);
    }

    public int getAnzGerichte() {
        return warenkorb.size();
    }

    public float berechneGesamtPreis() {
        float gesamtpreis = 0;
        for (GerichtVO position : warenkorb) {
            if (position != null) {
                gesamtpreis += position.getPreis();
            }
        }
        return gesamtpreis;
    }

    @Override
    public String toString() {
        StringBuilder wk = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.00€");
        wk.append("\nBestellung vom ");
        wk.append(zeitstempelBestellung.format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")));
        wk.append("\n");

        for (GerichtVO position : warenkorb) {
            if (position != null) {
                wk.append(position.getGerichtNummer());
                wk.append(" - ");
                wk.append(position.getGerichtName());
                if (position.getZutaten() != null) {
                    wk.append("\n");
                    wk.append(position.toStringZutaten());
                }
                wk.append("\nPreis: ");
                wk.append(df.format(position.getPreis()));
                wk.append("\n");
            }
        }
        wk.append("\nGesamtpreis: ");
        wk.append(df.format(berechneGesamtPreis()));
        if (wk == null)
            return "\nBestellung";

        else {
            if (zeitstempelAuslieferung != null) {
                wk.append(" mit Lieferung am ");
                wk.append(zeitstempelAuslieferung.format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")));
            }
            wk.append("\n");
            return wk.toString();
        }
    }

    public LocalDateTime getZeitstempelBestellung() {
        return zeitstempelBestellung;
    }

    public void setZeitstempelBestellung(LocalDateTime zeitstempelBestellung) {
        this.zeitstempelBestellung = zeitstempelBestellung;
    }

    public LocalDateTime getZeitstempelAuslieferung() {
        return zeitstempelAuslieferung;
    }

    public void setZeitstempelAuslieferung(LocalDateTime zeitstempelAuslieferung) {
        this.zeitstempelAuslieferung = zeitstempelAuslieferung;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + warenkorb.hashCode();
        result = prime * result + ((zeitstempelAuslieferung == null) ? 0 : zeitstempelAuslieferung.hashCode());
        result = prime * result + ((zeitstempelBestellung == null) ? 0 : zeitstempelBestellung.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bestellung that = (Bestellung) o;

        if (zeitstempelBestellung != null ? !zeitstempelBestellung.equals(that.zeitstempelBestellung) : that.zeitstempelBestellung != null)
            return false;
        if (zeitstempelAuslieferung != null ? !zeitstempelAuslieferung.equals(that.zeitstempelAuslieferung) : that.zeitstempelAuslieferung != null)
            return false;
        if (warenkorb != null ? !warenkorb.equals(that.warenkorb) : that.warenkorb != null) return false;
        if (kunde != null ? !kunde.equals(that.kunde) : that.kunde != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;

    }

    public KundeVO getKunde() {
        return kunde;
    }

    public void setKunde(KundeVO kunde) {
        this.kunde = kunde;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
