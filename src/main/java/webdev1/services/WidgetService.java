package webdev1.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webdev1.model.Widget;
import webdev1.repositories.WidgetRepository;

@Service
public class WidgetService {

	@Autowired
	WidgetRepository repository;
	
    public Widget save (Widget widget) {
        return repository.save(widget);
    }
	
	public List<Widget> findAllWidgets() {
        return repository.findAllWidgets();
    }

    public Widget findWidgetById(Integer id) {
        return repository.findWidgetById(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
}
}
