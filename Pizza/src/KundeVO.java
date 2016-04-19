import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class KundeVO {
	private static int naechstID = 0;
	private int id;
	private String nachname;
	private String vorname;
	private String geschlecht;
	private LocalDate geburtsdatum;
	private short alter;

	public KundeVO() {
		this("", "", "", LocalDate.now());
	}

	public KundeVO(String nachname, String vorname, String geschlecht, LocalDate geburtsdatum) {
		id = naechstID;
		naechstID++;
		setNachname(nachname);
		setVorname(vorname);
		setGeschlecht(geschlecht);
		setGeburtsdatum(geburtsdatum);
	}

	public KundeVO(String nachname, String vorname, String geschlecht) {
		this(nachname, vorname, geschlecht, LocalDate.now());
	}

	public KundeVO(String nachname, String vorname) {
		this(nachname, vorname, "männlich", LocalDate.now());
	}

	public void setGeburtsdatum(LocalDate geburtsdatum) {
		if (berechneAlter(geburtsdatum) > 17)
			this.geburtsdatum = geburtsdatum;
		else
			this.geburtsdatum = LocalDate.now();
	}

	public LocalDate getGeburtsdatum() {
		return geburtsdatum;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		if (nachname != "")
			this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		if (vorname != "")
			this.vorname = vorname;
	}

	public String getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(String geschlecht) {
		if (geschlecht == "männlich" || geschlecht == "weiblich")
			this.geschlecht = geschlecht;
	}

	public String getGeburtsdatumStr() {
		return geburtsdatum.format(DateTimeFormatter.ofPattern("dd.MMM.yyyy"));
	}

	public int getId() {
		return id;
	}

	public short getAlter() {
		return alter;
	}

	public short berechneAlter(LocalDate geburtsdatum) {
		Period zeit = Period.between(geburtsdatum, LocalDate.now());
		zeit.getYears();
		return (short) zeit.getYears();
	}

}
