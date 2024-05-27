package com.spotify.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.oauth2.api.Routes.BASE_PATH;

public class SpecBuilder {

     static String accessToken = "BQCtTMNNSe58BOAaZP2hkHuzKBW75ThLCWFpIcpiri0YlZMdxUC6dlZ2T1XWuwX8RTAhBOxeK1cBH6BzH8AZYZNGO7BhZH5nt9BgaFyT3RuB1tbw5ccs28weYOz4gFBWR-a5TflrJyBFpJoDnCdtLvR2sHu9lETf_MPdjvMynO3-E2cHpw3N5bVgioaqseIm0asoZQ1q_pOGrzP19r2-sSyjYqVs3gv-WT6gr3UiT5AsCd1t0xRwk57ZUotR6LZU-IopA0sgL_Bv";

    public static RequestSpecification getRequestSpecification(){

        return new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath(BASE_PATH).
                addHeader("Authorization", "Bearer "+ accessToken).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();
    }


    public static ResponseSpecification getResponseSpecification(){
        return new ResponseSpecBuilder().
                    log(LogDetail.ALL).
                    build();
    }

}


