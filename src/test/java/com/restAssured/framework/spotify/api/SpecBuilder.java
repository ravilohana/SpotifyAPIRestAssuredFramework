package com.restAssured.framework.spotify.api;

import io.qameta.allure.restassured.AllureRestAssured;
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
                // when we run test case using maven command  line
                // so we can mention url at that time
               // .setBaseUri(System.getProperty("BASE_URI"))
                .setBasePath(BASE_PATH)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }


    public static RequestSpecification getAccountRequestSpecs(){

        return new RequestSpecBuilder()
                .setBaseUri("https://accounts.spotify.com")
                //.setBaseUri(System.getProperty("ACCOUNT_BASE_URI"))
                .setContentType(ContentType.URLENC)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpecs(){

        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();

    }
}
