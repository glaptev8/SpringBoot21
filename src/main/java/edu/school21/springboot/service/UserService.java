package edu.school21.springboot.service;

import edu.school21.springboot.controller.AuthorizationController;
import edu.school21.springboot.entity.Role;
import edu.school21.springboot.entity.User;
import edu.school21.springboot.repository.api.UserRepository;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  public boolean save(User user) {
    LOGGER.info("try save user {}", ToStringBuilder.reflectionToString(user));
    User userFromDB = userRepository.findByUsername(user.getUsername());
    if (userFromDB != null) {
      LOGGER.info("User wasn't save, the username already exist {}", ToStringBuilder.reflectionToString(user));
      return false;
    }
    user.setRoles(Collections.singleton(Role.USER));
    user.setActive(true);
    userRepository.save(user);
    LOGGER.info("User was save {}", ToStringBuilder.reflectionToString(user));
    return true;
  }
}
