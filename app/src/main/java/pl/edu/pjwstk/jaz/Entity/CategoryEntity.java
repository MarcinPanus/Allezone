package pl.edu.pjwstk.jaz.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private SectionEntity section;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<AuctionEntity> auctions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SectionEntity getSection() {
        return section;
    }

    public void setSection(SectionEntity section) {
        this.section = section;
    }

    public List<AuctionEntity> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<AuctionEntity> auctions) {
        this.auctions = auctions;
    }
}
