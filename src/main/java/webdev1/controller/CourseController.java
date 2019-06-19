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
import services.CourseService;
import webdev1.model.Course;
@RestController
@CrossOrigin("*")
public class CourseController {
    private CourseService courseServiceInstance = CourseService.getInstance() ;
    
    @PostMapping("/api/courses")
    public List<Course> createCourse(
            @RequestBody Course course) {
    	List<Course> courses = courseServiceInstance.createCourse(course);
        return courses;
    } 
    
    @DeleteMapping("/api/courses/{courseId}")
    public List<Course> deleteCourse(@PathVariable("courseId") Integer id) {
    	System.out.println("Controller deleting course: "+ id);
    	courseServiceInstance.deleteCourses(id);
       	List<Course> courses = courseServiceInstance.findAllCourses();
        return courses;
        }
    
    @PutMapping("/api/courses/{courseId}")
    public List<Course> updateCourse(
            @PathVariable("courseId") Integer id,
            @RequestBody Course course) {
    	System.out.println("Controller updating course: "+ id);
       	List<Course> courses = courseServiceInstance.UpdateCourse(course, id);
        return courses; 	
    }
   
    @GetMapping("/api/courses")
    public List<Course> findAllCourses() {
    	System.out.println("Controller received getting all courses");
    	List<Course> courses = courseServiceInstance.findAllCourses();
    	return courses; 
    	}
    
    @GetMapping("/api/courses/{userId}")
    public Course findCourseById(
            @PathVariable("userId") Integer id) {
    	System.out.println("Controller received finding course " + id);
    	Course course = courseServiceInstance.findCourse(id);
    	return course;

    }
}
