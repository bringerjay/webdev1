package webdev1.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import webdev1.model.Widget;
import java.util.List;
public interface WidgetRepository
    extends CrudRepository<Widget, Integer> {

    @Query("select widget from Widget widget")
    public List<Widget> findAllWidgets();

    @Query("select widget from Widget widget where widget.id=:wid")
    public Widget findWidgetById(@Param("wid") Integer id);
}

