package webdev1.controller;
import java.util.ArrayList;
import webdev1.repositories.*;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import webdev1.model.*;
import webdev1.model.Module;
import webdev1.services.*;
@RestController
@CrossOrigin("*")
public class WidgetController{

	@Autowired
	TopicRepository topicRepository; 
	@Autowired
	WidgetRepository widgetRepository;
	
@PostMapping("/api/topics/{tId}/widgets")
public Widget createWidgetForTopic(

		@PathVariable("tId") Integer tId,
		@RequestBody Widget widget)
{
	System.out.println("Controller received widget creation request");
	Topic topic = topicRepository.findOne(tId);
	if (topic == null)
	{	
		System.out.println("No topic found for the ID");
    }
	widget.setTopic(topic);
	widgetRepository.save(widget);
	return widget;
}
@GetMapping("/api/topics/{tId}/widgets")
public List<Widget> findAllWidgetsForTopic(@PathVariable("tId") Integer tId)
{
	System.out.println("Controller received getting all widgets");
	Topic topic = topicRepository.findOne(tId);
	return topic.getWidgets();
}
@GetMapping("/api/widgets/{wId}")
public Widget findWidget(@PathVariable("wId")
Integer wId){
	System.out.println(
			"Controller getting widget: "+ wId);
	return widgetRepository.findWidgetById(wId);
}
@PutMapping("/api/widgets/{wId}")
public Widget UpdateWidget
(
		@PathVariable("wId") Integer wId,
		@RequestBody Widget newWidget){
	 System.out.println("processing request to update widget id : "+
newWidget.getId());
		Widget widget = widgetRepository.findWidgetById(wId);
		widget = newWidget;
	 return widget;
}
@DeleteMapping("/api/widgets/{wId}")
public void deleteWidget(@PathVariable("wId")
Integer wId)
{ 
	System.out.println("Controller deleting widget: "+ wId);
	widgetRepository.deleteById(wId);
}
@PutMapping("/api/topics/{tId}/widgets")
public List<Widget> UpdateWidgets
(
		@PathVariable("tId") Integer tId,
		@RequestBody ArrayList<Widget> previews){
	 System.out.println("processing request to save all widgets "+
Arrays.toString(previews.toArray()));
		Topic topic = topicRepository.findOne(tId);
	    for (Widget p: previews) {
        Widget widget = widgetRepository.findWidgetById(p.getId());
        widget.setCssClass(p.getCssClass());
        widget.setHeight(p.getHeight());
        widget.setList(p.getList());
        widget.setLtext((p.getLtext()));
        widget.setName(p.getName());
        widget.setOrder(p.getOrder());
        widget.setSize(p.getSize());
        widget.setStyle(p.getStyle());
        widget.setText(p.getText());
        widget.setType(p.getType());
        widget.setUrl(p.getUrl());
        widget.setWidth(p.getWidth());
	    widgetRepository.save(widget);
	    }
	 return topic.getWidgets();
	}
	}