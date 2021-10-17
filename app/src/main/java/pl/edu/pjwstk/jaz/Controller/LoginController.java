package pl.edu.pjwstk.jaz.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.Service.AuthenticationService;
import pl.edu.pjwstk.jaz.Entity.UserEntity;
import pl.edu.pjwstk.jaz.Request.LoginRequest;
import pl.edu.pjwstk.jaz.Service.UserService;
import pl.edu.pjwstk.jaz.UnauthorizedException;

import java.util.Optional;

@RestController
public class LoginController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    public LoginController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {

        Optional<UserEntity> userEntity = userService.findByUsername(loginRequest.getUsername());

        var isLogged = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (!isLogged) {
            throw new UnauthorizedException();
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
        }
    }


}

