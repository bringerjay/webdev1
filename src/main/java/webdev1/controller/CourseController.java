package webdev1.controller;
import java.util.ArrayList;
import webdev1.model.Module;
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
import webdev1.model.Course;
import webdev1.model.Faculty;
import webdev1.repositories.*;
@RestController
@CrossOrigin("*")
public class CourseController {
    
	@Autowired
	CourseRepository courseRepository; 
	
	@PostMapping("/api/courses") 
	public Course createCourse (@RequestBody Course course) 
	{
		return courseRepository.save(course);
	}
    
    @DeleteMapping("/api/courses/{courseId}")
    public void deleteCourse(@PathVariable("courseId") Integer id) {
    	System.out.println("Controller deleting course: "+ id);
    	courseRepository.deleteById(id);
        }
    
    @PutMapping("/api/courses/{courseId}")
    public Course updateCourse(
            @PathVariable("courseId") Integer id,
            @RequestBody Course newCourse) 
    {
    	System.out.println("Controller updating course: "+ id);
    	Course course   = courseRepository.findOne(id);
    	course.setTitle(newCourse.getTitle());
    	courseRepository.save(course);
    	return course; 	
    }
   
    @GetMapping("/api/courses")
    public List<Course> findAllCourses() {
    	System.out.println("Controller received getting all courses");
    	return (List<Course>) courseRepository.findAll(); 
    	}
    
    @GetMapping("/api/courses/{cId}")
    public Course findCourseById(
            @PathVariable("cId") Integer id) {
    	System.out.println("Controller received finding course " + id);
    	return courseRepository.findOne(id);

    }
    
    
}
