package entities;

public class Filiere {
	int id;
	String code;
	String libele;
	
/*	List<Module> modules;
	
	public Filiere() {
		modules = new ArrayList<Module>();
	}
	
	public List<Module> getModules() {
		return modules;
	}
	
	
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
*/
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLibele() {
		return libele;
	}
	public void setLibele(String libele) {
		this.libele = libele;
	}
}
