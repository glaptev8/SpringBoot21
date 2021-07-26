package edu.school21.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherControllers {
  @GetMapping("/session/search")
  public String searchSession() {
    return "hals";
  }

  @GetMapping("/films/{id}/chat")
  public String filmChat() {
    return "hals";
  }

  @GetMapping("/films/{id}/chat/messages")
  public String chatMessages() {
    return "hals";
  }
}
