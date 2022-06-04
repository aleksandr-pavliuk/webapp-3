package org.andersen.webapp.dao.datasource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleDatasource implements Datasource {

  public final static String DRIVER_CLASS = "org.postgresql.Driver";
  public final static String JDBC_URL = "jdbc:postgresql://172.24.0.4:5432/postgres";
  public final static String USERNAME = "postgres";
  public final static String PASSWORD = "postgres";

  private final Driver driver;

  private final String jdbcUrl;
  private final String username;
  private final String password;

  public SimpleDatasource() {
    String className = DRIVER_CLASS;
    this.jdbcUrl = JDBC_URL;
    this.username = USERNAME;
    this.password = PASSWORD;

    try {
      @SuppressWarnings("unchecked")
      Class<Driver> clazz = (Class<Driver>) Class.forName(className);
      driver = clazz.newInstance();
      DriverManager.registerDriver(driver);
    } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }

  @Override
  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(jdbcUrl, username, password);
  }

  @Override
  public void close() throws IOException {

    try {
      DriverManager.deregisterDriver(driver);
    } catch (SQLException e) {
      throw new IllegalStateException(e);
    }
  }
}
