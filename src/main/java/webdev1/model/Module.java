package webdev1.model;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import webdev1.model.Module;
@Entity
@Table(name="modules")
public class Module {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	public Module(String title) {
		super();
		this.title = title;
	}
	private String title;
    @OneToMany(mappedBy = "module",
    		cascade=CascadeType.ALL)
    private List<Lesson> lessons;
    @ManyToOne()
    @JsonIgnore
    private Course course;
    public Module(Integer id, String title, List<Lesson> lessons, Course course) {
		super();
		this.id = id;
		this.title = title;
		this.lessons = lessons;
		this.course = course;
	}
    public Module(Integer id, String title, Course course) {
		super();
		this.id = id;
		this.title = title;
		this.course = course;
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
    public Module() {}
    public Module(Integer id, String title) {
        super();
        this.id = id;
        this.title = title;
    }
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
		if(course.getModules()==null)
		{
		course.addModule(this);
		}
		if(!course.getModules().contains(this)) 
		{
		course.addModule(this);
		}
	}
	public List<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
	public void addLesson(Lesson lesson)
	{ 
	this.lessons.add(lesson); 
	if(lesson.getModule() != this) 
	{
		lesson.setModule(this);
	}
	}
}
