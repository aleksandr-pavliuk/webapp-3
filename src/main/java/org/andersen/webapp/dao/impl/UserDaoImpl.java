package org.andersen.webapp.dao.impl;

import lombok.RequiredArgsConstructor;
import org.andersen.webapp.dao.AbstractCrudDao;
import org.andersen.webapp.dao.UserDao;
import org.andersen.webapp.dao.mappers.UserMapper;
import org.andersen.webapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserDaoImpl extends AbstractCrudDao<User, Long> implements UserDao {

  public static final String SELECT_ONE = "SELECT * FROM users where user_id = ?";
  public static final String SELECT_ALL = "SELECT * FROM users";
  public static final String INSERT_ONE = "INSERT INTO users (user_name, user_surname, user_age) values (?,?,?)";
  public static final String UPDATE =
      "UPDATE users SET user_name = ?, user_surname = ?, user_age = ? where user_id = ?";
  public static final String DELETE_ONE = "DELETE FROM users WHERE user_id = ?";

  private final UserMapper mapper;

  @Override
  public User create(Connection connection, User entity) throws SQLException {
    try (PreparedStatement ps = connection.prepareStatement(INSERT_ONE, Statement.RETURN_GENERATED_KEYS)) {
      ps.setString(1, entity.getName());
      ps.setString(2, entity.getSurname());
      ps.setInt(3, entity.getAge());
      if (ps.executeUpdate() != 1) {
        throw new SQLException("Unable to create user " + entity);
      }
      try (ResultSet rs = ps.getGeneratedKeys()) {
        if (!rs.next()) {
          throw new SQLException("Unable to retrieve id");
        }
        Long id = rs.getLong(1);
        return new User(id, entity.getName(), entity.getSurname(), entity.getAge());
      }
    }
  }

  @Override
  protected User update(Connection connection, User entity) throws SQLException {
    try (PreparedStatement ps = connection.prepareStatement(UPDATE)) {
      ps.setString(1, entity.getName());
      ps.setString(2, entity.getSurname());
      ps.setInt(3, entity.getAge());
      ps.setLong(1, entity.getId());
      if (ps.executeUpdate() != 1) {
        throw new SQLException("Unable to update user " + entity);
      }
      return new User(entity.getId(), entity.getName(), entity.getSurname(), entity.getAge());
    }
  }

  @Override
  public Optional<User> findById(Connection connection, Long id) throws SQLException {
    try (PreparedStatement ps = connection.prepareStatement(SELECT_ONE)) {
      ps.setLong(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return Optional.of(mapper.apply(rs));
        } else {
          return Optional.empty();
        }
      }
    }
  }

  @Override
  public List<User> findAll(Connection connection) throws SQLException {
    try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
      try (ResultSet rs = statement.executeQuery()) {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
          users.add(mapper.apply(rs));
        }
        return users;
      }
    }
  }

  @Override
  public void deleteById(Connection connection, Long id) throws SQLException {
    try (PreparedStatement ps = connection.prepareStatement(DELETE_ONE)) {
      ps.setLong(1, id);
      if (ps.executeUpdate() != 1) {
        throw new SQLException("Unable to delete user (id = " + id + ")");
      }
    }
  }
}
