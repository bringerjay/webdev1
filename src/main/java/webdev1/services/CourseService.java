package webdev1.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webdev1.model.Course;
import webdev1.repositories.CourseRepository;
@Service
public class CourseService {
	@Autowired
	CourseRepository repository;
    public Course save(Course course) {
        return repository.save(course);
    }
	
	public List<Course> findAllCourses() {
        return repository.findAllCourses();
    }

    public Course findOne(Integer id) {
        return repository.findOne(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
}
}