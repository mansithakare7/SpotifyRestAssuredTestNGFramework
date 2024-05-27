package com.spotify.oauth2.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class PlaylistsTests extends BaseTest {


    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String accessToken = "BQBXY-VmZ8bzPS8Fzv8CA7725_sXGwViGFONuLIgrURT3FYdFE8ps380z81GgMSrObYHIXMwYHplaKprB6BPbA94y2L5uqJfvAxz-OmrJI9KhPBjDgCjn1Lg6nd08JhOVYgAtUqK-wRYyGnMezoZZ1znYdNO5LhmOT0EAGrdHNYB5N_9uOTKsXzqRV_pn2AyaGsXc3KEIo15hFgRh9ZbCoXV4-mNtbQkkqVkbbIERFVzDAuAoWP_ROUVVy28sMIS8hc9mpdDmBjH";

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

        String payload = "{\n" +
                "    \"name\": \"New Playlist2\",\n" +
                "    \"description\": \"New playlist description2\",\n" +
                "    \"public\": false\n" +
                "}";

       given(requestSpecification).
                body(payload).
                when().post("/users/dadovpysijxj9rxsjbgkfn723/playlists").
                then().spec(responseSpecification).
                assertThat().
                statusCode(201);


      //  String res = JsonPath.from(response).getString("title");
        //System.out.println("Response is " + res);
        // assertThat(title,equalTo("zHwoCjsm2BwY"));
        //Assert.assertEquals(res,"zHwoCjsm2BwY" );

    }

    @Test
    public void getAPlayList() {

        given(requestSpecification).
                when().get("/playlists/53MpfGN5T29mmeCINOmXFu").
                then().spec(responseSpecification).
                assertThat().
                statusCode(200);

    }

    @Test
    public void updateAPlayList() {

        String payload = "{\n" +
                "    \"name\": \"Updated Playlist Name 223\",\n" +
                "    \"description\": \"Updated playlist description223\",\n" +
                "    \"public\": false\n" +
                "}";

        given(requestSpecification).
                body(payload).
                when().put("/playlists/53MpfGN5T29mmeCINOmXFu").
                then().spec(responseSpecification).
                assertThat().
                statusCode(200);


    }

    //Negative Scenarios

    @Test
    public void do_not_createAPlayList() {

        String payload = "{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"New playlist description2\",\n" +
                "    \"public\": false\n" +
                "}";

        given(requestSpecification).
                body(payload).
                when().post("/users/dadovpysijxj9rxsjbgkfn723/playlists").
                then().spec(responseSpecification).
                assertThat().
                statusCode(400);

    }

    @Test
    public void do_not_createAPlayList_with_invalid_accessToken() {

        String payload = "{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"New playlist description2\",\n" +
                "    \"public\": false\n" +
                "}";

        given().
                baseUri("https://api.spotify.com").
                basePath("/v1").
                header("Authorization", "Bearer "+ "123").
                contentType(ContentType.JSON).
                log().all().
                body(payload).
                when().post("/users/dadovpysijxj9rxsjbgkfn723/playlists").
                then().spec(responseSpecification).
                assertThat().
                statusCode(401);

    }





}
