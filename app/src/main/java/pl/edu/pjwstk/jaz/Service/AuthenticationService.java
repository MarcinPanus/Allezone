package pl.edu.pjwstk.jaz.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.jaz.AppAuthentication;
import pl.edu.pjwstk.jaz.Entity.AuthorityEntity;
import pl.edu.pjwstk.jaz.Entity.UserEntity;
import pl.edu.pjwstk.jaz.Repository.UserRepository;
import pl.edu.pjwstk.jaz.User;
import pl.edu.pjwstk.jaz.UserSession;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuthenticationService {

    public final UserSession userSession;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserSession userSession, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userSession = userSession;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean login(String username, String password) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
            if(userEntity.isPresent()){
                if(passwordEncoder.matches(password, userEntity.get().getPasswordHash())){
                    userSession.logIn();
                    User user = new User(userEntity.get().getUsername(),userEntity.get().getPasswordHash(), userEntity.get().getAuthorityEntity().stream().map(AuthorityEntity::getAuthority).collect(Collectors.toSet()));
                    SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(user));
                    return true;
                }
            }
            return false;
    }
}
