package org.andersen.webapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class User extends LongEntity {

  public static final String USER_ID = "user_id";
  public static final String USER_NAME = "user_name";
  public static final String USER_SURNAME = "user_surname";
  public static final String USER_AGE = "user_age";

  private String name;
  private String surname;
  private Integer age;

  public User(Long id, String name, String surname, Integer age) {
    super(id);
    this.name = name;
    this.surname = surname;
    this.age = age;
  }

  public User(String name, String surname, Integer age) {
    this(null, name, surname, age);
  }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", age=" + age + '}';
  }
}
