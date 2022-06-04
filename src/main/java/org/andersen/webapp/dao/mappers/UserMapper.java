package org.andersen.webapp.dao.mappers;

import org.andersen.webapp.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.andersen.webapp.model.User.USER_AGE;
import static org.andersen.webapp.model.User.USER_ID;
import static org.andersen.webapp.model.User.USER_NAME;
import static org.andersen.webapp.model.User.USER_SURNAME;

public class UserMapper implements Mapper<User> {

  @Override
  public User apply(ResultSet resultSet) throws SQLException {
    return new User(resultSet.getLong(USER_ID), resultSet.getString(USER_NAME), resultSet.getString(USER_SURNAME), resultSet.getInt(USER_AGE));
  }
}
