package pl.edu.pjwstk.jaz.Request;

public class ParameterRequest {

    private String key;
    private String value;

    public ParameterRequest(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public ParameterRequest() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
