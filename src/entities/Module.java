package entities;

public class Module {
	int id;
	String libele;
	String code;
	//int coefficient;
	Filiere filiere;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibele() {
		return libele;
	}
	public void setLibele(String libele) {
		this.libele = libele;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
//	public int getCoefficient() {
//		return coefficient;
//	}
//	public void setCoefficient(int coefficient) {
//		this.coefficient = coefficient;
//	}
	public Filiere getFiliere() {
		return filiere;
	}
	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
}
