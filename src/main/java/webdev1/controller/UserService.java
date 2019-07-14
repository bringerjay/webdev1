package webdev1.controller;
import webdev1.model.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.http.HttpSession;
import webdev1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService{
	static int latestId = 7;
	static List<User> users = new ArrayList<User>();
	{
	List<Course> list1=new ArrayList<Course>();	
	List<Course> list2=new ArrayList<Course>();	
	List<Course> list3=new ArrayList<Course>();	
	List<Course> list4=new ArrayList<Course>();	
	List<Course> list5=new ArrayList<Course>();	
	List<Course> list6=new ArrayList<Course>();	
	List<Course> list7=new ArrayList<Course>();	
	SimpleDateFormat format = new SimpleDateFormat( "MM/dd/yyyy" );  // United States style of format. 
	Date date1 = new Date();
	Date date2 = new Date();
	Date date3 = new Date();
	Date date4 = new Date();
	Date date5 = new Date();
	Date date6 = new Date();
	Date date7 = new Date();
	try {
	 date1 = format.parse("10/10/1989");
	 date2 = format.parse("10/10/1989");
	 date3 = format.parse("10/10/1989");
	 date4 = format.parse("10/10/1989");
	 date5 = format.parse("10/10/1989");
	 date6 = format.parse("10/10/1989");
	 date7 = format.parse("10/10/1989");
	User alice = new User(1, "alice", "rabit", "Alice", "Wonderland",date1);
	User bob   = new User(2, "bob", "love", "Bob", "abcd",date2);
	User lee   = new User(3, "lee", "love", "Lee", "bcde",date3);
	User lin   = new User(4, "lin", "love", "Lin", "cdeg",date4);
	User chris   = new User(5, "chris", "love", "Chris", "hijk",date5);
	User xiao   = new User(6, "xiao", "love", "Xiao", "hello",date6);
	User rob   = new User(7, "rob", "love", "Rob", "kitty",date7);
	users.add(alice);
    users.add(bob);
    users.add(lee);
    users.add(lin);
    users.add(chris);
    users.add(xiao);
    users.add(rob);} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {}    }
    int check;
    @Autowired
    UserRepository userRepository;    
	@GetMapping("/api/users")
	public List<User> findAllUsers(){
		  return (List<User>) userRepository.findAll();
	}
	@PostMapping("/api/users")
	public List<User> createUser(@RequestBody User user){
		check = 0;
		int a = users.size();
		for(int m=0;m<a ;m++) {
		if(users.get(m).getUsername().equals(user.getUsername()))
		{ 
		check = 1;
		}}
		if(check == 1)
		{
			return null;
		}
		else{
	    latestId++;
		user.setId(latestId);
		/**String r1 = "STUDENT";
		String r2 = "FACULTY";
		String r3 = "ADMIN";
		webdev1.model.User.Role role1 = webdev1.model.User.Role.valueOf("Student");
		webdev1.model.User.Role role2 = webdev1.model.User.Role.valueOf("Faculty");
		webdev1.model.User.Role role3 = webdev1.model.User.Role.valueOf("Admin");
        if (user.getRole().toString().equals(r1))
        {user.setRole(role1);}
        else if(user.getRole().toString().equals(r2))
        {user.setRole(role2);}
        else {user.setRole(role3);}**/
		users.add(user);
		return users;}
	}
	@DeleteMapping("/api/users/delete/{userId}")
	public List<User> deleteUser(@PathVariable("userId") Integer id){
		ListIterator<User> userlist = users.listIterator();
		while(userlist.hasNext()){
		    if(userlist.next().getId().intValue()==id){
		    	userlist.remove();
		    }
		}
		return users;
	}
	@PostMapping("/api/login")
	public User login(
	        @RequestBody User loginUser,
	        HttpSession session) {
	    for(User user: users) {
	        if(user.getUsername().equals(loginUser.getUsername()) &&
	           user.getPassword().equals(loginUser.getPassword())) {
	            session.setAttribute("currentUser", user);
	            return user;
	        }
	    }
	    return null;
	}
	
	@PostMapping("/api/register")
	public User register(
	        @RequestBody User user,
	        HttpSession session) {
	    session.setAttribute("currentUser", user);
	    users.add(user);
	    return user;
	}
	
	@PostMapping("/api/loggedin")
	public User loggedin(HttpSession session) {
	    return (User)session.getAttribute("currentUser");
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(
			@PathVariable("userId") Integer id) {
		for(User user: users) {
			if(id == user.getId().intValue())
				return user;
		}
		return null;
	}
	@GetMapping("/api/user/username/{username}")
	public User findUserByUserName(
			@PathVariable("username") String username) {
		for(User user: users) {
			if(username.equals(user.getUsername().toString()))
				return user;
		}
		return null;
	}
	@GetMapping("/api/user/update/{username}/{firstName}/{lastName}/{password}")
    public User updateUser(@PathVariable("username") String username, @PathVariable("firstName") String firstName,
    		@PathVariable("lastName") String lastName,@PathVariable("password") String password) {
		int f = 0;
		int a = users.size();
		for(int i=0;i<a ;i++) {
			if(users.get(i).getUsername().equals(username))
			{
			//String firsName = users.get(users.size()-1).getFirstName();
			//String lastName = users.get(users.size()-1).getLastName();
			//String password = users.get(users.size()-1).getPassword();
			users.get(i).setLastName(lastName);
			users.get(i).setFirstName(firstName);
			users.get(i).setPassword(password);
			f = i;}
    }
		return users.get(f);}
}