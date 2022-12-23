package com.restAssured.framework.spotify.playList_testcases;

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

    String access_token = "BQCkQjPaZfFfpvgFaOratkSfm8ebJUi0A-WB-gDQIHXwE0WB7F3oSY4KVKRS1GEWhyF5H0WBGjTJz-i6wgcWiqzMF4hVNKTcg-dW4iyTDPuHHxeOZIXamjuPHmooeTLx9c94WfRqUkn10XGh_HZd-tumcdFCSCWAVmTuguhYxUp7E3lvDIM6I3cRlqXI29EIFwNuuvxXrwo-e3heHtm9gMIbWLGCO4vnWvgDia89TCf9jZwPhUXV-wYVnih2IKT2CcZHkqP_q_VwxnPp";




    @BeforeClass
    public void setUpSpecification(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com/")
                .setBasePath("v1")
                .addHeader("Authorization","Bearer " + access_token)

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
                .post("/users/312p2bxukvqmvk2zkzjwiigvjcgi/playlists")
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
                .get("/playlists/3uuZAymSsRixFpmpiNUxNi")
                .then()
                .spec(responseSpecification)

                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name",equalTo("Rest Assured Spotify PL 0002"),
                        "description",equalTo("Spotify playlist created by Spotify web api using Rest Assured"),
                        "public",equalTo(false));

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
                .post("/users/312p2bxukvqmvk2zkzjwiigvjcgi/playlists")
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
                .post("/users/312p2bxukvqmvk2zkzjwiigvjcgi/playlists")
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
                .post("/users/312p2bxukvqmvk2zkzjwiigvjcgi/playlists")
        .then()
                .log().all()
                .assertThat()
                .statusCode(401)
                .body("error.status",equalTo(401),
                        "error.message",equalTo("Invalid access token"));



    }



}
