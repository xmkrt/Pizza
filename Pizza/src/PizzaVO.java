import java.util.Arrays;

public class PizzaVO {
	private String name;
	private float preis;
	private String[] zutaten;

	public PizzaVO() {
		this(null, null, 0.0F);
	}

	public PizzaVO(String name, String[] zutaten, float preis) {
		setName(name);
		setPreis(preis);
		setZutaten(zutaten);
	}
	
	public Object clone(){
		PizzaVO neuePizza = new PizzaVO(this.name, this.zutaten, this.preis);
		return neuePizza;		
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(preis);
		result = prime * result + Arrays.hashCode(zutaten);
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
		PizzaVO other = (PizzaVO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(preis) != Float.floatToIntBits(other.preis))
			return false;
		if (!Arrays.equals(zutaten, other.zutaten))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != "")
			this.name = name;
	}

	public float getPreis() {
		return preis;
	}

	public void setPreis(float preis) {
		if (preis >= 0)
			this.preis = preis;
	}

	public String[] getZutaten() {
		return zutaten;
	}

	public void setZutaten(String[] zutaten) {
		this.zutaten = zutaten;
	}

	public String toString() {
		return "PizzaVO [Name: " + name + ", Preis: " + preis + " Zutaten:" + Arrays.toString(zutaten);
	}
	

}
