package edu.school21.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/panel")
public class AdminController {
  @GetMapping("/halls")
  public String halls() {
    return "hals";
  }

  @GetMapping("/films")
  public String films() {
    return "films";
  }

  @GetMapping("/sessions")
  public String sessions() {
    return "sessions";
  }
}
