package gr.hua.ds.web.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.ds.algorithms.RankingAlgorithm;
import gr.hua.ds.users.dao.ApplicationDAO;
import gr.hua.ds.users.dao.AuthorityDAO;
import gr.hua.ds.users.dao.DepartmentDAO;
import gr.hua.ds.users.dao.UserDAO;
import gr.hua.ds.users.dao.UserInformationDAO;
import gr.hua.ds.users.daoimpl.ApplicationDAOImpl;
import gr.hua.ds.users.daoimpl.AuthorityDAOImpl;
import gr.hua.ds.users.daoimpl.DepartmentDAOImpl;
import gr.hua.ds.users.daoimpl.UserDAOImpl;
import gr.hua.ds.users.daoimpl.UserInformationDAOImpl;
import gr.hua.ds.users.model.Application;
import gr.hua.ds.users.model.Authority;
import gr.hua.ds.users.model.Department;
import gr.hua.ds.users.model.Enums;
import gr.hua.ds.users.model.Enums.Role;
import gr.hua.ds.users.model.User;
import gr.hua.ds.users.model.UserInformation;

@Controller
public class FormController {
	
	
	
	@RequestMapping("/403")
	public String error(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "403";
	}
	
	@RequestMapping("/studentList")
	public String welcome(HttpServletRequest request, HttpServletResponse response, Model model) {
		UserDAO ud = new UserDAOImpl();
		List<User> students = ud.getStudents();
		model.addAttribute("studentList", students);
		return "welcomeSupervisor";
	}

	//This method receives the student object and changes the activated value
	@RequestMapping("/updateActivated")
	public String update(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession context = request.getSession();
		
		String username = (String) request.getParameter("username");
		UserDAO ud = new UserDAOImpl();
		User oldUser = ud.getUserByUsername(username);
		User newUser = oldUser;
		newUser.getUserInformation().setActivated(Enums.StringtoEnumCoverterActivable(request.getParameter("activated")));
		
		ud.updateUser(oldUser, newUser);

		//receive students
		List<User> students = ud.getStudentsByDept(Enums.StringtobeEnumConverterDept(String.valueOf(context.getAttribute("officerDepartment"))));
		model.addAttribute("studentList", students);

		return "welcome";
	}

	@RequestMapping("/department")
	public String department(HttpServletRequest request, HttpServletResponse response, Model model) {

		DepartmentDAO dd = new DepartmentDAOImpl();
		List<Department> departments = dd.getDepartments();
		model.addAttribute("departmentList", departments);
		return "departments";
	}

	@RequestMapping("/departmentStatus")
	public String departmentStatus(HttpServletRequest request, HttpServletResponse response, Model model) {
		DepartmentDAO dd = new DepartmentDAOImpl();
		Department department = new Department();
		
		if (request.getParameter("activate").equals("stop")) {
			
			department.setDepartmentName(Enums.StringtobeEnumConverterDept((String) request.getParameter("department")));
			department.setActive(Enums.Activable.inactive);
			department.setPercentage(0);
			
			ApplicationDAO ad = new ApplicationDAOImpl();
			List<Application> applications = ad.getActivatedApplicationsByDept(department.getDepartmentName());
			RankingAlgorithm algo = new RankingAlgorithm();
			for(Application application: applications) {
				algo.rankingCalculator(application);
			}
		    model.addAttribute("message","Points added!");
		} else {
			
			department.setDepartmentName(Enums.StringtobeEnumConverterDept(request.getParameter("department")));
			department.setActive(Enums.Activable.active);
			department.setPercentage(Integer.valueOf(request.getParameter("percentage")));	
		}
		
		dd.updateDepartment(department);
		
		List<Department> departments = dd.getDepartments();
		
		model.addAttribute("departmentList", departments);
		
		return "departments";
	}
	
	@RequestMapping("/application")
	public String application(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession context = request.getSession();
		ApplicationDAO ad = new ApplicationDAOImpl();
		String department=(String) context.getAttribute("officerDepartment");
		List<Application> applications = ad.getApplications(Enums.StringtobeEnumConverterDept(department));
		model.addAttribute("applicationList", applications);
		return "applications";
	}

	
	@RequestMapping("/applicationSubmit")
	public String applicationSubmit(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession context = request.getSession();
		ApplicationDAO ad = new ApplicationDAOImpl();
		Application application = ad.getApplicationByUsername((String)request.getParameter("username"));
		if(request.getParameter("submit").equals("approve")) {
			application.setActive(Enums.Activable.active);
			ad.updateApplication(application);
		} else {		
			ad.deleteApplication(application);
		}
		List<Application> applications = ad.getApplications(Enums.StringtobeEnumConverterDept(String.valueOf(context.getAttribute("officerDepartment"))));
		model.addAttribute("applicationList", applications);
		return "applications";
	}
}
