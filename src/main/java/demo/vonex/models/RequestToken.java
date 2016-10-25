package demo.vonex.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Jitendra Singh.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestToken {

    private String request;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
