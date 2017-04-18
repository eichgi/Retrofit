package com.hiram.curiousapps.fsretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Hiram on 16/04/2017.
 */

public interface CustomClient {

    @GET("/{file}")
    Call<Custom> getData(@Path("file") String file);
}
