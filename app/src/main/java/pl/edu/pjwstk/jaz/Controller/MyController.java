package pl.edu.pjwstk.jaz.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.Entity.UserEntity;
import pl.edu.pjwstk.jaz.Service.UserService;

import java.util.List;

@RestController
public class MyController {

    UserService userService;

    public MyController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/springAdmin")
    public void springAdmin() {
    }

    @PreAuthorize("hasAnyAuthority('user','admin')")
    @GetMapping("/springUser")
    public void springUser() {
    }

//  ?????????
    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/auth0/filtrAdmin")
    public void filtrAdmin() {
    }

    @PreAuthorize("hasAnyAuthority('user','admin')")
    @GetMapping("/auth0/filtrUser")
    public void filtrUser() {
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/springAdmin/showAll")
    public List<UserEntity> getAllUsers() {
        return userService.findAll();
    }

    @PreAuthorize("hasAnyAuthority('user')")
    @GetMapping("/springUser/deleteAll")
    public void deleteAll(){
        userService.deleteUsers();
    }
}
