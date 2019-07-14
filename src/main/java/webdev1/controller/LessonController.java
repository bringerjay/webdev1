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
public class LessonController {
	@Autowired
	ModuleRepository moduleRepository; 
	@Autowired
	LessonRepository lessonRepository; 
	
	@PostMapping("/api/modules/{mid}/lessons") 
	public Lesson createLessonForModule (
			@PathVariable("mid") Integer mId,
			@RequestBody Lesson lesson) 
	{
		Module module = moduleRepository.findOne(mId);
		if (module == null)
		{	
			System.out.println("No module found for the ID");
	    }
		lesson.setModule(module);
		lessonRepository.save(lesson);
		return lesson;
	}

	@PutMapping("/api/modules/{mId}/lessons/{lId}")
	public void belongToModule(
		@PathVariable("mId") int mId,
		@PathVariable("lId") int lId) 
	    {
		Module module = moduleRepository.findOne(mId);
		Lesson lesson = lessonRepository.findOne(lId);
		lesson.setModule(module);
		lessonRepository.save(lesson);
	    }

	@DeleteMapping("/api/lessons/{lId}")
	public void deleteLesson(@PathVariable("lId") Integer id) {
		System.out.println("Controller deleting lesson: "+ id);
		lessonRepository.deleteById(id);
	    }

	@PutMapping("/api/lessons/{lId}")
	public Lesson updateLesson(
			@PathVariable("lId") Integer id,
			@RequestBody Lesson newLesson)
	{
		System.out.println("Controller updating module: "+ id);
		Lesson lesson = lessonRepository.findOne(id);
		lesson.setTitle(newLesson.getTitle());
		lessonRepository.save(lesson);
		return lesson;
	}
	@GetMapping("/api/lessons")
	public List<Lesson> findAllLessons() 
	{
		System.out.println("Controller received getting all lessons");
		return lessonRepository.findAllLessons();
		}
	
	@GetMapping("/api/modules/{mId}/lessons")
	public List<Lesson> findLessonsForModule(@PathVariable("mId") Integer mId) 
	{
		System.out.println("Controller received getting all lessons for module "
	    + mId);
		Module module = moduleRepository.findOne(mId);
		return module.getLessons();
		}

	@GetMapping("/api/lessons/{lId}")
	public Lesson findLessonById(
	    @PathVariable("lId") Integer id) {
		System.out.println("Controller received finding lesson " + id);
		return lessonRepository.findOne(id);
	}
}
