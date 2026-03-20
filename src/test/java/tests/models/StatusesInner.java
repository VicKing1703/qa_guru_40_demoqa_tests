package tests.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusesInner {

    @JsonProperty("Active")
    private Integer active;
    @JsonProperty("Closed")
    private Integer closed;
    @JsonProperty("Featured")
    private Integer featured;

    public Integer getActive() {
        return active;
    }
    public Integer getClosed() {
        return closed;
    }
    public Integer getFeatured() {
        return featured;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
    public void setClosed(Integer closed) {
        this.closed = closed;
    }
    public void setFeatured(Integer featured) {
        this.featured = featured;
    }
}
