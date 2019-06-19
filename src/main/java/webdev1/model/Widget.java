package webdev1.model;
public class Widget {
	public Widget() {
		super();
	}
	private Integer id;
	private Type type;
	private String name;
	private String size;
	private String text;
	private String list;
	private String url;
	private String[] ltext;
	private Integer order;
	private Integer width;
	private Integer height;
	private String cssClass;
	private String style;

	public enum Type {
		Heading,List,Image,Link,Paragraph
	}
	public Widget(Integer id) {
		super();
		this.id = id;
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
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
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
}
