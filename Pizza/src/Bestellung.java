import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bestellung {
	private LocalDateTime zeitstempelBestellung;
	private LocalDateTime zeitstempelAuslieferung;
	
	public String toString() {
		return "Bestellung von " + zeitstempelBestellung.format(DateTimeFormatter.ofPattern("dd.MMM.yyyy hh:ss")) + "\nAuslieferung: "
				+ zeitstempelAuslieferung.format(DateTimeFormatter.ofPattern("dd.MMM.yyyy.hh.ss"))+ "\n";
	}

	public Bestellung(LocalDateTime zeitstempelBestellung, LocalDateTime zeitstempelAuslieferung) {
		this.zeitstempelBestellung = zeitstempelBestellung;
		this.zeitstempelAuslieferung = zeitstempelAuslieferung;
	}
	
	public Bestellung() {
		this(null,null);
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((zeitstempelAuslieferung == null) ? 0 : zeitstempelAuslieferung.hashCode());
		result = prime * result + ((zeitstempelBestellung == null) ? 0 : zeitstempelBestellung.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bestellung other = (Bestellung) obj;
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
	
	
	

}
