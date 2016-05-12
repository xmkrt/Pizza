import java.awt.Color;

public class Koch extends Angestellter {
	private String nachname;
	private String vorname;
	private Color farbeSchuerze;

	public Koch() {
		this(null, null, null);
	}
	
	public Koch(String nachname, String vorname, Color farbeSchuerze) {
		this.nachname = nachname;
		this.vorname = vorname;
		this.farbeSchuerze = farbeSchuerze;
	}

	public void setNachname(String nachname) {
		if (nachname != null)
			this.nachname = nachname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setVorname(String vorname) {
		if (vorname !=  null)
			this.vorname = vorname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setFarbeSchuerze(Color farbeSchuerze) {
		if (farbeSchuerze != null)
			this.farbeSchuerze = farbeSchuerze;
	}

	public Color getFarbeSchuerze() {
		return farbeSchuerze;
	}

	public String toString() {
		return "Nachname: " + nachname + "  Vorname: " + vorname + " Schuerzenfarbe: " + farbeSchuerze;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((farbeSchuerze == null) ? 0 : farbeSchuerze.hashCode());
		result = prime * result + ((nachname == null) ? 0 : nachname.hashCode());
		result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
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
		Koch check;
		check = (Koch) obj;
		if (this.nachname.equals(check.getNachname()) && this.vorname.equals(check.getVorname())
				&& this.farbeSchuerze.equals(check.getFarbeSchuerze()))
			return true;
		else
			return false;
	}
}
