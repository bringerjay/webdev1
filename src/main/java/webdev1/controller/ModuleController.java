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
public class ModuleController {
	
@Autowired
ModuleRepository moduleRepository; 
@Autowired
CourseRepository courseRepository; 

@PostMapping("/api/courses/{cid}/modules") 
public Module createModuleForCourse (
		@PathVariable("cid") Integer cId,
		@RequestBody Module module) 
{
	Course course = courseRepository.findOne(cId);
	if (course == null)
	{	
		System.out.println("No course found for the ID");
    }
	module.setCourse(course);
	moduleRepository.save(module);
	return module;
}

@PutMapping("/api/courses/{cId}/modules/{mId}")
public void belongToCourse(
	@PathVariable("mId") int mId,
	@PathVariable("cId") int cId) 
    {
	Module module = moduleRepository.findOne(mId);
	Course course   = courseRepository.findOne(cId);

	module.setCourse(course);
	moduleRepository.save(module);
    }

@DeleteMapping("/api/modules/{moduleId}")
public void deleteModule(@PathVariable("moduleId") Integer id) {
	System.out.println("Controller deleting module: "+ id);
	moduleRepository.deleteById(id);
    }

@PutMapping("/api/modules/{moduleId}")
public Module updateModule(
		@PathVariable("moduleId") Integer id,
		@RequestBody Module newModule)
{
	System.out.println("Controller updating module: "+ id);
	Module module = moduleRepository.findOne(id);
	module.setTitle(newModule.getTitle());
	moduleRepository.save(module);
	return module;
}

@GetMapping("/api/courses/{cId}/modules")
public List<Module> findAllModulesForCourse(@PathVariable("cId") int cId) {
	System.out.println("Controller received getting all modules for course " + cId);
	Course course   = courseRepository.findOne(cId);
	return course.getModules();
	}

@GetMapping("/api/modules/{moduleId}")
public Module findModuleById(
    @PathVariable("moduleId") Integer id) {
	System.out.println("Controller received finding module " + id);
	return moduleRepository.findOne(id);
}

}


