package entities;

public class Prof extends User{
    Module module;
	
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	
	
	@Override
	public String getType() {
		return "prof";
	}
}
