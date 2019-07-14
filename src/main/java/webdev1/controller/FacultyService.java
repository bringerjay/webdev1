package webdev1.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import webdev1.model.Course;
import webdev1.model.Faculty;
import webdev1.repositories.*;
@RestController
public class FacultyService {
	@Autowired
	FacultyRepository facultyRepository; 
	
	@Autowired
	CourseRepository courseRepository; 
	
	@GetMapping("/api/faculty")
	public List<Faculty> findAllFaculty() {
	return (List<Faculty>)facultyRepository.findAll(); }

@PutMapping("/api/faculty/{fId}/authored/{cId}")
public void authoredCourse(
	@PathVariable("fId") int fId,
	@PathVariable("cId") int cId) 
    {
	Faculty faculty = facultyRepository.findOne(fId);
	Course course   = courseRepository.findOne(cId);
	course.setAuthor(faculty);
	courseRepository.save(course);
    }
}