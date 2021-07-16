package edu.school21.springboot.controller;

import edu.school21.springboot.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;


@Controller
public class AuthorizationController {

  @GetMapping("/signIn")
  public String signInGet() {
    return "signIn";
  }

  @PostMapping("/signIn")
  public String signInPost(@Valid User user,
                           BindingResult bindingResult,
                           Model model) {
    return "home";
  }
}
