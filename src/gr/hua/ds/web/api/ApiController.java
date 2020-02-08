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

import gr.hua.ds.service.ApplicationService;
import gr.hua.ds.service.AuthorityService;
import gr.hua.ds.service.DepartmentService;
import gr.hua.ds.service.UserInformationService;
import gr.hua.ds.service.UserService;
import gr.hua.ds.users.model.Application;
import gr.hua.ds.users.model.Authority;
import gr.hua.ds.users.model.Department;
import gr.hua.ds.users.model.Enums;
import gr.hua.ds.users.model.User;
import gr.hua.ds.users.model.UserInformation;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserInformationService userInformationService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/students")
	public List<User> getStudents() {
		List<User> students = userService.getStudents();
		return students;
	}
	
	@GetMapping("/users")
	public List<User> getUsers() {
		List<User> users = userService.getUsers();
		return users;
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/user/{username}")
	public ResponseEntity getUser(@PathVariable String username) {
		User user = userService.getUserByUsername(username);
		if(user!=null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/students/{username}")
	public ResponseEntity getStudent(@PathVariable String username) {
		User user = userService.getStudentByUsername(username);
		if(user!=null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/departments/{departmentName}")
	public ResponseEntity getDepartment(@PathVariable Enums.Dept departmentName) {
		Department department = departmentService.getDepartment(departmentName);
		if(department!=null) {
			return new ResponseEntity<Department>(department, HttpStatus.OK);
		}
		return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
	}
	
	@GetMapping("/applications")
	public List<Application> getApplications() {
		List<Application> applications = applicationService.getActivatedApplications();
		return applications;
	}
	
	@PostMapping(value = "/addUser", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User addUser(@RequestBody User user) {
		userService.insertUserOnly(user);
		return user;
	}
	
	@PostMapping(value = "/addUserInformation", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserInformation addUserInformation(@RequestBody UserInformation userInformation) {
		userInformationService.insertUserInformation(userInformation);
		return userInformation;
	}
	
	@PostMapping(value = "/addAuthority", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Authority addAuthority(@RequestBody Authority authority) {
		authorityService.insertAuthority(authority);
		return authority;
	}
	
	@PostMapping(value = "/addApplication", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Application addApplication(@RequestBody Application application) {
		applicationService.insertApplication(application);
		return application;
	}

	
	@PatchMapping(value = "/editUser",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public UserInformation editUser(@RequestBody UserInformation userInformation) {
		userInformationService.updateUserInformation(userInformation);
		return userInformation;
	}
	
}
