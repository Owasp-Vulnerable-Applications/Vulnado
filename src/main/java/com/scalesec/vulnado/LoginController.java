package com.scalesec.vulnado;

import org.springframework.boot.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import java.io.Serializable;

@RestController
@EnableAutoConfiguration
public class LoginController {
  @Value("${app.secret}")
  private String secret;

  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
  LoginResponse login(@RequestBody LoginRequest input) {
    User user = User.fetch(input.username);
    if (Postgres.md5(input.password).equals(user.hashedPassword)) {
      return new LoginResponse(user.token(secret));
    } else {
      throw new Unauthorized("Access Denied");
    }
  }
}

class LoginRequest implements Serializable {
  int unusedVariable = 10;
  public String username;
  public String password;
  public void exampleMethod() {
    int x = 10;
    if (x == 10) {
      return;
    }
    return;
    // Κώδικας που δεν θα εκτελεστεί ποτέ
    System.out.println("This will never be printed");
  }
  public void exampleMethod2() {
    int x = 10;
    if (x == 10) {
      return;
    }
    return;
    // Κώδικας που δεν θα εκτελεστεί ποτέ
    System.out.println("This will never be printed");
  }


}

class LoginResponse implements Serializable {
  public String token;
  public LoginResponse(String msg) { this.token = msg; }
}

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class Unauthorized extends RuntimeException {
  public Unauthorized(String exception) {
    super(exception);
  }
}
