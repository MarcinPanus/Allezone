package pl.edu.pjwstk.jaz.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.Entity.SectionEntity;

import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<SectionEntity, Long> {
    Optional<SectionEntity> findByName(String name);
    Optional<SectionEntity> findById(Long id);


}
