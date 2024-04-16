package com.restAssured.framework.spotify.playList_testcases;

import com.restAssured.framework.base.BaseTest;
import com.restAssured.framework.spotify.api.StatusCode;
import com.restAssured.framework.spotify.applicationAPI.PlayListAPI;
import com.restAssured.framework.spotify.pojo.error.Error;
import com.restAssured.framework.spotify.pojo.playlist.SpotifyPlayList;


import com.restAssured.framework.spotify.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static com.restAssured.framework.spotify.utils.JavaFakerUtils.generateDescription;
import static com.restAssured.framework.spotify.utils.JavaFakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

// Enable Multiple environments
// Maven command
// mvn test -DBASE_URI="https://api.spotify.com" -DACCOUNT_BASE_URI="https://accounts.spotify.com"
@Epic("spotify OAuth 2.0")
@Feature("Spotify Playlist API")
public class SpotifyPlayListAPI_TestCases_Using_POJO_Builder_Pattern extends BaseTest {

    @Story("Create Spotify Playlist")
//    @Link("https://example.org")
//    @Link(name = "allure", type = "mylink")
//    @Issue("12345")
//    // tms -- test management software
//    @TmsLink("tms/12345")

    @Description("Spotify Playlist should be created using POST call")
    @Test(description = "Spotify Create Playlist API - POST Call")
    public void shouldBeAbleToCreateAPlaylist_pojo() {

        SpotifyPlayList req_spotifyPlayList = spotifyPlayListBuilder(generateName(), generateDescription(), false);

        Response response = PlayListAPI.post(req_spotifyPlayList);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
        assertThat(response.contentType(), equalTo("application/json; charset=utf-8"));

        // De-serialization


        assertSpotifyPlaylistEquals(response.as(SpotifyPlayList.class), req_spotifyPlayList);


    }

    @Description("Spotify Playlist fetch using GET call")
    @Test(description = "Spotify Playlist - GET API")
    public void shouldBeAbleToGetAPlaylist_pojo() {

        SpotifyPlayList req_spotifyPlayList = spotifyPlayListBuilder("Spotify PlayList POJO 2",
                "Spotify playlist created by rest assured using POJO 2", false);


        Response response = PlayListAPI.get(DataLoader.getInstance().getPlaylistID());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        assertThat(response.contentType(), equalTo("application/json; charset=utf-8"));

        // De-serialization


        assertThat(req_spotifyPlayList.getName(), equalTo("Spotify PlayList POJO 2"));
        assertThat(req_spotifyPlayList.getDescription(), equalTo("Spotify playlist created by rest assured using POJO 2"));
        assertThat(req_spotifyPlayList.get_public(), equalTo(false));


    }

    @Description("Spotify Playlist should be updated using PUT call")
    @Test(description = "Spotify Update playlist - PUT API")
    public void shouldBeAbleToUpdateAPlaylist_pojo() {


        SpotifyPlayList req_spotifyPlayList = spotifyPlayListBuilder(generateName(), generateDescription(), false);


        Response response = PlayListAPI.update(DataLoader.getInstance().getUpdatePlaylistID(), req_spotifyPlayList);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);


// De-serialization


    }


    // Negative test cases
    @Story("Create Spotify Playlist")
    @Description("Spotify Playlist should not be created using POST call as we provide empty value for name field.")
    @Test (description = "Spotify create playlist with empty name - negative test case")
    public void shouldNotBeAbleToCreateAPlaylist_with_Empty_Name() {

        SpotifyPlayList req_spotifyPlayList = spotifyPlayListBuilder("",
                "Spotify playlist created by Spotify web api using Rest Assured-updated", false);


        Response response = PlayListAPI.post(req_spotifyPlayList);
        assertStatusCode(response.getStatusCode(), StatusCode.CODE_400);

        // De-serialization

        assertError(response.as(Error.class), StatusCode.CODE_400);

    }

    @Story("Create Spotify Playlist")
    @Description("Spotify Playlist should not be created using POST call as token is invalid")
    @Test(description = "Spotify playlist API with Invalid token - Negative test case")
    public void createPlayWithInvalidAccessToken() {

        String invalid_token = "12345";
        SpotifyPlayList req_spotifyPlayList = spotifyPlayListBuilder(generateName(), generateDescription(), false);


        Response response = PlayListAPI.post(invalid_token, req_spotifyPlayList);
        assertStatusCode(response.getStatusCode(), StatusCode.CODE_401);

        // De-serialization
        assertError(response.as(Error.class), StatusCode.CODE_401);
    }


    @Step
    public SpotifyPlayList spotifyPlayListBuilder(String playlist_name, String playlist_description, boolean isPublic) {

        return SpotifyPlayList.builder()
                .name(playlist_name)
                .description(playlist_description)
                ._public(isPublic)
                .build();


    }

    @Step
    public void assertSpotifyPlaylistEquals(SpotifyPlayList res_spotifyPlayList, SpotifyPlayList req_spotifyPlayList) {




        assertThat(req_spotifyPlayList.getName(), equalTo(res_spotifyPlayList.getName()));
        assertThat(req_spotifyPlayList.getDescription(), equalTo(res_spotifyPlayList.getDescription()));
        assertThat(req_spotifyPlayList.get_public(), equalTo(res_spotifyPlayList.get_public()));

    }

    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode) {
        assertThat(actualStatusCode, equalTo(statusCode.code));
    }

    @Step
    public void assertError(Error responseError, StatusCode statusCode) {

        assertThat(responseError.getError().getStatus(), equalTo(statusCode.code));
        assertThat(responseError.getError().getMessage(), equalTo(statusCode.msg));

    }



}