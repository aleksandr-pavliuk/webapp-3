package org.andersen.webapp.servlet;

import org.andersen.webapp.dao.datasource.SimpleDatasource;
import org.andersen.webapp.dao.impl.UserDaoImpl;
import org.andersen.webapp.dao.mappers.UserMapper;
import org.andersen.webapp.exception.UserServiceException;
import org.andersen.webapp.model.User;
import org.andersen.webapp.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddServlet extends HttpServlet {

  private UserService service;

  @Override
  public void init() throws ServletException {
    super.init();
    service = new UserService(new UserDaoImpl(new UserMapper()), new SimpleDatasource());
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
    requestDispatcher.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      service.save(
          new User(req.getParameter("name"), req.getParameter("surname"), Integer.parseInt(req.getParameter("number"))));
    } catch (UserServiceException e) {
      throw new IllegalStateException("User wasn't save", e);
    }
  }
}
