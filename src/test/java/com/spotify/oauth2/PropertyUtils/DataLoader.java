package com.spotify.oauth2.PropertyUtils;

import java.util.Properties;

public class DataLoader {


        private  final Properties properties;
        private  static DataLoader dataLoader;


        private DataLoader(){

            properties=PropertyUtils.propertyLoader("src/test/resources/data.properties");
        }
        public static com.spotify.oauth2.PropertyUtils.DataLoader getInstance(){
            if (dataLoader==null)
            {
                dataLoader= new com.spotify.oauth2.PropertyUtils.DataLoader();

            }
            return dataLoader;
        }

        public String getPlayListID(){
            String prop= properties.getProperty("get_playlist_id");

            if (prop != null)
                return prop;
            else throw new RuntimeException("property is not specified in data.properties");
        }

    public String updatePlayListID(){
        String prop= properties.getProperty("update_playlist_id");

        if (prop != null)
            return prop;
        else throw new RuntimeException("property is not specified in data.properties");
    }




    }




