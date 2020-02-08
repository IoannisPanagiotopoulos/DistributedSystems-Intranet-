package gr.hua.ds.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.ds.users.dao.DepartmentDAO;
import gr.hua.ds.users.dao.UserDAO;
import gr.hua.ds.users.dao.UserInformationDAO;
import gr.hua.ds.users.model.Application;
import gr.hua.ds.users.model.Department;
import gr.hua.ds.users.model.User;

@Service
public class RankingAlgorithm {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private UserInformationDAO userInformationDao;
	
	@Autowired
	private DepartmentDAO departmentService;
	
	//pairnoume to application tou student kai analogws ta paidia dinoume kai tous analogous pontous
	
	@Transactional
	public void rankingCalculator(List<Application> applications) {
	
		if(!applications.isEmpty()) {		
			double departmentPercentage = departmentService.getDepartment(applications.get(0).getDepartmentName()).getPercentage();
			departmentPercentage =  departmentPercentage/ 100;
			
			int totalApplications = 0;
			
			List<User> rankedUsers = new ArrayList<User>();
			
			for (Application app : applications) {			
				int points = 0;
				if (app.getPersonalIncome() == 0 && String.valueOf(app.getParent1_employmentStatus()).equals("unemp")
						&& String.valueOf(app.getParent2_employmentStatus()).contentEquals("unemp")) {
					points += 999;
				} else {
					if (app.getFamilyIncome() < 10000) {
						points += 100;
					} else if (app.getFamilyIncome() >= 10000 && app.getFamilyIncome() < 15000) {
						points += 30;
					}
					;
					
					for (int i = 0; i < app.getSiblingsStudents(); i++) {
						points += 20;
					}
					
					if (!app.getCity().equals("Athens")) {
						points += 50;
					}
				}
				
				totalApplications++;
				
				User oldUser = userDao.getUserByUsername(app.getUsername());
				User newUser = oldUser;
				newUser.getUserInformation().setPoints(points);
				userInformationDao.updateUserInformation(newUser.getUserInformation());
				rankedUsers.add(userDao.getUserByUsername(app.getUsername()));
			}
			
			Collections.sort(rankedUsers, (o1, o2) -> o2.getUserInformation().getPoints() - o1.getUserInformation().getPoints());
			
			for(int i=0; i<=rankedUsers.size()-1; i++) {
				rankedUsers.get(i).getUserInformation().setRank(i+1);
				rankedUsers.get(i).getUserInformation().setTotalRanks(totalApplications);
				double number = Math.floor(totalApplications*departmentPercentage);
				if(i<number) {
					rankedUsers.get(i).getUserInformation().setHasFreeMeal(1);
				} else {
					rankedUsers.get(i).getUserInformation().setHasFreeMeal(0);
				}
				userInformationDao.updateUserInformation(rankedUsers.get(i).getUserInformation());
			}
		}
	}
	
	@Transactional
	public boolean applicationHasBegun() {
		List<Department> departments = departmentService.getDepartments();
		boolean flag = false;
		
		for(Department department : departments ) {
			if(department.getHasBegun() == 1) {
				flag = true;
				break;
			}
		}
		
		return flag;
	}
	
	
	//h klassi auth den xrhsimopoieitai akoma
//	public List<User> RankingAlgorithm1(int percentage) {
//		ApplicationDAO ad = new ApplicationDAOImpl();
//		List<Application> applicationlist = null;
//		Enums.Dept department = null;
//		StudentDAOImpl studentdata = new StudentDAOImpl();
//		List<User> students;
//		ArrayList<User> studentsactivated=new ArrayList<>();
//		applicationlist = applications.getActivatedApplications();
//		for (ApplicationDetails each : applicationlist) {
//			rankingCalculator(each);
//		}
//		float b = applicationlist.size() * (percentage / 100);
//		int c = (int) b +1;
//		System.out.println("here is your size :"+c);
//		students = studentdata.SortByPoints();
//		System.out.println(students.size());
//		for (int st = 0; st < c; st++) {
//			System.out.println("iterating");
//			if(String.valueOf(students.get(st).getActivated()).equals("active")){
//			 System.out.println(students.get(st).getPoints());
//			 User stadd=students.get(st);
//			studentsactivated.add(stadd);
//			}
//		}
//		
//		System.out.println("You are done"+studentsactivated.size());
//		return studentsactivated;
//	}
}
