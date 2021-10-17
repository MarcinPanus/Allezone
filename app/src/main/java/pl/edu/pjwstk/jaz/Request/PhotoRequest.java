package pl.edu.pjwstk.jaz.Request;

public class PhotoRequest {

    private String link;
    private int photonumber;

    public PhotoRequest(String link, int photonumber) {
        this.link = link;
        this.photonumber = photonumber;
    }

    public PhotoRequest() {

    }

    public int getPhotonumber() {
        return photonumber;
    }

    public void setPhotonumber(int photonumber) {
        this.photonumber = photonumber;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
