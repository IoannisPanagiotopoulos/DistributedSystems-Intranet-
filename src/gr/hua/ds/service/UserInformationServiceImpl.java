package gr.hua.ds.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.ds.users.dao.UserInformationDAO;
import gr.hua.ds.users.model.UserInformation;

@Service
public class UserInformationServiceImpl implements UserInformationService {

	@Autowired
	private UserInformationDAO userInformationDao;
	
	@Transactional
	@Override
	public void insertUserInformation(UserInformation ui) {
		userInformationDao.insertUserInformation(ui);
	}

	@Transactional
	@Override
	public void updateUserInformation(UserInformation ui) {
		userInformationDao.updateUserInformation(ui);
	}

	@Transactional
	@Override
	public void deleteUserInformation(UserInformation ui) {
		userInformationDao.deleteUserInformation(ui);
	}

}
