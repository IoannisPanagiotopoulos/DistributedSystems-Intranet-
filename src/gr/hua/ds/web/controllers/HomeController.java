package gr.hua.ds.web.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gr.hua.ds.users.dao.UserDAO;
import gr.hua.ds.users.model.User;

@Controller
public class HomeController {
	
	@Autowired
	private UserDAO userDao;

	@GetMapping("/")
	public String showHomePage(Model model, Principal principal) {
		User user = userDao.getOfficerByUsername(principal.getName());
		model.addAttribute("officerName", user.getUserInformation().getName());
		return "index";
	}
	
}
