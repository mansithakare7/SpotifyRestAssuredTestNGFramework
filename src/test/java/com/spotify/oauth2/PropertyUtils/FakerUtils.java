package com.spotify.oauth2.PropertyUtils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName(){

        Faker faker = new Faker();
        return "PlayList" + faker.regexify("[A-za-z0-9,/+]{20}");
    }

    public static String generateDescription(){

        Faker faker = new Faker();
        return "PlayListDescription" + faker.regexify("[A-za-z0-9,/+]{20}");
    }

}
