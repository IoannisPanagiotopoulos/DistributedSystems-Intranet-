package iDAO;

import DataBase.Authority;
import DataBase.User;

public interface AuthorityDAO {
	public Authority findAuthority(User user);
	public void insertAuthority(User user);
	public void updateAuthority(User user);
	public void deleteAuthority(User user);
}
