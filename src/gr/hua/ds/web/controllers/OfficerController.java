package gr.hua.ds.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import gr.hua.ds.users.model.Authority;
import gr.hua.ds.users.model.Enums;
import gr.hua.ds.users.model.User;
import gr.hua.ds.users.model.UserInformation;

@Controller
@RequestMapping("/officer")
public class OfficerController {
	
	@Autowired 
	private UserDAO userDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	};
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/list")
	public String listOfficers(Model model) {
		List<User> officers = userDao.getOfficers();
		model.addAttribute("officers", officers);
		return "list-officer";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/add")
	public String addOfficer(Model model) {
		return "form-officer-add";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/id/{username}")
	public String getOfficer(Model model, @PathVariable("username") String username) {
		User officer = userDao.getOfficerByUsername(username);
		model.addAttribute("officer", officer);
		return "form-officer-handle";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/handle")
	public RedirectView handleOfficer(HttpServletRequest request, Model model) {
		User user = userDao.getOfficerByUsername((String)request.getParameter("username"));
		if(user!=null) {
			if(request.getParameter("action").equals("delete")) {
				userDao.deleteUser(user);
				return new RedirectView(request.getContextPath()+"/officer/list");
			}else {
				User newUser = user;
				newUser.setPassword(passwordEncoder.encode(request.getParameter("password")));
				newUser.getUserInformation().setName(request.getParameter("name"));
				newUser.getUserInformation().setEmail(request.getParameter("email"));
				newUser.getUserInformation().setDepartmentName(Enums.StringtobeEnumConverterDept(request.getParameter("department")));
				newUser.getAuthority().setAuthorityRole(Enums.StringtoEnumConverterRole(request.getParameter("role")));
				
				userDao.updateUser(user, newUser);
				return new RedirectView(request.getContextPath()+"/officer/list");
			}
		} else {
			model.addAttribute("error", "Error");
			return new RedirectView(request.getContextPath()+"/officer/list");
		}
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/addPost")
	public RedirectView addOfficerPost(HttpServletRequest request, Model model) {
		User u = userDao.getOfficerByUsername((String)request.getParameter("username"));
		if(u==null) {
			User user = new User();
			UserInformation ui = new UserInformation();
			Authority a = new Authority();
			
			user.setUserInformation(ui);
			user.setAuthority(a);
			
			user.setUsername(request.getParameter("username"));
			user.setPassword(passwordEncoder.encode(request.getParameter("password")));
			user.setEnabled(1);
			user.getUserInformation().setUsername(user.getUsername());
			user.getUserInformation().setName(request.getParameter("name"));
			user.getUserInformation().setEmail(request.getParameter("email"));
			user.getUserInformation().setDepartmentName(Enums.StringtobeEnumConverterDept(request.getParameter("department")));
			user.getAuthority().setUsername(user.getUsername());
			user.getAuthority().setAuthorityRole(Enums.StringtoEnumConverterRole(request.getParameter("role")));
			
			userDao.insertUser(user);
			
			return new RedirectView(request.getContextPath()+"/officer/list");
		} else {
			model.addAttribute("error", "Username Already Exists!");
			return new RedirectView(request.getContextPath()+"/officer/list");
		}
	}
	
}
