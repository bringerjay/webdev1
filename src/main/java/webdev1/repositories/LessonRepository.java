package webdev1.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import webdev1.model.Lesson;
import java.util.List;
public interface LessonRepository 
extends CrudRepository<Lesson, Integer>
{
	@Query("select lesson from Lesson lesson")
    public List<Lesson> findAllLessons();

    @Query("select lesson from Lesson lesson where lesson.id=:lid")
    public Lesson findOne(@Param("lid") Integer lid);
}
