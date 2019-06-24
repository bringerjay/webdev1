package webdev1.model;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
@Entity
public class Faculty extends User{

	public Faculty(Integer id, String username, String password, String firstName, String lastName, Date dOB,
			String office, Boolean tenure, List<Course> authoredCourses) {
		super(id, username, password, firstName, lastName, dOB);
		this.office = office;
		this.tenure = tenure;
		this.authoredCourses = authoredCourses;
	}
	private String office;
	private Boolean tenure;
	@OneToMany(mappedBy = "author")
	private List<Course> authoredCourses;
	public Faculty(Integer id, String username, String password, String firstName, String lastName,
			Boolean tenure) {
		super(id, username, password, firstName, lastName);
		this.tenure = tenure;
	}
	public Faculty() {
		super();
	}
	public Faculty(Integer id, String username, String password, String firstName, String lastName,
			String office) {
		super(id, username, password, firstName, lastName);
		this.office = office;
	}
	public Faculty(Integer id, String username, String password, String firstName, String lastName) {
		super(id, username, password, firstName, lastName);
	}
	public Faculty(Integer id, String username, String password, String firstName, String lastName,
			String office, Boolean tenure) {
		super(id, username, password, firstName, lastName);
		this.office = office;
		this.tenure = tenure;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public Boolean getTenure() {
		return tenure;
	}
	public void setTenure(Boolean tenure) {
		this.tenure = tenure;
	}
	public List<Course> getAuthoredCourses() {
		return authoredCourses;
	}
	public void setAuthoredCourses(List<Course> authoredCourses) {
		this.authoredCourses = authoredCourses;
	}
	public void addAuthoredCourse(Course course)
	{ 
	this.authoredCourses.add(course); 
	if(course.getAuthor() != this) 
	{
	course.setAuthor(this); 
	}
	}
}
