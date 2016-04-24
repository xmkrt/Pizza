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
		this(null, null, null, null);
	}
	
	public KundeVO(String nachname, String vorname) {
		this(nachname, vorname, null, null);
	}
	
	public KundeVO(String nachname, String vorname, String geschlecht) {
		this(nachname, vorname, geschlecht, null);
	}
	
	public KundeVO(String nachname, String vorname, String geschlecht, LocalDate geburtsdatum) {
		id = naechstID;
		naechstID++;
		setNachname(nachname);
		setVorname(vorname);
		setGeschlecht(geschlecht);
		setGeburtsdatum(geburtsdatum);
	}	
	
	public int hashCode() {
		final int hashMultiplier = 47;
		int hc = 1;
		hc = hashMultiplier * hc + alter;
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
		check = (KundeVO)obj;
		if(this.id == check.getId())			
			return true;
		else 
			return false;
	}

	public void setGeburtsdatum(LocalDate geburtsdatum) {
		short alter = berechneAlter(geburtsdatum);
		if (alter > 17){
			this.geburtsdatum = geburtsdatum;
			this.alter = alter;
		}
		else
			this.geburtsdatum = null;
			alter = 0;
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
		else return "";
	}

	public int getId() {
		return id;
	}

	public short getAlter() {
		return alter;
	}

	public short berechneAlter(LocalDate geburtsdatum) {
		if (geburtsdatum != null){
			Period zeit = Period.between(geburtsdatum, LocalDate.now());
			if (!zeit.isNegative())
				return (short) zeit.getYears();
			else return -1;
			}
		else return -1;
	}
	
	public String toString(){
		
		return "ID: " + id + " Name: " + vorname  + " " + nachname 
				+ "\n" + getGeburtsdatumStr() + " Alter: " + alter
				+ " " + geschlecht;
	}
}
