package com.restAssured.framework.spotify.playList_testcases;

import com.restAssured.framework.spotify.api.TokenManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SpotifyPlayListAPI_TestCases {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    //String access_token = "BQCS3h01ARa1T1JiE3NZcUALbQ8tzyoBSqEPsvGVT3EtN7URRceI_qajahQVLGrlybyN6FmfTIg_FFCkbZZxGRatiPHb28GYpDrdV9wXyYYxo2TKbe2yAWbN_vTM6VgCkIYnMOdU69tgP-VBLsj-nx1PakD_Hrqdi216lFqyvfpGMgTW99dY6TAIYVqoYbcc9oqDDLVswWkbSKzR32qPvC2Hydk5m6DfajLtj_hAsjAY3tKqN0ypaBcCEQ7ec_u5bYj38lATXUwMHHur";




    @BeforeClass
    public void setUpSpecification(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                    .setBaseUri("https://api.spotify.com/")
                .setBasePath("v1")
                .addHeader("Authorization","Bearer " + TokenManager.getToken())

                .log(LogDetail.ALL);


        requestSpecification = requestSpecBuilder.build();


        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .log(LogDetail.ALL);

        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void shouldBeAbleToCreateAPlaylist(){

//        Map<String,String> createPlaylistPayload_hashMap = new HashMap<>();
//
//        createPlaylistPayload_hashMap.put("name","Rest Assured Spotify PL 0002");
//        createPlaylistPayload_hashMap.put("description","Spotify playlist created by Spotify web api using Rest Assured");
//        createPlaylistPayload_hashMap.put("public","false");


        String createPlaylistPayload = "{\n" +
                "  \"name\": \"Rest Assured Spotify PL 0002\",\n" +
                "  \"description\": \"Spotify playlist created by Spotify web api using Rest Assured\",\n" +
                "  \"public\": false\n" +
                "}";
        given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(createPlaylistPayload)
        .when()
                .post("/users/31xpojaotgmaxzoqgcdvx3g5uuou/playlists")
        .then()
                .spec(responseSpecification)

                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("name",equalTo("Rest Assured Spotify PL 0002"),
                        "description",equalTo("Spotify playlist created by Spotify web api using Rest Assured"),
                        "public",equalTo(false));

    }


    @Test
    public void shouldBeAbleToGetAPlaylist(){

        given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .when()
                .get("/playlists/223SMF4W5ofUDiPlx62VWv")
                .then()
                .spec(responseSpecification)

                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name",equalTo("Rest Assured Spotify PL 0002"),
                        "description",equalTo("Spotify playlist created by Spotify web api using Rest Assured"),
                        "public",equalTo(true));

    }


    @Test
    public void shouldBeAbleToUpdateAPlaylist(){

//        Map<String,String> createPlaylistPayload_hashMap = new HashMap<>();
//
//        createPlaylistPayload_hashMap.put("name","Rest Assured Spotify PL 0002");
//        createPlaylistPayload_hashMap.put("description","Spotify playlist created by Spotify web api using Rest Assured");
//        createPlaylistPayload_hashMap.put("public","false");


        String updatePlaylistPayload = "{\n" +
                "  \"name\": \"Rest Assured Spotify PL 0002-updated\",\n" +
                "  \"description\": \"Spotify playlist created by Spotify web api using Rest Assured-updated\",\n" +
                "  \"public\": false\n" +
                "}";
        given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(updatePlaylistPayload)
                .when()
                .post("/users/31xpojaotgmaxzoqgcdvx3g5uuou/playlists")
                .then()
                .spec(responseSpecification)

                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("name",equalTo("Rest Assured Spotify PL 0002-updated"),
                        "description",equalTo("Spotify playlist created by Spotify web api using Rest Assured-updated"),
                        "public",equalTo(false));

    }


    // Negative test cases

    @Test
    public void shouldNotBeAbleToCreateAPlaylist_with_Empty_Name(){

//        Map<String,String> createPlaylistPayload_hashMap = new HashMap<>();
//
//        createPlaylistPayload_hashMap.put("name","Rest Assured Spotify PL 0002");
//        createPlaylistPayload_hashMap.put("description","Spotify playlist created by Spotify web api using Rest Assured");
//        createPlaylistPayload_hashMap.put("public","false");


        String createPlaylistPayload = "{\n" +
                "  \"name\": \"\",\n" +
                "  \"description\": \"Spotify playlist created by Spotify web api using Rest Assured\",\n" +
                "  \"public\": false\n" +
                "}";
        given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(createPlaylistPayload)
                .when()
                .post("/users/31xpojaotgmaxzoqgcdvx3g5uuou/playlists")
                .then()
                .spec(responseSpecification)

                .assertThat()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("error.status",equalTo(400),
                        "error.message",equalTo("Missing required field: name"));

    }


    @Test
    public void createPlayWithInvalidAccessToken(){

        String playlistPayload = "{\n" +
                "  \"name\": \"Rest Assured Spotify PL 0002-updated\",\n" +
                "  \"description\": \"Spotify playlist created by Spotify web api using Rest Assured-updated\",\n" +
                "  \"public\": false\n" +
                "}";

        given()
                .baseUri("https://api.spotify.com/")
                .basePath("v1")
                .headers("Authorization" , "Bearer BQCkQjPaZfFfpvgFaOratkSfm8ebJUi0A-W" )
                .body(playlistPayload)
                .log().all()
        .when()
                .post("/users/31xpojaotgmaxzoqgcdvx3g5uuou/playlists")
        .then()
                .log().all()
                .assertThat()
                .statusCode(401)
                .body("error.status",equalTo(401),
                        "error.message",equalTo("Invalid access token"));



    }



}
