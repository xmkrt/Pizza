
public class PizzaVO {
	private String name;
	private float preis;
	private String[] zutaten;

	public PizzaVO() {
		this("Pizza", 5.00f, new String[] { "", "" });
	}

	public PizzaVO(String name, float preis, String[] zutaten) {
		setName(name);
		setPreis(preis);
		setZutaten(zutaten);
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

}
