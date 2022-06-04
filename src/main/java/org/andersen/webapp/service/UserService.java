package org.andersen.webapp.service;

import lombok.RequiredArgsConstructor;
import org.andersen.webapp.dao.datasource.Datasource;
import org.andersen.webapp.dao.impl.UserDaoImpl;
import org.andersen.webapp.exception.UserServiceException;
import org.andersen.webapp.model.User;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class UserService {

  private final UserDaoImpl userDao;
  private final Datasource datasource;

  public User save(User user) throws UserServiceException {
    User savedUser;
    try {
      savedUser = userDao.save(datasource.getConnection(), user);
    } catch (SQLException e) {
      throw new UserServiceException("User wasn't save", e);
    }
    return savedUser;
  }

  public void delete(Long id) throws UserServiceException {
    try {
      userDao.deleteById(datasource.getConnection(), id);
    } catch (SQLException e) {
      throw new UserServiceException("User wasn't delete", e);
    }
  }

  public User find(Long id) {
    try {
      return userDao.findById(datasource.getConnection(), id)
          .get();
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<User> findAll() {
    try {
      return userDao.findAll(datasource.getConnection());
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

}
