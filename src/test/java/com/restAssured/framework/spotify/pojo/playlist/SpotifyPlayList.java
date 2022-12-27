
package com.restAssured.framework.spotify.pojo.playlist;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;


@Getter @Setter
@Jacksonized
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

    public static SpotifyPlayListBuilder builder() {
        return new SpotifyPlayListBuilder();
    }


    public static class SpotifyPlayListBuilder {
        private Boolean collaborative;
        private String description;
        private ExternalUrls externalUrls;
        private Followers followers;
        private String href;
        private String id;
        private List<Object> images;
        private String name;
        private Owner owner;
        private Object primaryColor;
        private Boolean _public;
        private String snapshotId;
        private Tracks tracks;
        private String type;
        private String uri;

        SpotifyPlayListBuilder() {
        }

        public SpotifyPlayListBuilder collaborative(Boolean collaborative) {
            this.collaborative = collaborative;
            return this;
        }

        public SpotifyPlayListBuilder description(String description) {
            this.description = description;
            return this;
        }

        public SpotifyPlayListBuilder externalUrls(ExternalUrls externalUrls) {
            this.externalUrls = externalUrls;
            return this;
        }

        public SpotifyPlayListBuilder followers(Followers followers) {
            this.followers = followers;
            return this;
        }

        public SpotifyPlayListBuilder href(String href) {
            this.href = href;
            return this;
        }

        public SpotifyPlayListBuilder id(String id) {
            this.id = id;
            return this;
        }

        public SpotifyPlayListBuilder images(List<Object> images) {
            this.images = images;
            return this;
        }

        public SpotifyPlayListBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SpotifyPlayListBuilder owner(Owner owner) {
            this.owner = owner;
            return this;
        }

        public SpotifyPlayListBuilder primaryColor(Object primaryColor) {
            this.primaryColor = primaryColor;
            return this;
        }

        public SpotifyPlayListBuilder _public(Boolean _public) {
            this._public = _public;
            return this;
        }

        public SpotifyPlayListBuilder snapshotId(String snapshotId) {
            this.snapshotId = snapshotId;
            return this;
        }

        public SpotifyPlayListBuilder tracks(Tracks tracks) {
            this.tracks = tracks;
            return this;
        }

        public SpotifyPlayListBuilder type(String type) {
            this.type = type;
            return this;
        }

        public SpotifyPlayListBuilder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public SpotifyPlayList build() {
            return new SpotifyPlayList(collaborative, description, externalUrls, followers, href, id, images, name, owner, primaryColor, _public, snapshotId, tracks, type, uri);
        }

        public String toString() {
            return "SpotifyPlayList.SpotifyPlayListBuilder(collaborative=" + this.collaborative + ", description=" + this.description + ", externalUrls=" + this.externalUrls + ", followers=" + this.followers + ", href=" + this.href + ", id=" + this.id + ", images=" + this.images + ", name=" + this.name + ", owner=" + this.owner + ", primaryColor=" + this.primaryColor + ", _public=" + this._public + ", snapshotId=" + this.snapshotId + ", tracks=" + this.tracks + ", type=" + this.type + ", uri=" + this.uri + ")";
        }
    }
}
