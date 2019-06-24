package webdev1.model;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
    @OneToMany(mappedBy = "course",
    		cascade=CascadeType.ALL)
    private List<Module> modules;
    @ManyToOne()
    @JsonIgnore
    private Faculty author;
    @Transient
    public String getAuthorName( ) {
    if(this.author != null)
    {return author.getFirstName() + " " + author.getLastName();}
    else return null;
    }
    public Course(Integer id, String title, List<Module> modules, Faculty author) {
		super();
		this.id = id;
		this.title = title;
		this.modules = modules;
		this.author = author;
	}
    public Course(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public void addModule(Module module)
	{ 
	this.modules.add(module); 
	if(module.getCourse() != this) 
	{
		module.setCourse(this);
	}
	}
	public Course(Integer id, String title, List<Module> modules) {
		super();
		this.id = id;
		this.title = title;
		this.modules = modules;
	}
	public Course(String title) {
		super();
		this.title = title;
	}
	public Course(Integer id) {
		super();
		this.id = id;
	}
	public Course() {
		super();
	}
	public Faculty getAuthor() {
		return author;
	}
	public void setAuthor(Faculty author) {
		this.author = author;
		if(!author.getAuthoredCourses().contains(this)) {
		author.getAuthoredCourses().add(this); }}

}
