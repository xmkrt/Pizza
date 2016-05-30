import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Bestellung {
    private static final int MAX_GERICHTE = 10;

    private LocalDateTime zeitstempelBestellung;
    private LocalDateTime zeitstempelAuslieferung;
    private int index;
    private GerichtVO[] warenkorb;
    private KundeVO kunde;
    private String status;

    public static int getMAX_GERICHTE() {
        return MAX_GERICHTE;
    }

    public Bestellung() {
        this(null, null);
    }

    public Bestellung(LocalDateTime bestellung, KundeVO kunde) {
        this.kunde = kunde;
        setZeitstempelBestellung(bestellung);
        index = 0;
        status = "aufgegeben";
        warenkorb = new GerichtVO[MAX_GERICHTE];
    }

    public static int getMaxGerichte() {
        return MAX_GERICHTE;
    }

    public void hinzufuegenGericht(GerichtVO gericht) {
        if (index < MAX_GERICHTE && gericht != null)
            warenkorb[index++] = gericht;
    }

    public void loescheLetztesGericht() {
        warenkorb[--index] = null;
    }

    public GerichtVO getGericht(int index) {
        return warenkorb[index];
    }

    public int getAnzGerichte() {
        return index;
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
            return "\nhat keine Bestellung";

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
        result = prime * result + index;
        result = prime * result + Arrays.hashCode(warenkorb);
        result = prime * result + ((zeitstempelAuslieferung == null) ? 0 : zeitstempelAuslieferung.hashCode());
        result = prime * result + ((zeitstempelBestellung == null) ? 0 : zeitstempelBestellung.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bestellung other = (Bestellung) obj;
        if (index != other.index)
            return false;
        if (!Arrays.equals(warenkorb, other.warenkorb))
            return false;
        if (zeitstempelAuslieferung == null) {
            if (other.zeitstempelAuslieferung != null)
                return false;
        } else if (!zeitstempelAuslieferung.equals(other.zeitstempelAuslieferung))
            return false;
        if (zeitstempelBestellung == null) {
            if (other.zeitstempelBestellung != null)
                return false;
        } else if (!zeitstempelBestellung.equals(other.zeitstempelBestellung))
            return false;
        return true;
    }

    public int getIndex() {
        return index;
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
