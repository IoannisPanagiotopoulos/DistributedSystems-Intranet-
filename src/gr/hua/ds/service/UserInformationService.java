package gr.hua.ds.service;

import gr.hua.ds.users.model.UserInformation;

public interface UserInformationService {
	public void insertUserInformation(UserInformation ui);
	public void updateUserInformation(UserInformation ui);
	public void deleteUserInformation(UserInformation ui);
}
