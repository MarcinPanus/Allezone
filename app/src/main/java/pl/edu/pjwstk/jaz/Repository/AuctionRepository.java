package pl.edu.pjwstk.jaz.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.Entity.AuctionEntity;

import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<AuctionEntity, Long> {
    Optional<AuctionEntity> findByTitle(String title);
    Optional<AuctionEntity> findById(Long id);

}
