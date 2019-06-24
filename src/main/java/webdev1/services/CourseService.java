package webdev1.services;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import webdev1.model.Course;
import webdev1.model.Module;
public class CourseService {
	private static CourseService instance = null;
	private Integer idTracker = 6;
	public static CourseService getInstance() {
		if(instance == null) {
			instance = new CourseService();
		}
		return instance;
	}
	static List<Course> courses = new ArrayList<Course>();
	static {
		List<Module> moduleList = new ArrayList<Module>();
		Module module1 = new Module(1,"Module1");
		Module module2 = new Module(2,"Module2");
		Module module3 = new Module(3,"Module3");
		Module module4 = new Module(4,"Module4");
		Module module5 = new Module(5,"Module5");
		Module module6 = new Module(6,"Module6");
		moduleList.add(module1);
		moduleList.add(module2);
		moduleList.add(module3);
		moduleList.add(module4);
		moduleList.add(module5);
		moduleList.add(module6);
		courses.add(new Course(1,"Web Development",moduleList));
		courses.add(new Course(2,"Machine Learning",moduleList));
		courses.add(new Course(3,"Algorithm",moduleList));
		courses.add(new Course(4,"Database Systems",moduleList));
		courses.add(new Course(5,"Distributed Systems",moduleList));
		courses.add(new Course(6,"Cloud Computing",moduleList));
	}
	public List<Course> findAllCourses(){
		System.out.println("Service getting all courses");
		return courses;
	}
	public List<Course> createCourse(Course course){
		System.out.println(
				"Service creating course"+course.getTitle());
		courses.add(course);
		idTracker=idTracker+1;
		courses.get(courses.size()-1).setId(idTracker);
		System.out.println("Service assigning ID: " + idTracker);
		return courses;
	}
	public Course findCourse(Integer courseId){
		System.out.println(
				"Service getting course: "+ courseId);
		Course course = null;
	    for (Course c: courses) {
			if(c.getId().equals(courseId))
			{course=c;}
	    }
		return course;
	}
	public void deleteCourses(Integer courseId)
	{ System.out.println("Service deleting widget: "+ courseId);
	courses = courses
			.stream()
			.filter(course -> !course.getId()
					.equals(courseId))
					.collect
					(Collectors.toList());}
	public List<Course> UpdateCourse(Course newcourse,Integer id){
		System.out.println("Service processing course update request for course "+ id + " with "+newcourse.getTitle());
		for (Course c: courses) {
			if(c.getId()==id)
			{c.setTitle(newcourse.getTitle());
			System.out.println(c.getTitle());
}
	    }
		return courses;
		}
}