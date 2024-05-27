package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.ApplicationApi.PlayListApi;
import com.spotify.oauth2.pojo.PlayList;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpecification;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListsTestsReusableMethods {

    @Test
    public void createAPlayList() {

        PlayList playList = new PlayList().
                setName("New PlayList Pojo").
                setDescription("New playlist description Pojo").
                setPublic(false);

        Response response = PlayListApi.post(playList);
        assertThat(response.statusCode(), equalTo(201));

        PlayList responsePlayList=response.as(PlayList.class);
        assertThat(responsePlayList.getName(), equalTo(playList.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(playList.getDescription()));
        assertThat(responsePlayList.getPublic(), equalTo(playList.getPublic()));
    }

    @Test
    public void getAPlayList() {
        PlayList playList = new PlayList();
        playList.setName("Updated Playlist Name 223 Pojo");
        playList.setDescription("Updated playlist description223 Pojo");
        playList.setPublic(false);


        Response response = PlayListApi.get("53MpfGN5T29mmeCINOmXFu");
        assertThat(response.statusCode(), equalTo(200));

        PlayList responsePlayList=response.as(PlayList.class);

        assertThat(responsePlayList.getName(), equalTo(playList.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(playList.getDescription()));
        assertThat(responsePlayList.getPublic(), equalTo(playList.getPublic()));

        Assert.assertEquals(responsePlayList.getPublic(),playList.getPublic());

    }

    @Test
    public void updateAPlayList() {

        PlayList playList = new PlayList()
       .setName("Updated Playlist Name 223 Pojo")
        .setDescription("Updated playlist description223 Pojo")
       .setPublic(false);


        Response response = PlayListApi.update(playList, "53MpfGN5T29mmeCINOmXFu");
       assertThat(response.statusCode(), equalTo(200));




    }




}
