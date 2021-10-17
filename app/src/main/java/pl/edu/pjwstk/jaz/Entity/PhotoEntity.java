package pl.edu.pjwstk.jaz.Entity;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "link")
    private String link;

    @Column(name = "photonumber")
    private int photonumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    private AuctionEntity auction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getPhotonumber() {
        return photonumber;
    }

    public void setPhotonumber(int photonumber) {
        this.photonumber = photonumber;
    }

    public AuctionEntity getAuction() {
        return auction;
    }

    public void setAuction(AuctionEntity auction) {
        this.auction = auction;
    }
}
