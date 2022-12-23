
package com.restAssured.framework.spotify.pojo.playlist;


import com.fasterxml.jackson.annotation.JsonProperty;


public class InnerExternalUrls {

    @JsonProperty("spotify")
    private String spotify;

    /**
     * No args constructor for use in serialization
     * 
     */
    public InnerExternalUrls() {
    }

    /**
     * 
     * @param spotify
     */
    public InnerExternalUrls(String spotify) {
        super();
        this.spotify = spotify;
    }

    @JsonProperty("spotify")
    public String getSpotify() {
        return spotify;
    }

    @JsonProperty("spotify")
    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }

}
