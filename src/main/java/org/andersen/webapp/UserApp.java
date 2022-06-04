package org.andersen.webapp;

import org.andersen.webapp.dao.datasource.SimpleDatasource;
import org.andersen.webapp.dao.impl.UserDaoImpl;
import org.andersen.webapp.dao.mappers.UserMapper;
import org.andersen.webapp.exception.UserServiceException;
import org.andersen.webapp.model.User;
import org.andersen.webapp.service.UserService;

/**
 * @author Alex
 * @link https://intvw.me
 */
public class UserApp {

  public static void main(String[] args) throws UserServiceException {
    UserService service = new UserService(new UserDaoImpl(new UserMapper()), new SimpleDatasource());

    service.save(new User("name","surname", 11));
    service.save(new User("name2","surname", 11));
    service.save(new User("name3","surname", 11));
    service.save(new User("name4","surname", 11));
    service.save(new User("name5","surname", 11));
//    service.delete(4L);
    System.out.println(service.find(3L));
    System.out.println("-----------");
    service.findAll()
        .forEach(System.out::println);
  }
}
