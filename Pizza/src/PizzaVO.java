import java.util.Arrays;

public class PizzaVO {
	private String name;
	private float preis;
	private String[] zutaten;
	
	public PizzaVO() {
		this(null, 0.0F, null);
	}
	
	
	public PizzaVO(String name, float preis, String[] zutaten) {
		this.name = name;
		this.preis = preis;
		this.zutaten = zutaten;
	}	
	
	public Object clone(){
		PizzaVO neuePizza = new PizzaVO(this.name, this.preis, this.zutaten);
		return neuePizza;		
	}
	
	public int hashCode() {
		final int hashMultiplier = 31;
		int hc = 1;
		hc = hashMultiplier * hc + ((name == null) ? 0 : name.hashCode());
		hc = hashMultiplier * hc + Float.floatToIntBits(preis);
		hc = hashMultiplier * hc + Arrays.hashCode(zutaten);
		return hc;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PizzaVO check;
		check = (PizzaVO)obj;
		if(this.name.equals(check.getName()) && this.preis == check.getPreis() && this.zutaten.equals(check.getZutaten()))
			return true;
		else 
			return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null)
			this.name = name;
	}

	public float getPreis() {
		return preis;
	}

	public void setPreis(float preis) {
		if (preis >= 0.0F)
			this.preis = preis;
	}

	public String[] getZutaten() {
		return zutaten;
	}

	public void setZutaten(String[] zutaten) {
		if (zutaten != null)
			this.zutaten = zutaten;
	}

	public String toString() {
		return "Name: " + name + " Preis: " + preis + " Zutaten: " + Arrays.toString(zutaten);
	}
}
