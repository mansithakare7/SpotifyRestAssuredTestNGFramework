package com.spotify.oauth2.api.ApplicationApi;

import com.spotify.oauth2.pojo.PlayList;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpecification;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpecification;
import static io.restassured.RestAssured.given;

public class PlayListApi {


    public static Response post(PlayList requestPlayList) {

        return given(getRequestSpecification()).
                body(requestPlayList).
                when().post("/users/dadovpysijxj9rxsjbgkfn723/playlists").
                then().spec(getResponseSpecification()).
                extract().
                response();


    }

    public static Response get(String playListID) {
        return
                given(getRequestSpecification()).
                        when().
                        get("/playlists/" + playListID).
                        then().
                        spec(getResponseSpecification()).
                        extract().
                        response();

    }

    public static Response update(PlayList requestPlayList, String PlayListID) {
        return given(getRequestSpecification()).
                body(requestPlayList).
                when().
                put("/playlists/" + PlayListID).
                then().
                spec(getResponseSpecification()).
                extract().
                response();

    }
}