package com.alexcode.eatgo.interfaces;

import com.alexcode.eatgo.application.UserService;
import com.alexcode.eatgo.domain.User;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class SessionController {

  @Autowired
  private UserService userService;

  @PostMapping("/session")
  public ResponseEntity<SessionResponseDto> login(
      @RequestBody SessionRequestDto resource) throws URISyntaxException {

    String email = resource.getEmail();
    String password = resource.getPassword();
    User user = userService.authenticate(email, password);
    String accessToken = user.getAccessToken();

    SessionResponseDto sessionResponseDto =
        SessionResponseDto.builder().accessToken(accessToken).build();

    URI location = new URI("/session");

    return ResponseEntity.created(location).body(sessionResponseDto);
  }
}
