package webdev1.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webdev1.model.Topic;
import webdev1.repositories.TopicRepository;
@Service
public class TopicService {
	@Autowired
	TopicRepository repository;
    public Topic save (Topic topic) {
        return repository.save(topic);
    }
	
	public List<Topic> findAllTopics() {
        return repository.findAllTopics();
    }

    public Topic findOne(Integer id) {
        return repository.findOne(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
}
}
