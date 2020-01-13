package gr.hua.ds.users.dao;

import gr.hua.ds.users.model.UserInformation;

public interface UserInformationDAO {
	
	public void insertUserInformation(UserInformation ui);
	public void updateUserInformation(UserInformation ui);
	public void deleteUserInformation(UserInformation ui);

}