package webdev1.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webdev1.model.Lesson;
import webdev1.repositories.LessonRepository;

@Service
public class LessonService {
	@Autowired
	LessonRepository repository;
    public Lesson save (Lesson lesson) {
        return repository.save(lesson);
    }
	
	public List<Lesson> findAllLessons() {
        return repository.findAllLessons();
    }

    public Lesson findOne(Integer id) {
        return repository.findOne(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
}
}
