package org.andersen.webapp.exception;

import java.sql.SQLException;

public class UserServiceException extends Throwable {

  public UserServiceException(String s, SQLException e) {}
}
