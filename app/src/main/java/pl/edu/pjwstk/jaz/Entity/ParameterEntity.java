package pl.edu.pjwstk.jaz.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parameter")
public class ParameterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "key")
    private String key;

    @OneToMany(mappedBy = "parameter", cascade = CascadeType.ALL)
    List<AuctionParameterEntity> auctionParameters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<AuctionParameterEntity> getAuctionParameters() {
        return auctionParameters;
    }

    public void setAuctionParameters(List<AuctionParameterEntity> auctionParameters) {
        this.auctionParameters = auctionParameters;
    }

}
