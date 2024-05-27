package com.spotify.oauth2.api.ApplicationApi;

import com.spotify.oauth2.PropertyUtils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.getResponseSpecification;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class TokenManager {
   // private static  String  access_token;
  //  private static  String  expiry_time;

  //  public static String getToken(){

   // }

    public static Response renewToken(){
        HashMap<String, String > formParams = new HashMap<String, String>();
        formParams.put("client_id", "bdb3164b63564567b1eb04c9ff1f55c2");
        formParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret", ConfigLoader.getInstance().getSecretKey());
        formParams.put("grant_type",ConfigLoader.getInstance().getGrantType());
        formParams.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());


        Response response = given().
                baseUri("https://accounts.spotify.com/").
                contentType(ContentType.URLENC).
                formParams(formParams).
                when().
                post("api/token").
                then().spec(getResponseSpecification()).
                extract().
                response();

        if(response.getStatusCode()!=200){
            throw new RuntimeException("Abort !!! renew token failed");
        }
        return response.path("access_token");
       // return response;
    }
}
