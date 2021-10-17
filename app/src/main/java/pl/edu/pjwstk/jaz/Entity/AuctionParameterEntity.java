package pl.edu.pjwstk.jaz.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "auction_parameter")
public class AuctionParameterEntity implements Serializable {

    @EmbeddedId
    private AuctionParameterID auctionParameterID = new AuctionParameterID();

    @ManyToOne
    @MapsId("auction_id")
    @JoinColumn(name = "auction_id")
    private AuctionEntity auction;

    @ManyToOne
    @MapsId("parameter_id")
    @JoinColumn(name = "parameter_id")
    private ParameterEntity parameter;

    @Column(name = "value")
    private String value;

    public AuctionParameterID getAuctionParameterID() {
        return auctionParameterID;
    }

    public void setAuctionParameterID(AuctionParameterID auctionParameterID) {
        this.auctionParameterID = auctionParameterID;
    }

    public AuctionEntity getAuction() {
        return auction;
    }

    public void setAuction(AuctionEntity auction) {
        this.auction = auction;
    }

    public ParameterEntity getParameter() {
        return parameter;
    }

    public void setParameter(ParameterEntity parameter) {
        this.parameter = parameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
