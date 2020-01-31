package gr.hua.ds.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import gr.hua.ds.algorithms.RankingAlgorithm;
import gr.hua.ds.users.dao.ApplicationDAO;
import gr.hua.ds.users.dao.DepartmentDAO;
import gr.hua.ds.users.model.Application;
import gr.hua.ds.users.model.Department;
import gr.hua.ds.users.model.Enums;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentDAO departmentDao;
	
	@Autowired
	private ApplicationDAO applicationDao;
	
	@Secured("ROLE_SUPERVISOR")
	@GetMapping("/list")
	public String getDepartments(Model model) {
		List<Department> departments = departmentDao.getDepartments();
		model.addAttribute("departments", departments);
		return "list-department";
	}
	
	@Secured("ROLE_SUPERVISOR")
	@PostMapping("/activate")
	public RedirectView activateDepartment(HttpServletRequest request, Model model) {
		Department department = departmentDao
				.getDepartment(Enums.StringtobeEnumConverterDept(request.getParameter("department")));
		
		if (request.getParameter("activate").equals("stop")) {
			
			department
				.setDepartmentName(Enums.StringtobeEnumConverterDept((String) request.getParameter("department")));
			department.setActive(Enums.Activable.inactive);
			department.setPercentage(0);
			
			List<Application> applications = applicationDao.
					getActivatedApplicationsByDept(department.getDepartmentName());
			
			RankingAlgorithm algo = new RankingAlgorithm();
			
			for(Application application: applications) {
				algo.rankingCalculator(application);
			}
			
		    departmentDao.updateDepartment(department);
		    
		    return new RedirectView(request.getContextPath()+"/department/list");
		} else {
			
			department
				.setDepartmentName(Enums.StringtobeEnumConverterDept(request.getParameter("department")));
			department.setActive(Enums.Activable.active);
			department.setPercentage(Integer.valueOf(request.getParameter("percentage")));	
			
			departmentDao.updateDepartment(department);
			
			return new RedirectView(request.getContextPath()+"/department/list");
		}
	}
}
