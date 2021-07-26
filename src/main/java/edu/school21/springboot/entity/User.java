package edu.school21.springboot.entity;

import edu.school21.springboot.validation.annotation.PasswordMatches;
import edu.school21.springboot.validation.annotation.ValidPassword;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@PasswordMatches(message = "{password.not.matches}")
@Entity(name = "app_user")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @Column(
    name = "username",
    nullable = false,
    unique = true
  )
  @NotNull(message = "{not.null}")
  @NotEmpty(message = "{not.empty}")
  private String username;

  @Column(
    name = "phone",
    nullable = false
  )

  @Pattern(regexp = "^(\\+7)*\\(?[489][0-9]{2}\\)[0-9]{7}$", message = "{phone.not.valid}")
  private String phone;

  @Column(name = "active")
  private boolean active;

  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  private Set<Role> roles;

  @Email
  @NotEmpty(message = "{not.empty}")
  @NotNull(message = "{not.null}")
  @Column(
    name = "email",
    nullable = false
  )
  private String email;

  @Column(
    name = "password",
    nullable = false
  )
  @ValidPassword(message = "{password.not.valid}")
  private String password;

  @NotEmpty(message = "{not.empty}")
  @NotNull(message = "{not.null}")
  @Transient
  private String matchingPassword;

  @Column(name = "activate_code")
  private String activateCode;

  public User() {
  }

  public User(Long id, String username, String phone, @Email String email, String password) {
    this.id = id;
    this.username = username;
    this.phone = phone;
    this.email = email;
    this.password = password;
  }

  public User(Long id, String username, String phone, @Email String email, String password, String matchingPassword) {
    this.id = id;
    this.username = username;
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

  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return isActive();
  }

  @Override
  public boolean isAccountNonLocked() {
    return isActive();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isActive();
  }

  @Override
  public boolean isEnabled() {
    return isActive();
  }

  public void setUsername(String name) {
    this.username = name;
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

  public String getMatchingPassword() {
    return matchingPassword;
  }

  public void setMatchingPassword(String matchingPassword) {
    this.matchingPassword = matchingPassword;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toSet());
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public String getActivateCode() {
    return activateCode;
  }

  public void setActivateCode(String activateCode) {
    this.activateCode = activateCode;
  }
}
