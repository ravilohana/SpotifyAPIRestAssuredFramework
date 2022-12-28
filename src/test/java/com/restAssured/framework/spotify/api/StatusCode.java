package com.restAssured.framework.spotify.api;

public enum StatusCode {
    CODE_200(200,""),
    CODE_201(201,""),
    CODE_400(400,"Missing required field: name"),
    CODE_401(401,"Invalid access token");

//    private final int code;
//    private final String msg;

    // Make public to make code more optimize

    public final int code;
    public final String msg;
    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // as we make code and msg variable public, so no need of getters now

//    public int getCode() {
//        return code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
}
