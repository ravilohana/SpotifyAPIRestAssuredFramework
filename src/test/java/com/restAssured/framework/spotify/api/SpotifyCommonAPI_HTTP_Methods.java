package com.restAssured.framework.spotify.api;

import com.restAssured.framework.spotify.pojo.playlist.SpotifyPlayList;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.restAssured.framework.spotify.api.Route_BasePath_Constant.ACCOUNT_API;
import static com.restAssured.framework.spotify.api.Route_BasePath_Constant.ACCOUNT_TOKEN;
import static com.restAssured.framework.spotify.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class SpotifyCommonAPI_HTTP_Methods {



    public static Response post(String path,String token, Object spotify_request_payload) {
        return given()
                .header("Authorization", "Bearer " + token)
                .spec(getRequestSpecs())

                .contentType(ContentType.JSON)
                .body(spotify_request_payload)
                .when()
                .post(path)
                .then()
                .spec(getResponseSpecs())
                .extract().response();
    }


    public static Response postAccount(Map<String,String> token_formParam){


        return  given(getAccountRequestSpecs())
                .formParams(token_formParam)
                .when()
                .post(ACCOUNT_API  + ACCOUNT_TOKEN)
                .then().spec(getResponseSpecs())
                .extract().response();

    }
    public static Response get(String path,String token){
        return given()
                .header("Authorization", "Bearer " + token)
                .spec(getRequestSpecs())
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .spec(getResponseSpecs())
                .extract().response();
    }


    public static Response update(String path,String token, Object spotify_request_payload){
        return given()
                .header("Authorization", "Bearer " + token)
                .spec(getRequestSpecs())
                .contentType(ContentType.JSON)
                .body(spotify_request_payload)
                .when()
                .put(path)
                .then()
                .spec(getResponseSpecs())
                .extract()
                .response();
    }
}
