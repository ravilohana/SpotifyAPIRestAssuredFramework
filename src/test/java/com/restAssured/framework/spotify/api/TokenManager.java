package com.restAssured.framework.spotify.api;

import com.restAssured.framework.spotify.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.restAssured.framework.spotify.api.SpecBuilder.getResponseSpecs;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static  String access_token;
    private static Instant expiry_time;

    public synchronized static  String getToken(){
        try {

            if(access_token == null || Instant.now().isAfter(expiry_time)){
                System.out.println("Token Renewing.....");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            }
            else {
                System.out.println("Token is good to use");
            }

        }
        catch (Exception e){
                throw new RuntimeException("ABORT!! Failed to get token");
        }

            return access_token;


    }


    private static Response  renewToken(){
        Map<String,String> token_formParam = new HashMap<>();
        token_formParam.put("client_id", ConfigLoader.getInstance().getClientID());
        token_formParam.put("client_secret",ConfigLoader.getInstance().getClientSecret());
        token_formParam.put("grant_type",ConfigLoader.getInstance().getGrantType());
        token_formParam.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());


        Response response = SpotifyCommonAPI_HTTP_Methods.postAccount(token_formParam);


        if(response.statusCode() != 200){
            throw  new RuntimeException("ABORT!!! Renew token failed!");
        }
        return response;

    }

}
