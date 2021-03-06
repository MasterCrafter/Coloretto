package domein;

public class Kaart {

	private int nr;
    private String kleur;
    private KaartType type;
    
   	public Kaart(int nr, String kleur, KaartType type) {
   		this.nr = nr;
		this.kleur = kleur;
		this.type = type;
	}
   	
	@Override
	public String toString() {
		return "Kaart [ kleur=" + kleur + ", type=" + type + " ]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kleur == null) ? 0 : kleur.hashCode());
		result = prime * result + nr;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Kaart other = (Kaart) obj;
		if (kleur == null) {
			if (other.kleur != null)
				return false;
		} else if (!kleur.equals(other.kleur))
			return false;
		if (nr != other.nr)
			return false;
		if (type != other.type)
			return false;
		return true;
	}



	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public String getKleur() {
		return kleur;
	}

	public void setKleur(String kleur) {
		this.kleur = kleur;
	}

	public KaartType getType() {
		return type;
	}

	public void setType(KaartType type) {
		this.type = type;
	}
   
   	
    
}