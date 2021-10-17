package pl.edu.pjwstk.jaz.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.Entity.ParameterEntity;

@Repository
public interface ParameterRepository extends JpaRepository<ParameterEntity, Long> {
    void deleteById(Long id);

}
