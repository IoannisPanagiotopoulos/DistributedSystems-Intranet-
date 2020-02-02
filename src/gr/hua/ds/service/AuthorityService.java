package gr.hua.ds.service;

import gr.hua.ds.users.model.Authority;
import gr.hua.ds.users.model.User;

public interface AuthorityService {
	public Authority findAuthority(User user);
	
	public void insertAuthority(Authority authority);
	
	public void updateAuthority(Authority authority);
	
	public void deleteAuthority(Authority authority);
}
