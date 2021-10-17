package pl.edu.pjwstk.jaz.Request;

public class CategoryRequest {
    private String name;

    public CategoryRequest(String name) {
        this.name = name;
    }

    public CategoryRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
