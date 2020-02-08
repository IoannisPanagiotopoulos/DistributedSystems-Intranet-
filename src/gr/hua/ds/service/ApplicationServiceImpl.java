package gr.hua.ds.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.ds.users.dao.ApplicationDAO;
import gr.hua.ds.users.model.Application;
import gr.hua.ds.users.model.Enums.Dept;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationDAO applicationDao;
	
	@Transactional
	@Override
	public List<Application> getApplications(Dept department) {
		return applicationDao.getApplications(department);
	}

	@Transactional
	@Override
	public Application getApplicationActiveByUsername(String username) {
		return applicationDao.getApplicationActiveByUsername(username);
	}

	@Transactional
	@Override
	public void updateApplication(Application app) {
		applicationDao.updateApplication(app);
	}

	@Transactional
	@Override
	public void deleteApplication(Application app) {
		applicationDao.deleteApplication(app);
	}

	@Transactional
	@Override
	public List<Application> getApplicationsByDepartment(Dept department) {
		return applicationDao.getApplicationsByDepartment(department);
	}

	@Transactional
	@Override
	public List<Application> getActivatedApplicationsByDept(Dept department) {
		return applicationDao.getActivatedApplicationsByDept(department);
	}

	@Transactional
	@Override
	public List<Application> getActivatedApplications() {
		return applicationDao.getActivatedApplications();
	}

	@Transactional
	@Override
	public void insertApplication(Application app) {
		applicationDao.insertApplication(app);
	}

	@Transactional
	@Override
	public List<Application> getApplications() {
		return applicationDao.getApplications();
	}

}
