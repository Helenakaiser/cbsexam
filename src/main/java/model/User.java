package model;

import utils.Hashing;

public class User {

  public int id;
  public String firstname;
  public String lastname;
  public String email;
  private String password;
  private static long createdTime;
  private String token;

  //Helenas notes: A user has to contain all of these parameters:
  public User(int id, String firstname, String lastname, String password, String email, long createdTime) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.password = Hashing.sha(password);
    this.email = email;
    this.createdTime = createdTime;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public static long getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(long createdTime) {
    this.createdTime = createdTime;
  }

  //Helenas notes: (Related to the "delete users" to-do from the UserEndpoint class.)
    public String getToken() {
        return token;
    }

  public void setToken(String token) {
    this.token = token;
  }
}
