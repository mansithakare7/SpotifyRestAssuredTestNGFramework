package com.spotify.oauth2.tests;

import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.InnerError;
import com.spotify.oauth2.pojo.PlayList;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTestsPojo {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String accessToken = "BQAP4ER0j3rAAjCOayaPYLPPa06qYXnHEG5FkWeGKnbVU3OzqcacMEcqwJKYVykLpMSTATg15J2sgRZkV8R8P-Nckvh4ZmVlUi7zOiF7cF7WURl3SboQuHFz7FtteY8HMzJPOehVKdd7sb2tHtFgpkcWq89JVS5h_fl0KBukV5qt41bYOcbakoEYrwObKJykp5qfjtr1kqsla3cq2DdYLCvS1r-Mnf8YH96MwGtYLitcOepIoibYt4ZO8vaf1iVIDtOa2N-GxUcS";

    @BeforeClass
    public void beforeClass(){

        RequestSpecBuilder requestSpecBuilder= new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath("/v1").
                addHeader("Authorization", "Bearer "+ accessToken).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);

        requestSpecification= requestSpecBuilder.build();



        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                // expectContentType(ContentType.JSON).
                // expectStatusCode(200).
                        log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void createAPlayList() {

        PlayList playList = new PlayList();
        playList.setName("New PlayList Pojo");
        playList.setDescription("New playlist description Pojo");
        playList.setPublic(false);

        PlayList responsePlayList = given(requestSpecification).
                body(playList).
                when().post("/users/dadovpysijxj9rxsjbgkfn723/playlists").
                then().spec(responseSpecification).
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
                given(requestSpecification).
                when().
                get("/playlists/53MpfGN5T29mmeCINOmXFu").
                then().
                spec(responseSpecification).
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

         given(requestSpecification).
                body(playList).
                when().
                put("/playlists/53MpfGN5T29mmeCINOmXFu").
                then().
                spec(responseSpecification).
                 statusCode(200);


    }

    //Negative Scenarios

    @Test
    public void do_not_createAPlayList() {


        PlayList playList = new PlayList();
        playList.setName(" ");
        playList.setDescription("New playlist description Pojo");
        playList.setPublic(false);

        Error error =  given(requestSpecification).
               // contentType(ContentType.JSON).
                body(playList).
                when().
                post("/users/dadovpysijxj9rxsjbgkfn723/playlists").
                then().
                spec(responseSpecification).
                assertThat().
                extract().
                as(Error.class);




    }

    @Test
    public void do_not_createAPlayList_with_invalid_accessToken() {


        PlayList playList = new PlayList();
        playList.setName("PlayList");
        playList.setDescription("New playlist description Pojo");
        playList.setPublic(false);

        Error error =
               given().
                baseUri("https://api.spotify.com").
                basePath("/v1").
                header("Authorization", "Bearer "+ "123").
                contentType(ContentType.JSON).
                log().all().
                body(playList).
                when().
                        post("/users/dadovpysijxj9rxsjbgkfn723/playlists").
                then().
                        spec(responseSpecification).
                        assertThat().
                        statusCode(401).
                        extract().
                        as(Error.class);

     //  assertThat(error.getError().getStatus(), equalTo(401));



    }



}
