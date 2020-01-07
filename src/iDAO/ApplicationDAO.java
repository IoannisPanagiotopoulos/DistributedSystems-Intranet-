package iDAO;

import java.util.List;

import DataBase.Application;
import DataBase.Enums;
import DataBase.Enums.Dept;

public interface ApplicationDAO {
	public List<Application> getApplications();
	public Application getApplication(Dept department);
	public void updateApplication(Application app);

}
