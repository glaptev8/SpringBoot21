package edu.school21.springboot.service;

import edu.school21.springboot.entity.Role;
import edu.school21.springboot.entity.User;
import edu.school21.springboot.repository.UserRepository;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired
  private MailSender mailSender;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  public boolean save(User user) {
    LOGGER.info("try save user {}", ToStringBuilder.reflectionToString(user));
    User userFromDB = userRepository.findByUsername(user.getUsername());
    if (userFromDB != null) {
      LOGGER.info("User wasn't save, the username already exist {}", ToStringBuilder.reflectionToString(user));
      return false;
    }
    user.setRoles(Collections.singleton(Role.USER));
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setActive(false);
    String uuid = UUID.randomUUID().toString();
    user.setActivateCode(uuid);
    mailSender
      .send(user.getEmail(),
        "activateCode",
        String.format("Dear %s, visit this link for activate your account http://localhost:8080/activate/%s",
          user.getUsername(),
          uuid));
    userRepository.save(user);
    LOGGER.info("User was save {}", ToStringBuilder.reflectionToString(user));
    return true;
  }

  public boolean activate(String code) {
    User byActivateCode = userRepository.findByActivateCode(code);
    if (byActivateCode == null) {
      return false;
    }
    byActivateCode.setActive(true);
    byActivateCode.setActivateCode(null);
    byActivateCode.setActive(true);
    userRepository.save(byActivateCode);
    return true;
  }
}
