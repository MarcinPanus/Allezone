package pl.edu.pjwstk.jaz.Request;

import java.util.List;

public class AuctionRequest {

    private String title;
    private String description;
    private int price;
    private String nameOfCategory;
    private List<ParameterRequest> parameters;
    private List<PhotoRequest> photos;

    public AuctionRequest() {
    }

    public AuctionRequest(String title, String description, int price, String nameOfCategory, List<ParameterRequest> parameters, List<PhotoRequest> photos) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.nameOfCategory = nameOfCategory;
        this.parameters = parameters;
        this.photos = photos;
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

    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public void setNameOfCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    public List<ParameterRequest> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterRequest> parameters) {
        this.parameters = parameters;
    }

    public List<PhotoRequest> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoRequest> photos) {
        this.photos = photos;
    }
}
