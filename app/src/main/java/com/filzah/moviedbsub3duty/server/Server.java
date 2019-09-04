package com.filzah.moviedbsub3duty.server;

public class Server {
    private static final String BASE_URL = "https://api.themoviedb.org/";
    public  static final String API_KEY = "052cd671f4ad7b07cab0bec370af7128";


    public static InterfaceClass getApiClient(){
        return ServerConfig.getClient(BASE_URL).create(InterfaceClass.class);
    }
}
