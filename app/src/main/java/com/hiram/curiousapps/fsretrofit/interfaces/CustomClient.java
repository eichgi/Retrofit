package com.hiram.curiousapps.fsretrofit.interfaces;

import com.hiram.curiousapps.fsretrofit.modelos.Get;
import com.hiram.curiousapps.fsretrofit.modelos.Language;
import com.hiram.curiousapps.fsretrofit.modelos.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Hiram on 16/04/2017.
 */

public interface CustomClient {

    @GET("/get.php")
    Call<Get> getData(@Query("nombre") String nombre,
                      @Query("edad") int edad,
                      @Query("email") String email);

    /*@POST("/post.php")
    Call<Post> postData(@Body Post post);*/

    @POST("/{file}")
    @FormUrlEncoded
    Call<Post> postData(@Path("file") String file,
                        @Field("nombre") String nombre,
                        @Field("edad") int edad,
                        @Field("email") String email);

    /** --------------------------------------------------------------------------------------- */


    /*@GET("/users/{user}/repos")
    Call<List<GitHubRepo>> reposForUser(@Path("user") String user);*/

    @GET("/list.php")
    Call<List<Language>> getList();
}
