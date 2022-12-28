package com.restAssured.framework.spotify.utils;

import com.github.javafaker.Faker;

public class JavaFakerUtils {

    public static String generateName(){
        Faker faker = new Faker();
        return  "Playlist : " + faker.regexify("[a-zA-Z0-9 _-]{20}");
    }


    public static String generateDescription(){
        Faker faker = new Faker();
        return  "Description: " + faker.regexify("[a-zA-Z0-9_@,./$&+-#]{50}");
    }
}
