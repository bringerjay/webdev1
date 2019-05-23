package webdev1.repositories;

import org.springframework.data.repository.CrudRepository;


import webdev1.model.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {

}
