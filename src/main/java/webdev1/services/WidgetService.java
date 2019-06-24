package webdev1.services;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import webdev1.model.Widget;
import webdev1.model.Widget.Type;
public class WidgetService {
	private static WidgetService instance = null;
	private Integer idTracker = 9;
	public static WidgetService getInstance() {
		if(instance == null) {
			instance = new WidgetService();
		}
		return instance;
	}
	static List<Widget> widgets = new ArrayList<Widget>();
	static {
		Type heading = Type.Heading;
		Type list = Type.List;
		Type paragrah = Type.Paragraph;
		Type link = Type.Link;
		Type image = Type.Image;
	    String[] l1 = new String[3];
	    l1[0] = "Cheese";
	    l1[1] = "Pepperoni";
	    l1[2] = "Black Olives";
	    String[] l2 = new String[3];
	    l2[0] = "I";
	    l2[1] = "Love";
	    l2[2] = "You";
	    String[] l3 = new String[3];
	    l2[0] = "NA";
	    l2[1] = "NA";
	    l2[2] = "NA";
	widgets.add(new Widget(1,"Heading1",heading,"h2","hello world","NA","NA",l3));
	widgets.add(new Widget(2,"Heading3",heading,"h3","hello world","NA","NA",l3));
	widgets.add(new Widget(3,"Paragraph1",paragrah,"NA","hello world","NA","NA",l3));
	widgets.add(new Widget(4,"Heading2",heading,"h1","hello world","NA","NA",l3));
	widgets.add(new Widget(5,"Paragraph2",paragrah,"NA","hello world","NA","NA",l3));
	widgets.add(new Widget(6,"List1",list,"NA","hello world hello kitty","ul","NA",l1));
	widgets.add(new Widget(7,"List2",list,"NA","hello world hello kitty","ol","NA",l2));
	widgets.add(new Widget(8,"Image2",image,"NA","hello world hello kitty","NA","https://picsum.photos/300/200",l3));
	widgets.add(new Widget(9,"link1",link,"NA","hello world hello kitty","NA","https://picsum.photos/300/200",l3));
	}
	public List<Widget> findAllWidgets(){
		System.out.println("Service getting all widgets");
		return widgets;
	}
	public List<Widget> createWidget(Widget widget){
		System.out.println(
				"Service creating "+widget.getName() +
				"size: "+ widget.getSize() +
				"list: "+ widget.getList() +
				"type: "+ widget.getType() +
				"text: "+ widget.getText() +
				"ltext:"+ widget.getLtext()+
				"url : "+ widget.getUrl());
		widgets.add(widget);
		idTracker=idTracker+1;
		widgets.get(widgets.size()-1).setId(idTracker);
		System.out.println("Service assigning ID: " + idTracker);
		return widgets;
	}
	public Widget findWidget(Integer widgetId){
		System.out.println(
				"Service getting widget: "+ widgetId);
		Widget widget = null;
	    for (Widget w: widgets) {
			if(w.getId().equals(widgetId))
			{widget=w;}
	    }
		return widget;
	}
	public void deleteWidgets(Integer widgetId)
	{ System.out.println("Service deleting widget: "+ widgetId);
	  widgets = widgets
			.stream()
			.filter(widget -> !widget.getId()
					.equals(widgetId))
					.collect
					(Collectors.toList());}
	public List<Widget> UpdateWidgets(ArrayList<Widget> previews){
		System.out.println("Service processing update request "+Arrays.toString(previews.toArray()));
		widgets = previews;
		return widgets;
		}
}
