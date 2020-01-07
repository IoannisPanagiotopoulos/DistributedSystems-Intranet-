package Controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import Algorithms.RankingAlgorithm;
import DataBase.Application;
import DataBase.ApplicationDetails;
import DataBase.Enums;
import DataBase.Officer;
import DataBase.Student;
import Services.ApplicationDAOImpl;
import Services.ApplicationDetailsDAOImpl;
import Services.OfficerDAOImpl;
import Services.StudentDAOImpl;
import iDAO.ApplicationDAO;
import iDAO.ApplicationDetailsDAO;
import iDAO.OfficerDAO;
import iDAO.StudentDAO;

@Controller
public class FormController {

	//o controller autos kanei serve to login.
	@RequestMapping("/loginRegister")
	public String loginRegister(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession context = request.getSession();
		//an erxomaste apo to login page
		if (request.getParameterMap().containsKey("username")) {
			// username kai password sto context
			context.setAttribute("username", request.getParameter("username"));
			context.setAttribute("password", request.getParameter("password"));
		}

		String returnString = null;

		OfficerDAOImpl od = new OfficerDAOImpl();
		String userName = (String) context.getAttribute("username");
		String password = (String) context.getAttribute("password");
		context.setAttribute("username", userName);
		context.setAttribute("password", password);
		//anazhthsh sthn vash gia officer me ta stoixeia pou eishgage o xrhsths
		Officer o = od.getOfficer(userName, password);
		
		//an uparxei
		if (o != null) {
			//an einai aplos officer
			if (String.valueOf(o.getRole()).equals("Officer")) {
				
				//stoixeia pou tha xreiastoume ston kwdika ths efarmoghs
				StudentDAO u = new StudentDAOImpl();
				List<Student> user = u.getUsers(o.getDepartment());
				model.addAttribute("officerName", o.getUsername());
				context.setAttribute("officerName", o.getUsername());
				model.addAttribute("officerDepartment", String.valueOf(o.getDepartment()));
				context.setAttribute("officerDepartment", String.valueOf(o.getDepartment()));
				context.setAttribute("officerRole", String.valueOf(o.getRole()));
				model.addAttribute("userList", user);
				//epistrefoume to page pou einai gia ton aplo officer
				returnString = "welcome";
			
			//an einai Supervisor
			} else if (String.valueOf(o.getRole()).equals("Supervisor")) {
				StudentDAO u = new StudentDAOImpl();
				List<Student> user = u.getUsers();
				model.addAttribute("officerName", String.valueOf(o.getUsername()));
				context.setAttribute("officerName", String.valueOf(o.getUsername()));
				model.addAttribute("officerDepartment", String.valueOf(o.getDepartment()));
				context.setAttribute("officerDepartment", String.valueOf(o.getDepartment()));
				context.setAttribute("officerRole", String.valueOf(o.getRole()));
				model.addAttribute("userList", user);
				//epistrefoume to page pou einai gia ton Supervisor
				returnString = "welcomeSupervisor";
			// an einai Admin
			} else if (String.valueOf(o.getRole()).equals("Admin")) {
				StudentDAO u = new StudentDAOImpl();
				List<Student> user = u.getUsers();
				model.addAttribute("officerName", String.valueOf(o.getUsername()));
				context.setAttribute("officerName", String.valueOf(o.getUsername()));
				model.addAttribute("officerDepartment", String.valueOf(o.getDepartment()));
				context.setAttribute("officerDepartment", String.valueOf(o.getDepartment()));
				context.setAttribute("officerRole", String.valueOf(o.getRole()));
				model.addAttribute("userList", user);
				OfficerDAO od2 = new OfficerDAOImpl();
				List<Officer> officer = od2.getOfficers();
				model.addAttribute("officerList", officer);
				//epistrefoume to page gia ton katalogo xrhstwn
				returnString = "welcomeAdmin";
			}
		//an den uparxei officer me auto to username kai password
		} else {
			model.addAttribute("message", "Wrong Username/Password!");
			//epistrefoume pali sto login page
			returnString = "login";
		}

		return returnString;
	}

	
	@RequestMapping("/studentList")
	public String welcome(HttpServletRequest request, HttpServletResponse response, Model model) {
		StudentDAO sd = new StudentDAOImpl();
		List<Student> students = sd.getUsers();
		model.addAttribute("userList", students);
		return "welcomeSupervisor";
	}

	//o controller autos servirei thn periptwsh update
	@RequestMapping("/update")
	public String update(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession context = request.getSession();
		int userId = Integer.valueOf((String) request.getParameter("id"));
		StudentDAO sd = new StudentDAOImpl();
		Student s = sd.getUser(userId);
		s.setActivated(Enums.StringtoEnumCoverterActivable(request.getParameter("activated")));
		sd.updateUser(s);

		List<Student> students = sd.getUsers(Enums.StringtobeEnumConverterDept(String.valueOf(context.getAttribute("officerDepartment"))));

		model.addAttribute("userList", students);

		return "welcome";
	}

	@RequestMapping("/updatePost")
	public String updatePost(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession context = request.getSession();
		StudentDAO sd = new StudentDAOImpl();
		String activated = null;

		if (request.getParameter("activated").equals("Not Activated")) {
			activated = "0";
		} else {
			activated = "1";
		}

		Student student = new Student();
		student.setId(Integer.valueOf(request.getParameter("id")));
		student.setUser((String) request.getParameter("username"));
		student.setName((String) request.getParameter("name"));
		student.setPassword((String) request.getParameter("password"));
		student.setDept(Enums.StringtobeEnumConverterDept((String) request.getParameter("department")));
		if(activated=="0") {			
			student.setActivated(Enums.Activable.inactive);
		} else {
			student.setActivated(Enums.Activable.active);
		}

		sd.updateUser(student);
		String username = (String) context.getAttribute("username");
		String password = (String) context.getAttribute("password");
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		
		StudentDAO u = new StudentDAOImpl();
		List<Student> user = u.getUsers(Enums.StringtobeEnumConverterDept((String) context.getAttribute("officerDepartment")));
		model.addAttribute("userList", user);
		return "welcome";
	}

	@RequestMapping("/application")
	public String application(HttpServletRequest request, HttpServletResponse response, Model model) {

		ApplicationDAO ad = new ApplicationDAOImpl();
		List<Application> applications = ad.getApplications();
		model.addAttribute("applicationList", applications);
		return "applications";
	}

	@RequestMapping("/applicationStatus")
	public String applicationStatus(HttpServletRequest request, HttpServletResponse response, Model model) {
		ApplicationDAO ad = new ApplicationDAOImpl();
		Application app = new Application();
		
		if (request.getParameter("activate").equals("stop")) {
			
			app.setId(Integer.valueOf(request.getParameter("id")));
			app.setDept(Enums.StringtobeEnumConverterDept((String) request.getParameter("department")));
			app.setActive(Enums.Activable.inactive);
			app.setPercentage(0);
			
			ApplicationDetailsDAO a = new ApplicationDetailsDAOImpl();
			List<ApplicationDetails> applications = a.getActivatedApplicationsByDept(app.getDept());
			RankingAlgorithm algo = new RankingAlgorithm();
			for(ApplicationDetails application: applications) {
				algo.rankingCalculator(application);
			}
		    model.addAttribute("message","Points added!");
		} else {
			
			app.setDept(Enums.StringtobeEnumConverterDept(request.getParameter("department")));
			app.setActive(Enums.Activable.active);
			app.setPercentage(Integer.valueOf(request.getParameter("percentage")));
			app.setId(Integer.valueOf(request.getParameter("id")));	
		}
		
		ad.updateApplication(app);
		
		List<Application> applications = ad.getApplications();
		
		model.addAttribute("applicationList", applications);
		
		return "applications";
	}
	
	@RequestMapping("/applicationDetails")
	public String applicationDetails(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession context = request.getSession();
		ApplicationDetailsDAO ad = new ApplicationDetailsDAOImpl();
		String dept=(String) context.getAttribute("officerDepartment");
		List<ApplicationDetails> applications = ad.getApplications(Enums.StringtobeEnumConverterDept(dept));
		model.addAttribute("applicationList", applications);
		return "applicationDetails";
	}

	@RequestMapping("/studentHandler")
	public String studentHandler(HttpServletRequest request, HttpServletResponse response, Model model) {
		String returnString = null;
		
		if (request.getParameter("action").equals("delete")) {
			
			StudentDAO sd = new StudentDAOImpl();
			OfficerDAO od = new OfficerDAOImpl();
			
			Student s = sd.getUser(Integer.valueOf(request.getParameter("id")));
			
			sd.deleteUser(s);
		
			List<Student> students = sd.getUsers();
			List<Officer> officers = od.getOfficers();
			
			model.addAttribute("userList", students);
			model.addAttribute("officerList", officers);
			
			returnString = "welcomeAdmin";
			
		} else if (request.getParameter("action").equals("edit")) {
			
			StudentDAO sd = new StudentDAOImpl();
			
			Student s = sd.getUser(Integer.valueOf(request.getParameter("id")));
			
		    model.addAttribute("id", s.getId());
		    
		    model.addAttribute("username", s.getUser());
		    model.addAttribute("password", s.getPassword());
		    model.addAttribute("name", s.getName());
		    model.addAttribute("email",s.getEmail());
		    model.addAttribute("department", s.getDept());
		    model.addAttribute("activated", s.getActivated());
			    
			returnString = "updateStudent";		  
			
		} else if (request.getParameter("action").equals("add")) {
			
			returnString = "addStudent";
		}
		return returnString;
	}

	@RequestMapping("/updateStudent")
	public String updateStudent(HttpServletRequest request, HttpServletResponse response, Model model) {
		StudentDAO sd = new StudentDAOImpl();
		OfficerDAO od = new OfficerDAOImpl();
		
		Student s = sd.getUser(Integer.valueOf(request.getParameter("id")));
		
		s.setUser(request.getParameter("username"));
		s.setPassword(request.getParameter("password"));
		s.setName(request.getParameter("name"));
		s.setEmail(request.getParameter("email"));
		s.setDept(Enums.StringtobeEnumConverterDept(request.getParameter("department")));
		s.setActivated(Enums.StringtoEnumCoverterActivable(request.getParameter("activated")));
		
		sd.updateUser(s);
		
		List<Student> students = sd.getUsers();
		List<Officer> officers = od.getOfficers();
		
		model.addAttribute("userList", students);
		model.addAttribute("officerList", officers);
		
		return "welcomeAdmin";
	}

	@RequestMapping("/addStudent")
	public String addStudent(HttpServletRequest request, HttpServletResponse response, Model model) {
		StudentDAO sd = new StudentDAOImpl();
		OfficerDAO od = new OfficerDAOImpl();
		
		List<Student> studentList = sd.getUsers();
		
		boolean found=false;
		
		for(Student st:studentList) {
			if(st.getUser().equals(request.getParameter("username")) || st.getEmail().equals(request.getParameter("email"))){
				found=true;
				break;
			}
		}
		if(found==true) {
			model.addAttribute("errorun","Student with suck username/email already exists!");
			return  "addStudent";
		}
			
		Student s = new Student();
		
		s.setUser(request.getParameter("username"));
		s.setPassword(request.getParameter("password"));
		s.setName(request.getParameter("name"));
		s.setEmail(request.getParameter("email"));
		s.setDept(Enums.StringtobeEnumConverterDept(request.getParameter("department")));
		s.setActivated(Enums.StringtoEnumCoverterActivable(request.getParameter("activated")));
		
		sd.insertUser(s);
		
		List<Student> students = sd.getUsers();
		List<Officer> officers = od.getOfficers();
		
		model.addAttribute("userList", students);
		model.addAttribute("officerList", officers);
		
		return "welcomeAdmin";
	}

	@RequestMapping("/officerHandler")
	public String officerHandler(HttpServletRequest request, HttpServletResponse response, Model model) {
		String returnString = null;

		if (request.getParameter("action").equals("delete")) {

			OfficerDAO od = new OfficerDAOImpl();
			StudentDAO sd = new StudentDAOImpl();

			Officer o = od.getOfficer(request.getParameter("username"), request.getParameter("password"));

			od.removeOfficer(o);

			List<Officer> officers = od.getOfficers();
			List<Student> students = sd.getUsers();

			model.addAttribute("officerList", officers);
			model.addAttribute("userList", students);

			returnString = "welcomeAdmin";

		} else if (request.getParameter("action").equals("edit")) {

			OfficerDAO od = new OfficerDAOImpl();

			Officer o = od.getOfficer(request.getParameter("username"), request.getParameter("password"));

			model.addAttribute("id", o.getId());
			model.addAttribute("username", o.getUsername());
			model.addAttribute("password", o.getPassword());
			model.addAttribute("department",o.getDepartment());
			model.addAttribute("role", o.getRole());

			returnString = "updateOfficer";

		} else if (request.getParameter("action").equals("add")) {

			returnString = "addOfficer";
		}
		return returnString;
	}

	@RequestMapping("/updateOfficer")
	public String updateOfficer(HttpServletRequest request, HttpServletResponse response, Model model) {
		OfficerDAO od = new OfficerDAOImpl();
		StudentDAO sd = new StudentDAOImpl();

		Officer o = od.getOfficer(Integer.valueOf(request.getParameter("id")));
        
		o.setUsername(request.getParameter("username"));
		o.setPassword(request.getParameter("password"));
		o.setDepartment(Enums.StringtobeEnumConverterDept(request.getParameter("department")));
		o.setRole(Enums.StringtoEnumConverterRole(request.getParameter("role")));

		od.updateOfficer(o);

		List<Officer> officers = od.getOfficers();
		List<Student> students = sd.getUsers();

		model.addAttribute("officerList", officers);
		model.addAttribute("userList", students);

		return "welcomeAdmin";
	}

	@RequestMapping("/addOfficer")
	public String addOfficer(HttpServletRequest request, HttpServletResponse response, Model model) {
		OfficerDAO od = new OfficerDAOImpl();
		StudentDAO sd = new StudentDAOImpl();

		Officer o = new Officer();

		o.setUsername(request.getParameter("username"));
		o.setPassword(request.getParameter("password"));
		o.setDepartment(Enums.StringtobeEnumConverterDept(request.getParameter("department")));
		o.setRole(Enums.StringtoEnumConverterRole(request.getParameter("role")));

		od.insertOfficer(o);

		List<Officer> officers = od.getOfficers();
		List<Student> students = sd.getUsers();

		model.addAttribute("officerList", officers);
		model.addAttribute("userList", students);

		return "welcomeAdmin";
	}
	
	@RequestMapping("/applicationDetailSubmit")
	public String applicationDetailSubmit(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession context = request.getSession();
		ApplicationDetailsDAO ad = new ApplicationDetailsDAOImpl();
		ApplicationDetails application = ad.getApplication(Integer.valueOf(request.getParameter("id")));
		if(request.getParameter("submit").equals("approve")) {
			application.setActive(Enums.Activable.active);
			ad.updateApplication(application);
		} else {		
			ad.deleteApplication(application);
		}
		List<ApplicationDetails> applications = ad.getApplications(Enums.StringtobeEnumConverterDept(String.valueOf(context.getAttribute("officerDepartment"))));
		model.addAttribute("applicationList", applications);
		return "applicationDetails";
	}
}
