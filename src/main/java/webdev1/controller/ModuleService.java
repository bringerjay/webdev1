package webdev1.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import webdev1.model.Course;
import webdev1.model.Module;
import webdev1.repositories.CourseRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ModuleService {
    @Autowired
    CourseRepository repo;
    
    @GetMapping("/api/courses/{courseId}/modules")
    public List<Module> findModulesForCourse(
            @PathVariable("courseId") Integer courseId) {
        Course course = repo.findById(courseId).get();
        return course.getModules();
    }
}
