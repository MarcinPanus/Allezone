package pl.edu.pjwstk.jaz.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.jaz.Entity.PhotoEntity;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {
    Optional<PhotoEntity> deleteByAuctionId(Long auctionId);

}
