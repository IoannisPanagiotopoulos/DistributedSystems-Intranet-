package gr.hua.ds.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import gr.hua.ds.algorithms.RankingAlgorithm;
import gr.hua.ds.users.dao.ApplicationDAO;
import gr.hua.ds.users.dao.DepartmentDAO;
import gr.hua.ds.users.daoimpl.ApplicationDAOImpl;
import gr.hua.ds.users.daoimpl.DepartmentDAOImpl;
import gr.hua.ds.users.model.Application;
import gr.hua.ds.users.model.Department;
import gr.hua.ds.users.model.Enums;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Secured("ROLE_SUPERVISOR")
	@GetMapping("/list")
	public String getDepartments(Model model) {
		DepartmentDAO departmentDAO = new DepartmentDAOImpl();
		List<Department> departments = departmentDAO.getDepartments();
		model.addAttribute("departments", departments);
		return "list-department";
	}
	
	@Secured("ROLE_SUPERVISOR")
	@GetMapping("/departmentActivate")
	public RedirectView activateDepartment(HttpServletRequest request, Model model) {
		DepartmentDAO departmentDAO = new DepartmentDAOImpl();
		Department department = departmentDAO.getDepartment(Enums.StringtobeEnumConverterDept(request.getParameter("departmentName")));
		
		if (request.getParameter("activate").equals("stop")) {
			
			department.setDepartmentName(Enums.StringtobeEnumConverterDept((String) request.getParameter("departmentName")));
			department.setActive(Enums.Activable.inactive);
			department.setPercentage(0);
			
			ApplicationDAO applicationDAO = new ApplicationDAOImpl();
			
			List<Application> applications = applicationDAO.getActivatedApplicationsByDept(department.getDepartmentName());
			RankingAlgorithm algo = new RankingAlgorithm();
			for(Application application: applications) {
				algo.rankingCalculator(application);
			}
		    model.addAttribute("message","Points added!");
		    departmentDAO.updateDepartment(department);
		    return new RedirectView("/list");
		} else {
			
			department.setDepartmentName(Enums.StringtobeEnumConverterDept(request.getParameter("department")));
			department.setActive(Enums.Activable.active);
			department.setPercentage(Integer.valueOf(request.getParameter("percentage")));	
			
			departmentDAO.updateDepartment(department);
			
			return new RedirectView(request.getContextPath()+"/list");
		}
	}
}
