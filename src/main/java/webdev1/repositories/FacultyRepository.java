package webdev1.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import webdev1.model.Course;
import webdev1.model.Faculty; 
public interface FacultyRepository extends CrudRepository<Faculty, Integer>{

    @Query("select faculty from Faculty faculty where faculty.id=:fId")
    public Faculty findOne(@Param("fId") Integer fId);

}
