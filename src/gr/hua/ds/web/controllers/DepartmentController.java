package gr.hua.ds.web.controllers;

import java.util.ArrayList;
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

import gr.hua.ds.algorithms.RankingAlgorithm;
import gr.hua.ds.service.ApplicationService;
import gr.hua.ds.service.AuthorityService;
import gr.hua.ds.service.DepartmentService;
import gr.hua.ds.service.UserInformationService;
import gr.hua.ds.service.UserService;
import gr.hua.ds.users.model.Application;
import gr.hua.ds.users.model.Department;
import gr.hua.ds.users.model.Enums;
import gr.hua.ds.users.model.Enums.Activable;
import gr.hua.ds.users.model.User;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private RankingAlgorithm rankingAlgorithmService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserInformationService userInformationService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Secured("ROLE_SUPERVISOR")
	@GetMapping("/list")
	public String getDepartments(Model model) {
		List<Department> departments = departmentService.getDepartments();
		List<Integer> totalApplications = new ArrayList<Integer>();
		for(Department department : departments) {
			//totalApplications.add(department.getApplications().size());
			int count = applicationService.getActivatedApplicationsByDept(department.getDepartmentName()).size();
			totalApplications.add(count);
		}
		model.addAttribute("departments", departments);
		model.addAttribute("totalApplications", totalApplications);
		return "list-department";
	}
	
	@Secured("ROLE_SUPERVISOR")
	@PostMapping("/activate")
	public RedirectView activateDepartment(HttpServletRequest request, Model model, RedirectAttributes redir) {
		Department department = departmentService
				.getDepartment(Enums.StringtobeEnumConverterDept(request.getParameter("department")));
		
		if (request.getParameter("activate").equals("stop")) {
			
			department
				.setDepartmentName(Enums.StringtobeEnumConverterDept((String) request.getParameter("department")));
			department.setPercentage(Integer.valueOf(request.getParameter("percentage")));
			department.setActive(Enums.Activable.inactive);
			department.setHasBegun(1);
			
			List<Application> applications = applicationService.
					getActivatedApplicationsByDept(department.getDepartmentName());

			departmentService.updateDepartment(department);
			
			rankingAlgorithmService.rankingCalculator(applications);
			
		    departmentService.updateDepartment(department);
		    
		    redir.addFlashAttribute("success", "Application Process Stopped !");
		    return new RedirectView(request.getContextPath()+"/department/list");
		} else {
			
			if(department.getHasBegun() == 1) {
			    redir.addFlashAttribute("error", "Reset the System First !");
				return new RedirectView(request.getContextPath()+"/department/list");
			}
			
			department
				.setDepartmentName(Enums.StringtobeEnumConverterDept(request.getParameter("department")));
			department.setActive(Enums.Activable.active);
			
			departmentService.updateDepartment(department);
			
			redir.addFlashAttribute("success", "Application Process Started !");
			return new RedirectView(request.getContextPath()+"/department/list");
		}
	}
	
	@Secured("ROLE_SUPERVISOR")
	@PostMapping("/reset")
	public RedirectView newYear(HttpServletRequest request, Model model, RedirectAttributes redir) {
		List<User> students = userService.getStudents();
		System.out.println(students);
		for(User student : students) {
			if(student.getApplication() != null) {
				applicationService.deleteApplication(student.getApplication());
			}
			if(student.getUserInformation() != null) {
				userInformationService.deleteUserInformation(student.getUserInformation());				
			}
			if(student.getAuthority() != null) {				
				authorityService.deleteAuthority(student.getAuthority());
			}
			
			userService.deleteUser(student);
		}
		
		List<Department> departments = departmentService.getDepartments();
		for(Department department : departments) {
			department.setActive(Activable.inactive);
			department.setPercentage(0);
			department.setHasBegun(0);
			departmentService.updateDepartment(department);
		}
		
		redir.addFlashAttribute("success", "System reset !");
		return new RedirectView(request.getContextPath()+"/department/list");
	}
}
