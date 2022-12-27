package com.restAssured.framework.spotify.playList_testcases;

import com.restAssured.framework.spotify.applicationAPI.PlayListAPI;
import com.restAssured.framework.spotify.pojo.error.Error;
import com.restAssured.framework.spotify.pojo.playlist.SpotifyPlayList;


import com.restAssured.framework.spotify.utils.DataLoader;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SpotifyPlayListAPI_TestCases_Using_POJO_Builder_Pattern {



    @Test
    public void shouldBeAbleToCreateAPlaylist_pojo() {

        SpotifyPlayList req_spotifyPlayList = spotifyPlayListBuilder("Spotify PlayList POJO 2",
                "Spotify playlist created by rest assured using POJO 2", false);

        Response response = PlayListAPI.post(req_spotifyPlayList);
        assertStatusCode(response.statusCode(), 201);
        assertThat(response.contentType(), equalTo("application/json; charset=utf-8"));

        // De-serialization


        assertSpotifyPlaylistEquals(response.as(SpotifyPlayList.class), req_spotifyPlayList);


    }


    @Test
    public void shouldBeAbleToGetAPlaylist_pojo() {

        SpotifyPlayList req_spotifyPlayList = spotifyPlayListBuilder("Spotify PlayList POJO 2",
                "Spotify playlist created by rest assured using POJO 2", false);


        Response response = PlayListAPI.get(DataLoader.getInstance().getPlaylistID());
        assertStatusCode(response.statusCode(), 200);
        assertThat(response.contentType(), equalTo("application/json; charset=utf-8"));

        // De-serialization


        assertSpotifyPlaylistEquals(response.as(SpotifyPlayList.class), req_spotifyPlayList);


    }


    @Test
    public void shouldBeAbleToUpdateAPlaylist_pojo() {


        SpotifyPlayList req_spotifyPlayList = spotifyPlayListBuilder("Rest Assured Spotify PL 0002-updated",
                "Spotify playlist created by Spotify web api using Rest Assured-updated", false);


        Response response = PlayListAPI.update(DataLoader.getInstance().getUpdatePlaylistID(), req_spotifyPlayList);
        assertStatusCode(response.statusCode(), 200);


// De-serialization


    }


    // Negative test cases

    @Test
    public void shouldNotBeAbleToCreateAPlaylist_with_Empty_Name() {

        SpotifyPlayList req_spotifyPlayList = spotifyPlayListBuilder("",
                "Spotify playlist created by Spotify web api using Rest Assured-updated", false);


        Response response = PlayListAPI.post(req_spotifyPlayList);
        assertStatusCode(response.getStatusCode(), 400);

        // De-serialization

        assertError(response.as(Error.class), 400, "Missing required field: name");

    }


    @Test
    public void createPlayWithInvalidAccessToken() {

        String invalid_token = "12345";
        SpotifyPlayList req_spotifyPlayList = spotifyPlayListBuilder("",
                "Spotify playlist created by Spotify web api using Rest Assured-updated", false);


        Response response = PlayListAPI.post(invalid_token, req_spotifyPlayList);
        assertStatusCode(response.getStatusCode(), 401);

        // De-serialization
        assertError(response.as(Error.class), 401, "Invalid access token");
    }



    public SpotifyPlayList spotifyPlayListBuilder(String playlist_name, String playlist_description, boolean isPublic) {


        SpotifyPlayList spotifyPlayList = new SpotifyPlayList();
        spotifyPlayList.setName(playlist_name);
        spotifyPlayList.setDescription(playlist_description);
        spotifyPlayList.set_public(isPublic);
        return spotifyPlayList;

    }

    public void assertSpotifyPlaylistEquals(SpotifyPlayList res_spotifyPlayList, SpotifyPlayList req_spotifyPlayList) {

        assertThat(req_spotifyPlayList.getName(), equalTo(res_spotifyPlayList.getName()));
        assertThat(req_spotifyPlayList.getDescription(), equalTo(res_spotifyPlayList.getDescription()));
        assertThat(req_spotifyPlayList.get_public(), equalTo(res_spotifyPlayList.get_public()));

    }

    public void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }

    public void assertError(Error responseError, int expectedStatusCode, String expectedMsg) {

        assertThat(responseError.getError().getStatus(), equalTo(expectedStatusCode));
        assertThat(responseError.getError().getMessage(), equalTo(expectedMsg));

    }



}