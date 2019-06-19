package webdev1.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import services.WidgetService;
import webdev1.model.*;
@RestController
@CrossOrigin("*")
public class WidgetController{
protected WidgetService widgetServiceInstance = WidgetService.getInstance();
@PostMapping("/api/widgets")
public List<Widget> createWidget(@RequestBody Widget widget){
	System.out.println("Controller received widget creation request");
	List<Widget> widgets = widgetServiceInstance.createWidget(widget);
	return widgets;
}
@GetMapping("/api/widgets")
public List<Widget> findAllWidgets(){
	System.out.println("Controller received getting all widgets");
	List<Widget> widgets = widgetServiceInstance.findAllWidgets();
	return widgets;
}
@GetMapping("/api/widgets/{widgetId}")
public Widget findWidget(@PathVariable("widgetId")
Integer widgetId){
	System.out.println(
			"Controller getting widget: "+ widgetId);
	Widget widget = widgetServiceInstance.findWidget(widgetId);
	return widget;
}
@DeleteMapping("/api/widgets/{widgetId}")
public List<Widget> deleteWidgets(@PathVariable("widgetId")
Integer widgetId)
{ 
	System.out.println("Controller deleting widget: "+ widgetId);
	widgetServiceInstance.deleteWidgets(widgetId);
	List<Widget> widgets = widgetServiceInstance.findAllWidgets();
				return widgets;}
@PutMapping("/api/widgets")
public List<Widget> UpdateWidgets
(@RequestBody ArrayList<Widget> previews){
	 System.out.println("processing request "+Arrays.toString(previews.toArray()));
		List<Widget> widgets = widgetServiceInstance.UpdateWidgets(previews);
	return widgets;
	}
	}