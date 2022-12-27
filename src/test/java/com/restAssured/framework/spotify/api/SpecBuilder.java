package com.restAssured.framework.spotify.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.restAssured.framework.spotify.api.Route_BasePath_Constant.BASE_PATH;

public class SpecBuilder {


    public static RequestSpecification getRequestSpecs(){

        return new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .setBasePath(BASE_PATH)
                .log(LogDetail.ALL)
                .build();
    }


    public static RequestSpecification getAccountRequestSpecs(){

        return new RequestSpecBuilder()
                .setBaseUri("https://accounts.spotify.com")
                .setContentType(ContentType.URLENC)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpecs(){

        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();

    }
}
