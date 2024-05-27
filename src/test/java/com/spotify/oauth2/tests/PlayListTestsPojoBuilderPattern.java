package com.spotify.oauth2.tests;

import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.PlayList;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpecification;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpecification;
import static io.restassured.RestAssured.given;

public class PlayListTestsPojoBuilderPattern {
         @Test
        public void createAPlayList() {

            PlayList playList = new PlayList().
                    setName("New PlayList Pojo").
                    setDescription("New playlist description Pojo").
                    setPublic(false);


            PlayList responsePlayList = given(getRequestSpecification()).
                    body(playList).
                    when().post("/users/dadovpysijxj9rxsjbgkfn723/playlists").
                    then().spec(getResponseSpecification()).
                    assertThat().
                    extract().
                    response().
                    as(PlayList.class);
            String playlistName = responsePlayList.getName();
            System.out.println("Name is " + playlistName);
            Assert.assertEquals(playlistName,"New PlayList Pojo" );

            String playlistDesc = responsePlayList.getDescription();
            System.out.println("Description is " + playlistDesc);
            Assert.assertEquals(playlistDesc,"New playlist description Pojo" );

            Assert.assertEquals(responsePlayList.getPublic(),false);



        }

        @Test
        public void getAPlayList() {
            PlayList playList = new PlayList();
            playList.setName("Updated Playlist Name 223 Pojo");
            playList.setDescription("Updated playlist description223 Pojo");
            playList.setPublic(false);

            PlayList responsePlayList =
                    given(getRequestSpecification()).
                            when().
                            get("/playlists/53MpfGN5T29mmeCINOmXFu").
                            then().
                            spec(getResponseSpecification()).
                            assertThat().
                            extract().
                            response().
                            as(PlayList.class);

            String playlistName = responsePlayList.getName();
            System.out.println("Name is " + playlistName);
            Assert.assertEquals(playlistName,playList.getName() );

            String playlistDesc = responsePlayList.getDescription();
            System.out.println("Description is " + playlistDesc);
            Assert.assertEquals(playlistDesc,playList.getDescription() );

            Assert.assertEquals(responsePlayList.getPublic(),playList.getPublic());

        }

        @Test
        public void updateAPlayList() {

            PlayList playList = new PlayList();
            playList.setName("Updated Playlist Name 223 Pojo");
            playList.setDescription("Updated playlist description223 Pojo");
            playList.setPublic(false);

            given(getRequestSpecification()).
                    body(playList).
                    when().
                    put("/playlists/53MpfGN5T29mmeCINOmXFu").
                    then().
                    spec(getResponseSpecification()).
                    statusCode(200);

        }



    }



