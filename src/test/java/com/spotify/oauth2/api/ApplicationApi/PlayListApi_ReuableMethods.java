package com.spotify.oauth2.api.ApplicationApi;

import com.spotify.oauth2.PropertyUtils.ConfigLoader;
import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.PlayList;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.ApplicationApi.TokenManager.renewToken;
import static com.spotify.oauth2.api.Routes.PLAYLISTS;
import static com.spotify.oauth2.api.Routes.USERS;
import static com.spotify.oauth2.api.SpecBuilder.getRequestSpecification;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpecification;
import static io.restassured.RestAssured.given;

public class PlayListApi_ReuableMethods {

  static String accessToken = "BQCtTMNNSe58BOAaZP2hkHuzKBW75ThLCWFpIcpiri0YlZMdxUC6dlZ2T1XWuwX8RTAhBOxeK1cBH6BzH8AZYZNGO7BhZH5nt9BgaFyT3RuB1tbw5ccs28weYOz4gFBWR-a5TflrJyBFpJoDnCdtLvR2sHu9lETf_MPdjvMynO3-E2cHpw3N5bVgioaqseIm0asoZQ1q_pOGrzP19r2-sSyjYqVs3gv-WT6gr3UiT5AsCd1t0xRwk57ZUotR6LZU-IopA0sgL_Bv";

    public static Response post(PlayList requestPlayList) {

        return RestResource.post(USERS +"/dadovpysijxj9rxsjbgkfn723" + PLAYLISTS,accessToken ,requestPlayList);


    }

    public static Response get(String playListID) {
        return
                RestResource.get(PLAYLISTS + "/" + playListID, accessToken);

    }

    public static Response update(PlayList requestPlayList, String PlayListID) {
        return
                RestResource.update(requestPlayList,PLAYLISTS + "/" + PlayListID,accessToken);


    }
}