package pl.edu.pjwstk.jaz.Service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.Entity.AuthorityEntity;
import pl.edu.pjwstk.jaz.Entity.UserEntity;
import pl.edu.pjwstk.jaz.Repository.AuthorityRepository;
import pl.edu.pjwstk.jaz.Repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }


    public void addUser(UserEntity userEntity) {
        userRepository.save(userEntity);
        for (AuthorityEntity authorityEntity : userEntity.getAuthorityEntity()) {
            authorityEntity.setUserEntity(userEntity);
            authorityRepository.save(authorityEntity);
        }
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public void deleteUsers(){
        userRepository.deleteAll();
    }

}
