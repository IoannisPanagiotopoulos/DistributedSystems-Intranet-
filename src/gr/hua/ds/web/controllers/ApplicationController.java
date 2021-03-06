package gr.hua.ds.web.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import gr.hua.ds.service.ApplicationService;
import gr.hua.ds.service.UserInformationService;
import gr.hua.ds.service.UserService;
import gr.hua.ds.users.model.Application;
import gr.hua.ds.users.model.Enums.Activable;
import gr.hua.ds.users.model.User;

@Controller
@RequestMapping("/application")
public class ApplicationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserInformationService userInformationService;
	
	@Autowired
	private ApplicationService applicationService;

	@Secured("ROLE_OFFICER")
	@GetMapping("/list")
	public String showApplications(Principal principal, Model model) {
		User user = userService.getOfficerByUsername(principal.getName());

		List<Application> applications = applicationService.getApplicationsByDepartment(user.getUserInformation().getDepartmentName());
		model.addAttribute("applications", applications);
		
		return "list-application";
	}
	
	@Secured("ROLE_OFFICER")
	@PostMapping("/handle")
	public RedirectView handleApplication(HttpServletRequest request, RedirectAttributes redir) {
		String username = request.getParameter("username");
		Application application = applicationService.getApplicationActiveByUsername(username);
		User user = userService.getUserByUsername(username);
		if(request.getParameter("submit").equals("approve")) {
			application.setActive(Activable.active);
		} else {
			user.getUserInformation().setHasFreeMeal(0);
			application.setActive(Activable.inactive);
		}
		userInformationService.updateUserInformation(user.getUserInformation());
		applicationService.updateApplication(application);
		redir.addFlashAttribute("success", "Application Updated !");
		return new RedirectView(request.getContextPath()+"/application/list");
	}
}
