package webdev1.repositories;

import org.springframework.data.repository.CrudRepository;


import webdev1.model.Module;

public interface ModuleRepository extends CrudRepository<Module, Integer> {

}
