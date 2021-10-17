package pl.edu.pjwstk.jaz.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "auction")
public class AuctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private UserEntity creator;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private Set<PhotoEntity> photos;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private Set<AuctionParameterEntity> auctionParameters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public Set<PhotoEntity> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<PhotoEntity> photos) {
        this.photos = photos;
    }

    public Set<AuctionParameterEntity> getAuctionParameters() {
        return auctionParameters;
    }

    public void setAuctionParameters(Set<AuctionParameterEntity> auctionParameters) {
        this.auctionParameters = auctionParameters;
    }
}
