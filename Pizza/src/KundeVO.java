import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class KundeVO {
	private String nachname;
	private String vorname;
	private String geschlecht;
	private LocalDate geburtsdatum;
	
	public KundeVO(){
		
	}
	
	public KundeVO(String nachname, String vorname, String geschlecht, LocalDate gerburtsdatum){
		setNachname(nachname);
		setVorname(vorname);
		setGeschlecht(geschlecht);
		setGeburtsdatum(gerburtsdatum);
	}
	
	public KundeVO(String nachname, String vorname, String geschlecht){
		setNachname(nachname);
		setVorname(vorname);
		setGeschlecht(geschlecht);
	}
	
	public KundeVO(String nachname, String vorname){
		setNachname(nachname);
		setVorname(vorname);
	}
	
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getGeschlecht() {
		return geschlecht;
	}
	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}
	public LocalDate getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
	public String getGeburtsdatumStr(){
		return geburtsdatum.format(DateTimeFormatter.ofPattern("dd.MMM.yyyy"));
	}
}
