import java.awt.Color;

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
        return vorname + " " + nachname + "kocht!";
    }

    public void erstelltSpeisekarte() {
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

