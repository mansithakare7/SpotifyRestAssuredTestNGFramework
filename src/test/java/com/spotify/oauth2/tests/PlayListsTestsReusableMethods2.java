package com.spotify.oauth2.tests;

import com.spotify.oauth2.PropertyUtils.DataLoader;
import com.spotify.oauth2.api.ApplicationApi.PlayListApi;
import com.spotify.oauth2.api.ApplicationApi.PlayListApi_ReuableMethods;
import com.spotify.oauth2.pojo.PlayList;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.spotify.oauth2.PropertyUtils.FakerUtils.generateDescription;
import static com.spotify.oauth2.PropertyUtils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListsTestsReusableMethods2 {


    public PlayList playListBuilder(String name, String description, boolean publicBoolean){
       return new PlayList().
                setName(name).
                setDescription(description).
                setPublic(publicBoolean);
    }

    public void assertPlayList(PlayList responsePlaylist, PlayList requestPlaylist)
    {
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));

    }
    @Test (description = "should be able to create a playlist")
    public void createAPlayList() {

       // PlayList requestPlayList = playListBuilder("New PlayList Pojo","New playlist description Pojo",false);
        PlayList requestPlayList = playListBuilder(generateName(),generateDescription(),false);

       // PlayList playList = new PlayList().
         //       setName("New PlayList Pojo").
           //     setDescription("New playlist description Pojo").
            //    setPublic(false);

        Response response = PlayListApi_ReuableMethods.post(requestPlayList);
        assertThat(response.statusCode(), equalTo(201));

        PlayList responsePlayList=response.as(PlayList.class);
        assertPlayList(responsePlayList, requestPlayList);
       // assertThat(responsePlayList.getName(), equalTo(playList.getName()));
        //assertThat(responsePlayList.getDescription(), equalTo(playList.getDescription()));
      //  assertThat(responsePlayList.getPublic(), equalTo(playList.getPublic()));
    }

    @Test
    public void getAPlayList() {
        PlayList playList = new PlayList();
        playList.setName("Updated Playlist Name 223 Pojo");
        playList.setDescription("Updated playlist description223 Pojo");
        playList.setPublic(false);


      // Response response = PlayListApi_ReuableMethods.get("53MpfGN5T29mmeCINOmXFu");
        Response response = PlayListApi_ReuableMethods.get(DataLoader.getInstance().getPlayListID());
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


        //Response response = PlayListApi_ReuableMethods.update(playList, "53MpfGN5T29mmeCINOmXFu");
        Response response = PlayListApi_ReuableMethods.update(playList, DataLoader.getInstance().updatePlayListID());
       assertThat(response.statusCode(), equalTo(200));




    }




}
