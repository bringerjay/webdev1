package webdev1.model;
import java.util.List;
public class Course {
    public Course(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	private Integer id;
    private String title;
    private List<Module> modules;
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

}
