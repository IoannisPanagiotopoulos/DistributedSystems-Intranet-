package Controllers;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	//o controller autos kanei logout.
	@RequestMapping("/logoutSession")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		//HttpSession context = request.getSession();
		request.getSession(false);
		//context.setAttribute("username", "");
		//context.setAttribute("password", "");
		//context.setAttribute("officerName", "");
		
		return "login";
	}
}