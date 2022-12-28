package com.restAssured.framework.spotify.applicationAPI;

import com.restAssured.framework.spotify.api.SpotifyCommonAPI_HTTP_Methods;
import com.restAssured.framework.spotify.pojo.playlist.SpotifyPlayList;

import com.restAssured.framework.spotify.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;


import static com.restAssured.framework.spotify.api.Route_BasePath_Constant.PLAYLIST;
import static com.restAssured.framework.spotify.api.Route_BasePath_Constant.USERS;
import static com.restAssured.framework.spotify.api.TokenManager.getToken;


public class PlayListAPI {

    @Step
    public static Response post(SpotifyPlayList req_spotifyPlayList) {

        return SpotifyCommonAPI_HTTP_Methods.post(USERS + "/" + ConfigLoader.getInstance().getUserID() +PLAYLIST, getToken(), req_spotifyPlayList);

    }

    @Step
    public static Response post(String token, SpotifyPlayList req_spotifyPlayList) {

        return SpotifyCommonAPI_HTTP_Methods.post(USERS + "/" + ConfigLoader.getInstance().getUserID() + PLAYLIST, token, req_spotifyPlayList);

    }


    @Step
    public static Response get(String playlistID) {

        return SpotifyCommonAPI_HTTP_Methods.get(PLAYLIST + "/" + playlistID, getToken());

    }


    public static Response update(String playlistID, SpotifyPlayList req_spotifyPlayList) {

        return SpotifyCommonAPI_HTTP_Methods.update(PLAYLIST +"/" + playlistID, getToken(), req_spotifyPlayList);

    }
}
