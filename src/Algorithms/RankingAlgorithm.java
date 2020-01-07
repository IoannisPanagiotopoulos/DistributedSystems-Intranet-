package Algorithms;

import java.util.ArrayList;
import java.util.List;

import DataBase.Application;
import DataBase.ApplicationDetails;
import DataBase.Student;
import Services.ApplicationDAOImpl;
import Services.ApplicationDetailsDAOImpl;
import Services.StudentDAOImpl;
import iDAO.StudentDAO;

public class RankingAlgorithm {
	//h klassi auth den xrhsimopoieitai akoma
	public List<Student> RankingAlgorithm1(int percentage) {
		ApplicationDetailsDAOImpl applications = new ApplicationDetailsDAOImpl();
		List<ApplicationDetails> applicationlist = null;
		DataBase.Enums.Dept department = null;
		StudentDAOImpl studentdata = new StudentDAOImpl();
		List<Student> students;
		ArrayList<Student> studentsactivated=new ArrayList<>();
		applicationlist = applications.getActivatedApplications();
		for (ApplicationDetails each : applicationlist) {
			rankingCalculator(each);
		}
		float b = applicationlist.size() * (percentage / 100);
		int c = (int) b +1;
		System.out.println("here is your size :"+c);
		students = studentdata.SortByPoints();
		System.out.println(students.size());
		for (int st = 0; st < c; st++) {
			System.out.println("iterating");
			if(String.valueOf(students.get(st).getActivated()).equals("active")){
			 System.out.println(students.get(st).getPoints());
			 Student stadd=students.get(st);
			studentsactivated.add(stadd);
			}
		}
		
		System.out.println("You are done"+studentsactivated.size());
		return studentsactivated;
	}
	//pairnoume to application tou student kai analogws ta paidia dinoume kai tous analogous pontous
	public void rankingCalculator(ApplicationDetails app) {
		int points = 0;
		if (app.getIncome() == 0 && String.valueOf(app.getParent1_employmentStatus()).equals("unemp")
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
		StudentDAO sd = new StudentDAOImpl();
		System.out.println(app.getUsername());
		Student student = sd.getUser(app.getUsername());
		System.out.println(student.toString());
		student.setPoints(points);
		sd.updateUser(student);
	}
}
