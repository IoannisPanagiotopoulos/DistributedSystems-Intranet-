package gr.hua.ds.web.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import gr.hua.ds.users.dao.ApplicationDAO;
import gr.hua.ds.users.dao.UserDAO;
import gr.hua.ds.users.daoimpl.ApplicationDAOImpl;
import gr.hua.ds.users.daoimpl.UserDAOImpl;
import gr.hua.ds.users.model.Application;
import gr.hua.ds.users.model.Enums;
import gr.hua.ds.users.model.User;

@Controller
@RequestMapping("/application")
public class ApplicationController {

	@Secured("ROLE_OFFICER")
	@GetMapping("/list")
	public String showApplications(Principal principal, Model model) {
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getOfficerByUsername(principal.getName());
		ApplicationDAO applicationDAO = new ApplicationDAOImpl();
		List<Application> applications = applicationDAO.getApplicationsByDepartment(user.getUserInformation().getDepartmentName());
		model.addAttribute("applications", applications);
		
		return "list-application";
	}
	
	@Secured("ROLE_OFFICER")
	@PostMapping("/handle")
	public RedirectView handleApplication(HttpServletRequest request) {
		ApplicationDAO ad = new ApplicationDAOImpl();
		Application application = ad.getApplicationByUsername(request.getParameter("username"));
		if(request.getParameter("submit").equals("approve")) {
			application.setActive(Enums.Activable.active);
			ad.updateApplication(application);
		} else {		
			ad.deleteApplication(application);
		}
		return new RedirectView(request.getContextPath()+"/application/list");
	}
}
