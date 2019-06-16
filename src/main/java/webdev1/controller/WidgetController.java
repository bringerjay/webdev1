package webdev1.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import webdev1.model.Widget;
@RestController
@CrossOrigin("*")
public class WidgetController{
public Integer idTracker = 6;
static List<Widget> widgets = new ArrayList<Widget>();
static {
    String[] l1 = new String[20];
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
widgets.add(new Widget(1,"Heading1","Heading","h2","hello world","NA","NA",l3));
widgets.add(new Widget(2,"Heading3","Heading","h3","hello world","NA","NA",l3));
widgets.add(new Widget(3,"Paragraph1","Paragraph","NA","hello world","NA","NA",l3));
widgets.add(new Widget(4,"Heading2","Heading","h1","hello world","NA","NA",l3));
widgets.add(new Widget(5,"Paragraph2","Paragraph","NA","hello world","NA","NA",l3));
widgets.add(new Widget(6,"List1","List","NA","hello world hello kitty","ul","NA",l1));
widgets.add(new Widget(7,"List2","List","NA","hello world hello kitty","ol","NA",l2));
widgets.add(new Widget(8,"Image2","Image","NA","hello world hello kitty","NA","https://picsum.photos/300/200",l3));
widgets.add(new Widget(9,"link1","Link","NA","hello world hello kitty","NA","https://picsum.photos/300/200",l3));
}
@PostMapping("/api/widgets")
public List<Widget> createWidget(@RequestBody Widget widget){
	System.out.println(
			"creating "+widget.getName() +
			"size: "+ widget.getSize() +
			"list: "+ widget.getList() +
			"type: "+ widget.getType() +
			"text: "+ widget.getText() +
			"ltext:"+ widget.getLtext()+
			"url : "+ widget.getUrl());
	widgets.add(widget);
	idTracker=idTracker+1;
	widgets.get(widgets.size()-1).setId(idTracker);
	System.out.println("Assigning ID: " + idTracker);
	return widgets;
}

@GetMapping("/api/widgets")
public List<Widget> findAllWidgets(){
	return widgets;
}

@DeleteMapping("/api/widgets/{widgetId}")
public List<Widget> deleteWidgets(@PathVariable("widgetId")
Integer widgetId)
{ 
	System.out.println(
			"Deleting widget: "+ widgetId);
	widgets = widgets
		.stream()
		.filter(
				widget -> !widget.getId()
				.equals(widgetId))
				.collect
				(Collectors.toList());
				return widgets;}

/**@PutMapping("/api/widgets/{widgetId}")
public List<Widget> updateWidget
(@RequestBody Widget widget, @PathVariable("widgetId")
Integer widgetId){
	for (Widget w: widgets) {
		if(w.getId().equals(widgetId))
		{w.setName(widget.getName());
		w.setType(widget.getType());
		}
	}
	return widgets;
}**/

@PutMapping("/api/widgets")
public List<Widget> UpdateWidgets
(@RequestBody ArrayList<Widget> previews){
	 System.out.println("processing request "+Arrays.toString(previews.toArray()));
	widgets = previews;
	return widgets;
	}
	}
	
	
	/**System.out.println("processing request "+Arrays.toString(preview.toArray()));
	for (Widget w: widgets) {
		 int id = preview.get(w.getId()).getId();
		 System.out.println("Reviewing widget "+id);
		 String name = preview.get(w.getId()).getName();
		 String type = preview.get(w.getId()).getType();
		 String size = preview.get(w.getId()).getSize();
		 String text = preview.get(w.getId()).getText();
		 String list = preview.get(w.getId()).getList();
		 String url  = preview.get(w.getId()).getUrl();
		 String[] ltext = preview.get(w.getId()).getLtext();
		if (!name.equals("33init33"))
		{	System.out.println("Updated name to "+name);
			w.setName(name);}
		if (!list.equals("33init33"))
		{w.setList(list);
		System.out.println("Updated list to "+ list);}
		if (!size.equals("33init33"))
		{w.setSize(size);
		System.out.println("Updated size to "+ size);}
		if (!text.equals("33init33"))
		{w.setText(text);
		System.out.println("Updated text to "+ text);}
		if (!type.equals("33init33"))
		{w.setType(type);
		System.out.println("Updated type to "+ type);}
		if (!url.equals("33init33"))
		{w.setUrl(url);
		System.out.println("Updated url to "+ url);}
		if (!ltext[0].equals("33init33"))
		{w.setLtext(ltext);
		System.out.println("Updated url to "+ ltext);}
	}
	return widgets;
}
}**/