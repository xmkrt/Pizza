
public class PastaVO extends GerichtVO{
    private int pastaSorte;

    public PastaVO() {
        this(0, null, null, 0.0f, 0);
    }

    public PastaVO(int nummer, String name, String[] zutaten, float preis, int pastaSorte) {
        super(nummer, name, zutaten, preis);
        this.pastaSorte = pastaSorte;
    }

    @Override
    public int getGerichtNummer() {
        return pastaSorte * 100 + nummer;
    }

    @Override
    public String getGerichtName() {
        StringBuilder ausgabe = new StringBuilder();
        ausgabe.append("Pasta ");
        ausgabe.append(name);
        switch (pastaSorte){
            case 4:
                ausgabe.append(" - Spaghetti");
                break;
            case 5:
                ausgabe.append(" - Tortellini");
                break;
            case 6:
                ausgabe.append(" - Gnocchi");
                break;
        }
        return ausgabe.toString();
    }

    public Object clone() {
        PastaVO neuePasta = new PastaVO(this.nummer, this.name, this.zutaten, this.preis, this.pastaSorte);
        return neuePasta;
    }

    public int getPastaSorte() {
        return pastaSorte;
    }

    public void setPastaSorte(int pastaSorte) {
        this.pastaSorte = pastaSorte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PastaVO pastaVO = (PastaVO) o;

        return pastaSorte == pastaVO.pastaSorte;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + pastaSorte;
        return result;
    }
}
