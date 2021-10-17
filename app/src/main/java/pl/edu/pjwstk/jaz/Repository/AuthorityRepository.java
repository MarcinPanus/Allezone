package pl.edu.pjwstk.jaz.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.Entity.AuthorityEntity;

@Repository
public interface AuthorityRepository extends JpaRepository <AuthorityEntity, Long> {
}
