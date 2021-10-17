package pl.edu.pjwstk.jaz.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.Entity.AuctionParameterEntity;

import java.util.Optional;

@Repository
public interface AuctionParameterRepository extends JpaRepository<AuctionParameterEntity, Long> {
    Optional<AuctionParameterEntity> deleteByAuctionId(Long auctionId);
    Optional<AuctionParameterEntity> findByAuctionId(Long auctionId);

}
