package com.restAssured.framework.spotify.utils;

import java.util.Properties;

public class DataLoader {

    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader(){
        properties = PropertiesUtils.propertiesLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance(){
        if(dataLoader == null){
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }


    public String getPlaylistID(){
        String prop = properties.getProperty("get_playlist_ID");
        if(prop != null)return prop;
        else throw new RuntimeException("Property get_playlist_ID is not specified in the config.properties file");
    }
    public String getUpdatePlaylistID(){
        String prop = properties.getProperty("update_playlist_ID");
        if(prop != null)return prop;
        else throw new RuntimeException("Property update_playlist_ID is not specified in the config.properties file");
    }



}
