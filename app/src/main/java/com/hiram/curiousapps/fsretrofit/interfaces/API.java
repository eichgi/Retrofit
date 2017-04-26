package com.hiram.curiousapps.fsretrofit.interfaces;

import com.hiram.curiousapps.fsretrofit.models.Language;
import com.hiram.curiousapps.fsretrofit.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Hiram on 16/04/2017.
 */

public interface API {

    @GET("/get.php")
    Call<User> getData(@Query("nombre") String nombre,
                      @Query("edad") int edad,
                      @Query("email") String email);

    @POST("/{file}")
    @FormUrlEncoded
    Call<User> postData(@Path("file") String file,
                        @Field("nombre") String nombre,
                        @Field("edad") int edad,
                        @Field("email") String email);

    @GET("/list.php")
    Call<List<Language>> getList();
}
