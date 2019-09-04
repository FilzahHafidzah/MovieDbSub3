package com.filzah.moviedbsub3duty.server;


import com.filzah.moviedbsub3duty.model.ResponseMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InterfaceClass {

    @GET("3/discover/movie")
    Call<ResponseMovie> getMovie(@Query("api_key")String api_key,
                                 @Query("language")String language,
                                 @Query("sort_by")String sort_by,
                                 @Query("include_adult")String include_adult,
                                 @Query("include_video")String include_video,
                                 @Query("page")String page);

    @GET("3/discover/tv")
    Call<ResponseMovie> getTvshow(@Query("api_key")String api_key,
                                 @Query("language")String language,
                                 @Query("sort_by")String sort_by,
                                 @Query("include_adult")String include_adult,
                                 @Query("include_video")String include_video,
                                 @Query("page")String page);

    @GET("3/movie/popular")
    Call<ResponseMovie> getPopular(@Query("api_key")String api_key,
                                  @Query("language")String language,
                                  @Query("sort_by")String sort_by,
                                  @Query("include_adult")String include_adult,
                                  @Query("include_video")String include_video,
                                  @Query("page")String page);

    @GET("3/movie/upcoming")
    Call<ResponseMovie> getUpcoming(@Query("api_key")String api_key,
                                   @Query("language")String language,
                                   @Query("sort_by")String sort_by,
                                   @Query("include_adult")String include_adult,
                                   @Query("include_video")String include_video,
                                   @Query("page")String page);



}
