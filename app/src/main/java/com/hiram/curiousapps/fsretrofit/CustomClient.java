package com.hiram.curiousapps.fsretrofit;

import com.hiram.curiousapps.fsretrofit.modelos.Get;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Hiram on 16/04/2017.
 */

public interface CustomClient {

    /*@GET("/{file}")
    Call<Custom> getData(@Path("file") String file);*/

    @GET("/get.php")
    Call<Get> getData(@Query("nombre") String nombre, @Query("edad") int edad, @Query("profesion") String profesion);
}
