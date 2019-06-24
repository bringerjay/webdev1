package webdev1.model;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="widgets")
public class Widget {
	public Widget() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Type type;
	private String name;
	private String size;
	private String text;
	private String list;
	private String url;
	private String[] ltext;
	private Integer queueOrder;
	private Integer width;
	private Integer height;
	private String cssClass;
	private String style;
	@ManyToOne(cascade = {CascadeType.PERSIST,
			CascadeType.DETACH,
			CascadeType.REFRESH})
    @JsonIgnore
    private Topic topic;
	@Transient
    public Integer getTopicId() {
    if (this.topic != null)
		{return topic.getId();}
    else return null;
    }
	public enum Type {
		Heading,List,Image,Link,Paragraph
	}
	public Widget(Integer id) {
		super();
		this.id = id;
	}
	public Widget(Integer id, Type type, String name, String size, String text, String list, String url, String[] ltext,
			Integer queueOrder, Integer width, Integer height, String cssClass, String style, Topic topic) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.size = size;
		this.text = text;
		this.list = list;
		this.url = url;
		this.ltext = ltext;
		this.queueOrder = queueOrder;
		this.width = width;
		this.height = height;
		this.cssClass = cssClass;
		this.style = style;
		this.topic = topic;
	}
	public Widget(String name, Type type, String size, String text, String list, String url, String[] ltext) {
		super();
		this.name = name;
		this.type = type;
		this.size = size;
		this.text = text;
		this.list = list;
		this.url = url;
		this.ltext = ltext;
	}
	public Widget(Integer id, String name, Type type, String size, String text, String list, String url,
			String[] ltext) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.size = size;
		this.text = text;
		this.list = list;
		this.url = url;
		this.ltext = ltext;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String[] getLtext() {
		return ltext;
	}
	public void setLtext(String[] ltext) {
		this.ltext = ltext;
	}
	public Integer getOrder() {
		return queueOrder;
	}
	public void setOrder(Integer queueOrder) {
		this.queueOrder = queueOrder;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
		if (topic.getWidgets()==null)
		{
			topic.addWidget(this);
		}
		if(!topic.getWidgets().contains(this)) 
		{
			topic.getWidgets().add(this); 
		}
	}
}
