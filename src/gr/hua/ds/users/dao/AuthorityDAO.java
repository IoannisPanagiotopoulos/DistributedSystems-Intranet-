package gr.hua.ds.users.dao;

import gr.hua.ds.users.model.Authority;
import gr.hua.ds.users.model.User;

public interface AuthorityDAO {
	public Authority findAuthority(User user);
	public void insertAuthority(Authority authority);
	public void updateAuthority(Authority authority);
	public void deleteAuthority(Authority authority);
}
