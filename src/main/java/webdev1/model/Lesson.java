package webdev1.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="lessons")
public class Lesson {
	public Lesson() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private String title;
	@OneToMany(mappedBy = "lesson",
    		cascade=CascadeType.ALL)
    private List<Topic> topics;
    @ManyToOne()
    @JsonIgnore
    private Module module;
	public Lesson(String title, Module module) {
		super();
		this.title = title;
		this.module = module;
	}
	public Lesson(String title) {
		super();
		this.title = title;
	}
	public Lesson(String title, List<Topic> topics, Module module) {
		super();
		this.title = title;
		this.topics = topics;
		this.module = module;
	}
	public Lesson(Integer id) {
		super();
		this.id = id;
	}
	public Lesson(Integer id, String title, List<Topic> topics, Module module) {
		super();
		this.id = id;
		this.title = title;
		this.topics = topics;
		this.module = module;
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
	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	public void addTopic(Topic topic)
	{
		this.topics.add(topic);
		if (topic.getLesson() != this)
		{
			topic.setLesson(this);
		}
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
		if (module.getLessons()==null)
		{
			module.addLesson(this);
		}
		if (!module.getLessons().contains(this))
		{
		    module.addLesson(this);
		}
	}
    
}
