package gr.hua.ds.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.ds.users.dao.ApplicationDAO;
import gr.hua.ds.users.dao.AuthorityDAO;
import gr.hua.ds.users.dao.UserDAO;
import gr.hua.ds.users.dao.UserInformationDAO;
import gr.hua.ds.users.model.Application;
import gr.hua.ds.users.model.Authority;
import gr.hua.ds.users.model.User;
import gr.hua.ds.users.model.UserInformation;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ApiController {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private UserInformationDAO userInformationDao;
	
	@Autowired
	private AuthorityDAO authorityDao;
	
	@Autowired
	private ApplicationDAO applicationDao;
	
	@GetMapping("/students")
	public List<User> getStudents() {
		List<User> students = userDao.getStudents();
		return students;
	}
	
	@GetMapping("/users")
	public List<User> getUsers() {
		List<User> users = userDao.getUsers();
		return users;
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/user/{username}")
	public ResponseEntity getUser(@PathVariable String username) {
		User user = userDao.getUserByUsername(username);
		if(user!=null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
	}
	
	@GetMapping("/students/{username}")
	public User getStudent(@PathVariable String username) {
		User user = userDao.getStudentByUsername(username);
		return user;
	}
	
	@GetMapping("/applications")
	public List<Application> getApplications() {
		List<Application> applications = applicationDao.getActivatedApplications();
		return applications;
	}
	
	@PostMapping(value = "/addUser", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User addUser(@RequestBody User user) {
		userDao.insertUserOnly(user);
		return user;
	}
	
	@PostMapping(value = "/addUserInformation", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserInformation addUserInformation(@RequestBody UserInformation userInformation) {
		userInformationDao.insertUserInformation(userInformation);
		return userInformation;
	}
	
	@PostMapping(value = "/addAuthority", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Authority addAuthority(@RequestBody Authority authority) {
		authorityDao.insertAuthority(authority);
		return authority;
	}
	
	@PostMapping(value = "/addApplication", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Application addApplication(@RequestBody Application application) {
		applicationDao.insertApplication(application);
		return application;
	}

	
	@PatchMapping(value = "/editUser",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserInformation editUser(@RequestBody UserInformation userInformation) {
		userInformationDao.updateUserInformation(userInformation);
		return userInformation;
	}
	
}
