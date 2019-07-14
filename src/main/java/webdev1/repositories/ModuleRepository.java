package webdev1.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import webdev1.model.Module;
import java.util.List;
public interface ModuleRepository extends
CrudRepository<Module, Integer>
{
	@Query("select module from Module module")
    public List<Module> findAllModules();

    @Query("select module from Module module where module.id=:mid")
    public Module findOne(@Param("mid") Integer mid);
}
