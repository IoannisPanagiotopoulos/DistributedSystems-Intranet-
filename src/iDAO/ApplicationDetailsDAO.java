package iDAO;

import java.util.List;

import DataBase.ApplicationDetails;
import DataBase.Enums;
import DataBase.Enums.Dept;


public interface ApplicationDetailsDAO {

	public List<ApplicationDetails> getApplications(Dept department);
	public ApplicationDetails getApplication(int userId);
	public void updateApplication(ApplicationDetails app);
	public void deleteApplication(ApplicationDetails app);
	public List<ApplicationDetails> getApplicationsByDepartment(Dept department);
	List<ApplicationDetails> getActivatedApplicationsByDept(Dept department);;
	public List<ApplicationDetails> getActivatedApplications();
}
