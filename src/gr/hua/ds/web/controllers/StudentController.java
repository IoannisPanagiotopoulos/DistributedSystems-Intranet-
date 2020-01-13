package gr.hua.ds.web.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import gr.hua.ds.users.dao.UserDAO;
import gr.hua.ds.users.daoimpl.UserDAOImpl;
import gr.hua.ds.users.model.Enums.Activable;
import gr.hua.ds.users.model.Enums.Role;
import gr.hua.ds.users.model.Authority;
import gr.hua.ds.users.model.Enums;
import gr.hua.ds.users.model.User;
import gr.hua.ds.users.model.UserInformation;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};
	
	@Secured({"ROLE_SUPERVISOR", "ROLE_OFFICER", "ROLE_ADMIN"})
	@GetMapping("/list")
	public String listStudents(Principal principal, Model model) {
		List<User> students = null;
		UserDAO userDAO = new UserDAOImpl();
		User u = userDAO.getOfficerByUsername((String)principal.getName());
		if(u.getAuthority().getAuthorityRole().equals(Role.ROLE_ADMIN) ||
				u.getAuthority().getAuthorityRole().equals(Role.ROLE_SUPERVISOR)) {
			students = userDAO.getStudents();
		} else {
			students = userDAO.getStudentsByDept(u.getUserInformation().getDepartmentName());
		}
		model.addAttribute("students", students);
		return "list-student";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/add")
	public String addStudent() {
		return "form-student-add";
	}
	
	@PostMapping("/id/{username}")
	public String getStudent(Model model, @PathVariable("username") String username) {
		UserDAO userDAO = new UserDAOImpl();
		User student = userDAO.getUserByUsername(username);
		model.addAttribute("student", student);
		return "form-student-handle";
	}
	
	@PostMapping("/handle")
	public RedirectView handleStudent(HttpServletRequest request, Model model) {
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getStudentByUsername((String)request.getParameter("username"));
		if(user!=null) {
			if(request.getParameter("action").equals("delete")) {
				userDAO.deleteUser(user);
				return new RedirectView(request.getContextPath()+"/student/list");
			}else {
				User newUser = user;
				newUser.setPassword(passwordEncoder().encode(request.getParameter("password")));
				newUser.getUserInformation().setName(request.getParameter("name"));
				newUser.getUserInformation().setEmail(request.getParameter("email"));
				newUser.getUserInformation().setDepartmentName(Enums.StringtobeEnumConverterDept(request.getParameter("departmentName")));
				newUser.getUserInformation().setActivated(Enums.StringtoEnumCoverterActivable(request.getParameter("activated")));
				
				userDAO.updateUser(user, newUser);
				return new RedirectView(request.getContextPath()+"/student/list");
			}
		} else {
			model.addAttribute("error", "Error");
			return new RedirectView(request.getContextPath()+"/student/list");
		}
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/addPost")
	public RedirectView addStudentPost(HttpServletRequest request, Model model) {
		UserDAO userDAO = new UserDAOImpl();
		User u = userDAO.getOfficerByUsername((String)request.getParameter("username"));
		if(u==null) {
			User user = new User();
			UserInformation ui = new UserInformation();
			Authority a = new Authority();
			
			user.setUserInformation(ui);
			user.setAuthority(a);
			
			user.setUsername(request.getParameter("username"));
			user.setPassword(passwordEncoder().encode(request.getParameter("password")));
			user.setEnabled(1);
			user.getUserInformation().setUsername(user.getUsername());
			user.getUserInformation().setName(request.getParameter("name"));
			user.getUserInformation().setEmail(request.getParameter("email"));
			user.getUserInformation().setDepartmentName(Enums.StringtobeEnumConverterDept(request.getParameter("department")));
			user.getUserInformation().setActivated(Activable.inactive);
			user.getAuthority().setUsername(user.getUsername());
			user.getAuthority().setAuthorityRole(Role.ROLE_STUDENT);
			
			userDAO.insertUser(user);
			
			return new RedirectView(request.getContextPath()+"/student/list");
		} else {
			model.addAttribute("error", "Username Already Exists!");
			return new RedirectView(request.getContextPath()+"/student/add");
		}
	}
	
}