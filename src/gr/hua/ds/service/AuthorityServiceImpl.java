package gr.hua.ds.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.ds.users.dao.AuthorityDAO;
import gr.hua.ds.users.model.Authority;
import gr.hua.ds.users.model.User;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityDAO authorityDao;
	
	@Transactional
	@Override
	public Authority findAuthority(User user) {
		return authorityDao.findAuthority(user);
	}

	@Transactional
	@Override
	public void insertAuthority(Authority authority) {
		authorityDao.insertAuthority(authority);
	}

	@Transactional
	@Override
	public void updateAuthority(Authority authority) {
		authorityDao.updateAuthority(authority);
	}

	@Transactional
	@Override
	public void deleteAuthority(Authority authority) {
		authorityDao.deleteAuthority(authority);
	}

}
