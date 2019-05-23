package webdev1.controller;
import webdev1.model.*;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService {
	User alice = new User(1, "alice", "rabit", "Alice", "Wonderland");
	User bob   = new User(2, "bob", "love", "Bob", "abcd");
	User lee   = new User(3, "lee", "love", "Lee", "bcde");
	User lin   = new User(4, "lin", "love", "Lin", "cdeg");
	User chris   = new User(5, "chris", "love", "Chris", "hijk");
	User xiao   = new User(6, "xiao", "love", "Xiao", "hello");
	User rob   = new User(7, "rob", "love", "Rob", "kitty");
	static List<User> users = new ArrayList<User>();
	{
	    users.add(alice);
	    users.add(bob);
	    users.add(lee);
	    users.add(lin);
	    users.add(chris);
	    users.add(xiao);
	    users.add(rob);
	};
	@GetMapping("/api/users")
	public List<User> findAllUsers(){
		return users;
	}
	@PostMapping("/api/users")
	public List<User> createUser(@RequestBody User user){
		users.add(user);
		return users;
	}
	@PostMapping("/api/users/delete/{userId}")
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
	@GetMapping("/api/user/update/{username}/{firstName}/{lastName}/{password}")
    public User updateUser(@PathVariable("username") String username, @PathVariable("firstName") String firstName,
    		@PathVariable("lastName") String lastName,@PathVariable("password") String password) {
		int f = 0;
		for(int i=0;i< users.size()-1;i++) {
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
