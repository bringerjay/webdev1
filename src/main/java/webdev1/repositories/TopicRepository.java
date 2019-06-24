package webdev1.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import webdev1.model.Topic;
import java.util.List;

public interface TopicRepository 
extends CrudRepository<Topic, Integer>{
	@Query("select topic from Topic topic")
    public List<Topic> findAllTopics();

    @Query("select topic from Topic topic where topic.id=:tid")
    public Topic findOne(@Param("tid") Integer tid);
}
