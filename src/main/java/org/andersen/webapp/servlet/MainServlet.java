package org.andersen.webapp.servlet;


import org.andersen.webapp.dao.datasource.SimpleDatasource;
import org.andersen.webapp.dao.impl.UserDaoImpl;
import org.andersen.webapp.dao.mappers.UserMapper;
import org.andersen.webapp.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "mainServlet", value = "/main")
public class MainServlet extends HttpServlet {
    private UserService service;

    private String message;

    public void init() {
        service = new UserService(new UserDaoImpl(new UserMapper()), new SimpleDatasource());
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        // Hello
//        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> Hello " + command +  "</h1>");
        out.println("</body></html>");
        if(command.equals("add")) response.sendRedirect("/webapp_war_exploded/adding");
    }

    public void destroy() {
    }

}
