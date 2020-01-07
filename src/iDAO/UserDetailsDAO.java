package iDAO;

import DataBase.User;

public interface UserDetailsDAO {
  User findUserByUsername(String username);
}