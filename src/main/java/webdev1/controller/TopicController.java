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
@RestController
@CrossOrigin("*")
public class TopicController {
	@Autowired
	TopicRepository topicRepository; 
	@Autowired
	LessonRepository lessonRepository; 
	
	@PostMapping("/api/lessons/{lid}/topics") 
	public Topic createTopicForLesson (
			@PathVariable("lid") Integer lid,
			@RequestBody Topic topic) 
	{
		Lesson lesson = lessonRepository.findOne(lid);
		if (lesson == null)
		{	
			System.out.println("No lesson found for the ID");
	    }
		topic.setLesson(lesson);
		topicRepository.save(topic);
		return topic;
	}

	@PutMapping("/api/lessons/{lId}/topics/{tId}")
	public void belongToModule(
		@PathVariable("tId") int tId,
		@PathVariable("lId") int lId) 
	    {
		Topic topic = topicRepository.findOne(tId);
		Lesson lesson = lessonRepository.findOne(lId);
		topic.setLesson(lesson);
		topicRepository.save(topic);
	    }

	@DeleteMapping("/api/topics/{tId}")
	public void deleteTopic(@PathVariable("tId") Integer id) {
		System.out.println("Controller deleting topic: "+ id);
		topicRepository.deleteById(id);
	    }

	@PutMapping("/api/topics/{tId}")
	public Topic updateTopic(
			@PathVariable("tId") Integer id,
			@RequestBody Topic newTopic)
	{
		System.out.println("Controller updating topic: "+ id);
		Topic topic = topicRepository.findOne(id);
		topic.setTitle(newTopic.getTitle());
		topicRepository.save(topic);
		return topic;
	}
	@GetMapping("/api/topics")
	public List<Topic> findAllTopics() 
	{
		System.out.println("Controller received getting all topics");
		return topicRepository.findAllTopics();
		}
	
	@GetMapping("/api/lessons/{lid}/topics")
	public List<Topic> findTopicsForLesson(@PathVariable("lid") Integer lId) 
	{
		System.out.println("Controller received getting all topics for lesson "
	    + lId);
		Lesson lesson = lessonRepository.findOne(lId);
		return lesson.getTopics();
		}

	@GetMapping("/api/topics/{tId}")
	public Topic findTopicById(
	    @PathVariable("tId") Integer id) {
		System.out.println("Controller received finding topic " + id);
		return topicRepository.findOne(id);
	}
}
