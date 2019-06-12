package webdev1.controller;
import java.util.ArrayList;
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
static {widgets.add(new Widget(1,"Heading1","Heading","h2","hello world","NA","NA"));
widgets.add(new Widget(2,"Heading3","Heading","h3","hello world","NA","NA"));
widgets.add(new Widget(3,"Paragraph1","Paragraph","NA","hello world","NA","NA"));
widgets.add(new Widget(4,"Heading2","Heading","h1","hello world","NA","NA"));
widgets.add(new Widget(5,"Paragraph2","Paragraph","NA","hello world","NA","NA"));
widgets.add(new Widget(6,"List1","List","NA","hello world hello kitty","ul","NA"));
widgets.add(new Widget(7,"List2","List","NA","hello world hello kitty","ol","NA"));
widgets.add(new Widget(8,"Image2","Image","NA","hello world hello kitty","NA","https://picsum.photos/300/200"));
widgets.add(new Widget(9,"link1","Link","NA","hello world hello kitty","NA","https://picsum.photos/300/200"));
}
@PostMapping("/api/widgets")
public List<Widget> createWidget(@RequestBody Widget widget){
	System.out.println("creating "+widget.getName());
	widgets.add(widget);
	idTracker=idTracker+1;
	widgets.get(widgets.size()-1).setId(idTracker);
	return widgets;
}

@GetMapping("/api/widgets")
public List<Widget> findAllWidgets(){
	return widgets;
}

@DeleteMapping("/api/widgets/{widgetId}")
public List<Widget> deleteWidgets(@PathVariable("widgetId")
Integer widgetId)
{ widgets = widgets
		.stream()
		.filter(
				widget -> !widget.getId()
				.equals(widgetId))
				.collect
				(Collectors.toList());
				return widgets;}

@PutMapping("/api/widgets/{widgetId}")
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
}

@PutMapping("/api/widgets")
public List<Widget> SaveChanges
(@RequestBody ArrayList<Widget> preview){
	System.out.println("processing request "+preview.get(1).getName());
	for (Widget w: widgets) {
		System.out.println("Updating widget "+w.getId()+preview.get(w.getId()).getName());
		if (!preview.get(w.getId()).getName().equals("33init33"))
		{	System.out.println("Updated name to "+preview.get(w.getId()).getName());
			w.setName(preview.get(w.getId()).getName());}
		if (!preview.get(w.getId()).getList().equals("33init33"))
		{w.setList(preview.get(w.getId()).getList());
		System.out.println("Updated list to "+ preview.get(w.getId()).getList());}
		if (!preview.get(w.getId()).getSize().equals("33init33"))
		{w.setSize(preview.get(w.getId()).getSize());
		System.out.println("Updated size to "+ preview.get(w.getId()).getSize());}
		if (!preview.get(w.getId()).getText().equals("33init33"))
		{w.setText(preview.get(w.getId()).getText());
		System.out.println("Updated text to "+ preview.get(w.getId()).getText());}
		if (!preview.get(w.getId()).getType().equals("33init33"))
		{w.setType(preview.get(w.getId()).getType());
		System.out.println("Updated type to "+ preview.get(w.getId()).getType());}
		if (!preview.get(w.getId()).getUrl().equals("33init33"))
		{w.setUrl(preview.get(w.getId()).getUrl());
		System.out.println("Updated url to "+ preview.get(w.getId()).getUrl());}
	}
	return widgets;
}
}