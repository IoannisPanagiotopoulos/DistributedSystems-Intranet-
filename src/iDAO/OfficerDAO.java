package iDAO;

import java.util.List;

import DataBase.Officer;

public interface OfficerDAO {
	public Officer getOfficer(String username, String password);
	public Officer getOfficer(int id);
	public List<Officer> getOfficers();
	public void removeOfficer(Officer o);
	public void updateOfficer(Officer o);
	public void insertOfficer(Officer o);
	public List<Officer> getallOfficers();
}
