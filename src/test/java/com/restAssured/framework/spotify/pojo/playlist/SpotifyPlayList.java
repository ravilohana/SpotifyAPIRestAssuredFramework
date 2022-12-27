
package com.restAssured.framework.spotify.pojo.playlist;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
//@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpotifyPlayList {

    @JsonProperty("collaborative")
    private Boolean collaborative;
    @JsonProperty("description")
    private String description;
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;
    @JsonProperty("followers")
    private Followers followers;
    @JsonProperty("href")
    private String href;
    @JsonProperty("id")
    private String id;
    @JsonProperty("images")
    private List<Object> images = null;
    @JsonProperty("name")
    private String name;
    @JsonProperty("owner")
    private Owner owner;
    @JsonProperty("primary_color")
    private Object primaryColor;
    @JsonProperty("public")
    private Boolean _public;
    @JsonProperty("snapshot_id")
    private String snapshotId;
    @JsonProperty("tracks")
    private Tracks tracks;
    @JsonProperty("type")
    private String type;
    @JsonProperty("uri")
    private String uri;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SpotifyPlayList() {
    }


    /**
     * 
     * @param owner
     * @param images
     * @param snapshotId
     * @param _public
     * @param collaborative
     * @param primaryColor
     * @param description
     * @param externalUrls
     * @param type
     * @param uri
     * @param tracks
     * @param followers
     * @param name
     * @param href
     * @param id
     */



    public SpotifyPlayList(Boolean collaborative, String description, ExternalUrls externalUrls, Followers followers, String href, String id, List<Object> images, String name, Owner owner, Object primaryColor, Boolean _public, String snapshotId, Tracks tracks, String type, String uri) {
        super();
        this.collaborative = collaborative;
        this.description = description;
        this.externalUrls = externalUrls;
        this.followers = followers;
        this.href = href;
        this.id = id;
        this.images = images;
        this.name = name;
        this.owner = owner;
        this.primaryColor = primaryColor;
        this._public = _public;
        this.snapshotId = snapshotId;
        this.tracks = tracks;
        this.type = type;
        this.uri = uri;
    }


}
