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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthorizationController {

  @Autowired
  private UserService userService;

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationController.class);


  @GetMapping("/profile")
  public String main() {
    return "profile";
  }

  @GetMapping("/signUp")
  public String signUpGet() {
    return "signUp";
  }

  @GetMapping("/signIn")
  public String signInGet() {
    return "signIn";
  }

  @PostMapping("/signUp")
  public String signUpPost(@Valid User user,
                                 Errors errors,
                                 Model model) {
    if (CollectionUtils.isEmpty(errors.getAllErrors())) {
      userService.save(user);
      return "signIn";
    } else {
      LOGGER.info("not valid form for user {}", ToStringBuilder.reflectionToString(user));
      model.addAttribute("errors", errors);
      return "signUp";
    }
  }

  @GetMapping("/activate/{code}")
  public String activateAccount(@PathVariable String code) {
    userService.activate(code);
    return "redirect:/login";
  }
}
