package entities;

public class Admin extends User{
	@Override
	public String getType() {
		return "admin";
	}
}
