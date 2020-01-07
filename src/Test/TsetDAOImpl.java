package Test;

import java.util.List;

import DataBase.Application;
import DataBase.ApplicationDetails;
import DataBase.Enums;
import DataBase.Officer;
import Services.ApplicationDAOImpl;
import Services.ApplicationDetailsDAOImpl;
import Services.OfficerDAOImpl;

public class TsetDAOImpl {

	public static void main(String[] args) {
	ApplicationDetailsDAOImpl app=new ApplicationDetailsDAOImpl();
	List <ApplicationDetails> a;
    a=app.getApplications(Enums.Dept.Geography);
    for(ApplicationDetails app1 :a) {
	   System.out.println(app1.getName());
      }
    System.out.println(a.size());
    System.out.println("---------");
	ApplicationDAOImpl appnew=new ApplicationDAOImpl();
	List<Application> a1;
	a1=appnew.getApplications();
	OfficerDAOImpl officerd=new OfficerDAOImpl();
	List<Officer> officers;
	officers=officerd.getOfficers();
	for(Officer i:officers) {
	System.out.println(i.getDepartment().toString());
	}
	}
}
