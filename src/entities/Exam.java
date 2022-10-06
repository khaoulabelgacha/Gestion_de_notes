package entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Exam {
	int id;
	Module module;
	Date dateExam;
	int coefficient;
	
	
	public int getCoefficient() {
		return coefficient;
	}
	
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public Date getDateExam() {
		return dateExam;
	}
	
	public String getMySQLDateExam(){
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(dateExam);
	}
	
	
	public void setDateExam(Date dateExam) {
		this.dateExam = dateExam;
	}
}
