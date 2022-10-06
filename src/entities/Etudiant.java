package entities;

public class Etudiant extends User{
	String cne;
	Filiere filiere;
	
	
	
	public String getCne() {
		return cne;
	}
	public void setCne(String cne) {
		this.cne = cne;
	}
	public Filiere getFiliere() {
		return filiere;
	}
	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	
	@Override
	public String getType() {
		return "etudiant";
	}
}
