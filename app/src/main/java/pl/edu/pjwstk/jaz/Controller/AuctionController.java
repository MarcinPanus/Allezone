package pl.edu.pjwstk.jaz.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.Entity.AuctionEntity;
import pl.edu.pjwstk.jaz.ObjectNotFoundException;
import pl.edu.pjwstk.jaz.Repository.AuctionRepository;
import pl.edu.pjwstk.jaz.Request.AuctionRequest;
import pl.edu.pjwstk.jaz.Service.AuctionService;
import pl.edu.pjwstk.jaz.UnauthorizedException;
import pl.edu.pjwstk.jaz.User;

import java.util.List;

@RestController
public class AuctionController {

    AuctionService auctionService;
    AuctionRepository auctionRepository;

    public AuctionController(AuctionService auctionService, AuctionRepository auctionRepository) {
        this.auctionService = auctionService;
        this.auctionRepository = auctionRepository;
    }

    @GetMapping("/auctions/{title}")
    public ResponseEntity<?> getAuctionByTitle(@PathVariable("title") String title) {
        AuctionEntity auctionEntity = auctionService.getAuctionByTitle(title).orElseThrow(() -> new ObjectNotFoundException("Auction not found"));
        return ResponseEntity.ok(auctionEntity);
    }

    @PreAuthorize("hasAnyAuthority('user','admin')")
    @PostMapping("/addAuction")
    public void createAuction(@RequestBody AuctionRequest auctionRequest){
        auctionService.createAuction(auctionRequest);
    }

    @PreAuthorize("hasAnyAuthority('user','admin')")
    @PatchMapping("/updateAuction/{auctionName}")
    public void updateAuction(@RequestBody AuctionRequest auctionRequest, @PathVariable("auctionName") String auctionName) throws Exception {
        var auction = auctionRepository.findByTitle(auctionName).get();
        var currentlyLogInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(auction.getCreator().getUsername().equals(currentlyLogInUser.getUsername()))
        auctionService.updateAuction(auctionRequest, auctionName);
        else
            throw new UnauthorizedException();
    }

    @PreAuthorize("hasAnyAuthority('user','admin')")
    @GetMapping("/showAllAuctions")
    public List<AuctionEntity> showAllAuctions() {
        return auctionService.listOfAllAuctions();
    }
}