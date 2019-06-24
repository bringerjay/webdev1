package webdev1.repositories;
import webdev1.model.*; 
import org.springframework.data.repository.*;
public interface StudentRepository
extends CrudRepository<Student, Integer> { }