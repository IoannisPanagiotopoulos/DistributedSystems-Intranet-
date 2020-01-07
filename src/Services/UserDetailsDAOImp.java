package Services;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import DataBase.User;
import iDAO.UserDetailsDAO;
import iDAO.*;
@Repository
public class UserDetailsDAOImp implements UserDetailsDAO {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public User findUserByUsername(String username) {
    return sessionFactory.getCurrentSession().get(User.class, username);
  }
}
