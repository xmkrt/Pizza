import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class KundeVO {
	private static int naechsteID = 0;
	private int id;
	private String nachname;
	private String vorname;
	private String geschlecht;
	private LocalDate geburtsdatum;
	private Bestellung bestellung;

	public KundeVO() {
		this(null, null, null, null);
	}

	public KundeVO(String nachname, String vorname) {
		this(nachname, vorname, null, null);
	}

	public KundeVO(String nachname, String vorname, String geschlecht) {
		this(nachname, vorname, geschlecht, null);
	}

	public KundeVO(String nachname, String vorname, String geschlecht, LocalDate geburtsdatum) {
		id = naechsteID++;
		this.nachname = nachname;
		this.vorname = vorname;
		this.geschlecht = geschlecht;
		setGeburtsdatum(geburtsdatum);
	}

	public int hashCode() {
		final int hashMultiplier = 47;
		int hc = 1;
		hc = hashMultiplier * hc + ((geburtsdatum == null) ? 0 : geburtsdatum.hashCode());
		hc = hashMultiplier * hc + ((geschlecht == null) ? 0 : geschlecht.hashCode());
		hc = hashMultiplier * hc + ((nachname == null) ? 0 : nachname.hashCode());
		hc = hashMultiplier * hc + ((vorname == null) ? 0 : vorname.hashCode());
		return hc;
	}

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
		this.bestellung = new Bestellung(LocalDateTime.now(), null, this);
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

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		if (nachname != null)
			this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		if (vorname != null)
			this.vorname = vorname;
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

	public String toString() {

		return "ID: " + id + " Name: " + vorname + " " + nachname + "\n" + getGeburtsdatumStr() + " Alter: "
				+ berechneAlter() + " " + geschlecht;
	}

	public static int getNaechsteID() {
		return naechsteID;
	}

}