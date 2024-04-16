package com.restAssured.framework.spotify.playList_testcases;

import com.restAssured.framework.spotify.api.TokenManager;
import com.restAssured.framework.spotify.pojo.error.Error;
import com.restAssured.framework.spotify.pojo.playlist.SpotifyPlayList;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SpotifyPlayListAPI_TestCases_Using_POJO {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    SpotifyPlayList req_spotifyPlayList;

    //String access_token = "BQCS3h01ARa1T1JiE3NZcUALbQ8tzyoBSqEPsvGVT3EtN7URRceI_qajahQVLGrlybyN6FmfTIg_FFCkbZZxGRatiPHb28GYpDrdV9wXyYYxo2TKbe2yAWbN_vTM6VgCkIYnMOdU69tgP-VBLsj-nx1PakD_Hrqdi216lFqyvfpGMgTW99dY6TAIYVqoYbcc9oqDDLVswWkbSKzR32qPvC2Hydk5m6DfajLtj_hAsjAY3tKqN0ypaBcCEQ7ec_u5bYj38lATXUwMHHur";


    @BeforeClass
    public void setUpSpecification() {

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com/")
                .setBasePath("v1")
                .addHeader("Authorization", "Bearer " + TokenManager.getToken())

                .log(LogDetail.ALL);


        requestSpecification = requestSpecBuilder.build();


        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .log(LogDetail.ALL);

        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void shouldBeAbleToCreateAPlaylist_pojo() {


        SpotifyPlayList req_spotifyPlayList = new SpotifyPlayList();
        req_spotifyPlayList.setName("Spotify PlayList POJO 1");
        req_spotifyPlayList.setDescription("Spotify playlist created by rest assured using POJO");
        req_spotifyPlayList.set_public(false);

        // De-serialization
        SpotifyPlayList res_spotifyPlayList = given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(req_spotifyPlayList)
                .when()
                .post("/users/31xpojaotgmaxzoqgcdvx3g5uuou/playlists")
                .then()
                .spec(responseSpecification)

                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().response()
                .as(SpotifyPlayList.class);


        assertThat(req_spotifyPlayList.getName(), equalTo(res_spotifyPlayList.getName()));
        assertThat(req_spotifyPlayList.getDescription(), equalTo(res_spotifyPlayList.getDescription()));
        assertThat(req_spotifyPlayList.get_public(), equalTo(res_spotifyPlayList.get_public()));
    }


    @Test
    public void shouldBeAbleToGetAPlaylist_pojo() {

        SpotifyPlayList req_spotifyPlayList = new SpotifyPlayList();
        req_spotifyPlayList.setName("Spotify PlayList POJO 1");
        req_spotifyPlayList.setDescription("Spotify playlist created by rest assured using POJO");
        req_spotifyPlayList.set_public(true);

        // De-serialization
        SpotifyPlayList res_spotifyPlayList = given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .when()
                .get("/playlists/6FN7OdCrAMoFrPsK2RDSbj")
                .then()
                .spec(responseSpecification)

                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response()
                .as(SpotifyPlayList.class);
        assertThat(req_spotifyPlayList.getName(), equalTo(res_spotifyPlayList.getName()));
        assertThat(req_spotifyPlayList.getDescription(), equalTo(res_spotifyPlayList.getDescription()));
        assertThat(req_spotifyPlayList.get_public(), equalTo(res_spotifyPlayList.get_public()));

    }


    @Test
    public void shouldBeAbleToUpdateAPlaylist_pojo() {

        SpotifyPlayList req_spotifyPlayList = new SpotifyPlayList();
        req_spotifyPlayList.setName("Rest Assured Spotify PL 0002-updated");
        req_spotifyPlayList.setDescription("Spotify playlist created by Spotify web api using Rest Assured-updated");
        req_spotifyPlayList.set_public(false);

// De-serialization
        SpotifyPlayList res_spotifyPlayList = given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(req_spotifyPlayList)
                .when()
                .post("/users/31xpojaotgmaxzoqgcdvx3g5uuou/playlists")
                .then()
                .spec(responseSpecification)

                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract()
                .response()
                .as(SpotifyPlayList.class);
        assertThat(req_spotifyPlayList.getName(), equalTo(res_spotifyPlayList.getName()));
        assertThat(req_spotifyPlayList.getDescription(), equalTo(res_spotifyPlayList.getDescription()));
        assertThat(req_spotifyPlayList.get_public(), equalTo(res_spotifyPlayList.get_public()));

    }


    // Negative test cases

    @Test
    public void shouldNotBeAbleToCreateAPlaylist_with_Empty_Name() {

        SpotifyPlayList req_spotifyPlayList = new SpotifyPlayList();
        req_spotifyPlayList.setName("");
        req_spotifyPlayList.setDescription("Spotify playlist created by Spotify web api using Rest Assured-updated");
        req_spotifyPlayList.set_public(false);


        // De-serialization
        Error res_error = given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(req_spotifyPlayList)
                .when()
                .post("/users/31xpojaotgmaxzoqgcdvx3g5uuou/playlists")
                .then()
                .spec(responseSpecification)

                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(400)
                .extract()
                .response()
                .as(Error.class);
        assertThat(res_error.getError().getStatus(), equalTo(400));
        assertThat(res_error.getError().getMessage(), equalTo("Missing required field: name"));

    }


    @Test
    public void createPlayWithInvalidAccessToken() {
        SpotifyPlayList req_spotifyPlayList = new SpotifyPlayList();
        req_spotifyPlayList.setName("");
        req_spotifyPlayList.setDescription("Spotify playlist created by Spotify web api using Rest Assured-updated");
        req_spotifyPlayList.set_public(false);

        // De-serialization
        Error res_error = given()
                .baseUri("https://api.spotify.com/")
                .basePath("v1")
                .headers("Authorization", "Bearer BQCkQjPaZfFfpvgFaOratkSfm8ebJUi0A-W")
                .body(req_spotifyPlayList)
                .log().all()
                .when()
                .post("/users/31xpojaotgmaxzoqgcdvx3g5uuou/playlists")
                .then()
                .log().all()
                .assertThat()
                .statusCode(401)
                .extract()
                .response()
                .as(Error.class);

        assertThat(res_error.getError().getStatus(), equalTo(401));
        assertThat(res_error.getError().getMessage(), equalTo("Invalid access token"));


    }


}