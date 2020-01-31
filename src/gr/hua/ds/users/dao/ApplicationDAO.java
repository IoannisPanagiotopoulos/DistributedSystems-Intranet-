package gr.hua.ds.users.dao;

import java.util.List;

import gr.hua.ds.users.model.Application;
import gr.hua.ds.users.model.Enums.Dept;

public interface ApplicationDAO {

	public List<Application> getApplications(Dept department);
	public Application getApplicationActiveByUsername(String username);
	public void updateApplication(Application app);
	public void deleteApplication(Application app);
	public List<Application> getApplicationsByDepartment(Dept department);
	List<Application> getActivatedApplicationsByDept(Dept department);;
	public List<Application> getActivatedApplications();
	public void insertApplication(Application app);
}
