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
@Table(name="topics")
public class Topic {
	public Topic() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private String title;
	@OneToMany(mappedBy = "topic",
    		cascade=CascadeType.ALL)
    private List<Widget> widgets;
    @ManyToOne()
    @JsonIgnore
    private Lesson lesson;
	public Topic(String title, Lesson lesson) {
		super();
		this.title = title;
		this.lesson = lesson;
	}
	public Topic(String title) {
		super();
		this.title = title;
	}
	public Topic(Integer id) {
		super();
		this.id = id;
	}
	public Topic(Integer id, String title, List<Widget> widgets, Lesson lesson) {
		super();
		this.id = id;
		this.title = title;
		this.widgets = widgets;
		this.lesson = lesson;
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
	public List<Widget> getWidgets() {
		return widgets;
	}
	public void setWidgets(List<Widget> widgets) {
		this.widgets = widgets;
	}
	public void addWidget(Widget widget)
	{
		this.widgets.add(widget);
		if (widget.getTopic() != this)
		{
			widget.setTopic(this);
		}
	}
	public Lesson getLesson() {
		return lesson;
	}
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
		if (lesson.getTopics() == null)
		{
			lesson.addTopic(this);
		}
		if (! lesson.getTopics().contains(this))
		{
			lesson.addTopic(this);
		}
	}
}
