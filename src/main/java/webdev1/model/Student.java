package webdev1.model;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
@Entity
public class Student extends User {
	private float gpa;
	private int graduationYear;
	public Student() {
		super();
	}
	public Student(Integer id, String username, String password, String firstName, String lastName, Date dOB) {
		super(id, username, password, firstName, lastName, dOB);
	}
	public Student(Integer id, String username, String password, String firstName, String lastName, Date dOB, int graduationYear) {
		super(id, username, password, firstName, lastName, dOB);
		this.graduationYear = graduationYear;
	}
	public Student(Integer id, String username, String password, String firstName, String lastName, Date dOB, float gpa) {
		super(id, username, password, firstName, lastName, dOB);
		this.gpa = gpa;
	}
	public Student(Integer id, String username, String password, String firstName, String lastName, Date dOB, float gpa, int graduationYear) {
		super(id, username, password, firstName, lastName, dOB);
		this.gpa = gpa;
		this.graduationYear = graduationYear;
	}
	public float getGpa() {
		return gpa;
	}
	public void setGpa(float gpa) {
		this.gpa = gpa;
	}
	public int getGraduationYear() {
		return graduationYear;
	}
	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}
	
}
