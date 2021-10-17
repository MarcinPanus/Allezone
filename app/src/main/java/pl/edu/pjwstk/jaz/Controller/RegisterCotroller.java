package pl.edu.pjwstk.jaz.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.Entity.AuthorityEntity;
import pl.edu.pjwstk.jaz.Entity.UserEntity;
import pl.edu.pjwstk.jaz.Request.RegisterRequest;
import pl.edu.pjwstk.jaz.Service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RegisterCotroller {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterCotroller(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest registerRequest) {

        if (userService.findByUsername(registerRequest.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(registerRequest.getUsername());
            userEntity.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));

            List<AuthorityEntity> authorityList = new ArrayList<>();
            AuthorityEntity authorityEntity = new AuthorityEntity();

            if(userService.findAll().isEmpty())
                authorityEntity.setAuthority("admin");
            else
                authorityEntity.setAuthority("user");


            authorityList.add(authorityEntity);
            userEntity.setAuthorityEntity(authorityList);
            userService.addUser(userEntity);
            return  ResponseEntity.ok().build();
        }
    }
}
