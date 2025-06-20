package net.icestone.springsecurity.setup;

import net.icestone.springsecurity.service.UserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CreateDefaultAdminListener {

  private final UserService userService;

  public CreateDefaultAdminListener(UserService userService) {
    this.userService = userService;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void createDefaultAdmin() {

    userService.createDefaultAdminIfNotExist();
  }
}
