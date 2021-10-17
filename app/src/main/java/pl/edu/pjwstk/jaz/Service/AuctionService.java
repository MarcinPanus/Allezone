package pl.edu.pjwstk.jaz.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.Entity.AuctionEntity;
import pl.edu.pjwstk.jaz.Entity.AuctionParameterEntity;
import pl.edu.pjwstk.jaz.Entity.ParameterEntity;
import pl.edu.pjwstk.jaz.Entity.PhotoEntity;
import pl.edu.pjwstk.jaz.Repository.*;
import pl.edu.pjwstk.jaz.Request.AuctionRequest;
import pl.edu.pjwstk.jaz.Request.ParameterRequest;
import pl.edu.pjwstk.jaz.Request.PhotoRequest;
import pl.edu.pjwstk.jaz.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
public class AuctionService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;
    private final AuctionRepository auctionRepository;
    private  final PhotoRepository photoRepository;
    private  final AuctionParameterRepository auctionParameterRepository;
    private final ParameterRepository parameterRepository;

    public AuctionService(UserRepository userRepository, CategoryRepository categoryRepository, EntityManager entityManager, AuctionRepository auctionRepository, PhotoRepository photoRepository, AuctionParameterRepository auctionParameterRepository, ParameterRepository parameterRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
        this.auctionRepository = auctionRepository;
        this.photoRepository = photoRepository;
        this.auctionParameterRepository = auctionParameterRepository;
        this.parameterRepository = parameterRepository;

    }

    public Optional<AuctionEntity> getAuctionByTitle(String title) {
        return auctionRepository.findByTitle(title);
    }

    public void createAuction(AuctionRequest auctionRequest) {

        var auction = new AuctionEntity();
        var currentlyLogInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        auction.setTitle(auctionRequest.getTitle());
        auction.setDescription(auctionRequest.getDescription());
        auction.setPrice(auctionRequest.getPrice());
        auction.setCategory(categoryRepository.findByName(auctionRequest.getNameOfCategory()).get());
        auction.setCreator(userRepository.findByUsername(currentlyLogInUser.getUsername()).get());
        auction.setAuctionParameters(setAuctionParameters(auctionRequest.getParameters(), auction));
        auction.setPhotos(setAuctionPhotos(auctionRequest.getPhotos(), auction));
        entityManager.persist(auction);
    }


    public void updateAuction(AuctionRequest auctionRequest, String auctionName) throws Exception {
        var auction = auctionRepository.findByTitle(auctionName).get();
        var auctionId = auction.getId();

            if (auctionRequest.getTitle() != null) {
                auction.setTitle(auctionRequest.getTitle());
            }
            if (auctionRequest.getDescription() != null) {
                auction.setDescription(auctionRequest.getDescription());
            }
            if (auctionRequest.getPrice() != 0) {
                auction.setPrice(auctionRequest.getPrice());
            }
            if (auctionRequest.getNameOfCategory() != null) {
                try {
                    if ((categoryRepository.findByName(auctionRequest.getNameOfCategory())).isPresent())
                        auction.setCategory(categoryRepository.findByName(auctionRequest.getNameOfCategory()).get());
                } catch (RuntimeException exception) {
                    throw new Exception("This category does no exist");
                }
            }
            if (auctionRequest.getParameters() != null) {
                var acutionParameter = auctionParameterRepository.findByAuctionId(auctionId).get();
                var id = acutionParameter.getParameter().getId();
                parameterRepository.deleteById(id);
                auctionParameterRepository.deleteByAuctionId(auctionId);
                auction.setAuctionParameters(setAuctionParameters(auctionRequest.getParameters(), auction));
                entityManager.persist(auction);
            }
            if (auctionRequest.getPhotos() != null) {
                photoRepository.deleteByAuctionId(auctionId);
                auction.setPhotos(setAuctionPhotos(auctionRequest.getPhotos(), auction));
                entityManager.persist(auction);
            }
            entityManager.merge(auction);
    }

    public Set<AuctionParameterEntity> setAuctionParameters(List<ParameterRequest> parameters, AuctionEntity auction) {

        Set<AuctionParameterEntity> auctionParameters = new HashSet<>();

        for (ParameterRequest parameterRequest : parameters) {
            var parameter = new ParameterEntity();
            parameter.setKey(parameterRequest.getKey());

            var auctionParameter = new AuctionParameterEntity();
            auctionParameter.setAuction(auction);
            auctionParameter.setParameter(parameter);
            auctionParameter.setValue(parameterRequest.getValue());
            auctionParameters.add(auctionParameter);
        }
        return auctionParameters;
    }

    public Set<PhotoEntity> setAuctionPhotos(List<PhotoRequest> auctionPhotos, AuctionEntity auction) {
        Set<PhotoEntity> auctionPhotoSet = new HashSet<>();

        for (PhotoRequest photo : auctionPhotos) {
            var auctionPhoto = new PhotoEntity();
            auctionPhoto.setLink(photo.getLink());
            auctionPhoto.setPhotonumber(photo.getPhotonumber());
            auctionPhoto.setAuction(auction);
            auctionPhotoSet.add(auctionPhoto);
        }
        return auctionPhotoSet;
    }

    public List<AuctionEntity> listOfAllAuctions(){
        return entityManager.createQuery("select au.title, au. price, au.description, ph.link from AuctionEntity au, PhotoEntity ph where " +
                "ph.auction.id = au.id and ph.photonumber = 1").getResultList();
    }

}

