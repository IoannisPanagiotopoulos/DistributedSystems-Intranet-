package gr.hua.ds.web.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gr.hua.ds.users.dao.UserDAO;
import gr.hua.ds.users.daoimpl.UserDAOImpl;
import gr.hua.ds.users.model.User;
import gr.hua.ds.users.model.Enums;
import gr.hua.ds.users.model.Enums.*;

@Controller
public class LoginController {
	
	@GetMapping(path="/login")
	public String showLoginPage() {
	  return "login";
	}
	
//	@RequestMapping("/")
//	public String loginPost(HttpServletRequest request, HttpServletResponse response, Model model, Principal principal) {
//		HttpSession context = request.getSession();
//		UserDAO ud = new UserDAOImpl();
//		User officer = ud.getUserByUsername(principal.getName());
//		if(officer.getAuthority().getAuthorityRole().equals(Role.ROLE_STUDENT)) {
//			return "login";
//		}
//		context.setAttribute("username", officer.getUsername());
//		context.setAttribute("password", officer.getPassword());
//
//		
//		String returnString = null;
//		
//		List<User> students = null;
//		
//		model.addAttribute("officerName", officer.getUsername());
//		context.setAttribute("officerName", officer.getUsername());
//		context.setAttribute("officerRole", String.valueOf(officer.getAuthority().getAuthorityRole()));
//		
//		//If it is an officer
//		if (officer.getAuthority().getAuthorityRole().equals(Role.ROLE_OFFICER)) {
//			model.addAttribute("officerDepartment", String.valueOf(officer.getUserInformation().getDepartmentName()));
//			context.setAttribute("officerDepartment", String.valueOf(officer.getUserInformation().getDepartmentName()));
//			students = ud.getStudentsByDept(officer.getUserInformation().getDepartmentName());
//			returnString = "welcome";
//		//If it is a supervisor
//		} else if (officer.getAuthority().getAuthorityRole().equals(Role.ROLE_SUPERVISOR)) {
//			students = ud.getStudents();
//			returnString = "welcomeSupervisor";
//		// If it is an admin
//		} else if (officer.getAuthority().getAuthorityRole().equals(Role.ROLE_ADMIN)) {
//			students = ud.getStudents();
//			List<User> officers = ud.getOfficers();
//			model.addAttribute("officerList", officers);
//			returnString = "welcomeAdmin";
//		}
//		model.addAttribute("studentList", students);
//
//
//		return returnString;
//	}
}