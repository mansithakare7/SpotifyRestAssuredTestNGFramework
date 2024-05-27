package com.spotify.oauth2.api;

import com.spotify.oauth2.pojo.PlayList;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpecification;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpecification;
import static io.restassured.RestAssured.given;

public class RestResource {
    public static Response post(String path, String token, Object requestPlayList) {

        return given(getRequestSpecification()).
                body(requestPlayList).
                auth().oauth2(token).
               // header("Authorization", "Bearer" + token).
                when().post(path).
                then().spec(getResponseSpecification()).
                extract().
                response();


    }

    public static Response get(String path,String token) {
        return
                given(getRequestSpecification()).
                        header("Authorization", "Bearer" + token).
                        when().
                        get(path).

                        then().
                        spec(getResponseSpecification()).
                        extract().
                        response();

    }

    public static Response update(Object requestPlayList, String path, String token) {
        return given(getRequestSpecification()).
                header("Authorization", "Bearer" + token).
                body(requestPlayList).
                when().
                put(path).
                then().
                spec(getResponseSpecification()).
                extract().
                response();

    }
}
