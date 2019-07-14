package webdev1.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webdev1.model.Module;
import webdev1.repositories.ModuleRepository;

@Service
public class ModuleService {
	@Autowired
	ModuleRepository repository;
    public Module save(Module module) {
        return repository.save(module);
    }
	
	public List<Module> findAllModules() {
        return repository.findAllModules();
    }

    public Module findOne(Integer id) {
        return repository.findOne(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
}
}
