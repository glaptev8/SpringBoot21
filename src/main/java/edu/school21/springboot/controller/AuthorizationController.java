package edu.school21.springboot.controller;

import edu.school21.springboot.entity.User;
import edu.school21.springboot.service.UserService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorizationController {

  @Autowired
  private UserService userService;

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationController.class);


  @GetMapping("/home")
  public String main() {
    return "home";
  }

  @GetMapping("/registration")
  public String registrationGet() {
    return "registration";
  }

  @GetMapping("/signIn")
  public String signInGet() {
    return "signIn";
  }

  @PostMapping("/registration")
  public String registrationPost(User user,
                                 Errors errors,
                                 Model model) {
    if (CollectionUtils.isEmpty(errors.getAllErrors())) {
      userService.save(user);
      return "home";
    } else {
      LOGGER.info("not valid form for user {}", ToStringBuilder.reflectionToString(user));
      model.addAttribute("errors", errors);
      return "signIn";
    }
  }
}
