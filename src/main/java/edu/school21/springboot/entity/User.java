package edu.school21.springboot.entity;

import edu.school21.springboot.validation.annotation.PasswordMatches;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@PasswordMatches
@Entity(name = "app_user")
public class User {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @Column(
    name = "name",
    nullable = false
  )
  @NotNull
  @NotEmpty
  private String name;

  @Column(
    name = "phone",
    nullable = false
  )

  @Pattern(regexp = "^(\\+7)*\\(?[489][0-9]{2}\\)[0-9]{7}$", message = "phone is not valid")
  private String phone;

  @Column(
    name = "email",
    nullable = false
  )

  @Email
  private String email;

  @Column(
    name = "password",
    nullable = false
  )
  private String password;

  private String matchingPassword;

  public User() {
  }

  public User(Long id, String name, String phone, @Email String email, String password) {
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.password = password;
  }

  public User(Long id, String name, String phone, @Email String email, String password, String matchingPassword) {
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.password = password;
    this.matchingPassword = matchingPassword;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Transient
  public String getMatchingPassword() {
    return matchingPassword;
  }

  public void setMatchingPassword(String matchingPassword) {
    this.matchingPassword = matchingPassword;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
